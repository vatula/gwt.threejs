package com.google.code.gwt.threejs.client.materials;

import com.google.code.gwt.threejs.client.materials.enums.Blending;

public class Material {
	
	static class MaterialCounter {
		public int value;
	}
	
	static class MaterialOptions {
		public double opacity = 1;
		public boolean transparent = false;
		public Blending blending = Blending.NormalBlending;
		public boolean depthTest = true;
	}
	
	public static MaterialCounter MaterialCounter;
	
	static {
		MaterialCounter = new MaterialCounter();
		MaterialCounter.value = 0;
	}
	
	private double opacity;
	private boolean transparent;
	private Blending blending;
	private boolean depthTest;
	private int id;
	
	public Material(MaterialOptions parameters){
		this.id = MaterialCounter.value++;
		this.opacity = parameters.opacity;
		this.transparent = parameters.transparent;
		this.blending = parameters.blending;
		this.depthTest = parameters.depthTest;
	}

	// Properties
	
	public double getOpacity(){
		return this.opacity;
	}
	public void setOpacity(double opacity){
		this.opacity = opacity;
	}
	
	public boolean getTransparent(){
		return this.transparent;
	}
	public void setTransparent(boolean transparent){
		this.transparent = transparent;
	}
	
	public Blending getBlending(){
		return this.blending;
	}
	public void setBlending(Blending blending){
		this.blending = blending;
	}
	
	public boolean getDepthTest(){
		return this.depthTest;
	}
	public void setDepthTest(boolean depthTest){
		this.depthTest = depthTest;
	}

	public int getId() {
		return id;
	}
}
