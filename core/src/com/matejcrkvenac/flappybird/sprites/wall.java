package com.matejcrkvenac.flappybird.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import java.util.Random;

public class wall {
    private static final int sizediff = 150;
    private static final int openingdiff = 100;
    private static final int minopening = 120;
    public static final int wallwidth = 52;
    private Texture top, bottom;
    private Vector2 positiontop, positionbottom;
    private Rectangle toplimit, bottomlimit;
    private Random rand;
    private int check;
    private Rectangle score;

    public wall(float x){
        top = new Texture("toptube.png");
        bottom = new Texture("bottomtube.png");
        rand = new Random();
        positiontop = new Vector2(x,rand.nextInt(sizediff) + openingdiff + minopening);
        positionbottom = new Vector2(x, positiontop.y - openingdiff - bottom.getHeight());
        toplimit = new Rectangle(positiontop.x, positionbottom.y, top.getWidth(), top.getHeight());
        bottomlimit = new Rectangle(positionbottom.x, positionbottom.y, bottom.getWidth(), bottom.getHeight());
        score = new Rectangle(positionbottom.x + bottom.getWidth(), positionbottom.y, 1,bottom.getHeight() + top.getHeight() + openingdiff);
        check = 0;
    }

    public Texture getTop() {
        return top;
    }

    public Texture getBottom() {
        return bottom;
    }

    public Vector2 getPositiontop() {
        return positiontop;
    }

    public Vector2 getPositionbottom() {
        return positionbottom;
    }

    public int getCheck(){
        return check;
    }

    public void setCheck(int value){
        check = value;
    }

    public boolean scorecounter(Rectangle player){
        return player.overlaps(score);
    }

    public void reposition(float x){
        positiontop.set(x,rand.nextInt(sizediff) + openingdiff + minopening);
        positionbottom.set(x, positiontop.y - openingdiff - bottom.getHeight());
        check = 0;
        score.setPosition(positionbottom.x + bottom.getWidth(), positionbottom.y);
    }

    public boolean collides(Rectangle player){
        toplimit.setPosition(positiontop.x, positiontop.y);
        bottomlimit.setPosition(positionbottom.x, positionbottom.y);
        return player.overlaps(toplimit) || player.overlaps(bottomlimit);
    }

    public void dispose(){
        top.dispose();
        bottom.dispose();
    }
}
