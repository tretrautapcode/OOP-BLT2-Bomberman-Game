package control;

import entities.DestroyAble.Character.Balloom;
import entities.DestroyAble.Character.Bomber;
import entities.DestroyAble.Character.Oneal;
import entities.DestroyAble.Bomb;
import entities.DestroyAble.Brick;
import entities.DestroyAble.Flame;
import entities.Item.BombItem;
import entities.Item.FlameItem;
import entities.Item.SpeedItem;
import entities.StillEntity.*;
import graphics.Sprite;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

public class BombermanGame extends Application {
    public static Pane playerPane;
    public static List<Grass>grasses=new ArrayList<>();
    public static List<Wall>walls=new ArrayList<>();
    public static List<Brick>bricks=new ArrayList<>();
    public static List<Balloom>ballooms=new ArrayList<>();
    public static List<Oneal>oneals=new ArrayList<>();
    public static List<Portal>portals=new ArrayList<>();
    public static List<Flame>flames=new ArrayList<>();
    public static List<FlameItem>flameItems=new ArrayList<>();
    public static List<SpeedItem>speedItems=new ArrayList<>();
    public static List<BombItem>bombItems=new ArrayList<>();
    public static List<Bomb>bombs=new ArrayList<>();
    public static Bomber player;
    public static Scene scene;
    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        playerPane = new Pane();
        playerPane.setStyle("-fx-background-color: black;");
        Group root = new Group();
        root.getChildren().addAll(playerPane);
        scene = new Scene(root, Sprite.SCALED_SIZE * Setting.WIDTH, Sprite.SCALED_SIZE * Setting.HEIGHT);

        stage.setScene(scene);

        stage.show();
        loadMap();
        addToPane(playerPane);
        render();
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(player.checkDestroy()){
                    stop();
                }
                update();
                render();
            }
        };
        gameLoop.start();
        player.KeyEvent();
    }

    public void loadMap() {
        File file = new File("res/levels/Level1.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int x;
        x = scanner.nextInt();
        Setting.R = scanner.nextInt();
        Setting.L = scanner.nextInt();
        String s;
        s = scanner.nextLine();
        for (int i = 0; i < Setting.R; ++i) {
            s = scanner.nextLine();
            for (int j = 0; j < Setting.L; ++j) {
                switch (s.charAt(j)) {
                    case '#':
                        walls.add(new Wall(j, i, Sprite.wall.getFxImage()));
                        break;
                    case '*':
                        grasses.add(new Grass(j,i,Sprite.grass.getFxImage()));
                        bricks.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        break;
                    case 'x':
                        bricks.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        portals.add(new Portal(j, i, Sprite.portal.getFxImage()));
                        break;
                    case 'p':
                        player = new Bomber(j, i, Sprite.player_right.getFxImage());
                        grasses.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        break;
                    case '1':
                        grasses.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        ballooms.add(new Balloom(j, i, Sprite.balloom_left1.getFxImage()));
                        break;
                    case '2':
                        grasses.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        oneals.add(new Oneal(j, i, Sprite.oneal_left1.getFxImage()));
                        break;
                    case 's':
                        bricks.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        speedItems.add(new SpeedItem(j, i, Sprite.powerup_speed.getFxImage()));
                        break;
                    case 'f':
                        bricks.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        flameItems.add(new FlameItem(j, i, Sprite.powerup_flames.getFxImage()));
                        break;
                    case 'b':
                        bricks.add(new Brick(j, i, Sprite.brick.getFxImage()));
                        bombItems.add(new BombItem(j, i, Sprite.powerup_bombs.getFxImage()));
                        break;
                    default:
                        grasses.add(new Grass(j, i, Sprite.grass.getFxImage()));
                }
            }
        }
    }

    public void update() {
        player.update();
        for (int i=0;i<ballooms.size();)
        {
            ballooms.get(i).update();
            if (ballooms.get(i).checkDestroy()) {
                ballooms.remove(i);
            }else {
                i++;
            }
        }
        for (int i=0;i<oneals.size();)
        {
            oneals.get(i).update();
            if (oneals.get(i).checkDestroy()) {
                oneals.remove(i);
            }else {
                i++;
            }
        }
        for (int i=0;i<bricks.size();)
        {
            bricks.get(i).update();
            if(bricks.get(i).checkDestroy()){
                bricks.remove(i);
            }else {
                i++;
            }
        }
        for (int i=0;i<bombs.size();)
        {
            bombs.get(i).update();
            if(bombs.get(i).checkDestroy()){
                bombs.remove(i);
            }else {
                i++;
            }
        }
        for (int i=0;i<flames.size();)
        {
            flames.get(i).update();
            if(flames.get(i).checkDestroy()){
                flames.remove(i);
            }else {
                i++;
            }
        }
    }

    public void addToPane(Pane pane) {
        grasses.forEach(g -> g.addToPane());
        walls.forEach(g -> g.addToPane());
        portals.forEach(g->g.addToPane());
        flameItems.forEach(g -> g.addToPane());
        bombItems.forEach(g -> g.addToPane());
        speedItems.forEach(g -> g.addToPane());
        bricks.forEach(g -> g.addToPane());
        ballooms.forEach(g->g.addToPane());
        oneals.forEach(g->g.addToPane());
        player.addToPane();
    }

    public void render() {
        grasses.forEach(g -> g.render());
        walls.forEach(g -> g.render());
        portals.forEach(g->g.render());
        flameItems.forEach(g -> g.render());
        bombItems.forEach(g -> g.render());
        speedItems.forEach(g -> g.render());
        ballooms.forEach(g->g.render());
        oneals.forEach(g->g.render());
        bricks.forEach(g -> g.render());
        bombs.forEach(g->g.render());
        flames.forEach(g->g.render());
        player.render();
    }
}
