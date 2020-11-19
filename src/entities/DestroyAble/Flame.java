package entities.DestroyAble;

import control.Setting;
import control.MyList;
import entities.DestroyAble.Character.Balloom;
import entities.DestroyAble.Character.Oneal;
import graphics.Sprite;
import javafx.scene.image.Image;

public class Flame extends DestroyAble {
    private final int type;

    public Flame(double x, double y, Image img,int type) {
        super(x, y, img, Setting.timeFlameDestroy);
        this.type=type;
        setIsDestroy(true);
    }
    @Override
    public void update() {
        chooseAnimation();
        if(checkDestroy()){
            this.remove();
        }
        Bomb bomb=MyList.getBomb((int)x,(int)y);
        if(bomb!=null){
            bomb.setIsDestroy(true);
            bomb.setTimeDestroy(-1);
        }
        if(intersect(MyList.player)){
            MyList.player.setIsDestroy(true);
        }
    }
    public void chooseAnimation()
    {
        switch (type){
            case 0:
                Animation(Sprite.bomb_exploded,Sprite.bomb_exploded1,Sprite.bomb_exploded2,20);
                break;
            case 1:
                Animation(Sprite.explosion_horizontal_left_last,Sprite.explosion_horizontal_left_last1,Sprite.explosion_horizontal_left_last2,20);
                break;
            case 2:
                Animation(Sprite.explosion_vertical_top_last,Sprite.explosion_vertical_top_last1,Sprite.explosion_vertical_top_last2,20);
                break;
            case 3:
                Animation(Sprite.explosion_horizontal_right_last, Sprite.explosion_horizontal_right_last1,Sprite.explosion_horizontal_right_last2,20);
                break;
            case 4:
                Animation(Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1, Sprite.explosion_vertical_down_last2, 20);
                break;
            case 5:
                Animation(Sprite.explosion_vertical, Sprite.explosion_vertical1, Sprite.explosion_vertical2, 20);
                break;
            default:
                Animation(Sprite.explosion_horizontal, Sprite.explosion_horizontal1, Sprite.explosion_horizontal2, 20);
        }
    }
}
