package control;

import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.HikingEntry;
import model.User;

public class ViewEntriesController implements Initializable {
	private static User userEntry;

	@FXML
	private TextField searchBar;
	@FXML
	private ListView<HikingEntry> entries;
	@FXML
	private AnchorPane entryInfo;
	@FXML
	private TextField date;
	@FXML
	private TextField trailName;
	@FXML
	private TextField distanceHiked;
	@FXML
	private TextField duration;
	@FXML
	private TextField averagePace;
	@FXML
	private TextArea comments;
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		if (userEntry.getEntries().getEntries().size() == 0) {
			entries.setPlaceholder(new Label("There are no entries at the moment"));
		} else {
			entries.getItems().addAll(userEntry.getEntries().getEntries());

			searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
				updateListView(newValue);
			});
			
			entries.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
				if (newValue != null) {
					HikingEntry selectedEntry = entries.getSelectionModel().selectedItemProperty().get();
					date.setText(selectedEntry.getStartDate() + " to " + selectedEntry.getEndDate());
					trailName.setText(selectedEntry.getTrailName());
					distanceHiked.setText(String.valueOf(selectedEntry.getDistanceHiked()));
					duration.setText(String.valueOf(selectedEntry.getDistanceHiked()));
					averagePace.setText(String.valueOf(selectedEntry.getAvgPace()));
					comments.setText(selectedEntry.getComments());
				}
			});
		}
	}

	public static void initiData(User user) {
		userEntry = user;
	}

	@FXML
	void search(ActionEvent e) {
		entries.getItems().clear();
		entries.getItems().addAll(searchList(searchBar.getText(), userEntry.getEntries().getEntries()));
	}

	private void updateListView(String searchText) {
		if (searchText.isEmpty()) {
			entries.getItems().clear();
			entries.getItems().addAll(userEntry.getEntries().getEntries());
		} else {
			entries.getItems().clear();
			entries.getItems().addAll(searchList(searchText, userEntry.getEntries().getEntries()));
		}
	}

	private List<HikingEntry> searchList(String searchWords, TreeSet<HikingEntry> entries) {
		List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));
		return entries.stream()
				.filter(input -> searchWordsArray.stream()
						.allMatch(word -> input.getTrailName().toLowerCase().contains(word.toLowerCase())))
				.collect(Collectors.toList());
	}

}
