package com.google.code.gwt.threejs.client.materials.enums;

public enum Blending {
	
	NormalBlending(0),
	AdditiveBlending(1),
	SubtractiveBlending(2),
	MultiplyBlending(3),
	AdditiveAlphaBlending(4),
	BillboardBlending(5);
	
	private final int blending;
	Blending(int blending){
		this.blending = blending;
	}
	public int getBlending() {
		return blending;
	}
}
