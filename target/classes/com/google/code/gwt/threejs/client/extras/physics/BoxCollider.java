package com.google.code.gwt.threejs.client.extras.physics;

import com.google.code.gwt.threejs.client.core.Vector3;
import com.google.code.gwt.threejs.client.objects.Mesh;

public class BoxCollider extends AbstractCollider {
	public Vector3 min, max;
	public Boolean dynamic;
	public Vector3 normal;
	public Mesh mesh;
	public BoxCollider(Vector3 min, Vector3 max){
		this.min = min;
		this.max = max;
		this.dynamic = true;
		this.normal = new Vector3();
		this.mesh = null;
	}
}