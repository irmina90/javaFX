package javafx.library.dataprovider.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import javafx.library.dataprovider.DataProvider;
import javafx.library.dataprovider.data.Book;

public class DataProviderImpl implements DataProvider {

	private static final Logger LOG = Logger.getLogger(DataProviderImpl.class);

	public DataProviderImpl() {
	}

	@Override
	public Collection<Book> findBooks(String title) {
		LOG.debug("Entering findBooks()");
		Collection<Book> result = new ArrayList<>();
		String url = "http://localhost:9721/workshop/rest/books/books-by-title?titlePrefix=" + title;

		// ObjectMapper mapper = new ObjectMapper();
		// Book book = mapper.readValue(new URL(url), Book.class);
		// result.add(book);

		LOG.debug("Leaving findBooks()");

		return result;
	}
}
