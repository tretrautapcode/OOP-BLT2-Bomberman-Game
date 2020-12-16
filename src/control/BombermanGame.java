package control;

import graphics.Sprite;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.scene.text.Font;

public class BombermanGame extends Application {
    public static Pane playerPane;
    public static Scene scene;
    private static int point = 0;
    private long fps = 0;
    private int animation = 0;
    private static AnimationTimer gameLoop;

    public static void addPoint(int input) {
        point += input;
    }

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        Label label = new Label();
        label.setFont(new Font("Arial", 30));
        label.setLayoutX(0);
        label.setLayoutY(0);
        label.setMaxSize(Sprite.SCALED_SIZE * Setting.WIDTH, Sprite.SCALED_SIZE);
        playerPane = new Pane();
        playerPane.setLayoutY(Sprite.SCALED_SIZE);
        Group root = new Group();
        root.getChildren().addAll(label, playerPane);
        scene = new Scene(root, Sprite.SCALED_SIZE * Setting.WIDTH, Sprite.SCALED_SIZE * (Setting.HEIGHT + 1));

        stage.setScene(scene);

        stage.show();
        MyList.loadMap();
        render();
        Audio.playBackground();
        gameLoop = new AnimationTimer() {
            long timeStart = 0;

            @Override
            public void handle(long now) {
                animation++;
                if (now - timeStart > 1000_000_000) {
                    fps = animation / ((now - timeStart) / 1000_000_000);
                    animation = 0;
                    timeStart = now;
                }
                label.setText("Point: " + point + "     Fps: " + fps);
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
                point += 100;
            } else {
                i++;
            }
        }
        for (int i = 0; i < MyList.oneals.size(); ) {
            MyList.oneals.get(i).update();
            if (MyList.oneals.get(i).checkDestroy()) {
                MyList.oneals.remove(i);
                point += 200;
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
