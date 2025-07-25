package control;

import java.io.IOException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;
import model.UserBag;

public class SignInViewController {
	private Stage currentStage;
	@FXML
	private TextField tfUsername;
	@FXML
	private PasswordField pfPassword;
	@FXML
	private TextField tfPhoneNumber;
	@FXML
	private Button btnSignUp;
	@FXML
	private Button btnCancel;
	
	public void btnSignUpClicked(ActionEvent e) throws IOException {
		Optional<User> foundUser = UserBag.getInstance().search(tfUsername.getText());
		if (foundUser.isPresent()) {
			Alert userAlreadyExistsAlert = new Alert(AlertType.ERROR);
			userAlreadyExistsAlert.setTitle("Username Already Exists!");
			userAlreadyExistsAlert.setHeaderText(null);
			userAlreadyExistsAlert.setContentText("That username is already taken...\nPlease try a different username");
			userAlreadyExistsAlert.showAndWait();
		} else {
			Alert signInSuccessfulAlert = new Alert(AlertType.CONFIRMATION);
			signInSuccessfulAlert.setTitle("New User Has Been Created!");
			signInSuccessfulAlert.setHeaderText(null);
			signInSuccessfulAlert.setContentText("A new user has been created with the following info!\nPlease login with your new account!");
			signInSuccessfulAlert.showAndWait();
			UserBag.getInstance().add(new User(tfUsername.getText(), pfPassword.getText(), tfPhoneNumber.getText()));
			UserBag.getInstance().display();
	
			Parent root = FXMLLoader.load(getClass().getResource("/view/LoginView.fxml"));
			currentStage = (Stage) btnSignUp.getScene().getWindow();
			currentStage.setScene(new Scene(root));
			currentStage.centerOnScreen();
			currentStage.show();
		}
	}
	
	public void btnCancelClicked(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/LoginView.fxml"));
		currentStage = (Stage) btnCancel.getScene().getWindow();
		currentStage.setScene(new Scene(root));
		currentStage.centerOnScreen();
		currentStage.show();
	}

}
