package javafx.library.dataprovider.book;

public class Book {
	private int id;
	private String title;
	private String authors;

	public Book() {

	}

	public Book(String name, String authors) {
		this.title = name;
		this.authors = authors;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String name) {
		this.title = name;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Person [name=" + title + ", author=" + getAuthors() + "]";
	}

}
