package ge.tbcacademy.data.steps;

import ge.tbcacademy.data.constants.ConstantsForSpring;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;
import spring.security.api.AuthenticationApi;
import spring.security.api.AuthorizationApi;
import spring.security.invoker.ApiClient;
import spring.security.invoker.JacksonObjectMapper;
import spring.security.model.AuthenticationRequest;
import spring.security.model.AuthenticationResponse;
import spring.security.model.RefreshTokenRequest;
import spring.security.model.RegisterRequest;

import static io.restassured.config.ObjectMapperConfig.objectMapperConfig;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.AssertJUnit.assertTrue;
import static spring.security.invoker.ResponseSpecBuilders.shouldBeCode;
import static spring.security.invoker.ResponseSpecBuilders.validatedWith;

public class SpringSteps {
    AuthenticationResponse authenticationResponse;
    @Getter
    @Setter
    String accessToken;
    @Getter
    @Setter
    RegisterRequest registerRequest;
    AuthenticationApi.AuthenticateOper authenticateOper;
    AuthenticationApi.RegisterOper registerOper;
    AuthorizationApi authorizationApi;
    @Setter
    AuthenticationApi.RefreshTokenOper refreshToken;
    ApiClient apiClient;
    @Step("Create API client")
    public SpringSteps getApi(){
        apiClient = ApiClient.api(ApiClient.Config.apiConfig()
                .reqSpecSupplier(() -> new RequestSpecBuilder()
                        .log(LogDetail.ALL)
                        .setConfig(RestAssuredConfig.config()
                                .objectMapperConfig(objectMapperConfig()
                                        .defaultObjectMapper(JacksonObjectMapper.jackson())))
                        .addFilter(new ErrorLoggingFilter())
                        .addFilter(new AllureRestAssured())
                        .setBaseUri(ConstantsForSpring.BASE_URI)));
        return this;
    }
    @Step("Making register Request ")
    public SpringSteps makingRegisterRequest(String password){
        registerRequest = new RegisterRequest()
                .firstname(ConstantsForSpring.FIRST_NAME)
                .lastname(ConstantsForSpring.LAST_NAME)
                .email(ConstantsForSpring.EMAIL)
                .password(password)
                .role(RegisterRequest.RoleEnum.ADMIN);

        registerOper = apiClient.authentication().register().body(registerRequest);
        registerOper.executeAs(validatedWith(shouldBeCode(SC_OK)));

        return this;
    }
    @Step("Setting access token from response")
    public SpringSteps settingAccessToken(){
        authenticationResponse = registerOper.executeAs(response1 -> response1);
        System.out.println(authenticationResponse);
        setAccessToken(authenticationResponse.getAccessToken());
        return this;
    }
    @Step("Authorizing")
    public SpringSteps authorizing(){
        apiClient.authorization().reqSpec(requestSpecBuilder -> requestSpecBuilder.addHeader("Authorization", "Bearer " + accessToken))
                .sayHelloWithRoleAdminAndReadAuthority();
        apiClient.authentication().authenticate().body(new AuthenticationRequest()
                .email(registerRequest.email()).password(registerRequest.password()));

        return this;
    }
    @Step("Asserting privileges")
    public SpringSteps asserting(){
        assertThat(authenticationResponse.roles(), containsInAnyOrder(ConstantsForSpring.
                ROLE_ADMIN, ConstantsForSpring.READ_PRIVILEGE, ConstantsForSpring.WRITE_PRIVILEGE, ConstantsForSpring.DELETE_PRIVILEGE, ConstantsForSpring.UPDATE_PRIVILEGE));
    return this;
    }

    @Step("Refreshing Token")
    public SpringSteps refreshToken(){
                    AuthenticationApi.RefreshTokenOper refreshTokenOper = apiClient.authentication().refreshToken()
                            .body(new RefreshTokenRequest().refreshToken(authenticationResponse.refreshToken()));
            refreshTokenOper.executeAs(response -> response).refreshToken();
            return this;
    }
    @Step("Checking that old token is not valid with code unathorized")
    public SpringSteps authorizingWithOldToken(){
        authorizationApi = apiClient.authorization().reqSpec(requestSpecBuilder -> requestSpecBuilder.addHeader("Authorization", "Bearer " + accessToken));
            authenticateOper = apiClient.authentication().authenticate().body(new AuthenticationRequest()
                    .email(registerRequest.email()).password(registerRequest.password()));
            authenticationResponse = authenticateOper.executeAs(validatedWith(shouldBeCode(SC_UNAUTHORIZED)));
            return this;
    }
}

