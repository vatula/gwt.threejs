package com.google.code.gwt.threejs.client.materials;

import com.google.code.gwt.threejs.client.materials.enums.Shading;

public final class MeshDepthMaterial extends Material {
	
	static class MeshDepthMaterialOptions extends Material.MaterialOptions {
		public Shading shading = Shading.SmoothShading;
		public boolean wireframe = false;
		public int wireframeLinewidth = 1;
	}
	
	private Shading shading;
	private boolean wireframe;
	private int wireframeLinewidth;

	public MeshDepthMaterial(MeshDepthMaterialOptions options){
		super(options);
		this.shading = options.shading;
		this.wireframe = options.wireframe;
		this.wireframeLinewidth = options.wireframeLinewidth;
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
}
