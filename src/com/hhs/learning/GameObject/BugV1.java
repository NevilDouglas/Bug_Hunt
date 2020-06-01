//package com.hhs.learning.GameObject;
//
//import com.hhs.learning.Map.Tile;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//
//public class Bug extends Tile {
//
//    ImageView fly;
//    double xPos = 0;
//    double yPos = 0;
//
//    public Bug(double x, double y, double width, double height) {
//        super(x, y, width, height);
//        try {
//            FileInputStream file = new FileInputStream("res/sprites/fly.png");
//            ImageView flyImg = new ImageView(new Image(file, width, height, false, true));
//            this.fly = flyImg;
//            getChildren().add(flyImg);
//        } catch (FileNotFoundException e) {
//            System.out.println("GameSceneManager: File not found, please check the path.");
//        }
//    }
//
//    public ImageView getFly() {
//        return fly;
//    }
//
//    public void setxPos(double xPos) {
//        this.xPos = xPos;
//    }
//
//    public void setyPos(double yPos) {
//        this.yPos = yPos;
//    }
//
//    public double getxPos() {
//        return xPos;
//    }
//
//    public double getyPos() {
//        return yPos;
//    }
//
//    public void update() {
//        fly.setLayoutX(getLayoutX());
//        fly.setLayoutY(getLayoutY());
//    }
//}
