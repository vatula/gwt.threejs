package com.google.code.gwt.threejs.client.renderers.renderables;

import java.util.List;

import com.google.code.gwt.threejs.client.materials.Material;

public final class RenderableLine implements Renderable {
	private Double z;
	private RenderableVertex v1;
	private RenderableVertex v2;
	private List<Material> materials;
	
	public RenderableLine(){
		this.z = null;
		this.v1 = new RenderableVertex();
		this.v2 = new RenderableVertex();
		this.materials = null;
	}

	public void setZ(Double z) {
		this.z = z;
	}

	public Double getZ() {
		return z;
	}

	public void setV1(RenderableVertex v1) {
		this.v1 = v1;
	}

	public RenderableVertex getV1() {
		return v1;
	}

	public void setV2(RenderableVertex v2) {
		this.v2 = v2;
	}

	public RenderableVertex getV2() {
		return v2;
	}

	public void setMaterials(List<Material> materials) {
		this.materials = materials;
	}

	public List<Material> getMaterials() {
		return materials;
	}
}
