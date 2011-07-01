package com.google.code.gwt.threejs.client.extras.physics;

import com.google.code.gwt.threejs.client.core.Geometry;
import com.google.code.gwt.threejs.client.core.Geometry.BoundingBox;
import com.google.code.gwt.threejs.client.core.Vector3;
import com.google.code.gwt.threejs.client.objects.Mesh;

public final class CollisionUtils {

	// @params m THREE.Mesh
	// @returns CBox dynamic Object Bounding Box
	public static BoxCollider meshOBB(Mesh m){
		Geometry g = m.getGeometry();
		g.computeBoundingBox();
		BoundingBox b = g.getBoundingBox();
		Vector3 min = new Vector3(b.x[0], b.y[0], b.z[0]);
		Vector3 max = new Vector3(b.x[1], b.y[1], b.z[1]);
		BoxCollider box = new BoxCollider(min, max);
		box.mesh = m;
		return box;
	}
	
	// @params m THREE.Mesh
	// @returns CBox static Axis-Aligned Bounding Box
	//
	// The AABB is calculated based on current
	// position of the object (assumes it won't move)
	public static BoxCollider meshAABB(Mesh m){
		BoxCollider box = CollisionUtils.meshOBB(m);
		box.min.addSelf(m.getPosition());
		box.max.addSelf(m.getPosition());
		box.dynamic = false;
		return box;
	}
	
	// @params m THREE.Mesh
	// @returns CMesh with aOOB attached (that speeds up intersection tests)
	
	public static MeshCollider meshColliderWBox(Mesh m){
		MeshCollider mc = new MeshCollider(m, CollisionUtils.meshOBB(m));
		return mc;
	}
}
