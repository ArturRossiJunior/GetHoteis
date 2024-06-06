package com.n2.hotelaria;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


public class App extends Application {

    private static Scene scene;
    
    @Override
    public void start(Stage stage) throws IOException {
        //scene = new Scene(loadFXML("Login"));
        scene = new Scene(loadFXML("Login"));
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.setMinHeight(400);
        stage.setMinWidth(300);
        stage.show();
    }

    public static void changeScene(String fxml, Stage... optionalStage) throws IOException {
        Stage stage = (optionalStage.length > 0 && optionalStage[0] != null) ? optionalStage[0] : (Stage) scene.getWindow();
        stage.setScene(new Scene(loadFXML(fxml)));
        stage.sizeToScene();
    }
    
    public static void openNewWindow(String fxml) throws IOException {
        Stage newStage = new Stage();
        newStage.setScene(new Scene(loadFXML(fxml)));
        newStage.sizeToScene();
        newStage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}