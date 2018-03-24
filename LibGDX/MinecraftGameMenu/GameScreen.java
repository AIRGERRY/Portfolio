package com.mygdx.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameScreen implements Screen {
	private MainClass main;
	private Stage stage = new Stage(new ScreenViewport());
	private SpriteBatch batch = new SpriteBatch();
	private LoadingScreen sound;
	private LoadingScreen texture;
	private LoadingScreen skin;
	private LoadingScreen image;
	private LoadingScreen music;	

	private boolean muted = false;
	
	public GameScreen(MainClass parent) {
		main = parent;
		Gdx.input.setInputProcessor(stage); 
		sound = new LoadingScreen(parent);
		texture = new LoadingScreen(parent);
		skin = new LoadingScreen(parent);
		image = new LoadingScreen(parent);
		music = new LoadingScreen(parent);
	}
	@Override
	public void show() {
		Table table = new Table();
		table.setFillParent(true);

		TextButton newGameButton = new TextButton("Play Game", skin.getSkin(), "default"); 
		TextButton loadGameButton = new TextButton("Load Game", skin.getSkin(), "default");
		TextButton backGameButton = new TextButton("Back to Main Menu", skin.getSkin(), "default");
		
		Image soundImage = new Image(image.getImage());
		soundImage.setX(5);
		soundImage.setY(535);
		
		soundImage.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(music.getMusic().getVolume() == 1.0f && muted == false) {
					music.getMusic().setVolume(0.0f);
					sound.getSound().play(0.75f);
					muted = true;
				} else {
					music.getMusic().setVolume(1.0f);
					sound.getSound().play(0.75f);
					muted = false;
				}
			}
		});
		newGameButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				main.setScreen(new PlayerScreen(main));
				sound.getSound().play(0.75f);
			}
		});
		loadGameButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				main.setScreen(new PlayerScreen(main));
				sound.getSound().play(0.75f);
			}
		});
		backGameButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				main.setScreen(new MenuScreen(main));
				sound.getSound().play(0.75f);
			}
		});
		table.add(newGameButton).pad(10);
		table.row();
		table.add(loadGameButton).pad(10);
		table.row();
		table.add(backGameButton).pad(10);
		stage.addActor(table);
		stage.addActor(soundImage);
	}
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
		batch.begin();
		batch.draw(texture.getTexture(), 0, 0);
		batch.end();
		stage.draw();
	}
	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}
	@Override
	public void pause() {}

	@Override
	public void resume() {}
	
	@Override
	public void hide() {}
	
	@Override
	public void dispose() {
		stage.dispose();
		skin.dispose();
		batch.dispose();
		texture.dispose();
		sound.dispose();
		music.dispose();
		image.dispose();
	}
}
