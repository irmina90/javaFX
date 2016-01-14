package javafx.library.dataprovider;

import java.util.Collection;

import javafx.library.dataprovider.book.Book;
import javafx.library.dataprovider.impl.DataProviderImpl;

public interface DataProvider {

	DataProvider INSTANCE = new DataProviderImpl();

	Collection<Book> findBooks(String title);

	Book addBook(String title, String authors);
}
