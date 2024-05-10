package ge.tbcacademy;

import ge.tbcacademy.data.models.swapi.request.PlanetRequest;
import ge.tbcacademy.data.models.swapi.response.PlanetResponse;
import ge.tbcacademy.data.models.swapi.response.ResultsItem;
import ge.tbcacademy.data.steps.swapi.PlanetSteps;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PlanetsTest {
    private PlanetSteps planetSteps;
    private final PlanetRequest planetRequest = new PlanetRequest();

    @BeforeClass
    public void setUp() {

        Response response = planetRequest.getPlanets();


        PlanetResponse planetResponse = response.as(PlanetResponse.class);

        planetSteps = new PlanetSteps(planetResponse);
    }

    @Test
    public void testPlanetOperations() {
       planetSteps
               .getThreeMostRecentPlanets()
               .validatePlanetFields();
        System.out.println("The url is " + planetSteps.getTopPlanetUrlByRotationPeriod());
    }
}