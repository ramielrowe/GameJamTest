package com.ramielrowe.TestGame;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.AppGameContainer;

public class TestGame extends BasicGame{
	
	public static final int EXIT_KEY = Input.KEY_ESCAPE;
	
	Player p1 = new Player();
	
    public TestGame() {
        super("TestGame");
    }
    
    @Override
    public void init(GameContainer container) throws SlickException {
    	
    }

    @Override
    public void update(GameContainer container, int delta)
            throws SlickException {
    	
    	if(container.getInput().isKeyDown(TestGame.EXIT_KEY)){
    		container.exit();
    	}
    	p1.update(container, delta);
    }

    @Override
    public void render(GameContainer container, Graphics g)
            throws SlickException {
    	
        g.setColor(Color.cyan);
        g.drawRect(p1.getXPos(), p1.getYPos(), Player.WIDTH, Player.HEIGHT);
    }

    public static void main(String[] args) {
        try {
        	TestGame game = new TestGame();
            AppGameContainer app = new AppGameContainer(game);
            //app.setDisplayMode(app.getScreenWidth(), app.getScreenHeight(), true);
            app.setDisplayMode(1024, 768, false);
            app.setTargetFrameRate(60);
            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}