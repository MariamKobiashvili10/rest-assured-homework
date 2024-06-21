package ge.tbcacademy;

import ge.tbcacademy.data.steps.EmployeeStepsWithUnmarshalling;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EmployeeTestUnmarshalling {
    EmployeeStepsWithUnmarshalling employeeStepsWithUnmarshalling;

    @BeforeClass

    public void setUp(){
        employeeStepsWithUnmarshalling = new EmployeeStepsWithUnmarshalling();
    }

    @Description("This test case validates the adding employee and then asserting response")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Adding new employee using soap request body")
    @Test(priority = 1, description = "test of adding employee")
    public void addingNewEmployee() {
        employeeStepsWithUnmarshalling
                .createRequestBody()
                .wrappingBodyIntoSoapRequest()
                .sendingRequest()
                .validating();
    }

    @Description("This test case validates the getting employee and then asserting response")
    @Severity(SeverityLevel.CRITICAL)
    @Story("trying to get our added employee using Id ")
    @Test(priority = 2, description = "getting employee with id")
    public void gettingEmployeeWithId(){
        employeeStepsWithUnmarshalling.getEmployeeById()
                .validatingWithId();
    }
    @Description("This test case updates employee and then asserting response")
    @Severity(SeverityLevel.CRITICAL)
    @Story("trying to update our added employee and asserting")
    @Test(priority = 3,description = "updating employee")
         public void updatingEmployee() {

        employeeStepsWithUnmarshalling
                .updateEmployee()
                .assertingUpdatedResponse();
    }

    @Description("This test case validates the deleting employee and then asserting response")
    @Severity(SeverityLevel.CRITICAL)
    @Story("deleting new employee using id")
    @Test(priority = 4, description = "Deleting Employee")
                public void deleteEmployee(){
               employeeStepsWithUnmarshalling
                       .deletingEmployee()
                       .validatingResponseAfterDeleting();
    }

    @Description("in this case we are trying to get deleted employee with id")
    @Severity(SeverityLevel.CRITICAL)
    @Story("getting employee with id and asserting")
    @Test(priority = 5, description = "getting deleted Employee")
    public void gettingDeletedEmployee(){
        employeeStepsWithUnmarshalling .tryingTogetDeletedEmployee()
                .unmarshalling();
        employeeStepsWithUnmarshalling.validateErrorMessage();
    }
}
