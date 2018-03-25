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

public class MenuScreen implements Screen {
	private MainClass main;
	private Stage stage = new Stage(new ScreenViewport());
	private SpriteBatch batch = new SpriteBatch();
	private LoadingScreen sound;
	private LoadingScreen texture;
	private LoadingScreen skin;
	private LoadingScreen image;
	private LoadingScreen title;
	private LoadingScreen music;
	
	private boolean muted = false;
	
	public MenuScreen(MainClass parent) {
		main = parent;
		Gdx.input.setInputProcessor(stage); 
		sound = new LoadingScreen(parent);
		texture = new LoadingScreen(parent);
		skin = new LoadingScreen(parent);
		image = new LoadingScreen(parent);
		title = new LoadingScreen(parent);
		music = new LoadingScreen(parent);
	}
	@Override
	public void show() {
		Table table = new Table();
		table.setFillParent(true);
	
		final TextButton startGameButton = new TextButton("Start", skin.getSkin(), "default"); 
		final TextButton optionsGameButton = new TextButton("Options", skin.getSkin(), "default"); 
		final TextButton quitGameButton = new TextButton("Quit", skin.getSkin(), "default");
		
		Image soundImage = new Image(image.getImage());
		Image titleImage = new Image(title.getTitle());
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
		
		startGameButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				sound.getSound().play(0.75f);
				main.setScreen(new GameScreen(main));
			}
		});
		
		optionsGameButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				sound.getSound().play(0.75f);
				main.setScreen(new OptionScreen(main));
			}
			
		});
		
		quitGameButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				sound.getSound().play(0.75f);
				Gdx.app.exit();
			}
		});
		
		table.add(titleImage).pad(15);
		table.row();
		table.add(startGameButton).pad(10);
		table.row();
		table.add(optionsGameButton).pad(10);
		table.row();
		table.add(quitGameButton).pad(10);
		table.row();
		stage.addActor(table);
		stage.addActor(soundImage);
	}
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 30 / 30f));
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
		texture.dispose();
		batch.dispose();
		sound.dispose();
		title.dispose();
		music.dispose();
		image.dispose();
	}
	
}
