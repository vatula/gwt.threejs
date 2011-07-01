package com.google.code.gwt.threejs.client.materials;

import com.google.code.gwt.threejs.client.materials.enums.Shading;
import com.google.gwt.canvas.dom.client.Context2d.LineCap;
import com.google.gwt.canvas.dom.client.Context2d.LineJoin;

public final class MeshNormalMaterial extends Material {
	
	static class MeshNormalMaterialOptions extends Material.MaterialOptions {
		public Shading shading = Shading.SmoothShading;
		public boolean wireframe = false;
		public int wireframeLinewidth = 1;
		public LineCap wireframeLinecap = LineCap.ROUND;
		public LineJoin wireframeLinejoin = LineJoin.ROUND;
	}
	
	private Shading shading;
	private boolean wireframe;
	private int wireframeLinewidth;
	private LineCap wireframeLinecap;
	private LineJoin wireframeLinejoin;


	public MeshNormalMaterial(MeshNormalMaterialOptions options){
		super(options);
		this.shading = options.shading;
		this.wireframe = options.wireframe;
		this.wireframeLinewidth = options.wireframeLinewidth;
		this.wireframeLinecap = options.wireframeLinecap;
		this.wireframeLinejoin = options.wireframeLinejoin;
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
}
