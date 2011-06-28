package com.google.code.gwt.threejs.client.extras.physics;

import com.google.code.gwt.threejs.client.core.Vector3;
import com.google.code.gwt.threejs.client.objects.Mesh;

public class MeshCollider extends AbstractCollider {
	public Mesh mesh;
	public BoxCollider box;
	public int numFaces;
	public Vector3 normal;
	public MeshCollider(Mesh mesh, BoxCollider box){
		this.mesh = mesh;
		this.box = box;
		this.numFaces = this.mesh.getGeometry().getFaces().size();
		this.normal = new Vector3();
	}
}