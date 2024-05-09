package ge.tbcacademy.data.models.bookstore;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Books {

	@JsonProperty("books")
	private List<BooksItem> books;

	public void setBooks(List<BooksItem> books){
		this.books = books;
	}

	public List<BooksItem> getBooks(){
		return books;
	}

	@Override
 	public String toString(){
		return 
			"Booking{" + 
			"books = '" + books + '\'' + 
			"}";
		}
}