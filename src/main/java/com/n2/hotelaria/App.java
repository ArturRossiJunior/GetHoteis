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
        scene = new Scene(loadFXML("Login"));
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.setMinHeight(400);
        stage.setMinWidth(300);
        stage.show();
    }

   public static void changeScene(String fxml) throws IOException {
    Stage stage = (Stage) scene.getWindow();
    changeScene(fxml, stage);
}

public static void changeScene(String fxml, Stage stage) throws IOException {
    Parent root = loadFXML(fxml);
    Scene newScene = new Scene(root);
    stage.setScene(newScene);
    stage.sizeToScene(); // Ajusta o tamanho da janela para corresponder ao tamanho do conteúdo
}

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
    
    
}