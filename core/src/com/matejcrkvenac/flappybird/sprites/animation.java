package com.matejcrkvenac.flappybird.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class animation {
    private Array<TextureRegion> frames;
    private float maxframetime;
    private float currentframetime;
    private int framecount;
    private int frame;

    public animation(TextureRegion region, int framecount, float cycletime){
        frames = new Array<TextureRegion>();
        int framewidth = region.getRegionWidth() / framecount;
        for (int i=0; i<framecount; i++){
            frames.add(new TextureRegion(region, i * framewidth, 0, framewidth, region.getRegionHeight()));
        }
        this.framecount = framecount;
        maxframetime = cycletime / framecount;
        frame = 0;
    }

    public void update(float dt){
        currentframetime += dt;
        if(currentframetime > maxframetime){
            frame++;
            currentframetime = 0;
        }
        if(frame >= framecount){
            frame = 0;
        }
    }

    public TextureRegion getFrame(){
        return frames.get(frame);
    }
}
