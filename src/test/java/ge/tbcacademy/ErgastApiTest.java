package ge.tbcacademy;

import ge.tbcacademy.data.models.ergast.DriversItem;
import ge.tbcacademy.data.steps.ergast.ErgastSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class ErgastApiTest {
    ErgastSteps ergastSteps;
    @BeforeClass
    public void setUp(){
        ergastSteps = new ErgastSteps();
    }

    @Test
    public void ergastTest(){
        DriversItem item = ergastSteps.createResponseBody();
        ergastSteps
                .deserializing()
                .asserting(item);
    }
    }
