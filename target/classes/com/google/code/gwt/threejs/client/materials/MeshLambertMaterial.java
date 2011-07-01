package com.google.code.gwt.threejs.client.materials;

import com.google.code.gwt.threejs.client.core.Color;
import com.google.code.gwt.threejs.client.materials.enums.Operation;
import com.google.code.gwt.threejs.client.materials.enums.Shading;
import com.google.gwt.canvas.dom.client.Context2d.LineCap;
import com.google.gwt.canvas.dom.client.Context2d.LineJoin;

public final class MeshLambertMaterial extends Material implements HasMaterialMap, HasMaterialEnvMap {

	static class MeshLambertMaterialOptions extends Material.MaterialOptions {
		public Color color = new Color(0xffffff);
		public Texture map = null;
		public Texture lightMap = null;
		public Texture envMap = null;
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
	private Texture map;
	private Texture lightMap;
	private Texture envMap;
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

	
	public MeshLambertMaterial(MeshLambertMaterialOptions parameters) {
		super(parameters);
		this.color = parameters.color;
		this.map = parameters.map;
		this.lightMap = parameters.lightMap;
		this.envMap = parameters.envMap;
		this.combine = parameters.combine;
		this.reflectivity = parameters.reflectivity;
		this.refractionRatio = parameters.refractionRatio;
		this.shading = parameters.shading;
		this.wireframe = parameters.wireframe;
		this.wireframeLinewidth = parameters.wireframeLinewidth;
		this.wireframeLinecap = parameters.wireframeLinecap;
		this.wireframeLinejoin = parameters.wireframeLinejoin;
		this.vertexColors = parameters.vertexColors;
		this.skinning = parameters.skinning;
		this.morphTargets = parameters.morphTargets;
	}

	public void setColor(Color color) {
		this.color = color;
	}


	public Color getColor() {
		return color;
	}


	public void setMap(Texture map) {
		this.map = map;
	}


	public Texture getMap() {
		return map;
	}


	public void setLightMap(Texture lightMap) {
		this.lightMap = lightMap;
	}


	public Texture getLightMap() {
		return lightMap;
	}


	public void setEnvMap(Texture envMap) {
		this.envMap = envMap;
	}


	public Texture getEnvMap() {
		return envMap;
	}


	public void setCombine(Operation combine) {
		this.combine = combine;
	}


	public Operation getCombine() {
		return combine;
	}


	public void setReflectivity(double reflectivity) {
		this.reflectivity = reflectivity;
	}


	public double getReflectivity() {
		return reflectivity;
	}


	public void setRefractionRatio(double refractionRatio) {
		this.refractionRatio = refractionRatio;
	}


	public double getRefractionRatio() {
		return refractionRatio;
	}


	public void setShading(Shading shading) {
		this.shading = shading;
	}


	public Shading getShading() {
		return shading;
	}


	public void setWireframe(boolean wireframe) {
		this.wireframe = wireframe;
	}


	public boolean isWireframe() {
		return wireframe;
	}


	public void setWireframeLinewidth(int wireframeLinewidth) {
		this.wireframeLinewidth = wireframeLinewidth;
	}


	public int getWireframeLinewidth() {
		return wireframeLinewidth;
	}


	public void setWireframeLinecap(LineCap wireframeLinecap) {
		this.wireframeLinecap = wireframeLinecap;
	}


	public LineCap getWireframeLinecap() {
		return wireframeLinecap;
	}


	public void setWireframeLinejoin(LineJoin wireframeLinejoin) {
		this.wireframeLinejoin = wireframeLinejoin;
	}


	public LineJoin getWireframeLinejoin() {
		return wireframeLinejoin;
	}


	public void setVertexColors(boolean vertexColors) {
		this.vertexColors = vertexColors;
	}


	public boolean isVertexColors() {
		return vertexColors;
	}


	public void setSkinning(boolean skinning) {
		this.skinning = skinning;
	}


	public boolean isSkinning() {
		return skinning;
	}


	public void setMorphTargets(boolean morphTargets) {
		this.morphTargets = morphTargets;
	}


	public boolean isMorphTargets() {
		return morphTargets;
	}
}
