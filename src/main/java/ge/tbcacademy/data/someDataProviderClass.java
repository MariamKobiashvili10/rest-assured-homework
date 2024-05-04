package ge.tbcacademy.data;

import org.testng.annotations.DataProvider;

public class someDataProviderClass {
    @DataProvider(name = "indexAndISBNData")
    public Object[][] bookData() {
        int numberOfBooks = 8;

        Object[][] data = new Object[numberOfBooks][2];
        for (int i = 0; i < numberOfBooks; i++) {
            data[i][0] = i;
            data[i][1] = "books[" + i + "].isbn";
        }
        return data;
    }
}
