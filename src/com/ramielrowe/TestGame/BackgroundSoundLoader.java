package com.ramielrowe.TestGame;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.util.Log;

public class BackgroundSoundLoader extends Thread implements BackgroundResourceLoader{

	private HashMap<String, Resource> resourceMap;
	private HashMap<String, Sound> soundMap;
	
	public BackgroundSoundLoader(HashMap<String, Resource> resourceMap, HashMap<String, Sound> soundMap){
		this.resourceMap = resourceMap;
		this.soundMap = soundMap;
	}
	
	public int getSize(){
		return resourceMap.size();
	}
	
	public int getLoaded(){
		return soundMap.size();
	}
	
	@Override
	public void run(){
		Set<Entry<String, Resource>> entries = resourceMap.entrySet();
		for(Entry<String, Resource> entry : entries){
			Resource r = entry.getValue();
			try {
				Sound s = new Sound(r.getLocation());
				soundMap.put(entry.getValue().getKey(), s);
			} catch (SlickException e) {
				Log.error("Error loading "+entry.getValue().getKey()+" at "+r.getLocation(), e);
			}
		}
	}
	
}
