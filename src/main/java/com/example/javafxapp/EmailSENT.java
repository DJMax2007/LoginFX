package com.example.javafxapp;

import com.example.javafxapp.ForgottenPassword.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;

public class EmailSENT {

    public static String email;

    @FXML
    private Label emailsentreset;


    @FXML
    public void initialize() {
        emailsentreset.setText("Email sent to " + email +". Reset link will expire in 15 minutes" );
    }
}
