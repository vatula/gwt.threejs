package com.google.code.gwt.threejs.client.extras.physics;

import com.google.code.gwt.threejs.client.core.Vector3;

public class SphereCollider extends AbstractCollider {
	public Vector3 center;
	public double radius;
	public double radiusSq;
	
	public SphereCollider(Vector3 center, double radius){
		this.center = center;
		this.radius = radius;
		this.radiusSq = radius*radius;
	}
}