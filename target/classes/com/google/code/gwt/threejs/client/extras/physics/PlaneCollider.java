package com.google.code.gwt.threejs.client.extras.physics;

import com.google.code.gwt.threejs.client.core.Vector3;

public class PlaneCollider extends AbstractCollider {
	public Vector3 point;
	public Vector3 normal;
	public PlaneCollider(Vector3 point, Vector3 normal){
		this.point = point;
		this.normal = normal;
	}
}