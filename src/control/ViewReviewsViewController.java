package control;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.Review;
import model.TrailBag;
import model.User;
import model.UserBag;

public class ViewReviewsViewController implements Initializable {
	private static User userEntry;
	private List<Review> allReviews = TrailBag.getInstance().getTrailBag().stream()
			.flatMap(trail -> trail.getReviews().getReviews().stream()).collect(Collectors.toList());
	private ObservableList<Review> observableReviews = FXCollections.observableArrayList(allReviews);

	@FXML
	private TextField searchBar;
	@FXML
	private TextField username;
	@FXML
	private TextField rating;
	@FXML
	private TextField postingDate;
	@FXML
	private TextArea comments;
	@FXML
	private Button followAuthor;
	@FXML
	private ListView<Review> reviewView;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		if (allReviews.size() == 0) {
			reviewView.setPlaceholder(new Label("There are no reviews at this time"));
		} else {
			reviewView.getItems().addAll(observableReviews);
			searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
				updateListView(newValue);
			});

			reviewView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
				if (newValue == null) {
					followAuthor.setDisable(true);
				} else {
					followAuthor.setDisable(false);
					if (newValue.getUser().getUsername().equals(userEntry.getUsername())) {
						followAuthor.setDisable(true);
					}
					username.setText(newValue.getUser().getUsername());
					rating.setText(String.valueOf(newValue.getRating()));
					postingDate.setText(String.valueOf(newValue.getPostingDate()));
					comments.setText(comments.getText());
				}
			});
		}
		followAuthor.setDisable(true);
	}

	public static void initiData(User user) {
		userEntry = user;
	}

	private List<Review> getVisibleReviews() {
		return allReviews.stream().filter(review -> !userEntry.getBlocked().contains(review.getUser()))
				.collect(Collectors.toList());
	}

	public void followAuthorClicked(ActionEvent e) {
		Review selectedReview = reviewView.getSelectionModel().getSelectedItem();
		if (selectedReview != null) {
			User author = selectedReview.getUser();
			if (!author.getUsername().equals(userEntry.getUsername()) && !userEntry.getFollowing().contains(author)) {
				userEntry.getFollowing().add(author);
				author.getFollowers().add(userEntry);
				Alert userFollowedAlert = new Alert(AlertType.CONFIRMATION);
				userFollowedAlert.setTitle("User has been followed");
				userFollowedAlert.setContentText("You can view users in your following section");
				userFollowedAlert.showAndWait();
			} else {
				Alert alreadyFollowingAlert = new Alert(AlertType.INFORMATION);
				alreadyFollowingAlert.setTitle("Already Following");
				alreadyFollowingAlert.setContentText("You are already following this user.");
				alreadyFollowingAlert.showAndWait();
			}
		}
	}

	@FXML
	void search(ActionEvent e) {
		reviewView.getItems().clear();
		reviewView.getItems().addAll(searchList(searchBar.getText(), allReviews));
	}

	private void updateListView(String searchText) {
		if (searchText.isEmpty()) {
			reviewView.getItems().clear();
			reviewView.getItems().addAll(observableReviews);
		} else {
			reviewView.getItems().clear();
			reviewView.getItems().addAll(searchList(searchText, allReviews));
		}
	}

	private List<Review> searchList(String searchWords, List<Review> reviews) {
		List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));
		return reviews.stream()
				.filter(input -> searchWordsArray.stream()
						.allMatch(word -> input.getUser().getUsername().toLowerCase().contains(word.toLowerCase())))
				.collect(Collectors.toList());
	}

}
