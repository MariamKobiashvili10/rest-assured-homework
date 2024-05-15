package ge.tbcacademy;

import ge.tbcacademy.data.DataProviderForPassword;
import ge.tbcacademy.data.steps.SpringSteps;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class SpringTests {
    SpringSteps springSteps;
    @BeforeSuite
    public void getApi() {
        springSteps = new SpringSteps();
        springSteps.getApi();
    }

    @Description("This test validates the registration process with authentication formats.")
    @Story("Registration and authentication on springRest")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "passwordsData", dataProviderClass = DataProviderForPassword.class, description = "authorizing using tokens")
    public void authTest(String password) {
        springSteps.makingRegisterRequest(password)
                .settingAccessToken()
                .authorizing()
                .asserting()
                .refreshToken()
                .authorizingWithOldToken();

    }
}


