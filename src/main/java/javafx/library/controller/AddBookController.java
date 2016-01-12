package javafx.library.controller;

import org.apache.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddBookController {

	private static final Logger LOG = Logger.getLogger(AddBookController.class);

	@FXML
	TextField newBookTitle;
	@FXML
	TextField newBookAuthor;
	@FXML
	Button addBookButton;

	@FXML
	private void addBookAction(ActionEvent event) {
		LOG.debug("'Save' button clicked");
	}

}
