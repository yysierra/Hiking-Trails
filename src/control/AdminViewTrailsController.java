package control;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Trail;
import model.TrailBag;

public class AdminViewTrailsController implements Initializable {
	private Stage newStage;
	
	@FXML
	private Button editTrails;
	@FXML
	private TextField searchBar;
	@FXML
	private ListView<Trail> trailView;
	
	@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        trailView.getItems().addAll(TrailBag.getInstance().getTrailBag());
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            updateListView(newValue);
        });
        
        trailView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
            	editTrails.setDisable(false);
            } else {
                editTrails.setDisable(true);
            }
        });
        
    }
	
	public void editTrailsClicked(ActionEvent e) throws IOException {
		AdminEditTrailViewController.initiData(trailView.getSelectionModel().getSelectedItem());
		Parent root = FXMLLoader.load(getClass().getResource("/view/AdminEditTrailView.fxml"));
		newStage = new Stage();
		newStage.initModality(Modality.APPLICATION_MODAL);
		newStage.initStyle(StageStyle.UNDECORATED);
		newStage.initStyle(StageStyle.TRANSPARENT);
		
		Scene scene = new Scene(root);
		scene.setFill(Color.TRANSPARENT);
		newStage.setScene(scene);
		newStage.centerOnScreen();
		newStage.show();
		
	}
	
	@FXML
    void search(ActionEvent e) {
        trailView.getItems().clear();
        trailView.getItems().addAll(searchList(searchBar.getText(), TrailBag.getInstance().getTrailBag()));
    }

    private void updateListView(String searchText) {
        if (searchText.isEmpty()) {
            trailView.getItems().clear();
            trailView.getItems().addAll(TrailBag.getInstance().getTrailBag());
        } else {
            trailView.getItems().clear();
            trailView.getItems().addAll(searchList(searchText, TrailBag.getInstance().getTrailBag()));
        }
    }

    private List<Trail> searchList(String searchWords, LinkedList<Trail> trails) {
        List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));
        return trails.stream().filter(input ->
            searchWordsArray.stream().allMatch(word ->
                input.getTrailName().toLowerCase().contains(word.toLowerCase()) ||
                input.getHeadAddress().toLowerCase().contains(word.toLowerCase()) ||
                String.valueOf(input.getLength()).toLowerCase().contains(word.toLowerCase()) ||
                String.valueOf(input.getElevationGain()).toLowerCase().contains(word.toLowerCase()) ||
                input.getDifficulty().toLowerCase().contains(word.toLowerCase()) ||
                input.getTrailType().toLowerCase().contains(word.toLowerCase())
            )
        ).collect(Collectors.toList());
    }
	
}
	