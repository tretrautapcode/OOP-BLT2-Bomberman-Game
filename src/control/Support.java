package control;

import entities.DestroyAble.Brick;
import entities.Entity;

import java.util.List;

public class Support {
    public static Entity search(int x, int y, List<? extends Entity> list) {
        for (Entity entity : list) {
            if (entity.getX() == x && entity.getY() == y) {
                return entity;
            }
        }
        return null;
    }
    public static Entity get(int x,int y){
        Entity entity=search(x,y,BombermanGame.bricks);
        if(entity!=null)return entity;
        return search(x,y,BombermanGame.walls);
    }
    public static boolean isWall(int x,int y){
        return search(x,y,BombermanGame.walls)!=null;
    }
    public static boolean isBrick(int x,int y){
        return search(x,y,BombermanGame.bricks)!=null;
    }
    public static Brick getBrick(int x,int y)
    {
        return (Brick)search(x,y,BombermanGame.bricks);
    }
}
