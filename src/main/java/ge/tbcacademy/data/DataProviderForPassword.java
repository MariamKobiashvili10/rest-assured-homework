package ge.tbcacademy.data;

import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;

public class DataProviderForPassword {
    @DataProvider(name = "passwordsData")
    public Object[][] passwords() {
        Faker faker = new Faker();

        return new Object[][]{
                {faker.lorem().characters(7)},
                {faker.regexify("[A-Z]+")},
                {faker.regexify("[a-z]+")},
                {faker.regexify("[0-9]+")},
                {faker.regexify("[^a-zA-Z0-9]+")},
                {faker.regexify("[A-Za-z0-9]{7}")},
                {"Mari1-?%"} // ვალიდური ხელით გავწერე
        };
    }
}
