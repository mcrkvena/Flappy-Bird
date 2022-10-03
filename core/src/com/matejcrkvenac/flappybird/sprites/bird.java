package com.matejcrkvenac.flappybird.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import javax.xml.soap.Text;

public class bird {
    private static final int gravity = -15;
    private static final int movement = 100;
    private Vector3 position;
    private Vector3 velocity;
    private Rectangle birdlimit;
    private animation birdanimation;
    private Texture birdtexture;

    public bird(int x, int y){
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        birdtexture = new Texture("birdanimation.png");
        birdanimation = new animation(new TextureRegion(birdtexture), 3, 0.5f);
        birdlimit = new Rectangle(x,y,birdtexture.getWidth() / 3, birdtexture.getHeight());
    }

    public void update(float dt){
        birdanimation.update(dt);
        if(position.y>0) {
            velocity.add(0, gravity, 0);
        }
        velocity.scl(dt);
        position.add(movement * dt,velocity.y,0);
        if(position.y<0){
            position.y=0;
        }
        velocity.scl(1/dt);
        birdlimit.setPosition(position.x, position.y);
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getBirdy() {
        return birdanimation.getFrame();
    }

    public void jump(){
        velocity.y=300;
    }
// metoda za rotaciju izbacena zbog manjka konzistencije sa collision detectionom
//    public float getRotation() {
//        if (velocity.y >= 10)
//            return 10F;
//        if (velocity.y < -10)
//            return -10F;
//        return velocity.y;
//    }

    public Rectangle getBirdlimit() {
        return birdlimit;
    }

    public void dispose(){
        birdtexture.dispose();
    }
}
