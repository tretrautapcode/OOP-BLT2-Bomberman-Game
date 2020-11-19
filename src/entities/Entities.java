package entities;

import entities.DestroyAble.Bomb;
import entities.DestroyAble.Brick;
import entities.DestroyAble.DestroyAble;
import entities.DestroyAble.Flame;
import entities.Item.BombItem;
import entities.Item.FlameItem;
import entities.Item.SpeedItem;
import entities.StillEntity.Grass;
import entities.StillEntity.Portal;
import entities.StillEntity.Wall;

import java.util.ArrayList;
import java.util.List;

public class Entities {
    private List<Entity>list=new ArrayList<>();
    public void add(Entity entity){
        list.add(entity);
    }
    public void addToPane(){
        for(Entity entity:list){
            entity.addToPane();
        }
    }
    public void update(){
        Entity entity;
        for (int i=0;i< list.size();)
        {
            entity=list.get(i);
            entity.update();
            if(entity instanceof DestroyAble &&((DestroyAble) entity).checkDestroy()){
                list.remove(i);
            }else {
                i++;
            }
        }
    }
    public void render(){
        for(Entity entity:list){
            entity.render();
        }
    }
    public Brick getBrick() {
        for (Entity entity:list){
            if(entity instanceof Brick){
                return (Brick)entity;
            }
        }
        return null;
    }
    public Wall getWall() {
        for (Entity entity:list){
            if(entity instanceof Wall){
                return (Wall) entity;
            }
        }
        return null;
    }
    public SpeedItem getSpeedItem()
    {
        for (Entity entity:list){
            if(entity instanceof SpeedItem){
                return (SpeedItem) entity;
            }
        }
        return null;
    }
    public BombItem getBombItem()
    {
        for (Entity entity:list){
            if(entity instanceof BombItem){
                return (BombItem) entity;
            }
        }
        return null;
    }
    public FlameItem getFlameItem()
    {
        for (Entity entity:list){
            if(entity instanceof FlameItem){
                return (FlameItem) entity;
            }
        }
        return null;
    }
    public Portal getPortal()
    {
        for (Entity entity:list){
            if(entity instanceof Portal){
                return (Portal) entity;
            }
        }
        return null;
    }
    public Bomb getBomb()
    {
        for (Entity entity:list){
            if(entity instanceof Bomb){
                return (Bomb) entity;
            }
        }
        return null;
    }
    public void remove(Entity entity){
        list.remove(entity);
    }
    public Flame getFlame()
    {
        for (Entity entity:list){
            if(entity instanceof Flame){
                return (Flame) entity;
            }
        }
        return null;
    }
    public Grass getGrass()
    {
        for (Entity entity:list){
            if(entity instanceof Grass){
                return (Grass) entity;
            }
        }
        return null;
    }
}
