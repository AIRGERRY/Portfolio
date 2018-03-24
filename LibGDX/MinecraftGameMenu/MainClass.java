package com.mygdx.menu;
import com.badlogic.gdx.Game;

public class MainClass extends Game {

	private LoadingScreen loadingScreen;
	
	@Override
	public void create() {
		loadingScreen = new LoadingScreen(this);
		setScreen(loadingScreen);
	}
	
	public void render() {
		super.render();
	}

	public void dispose() {
		super.dispose();
	}	

}

