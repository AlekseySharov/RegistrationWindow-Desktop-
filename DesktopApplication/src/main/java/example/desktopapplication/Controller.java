package example.desktopapplication;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import example.desktopapplication.animations.Shake;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button authSignInButton;

    @FXML
    private TextField loginField;

    @FXML
    private Button loginSignUpButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    void initialize() {
        authSignInButton.setOnAction(event -> {
            String loginText = loginField.getText().trim();
            String loginPassword = passwordField.getText().trim();

            if(!loginText.equals("") && !loginPassword.equals(""))
                loginUser(loginText, loginPassword);
            else
                System.out.println("Login and password is empty");
        });

        loginSignUpButton.setOnAction(event ->{
newOpenScene("/example/desktopapplication/signUp.fxml");
        });

    }

    private void loginUser(String loginText, String loginPassword) {
DatabaseHandler databaseHandler = new DatabaseHandler();
User user = new User();
user.setUserName(loginText);
user.setPassword(loginPassword);
ResultSet resultSet = databaseHandler.getUser(user);

int count = 0;
while(true){
    try {
        if (!resultSet.next()) break;
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    count++;
}

if(count>=1)
{
newOpenScene("/example/desktopapplication/app.fxml");
}else{
    Shake userLoginAnim = new Shake(loginField);
    Shake userPassAnim = new Shake(passwordField);
    userLoginAnim.playAnim();
    userPassAnim.playAnim();
}
    }
    public void newOpenScene(String window)
    {
        loginSignUpButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

}
