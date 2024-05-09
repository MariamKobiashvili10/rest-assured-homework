package ge.tbcacademy.data.steps.ergast;

import ge.tbcacademy.data.constants.ErgastConstants;
import ge.tbcacademy.data.models.ergast.DriversItem;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ErgastSteps {
    DriversItem driversItem;
    Response response;
    public ErgastSteps deserializing(){

         response = RestAssured.get(ErgastConstants.URL);

        driversItem = response.jsonPath().getObject("MRData.DriverTable.Drivers[0]", DriversItem.class);
        return this;
    }

    public DriversItem createResponseBody(){
        DriversItem expectedDriver = new DriversItem();
        expectedDriver.setDriverId(ErgastConstants.DRIVER_ID);
        expectedDriver.setPermanentNumber(ErgastConstants.PERMANENT_NUMBER);
        expectedDriver.setCode(ErgastConstants.CODE);
        expectedDriver.setUrl(ErgastConstants.DRIVER_URL);
        expectedDriver.setGivenName(ErgastConstants.GIVEN_NAME);
        expectedDriver.setFamilyName(ErgastConstants.FAMILY_NAME);
        expectedDriver.setDateOfBirth(ErgastConstants.DATE_OF_BIRTH);
        expectedDriver.setNationality(ErgastConstants.NATIONALITY);
        return expectedDriver;
    }
    public ErgastSteps asserting(DriversItem item) {
        assertThat(driversItem.toString(), equalTo(item.toString()));
        return this;
    }
}
