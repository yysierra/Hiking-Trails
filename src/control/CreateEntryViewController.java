package control;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.HikingEntry;
import model.User;
import model.Utilities;

public class CreateEntryViewController implements Initializable {
	private Stage currentStage;
	private static String trailNameEntry;
	private static User userEntry;

	@FXML
	private Button btnClose;
	@FXML
	private Button btnPost;
	@FXML
	private TextField tfTrailName;
	@FXML
	private TextField tfDistanceHiked;
	@FXML
	private TextField tfHikeDuration;
	@FXML
	private TextField tfAveragePace;
	@FXML
	private TextArea taComments;
	@FXML
	private DatePicker dpStartDate;
	@FXML
	private DatePicker dpEndDate;
	@FXML
	private TextField tfStartTime;
	@FXML
	private TextField tfEndTime;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		tfTrailName.setText(trailNameEntry);
	}

	public static void initiData(User user) {
		userEntry = user;
	}

	public static void setTrailName(String trailName) {
		trailNameEntry = trailName;
	}

	public void btnPostClicked(ActionEvent e) {
		if (!tfTrailName.getText().isEmpty() && !tfDistanceHiked.getText().isEmpty()
				&& !tfHikeDuration.getText().isEmpty() && !tfAveragePace.getText().isEmpty()
				&& !taComments.getText().isEmpty() && dpStartDate.getValue() != null && dpEndDate.getValue() != null
				&& !tfStartTime.getText().isBlank() && !tfEndTime.getText().isBlank()) {

			Alert postCreatedAlert = new Alert(AlertType.CONFIRMATION);
			postCreatedAlert.setTitle("Post has been created");
			postCreatedAlert.setContentText("Post has been successfully created!");
			postCreatedAlert.showAndWait();

			LocalDateTime startDate = LocalDateTime.of(dpStartDate.getValue(), LocalTime.parse(tfStartTime.getText()));
			LocalDateTime endDate = LocalDateTime.of(dpEndDate.getValue(), LocalTime.parse(tfEndTime.getText()));

			userEntry.getEntries().getEntries()
					.add(new HikingEntry(tfTrailName.getText(), startDate, endDate,
							Double.parseDouble(tfDistanceHiked.getText()), tfHikeDuration.getText(),
							Double.parseDouble(tfAveragePace.getText()), taComments.getText()));
			Utilities.saveInfo();
			ViewEntriesController.initiData(userEntry);
			currentStage = (Stage) btnPost.getScene().getWindow();
			currentStage.close();

		} else {
			Alert missingInputAlert = new Alert(AlertType.ERROR);
			missingInputAlert.setTitle("Error in creating post");
			missingInputAlert.setContentText("One or more fields are missing!\nPlease try again");
			missingInputAlert.showAndWait();
		}
	}

	public void btnCloseClicked(ActionEvent e) {
		currentStage = (Stage) btnClose.getScene().getWindow();
		currentStage.close();
	}

}
