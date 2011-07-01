package com.google.code.gwt.threejs.client.extras.physics;

import java.util.Collection;
import java.util.List;

import com.google.code.gwt.threejs.client.core.Face3;
import com.google.code.gwt.threejs.client.core.Face4;
import com.google.code.gwt.threejs.client.core.Geometry;
import com.google.code.gwt.threejs.client.core.Matrix4;
import com.google.code.gwt.threejs.client.core.Ray;
import com.google.code.gwt.threejs.client.core.Vector3;
import com.google.code.gwt.threejs.client.core.Vertex;
import com.google.code.gwt.threejs.client.objects.Mesh;
import com.google.common.collect.*;

public final class CollisionSystem {
	public class RayMesh {
		public Integer faceIndex;
		public double dist;
		public RayMesh(Integer faceIndex, double dist){
			this.faceIndex = faceIndex;
			this.dist = dist;
		}
	}
	
	private Vector3 collisionNormal;
	private Collection<Collider> colliders;
	private List<Collider> hits;
	
	public CollisionSystem(){
		this.colliders = Lists.newArrayList();
		this.hits = Lists.newArrayList();
		this.collisionNormal = new Vector3();
	}
	
	public void merge(CollisionSystem collisionSystem){
		this.colliders.addAll(collisionSystem.colliders);
		this.hits.addAll(collisionSystem.hits);
	}
	
	public List<Collider> rayCastAll(Ray ray){
		ray.getDirection().normalize();
		this.hits.clear();
		double d, ld = 0;
		
		for (Collider collider : this.colliders){
			d = this.rayCast(ray, collider);
			if (d < Double.MAX_VALUE){
				collider.setDistance(d);
				if (d > ld){
					this.hits.add(collider);
				} else {
					this.hits.add(0, collider);
				}
				ld = d;
			}
		}
		return this.hits;
	}
	
	public double rayCast(Ray ray, Collider collider){
		if (collider.getClass() == PlaneCollider.class){
			return this.rayPlane(ray, (PlaneCollider)collider);
		} else if (collider.getClass() == SphereCollider.class){
			return this.raySphere(ray, (SphereCollider)collider);
		} else if (collider.getClass() == BoxCollider.class){
			return this.rayBox(ray, (BoxCollider)collider);
		} else if ((collider.getClass() == MeshCollider.class) && ((MeshCollider)collider).box != null){
			return this.rayBox(ray, ((MeshCollider)collider).box);
		}
		return 0;
	}
	
	public Collider rayCastNearest(Ray ray){
		Collider nearest = null;
		List<Collider> cs = this.rayCastAll(ray);
		if(cs.size() != 0){
			int i = 0;
			while(cs.get(i).getClass() == MeshCollider.class) {
				Collider c = cs.get(i);
				RayMesh distIndex = this.rayMesh(ray, (MeshCollider)c);
	
				if(distIndex.dist < Double.MAX_VALUE) {
	
					c.setDistance(distIndex.dist);
		            c.setFaceIndex(distIndex.faceIndex);
					break;
				}
	
				i++;
	
			}
	
			if (i <= cs.size()){
				nearest = cs.get(i);
			};
		}
		return nearest;
	}

	public RayMesh rayMesh(Ray r, MeshCollider me){
		Ray rt = this.makeRayLocal(r, me.mesh);

		double d = Double.MAX_VALUE;
	    Integer nearestface = null;

	    if (me.numFaces > 0){
    		Geometry g = me.mesh.getGeometry();
    		List<Vertex> vs = g.getVertices();
    		
			for(int i = 0; i < me.numFaces; i++) {
		        Face3 face = me.mesh.getGeometry().getFaces().get(i);
		    	Vector3 p0 = vs.get(face.getA()).getPosition();
		    	Vector3 p1 = vs.get(face.getB()).getPosition();
		    	Vector3 p2 = vs.get(face.getC()).getPosition();
		    	Vector3 p3 = face.getClass() == Face4.class ? vs.get(((Face4)face).getD()).getPosition() : null;
	
		        if (face.getClass() == Face3.class) {
		            double nd = this.rayTriangle(rt, p0, p1, p2, d, this.collisionNormal);
	
		            if(nd < d) {
		                d = nd;
		                nearestface = i;
		                me.normal.copy(this.collisionNormal);
		                me.normal.normalize();
		            }
		        }
		        
		        else if (face.getClass() == Face4.class) {
		            double nd = this.rayTriangle(rt, p0, p1, p3, d, this.collisionNormal);
		            if(nd < d) {
		                d = nd;
		                nearestface = i;
		                me.normal.copy(this.collisionNormal);
		                me.normal.normalize();
		            }
		            
		            nd = this.rayTriangle(rt, p1, p2, p3, d, this.collisionNormal);
		            if(nd < d) {
		                d = nd;
		                nearestface = i;
		                me.normal.copy(this.collisionNormal);
		                me.normal.normalize();
		            }
	
		        }
	
			}
	    }

		return new RayMesh(nearestface, d);
	}
	
