package com.google.code.gwt.threejs.client.scenes;

import java.util.ArrayList;
import com.google.code.gwt.threejs.client.cameras.Camera;
import com.google.code.gwt.threejs.client.core.DimentionObject;
import com.google.code.gwt.threejs.client.core.Object3D;
import com.google.code.gwt.threejs.client.extras.physics.Collider;
import com.google.code.gwt.threejs.client.lights.Light;
import com.google.code.gwt.threejs.client.objects.Bone;
import com.google.code.gwt.threejs.client.objects.Sound;

public final class Scene extends Object3D {
	
	protected ArrayList<DimentionObject> objects;
	protected ArrayList<Light> lights;
	protected ArrayList<Sound> sounds;
	protected Fog fog;
	protected ArrayList<Collider> collisions;
	
	private ArrayList<DimentionObject> objectsAdded;
	private ArrayList<DimentionObject> objectsRemoved;
	
	public Scene(){
		super();
		this.matrixAutoUpdate = false;
		this.fog = null;
		this.collisions = null;
		this.objects = new ArrayList<DimentionObject>();
		this.lights = new ArrayList<Light>();
		this.sounds = new ArrayList<Sound>();
		this.objectsAdded = new ArrayList<DimentionObject>();
		this.objectsRemoved = new ArrayList<DimentionObject>();
	}

	// Properties
	
	public ArrayList<Light> getLights(){
		return this.lights;
	}
	public ArrayList<DimentionObject> getObjects(){
		return this.objects;
	}
	public ArrayList<Sound> getSounds(){
		return this.sounds;
	}
	
	@Override
	public <E extends DimentionObject> void addChild(E child){
		super.addChild(child);
		this.addSceneItem(child);
	};
	
	public <E extends DimentionObject> void addSceneItem(E child){
		if (child.getClass() == Light.class){
			Light light = (Light)child;
			if (this.lights.indexOf(light) == -1) {
				this.lights.add(light);
			}
		}
		else if (child.getClass() == Sound.class){
			Sound sound = (Sound)child;
			if (this.sounds.indexOf(sound) == -1) {
				this.sounds.add(sound);
			}
		}
		else if (!(child.getClass() == Camera.class || child.getClass() == Bone.class)) {
			if (this.objects.indexOf(child) == -1) {
				this.objects.add(child);
				this.objectsAdded.add(child);
			}
		}

		for (DimentionObject item : child.getChildren()) {
			this.addSceneItem(item);
		}
	}
	
	@Override
	public <E extends DimentionObject> void removeChild(E child){
		super.removeChild(child);
		this.removeSceneItem(child);
	};
	
	public <E extends DimentionObject> void removeSceneItem(E child){
		if (child.getClass() == Light.class){
			Light light = (Light)child;
			if (this.lights.indexOf(light) != -1){
				this.lights.remove(light);
			}
		}
		else if (child.getClass() == Sound.class){
			Sound sound = (Sound)child;
			if (this.sounds.indexOf(sound) != -1){
				this.sounds.remove(sound);
			}
		}
		else if (!(child.getClass() == Camera.class || child.getClass() == Bone.class)){
			if (this.objects.indexOf(child) != -1){
				this.objects.remove(child);
				this.objectsRemoved.add(child);
			}
		}
		
		for (DimentionObject item : child.getChildren()) {

			this.removeSceneItem(item);

		}
	}
}
