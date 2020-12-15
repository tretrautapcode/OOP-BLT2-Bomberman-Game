package control;

import entities.DestroyAble.Bomb;
import entities.DestroyAble.Brick;
import entities.DestroyAble.Character.Balloom;
import entities.DestroyAble.Character.Bomber;
import entities.DestroyAble.Character.Character;
import entities.DestroyAble.Character.Oneal;
import entities.DestroyAble.Flame;
import entities.Entities;
import entities.Entity;
import entities.Item.BombItem;
import entities.Item.FlameItem;
import entities.Item.SpeedItem;
import entities.StillEntity.Grass;
import entities.StillEntity.Portal;
import entities.StillEntity.Wall;
import graphics.Sprite;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyList {
    private static int level = 0;
    public static List<Balloom> ballooms;
    public static List<Oneal> oneals;
    public static Bomber player;
    private static Entities[][] array;

    public static void loadMap() {
        BombermanGame.playerPane.getChildren().clear();
        ballooms = new ArrayList<>();
        oneals = new ArrayList<>();
        array = new Entities[Setting.MAX_HEIGHT][Setting.MAX_WIDTH];
        level++;
        if (level > Setting.maxLevel) {
            BombermanGame.Win();
            return;
        }
        File file = new File("res/levels/Level" + level + ".txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Setting.R = scanner.nextInt();
        Setting.L = scanner.nextInt();
        String s;
        s = scanner.nextLine();
        for (int y = 0; y < Setting.R; ++y) {
            s = scanner.nextLine();
            for (int x = 0; x < Setting.L; ++x) {
                array[x][y] = new Entities();
                array[x][y].add(new Grass(x, y, Sprite.grass.getFxImage()));
                switch (s.charAt(x)) {
                    case '#':
                        array[x][y].add(new Wall(x, y, Sprite.wall.getFxImage()));
                        break;
                    case '*':
                        array[x][y].add(new Brick(x, y, Sprite.brick.getFxImage()));
                        break;
                    case 'x':
                        array[x][y].add(new Portal(x, y, Sprite.portal.getFxImage()));
                        array[x][y].add(new Brick(x, y, Sprite.brick.getFxImage()));
                        break;
                    case 'p':
                        MyList.player = new Bomber(x, y, Sprite.player_right.getFxImage());
                        break;
                    case '1':
                        MyList.ballooms.add(new Balloom(x, y, Sprite.balloom_left1.getFxImage()));
                        break;
                    case '2':
                        MyList.oneals.add(new Oneal(x, y, Sprite.oneal_left1.getFxImage()));
                        break;
                    case 's':
                        array[x][y].add(new SpeedItem(x, y, Sprite.powerup_speed.getFxImage()));
                        array[x][y].add(new Brick(x, y, Sprite.brick.getFxImage()));
                        break;
                    case 'f':
                        array[x][y].add(new FlameItem(x, y, Sprite.powerup_flames.getFxImage()));
                        array[x][y].add(new Brick(x, y, Sprite.brick.getFxImage()));
                        break;
                    case 'b':
                        array[x][y].add(new BombItem(x, y, Sprite.powerup_bombs.getFxImage()));
                        array[x][y].add(new Brick(x, y, Sprite.brick.getFxImage()));
                        break;
                }
            }
        }
        addToPane();
        player.KeyEvent();
    }

    public static void update() {
        for (int x = 0; x <= Setting.L; ++x) {
            for (int y = 0; y <= Setting.R; ++y) {
                if (array[x][y] != null)
                    array[x][y].update();
            }
        }
    }

    public static void remove(Entity entity) {
        if (entity instanceof Character) {

        } else {
            int x = (int) entity.getX();
            int y = (int) entity.getY();
            array[x][y].remove(entity);
        }
    }

    public static void add(Entity entity) {
        int x = (int) entity.getX();
        int y = (int) entity.getY();
        if (array[x][y] == null)
            array[x][y] = new Entities();
        array[x][y].add(entity);
    }

    public static void addToPane() {
        for (int x = 0; x <= Setting.L; ++x) {
            for (int y = 0; y <= Setting.R; ++y) {
                if (array[x][y] != null)
                    array[x][y].addToPane();
            }
        }
        MyList.ballooms.forEach(g -> g.addToPane());
        MyList.oneals.forEach(g -> g.addToPane());
        MyList.player.addToPane();
    }

    public static void render() {
        for (int x = 0; x <= Setting.L; ++x) {
            for (int y = 0; y <= Setting.R; ++y) {
                if (array[x][y] != null)
                    array[x][y].render();
            }
        }
    }

    public static Brick getBrick(int x, int y) {
        return array[x][y].getBrick();
    }

    public static Wall getWall(int x, int y) {
        return array[x][y].getWall();
    }

    public static SpeedItem getSpeedItem(int x, int y) {
        return array[x][y].getSpeedItem();
    }

    public static BombItem getBombItem(int x, int y) {
        return array[x][y].getBombItem();
    }

    public static FlameItem getFlameItem(int x, int y) {
        return array[x][y].getFlameItem();
    }

    public static Portal getPortal(int x, int y) {
        return array[x][y].getPortal();
    }

    public static Bomb getBomb(int x, int y) {
        return array[x][y].getBomb();
    }

    public static Flame getFlame(int x, int y) {
        return array[x][y].getFlame();
    }

    public static Grass getGrass(int x, int y) {
        return array[x][y].getGrass();
    }
}
