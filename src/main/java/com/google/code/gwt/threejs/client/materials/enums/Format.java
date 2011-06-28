package com.google.code.gwt.threejs.client.materials.enums;

public enum Format {
	AlphaFormat(0),
	RGBFormat(1),
	RGBAFormat(2),
	LuminanceFormat(3),
	LuminanceAlphaFormat(4);
	
	private final int format;
	Format(int format){
		this.format = format;
	}
	public int getFormatType() {
		return format;
	}
}
