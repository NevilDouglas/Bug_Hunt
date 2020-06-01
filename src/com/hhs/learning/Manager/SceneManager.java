package com.hhs.learning.Manager;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements the main scene of the game
 */
public class SceneManager {

    /**
     * Constants
     */
    private final static int PANE_WIDTH = 500;
    private final static int PANE_HEIGHT = 700;
    private final static int BUTTON_WIDTH = 200;
    private final static int BUTTON_HEIGHT = 50;
    private final static int FIRST_BUTTON_X_POS = 140;
    private final static int FIRST_BUTTON_Y_POS = PANE_HEIGHT/3;

    /**
     * Stage components
     */
    private AnchorPane rootPane;
    private Scene rootScene;
    private Stage rootStage;
    private List<Button> menuButtons;

    /**
     * Constructor
     */
    public SceneManager() {
        menuButtons = new ArrayList<>();
        rootPane = new AnchorPane();
        rootPane.setPrefSize(PANE_WIDTH, PANE_HEIGHT);
        rootScene = new Scene(rootPane);
        rootStage = new Stage();
        rootStage.setScene(rootScene);
        createButtons();
        createBackground();
        createGameTitle();
    }

    /**
     * Sets up the buttons
     */
    private void createButtons() {
        createStartButton();
        createExitButton();
    }

    /**
     * Sets up the START button
     */
    private void createStartButton() {
        Button startButton = new Button("NEW GAME");
        addMenuButtons(startButton);

        startButton.setOnAction(event -> {
            GameSceneManager game = new GameSceneManager();
            game.createGame(rootStage);
        });
    }

    /**
     * Sets up the EXIT button
     */
    private void createExitButton() {
        Button exitButton = new Button("EXIT");
        addMenuButtons(exitButton);

        exitButton.setOnAction(event -> rootStage.close());
    }

    /**
     * Adds a button the to the ArrayList of Buttons
     * @param button takes an instance of Button
     */
    private void addMenuButtons(Button button) {
        button.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        button.setLayoutX(FIRST_BUTTON_X_POS);
        button.setLayoutY(FIRST_BUTTON_Y_POS + (menuButtons.size() * 100));
        menuButtons.add(button);
        rootPane.getChildren().add(button);
    }

    /**
     * Sets up the background of the application
     */
    private void createBackground() {
        try {
            FileInputStream file = new FileInputStream("res/sprites/background.png");
            Image bgImage = new Image(file, PANE_WIDTH, PANE_HEIGHT, false, true);
            BackgroundImage bg = new BackgroundImage(bgImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
            rootPane.setBackground(new Background(bg));
        } catch (FileNotFoundException e ) {
            System.out.println("SceneManager: File not found, please check the path.");
        }
    }

    private void createGameTitle() {

        Label titleLabel = new Label("Bug Hunt");
        titleLabel.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 60));
        titleLabel.setLayoutX(120);
        titleLabel.setLayoutY(50);
        rootPane.getChildren().add(titleLabel);
    }

    /**
     * Returns Stage instance instantiated in the constructor
     *
     * @return a Stage instance
     */
    public Stage getMainStage() {
        return rootStage;
    }
}
