package com.google.code.gwt.threejs.client.lights;

public class PointLight extends Light {
	private int intensity;
	private int distance;
	
	public PointLight(int hex){
		this(hex, 1, 0);
	}
	
	public PointLight(int hex, int intensity, int distance){
		super(hex);
		this.intensity = intensity;
		this.distance = distance;
	}
	
	public int getIntensity(){
		return this.intensity;
	}
	public void setIntensity(int intensity){
		this.intensity = intensity;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getDistance() {
		return distance;
	}
}
