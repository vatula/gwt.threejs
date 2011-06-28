package com.google.code.gwt.threejs.client.renderers.renderables;

import com.google.code.gwt.threejs.client.core.DimentionObject;

public final class RenderableObject implements Renderable {
	private DimentionObject object;
	private Double z;

	public RenderableObject(){
		this.object = null;
		this.z = null;
	}
	public void setObject(DimentionObject object) {
		this.object = object;
	}

	public DimentionObject getObject() {
		return object;
	}

	public void setZ(Double z) {
		this.z = z;
	}

	public Double getZ() {
		return z;
	}
}
