package com.hhs.learning.Manager;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements the scene where the player has started the game
 */
public class GameSceneManager {

    /**
     * Constants
     */
    private final static int PANE_WIDTH = 500;
    private final static int GAME_PANE_HEIGHT = 700;
    private final static int CONTROL_PANE_HEIGHT = 200;
    private final static int SCREEN_PANE_HEIGHT = 500;
    //    private final static double SCREEN_PANE_WIDTH = 500;
//    private final static double SCREEN_PANE_HEIGHT = 500;
    private final static int BUTTON_WIDTH = 80;
    private final static int BUTTON_HEIGHT = 50;
    private final static int TILE_SIZE = 50;
    private final static int GRID_ROWS = 10;
    private final static int GRID_COLUMNS = 10;

//    private int n = 10;
//    private int m = 10;
//    private double gridWidth = SCREEN_PANE_WIDTH / n;
//    private double gridHeight = SCREEN_PANE_HEIGHT / m;
//    private Tile tile;
//    private Tile[][] playfield = new Tile[n][m];
//    private Bug bug1;


    /**
     * Stage elements
     */
    private Stage gameStage;
    private Scene gameScene;
    private AnchorPane gamePane;
    private FlowPane consolePane;
    private List<Button> controllerButtons;
    private Stage mainStage;
    private GridPane screenPane;
//    private StackPane screenPane;

    private ImageView spider;

    /**
     *
     */
    public GameSceneManager() {
        initStage();
    }

    private void initStage() {
        gamePane = new AnchorPane();
        gamePane.setPrefSize(PANE_WIDTH, GAME_PANE_HEIGHT);
        gameScene = new Scene(gamePane);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
        createController();
        createScreen();

    }

    private void createScreen() {
//        screenPane = new StackPane();
        screenPane = new GridPane();
        screenPane.setPrefSize(PANE_WIDTH, SCREEN_PANE_HEIGHT);
        screenPane.setLayoutX(0);
        screenPane.setLayoutY(0);

        gamePane.getChildren().add(screenPane);

        try {
            FileInputStream file = new FileInputStream("res/sprites/background.png");
            Image bgImage = new Image(file, PANE_WIDTH, SCREEN_PANE_HEIGHT, false, true);
            BackgroundImage bg = new BackgroundImage(bgImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
            screenPane.setBackground(new Background(bg));
        } catch (FileNotFoundException e) {
            System.out.println("GameSceneManager: File not found, please check the path.");
        }


        for (int i = 0; i < GRID_ROWS; i++) {
            screenPane.addColumn(i);
            for (int j = 0; j < GRID_COLUMNS; j++) {
                Rectangle baseTile = new Rectangle(TILE_SIZE - 1, TILE_SIZE - 1);
                baseTile.setStroke(Color.BLACK);
                baseTile.setFill(Color.TRANSPARENT);
                screenPane.addRow(i, baseTile);
            }
        }

        try {
            spider = new ImageView(new Image(new FileInputStream("res/sprites/spider.png")));
            screenPane.getChildren().add(spider);
        } catch (FileNotFoundException e) {
            System.out.println("Image URL not found. Please check the path.");
        }


//        for( int i=0; i < n; i++) {
//            for( int j=0; j < m; j++) {
//                // create node
//                if (i==0 && j==0) {
//                    tile = new Bug(i * gridWidth, j * gridHeight, gridWidth, gridHeight);
//                    bug1 = (Bug) tile;
//                    bug1.setxPos(i);
//                    bug1.setyPos(j);
//
//                } else {
//                    tile = new Tile(i * gridWidth, j * gridHeight, gridWidth, gridHeight);
//                    // add node to group
//                }
//                gamePane.getChildren().add(tile);
//                // add to playfield for further reference using an array
//                playfield[i][j] = tile;
//            }
//        }
//
//        tile.setPrefSize(SCREEN_PANE_WIDTH, SCREEN_PANE_HEIGHT);
//        tile.setLayoutX(0);
//        tile.setLayoutY(0);
    }

    private void createController() {
        controllerButtons = new ArrayList<>();
        consolePane = new FlowPane();
        consolePane.setPrefSize(PANE_WIDTH, CONTROL_PANE_HEIGHT);
        consolePane.setHgap(10);
        consolePane.setAlignment(Pos.CENTER);
        consolePane.setLayoutX(0);
        consolePane.setLayoutY(GAME_PANE_HEIGHT - CONTROL_PANE_HEIGHT);
        consolePane.setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));
        gamePane.getChildren().add(consolePane);
        createButtons();

    }

    private void addConsoleButtons(Button button) {
        button.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        consolePane.getChildren().add(button);
        controllerButtons.add(button);
    }

    private void createButtons() {
        createUpButton();
        createDownButton();
        createLeftButton();
        createRightButton();
        createMoveButton();
    }

    private void createLeftButton() {
        Button leftButton = new Button("A");
        addConsoleButtons(leftButton);

        leftButton.setOnAction(event -> {
            if(leftButton.getRotate() != -90) {
                spider.setRotate(-90);
            }
        });
    }

    private void createRightButton() {
        Button rightButton = new Button("D");
        addConsoleButtons(rightButton);

        rightButton.setOnAction(event -> {
            if(spider.getRotate() != 90) {
                spider.setRotate(90);
            }
        });

    }

    private void createUpButton() {
        Button upButton = new Button("W");
        addConsoleButtons(upButton);

        upButton.setOnAction(event -> {
            if(spider.getRotate() != 0) {
                spider.setRotate(0);
            }
        });

    }

    private void createDownButton() {
        Button downButton = new Button("S");
        addConsoleButtons(downButton);

        downButton.setOnAction(event -> {
            if(spider.getRotate() != 180) {
                spider.setRotate(180);
            }
        });

    }

    private void createMoveButton() {
        Button moveButton = new Button("MOVE");
        addConsoleButtons(moveButton);

        moveButton.setOnAction(event -> {
            try {
                if(spider.getTranslateX() <= PANE_WIDTH) {
                    spider.setTranslateX(spider.getTranslateX() + 50);
                }
            } catch (Exception e) {
                System.out.println("You've hit the wall.");
            }

        });
//        moveButton.setOnAction(event -> {
//            bug1.setxPos(bug1.getxPos() + 1);
//            bug1.setyPos(bug1.getyPos() + 1);
//            bug1.update();
//
//        });
    }


    public void createGame(Stage mainStage) {
        this.mainStage = mainStage;
        this.mainStage.hide();
        this.gameStage.show();
    }


//    public void update() {
//        bug1.update();
//    }
}
