package com.example.javafxapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.*;
import java.io.IOException;

public class HelloController{
    @FXML
    private Label TitleLabel;

    @FXML
    private Label ReturningLabel;

    @FXML
    private Label FirstLabel;

    @FXML
    private Label RExplainLabel;

    @FXML
    private Label FExplainLabel;


    @FXML
    private ImageView imageview456;

    @FXML
    private ImageView imageview789;

    @FXML
    private Button Continuebutton;

    @FXML
    private Button CreateAccountbutton;


    @FXML
    public void initialize() {

        Image image = new Image(getClass().getResource("/image.png").toExternalForm());
        imageview456.setImage(image);

    }

    @FXML
    protected void onFirstButtonClick(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login-page.fxml"));
            Scene newScene = new Scene(loader.load());
            Stage currentStage = (Stage) CreateAccountbutton.getScene().getWindow();
            currentStage.setScene(newScene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading the login-page.fxml file.");
        }
    }

    @FXML
    protected void onReturnButtonClick(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("return-page.fxml"));
            Scene newScene = new Scene(loader.load());
            Stage currentStage = (Stage) Continuebutton.getScene().getWindow();
            currentStage.setScene(newScene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading the login-page.fxml file.");
        }
    }

}