	public double rayTriangle(Ray ray, Vector3 p0, Vector3 p1, Vector3 p2, double mind, Vector3 n){
		Vector3 e1 = CollisionSystem.__v1,
		e2 = CollisionSystem.__v2;

		n.set(0, 0, 0);
	
		// do not crash on quads, fail instead
	
		e1.sub(p1, p0);
		e2.sub(p2, p1);
		n.cross(e1, e2);
	
		double dot = n.dot(ray.getDirection());
		if (!(dot < 0)) return Double.MAX_VALUE;
	
		double d = n.dot(p0);
		double t = d - n.dot(ray.getOrigin());
	
		if (!( t <= 0 )) return Double.MAX_VALUE;
		if (!( t >= dot * mind)) return Double.MAX_VALUE;
	
		t = t / dot;
	
		Vector3 p = CollisionSystem.__v3;
	
		p.copy(ray.getDirection());
		p.multiplyScalar(t);
		p.addSelf(ray.getOrigin());
	
		double u0, u1, u2, v0, v1, v2;
	
		if (Math.abs(n.getX()) > Math.abs(n.getY()) ) {
	
			if (Math.abs( n.getX()) > Math.abs(n.getZ())) {
	
				u0 = p.getY()  - p0.getY();
				u1 = p1.getY() - p0.getY();
				u2 = p2.getY() - p0.getY();
	
				v0 = p.getZ()  - p0.getZ();
				v1 = p1.getZ() - p0.getZ();
				v2 = p2.getZ() - p0.getZ();
	
			} else {
	
				u0 = p.getX()  - p0.getX();
				u1 = p1.getX() - p0.getX();
				u2 = p2.getX() - p0.getX();
	
				v0 = p.getY()  - p0.getY();
				v1 = p1.getY() - p0.getY();
				v2 = p2.getY() - p0.getY();
	
			}
	
		} else {
	
			if(Math.abs(n.getY()) > Math.abs( n.getZ())) {
	
				u0 = p.getX()  - p0.getX();
				u1 = p1.getX() - p0.getX();
				u2 = p2.getX() - p0.getX();
	
				v0 = p.getZ()  - p0.getZ();
				v1 = p1.getZ() - p0.getZ();
				v2 = p2.getZ() - p0.getZ();
	
			} else {
	
				u0 = p.getX()  - p0.getX();
				u1 = p1.getX() - p0.getX();
				u2 = p2.getX() - p0.getX();
	
				v0 = p.getY()  - p0.getY();
				v1 = p1.getY() - p0.getY();
				v2 = p2.getY() - p0.getY();
	
			}
	
		}
	
		double temp = u1 * v2 - v1 * u2;
		if(!(temp != 0) ) return Double.MAX_VALUE;
		//console.log("temp: " + temp);
		temp = 1 / temp;
	
		double alpha = ( u0 * v2 - v0 * u2 ) * temp;
		if(!(alpha >= 0) ) return Double.MAX_VALUE;
		//console.log("alpha: " + alpha);
	
		double beta = ( u1 * v0 - v1 * u0 ) * temp;
		if(!(beta >= 0) ) return Double.MAX_VALUE;
		//console.log("beta: " + beta);
	
		double gamma = 1 - alpha - beta;
		if(!(gamma >= 0) ) return Double.MAX_VALUE;
		//console.log("gamma: " + gamma);
	
		return t;
	}
	
	public Ray makeRayLocal(Ray ray, Mesh m){
		Matrix4 mt = CollisionSystem.__m;
		Matrix4.makeInvert(m.getMatrixWorld(), mt);

		Ray rt = CollisionSystem.__r;
		rt.getOrigin().copy(ray.getOrigin());
		rt.getDirection().copy(ray.getDirection());

		mt.multiplyVector3(rt.getOrigin());
		mt.rotateAxis(rt.getDirection());
		rt.getDirection().normalize();
		//m.localRay = rt;

		return rt;
	}
	
