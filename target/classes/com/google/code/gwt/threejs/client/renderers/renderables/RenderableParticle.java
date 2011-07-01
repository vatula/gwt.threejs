package com.google.code.gwt.threejs.client.renderers.renderables;

import java.util.List;

import com.google.code.gwt.threejs.client.core.Vector2;
import com.google.code.gwt.threejs.client.materials.Material;

public final class RenderableParticle implements Renderable {
	private Double x;
	private Double y;
	private Double z;
	private Double rotation;
	private Vector2 scale;
	private List<Material> materials;
	
	public RenderableParticle(){
		this.x = null;
		this.y = null;
		this.z = null;
		this.rotation = null;
		this.scale = new Vector2();
		this.materials = null;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getX() {
		return x;
	}

	public void setY(Double y) {
		this.y = y;
	}

	public Double getY() {
		return y;
	}

	public void setZ(Double z) {
		this.z = z;
	}

	public Double getZ() {
		return z;
	}

	public void setRotation(Double rotation) {
		this.rotation = rotation;
	}

	public Double getRotation() {
		return rotation;
	}

	public void setScale(Vector2 scale) {
		this.scale = scale;
	}

	public Vector2 getScale() {
		return scale;
	}

	public void setMaterials(List<Material> materials) {
		this.materials = materials;
	}

	public List<Material> getMaterials() {
		return materials;
	}
}
