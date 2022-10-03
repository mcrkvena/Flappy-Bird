package com.matejcrkvenac.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.matejcrkvenac.flappybird.states.GameStateManager;
import com.matejcrkvenac.flappybird.states.menustate;

public class FlappyBird extends ApplicationAdapter {
	public static final int width = 480;
	public static final int height = 800;
	public static final String title = "Flappy Bird";
	private GameStateManager gsm;
	private SpriteBatch batch;
	public static int counter;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		counter = 0;
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new menustate(gsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
}
