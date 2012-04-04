package com.ramielrowe.TestGame;

import java.util.Iterator;
import java.util.Map.Entry;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class LoadState implements GameState {

	private ResourceManager resourceManager;
	Image image;
	private Iterator<Entry<String, Resource>> imageResources;
	
	public LoadState(ResourceManager resourceManager){
		this.resourceManager = resourceManager;
		this.imageResources = this.resourceManager.getImageResourceMap().entrySet().iterator();
	}
	
	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(int oldx, int oldy, int newx, int newy) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(int button, int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseWheelMoved(int change) {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputEnded() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputStarted() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isAcceptingInput() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setInput(Input input) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(int key, char c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(int key, char c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerButtonPressed(int controller, int button) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerButtonReleased(int controller, int button) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerDownPressed(int controller) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerDownReleased(int controller) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerLeftPressed(int controller) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerLeftReleased(int controller) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerRightPressed(int controller) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerRightReleased(int controller) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerUpPressed(int controller) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerUpReleased(int controller) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return TestStateGame.LOAD_STATE_ID;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {

		this.resourceManager.startLoad();

	}

	@Override
	public void leave(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		g.drawString("Load: "+resourceManager.getProgress(), 50, 50);
		if(image != null)
			g.drawImage(image, 300, 300);

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		if(imageResources.hasNext()){
			Entry<String, Resource> entry = imageResources.next();
			this.resourceManager.loadImage(entry.getKey(), new Image(entry.getValue().getLocation()));
		}
		if(this.resourceManager.getProgress() == 100)
			game.enterState(TestStateGame.PLAY_STATE_ID, null, null);

	}

}
