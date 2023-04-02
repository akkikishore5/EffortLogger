import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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



public class EncryptionTest extends Application {
  
    //main function
    public static void main(String[] args) {
        launch(args);
    }
    String text;
    
    //Define the secret key
    public static final String SECRET_KEY = "1234567891234567";
    
    public void start(Stage primaryStage) {
      
        //set the title of the stage
        primaryStage.setTitle("Authentication System");
      
        //GridPane to display all the components
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
      
        //Login form
    	  Label usernameLabel = new Label("Username");
        TextField usernameTextField = new TextField();
        
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        
        //add the login form to the gridpane
        gridPane.add(usernameLabel, 0, 0);
        gridPane.add(usernameTextField, 1, 0);
        gridPane.add(passwordLabel, 0, 1);
        gridPane.add(passwordField, 1, 1);
        
        //create a loginbutton to test the submission and a clear to try another test
        Button loginButton = new Button("Login");
        Button clearButton = new Button("Clear");
        gridPane.add(loginButton, 0, 2);
        gridPane.add(clearButton, 1, 2); 
        
        loginButton.setOnAction(event -> {
            String username = usernameTextField.getText();
            String password = passwordField.getText();

            // Encrypt the username and password

            String encryptedUsername = encrypt(username, SECRET_KEY);
            String encryptedPassword = encrypt(password, SECRET_KEY);

            // Store the encrypted username and password
            System.out.println("Encrypted username: " + encryptedUsername);
            System.out.println("Encrypted password: " + encryptedPassword);

            // Decrypt the username and password
            String decryptedUsername = decrypt(encryptedUsername, SECRET_KEY);
            String decryptedPassword = decrypt(encryptedPassword, SECRET_KEY);

            // Display the decrypted username and password
            System.out.println("Decrypted username: " + decryptedUsername);
            System.out.println("Decrypted password: " + decryptedPassword);
        });
        clearButton.setOnAction(event -> {
            usernameTextField.clear();
            passwordField.clear();
        });
      
        //Create the scene and load the gridPane
        Scene scene = new Scene(gridPane, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Authentication System");
        primaryStage.show();
        
    }
    
    
    private static String encrypt(String input, String secretKey) {
        try {
            // Create a key from the secret key bytes
            byte[] keyBytes = secretKey.getBytes();
            Key key = new SecretKeySpec(keyBytes, "AES");

            // Create the cipher and initialize it for encryption
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            // Encrypt the input string and encode it as a Base64 string
            byte[] encrypted = cipher.doFinal(input.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private static String decrypt(String input, String secretKey) {
        try {
            // Create a key from the secret key bytes
            byte[] keyBytes = secretKey.getBytes();
            Key key = new SecretKeySpec(keyBytes, "AES");

            // Create the cipher and initialize it for decryption
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);

            // Decode the input string as a Base64 string and decrypt it
            byte[] decoded = Base64.getDecoder().decode(input);
            byte[] decrypted = cipher.doFinal(decoded);
            return new String(decrypted);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    


}
