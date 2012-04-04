package com.ramielrowe.TestGame;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.Log;

public class BackgroundMusicLoader extends Thread implements BackgroundResourceLoader{

	private HashMap<String, Resource> resourceMap;
	private HashMap<String, Music> musicMap;
	private BackgroundSoundLoader bsl;
	
	public BackgroundMusicLoader(HashMap<String, Resource> resourceMap, HashMap<String, Music> musicMap, BackgroundSoundLoader bsl){
		this.resourceMap = resourceMap;
		this.musicMap = musicMap;
		this.bsl = bsl;
	}
	
	public int getSize(){
		return resourceMap.size();
	}
	
	public int getLoaded(){
		return musicMap.size();
	}
	
	@Override
	public void run(){
		Set<Entry<String, Resource>> entries = resourceMap.entrySet();
		for(Entry<String, Resource> entry : entries){
			Resource r = entry.getValue();
			try {
				/*
				 * Load music as streaming...
				 * Not only does it decrease load time significantly,
				 * it also decreases memory footprint significantly...
				 * This is because when loaded into memory, it is 
				 * fully uncompressed which is HUGE...
				 */
				Music m = new Music(r.getLocation(), true);
				musicMap.put(entry.getValue().getKey(), m);
			} catch (SlickException e) {
				Log.error("Error loading "+entry.getValue().getKey()+" at "+r.getLocation(), e);
			}
		}
		bsl.start();
	}
	
}
