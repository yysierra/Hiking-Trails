package control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.User;

public class GreetingsViewController implements Initializable {
	private static User userEntry;
	
	@FXML
	private Label lblGreetings;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		lblGreetings.setText("Welcome " + userEntry.getUsername() + "!");
	}
	
	public static void initiData(User user) {
		userEntry = user;
	}
	
}
