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


public class ForgottenPassword {
    @FXML
    private Label Passwordlabel;

    @FXML
    private Label Emaillabel;

    @FXML
    private Label invalid;

    @FXML
    private PasswordField Passwordfield;

    @FXML
    private TextField Emailfield;

    @FXML
    private CheckBox Agreecheckbox;

    @FXML
    private ImageView imageview123;

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
    private Button EmailReset;


    @FXML
    public void initialize() {

        Image image = new Image(getClass().getResource("/image.png").toExternalForm());
        imageview123.setImage(image);
    }

    @FXML
    protected void onBackButtonClick(){
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
    protected void onEmailResetButtonClick(){
        String email = Emailfield.getText();
        boolean emailTaken = MyJDBC.isEmailTaken(email);
        if (Emailfield != null && emailTaken) {
            EmailSENT.email = email;
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("emailsent.fxml"));
                Scene newScene = new Scene(loader.load());
                Stage currentStage = (Stage) EmailReset.getScene().getWindow();
                currentStage.setScene(newScene);
                currentStage.show();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error loading the login-page.fxml file.");
            }
        }else{
            invalid.setText("Invalid Email. Please try again. ");
        }
    }

}

