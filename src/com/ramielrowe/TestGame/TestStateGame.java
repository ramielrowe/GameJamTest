package com.ramielrowe.TestGame;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class TestStateGame extends StateBasedGame{
	
	public static final int LOAD_STATE_ID = 1;
	public static final int PLAY_STATE_ID = 2;
	
	private ResourceManager resourceManager;
	
	public TestStateGame(String name) {
		super(name);
	}
	
	@Override
	public boolean closeRequested(){
		return true;
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		this.resourceManager = new ResourceManager("music.xml", "images.xml", "sounds.xml", "maps.xml", "tilesets");
		
		this.addState(new LoadState(this.resourceManager));
		this.addState(new PlayState(this.resourceManager));
		
	}
	
	public static void main(String[] args){
		try {
			TestStateGame game = new TestStateGame("Test");
			AppGameContainer app = new AppGameContainer(game);
			app.setDisplayMode(800,600,false);
            app.setTargetFrameRate(60);
            app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}

}
