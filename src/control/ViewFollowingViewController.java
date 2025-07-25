package control;

import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.HikingEntry;
import model.User;

public class ViewFollowingViewController implements Initializable {
	private static User userEntry;

	@FXML
	private ListView<User> followingView;
	@FXML
	private TextField username;
	@FXML
	private TextField numberOfTrailEntries;
	@FXML
	private TextField numberOfReviews;
	@FXML
	private Button unfollow;
	@FXML
	private Button block;
	@FXML
	private TextField searchBar;
	@FXML
	private TextField following;
	@FXML
	private TextField followers;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		if (userEntry.getFollowing().size() == 0) {
			followingView.setPlaceholder(new Label("You are not following anyone"));
		} else {
			followingView.getItems().addAll(userEntry.getFollowing());

			searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
				updateListView(newValue);
			});
			
			followingView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
				if (newValue != null) {
					User userFollowed = followingView.getSelectionModel().selectedItemProperty().get();
					username.setText(userFollowed.getUsername());
					numberOfTrailEntries.setText(String.valueOf(userFollowed.getEntries().getEntries().size()));
					numberOfReviews.setText(String.valueOf(userFollowed.getReviews().getReviews().size()));
					following.setText(String.valueOf(userFollowed.getFollowing().size()));
					followers.setText(String.valueOf(userFollowed.getFollowers().size()));
					unfollow.setDisable(false);
					block.setDisable(false);
				} else {
					unfollow.setDisable(true);
					block.setDisable(true);
				}
			});
		}
		 unfollow.setDisable(true);
         block.setDisable(true);
	}

	public static void initiData(User user) {
		userEntry = user;
	}
	
	public void blockClicked(ActionEvent e) {
		User selectedUser = followingView.getSelectionModel().getSelectedItem();
	    if (selectedUser != null) {
	        userEntry.getFollowers().remove(selectedUser);
	        userEntry.getFollowing().remove(selectedUser);
	        userEntry.getBlocked().add(selectedUser);
	        selectedUser.getFollowers().remove(userEntry);
	        selectedUser.getFollowing().remove(userEntry);
	        followingView.getItems().clear();
	        followingView.getItems().addAll(userEntry.getFollowers());
	    }
	}

	@FXML
	void search(ActionEvent e) {
		followingView.getItems().clear();
		followingView.getItems().addAll(searchList(searchBar.getText(), userEntry.getFollowing()));
	}

	private void updateListView(String searchText) {
		 List<User> filteredList = searchList(searchText, userEntry.getFollowing());
		 followingView.getItems().clear();
		 followingView.getItems().addAll(filteredList);
	}

	private List<User> searchList(String searchWords, LinkedList<User> followers) {
		List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));
		return followers.stream()
				.filter(input -> searchWordsArray.stream()
						.allMatch(word -> input.getUsername().toLowerCase().contains(word.toLowerCase())))
				.collect(Collectors.toList());
	}

}
