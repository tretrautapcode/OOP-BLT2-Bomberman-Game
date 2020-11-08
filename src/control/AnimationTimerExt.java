package control;

import javafx.animation.AnimationTimer;

public abstract class AnimationTimerExt extends AnimationTimer {
    private long timeSleep=0;
    public AnimationTimerExt(long miniSecond) {
        this.timeSleep=miniSecond*1_000_000;
    }
    long timeStart=System.nanoTime();
    @Override
    public void handle(long now) {
        if(now-timeStart<timeSleep){
            return;
        }
        handle();
    }
    public abstract void handle();
}
