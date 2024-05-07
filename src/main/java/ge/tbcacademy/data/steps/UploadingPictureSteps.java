package ge.tbcacademy.data.steps;

import ge.tbcacademy.data.ConstantsForPetStore;
import ge.tbcacademy.data.RequestSpecs;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class UploadingPictureSteps {
    RequestSpecification requestSpecification;
    Response response;

    public UploadingPictureSteps(){
        requestSpecification = RequestSpecs.getBaseRequestForPetStore();
    }
    public UploadingPictureSteps uploadingPicture(){
        File file = new File(ConstantsForPetStore.FILEPATH);
        response = given(requestSpecification)
                .log().all()
                .accept(ContentType.JSON)
                .contentType(ContentType.MULTIPART)
                .multiPart("file", file, ConstantsForPetStore.FILE_TYPE)
                .formParam("additionalMetadata", ConstantsForPetStore.METADATA)
                .when()
                .post(ConstantsForPetStore.PETS_ENDPOINT + ConstantsForPetStore.ID + ConstantsForPetStore.UPLOAD_PICTURE_ENDPOINT);
        return this;
    }
    public UploadingPictureSteps validatingMetadataInMessage(){
        response.then().assertThat()
                .body("message", containsString(ConstantsForPetStore.METADATA));
        return this;
    }
    public UploadingPictureSteps validatingImageInMessage(){
         response.then().assertThat().
                 body("message", containsString(ConstantsForPetStore.FILE_NAME));
         return this;
    }
    public UploadingPictureSteps validatingFileSize(){
        response.then().assertThat()
                .body("message", containsString(ConstantsForPetStore.FILESIZE));
        return this;
    }
}
