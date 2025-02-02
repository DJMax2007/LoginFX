package com.example.javafxapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import java.io.IOException;


public class EmailLogin {
    @FXML
    private Label Passwordlabel;

    @FXML
    private Label Emaillabel;

    @FXML
    private Label invalid1;

    @FXML
    private PasswordField Passwordfield2;

    @FXML
    private TextField Emailfield;

    @FXML
    private CheckBox Agreecheckbox;

    @FXML
    private ImageView imageview123;

    @FXML
    private Hyperlink ForgotPassword;

    @FXML
    private Shape circle;

    @FXML
    private Shape line1;

    @FXML
    private Shape line2;

    @FXML
    private Shape line3;

    @FXML
    private Button BackButton;

    @FXML
    private Button Submit_login;


    @FXML
    public void initialize() {

        Image image = new Image(getClass().getResource("/image.png").toExternalForm());
        imageview123.setImage(image);
    }

    @FXML
    protected void onBackButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("return-page.fxml"));
            Scene newScene = new Scene(loader.load());
            Stage currentStage = (Stage) BackButton.getScene().getWindow();
            currentStage.setScene(newScene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading the login-page.fxml file.");
        }
    }

    @FXML
    protected void onEmailLoginClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("forgotten_password.fxml"));
            Scene newScene = new Scene(loader.load());
            Stage currentStage = (Stage) ForgotPassword.getScene().getWindow();
            currentStage.setScene(newScene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading the login-page.fxml file.");
        }
    }

    @FXML
    protected void onSubmitButtonClick() {
        String email = Emailfield.getText();
        String password = Passwordfield2.getText();


        if (Agreecheckbox.isSelected()) {
            if (MyJDBC.isEmailTaken(email)) {
                String username = MyJDBC.getUserFromDatabase(email);
                String retrievedPassword = MyJDBC.getPasswordFromDatabase(username);

                if (retrievedPassword.equals(password)) {
                    FinalPage.username = username;
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("final_page.fxml"));
                        Scene newScene = new Scene(loader.load());
                        Stage currentStage = (Stage) Submit_login.getScene().getWindow();
                        currentStage.setScene(newScene);
                        currentStage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("Error loading the final_page.fxml file.");
                    }
                } else {
                    invalid1.setText("Invalid Password. Please try again. ");
                }
            } else {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("user_undefined.fxml"));
                    Scene newScene = new Scene(loader.load());
                    Stage agreeStage = new Stage();
                    agreeStage.setScene(newScene);
                    agreeStage.setTitle("Username Not Found");
                    agreeStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Error loading the user-defined.fxml file.");
                }
            }
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("agree.fxml"));
                Scene newScene = new Scene(loader.load());
                Stage agreeStage = new Stage();
                agreeStage.setScene(newScene);
                agreeStage.setTitle("Agreement Page");
                agreeStage.show();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error loading the agree.fxml file.");
            }
        }

    }
}


