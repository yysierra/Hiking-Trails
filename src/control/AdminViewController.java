package control;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Admin;
import model.Utilities;

public class AdminViewController {
	private Stage currentStage;
	
	@FXML
	private BorderPane bpStage;
	@FXML
	private Button viewTrails;
	@FXML
	private Button viewUsers;
	@FXML
	private Button logout;
	
	public void viewTrailsClicked(ActionEvent e) throws IOException {
		bpStage.setCenter(FXMLLoader.load(getClass().getResource("/view/AdminViewTrails.fxml")));
	}
	
	public void viewUsersClicked(ActionEvent e) throws IOException {
		bpStage.setCenter(FXMLLoader.load(getClass().getResource("/view/AdminViewUsers.fxml")));
	}
	
	public void logoutClicked(ActionEvent e) {
		Utilities.saveInfo();
		currentStage = (Stage) logout.getScene().getWindow();
		currentStage.close();
	}
	
}
