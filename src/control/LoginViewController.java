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
import model.Admin;
import model.User;
import model.UserBag;
import model.Utilities;

public class LoginViewController {
	private Stage currentStage;
	private Parent root;
	
	@FXML
	private TextField tfUsername;
	@FXML 
	private PasswordField pfPassword;
	@FXML
	private Button btnLogin;
	@FXML
	private Button btnSignUp;
	@FXML
	private Button btnCancel;
		
	public void btnLoginClicked(ActionEvent e) throws IOException {
	    Optional<User> foundUser = UserBag.getInstance().search(tfUsername.getText());
	    if (foundUser.isPresent()) {
	        if (!pfPassword.getText().isEmpty() && foundUser.get().getPassword().equals(pfPassword.getText())) {
	            Alert loginAlert;
	            if (foundUser.get().getIsAdmin()) {
	                loginAlert = new Alert(AlertType.CONFIRMATION);
	                loginAlert.setTitle("Admin Login Successful!");
	                loginAlert.setContentText("You have successfully logged in as an admin!\nWelcome!");
	                HomeViewController.initiData(foundUser.get());
	                root = FXMLLoader.load(getClass().getResource("/view/HomeView.fxml"));
	            } else {
	                loginAlert = new Alert(AlertType.CONFIRMATION);
	                loginAlert.setTitle("Login Has Been Successful!");
	                loginAlert.setContentText("You have successfully logged in!\nEnjoy Hiking!");
	                HomeViewController.initiData(foundUser.get());
	                root = FXMLLoader.load(getClass().getResource("/view/HomeView.fxml"));
	            }
	            loginAlert.showAndWait();
	            currentStage = (Stage) btnLogin.getScene().getWindow();
	            currentStage.setScene(new Scene(root));
	            currentStage.centerOnScreen();
	            currentStage.show();
	        } else {
	            Alert wrongPasswordAlert = new Alert(AlertType.ERROR);
	            wrongPasswordAlert.setTitle("Password Entered Is Incorrect!");
	            wrongPasswordAlert.setContentText("Your password entered is incorrect...\nPlease try again!");
	            wrongPasswordAlert.showAndWait();
	        }
	    } else {
	        Alert wrongCredentialsAlert = new Alert(AlertType.ERROR);
	        wrongCredentialsAlert.setTitle("Credentials Entered Wrong!");
	        wrongCredentialsAlert.setContentText("Either one or two of your credentials entered is wrong...\nPlease try again!");
	        wrongCredentialsAlert.showAndWait();
	    }
	}
	
	public void adminLoginClicked(ActionEvent e) throws IOException {
		if (tfUsername.getText().equals(Admin.getValidUsername())) {
			if (!pfPassword.getText().isEmpty() && pfPassword.getText().equals(Admin.getValidPassword())) {
				Alert loginAlert = new Alert(AlertType.CONFIRMATION);
                loginAlert.setTitle("Admin Login Successful!");
                loginAlert.setContentText("You have successfully logged in as an admin!\nWelcome!");
                loginAlert.showAndWait();
                currentStage = (Stage) btnLogin.getScene().getWindow();
                currentStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AdminView.fxml"))));
                currentStage.centerOnScreen();
                currentStage.show();
			} else {
				Alert wrongPasswordAlert = new Alert(AlertType.ERROR);
	            wrongPasswordAlert.setTitle("Password Entered Is Incorrect!");
	            wrongPasswordAlert.setContentText("Your password entered is incorrect...\nPlease try again!");
	            wrongPasswordAlert.showAndWait();
			}
		} else {
			Alert wrongCredentialsAlert = new Alert(AlertType.ERROR);
	        wrongCredentialsAlert.setTitle("You do not have access to this account");
	        wrongCredentialsAlert.setContentText("You are not the initial admin!");
	        wrongCredentialsAlert.showAndWait();
		}
	}

	public void btnSignUpClicked(ActionEvent e) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/view/SignInView.fxml"));
		currentStage = (Stage) btnSignUp.getScene().getWindow();
		currentStage.setScene(new Scene(root));
		currentStage.centerOnScreen();
		currentStage.show();
	}

	public void btnCancelClicked(ActionEvent e) {
		Utilities.saveInfo();
		currentStage = (Stage) btnCancel.getScene().getWindow();
		currentStage.close();
	}

}
