package postDeploymentPrototype;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PostDeploymentPrototype extends Application {

    private ListView<String> listView;
    private ListView<String> historyListView;
    private TextField textField;
    LocalDateTime currentTime = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String formattedTime = currentTime.format(formatter);
    
    @Override
    public void start(Stage primaryStage) {
        Label activityLabel = new Label("Enter activity:");
        textField = new TextField();

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addItem());

        Button editButton = new Button("Edit");
        editButton.setOnAction(e -> editItem());

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deleteItem());

        HBox inputBox = new HBox(10, activityLabel, textField, addButton, editButton, deleteButton);
        inputBox.setPadding(new Insets(10));

        listView = new ListView<>();
        listView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    editButton.setDisable(newValue == null);
                    deleteButton.setDisable(newValue == null);
                });

        historyListView = new ListView<>();
        
        Label historyLabel = new Label("Document History");

        VBox root = new VBox(10, inputBox, listView, historyLabel, historyListView);
        root.setPadding(new Insets(10));
        
        Scene scene = new Scene(root, 450, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Effort Log");
        primaryStage.show();
    }

    private void addItem() {
        String item = textField.getText();

        currentTime = LocalDateTime.now();
        formattedTime = currentTime.format(formatter);

        String listItem = String.format("%s", item);
        listView.getItems().add(listItem);

        String historyItem = String.format("%s - Added %s ", formattedTime, item);
        historyListView.getItems().add(historyItem);      
        
        textField.clear();
    }

    private void editItem() {
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        String selectedItem = listView.getSelectionModel().getSelectedItem();
        
        TextField editTextField = new TextField(selectedItem);

        VBox vbox = new VBox(10, new Label("Enter new value:"), editTextField);
        vbox.setPadding(new Insets(10));
        Scene editScene = new Scene(vbox, 200, 100);
        Stage editStage = new Stage();
        editStage.setScene(editScene);
        Button okButton = new Button("OK");
        okButton.setOnAction(e -> {
            String newValue = editTextField.getText();
            listView.getItems().set(selectedIndex, newValue);

            currentTime = LocalDateTime.now();
            formattedTime = currentTime.format(formatter);
            
            String historyItem = String.format("%s - Edited %s to %s", formattedTime, selectedItem, newValue);
            historyListView.getItems().add(historyItem);

            editStage.close();
        });
        vbox.getChildren().add(okButton);

        editStage.show();
    }

    private void deleteItem() {
    	
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        String selectedItem = listView.getSelectionModel().getSelectedItem();

        listView.getItems().remove(selectedIndex);

        currentTime = LocalDateTime.now();
        formattedTime = currentTime.format(formatter);
        
        String historyItem = String.format(" %s - Deleted %s", formattedTime, selectedItem);
        historyListView.getItems().add(historyItem);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

        
