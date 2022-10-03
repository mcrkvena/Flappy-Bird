package com.matejcrkvenac.flappybird.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.matejcrkvenac.flappybird.FlappyBird;

public class gameoverstate extends state{
    private Texture background;
    private Texture gameover;
    private Texture play;

    public gameoverstate(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, FlappyBird.width/2, FlappyBird.height/2);
        background = new Texture("bg.png");
        gameover = new Texture("gameover.png");
        play = new Texture("playbtn.png");
        font.getData().setScale(2);
        font.setUseIntegerPositions(false);
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
        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background, camera.position.x - camera.viewportWidth / 2, 0);
        sb.draw(gameover, camera.position.x - gameover.getWidth()/2, camera.position.y);
        sb.draw(play, camera.position.x - play.getWidth()/2, camera.position.y - play.getHeight() - 10);
        font.draw(sb, "Score: " + FlappyBird.counter, camera.position.x - camera.viewportWidth / 2, camera.viewportHeight - 50);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        gameover.dispose();
    }
}
