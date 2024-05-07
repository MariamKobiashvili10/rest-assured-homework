package ge.tbcacademy;

import ge.tbcacademy.data.ConstantsForPetStore;
import ge.tbcacademy.data.steps.NewPetAddingSteps;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddingNewPetTest {
    public static NewPetAddingSteps newPetAddingSteps;

    @BeforeClass
    public static void beforeClass(){
        newPetAddingSteps = new NewPetAddingSteps();

    }
    @Test
    public void addingNewPetAndOtherThings(){
        JSONObject body = newPetAddingSteps.createParametrizedRequestBody();

        newPetAddingSteps.addingPet(body)
                .ensuringStatusCode()
                .ensuringPetWasAdded()
                .findByStatus()
                .validateResponseContainsId(ConstantsForPetStore.ID)
                .validatePetObject(body)
                .updatePetWithFormData(ConstantsForPetStore.ID)
                .getNewPetWithId(ConstantsForPetStore.ID)
                .validateUpdatedNameAndStatus();

    }
}
