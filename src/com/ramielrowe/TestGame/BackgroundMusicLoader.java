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
	
	public BackgroundMusicLoader(HashMap<String, Resource> resourceMap, HashMap<String, Music> musicMap){
		this.resourceMap = resourceMap;
		this.musicMap = musicMap;
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
				Music m = new Music(r.getLocation());
				musicMap.put(entry.getValue().getKey(), m);
			} catch (SlickException e) {
				Log.error("Error loading "+entry.getValue().getKey()+" at "+r.getLocation(), e);
			}
		}
	}
	
}
