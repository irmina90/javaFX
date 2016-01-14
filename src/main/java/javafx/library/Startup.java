package javafx.library;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.library.controller.BookSearchController;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Startup extends Application {
	private Stage primaryStage;
	private BookSearchController bookSearchCtrl;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.setPrimaryStage(primaryStage);

		String langCode = getParameters().getNamed().get("--lang");
		if (langCode != null && !langCode.isEmpty()) {
			Locale.setDefault(Locale.forLanguageTag(langCode));
		}

		primaryStage.setTitle("Library");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/javafx/library/view/book-search.fxml"));
		loader.setResources(ResourceBundle.getBundle("javafx/library/bundle/base"));
		Parent root = (Parent) loader.load();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/javafx/library/css/alternative.css").toExternalForm());
		setBookSearchCtrl(loader.getController());
		getBookSearchCtrl().setMain(this);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public BookSearchController getBookSearchCtrl() {
		return bookSearchCtrl;
	}

	public void setBookSearchCtrl(BookSearchController bookSearchCtrl) {
		this.bookSearchCtrl = bookSearchCtrl;
	}

}
