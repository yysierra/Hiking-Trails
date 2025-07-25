package control;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.User;
import model.UserBag;

public class AdminViewUsersController implements Initializable {
	@FXML
	private Button promoteAdmin;
	@FXML
	private TextField searchBar;
	@FXML
	private ListView<User> userView;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		if (UserBag.getInstance().getUsers().size() == 0) {
			userView.setPlaceholder(new Label("There are no users"));
		} else {
			userView.getItems().clear();
			userView.getItems().addAll(UserBag.getInstance().getUsers());

			searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
				updateListView(newValue);
			});
			
			userView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
				if (newValue != null) {
					promoteAdmin.setDisable(false);
				} else {
					promoteAdmin.setDisable(true);
				}
			});
			
		}
	}
	
	public void promoteAdminClicked(ActionEvent e) {
		User selectedUser = userView.getSelectionModel().getSelectedItem();
		selectedUser.setIsAdmin(true);
		Alert promotedUserAlert = new Alert(AlertType.CONFIRMATION);
		promotedUserAlert.setTitle("User has been promoted to admin!");
		promotedUserAlert.setContentText("The selected user has been promoted to admin status!");
		promotedUserAlert.showAndWait();
	}
	
	public void search(ActionEvent e) {
		userView.getItems().clear();
		userView.getItems().addAll(searchList(searchBar.getText(), UserBag.getInstance().getUsers()));
	}

	private void updateListView(String searchText) {
		if (searchText.isEmpty()) {
			userView.getItems().clear();
			userView.getItems().addAll(UserBag.getInstance().getUsers());
		} else {
			userView.getItems().clear();
			userView.getItems().addAll(searchList(searchText, UserBag.getInstance().getUsers()));
		}
	}

	private List<User> searchList(String searchWords, TreeSet<User> followers) {
		List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));
		return followers.stream()
				.filter(input -> searchWordsArray.stream()
						.allMatch(word -> input.getUsername().toLowerCase().contains(word.toLowerCase())))
				.collect(Collectors.toList());
	}

}
