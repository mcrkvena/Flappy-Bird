package com.matejcrkvenac.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.matejcrkvenac.flappybird.FlappyBird;

public class menustate extends state{
    private Texture background;
    private Texture play;

    public menustate(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, FlappyBird.width/2, FlappyBird.height/2);
        background = new Texture("bg.png");
        play = new Texture("playbtn.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new playstate(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, FlappyBird.width, FlappyBird.height);
        sb.draw(play, FlappyBird.width/2 - play.getWidth()/2, FlappyBird.height/2);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        play.dispose();
    }
}
