package example.desktopapplication;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button SignUpButton;

    @FXML
    private CheckBox SignUpCheckBoxFemale;

    @FXML
    private CheckBox SignUpCheckBoxMale;

    @FXML
    private TextField SignUpCountry;

    @FXML
    private TextField SignUpLastName;

    @FXML
    private TextField SignUpName;

    @FXML
    private TextField loginField1;

    @FXML
    private PasswordField passwordField;

    @FXML
    void initialize() {

SignUpButton.setOnAction(event -> {
    signUpNewUser();


});
    }

    private void signUpNewUser() {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String firstName = SignUpName.getText();
        String lastName = SignUpLastName.getText();
        String userName = loginField1.getText();
        String password = passwordField.getText();
        String location = SignUpCountry.getText();
        String gender = "";
        if(SignUpCheckBoxMale.isSelected())
            gender = "Мужской";
        else
            gender = "Женский";
        User user = new User(firstName, lastName, userName, password,location,gender);

        dbHandler.signUpUsers(user );
    }

}

