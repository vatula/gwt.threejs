package com.google.code.gwt.threejs.client.materials;

import com.google.code.gwt.threejs.client.core.Color;
import com.google.code.gwt.threejs.client.materials.enums.Operation;
import com.google.code.gwt.threejs.client.materials.enums.Shading;
import com.google.gwt.canvas.dom.client.Context2d.LineCap;
import com.google.gwt.canvas.dom.client.Context2d.LineJoin;

public final class MeshPhongMaterial extends Material implements HasMaterialMap, HasMaterialEnvMap {
	
	static class MeshPhongMaterialOptions extends Material.MaterialOptions {
		public Color color = new Color(0xffffff);
		public Color ambient = new Color(0x050505);
		public Color specular = new Color(0x111111);
		public int shininess = 30;
		public Texture map = null;
		public Texture lightMap = null;
		public Texture envMap = null; // TODO TextureCube
		public Operation combine = Operation.MultiplyOperation;
		public double reflectivity = 1;
		public double refractionRatio = 0.98;
		public Shading shading = Shading.SmoothShading;
		public boolean wireframe = false;
		public int wireframeLinewidth = 1;
		public LineCap wireframeLinecap = LineCap.ROUND;
		public LineJoin wireframeLinejoin = LineJoin.ROUND;
		public boolean vertexColors = false;
		public boolean skinning = false;
		public boolean morphTargets = false;
	}
	
	private Color color;
	private Color ambient;
	private Color specular;
	private int shininess;
	private Texture map;
	private Texture lightMap;
	private Operation combine;
	private double reflectivity;
	private double refractionRatio;
	private Shading shading;
	private boolean wireframe;
	private int wireframeLinewidth;
	private LineCap wireframeLinecap;
	private LineJoin wireframeLinejoin;
	private boolean vertexColors;
	private boolean skinning;
	private boolean morphTargets;
	private Texture envMap;

	public MeshPhongMaterial(MeshPhongMaterialOptions options){
		super(options);
		this.color = options.color;
		this.ambient = options.ambient;
		this.specular = options.specular;
		this.shininess = options.shininess;
		this.map = options.map;
		this.lightMap = options.lightMap;
		this.envMap = options.envMap;
		this.combine = options.combine;
		this.reflectivity = options.reflectivity;
		this.refractionRatio = options.refractionRatio;
		this.shading = options.shading;
		this.wireframe = options.wireframe;
		this.wireframeLinewidth = options.wireframeLinewidth;
		this.wireframeLinecap = options.wireframeLinecap;
		this.wireframeLinejoin = options.wireframeLinejoin;
		this.vertexColors = options.vertexColors;
		this.skinning = options.skinning;
		this.morphTargets = options.morphTargets;
	}

	public Color getColor(){
		return this.color;
	}
	
	public Texture getMap(){
		return this.map;
	}
	
	public Texture getLightMap(){
		return this.lightMap;
	}
	
	public Operation getCombine(){
		return this.combine;
	}

	public double getReflectivity(){
		return this.reflectivity;
	}

	public double getRefractionRatio(){
		return this.refractionRatio;
	}

	public Shading getShading(){
		return this.shading;
	}

	public Boolean getWireframe(){
		return this.wireframe;
	}

	public double getWireframeLinewidth(){
		return this.wireframeLinewidth;
	}

	public Boolean getVertexColors(){
		return this.vertexColors;
	}

	public Boolean getSkinning(){
		return this.skinning;
	}

	public boolean isMorphTargets() {
		return morphTargets;
	}

	public Texture getEnvMap() {
		return envMap;
	}

	public LineCap getWireframeLinecap() {
		return wireframeLinecap;
	}

	public LineJoin getWireframeLinejoin() {
		return wireframeLinejoin;
	}

	public Color getAmbient() {
		return ambient;
	}

	public Color getSpecular() {
		return specular;
	}

	public int getShininess() {
		return shininess;
	}

}
