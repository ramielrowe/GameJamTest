package com.ramielrowe.TestGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.JAXB;

import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.Log;
import org.newdawn.slick.util.ResourceLoader;

public class ResourceManager {

	private HashMap<String, Resource> musicResourceMap;
	private HashMap<String, Music> musicMap;
	private HashMap<String, Resource> imageResourceMap;
	private HashMap<String, Image> imageMap;
	private HashMap<String, Resource> soundResourceMap;
	private HashMap<String, Sound> soundMap;
	private HashMap<String, Resource> mapResourceMap;
	private HashMap<String, TiledMap> mapMap;
	private String tileSetLocation;
	private List<BackgroundResourceLoader> loaders = new ArrayList<BackgroundResourceLoader>(3);

	public ResourceManager(String musicXML, String imageXml, String soundXml, String mapXml, String tileSetLocation){
		musicResourceMap = new HashMap<String, Resource>();
		musicMap = new HashMap<String, Music>();
		imageResourceMap = new HashMap<String, Resource>();
		imageMap = new HashMap<String, Image>();
		soundResourceMap = new HashMap<String, Resource>();
		soundMap = new HashMap<String, Sound>();
		mapResourceMap = new HashMap<String, Resource>();
		mapMap = new HashMap<String, TiledMap>();
		this.tileSetLocation = tileSetLocation;
		Resources musicResources = JAXB.unmarshal(ResourceLoader.getResourceAsStream(musicXML), Resources.class);
		if(musicResources.getResource() != null){
			for(Resource r : musicResources.getResource()){
				musicResourceMap.put(r.getKey(), r);
			}
		}		
		Resources imageResources = JAXB.unmarshal(ResourceLoader.getResourceAsStream(imageXml), Resources.class);
		if(imageResources.getResource() != null){
			for(Resource r : imageResources.getResource()){
				imageResourceMap.put(r.getKey(), r);
			}
		}
		Resources soundResources = JAXB.unmarshal(ResourceLoader.getResourceAsStream(soundXml), Resources.class);
		if(soundResources.getResource() != null){
			for(Resource r : soundResources.getResource()){
				soundResourceMap.put(r.getKey(), r);
			}
		}
		Resources mapResources = JAXB.unmarshal(ResourceLoader.getResourceAsStream(mapXml), Resources.class);
		if(mapResources.getResource() != null){
			for(Resource r : mapResources.getResource()){
				mapResourceMap.put(r.getKey(), r);
			}
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

	public Sound getSound(String key){
		if(!soundMap.containsKey(key))
			throw new RuntimeException("No such sound "+key);
		return soundMap.get(key);
	}
	
	public void loadMap(String key){
		Resource r = this.mapResourceMap.get(key);
		try {
			TiledMap tm = new TiledMap(r.getLocation(), this.tileSetLocation);
			mapMap.put(key, tm);
		} catch (SlickException e) {
			Log.error("Error loading map "+key, e);
		}
	}
	
	public TiledMap getMap(String key){
		if(!mapMap.containsKey(key))
			loadMap(key);
		if(!mapMap.containsKey(key))
			throw new RuntimeException("No such map "+key);
		return mapMap.get(key);
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
		BackgroundSoundLoader bml = new BackgroundSoundLoader(musicResourceMap, musicMap, soundResourceMap, soundMap);
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
