package ge.tbcacademy;

import ge.tbcacademy.data.constants.ConstantsForPetStore;
import ge.tbcacademy.data.steps.petstore.NewPetAddingStepsAndPictureUpload;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddingNewPetTest {
    public static NewPetAddingStepsAndPictureUpload newPetAddingStepsAndPictureUpload;

    @BeforeClass
    public static void beforeClass(){
        newPetAddingStepsAndPictureUpload = new NewPetAddingStepsAndPictureUpload();

    }
    @Test
    public void addingNewPetAndOtherThings(){


        newPetAddingStepsAndPictureUpload.addingPet()
                .ensuringStatusCode()
                .ensurePetWasAdded()
                .findByStatus()
                .validateResponseContainsId(ConstantsForPetStore.ID)
                .updatePetWithFormData(ConstantsForPetStore.ID)
                .getNewPetWithId(ConstantsForPetStore.ID)
                .validateUpdatedNameAndStatus();
    }
    @Test
    public void updateImage(){
        newPetAddingStepsAndPictureUpload.uploadingPicture()
                .validatingMetadataInMessage()
                .validatingImageInMessage()
                .validatingFileSize();
    }
}
