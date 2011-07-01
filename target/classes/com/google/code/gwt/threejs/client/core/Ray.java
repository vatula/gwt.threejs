package com.google.code.gwt.threejs.client.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.code.gwt.threejs.client.objects.Mesh;
import com.google.code.gwt.threejs.client.scenes.Scene;
import com.google.common.collect.Lists;

public class Ray {
	
	public class Intersect {
		public double distance;
		public Vector3 point;
		public Face3 face;
		public GeometryObject object;
	}
	
	protected Vector3 origin;
	protected Vector3 direction;
	public Ray(){
		this(new Vector3(),new Vector3());
	}
	public Ray(Vector3 origin, Vector3 direction){
		this.origin = origin;
		this.direction = direction;
	}
	
	public List<Intersect> intersectScene(Scene scene){
		return this.intersectObjects(scene.getObjects());
	}
	
	public List<Intersect> intersectObjects(Collection<DimentionObject> objects){
		List<Intersect> intersections = Lists.newArrayList();
		for(DimentionObject object : objects){
			if (object.getClass() == Mesh.class){
				intersections.addAll(this.intersectObject((Mesh)object));
			}
		}
		Collections.sort(intersections, new Comparator<Intersect>(){

			@Override
			public int compare(Intersect a, Intersect b) {
				return (int) (a.distance - b.distance);
			}
			
		});
		return intersections;
	}
	
	public List<Intersect> intersectObject(GeometryObject object){
		Geometry geometry = object.getGeometry();
		ArrayList<Intersect> intersections = Lists.newArrayList();
		for(Face3 face : geometry.getFaces()){
			Boolean isFace4 = face.getClass() == Face4.class;
			Vector3 origin = this.origin.clone(),
			direction = this.direction.clone();
			
			Matrix4 objMatrix = object.getMatrixWorld();
			List<Vertex> verticles = geometry.getVertices();
			
			Vector3 a = objMatrix.multiplyVector3(verticles.get(face.a).position.clone()),
			b = objMatrix.multiplyVector3(verticles.get(face.b).position.clone()),
			c = objMatrix.multiplyVector3(verticles.get(face.c).position.clone()),
			d = isFace4 ? objMatrix.multiplyVector3(verticles.get(((Face4)face).d).position.clone()) : null;
			
			Vector3 normal = object.getMatrixRotationWorld().multiplyVector3(face.normal.clone());
			double dot = direction.dot(normal);
			
			if(object instanceof SidesObject){
				SidesObject sideObject = (SidesObject)object;
				if (sideObject.getDoubleSided() || (sideObject.getFlipSided() ? dot > 0 : dot < 0)){ // Math.abs( dot ) > 0.0001
					double scalar = normal.dot(new Vector3().sub(a, origin)) / dot;
					Vector3 intersectPoint = origin.addSelf(direction.multiplyScalar(scalar));
					if (intersects(intersectPoint, a, b, c, d)){
						Intersect intersect = new Intersect();
						intersect.distance = this.origin.distanceTo(intersectPoint);
						intersect.point = intersectPoint;
						intersect.face = face;
						intersect.object = object;
						
						intersections.add(intersect);
					}
				}
			}
		}
		return intersections;
	}
	
	// Private Methods
	
	private Boolean intersects(Vector3 intersectPoint, Vector3 a, Vector3 b, Vector3 c, Vector3 d){
		Boolean intersects = false;
		if (/*is Face3*/ d == null && pointInFace3(intersectPoint, a, b, c)){
			intersects = true;
		} else if (/* is Face4 */ d != null && pointInFace3(intersectPoint, a, b, d) || pointInFace3(intersectPoint, b, c, d)) {
			intersects = true;
		}
		return intersects;
	}
	
	// http://www.blackpawn.com/texts/pointinpoly/default.html
	private Boolean pointInFace3(Vector3 p, Vector3 a, Vector3 b, Vector3 c){
		Vector3 v0 = c.clone().subSelf(a),
		v1 = b.clone().subSelf(a),
		v2 = p.clone().subSelf(a);
		double dot00 = v0.dot(v0), dot01 = v0.dot(v1), dot02 = v0.dot(v2), dot11 = v1.dot(v1), dot12 = v1.dot(v2);
		
		double invDenom = 1/(dot00*dot11 - dot01*dot01),
		u = (dot11*dot02 - dot01*dot12)*invDenom,
		v = (dot00*dot12 - dot01*dot02)*invDenom;
		
		return (u>0)&&(v>0)&&(u+v<1);
	}
	public void setOrigin(Vector3 origin) {
		this.origin = origin;
	}
	public Vector3 getOrigin() {
		return origin;
	}
	public void setDirection(Vector3 direction) {
		this.direction = direction;
	}
	public Vector3 getDirection() {
		return direction;
	}
}
