package javafx.library.model;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.library.dataprovider.book.Book;

public class BookSearch {

	private final StringProperty title = new SimpleStringProperty();
	private final StringProperty author = new SimpleStringProperty();
	private final ListProperty<Book> result = new SimpleListProperty<>(FXCollections.observableList(new ArrayList<>()));

	public BookSearch() {

	}

	public BookSearch(String title, String author) {
		setTitle(title);
		setAuthor(author);
	}

	public final String getTitle() {
		return title.get();
	}

	public final void setTitle(String value) {
		title.set(value);
	}

	public StringProperty titleProperty() {
		return title;
	}

	public final String getAuthor() {
		return author.get();
	}

	public final void setAuthor(String value) {
		author.set(value);
	}

	public StringProperty authorProperty() {
		return author;
	}

	public final List<Book> getResult() {
		return result.get();
	}

	public final void setResult(List<Book> value) {
		result.setAll(value);
	}

	public final void setOneResult(Book value) {
		result.add(value);
	}

	public ListProperty<Book> resultProperty() {
		return result;
	}

	@Override
	public String toString() {
		return "BookSearch [name=" + title + ", result=" + result + "]";
	}

}
