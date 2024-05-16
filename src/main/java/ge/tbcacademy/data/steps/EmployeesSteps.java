package ge.tbcacademy.data.steps;

import com.example.springboot.soap.interfaces.*;
import ge.tbcacademy.data.constants.ConstantsForSpring;
import ge.tbcacademy.data.util.CalendarHelper;
import ge.tbcacademy.data.util.HelperForResponseXpath;
import ge.tbcacademy.data.util.Marshall;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import javax.xml.datatype.XMLGregorianCalendar;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.xml.HasXPath.hasXPath;

public class EmployeesSteps {
    ObjectFactory objectFactory;
    EmployeeInfo employeeInfo;
    AddEmployeeRequest addEmployeeRequest;
String body;
Response response;
GetEmployeeByIdRequest getEmployeeByIdRequest;
    UpdateEmployeeRequest updateEmployeeRequest;

    @Step("creatingBody")
    public EmployeesSteps createRequestBody() {
        objectFactory = new ObjectFactory();
        employeeInfo = objectFactory.createEmployeeInfo();
        employeeInfo.setEmployeeId(ConstantsForSpring.ID);
        employeeInfo.setName(ConstantsForSpring.FIRST_NAME);
        employeeInfo.setEmail(ConstantsForSpring.EMAIL);
        employeeInfo.setAddress(ConstantsForSpring.ADDRESS);
        employeeInfo.setPhone(ConstantsForSpring.NUMBER);
        XMLGregorianCalendar birthDate = CalendarHelper.createXMLGregorianCalendar(2001, 10, 10);
        employeeInfo.setBirthDate(birthDate);
        return this;
    }

    @Step("Wrapping body into soap request")
    public EmployeesSteps wrappingBodyIntoSoapRequest(){
        addEmployeeRequest = new AddEmployeeRequest();
        addEmployeeRequest.setEmployeeInfo(employeeInfo);
        return this;
    }
    @Step("Sending Request")
    public EmployeesSteps sendingRequest(){
       body = Marshall.marshallSoapRequest(addEmployeeRequest);
       response = given()
               .header("Content-Type", "text/xml; charset=utf-8")
               .header("SOAPAction", "interfaces.soap.springboot.example.com/exampleSoapHttp/addEmployeeRequest")
               .body(body)
               .filter(new AllureRestAssured())
               .post(ConstantsForSpring.URL);
       response.then().statusCode(200);

        return this;
    }

    @Step("Validating that it was added")
    public EmployeesSteps validating(){
        response
                .then()
                .assertThat()
                .body(hasXPath("//ns2:message",  new HelperForResponseXpath(), containsString(ConstantsForSpring.ADDED)));
        return this;
    }
    @Step("Getting employee By ID")
    public EmployeesSteps getEmployeeById(){
        getEmployeeByIdRequest = objectFactory.createGetEmployeeByIdRequest();
        getEmployeeByIdRequest.setEmployeeId(employeeInfo.getEmployeeId());
         body = Marshall.marshallSoapRequest(getEmployeeByIdRequest);


        response = given()
                .header("Content-Type", "text/xml; charset=utf-8")
                .header("SOAPAction", "interfaces.soap.springboot.example.com/exampleSoapHttp/getEmployeeByIdRequest")
                .body(body)
                .filter(new AllureRestAssured())
                .post(ConstantsForSpring.URL)
        response.then().statusCode(200);

        return this;
    }
    @Step("Validating returned employee")
    public EmployeesSteps validatingWithId(){
        response.then()
                .assertThat()
                .body(hasXPath("//ns2:name", new HelperForResponseXpath(), containsString(ConstantsForSpring.FIRST_NAME)))
                .body(hasXPath("//ns2:phone", new HelperForResponseXpath(), containsString(ConstantsForSpring.NUMBER)))
                .body(hasXPath("//ns2:birthDate", new HelperForResponseXpath(), containsString("2001-10-10")))
                .body(hasXPath("//ns2:address", new HelperForResponseXpath(), containsString(ConstantsForSpring.ADDRESS)));
    return this;
    }

    @Step("updating employee")
    public EmployeesSteps updateEmployee(){
        updateEmployeeRequest = objectFactory.createUpdateEmployeeRequest();
        employeeInfo.setName(ConstantsForSpring.NEW_NAME);
        updateEmployeeRequest.setEmployeeInfo(employeeInfo);
        body = Marshall.marshallSoapRequest(updateEmployeeRequest);
        response = given()
                .header("Content-Type", "text/xml; charset=utf-8")
                .header("SOAPAction", "interfaces.soap.springboot.example.com/exampleSoapHttp/updateEmployeeRequest")
                .body(body)
                .filter(new AllureRestAssured())
                .post(ConstantsForSpring.URL);
        response.then().statusCode(200);


        return this;
    }
    @Step("validating updated responses message")
    public EmployeesSteps assertingUpdatedResponse(){
        response.then()
                .assertThat()
                .body(hasXPath("//ns2:message", new HelperForResponseXpath(), containsString(ConstantsForSpring.UPDATED)));

        return this;
    }

    @Step("Deleting employee  with id")
    public EmployeesSteps deletingEmployee(){
        DeleteEmployeeRequest deleteEmployeeRequest = objectFactory.createDeleteEmployeeRequest();
        deleteEmployeeRequest.setEmployeeId(employeeInfo.getEmployeeId());
        body = Marshall.marshallSoapRequest(deleteEmployeeRequest);
        response = given()
                .header("Content-Type", "text/xml; charset=utf-8")
                .header("SOAPAction", "interfaces.soap.springboot.example.com/exampleSoapHttp/deleteEmployeeRequest")
                .body(body)
                .filter(new AllureRestAssured())
                .post(ConstantsForSpring.URL);
        response.then().statusCode(200)
                .body(hasXPath("//ns2:message", new HelperForResponseXpath(), containsString(ConstantsForSpring.DELETED)));;
        return this;
    }
    @Step("Validating Deleted employee")
    public EmployeesSteps validatingDeletedEmployee(){
        body = Marshall.marshallSoapRequest(getEmployeeByIdRequest);

        response = given()
                .header("Content-Type", "text/xml; charset=utf-8")
                .header("SOAPAction", "interfaces.soap.springboot.example.com/exampleSoapHttp/getEmployeeByIdRequest")
                .body(body)
                .filter(new AllureRestAssured())
                .post(ConstantsForSpring.URL);

        response.then().statusCode(500)
                .body(hasXPath("//soapenv:Fault", new HelperForResponseXpath(), containsString(ConstantsForSpring.ERROR_MESSAGE)));;
        return this;
    }

}
