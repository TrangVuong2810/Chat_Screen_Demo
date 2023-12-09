package main;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class ChatApplication extends Application {
    @FXML
    private ChatScreenController chatScreenController;

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("chatScreen.fxml"));
            scene = new Scene(fxmlLoader.load());
            chatScreenController = fxmlLoader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }

        chatScreenController.run();

        stage.setScene(scene);
        stage.setHeight(600);
        stage.setWidth(400);

        stage.setTitle("Chat Screen Demo");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}