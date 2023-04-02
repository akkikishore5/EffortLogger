package asuHelloWorldJavaFX;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TimeAndReportPrototype extends Application {
	
	 public static void main(String[] args) {
	        launch(args);
	    }
    
    private ListView<String> logEntriesListView;
    private TextArea activityPerformedTextArea;
    private TextField loginTimeTextField;
    
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void start(Stage primaryStage) {
        
        Label loginTimeLabel = new Label("Login Time:");
        loginTimeTextField = new TextField();
        loginTimeTextField.setPrefWidth(200);
        
        Button currentTimeButton = new Button("Current Time");
        currentTimeButton.setOnAction(e -> {
            LocalDateTime now = LocalDateTime.now();
            loginTimeTextField.setText(dateTimeFormatter.format(now));
        });
        
        Label activityPerformedLabel = new Label("Activity Performed:");
        activityPerformedTextArea = new TextArea();
        activityPerformedTextArea.setPrefRowCount(5);
        activityPerformedTextArea.setWrapText(true);
        
        Button logActivityButton = new Button("Log Activity");
        logActivityButton.setOnAction(e -> logActivity());
        
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        buttonBox.getChildren().addAll(currentTimeButton, logActivityButton);
        
        VBox inputBox = new VBox(10);
        inputBox.getChildren().addAll(loginTimeLabel, loginTimeTextField, activityPerformedLabel, activityPerformedTextArea, buttonBox);
        inputBox.setPadding(new Insets(10));
        
        logEntriesListView = new ListView<>();
        logEntriesListView.setOnMouseClicked(e -> {
            String selectedItem = logEntriesListView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                showActivityDetails(selectedItem);
            }
        });
        
        VBox root = new VBox(10);
        root.getChildren().addAll(inputBox, logEntriesListView);
        root.setPadding(new Insets(10));
        
        Scene scene = new Scene(root, 600, 400);
        
        primaryStage.setTitle("Log Activity");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void logActivity() {
        String loginTime = loginTimeTextField.getText();
        String activityPerformed = activityPerformedTextArea.getText();
        
        String logEntry = loginTime + ": " + activityPerformed;
        logEntriesListView.getItems().add(logEntry);
        
        loginTimeTextField.clear();
        activityPerformedTextArea.clear();
    }
    
    private void showActivityDetails(String logEntry) {
        String[] parts = logEntry.split(": ", 2);
        String loginTime = parts[0];
        String activities = parts[1];
        
        Stage detailsStage = new Stage();
        
        Label loginTimeLabel = new Label("Login Time: " + loginTime);
        Label activitiesLabel = new Label("Activities:");
        TextArea activitiesTextArea = new TextArea();
        activitiesTextArea.setText(activities);
        activitiesTextArea.setEditable(false);
        activitiesTextArea.setPrefRowCount(5);
        activitiesTextArea.setWrapText(true);
        
        VBox root = new VBox(10);
        root.getChildren().addAll(loginTimeLabel, activitiesLabel, activitiesTextArea);
        root.setPadding(new Insets(10));
        
        Scene scene = new Scene(root, 400, 300);
        
        detailsStage.setTitle("Activity Details");
        detailsStage.setScene(scene);
        detailsStage.show();
    }
}
   
