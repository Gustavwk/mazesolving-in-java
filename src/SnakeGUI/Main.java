package SnakeGUI;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        //loader.load(getClass().getResource("SnakeGUI.fxml".ope));
        Parent root = loader.load();
        primaryStage.setTitle("PacMan EC2");

        Controller controller = (Controller) loader.getController();

        Scene scene = new Scene(root, 600,400);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

            }
        });
        primaryStage.setScene(scene);
        primaryStage.getScene().getStylesheets().add("SnakeGUI/stylesheet.css");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
