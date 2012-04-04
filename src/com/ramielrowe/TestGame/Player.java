package com.ramielrowe.TestGame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class Player{
	public static final Color COLOR = Color.cyan;

	public static final int HEIGHT = 50;
	public static final int WIDTH = 50;

	public static final float DELTA_X = 0.2f;
	public static final float DELTA_Y = 0.2f;

	public static final float BOOST_SCALER = 2.5f;

	private ResourceManager rm;
	
	private float xPos = 0;
	private float yPos = 0;
	private Image image;
	private Sound boostSound;
	private boolean boosting = false;
	
	public Player(ResourceManager rm, int xPos, int yPos){
		this.rm = rm;
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public Image getImage(){
		if(image == null){
			image = rm.getImage("player1");
		}
		return image;
	}
	
	public Sound getBoostSound(){
		if(boostSound == null){
			boostSound = rm.getSound("player_boost");
		}
		return boostSound;
	}

	public void update(GameContainer container, int delta){

		float boost = 1f;

		if(container.getInput().isKeyDown(Input.KEY_SPACE)){
			if(!this.boosting){
				System.out.println("test");
				this.getBoostSound().play();
				System.out.println(this.getBoostSound().playing());
			}
			boost = Player.BOOST_SCALER;
			this.boosting = true;
		}else{
			if(this.getBoostSound().playing())
				this.getBoostSound().stop();
			this.boosting = false;
		}

		if(container.getInput().isKeyDown(Input.KEY_W))
			this.setYPos(getYPos() - Player.DELTA_Y*delta*boost);
		if(container.getInput().isKeyDown(Input.KEY_S))
			this.setYPos(getYPos() + Player.DELTA_Y*delta*boost);

		if(container.getInput().isKeyDown(Input.KEY_A))
			this.setXPos(getXPos() - Player.DELTA_X*delta*boost);
		if(container.getInput().isKeyDown(Input.KEY_D))
			this.setXPos(getXPos() + Player.DELTA_Y*delta*boost);

		if(this.getXPos() > container.getWidth()-Player.WIDTH){
			this.setXPos(container.getWidth()-Player.WIDTH);
		}
		else if(this.getXPos() < 0){
			this.setXPos(0);
		}

		if(this.getYPos() > container.getHeight()-Player.HEIGHT){
			this.setYPos(container.getHeight()-Player.HEIGHT);
		}
		else if(this.getYPos() < 0){
			this.setYPos(0);
		}

	}

	public void render(GameContainer container, Graphics g)
			throws SlickException {

		g.setColor(Player.COLOR);
		g.drawImage(this.getImage(), this.getXPos(), this.getYPos());
		//g.drawRect(this.getXPos(), this.getYPos(), Player.WIDTH, Player.HEIGHT);
		
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
