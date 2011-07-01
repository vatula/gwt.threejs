package com.google.code.gwt.threejs.client.materials.enums;

public enum Wrapping {
	RepeatWrapping(0),
	ClampToEdgeWrapping(1),
	MirroredRepeatWrapping(2);
	
	private final int wrapping;
	Wrapping(int wrapping){
		this.wrapping = wrapping;
	}
	public int getWrappingType() {
		return wrapping;
	}
}
