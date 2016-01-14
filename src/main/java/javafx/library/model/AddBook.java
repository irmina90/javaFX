package javafx.library.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AddBook {

	private final StringProperty title = new SimpleStringProperty();
	private final StringProperty author = new SimpleStringProperty();

	public final String getTitle() {
		return title.get();
	}

	public final void setTitle(String value) {
		title.set(value);
	}

	public StringProperty titleProperty() {
		return title;
	}

	public final void setAuthor(String value) {
		author.set(value);
	}

	public String getAuthor() {
		return author.get();
	}

	public StringProperty authorProperty() {
		return author;
	}

}
