package com.google.code.gwt.threejs.client.renderers.renderables;

import com.google.code.gwt.threejs.client.core.Vector3;
import com.google.code.gwt.threejs.client.core.Vector4;

public final class RenderableVertex implements Renderable {
	private Vector3 positionWorld;
	private Vector4 positionScreen;
	private Boolean visible;
	private Double z;
	
	public RenderableVertex(){
		this.positionWorld = new Vector3();
		this.positionScreen = new Vector4();
		this.visible = true;
		this.z = null;
	}
	
	// Properties
	
	public void setPositionWorld(Vector3 positionWorld) {
		this.positionWorld = positionWorld;
	}
	public Vector3 getPositionWorld() {
		return positionWorld;
	}
	public void setPositionScreen(Vector4 positionScreen) {
		this.positionScreen = positionScreen;
	}
	public Vector4 getPositionScreen() {
		return positionScreen;
	}
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
	public Boolean getVisible() {
		return visible;
	}
	
	// Methods
	
	public RenderableVertex copy(RenderableVertex vertex){
		this.positionWorld.copy(vertex.positionWorld);
		this.positionScreen.copy(vertex.positionScreen);
		return this;
	}

	@Override
	public Double getZ() {
		return z;
	}

	@Override
	public void setZ(Double z) {
		this.z = z;
	}
}
