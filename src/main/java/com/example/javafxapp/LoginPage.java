package com.example.javafxapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class LoginPage {
    @FXML
    private Label Usernamelabel;

    @FXML
    private Label Passwordlabel;

    @FXML
    private Label Emaillabel;

    @FXML
    private Label Phonelabel;

    @FXML
    private TextField Usernamefield;

    @FXML
    private PasswordField Passwordfield2;

    @FXML
    private TextField Emailfield;

    @FXML
    private TextField Phonefield;

    @FXML
    private ComboBox<CountryCode> countryCodeComboBox;

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
    private Button Submit_login;

    @FXML
    public void initialize() {
//        ObservableList<String> countryCodes = FXCollections.observableArrayList(
//                "+1 (US)", "+44 (UK)", "+234 (Nigeria)", "+971 (UAE)"
//        );
//
//        countryCodeComboBox.setItems(countryCodes);

        ObservableList<CountryCode> countryCodes = FXCollections.observableArrayList(
                new CountryCode("+1", "+1 (US)"),
                new CountryCode("+44", "+44 (UK)"),
                new CountryCode("+234", "+234 (Nigeria)"),
                new CountryCode("+971", "+971 (UAE)")
        );

        countryCodeComboBox.setItems(countryCodes);

        countryCodeComboBox.setConverter(new StringConverter<CountryCode>() {
            @Override
            public String toString(CountryCode countryCode) {
                return countryCode != null ? countryCode.getDisplayName() : "";
            }

            @Override
            public CountryCode fromString(String string) {
                for (CountryCode countryCode : countryCodes) {
                    if (countryCode.getDisplayName().equals(string)) {
                        return countryCode;
                    }
                }
                return null;
            }
        });


        Image image = new Image(getClass().getResource("/image.png").toExternalForm());
        imageview123.setImage(image);
    }

    @FXML
    protected void onSubmitButtonClick() {
        String username = Usernamefield.getText();
        String password = Passwordfield2.getText();
        String email = Emailfield.getText();
        String countryCode = null;
        CountryCode selectedCountryCode = countryCodeComboBox.getSelectionModel().getSelectedItem();
        if (selectedCountryCode != null) {
            countryCode = selectedCountryCode.getCode();
        }
        String phone = Phonefield.getText();

        if (Agreecheckbox.isSelected()) {
            if (MyJDBC.isUsernameTaken(username)) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("username.fxml"));
                    Scene newScene = new Scene(loader.load());
                    Stage agreeStage = new Stage();
                    agreeStage.setScene(newScene);
                    agreeStage.setTitle("Username Taken");
                    agreeStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Error loading the login-page.fxml file.");
                }
            } else {
                if (MyJDBC.isEmailTaken(email)) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("email.fxml"));
                        Scene newScene = new Scene(loader.load());
                        Stage agreeStage = new Stage();
                        agreeStage.setScene(newScene);
                        agreeStage.setTitle("Email Taken");
                        agreeStage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("Error loading the login-page.fxml file.");
                    }
                } else {
                    if (countryCode != null) {
                        if (MyJDBC.isPhoneTaken(phone, countryCode)) {
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("phone.fxml"));
                                Scene newScene = new Scene(loader.load());
                                Stage agreeStage = new Stage();
                                agreeStage.setScene(newScene);
                                agreeStage.setTitle("Phone Number Taken");
                                agreeStage.show();
                            } catch (IOException e) {
                                e.printStackTrace();
                                System.out.println("Error loading the login-page.fxml file.");
                            }
                        } else {
                            saveToDatabase(username, password, email, countryCode, phone);
                            FinalPage.username = username;
                        }
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("final_page.fxml"));
                            Scene newScene = new Scene(loader.load());
                            Stage currentStage = (Stage) Submit_login.getScene().getWindow();
                            currentStage.setScene(newScene);
                            currentStage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println("Error loading the next page.");
                        }
                    }

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
                System.out.println("Error loading the login-page.fxml file.");
            }
        }
    }


    private void saveToDatabase(String username, String password, String email, String countryCode, String phone) {
        String url = "jdbc:mysql://127.0.0.1:3306/login_schema";
        String dbUser = "root";
        String dbPassword = "Makuasql@123!";

        String insertQuery = "INSERT INTO users (Username, Password, Email, Country_code, Phone_number) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, dbUser, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, countryCode);
            preparedStatement.setString(5, phone);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Data saved successfully!");
            } else {
                System.out.println("Failed to save data.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onBackButtonClick(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Scene newScene = new Scene(loader.load());
            Stage currentStage = (Stage) BackButton.getScene().getWindow();
            currentStage.setScene(newScene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading the login-page.fxml file.");
        }
    }
}