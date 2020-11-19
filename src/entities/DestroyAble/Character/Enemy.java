package entities.DestroyAble.Character;

import control.MyList;
import javafx.scene.image.Image;

public class Enemy extends Character {
    public Enemy(double x, double y, Image img, long nanoSecond) {
        super(x, y, img, nanoSecond);
    }

    @Override
    public void update() {
        int floorX = (int) Math.floor(x);
        int floorY = (int) Math.floor(y);
        int ceilX = (int) Math.ceil(x);
        int ceilY = (int) Math.ceil(y);
        if (MyList.getFlame(floorX, floorY) != null || MyList.getFlame(floorX, ceilY) != null ||
                MyList.getFlame(ceilX, floorY) != null || MyList.getFlame(ceilX, ceilY) != null) {
            setIsDestroy(true);
        }
        if (getIsDestroy()) {
            if (checkDestroy()) {
                this.remove();
            }
        } else {
            move();
            if (intersect(MyList.player)) {
                MyList.player.setIsDestroy(true);
            }
        }
    }

    /**
     * Kiểm tra tọa độ của enemy có xấp xỉ nguyên không
     * @return
     */
    public boolean checkInt(){
        int floorX = (int) Math.floor(x);
        int floorY = (int) Math.floor(y);
        int ceilX = (int) Math.ceil(x);
        int ceilY = (int) Math.ceil(y);
        if(x-floorX>speed&&ceilX-x>speed){
            return false;
        }
        if((y-floorY)>speed&&ceilY-y>speed){
            return false;
        }
        return true;
    }
}
