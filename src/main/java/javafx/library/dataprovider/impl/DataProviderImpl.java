package javafx.library.dataprovider.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import javafx.library.dataprovider.DataProvider;
import javafx.library.dataprovider.book.Book;

public class DataProviderImpl implements DataProvider {

	private static final Logger LOG = Logger.getLogger(DataProviderImpl.class);
	private static final String GET = "http://localhost:9721/workshop/books-by-title";
	private static final String POST = "http://localhost:9721/workshop/book";

	public DataProviderImpl() {
	}

	@Override
	public Collection<Book> findBooks(String title) {
		LOG.debug("Entering findBooks()");
		Collection<Book> result = new ArrayList<>();

		try {
			Client client = Client.create();
			WebResource webResource = client.resource(GET);
			if (title == null)
				title = "";

			ClientResponse response = webResource.queryParam("titlePrefix", title).accept("application/json")
					.get(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			String output = response.getEntity(String.class);

			ObjectMapper mapper = new ObjectMapper();
			Book[] books = mapper.readValue(output, Book[].class);
			for (Book book : books) {
				result.add(book);
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		LOG.debug("Leaving findBooks()");

		return result;
	}

	@Override
	public Book addBook(String title, String authors) {
		LOG.debug("Entering addBook()");
		Book book = new Book(title, authors);

		try {

			Client client = Client.create();

			WebResource webResource = client.resource(POST);

			ObjectMapper mapper = new ObjectMapper();
			String jsonBook = mapper.writeValueAsString(book);

			ClientResponse response = webResource.type("application/json").post(ClientResponse.class, jsonBook);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			LOG.debug("Leaving addBook()");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return book;

	}

}
