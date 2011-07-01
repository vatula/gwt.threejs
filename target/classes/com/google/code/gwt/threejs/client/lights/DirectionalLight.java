package com.google.code.gwt.threejs.client.lights;

public final class DirectionalLight extends PointLight {
	private Boolean castShadow;
	public DirectionalLight(int hex) {
		super(hex);
		this.castShadow = false;
	}
	public DirectionalLight(int hex, int intensity, int distance, Boolean castShadow){
		super(hex, intensity, distance);
		this.castShadow = castShadow;
	}
	public void setCastShadow(Boolean castShadow) {
		this.castShadow = castShadow;
	}
	public Boolean getCastShadow() {
		return castShadow;
	}

}
