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


            @Description("This test case validates the adding employee and then asserting response")
            @Severity(SeverityLevel.CRITICAL)
            @Story("Adding new employee using soap request body")
            @Test(priority = 1, description = "test of adding employee")
            public void addingEmployee() {
                employeesSteps
                        .createRequestBody()
                        .wrappingBodyIntoSoapRequest()
                        .sendingRequest()
                        .validating();
            }
    @Description("This test case validates the getting employee and then asserting response")
    @Severity(SeverityLevel.CRITICAL)
    @Story("trying to get our added employee using Id ")
            @Test(priority = 2,description = "test of getting Employee By Id")
            public void gettingEmployee(){
                        employeesSteps.getEmployeeById()
                        .validatingWithId();
            }


    @Description("This test case validates the update employee and then asserting response")
    @Severity(SeverityLevel.CRITICAL)
    @Story("updating  employees name and then validating")
    @Test(priority = 3, description = "Updating employee")
    public void UpdatingEmployee() {
        employeesSteps.updateEmployee()
                .assertingUpdatedResponse();
            }


    @Description("This test case validates the deleting employee and then asserting response")
    @Severity(SeverityLevel.CRITICAL)
    @Story("deleting new employee using id")
    @Test(priority = 4, description = "Deleting Employee")
    public void deletingEmployee(){
        employeesSteps
                .deletingEmployee()
                .tryingToGetDeletedEmployeeById();
        }

}
