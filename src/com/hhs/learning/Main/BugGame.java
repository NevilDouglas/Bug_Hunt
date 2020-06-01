package com.hhs.learning.Main;

import com.hhs.learning.Manager.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

/*
   Implements the BugGame application.
 */

public class BugGame extends Application {

    @Override
    public void start(Stage stage) {
        SceneManager sceneManager = new SceneManager();
        stage = sceneManager.getMainStage();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
