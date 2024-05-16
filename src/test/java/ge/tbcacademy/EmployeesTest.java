package ge.tbcacademy;

import ge.tbcacademy.data.steps.EmployeesSteps;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
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
            @Test(description = "making some actions  about employees")
            @Story("Adding, updating, and deleting employee records")
            @Description("This test case verifies the complete lifecycle of an employee record: adding, updating, and deleting")
            public void addingEmployee(){
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
                .validatingDeletedEmployee();


        }

}
