package control;

import entities.Entity;
import entities.dow.*;
import entities.up.*;
import graphics.Sprite;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

public class BombermanGame extends Application {

    public static final int WIDTH = 20;
    public static final int HEIGHT = 13;
    public static int animation = 0;
    public static GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> upEntities = new ArrayList<>();
    private List<Entity> dowEntities = new ArrayList<>();

    private Bomber player;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);

        stage.show();
        loadMap();
        render();
        AnimationTimer gameLoop=new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        /*AnimationTimerExt gameLoop = new AnimationTimerExt(6000) {
            @Override
            public void handle() {
                update();
            }
        };*/
        event(scene);
        gameLoop.start();

    }

    public void event(Scene scene) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.RIGHT) {
                    player.moveRight();
                }
                if (event.getCode() == KeyCode.LEFT) {
                    player.moveLeft();
                }
                if (event.getCode() == KeyCode.UP) {
                    player.moveUp();
                }
                if (event.getCode() == KeyCode.DOWN) {
                    player.moveDow();
                }
            }
        });
    }

    public void loadMap() {
        File file = new File("res/levels/Level1.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int x, R, L;
        x = scanner.nextInt();
        R = scanner.nextInt();
        L = scanner.nextInt();
        String s;
        s = scanner.nextLine();
        for (int i = 0; i < R; ++i) {
            s = scanner.nextLine();
            for (int j = 0; j < L; ++j) {
                switch (s.charAt(j)) {
                    case '#':
                        dowEntities.add(new Wall(j, i, Sprite.wall.getFxImage()));
                        break;
                    case '*':
                        upEntities.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        break;
                    case 'x':
                        upEntities.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        dowEntities.add(new Portal(j, i, Sprite.portal.getFxImage()));
                        break;
                    case 'p':
                        player = new Bomber(j, i, Sprite.player_right.getFxImage());
                        dowEntities.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        upEntities.add(player);
                        break;
                    case '1':
                        dowEntities.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        upEntities.add(new Balloom(j, i, Sprite.balloom_left1.getFxImage()));
                        break;
                    case '2':
                        dowEntities.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        upEntities.add(new Oneal(j, i, Sprite.oneal_left1.getFxImage()));
                        break;
                    case 's':
                        upEntities.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        dowEntities.add(new SpeedItem(j, i, Sprite.powerup_speed.getFxImage()));
                        break;
                    case 'f':
                        upEntities.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        dowEntities.add(new FlameItem(j, i, Sprite.powerup_flames.getFxImage()));
                        break;
                    case 'b':
                        upEntities.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        dowEntities.add(new BombItem(j, i, Sprite.powerup_bombs.getFxImage()));
                        break;
                    default:
                        dowEntities.add(new Grass(j, i, Sprite.grass.getFxImage()));
                }
            }
        }
    }

    public void update() {
        animation();
        upEntities.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        dowEntities.forEach(g -> g.render());
        upEntities.forEach(g -> g.render());
    }

    public static void animation() {
        animation++;
        if (animation >= 1000000) animation = 0;
    }
}
