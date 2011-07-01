package com.google.code.gwt.threejs.client.lights;

import com.google.code.gwt.threejs.client.core.Color;
import com.google.code.gwt.threejs.client.core.Object3D;

public abstract class Light extends Object3D {
	protected Color color;
	public Light(int hex){
		super();
		this.color = new Color(hex);
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Color getColor() {
		return color;
	}
}
