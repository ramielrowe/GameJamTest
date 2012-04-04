package com.ramielrowe.TestGame;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.util.Log;

public class BackgroundSoundLoader extends Thread implements BackgroundResourceLoader{

	private HashMap<String, Resource> musicResourceMap;
	private HashMap<String, Music> musicMap;
	private HashMap<String, Resource> soundResourceMap;
	private HashMap<String, Sound> soundMap;
	
	public BackgroundSoundLoader(
			HashMap<String, Resource> musicResourceMap, 
			HashMap<String, Music> musicMap, 
			HashMap<String, Resource> soundResourceMap, 
			HashMap<String, Sound> soundMap){
		this.musicResourceMap = musicResourceMap;
		this.musicMap = musicMap;
		this.soundResourceMap = soundResourceMap;
		this.soundMap = soundMap;
	}
	
	public int getSize(){
		return musicResourceMap.size() + soundResourceMap.size();
	}
	
	public int getLoaded(){
		return musicMap.size() + soundMap.size();
	}
	
	@Override
	public void run(){
		Set<Entry<String, Resource>> entries = musicResourceMap.entrySet();
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
				musicMap.put(r.getKey(), m);
			} catch (SlickException e) {
				Log.error("Error loading "+r.getKey()+" at "+r.getLocation(), e);
			}
		}
		entries = soundResourceMap.entrySet();
		for(Entry<String, Resource> entry : entries){
			Resource r = entry.getValue();
			try {
				Sound s = new Sound(r.getLocation());
				soundMap.put(r.getKey(), s);
			} catch (SlickException e) {
				Log.error("Error loading "+r.getKey()+" at "+r.getLocation(), e);
			}
		}
	}
	
}
