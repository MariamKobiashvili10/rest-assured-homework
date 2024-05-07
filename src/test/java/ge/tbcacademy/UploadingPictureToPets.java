package ge.tbcacademy;

import ge.tbcacademy.data.steps.UploadingPictureSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UploadingPictureToPets {

    public static UploadingPictureSteps uploadingPictureSteps;

    @BeforeClass

    public void beforeClass(){

        uploadingPictureSteps= new UploadingPictureSteps();
    }
    @Test
    public void updateImage(){
        uploadingPictureSteps.uploadingPicture()
                .validatingMetadataInMessage()
                .validatingImageInMessage()
                .validatingFileSize();

    }
}
