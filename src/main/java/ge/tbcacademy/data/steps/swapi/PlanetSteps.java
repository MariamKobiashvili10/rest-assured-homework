package ge.tbcacademy.data.steps.swapi;

import ge.tbcacademy.data.models.swapi.response.PlanetResponse;
import ge.tbcacademy.data.models.swapi.response.ResultsItem;
import io.qameta.allure.Step;
import lombok.Getter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PlanetSteps {


    private final PlanetResponse planetResponse;
    private List<ResultsItem> recentPlanets;

    public PlanetSteps(PlanetResponse planetResponse) {
        this.planetResponse = planetResponse;
    }

    @Step("Get three most recent planets")
    public PlanetSteps getThreeMostRecentPlanets() {
        recentPlanets = planetResponse.results().stream()
                .sorted(Comparator.comparing(ResultsItem::created).reversed())
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(recentPlanets);
        return this;
    }

    @Step("Validate planet fields")
    public PlanetSteps validatePlanetFields() {

        for (ResultsItem planet : recentPlanets) {
            assertThat(planet.name(), not(emptyOrNullString()));
            assertThat(planet.climate(),notNullValue());
            assertThat(planet.diameter(), notNullValue());
            assertThat(planet.rotationPeriod(), notNullValue());
        }
        return this;

    }

    @Step("Get top planet URL by rotation period")
    public PlanetSteps getTopPlanetUrlByRotationPeriod() {
        List<ResultsItem> planetsWithRotationPeriod = planetResponse.results().stream()
                .filter(planet -> planet.rotationPeriod() != null)
                .toList();

        Optional<ResultsItem> topPlanet = planetsWithRotationPeriod.stream()
                .max(Comparator.comparingInt(planet -> Integer.parseInt(planet.rotationPeriod())));

         topPlanet.map(ResultsItem::residents)
                .flatMap(residents -> residents.stream().findFirst())
                .orElse(null);
        System.out.println("Top planet with rotation period is " + topPlanet);
         return this;
    }
}