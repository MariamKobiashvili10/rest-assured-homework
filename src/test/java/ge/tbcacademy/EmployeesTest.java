package ge.tbcacademy;

import ge.tbcacademy.steps.employeeSteps.EmployeesSteps;
import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


@Epic("Employees Management")
@Feature("Manage Employee Data")
public class EmployeesTest {
    EmployeesSteps employeesSteps;
    @BeforeClass
    public void setUp(){
        employeesSteps = new EmployeesSteps();
    }

            @Story("Adding new employee using soap request body, updating and deleting it")
            @Description("This test case validates the adding, updating deleting operations of employee and then asserting response")
            @Severity(SeverityLevel.CRITICAL)
            @Story("Adding new employee using soap request body, updating and deleting it")
            @Test(description = "test of adding, updating and deleting employee")
            public void addingEmployee() {
                employeesSteps
                        .createRequestBody()
                        .wrappingBodyIntoSoapRequest()
                        .sendingRequest()
                        .validating()
                        .getEmployeeById()
                        .validatingWithId()
                        .updateEmployee()
                        .assertingUpdatedResponse()
                        .deletingEmployee()
                        .tryingToGetDeletedEmployeeById();
        }

}