	public double rayBox(Ray ray, BoxCollider ab){
		Ray rt;

		if (ab.dynamic && ab.mesh != null && ab.mesh.getMatrixWorld() != null) {

			rt = this.makeRayLocal( ray, ab.mesh );

		} else {
			rt = CollisionSystem.__r;
			rt.getOrigin().copy(ray.getOrigin());
			rt.getDirection().copy(ray.getDirection());
		}

		double xt = 0, yt = 0, zt = 0;
		double xn = 0, yn = 0, zn = 0;
		Boolean ins = true;

		if(rt.getOrigin().getX() < ab.min.getX()) {
			xt = ab.min.getX() - rt.getOrigin().getX();
			//if(xt > ray.direction.x) return return Number.MAX_VALUE;
			xt /= rt.getDirection().getX();
			ins = false;
			xn = -1;
		} else if(rt.getOrigin().getX() > ab.max.getX()) {
			xt = ab.max.getX() - rt.getOrigin().getX();
			//if(xt < ray.direction.x) return return Number.MAX_VALUE;
			xt /= rt.getDirection().getX();
			ins = false;
			xn = 1;
		}

		if(rt.getOrigin().getY() < ab.min.getY()) {
			yt = ab.min.getY() - rt.getOrigin().getY();
			//if(yt > ray.direction.y) return return Number.MAX_VALUE;
			yt /= rt.getDirection().getY();
			ins = false;
			yn = -1;
		} else if(rt.getOrigin().getY() > ab.max.getY()) {
			yt = ab.max.getY() - rt.getOrigin().getY();
			//if(yt < ray.direction.y) return return Number.MAX_VALUE;
			yt /= rt.getDirection().getY();
			ins = false;
			yn = 1;
		}

		if(rt.getOrigin().getZ() < ab.min.getZ()) {
			zt = ab.min.getZ() - rt.getOrigin().getZ();
			//if(zt > ray.direction.z) return return Number.MAX_VALUE;
			zt /= rt.getDirection().getZ();
			ins = false;
			zn = -1;
		} else if(rt.getOrigin().getZ() > ab.max.getZ()) {
			zt = ab.max.getZ() - rt.getOrigin().getZ();
			//if(zt < ray.direction.z) return return Number.MAX_VALUE;
			zt /= rt.getDirection().getZ();
			ins = false;
			zn = 1;
		}

		if (ins) return -1;

		int which = 0;
		double t = xt;

		if(yt > t) {
			which = 1;
			t = yt;
		}

		if (zt > t) {
			which = 2;
			t = zt;
		}

		switch(which) {
			case 0:
				double y0 = rt.getOrigin().getY() + rt.getDirection().getY() * t;
				if (y0 < ab.min.getY() || y0 > ab.max.getY()) return Double.MAX_VALUE;
				double z0 = rt.getOrigin().getZ() + rt.getDirection().getZ() * t;
				if (z0 < ab.min.getZ() || z0 > ab.max.getZ()) return Double.MAX_VALUE;
				ab.normal.set(xn, 0, 0);
				break;
			case 1:
				double x1 = rt.getOrigin().getX() + rt.getDirection().getX() * t;
				if (x1 < ab.min.getX() || x1 > ab.max.getX()) return Double.MAX_VALUE;
				double z1 = rt.getOrigin().getZ() + rt.getDirection().getZ() * t;
				if (z1 < ab.min.getZ() || z1 > ab.max.getZ()) return Double.MAX_VALUE;
				ab.normal.set(0, yn, 0) ;
				break;

			case 2:
				double x2 = rt.getOrigin().getX() + rt.getDirection().getX() * t;
				if (x2 < ab.min.getX() || x2 > ab.max.getX()) return Double.MAX_VALUE;
				double y2 = rt.getOrigin().getY() + rt.getDirection().getY() * t;
				if (y2 < ab.min.getY() || y2 > ab.max.getY()) return Double.MAX_VALUE;
				ab.normal.set(0, 0, zn);
				break;
		}

		return t;
	}
	
	public double rayPlane(Ray r, PlaneCollider p){
		double t = r.getDirection().dot(p.normal);
		double d = p.point.dot(p.normal);
		double result;

		result = (t < 0) ? (d-r.getOrigin().dot(p.normal)) / t : Double.MAX_VALUE;

		return (result > 0) ? result : Double.MAX_VALUE;
	}
	
	public double raySphere(Ray r, SphereCollider s){
		Vector3 e = s.center.clone().subSelf(r.getOrigin());
		if (e.lengthSq() < s.radiusSq) return -1;

		double a = e.dot(r.getDirection().clone());
		if (a <= 0) return Double.MAX_VALUE;

		double t = s.radiusSq - (e.lengthSq() - a*a);
		if (t >= 0) return Math.abs(a) - Math.sqrt(t);

		return Double.MAX_VALUE;
	}
	
	private static final Vector3 __v1 = new Vector3();
	private static final Vector3 __v2 = new Vector3();
	private static final Vector3 __v3 = new Vector3();
	//private static final Vector3 __nr = new Vector3();
	private static final Matrix4 __m = new Matrix4();
	private static final Ray __r = new Ray();
}
