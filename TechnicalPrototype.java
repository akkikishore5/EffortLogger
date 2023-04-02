package asuHelloWorldJavaFX;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.*;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
 
public class TechnicalPrototype extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    String text;
    
    
    public void start(Stage primaryStage) {
    	
    	//set title of stage
        primaryStage.setTitle("Technical Prototype");
        
        //scene for gridpane components
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        
        //Start activity button
        Label startLbl = new Label("1: When you start a new activity select your name and press the 'Start an Activity' button." );
        Button startBtn = new Button("Start an Activity");

        ComboBox<String> nameBox = new ComboBox<String>();
        TextField name = new TextField();
        nameBox.getItems().addAll("Akki Kishore", "Hriday Shah", "Mehul Srivastava", "Ka Wai Szeto", "Dylan Wynne");
        
        gridPane.add(startLbl, 0, 0);
        gridPane.add(startBtn, 0, 1);
        gridPane.add(nameBox, 1, 1);
        
        startBtn.setOnAction(event -> {
            String activity = nameBox.getValue() + " has started an Activity!";
            System.out.println(activity);

        });
        
        //Project box
        Label projectLbl = new Label("Select the project" );
        ComboBox<String> projectBox = new ComboBox<String>();
        projectBox.getItems().addAll("Business Project", "Developement Project");
        gridPane.add(projectLbl, 0, 2);
        gridPane.add(projectBox, 0, 3);
        
        //LCS box
        Label LCSLbl = new Label("Life Cycle Steps" );
        ComboBox<String> LCSBox = new ComboBox<String>();
        LCSBox.getItems().addAll("Project Understanding", "Conceptual Design Plan", "Requirements", "Conceptual Design", "Conceptual Design Review",
        		"Detailed Design Plan", "Detailed Design/Protoype","Detailed Design Review", "Implementation Plan", "Test Case Generation", "Solution Specification"
        				,"Solution Review", "Solution Implementation", "Unit/System Test", "Reflection", "Repository Update");
        gridPane.add(LCSLbl, 1, 2);
        gridPane.add(LCSBox, 1, 3);
        
        Label EffortLbl = new Label("Effort Category" );
        ComboBox<String> EffortBox = new ComboBox<String>();
        EffortBox.getItems().addAll("Plans", "Deliverables", "Interruptions", "Defects", "Others");
        gridPane.add(EffortLbl, 0, 4);
        gridPane.add(EffortBox, 0, 5);
        
        Label planLbl = new Label("Plan" );
        ComboBox<String> planBox = new ComboBox<String>();
        planBox.getItems().addAll("Project Plan", "Risk Management Plan", "Conceptual Design Plan", "Detailed Design Plan", "Implementation Plan");
        gridPane.add(planLbl, 1, 4);
        gridPane.add(planBox, 1, 5);
        //ActiveXComponent excel = new ActiveXComponent("EffortLoggerV1-12");
        //Dispatch.call(macro, "Run", "MyMacroName");
        
        Button stopBtn = new Button("Stop Activity");
        gridPane.add(stopBtn, 0, 6);
       
        stopBtn.setOnAction(event -> {
            String Project = projectBox.getValue();
            String LCS = LCSBox.getValue();
            String Effort = EffortBox.getValue();
            String Plan = planBox.getValue();
            System.out.println(nameBox.getValue() + " has clocked out from\n" + 
            		"Project: " + Project + 
            		"\nLife Cycle Step: " + LCS +
            		"\nEffort: " + Effort + 
            		"\nPlan  " + Plan);

        });
        
        Scene scene = new Scene(gridPane, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
  

    

    


}