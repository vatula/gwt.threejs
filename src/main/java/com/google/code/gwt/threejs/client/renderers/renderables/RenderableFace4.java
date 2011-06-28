package com.google.code.gwt.threejs.client.renderers.renderables;

import com.google.code.gwt.threejs.client.core.Vector3;

public final class RenderableFace4 extends RenderableFace3 implements Renderable {
	private RenderableVertex v4;
	public RenderableFace4(){
		super();
		this.v4 = new RenderableVertex();
		this.vertexNormalsWorld.add(new Vector3());
	}
	public void setV4(RenderableVertex v4) {
		this.v4 = v4;
	}
	public RenderableVertex getV4() {
		return v4;
	}
}
