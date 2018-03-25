package com.mygdx.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class LoadingScreen implements Screen {
	private MainClass main;
	private static Music music = Gdx.audio.newMusic(Gdx.files.internal("assets/music.mp3"));
	private static Sound sound = Gdx.audio.newSound(Gdx.files.internal("assets/clickSound.mp3"));
	private static Texture backgroundTexture = new Texture(Gdx.files.internal("assets/BackgroundImage.jpg"));
	private static Texture soundImage = new Texture(Gdx.files.internal("assets/soundButton.png"));
	private static Texture titleImage = new Texture(Gdx.files.internal("assets/title.png"));
	private static Skin skin = new Skin(Gdx.files.internal("assets/craftacular-ui.json"));
	private static Skin checkBox = new Skin(Gdx.files.internal("extra/glassy-ui.json"));
	
	public LoadingScreen(MainClass parent) {
		main = parent;
		music.stop();
		music.setLooping(true);
	}
	
	public Skin getCheckBoxSkin() {
		return checkBox;
	}
	
	public Texture getTitle() {
		return titleImage;
	}
	
	public Texture getImage() {
		return soundImage;
	}
	
	public Skin getSkin() {
		return skin;
	}
	
	public Texture getTexture() {
		return backgroundTexture;
	}
	
	public Music getMusic() {
		return music;
	}
	
	public Sound getSound() {
		return sound;
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(float delta) {
		main.setScreen(new MenuScreen(main));
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
	}

	@Override
	public void dispose() {
		music.dispose();
		sound.dispose();
		backgroundTexture.dispose();
		skin.dispose();
		soundImage.dispose();
		titleImage.dispose();
		checkBox.dispose();
	}

}
