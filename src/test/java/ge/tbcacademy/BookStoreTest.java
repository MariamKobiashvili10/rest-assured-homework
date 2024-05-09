package ge.tbcacademy;

import ge.tbcacademy.data.constants.ConstantsForBookStore;
import ge.tbcacademy.data.steps.bookStore.BookStoreSteps;
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
    @Test
    public void testingBooks2(){
        bookStoreSteps.getSpecificResponse()
                .validatingPages()
                .validatinglastTwoAuthors(ConstantsForBookStore.LAST_AUTHOR, ConstantsForBookStore.BEFORE_LAST_AUTHOR);
    }
}
