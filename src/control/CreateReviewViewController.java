package control;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Review;
import model.TrailBag;
import model.User;
import model.UserBag;

public class CreateReviewViewController implements Initializable {
	private Stage currentStage;
	private static User userEntry;
	private static String trailNameEntry;

	@FXML
	private TextField username;
	@FXML
	private TextField postingDate;
	@FXML
	private TextArea comments;
	@FXML
	private ComboBox<String> ratings;
	@FXML
	private Button post;
	@FXML
	private Button close;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		ratings.setItems(FXCollections.observableArrayList("1", "2", "3", "4", "5"));
		postingDate.setText(String.valueOf(LocalDateTime.now()));
		username.setText(userEntry.getUsername());
	}
	
	public static void initiUserData(User user) {
		userEntry = user;
	}
	
	public static void initData(String trailName) {
		trailNameEntry = trailName;
	}

	public void postClicked(ActionEvent e) {
		if (!comments.getText().isEmpty() && !ratings.getValue().isEmpty()) {
			Alert postCreatedAlert = new Alert(AlertType.CONFIRMATION);
			postCreatedAlert.setTitle("Review has been created");
			postCreatedAlert.setContentText("Review has been successfully created!");
			postCreatedAlert.showAndWait();

			TrailBag.getInstance().getTrailBag().stream().filter(trail -> trail.getTrailName().equals(trailNameEntry)).findAny()
					.ifPresent(trail -> trail.getReviews()
							.add(new Review(userEntry, Integer.parseInt(ratings.getValue()), comments.getText())));
			UserBag.getInstance().getUsers().stream().filter(user -> user.getUsername().equals(userEntry.getUsername()))
					.findAny().ifPresent(user -> user.getReviews()
							.add(new Review(userEntry, Integer.parseInt(ratings.getValue()), comments.getText())));
			
			currentStage = (Stage) close.getScene().getWindow();
			currentStage.close();
		}
	}
	
	public void closeClicked(ActionEvent e) {
		currentStage = (Stage) close.getScene().getWindow();
		currentStage.close();
	}

}
