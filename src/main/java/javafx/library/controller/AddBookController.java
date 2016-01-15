package javafx.library.controller;

import org.apache.log4j.Logger;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.library.dataprovider.DataProvider;
import javafx.library.dataprovider.book.Book;
import javafx.library.model.AddBook;
import javafx.library.model.BookSearch;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddBookController {

	private static final Logger LOG = Logger.getLogger(AddBookController.class);

	@FXML
	TextField newBookTitle;
	@FXML
	TextField newBookAuthor;
	@FXML
	Button addBookButton;
	@FXML
	Button cancelButton;

	private final DataProvider dataProvider = DataProvider.INSTANCE;

	private BookSearchController bookSearchCtrl;
	private BookSearch modelBookSearch;
	private AddBook model = new AddBook();
	private Stage dialogStage;

	@FXML
	private void initialize() {
		newBookTitle.textProperty().bindBidirectional(model.titleProperty());
		newBookAuthor.textProperty().bindBidirectional(model.authorProperty());
	}

	@FXML
	private void addBookAction(ActionEvent event) {
		LOG.debug("'Save' button clicked");

		Task<Book> backgroundTask = new Task<Book>() {

			@Override
			protected void failed() {
				Alert alert = new Alert(AlertType.ERROR);
				alert.initOwner(dialogStage);
				alert.setTitle("Invalid Fields");
				alert.setHeaderText("Please correct invalid fields");
				alert.showAndWait();
			}

			@Override
			protected Book call() throws Exception {
				LOG.debug("call() called");
				Book result = null;
				if (isInputValid()) {
					result = dataProvider.addBook(model.getTitle(), model.getAuthor());
				}
				return result;
			}

			@Override
			protected void succeeded() {
				LOG.debug("succeeded() called");

				Book result = getValue();

				String searchField = "";
				if (bookSearchCtrl.getTitleField().getText() != null)
					searchField = bookSearchCtrl.getTitleField().getText();
				if (result.getTitle().startsWith(searchField)) {
					modelBookSearch.setOneResult(result);
				}
				dialogStage.close();

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information");
				alert.setHeaderText("Success!");
				alert.setContentText("Book " + newBookTitle.getText() + " has been saved successfully!");
				alert.showAndWait();
			}
		};

		new Thread(backgroundTask).start();
	}

	private boolean isInputValid() {
		String errorMessage = "";
		if (newBookTitle.getText() == null || newBookTitle.getText().length() == 0) {
			errorMessage += "No valid title!\n";
		}
		if (newBookAuthor.getText() == null || newBookAuthor.getText().length() == 0) {
			errorMessage += "No valid author!\n";
		}
		if (errorMessage.length() == 0) {
			return true;
		} else {
			throw new IllegalArgumentException();
		}
	}

	@FXML
	private void closeButtonAction(ActionEvent event) {
		LOG.debug("'Close' button clicked");
		dialogStage.close();
	}

	public Stage getDialogStage() {
		return dialogStage;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public BookSearch getModelBookSearch() {
		return modelBookSearch;
	}

	public void setModelBookSearch(BookSearch modelBookSearch) {
		this.modelBookSearch = modelBookSearch;
	}

	public BookSearchController getBookSearchCtrl() {
		return bookSearchCtrl;
	}

	public void setBookSearchCtrl(BookSearchController bookSearchCtrl) {
		this.bookSearchCtrl = bookSearchCtrl;
	}

}
