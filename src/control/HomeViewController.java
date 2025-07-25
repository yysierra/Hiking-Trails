package control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.User;
import model.Utilities;

public class HomeViewController implements Initializable {
	private Stage stage;	
	private static User userEntry;
	
	@FXML
	private BorderPane bpStage;
	@FXML
	private Button btnHome;
	@FXML
	private Button btnProfile;
	@FXML
	private Button btnFollowing;
	@FXML
	private Button btnFollowers;
	@FXML
	private Button btnLogout;
	@FXML
	private Button btnTrailSearch;
	@FXML
	private Button btnViewEntries;
	@FXML
	private Button btnViewReviews;
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		GreetingsViewController.initiData(userEntry);
		CreateEntryViewController.initiData(userEntry);
		CreateReviewViewController.initiUserData(userEntry);
		ViewEntriesController.initiData(userEntry);
		ViewReviewsViewController.initiData(userEntry);
		ViewFollowingViewController.initiData(userEntry);
		ViewFollowersViewController.initiData(userEntry);
		
		try {
			bpStage.setCenter(FXMLLoader.load(getClass().getResource("/view/GreetingsView.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void initiData(User user) {
		userEntry = user;
	}
	
	public void btnHomeClicked(ActionEvent e) throws IOException {
		bpStage.setCenter(FXMLLoader.load(getClass().getResource("/view/GreetingsView.fxml")));
	}
	
	public void btnFollowingClicked(ActionEvent e) throws IOException {
		bpStage.setCenter(FXMLLoader.load(getClass().getResource("/view/ViewFollowingView.fxml")));
	}
	
	public void btnFollowersClicked(ActionEvent e) throws IOException {
		bpStage.setCenter(FXMLLoader.load(getClass().getResource("/view/ViewFollowersView.fxml")));
	}
	
	public void btnProfileClicked(ActionEvent e) throws IOException {
		bpStage.setCenter(FXMLLoader.load(getClass().getResource("/view/ProfileView.fxml")));
	}
	
	public void btnViewReviewsClicked(ActionEvent e) throws IOException {
		bpStage.setCenter(FXMLLoader.load(getClass().getResource("/view/ViewReviewsView.fxml")));
	}
	
	public void btnViewEntriesClicked(ActionEvent e) throws IOException {
		bpStage.setCenter(FXMLLoader.load(getClass().getResource("/view/ViewEntriesView.fxml")));
	}
	
	public void btnTrailSearchClicked(ActionEvent e) throws IOException {
		bpStage.setCenter(FXMLLoader.load(getClass().getResource("/view/TrailSearchView.fxml")));
	}
	
	public void btnLogoutClicked(ActionEvent e) {
		Utilities.saveInfo();
		stage = (Stage) btnLogout.getScene().getWindow();
		stage.close();
	}

}
