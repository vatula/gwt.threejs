package com.google.code.gwt.threejs.client.core;

public final class Vertex {
	protected Vector3 position;
	public Vertex(){
		this(new Vector3());
	}
	public Vertex(Vector3 position){
		this.position = position;
	}

	public Vector3 getPosition(){
		return this.position;
	}
}
