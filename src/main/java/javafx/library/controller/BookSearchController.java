package javafx.library.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.library.dataprovider.DataProvider;
import javafx.library.dataprovider.data.Book;
import javafx.library.model.BookSearch;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BookSearchController {

	private static final Logger LOG = Logger.getLogger(BookSearchController.class);

	@FXML
	private Stage searchBookStage;

	@FXML
	private Scene searchBookScene;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField titleField;

	@FXML
	private Button searchButton;

	@FXML
	private Button addBookWindowButton;

	@FXML
	private TableView<Book> resultTable;

	@FXML
	private TableColumn<Book, String> titleColumn;

	@FXML
	private TableColumn<Book, String> authorColumn;

	private final DataProvider dataProvider = DataProvider.INSTANCE;

	private final BookSearch model = new BookSearch();

	public BookSearchController() {
		LOG.debug("Constructor: nameField = " + titleField);
	}

	@FXML
	private void initialize() {
		LOG.debug("initialize(): nameField = " + titleField);

		initializeResultTable();
		titleField.textProperty().bindBidirectional(model.titleProperty());
		resultTable.itemsProperty().bind(model.resultProperty());
	}

	private void initializeResultTable() {
		titleColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getTitle()));
		authorColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getAuthors()));
	}

	@FXML
	private void searchButtonAction(ActionEvent event) {
		LOG.debug("'Search' button clicked");

		Task<Collection<Book>> backgroundTask = new Task<Collection<Book>>() {

			@Override
			protected Collection<Book> call() throws Exception {
				LOG.debug("call() called");
				Collection<Book> result = dataProvider.findBooks(model.getTitle());
				return result;
			}

			@Override
			protected void succeeded() {
				LOG.debug("succeeded() called");

				Collection<Book> result = getValue();
				model.setResult(new ArrayList<Book>(result));
				resultTable.getSortOrder().clear();
			}
		};

		new Thread(backgroundTask).start();

	}

	@FXML
	private void addBookWindowButtonAction(ActionEvent event) {
		LOG.debug("'Add book window' button clicked");

		openWindowAddBook();
	}

	private void openWindowAddBook() {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/javafx/library/view/book-add.fxml"), resources);
			Scene scene = new Scene(root, 450, 190);
			scene.getStylesheets().add(getClass().getResource("/javafx/library/css/standard.css").toExternalForm());
			Stage stageAddBook = new Stage();
			stageAddBook.setTitle("Add book");
			stageAddBook.setScene(scene);
			stageAddBook.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
