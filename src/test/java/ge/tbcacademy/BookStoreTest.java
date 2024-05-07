package ge.tbcacademy;

import ge.tbcacademy.data.Constants;
import ge.tbcacademy.data.ConstantsForBookStore;
import ge.tbcacademy.data.ConstantsForPetStore;
import ge.tbcacademy.data.steps.BookStoreSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BookStoreTest {
    public static BookStoreSteps bookStoreSteps;

    @BeforeClass
    public static void BeforeClass(){
        bookStoreSteps = new BookStoreSteps();
    }
    @Test
    public void testingBooks(){
        bookStoreSteps.
                getSpecificResponse()
                .validatingPages()
                .validatingAuthors(ConstantsForBookStore.AUTHOR1, ConstantsForBookStore.AUTHOR2);
    }
}
