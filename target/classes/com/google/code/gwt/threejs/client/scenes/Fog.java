package com.google.code.gwt.threejs.client.scenes;

import com.google.code.gwt.threejs.client.core.Color;

public final class Fog {
	private Color color;
	private double near;
	private double far;
	
	public Fog(int hex){
		this(hex, 1, 1000);
	}
	public Fog(int hex, double near, double far){
		this.color = new Color(hex);
		this.near = near;
		this.far = far;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public void setNear(double near) {
		this.near = near;
	}

	public double getNear() {
		return near;
	}

	public void setFar(double far) {
		this.far = far;
	}

	public double getFar() {
		return far;
	}
}
