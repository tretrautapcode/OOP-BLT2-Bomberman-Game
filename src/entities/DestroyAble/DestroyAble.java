package entities.DestroyAble;

import entities.Entity;
import graphics.Sprite;
import javafx.scene.image.Image;
public abstract class DestroyAble extends Entity {
    protected int animation = 0;//Số lượng hoạt ảnh
    private long timeStart;//thời điểm bắt đầu xảy ra hiệu ứng phá hủy
    private long timeDestroy;//khoảng thời gian timeStart đến lúc bị phá hủy hoàn toàn
    private boolean isDestroy=false;//isDestroy=true bắt đầu phá hủy, =false quá trình chưa bắt đầu
    public DestroyAble(double x, double y, Image img, long nanoSecond) {
        super(x,y, img);
        timeDestroy=nanoSecond;
    }

    public void setTimeDestroy(long timeDestroy) {
        this.timeDestroy = timeDestroy;
    }
    public void setIsDestroy(boolean isDestroy) {
        if(!this.isDestroy&&isDestroy){
            timeStart=System.nanoTime();
        }
        this.isDestroy=isDestroy;
    }
    public boolean getIsDestroy(){
        return isDestroy;
    }
    @Override
    public abstract void update();

    /**
     *
     * @ Kiểm tra xem đối tượng đã phá hủy xong chưa
     */
    public boolean checkDestroy() {
        if (System.nanoTime() - timeStart < timeDestroy) {
            return false;
        }
        return isDestroy;
    }

    public void Animation(Sprite x1, Sprite x2, Sprite x3, int time) {
        Sprite sprite = Sprite.movingSprite(x1, x2, x3, animation, time);
        img.setImage(sprite.getFxImage());
        animation++;
        if (animation > 1000000) {
            animation = 0;
        }
    }
}
