package com.matejcrkvenac.flappybird.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.Files;
import com.matejcrkvenac.flappybird.FlappyBird;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=FlappyBird.width;
		config.height=FlappyBird.height;
		config.title=FlappyBird.title;
		config.addIcon("birdicon.png", Files.FileType.Internal);
		new LwjglApplication(new FlappyBird(), config);
	}
}
