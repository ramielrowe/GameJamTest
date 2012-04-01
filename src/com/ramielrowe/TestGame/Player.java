package com.ramielrowe.TestGame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;

public class Player{
	public static final Color COLOR = Color.cyan;

	public static final int HEIGHT = 50;
	public static final int WIDTH = 50;

	public static final float DELTA_X = 0.2f;
	public static final float DELTA_Y = 0.2f;

	public static final float BOOST_SCALER = 2.5f;

	private float xPos = 0;
	private float yPos = 0;
	
	public Player(int xPos, int yPos){
		this.xPos = xPos;
		this.yPos = yPos;
	}

	public void update(GameContainer container, int delta){

		float boost = 1f;

		if(container.getInput().isKeyDown(Input.KEY_SPACE))
			boost = Player.BOOST_SCALER;

		if(container.getInput().isKeyDown(Input.KEY_W))
			this.setYPos(getYPos() - Player.DELTA_Y*delta*boost);
		if(container.getInput().isKeyDown(Input.KEY_S))
			this.setYPos(getYPos() + Player.DELTA_Y*delta*boost);

		if(container.getInput().isKeyDown(Input.KEY_A))
			this.setXPos(getXPos() - Player.DELTA_X*delta*boost);
		if(container.getInput().isKeyDown(Input.KEY_D))
			this.setXPos(getXPos() + Player.DELTA_Y*delta*boost);

		if(this.getXPos() > container.getWidth()-Player.WIDTH-1){
			this.setXPos(container.getWidth()-Player.WIDTH-1);
		}
		else if(this.getXPos() < 0){
			this.setXPos(0);
		}

		if(this.getYPos() > container.getHeight()-Player.HEIGHT-1){
			this.setYPos(container.getHeight()-Player.HEIGHT-1);
		}
		else if(this.getYPos() < 0){
			this.setYPos(0);
		}

	}

	public void render(GameContainer container, Graphics g)
			throws SlickException {

		g.setColor(Player.COLOR);
		g.drawRect(this.getXPos(), this.getYPos(), Player.WIDTH, Player.HEIGHT);
		
	}

	public float getXPos() {
		return xPos;
	}

	public void setXPos(float xPos) {
		this.xPos = xPos;
	}

	public float getYPos() {
		return yPos;
	}

	public void setYPos(float yPos) {
		this.yPos = yPos;
	}

}
