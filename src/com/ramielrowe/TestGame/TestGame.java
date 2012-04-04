package com.ramielrowe.TestGame;

import java.io.IOException;
import java.util.Properties;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.util.ResourceLoader;

public class TestGame extends BasicGame{
	
	public static final String MUSIC_VOLUME_CONFIG_KEY = "music.volume";

	public static final String WINDOW_FULLSCREEN_CONFIG_KEY = "window.fullscreen";

	public static final String WINDOW_HEIGHT_CONFIG_KEY = "window.height";

	public static final String WINDOW_WIDTH_CONFIG_KEY = "window.width";

	public static final int EXIT_KEY = Input.KEY_ESCAPE;
	
	public static final Color DEFAULT_COLOR = Color.white;
	
	private Properties config;
	
	private Player p1;
	private Music m;
	
    public TestGame(Properties config) {
        super("TestGame");
        this.config = config;
    }
    
    @Override
    public void init(GameContainer container) throws SlickException {
    	container.getGraphics().drawString("Loading...", 200, 200);
    	container.setMusicVolume(Float.parseFloat(config.getProperty(MUSIC_VOLUME_CONFIG_KEY)));
    	//p1 = new Player((container.getWidth()-Player.WIDTH)/2, (container.getHeight()-Player.HEIGHT)/2);
    	m = new Music("music/Toccata_et_Fugue_BWV565.ogg", true);
    	m.play();
    }

    @Override
    public void update(GameContainer container, int delta)
            throws SlickException {
    	
    	if(container.getInput().isKeyDown(TestGame.EXIT_KEY)){
    		container.exit();
    	}
    	
    	if(container.getInput().isKeyDown(Input.KEY_UP) && container.getMusicVolume() < 1.0f){
    		container.setMusicVolume(container.getMusicVolume()+.015f);
    	}
    	
    	if(container.getInput().isKeyDown(Input.KEY_DOWN) && container.getMusicVolume() > 0.0f){
    		container.setMusicVolume(container.getMusicVolume()-.015f);
    	}
    	
    	p1.update(container, delta);
    }

    @Override
    public void render(GameContainer container, Graphics g)
            throws SlickException {
    	
        p1.render(container, g);
        g.drawString(String.format("Volume: %.0f", container.getMusicVolume() * 100), 20, 20);
        g.setColor(TestGame.DEFAULT_COLOR);
    }

    public static void main(String[] args) {
        try {
        	Properties config = new Properties();
        	config.load(ResourceLoader.getResourceAsStream("game.properties"));
        	TestGame game = new TestGame(config);
            AppGameContainer app = new AppGameContainer(game);
            //app.setDisplayMode(app.getScreenWidth(), app.getScreenHeight(), true);
            app.setDisplayMode(Integer.parseInt((String)config.get(WINDOW_WIDTH_CONFIG_KEY)), Integer.parseInt((String)config.get(WINDOW_HEIGHT_CONFIG_KEY)), Boolean.parseBoolean((String)config.get(WINDOW_FULLSCREEN_CONFIG_KEY)));
            app.setTargetFrameRate(60);
            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
		} catch (RuntimeException e){
			e.printStackTrace();
		}
    }
}