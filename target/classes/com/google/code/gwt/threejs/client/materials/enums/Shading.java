package com.google.code.gwt.threejs.client.materials.enums;

public enum Shading {
	NoShading(0),
	FlatShading(1),
	SmoothShading(2);
	
	private final int shadingType;
	Shading(int shadingType){
		this.shadingType = shadingType;
	}
	public int getShadingType() {
		return shadingType;
	}
}
