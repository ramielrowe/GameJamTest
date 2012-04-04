package com.ramielrowe.TestGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.JAXB;

import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.util.ResourceLoader;

public class ResourceManager {

	private HashMap<String, Resource> musicResourceMap;
	private HashMap<String, Music> musicMap;
	private HashMap<String, Resource> imageResourceMap;
	private HashMap<String, Image> imageMap;
	private List<BackgroundResourceLoader> loaders = new ArrayList<BackgroundResourceLoader>(3);
	
	public ResourceManager(String musicXML, String imageXml){
		musicResourceMap = new HashMap<String, Resource>();
		musicMap = new HashMap<String, Music>();
		imageResourceMap = new HashMap<String, Resource>();
		imageMap = new HashMap<String, Image>();
		Resources musicResources = JAXB.unmarshal(ResourceLoader.getResourceAsStream(musicXML), Resources.class);
		for(Resource r : musicResources.getResource()){
			musicResourceMap.put(r.getKey(), r);
		}
		Resources imageResources = JAXB.unmarshal(ResourceLoader.getResourceAsStream(imageXml), Resources.class);
		for(Resource r : imageResources.getResource()){
			imageResourceMap.put(r.getKey(), r);
		}
	}
	
	public Music getMusic(String key){
		if(!musicMap.containsKey(key))
			throw new RuntimeException("No such music "+key);
		return musicMap.get(key);
	}
	
	public void loadImage(String key, Image i){
		imageMap.put(key, i);
	}
	
	public Image getImage(String key){
		if(!imageMap.containsKey(key))
			throw new RuntimeException("No such image "+key);
		return imageMap.get(key);
	}
	
	public int getProgress(){
		float loaded = 0;
		float size = 0;
		for(BackgroundResourceLoader brl : loaders){
			loaded += brl.getLoaded();
			size += brl.getSize();
		}
		loaded += this.imageMap.size();
		size += this.imageResourceMap.size();
		return (int)((((float)loaded)/size)*100);
	}
	
	public void startLoad(){
		BackgroundMusicLoader bml = new BackgroundMusicLoader(musicResourceMap, musicMap);
		bml.start();
		loaders.add(bml);
	}

	public HashMap<String, Resource> getMusicResourceMap() {
		return musicResourceMap;
	}

	public HashMap<String, Resource> getImageResourceMap() {
		return imageResourceMap;
	}
	
}
