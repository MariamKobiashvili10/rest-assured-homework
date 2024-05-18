package ge.tbcacademy.data.steps;

import com.example.springboot.soap.interfaces.*;
import ge.tbcacademy.data.constants.ConstantsForSpring;
import ge.tbcacademy.data.util.CalendarHelper;
import ge.tbcacademy.data.util.Marshall;
import ge.tbcacademy.data.util.Unmarshall;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;

import javax.xml.datatype.XMLGregorianCalendar;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class EmployeeStepsWithUnmarshalling {
    ObjectFactory objectFactory;
    EmployeeInfo employeeInfo;
    AddEmployeeRequest addEmployeeRequest;
    String body;
    Response response;
    String errorMessage;
    GetEmployeeByIdRequest getEmployeeByIdRequest;
    UpdateEmployeeRequest updateEmployeeRequest;
    String responseAsString;
GetEmployeeByIdResponse getEmployeeByIdResponse;
  @Step("creating Body")
    public EmployeeStepsWithUnmarshalling createRequestBody() {
        objectFactory = new ObjectFactory();
        employeeInfo = objectFactory.createEmployeeInfo();

        XMLGregorianCalendar birthDate = CalendarHelper.createXMLGregorianCalendar(2001, 10, 10);

        employeeInfo.withEmployeeId(ConstantsForSpring.ID)
                .withAddress(ConstantsForSpring.ADDRESS)
                .withEmail(ConstantsForSpring.EMAIL)
                .withBirthDate(birthDate)
                .withPhone(ConstantsForSpring.NUMBER)
                .withName(ConstantsForSpring.FIRST_NAME)
                .withDepartment(ConstantsForSpring.DEPARTMENT);
        return this;
    }

    @Step("Wrapping body into soap request")
    public EmployeeStepsWithUnmarshalling wrappingBodyIntoSoapRequest(){
        addEmployeeRequest = new AddEmployeeRequest();
        addEmployeeRequest.withEmployeeInfo(employeeInfo);
        return this;
    }
    @Step("Sending Request")
    public EmployeeStepsWithUnmarshalling sendingRequest(){
        body = Marshall.marshallSoapRequest(addEmployeeRequest);
        response = given()
                .header("Content-Type", "text/xml; charset=utf-8")
                .header("SOAPAction", "interfaces.soap.springboot.example.com/exampleSoapHttp/addEmployeeRequest")
                .body(body)
                .filter(new AllureRestAssured())
                .post(ConstantsForSpring.URL)
                .then().extract().response();

        response.then().statusCode(200);
        return this;
    }

    @Step("Validating that it was added")
    public EmployeeStepsWithUnmarshalling validating(){
        responseAsString = response.body().asString();

        AddEmployeeResponse addEmployeeResponse = Unmarshall.unmarshallResponse(responseAsString, AddEmployeeResponse.class);

       assertThat(addEmployeeResponse.getServiceStatus().getMessage(), equalTo(ConstantsForSpring.ADDED));
       return this;
    }
    @Step("Getting employee By ID")
    public EmployeeStepsWithUnmarshalling getEmployeeById(){
        getEmployeeByIdRequest = objectFactory.createGetEmployeeByIdRequest();
        getEmployeeByIdRequest.withEmployeeId(employeeInfo.getEmployeeId());
        body = Marshall.marshallSoapRequest(getEmployeeByIdRequest);


        response = given()
                .header("Content-Type", "text/xml; charset=utf-8")
                .header("SOAPAction", "interfaces.soap.springboot.example.com/exampleSoapHttp/getEmployeeByIdRequest")
                .body(body)
                .filter(new AllureRestAssured())
                .post(ConstantsForSpring.URL);
        response.then().statusCode(200).extract().response();

        return this;
    }
    @Step("Validating returned employee")
    public EmployeeStepsWithUnmarshalling validatingWithId(){
        responseAsString = response.body().asString();
        GetEmployeeByIdResponse getEmployeeByIdResponse = Unmarshall.unmarshallResponse(responseAsString, GetEmployeeByIdResponse.class);
        assertThat(getEmployeeByIdResponse.getEmployeeInfo().getEmployeeId(), equalTo(employeeInfo.getEmployeeId()));
        assertThat(getEmployeeByIdResponse.getEmployeeInfo().getAddress(), equalTo(employeeInfo.getAddress()));
        assertThat(getEmployeeByIdResponse.getEmployeeInfo().getEmail(), equalTo(employeeInfo.getEmail()));
        assertThat(getEmployeeByIdResponse.getEmployeeInfo().getAddress(), equalTo(employeeInfo.getAddress()));
        assertThat(getEmployeeByIdResponse.getEmployeeInfo().getName(), equalTo(employeeInfo.getName()));
        assertThat(getEmployeeByIdResponse.getEmployeeInfo().getDepartment(), equalTo(employeeInfo.getDepartment()));
        return this;
    }

    @Step("updating employee")
    public EmployeeStepsWithUnmarshalling updateEmployee(){
        updateEmployeeRequest = objectFactory.createUpdateEmployeeRequest();
        employeeInfo.withName(ConstantsForSpring.NEW_NAME);
        updateEmployeeRequest.withEmployeeInfo(employeeInfo);
        body = Marshall.marshallSoapRequest(updateEmployeeRequest);
        response = given()
                .header("Content-Type", "text/xml; charset=utf-8")
                .header("SOAPAction", "interfaces.soap.springboot.example.com/exampleSoapHttp/updateEmployeeRequest")
                .body(body)
                .filter(new AllureRestAssured())
                .post(ConstantsForSpring.URL)
                        .then().extract().response();
        response.then().statusCode(200);


        return this;
    }
    @Step("validating updated responses message")
    public EmployeeStepsWithUnmarshalling assertingUpdatedResponse(){
        responseAsString = response.body().asString();
        UpdateEmployeeResponse updateEmployeeResponse = Unmarshall.unmarshallResponse(responseAsString, UpdateEmployeeResponse.class);
        assertThat(updateEmployeeResponse.getServiceStatus().getMessage(), equalTo(ConstantsForSpring.UPDATED));
        return this;
    }

    @Step("Deleting employee  with id")
    public EmployeeStepsWithUnmarshalling deletingEmployee() {
        DeleteEmployeeRequest deleteEmployeeRequest = objectFactory.createDeleteEmployeeRequest();
        deleteEmployeeRequest.withEmployeeId(employeeInfo.getEmployeeId());
        body = Marshall.marshallSoapRequest(deleteEmployeeRequest);
        response = given()
                .header("Content-Type", "text/xml; charset=utf-8")
                .header("SOAPAction", "interfaces.soap.springboot.example.com/exampleSoapHttp/deleteEmployeeRequest")
                .body(body)
                .filter(new AllureRestAssured())
                .post(ConstantsForSpring.URL).then().extract().response();
        response.then().statusCode(200);
     return this;
    }
    @Step("Validating that employee was deleted")
    public EmployeeStepsWithUnmarshalling validatingResponseAfterDeleting(){
        responseAsString = response.body().asString();
        DeleteEmployeeResponse deleteEmployeeResponse = Unmarshall.unmarshallResponse(responseAsString, DeleteEmployeeResponse.class);
        assertThat(deleteEmployeeResponse.getServiceStatus().getMessage(), equalTo(ConstantsForSpring.DELETED));
        return this;
    }

    @Step("Validating Deleted employee")
    public EmployeeStepsWithUnmarshalling tryingTogetDeletedEmployee(){
        body = Marshall.marshallSoapRequest(getEmployeeByIdRequest);

        response = given()
                .header("Content-Type", "text/xml; charset=utf-8")
                .header("SOAPAction", "interfaces.soap.springboot.example.com/exampleSoapHttp/getEmployeeByIdRequest")
                .body(body)
                .filter(new AllureRestAssured())
                .post(ConstantsForSpring.URL);
        return this;
    }
    @Step("Getting error message")
    public GetEmployeeByIdResponse unmarshalling(){
        responseAsString = response.body().asString();
        try {
            return getEmployeeByIdResponse = Unmarshall.unmarshallResponse(responseAsString, GetEmployeeByIdResponse.class);
        } catch (RuntimeException e) {
            errorMessage = e.getMessage();
        }
        return getEmployeeByIdResponse;
    }
    @Step("Validate null source message")
    public EmployeeStepsWithUnmarshalling validateErrorMessage() {
        assertThat(errorMessage, equalTo(ConstantsForSpring.ERROR_MESSAGE));
        return this;
    }

}
