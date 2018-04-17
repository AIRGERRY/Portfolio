package com.mygdx.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldFilter;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class OptionScreen implements Screen{
	private MainClass main;
	private Stage stage = new Stage(new ScreenViewport());
	private SpriteBatch batch = new SpriteBatch();
	private LoadingScreen music;
	private LoadingScreen sound;
	private LoadingScreen texture;
	private LoadingScreen skin;
	
	private boolean musicOnOff = true;
	
	public OptionScreen(MainClass parent) {
		main = parent;
		Gdx.input.setInputProcessor(stage); 
		music = new LoadingScreen(parent);
		sound = new LoadingScreen(parent);
		texture = new LoadingScreen(parent);
		skin = new LoadingScreen(parent);
	}
	
	@Override
	public void show() {
		Table table = new Table();
		table.setFillParent(true);
		
		final Label labelMusic = new Label("Music", skin.getSkin());
		final Label labelVolume = new Label("Volume : 100%", skin.getSkin());
		final Slider volumeSlider = new Slider(0, 4, 1, false, skin.getSkin());
		final TextButton musicButton = new TextButton("Music : On", skin.getSkin(), "default");
		final TextButton backGameButton = new TextButton("Back", skin.getSkin(), "default");
		final TextField deltaField = new TextField("0 to 1", skin.getSkin());
		
		volumeSlider.setAnimateDuration(0.05f);
		volumeSlider.setValue(6);
		deltaField.setTextFieldFilter(new TextFieldFilter.DigitsOnlyFilter());
		deltaField.setMaxLength(3);
		
		musicButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				sound.getSound().play(0.75f);
				if(musicOnOff == true) {
					musicButton.setText("Music : Off");
					music.getMusic().stop();
					musicOnOff = false;
				} else {
					musicButton.setText("Music : On");
					music.getMusic().play();
					music.getMusic().setLooping(true);
					musicOnOff = true;
				}
			}
		});
		//Volume settings logic. Sets label for each volume. (i.e Max volume = Volume: 100%)
		volumeSlider.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				music.getMusic().setVolume(volumeSlider.getValue());
				float sliderValue = music.getMusic().getVolume();
				if(sliderValue == 0) {
					music.getMusic().setVolume(0.0f);
					labelVolume.setText("Volume: 0%");
				} else if (sliderValue == 4) {
					music.getMusic().setVolume(1.0f);
					labelVolume.setText("Volume: 100%");
				} else if (sliderValue == 3) {
					music.getMusic().setVolume(0.75f);
					labelVolume.setText("Volume: 75%");
				} else if (sliderValue == 1) {
					music.getMusic().setVolume(0.25f);
					labelVolume.setText("Volume: 25%");
				} else {
					music.getMusic().setVolume(0.50f);
					labelVolume.setText("Volume: 50%");
				}
			}
		});
		backGameButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				main.setScreen(new MenuScreen(main));
				sound.getSound().play(0.75f);
			}
		});
		table.add(labelMusic).pad(5);
		table.row();
		table.add(musicButton).pad(5);
		table.row();
		table.add(labelVolume).pad(5);
		table.row();
		table.add(volumeSlider).pad(5);
		table.row();
		table.add(backGameButton).pad(5);
		stage.addActor(table);
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
		batch.dispose();
		texture.dispose();
		music.dispose();
		sound.dispose();
	}

}
