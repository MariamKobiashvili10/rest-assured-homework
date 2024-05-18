package ge.tbcacademy;

import ge.tbcacademy.data.steps.EmployeeStepsWithUnmarshalling;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EmployeeTestUnmarshalling {
    EmployeeStepsWithUnmarshalling employeeStepsWithUnmarshalling;

    @BeforeClass

    public void setUp(){
        employeeStepsWithUnmarshalling = new EmployeeStepsWithUnmarshalling();
    }

    @Test(priority = 1)
    public void addingNewEmployee() {
        employeeStepsWithUnmarshalling
                .createRequestBody()
                .wrappingBodyIntoSoapRequest()
                .sendingRequest()
                .validating();
    }
    @Test(priority = 2)
         public void updatingEmployee() {

        employeeStepsWithUnmarshalling.getEmployeeById()
                .validatingWithId()
                .updateEmployee()
                .assertingUpdatedResponse();
    }

        @Test(priority = 3)
                public void deleteEmployee(){
               employeeStepsWithUnmarshalling
                       .deletingEmployee()
                       .validatingResponseAfterDeleting();
    }

    @Test(priority = 4)
    public void gettingDeletedEmployee(){
        employeeStepsWithUnmarshalling .tryingTogetDeletedEmployee()
                .unmarshalling();
        employeeStepsWithUnmarshalling.validateErrorMessage();
    }
}
