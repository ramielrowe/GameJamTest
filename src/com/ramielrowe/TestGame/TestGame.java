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
	
	public static final Color DEFAULT_COLOR = Color.white;
	
	Player p1;
	
    public TestGame() {
        super("TestGame");
    }
    
    @Override
    public void init(GameContainer container) throws SlickException {
    	p1 = new Player((container.getWidth()-Player.WIDTH)/2, (container.getHeight()-Player.HEIGHT)/2);
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
    	
        p1.render(container, g);
        g.setColor(TestGame.DEFAULT_COLOR);
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