package control;

import graphics.Sprite;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static com.sun.javafx.scene.control.skin.Utils.executeOnceWhenPropertyIsNonNull;
import static com.sun.javafx.scene.control.skin.Utils.getResource;

public class BombermanGame extends Application {
    public static Pane playerPane;
    public static Scene scene;
    private static int level = 0;
    private int Point = 0;
    private int fps;
    private static AnimationTimer gameLoop;

    public void addPoint(int input) {
        Point += input;
    }

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        playerPane = new Pane();
        Group root = new Group();
        root.getChildren().addAll(playerPane);
        scene = new Scene(root, Sprite.SCALED_SIZE * Setting.WIDTH, Sprite.SCALED_SIZE * Setting.HEIGHT);

        stage.setScene(scene);

        stage.show();
        MyList.loadMap();
        Audio.playBackground();
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                render();
            }
        };
        gameLoop.start();
    }

    public static void Win() {
        Audio.victory();
        try {
            InputStream inputStream = new FileInputStream("res/sprites/WinGame.jpg");
            Image image = new Image(inputStream);
            playerPane.getChildren().add(new ImageView(image));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        gameLoop.stop();
    }

    public static void Lose() {
        Audio.stopBackground();
        Audio.bomberDeathSound();
        try {
            InputStream inputStream = new FileInputStream("res/sprites/lose.jpg");
            Image image = new Image(inputStream);
            playerPane.getChildren().add(new ImageView(image));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        gameLoop.stop();
    }

    public void update() {
        if (MyList.player != null)
            MyList.player.update();
        MyList.update();
        for (int i = 0; i < MyList.ballooms.size(); ) {
            MyList.ballooms.get(i).update();
            if (MyList.ballooms.get(i).checkDestroy()) {
                MyList.ballooms.remove(i);
            } else {
                i++;
            }
        }
        for (int i = 0; i < MyList.oneals.size(); ) {
            MyList.oneals.get(i).update();
            if (MyList.oneals.get(i).checkDestroy()) {
                MyList.oneals.remove(i);
            } else {
                i++;
            }
        }
    }

    public void render() {
        MyList.render();
        MyList.ballooms.forEach(g -> g.render());
        MyList.oneals.forEach(g -> g.render());
        if (MyList.player != null)
            MyList.player.render();
    }
}
