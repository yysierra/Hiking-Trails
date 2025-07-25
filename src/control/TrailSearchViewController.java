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
import model.User;

public class TrailSearchViewController implements Initializable {
	private Stage newStage;
	private static User userEntry;
	
	@FXML
	private TextField searchBar;
	@FXML
	private Button btnCreateEntry;
	@FXML
	private Button btnCreateReview;
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
                btnCreateEntry.setDisable(false);
                btnCreateReview.setDisable(false);
            } else {
                btnCreateEntry.setDisable(true);
                btnCreateReview.setDisable(true);
            }
        });
        
    }
	
	public static void initiData(User user) {
		userEntry = user;
	}
	
	public void btnCreateEntryClicked(ActionEvent e) throws IOException {
		CreateEntryViewController.setTrailName(trailView.getSelectionModel().getSelectedItem().getTrailName());
		Parent root = FXMLLoader.load(getClass().getResource("/view/CreateEntryView.fxml"));
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
	
	public void btnCreateReviewClicked(ActionEvent e) throws IOException {
		CreateReviewViewController.initData(trailView.getSelectionModel().getSelectedItem().getTrailName());
		Parent root = FXMLLoader.load(getClass().getResource("/view/CreateReviewView.fxml"));
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
