package com.example.javafxapp;

import com.example.javafxapp.MyJDBC;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.*;
import java.io.IOException;

public class FinalPage {

    public static String username;

    @FXML
    private Label TitleLabel;

    @FXML
    private Label UserLabel;

    @FXML
    private Label EmailLabel;

    @FXML
    private Label PhoneLabel;

    @FXML
    private ImageView imageview456;

    @FXML
    private ImageView imageview789;

    @FXML
    private Button logout;

    @FXML
    public void initialize() {
        Image image = new Image(getClass().getResource("/image.png").toExternalForm());
        imageview456.setImage(image);


        String email = MyJDBC.getEmailFromDatabase(username);
        String phone = MyJDBC.getPhoneFromDatabase(username);
        String countryCode = MyJDBC.getCCFromDatabase(username);

        UserLabel.setText("Welcome, " + username);
        EmailLabel.setText("Your Email: " + email);
        PhoneLabel.setText("Your Phone: " + countryCode +"-"+ phone);

    }

    @FXML
    protected void onReturnButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("return-page.fxml"));
            Scene newScene = new Scene(loader.load());
            Stage currentStage = (Stage) logout.getScene().getWindow();
            currentStage.setScene(newScene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading the return-page.fxml file.");
        }
    }
}