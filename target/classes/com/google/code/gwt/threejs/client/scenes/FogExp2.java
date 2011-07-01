package com.google.code.gwt.threejs.client.scenes;

import com.google.code.gwt.threejs.client.core.Color;

public final class FogExp2 {
	private Color color;
	private double density;
	public FogExp2(int hex){
		this(hex, 0.00025);
	}
	public FogExp2(int hex, double density){
		this.color = new Color(hex);
		this.density = density;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Color getColor() {
		return color;
	}
	public void setDensity(double density) {
		this.density = density;
	}
	public double getDensity() {
		return density;
	}
}
