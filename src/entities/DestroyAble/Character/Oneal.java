package entities.DestroyAble.Character;

import control.MyList;
import control.Point;
import control.Setting;
import graphics.Sprite;
import javafx.scene.image.Image;

import java.util.LinkedList;
import java.util.Queue;

public class Oneal extends Enemy {
    private int[] Dx = {-1, 0, 1, 0};
    private int[] Dy = {0, -1, 0, 1};
    private int[][] f = new int[Setting.MAX_HEIGHT][Setting.MAX_WIDTH];

    public Oneal(double x, double y, Image img) {
        super(x, y, img, Setting.timeEnemyDestroy);
        dx = 0;
        dy = 0;
        speed = Setting.speedOneal;
    }

    @Override
    public void update() {
        Bfs();
        findPath();
        super.update();
        if (getIsDestroy()) {
            img.setImage(Sprite.oneal_dead.getFxImage());
        }
    }

    public boolean move() {
        if (dx == 1 || dy == 1) {
            Animation(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, 30);

        } else {
            Animation(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, 30);
        }
        return super.move();
    }

    public void init() {
        for (int x = 0; x < Setting.L; ++x) {
            for (int y = 0; y < Setting.R; ++y) {
                f[x][y] = -1;
            }
        }
    }

    public void findPath() {
        int intX = (int) x;
        int intY = (int) y;
        int X;
        int Y;
        if (checkInt()) {
            if (intX + 1 - x <= speed) {
                intX = intX + 1;
            }
            if (intY + 1 - y <= speed) {
                intY = intY + 1;
            }
            if (f[intX][intY] != -1) {
                speed = Setting.speedOneal;
                for (int i = 0; i < 4; ++i) {
                    X = intX + Dx[i];
                    Y = intY + Dy[i];
                    if (checkMove(X, Y) && f[intX][intY] == f[X][Y] + 1) {
                        dx = Dx[i];
                        dy = Dy[i];
                    }
                }
            } else {
                speed = Setting.speedBallom;
                randomMove();
            }
        }
    }

    public void Bfs() {
        init();
        Queue<Point> queue = new LinkedList<>();
        int floorX = (int) Math.floor(MyList.player.getX());
        int floorY = (int) Math.floor(MyList.player.getY());
        int ceilX = (int) Math.ceil(MyList.player.getX());
        int ceilY = (int) Math.ceil(MyList.player.getY());
        if (checkMove(floorX, floorY)) {
            queue.add(new Point(floorX, floorY));
            f[floorX][floorY] = 1;
        }
        if (checkMove(floorX, ceilY)) {
            queue.add(new Point(floorX, ceilY));
            f[floorX][ceilY] = 1;
        }
        if (checkMove(ceilX, floorY)) {
            queue.add(new Point(ceilX, floorY));
            f[ceilX][floorY] = 1;
        }
        if (checkMove(ceilX, ceilY)) {
            queue.add(new Point(ceilX, ceilY));
            f[ceilX][ceilY] = 1;
        }
        Point u;
        Point v;
        while (!queue.isEmpty()) {
            u = queue.poll();
            for (int i = 0; i < 4; ++i) {
                v = new Point(u.getX() + Dx[i], u.getY() + Dy[i]);
                if (!checkMove(v.getX(), v.getY())) {
                    continue;
                }
                if (f[v.getX()][v.getY()] == -1) {
                    f[v.getX()][v.getY()] = f[u.getX()][u.getY()] + 1;
                    queue.add(v);
                }
            }
        }
    }
}
