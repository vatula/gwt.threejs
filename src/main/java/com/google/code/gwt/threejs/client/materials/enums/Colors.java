package com.google.code.gwt.threejs.client.materials.enums;

public enum Colors {
	
	NoColors(0),
	FaceColors(1),
	VertexColors(2);
	
	private final int colorType;
	Colors(int colorType){
		this.colorType = colorType;
	}
	public int getColorType() {
		return colorType;
	}
}
