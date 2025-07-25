package control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Trail;
import model.Utilities;

public class AdminEditTrailViewController implements Initializable {
	private static Trail trailEntry;
	private Stage currentStage;
	private boolean difficultyChanged = false;
	private boolean elevationGainChanged = false;
	private boolean headAddressChanged = false;
	private boolean lengthChanged = false;
	private boolean trailNameChanged = false;
	private boolean typeChanged = false;

	@FXML
	private Button close;
	@FXML
	private TextField difficulty;
	@FXML
	private TextField elevationGain;
	@FXML
	private TextField headAddress;
	@FXML
	private TextField length;
	@FXML
	private TextField trailName;
	@FXML
	private TextField type;
	@FXML
	private Button update;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		difficulty.setText(trailEntry.getDifficulty());
		elevationGain.setText(String.valueOf(trailEntry.getElevationGain()));
		headAddress.setText(trailEntry.getHeadAddress());
		length.setText(String.valueOf(trailEntry.getLength()));
		trailName.setText(trailEntry.getTrailName());
		type.setText(trailEntry.getTrailType());
		
		difficulty.textProperty().addListener((observable, oldValue, newValue) -> difficultyChanged = true);
		elevationGain.textProperty().addListener((observable, oldValue, newValue) -> elevationGainChanged = true);
		headAddress.textProperty().addListener((observable, oldValue, newValue) -> headAddressChanged = true);
		length.textProperty().addListener((observable, oldValue, newValue) -> lengthChanged = true);
		trailName.textProperty().addListener((observable, oldValue, newValue) -> trailNameChanged = true);
		type.textProperty().addListener((observable, oldValue, newValue) -> typeChanged = true);
	}
	
	public static void initiData(Trail trail) {
		trailEntry = trail;
	}

	public void updateClicked(ActionEvent e) {
		if (isUpdateEnabled()) {
			if (difficultyChanged) {
				trailEntry.setDifficulty(difficulty.getText());
			}
			if (elevationGainChanged) {
				trailEntry.setElevationGain(Double.parseDouble(elevationGain.getText()));
			}
			if (headAddressChanged) {
				trailEntry.setHeadAddress(headAddress.getText());
			}
			if (lengthChanged) {
				trailEntry.setLength(Double.parseDouble(length.getText()));
			}
			if (trailNameChanged) {
				trailEntry.setTrailName(trailName.getText());
			}
			if (typeChanged) {
				trailEntry.setTrailType(type.getText());
			}
			
			Alert changesMadeAlert = new Alert(AlertType.CONFIRMATION);
			changesMadeAlert.setTitle("Changes have been made!");
			changesMadeAlert.setContentText("Changes have been to the selected trail!");
			changesMadeAlert.showAndWait();

			Utilities.saveInfo();
			currentStage = (Stage) update.getScene().getWindow();
			currentStage.close();
			resetFlags();
		}
	}

	private boolean isUpdateEnabled() {
		return difficultyChanged || elevationGainChanged || headAddressChanged || lengthChanged || trailNameChanged
				|| typeChanged;
	}

	private void resetFlags() {
		difficultyChanged = false;
		elevationGainChanged = false;
		headAddressChanged = false;
		lengthChanged = false;
		trailNameChanged = false;
		typeChanged = false;
	}

	public void closeClicked(ActionEvent e) {
		currentStage = (Stage) close.getScene().getWindow();
		currentStage.close();
	}
	
}
