package com.google.code.gwt.threejs.client.core;

import java.util.ArrayList;

import com.google.common.collect.*;

public class Geometry {

	public class BoundingBox{
		public double[] x = new double[2];
		public double[] y = new double[2];
		public double[] z = new double[2];
	}
	
	public class BoundingSphere{
		public BoundingSphere(double radius){
			this.radius = radius;
		}
		public double radius;
	}
	
	protected BoundingBox boundingBox;
	protected BoundingSphere boundingSphere;
	protected ArrayList<DimentionObject> morphTargets;
	private String id;
	protected ArrayList<Vertex> vertices;
	protected ArrayList<Vector3> tempVerticles;
	protected ArrayList<Color> colors;
	protected ArrayList<Face3> faces;
	protected ArrayList<Edge> edges;
	protected ArrayList<ArrayList<UV>> faceUvs;
	protected ArrayList<ArrayList<ArrayList<UV>>> faceVertexUvs;
	protected Boolean hasTangents;

	public static int GeometryIdCounter = 0;
	
	public Geometry(){
		this.id = "Geometry" + GeometryIdCounter++;

		this.vertices = Lists.newArrayList();
		this.colors = Lists.newArrayList(); // one-to-one vertex colors, used in ParticleSystem, Line and Ribbon

		this.faces = Lists.newArrayList();

		this.edges = Lists.newArrayList();

		this.faceUvs = Lists.newArrayList();
		this.faceVertexUvs = Lists.newArrayList();
		ArrayList<ArrayList<UV>> firstChild = Lists.newArrayList();
		this.faceVertexUvs.add(firstChild);

		this.morphTargets = Lists.newArrayList();
		//this.morphColors = [];

		//this.skinWeights = [];
		//this.skinIndices = [];

		this.boundingBox = null;
		this.boundingSphere = null;

		this.hasTangents = false;
	}

	// Methods
	
	public void computeCentroids(){
		int f, fl;

		Face3 face;
		
		for (f = 0, fl = this.faces.size(); f < fl; f++) {

			face = this.faces.get(f);
			face.getCentroid().set(0,0,0);

			if (face.getClass() == Face3.class) {
				Face3 face3 = (Face3)face;
				face3.getCentroid().addSelf(this.vertices.get(face3.getA()).getPosition());
				face3.getCentroid().addSelf(this.vertices.get(face3.getB()).getPosition());
				face3.getCentroid().addSelf(this.vertices.get(face3.getC()).getPosition());
				face3.getCentroid().divideScalar(3);

			} else if (face.getClass() == Face4.class) {
				Face4 face4 = (Face4)face;
				face4.getCentroid().addSelf(this.vertices.get(face4.getA()).getPosition());
				face4.getCentroid().addSelf(this.vertices.get(face4.getB()).getPosition());
				face4.getCentroid().addSelf(this.vertices.get(face4.getC()).getPosition());
				face4.getCentroid().addSelf(this.vertices.get(face4.getD()).getPosition());
				face4.getCentroid().divideScalar(4);
			}

		}
	}
	public void computeFaceNormals(Boolean useVertexNormals){
		int n, nl, f, fl;
		Face3 face;
		Vertex vA, vB, vC;
		Vector3 cb = new Vector3(), ab = new Vector3();

		/*
		for ( v = 0, vl = this.vertices.length; v < vl; v ++ ) {

			vertex = this.vertices[ v ];
			vertex.normal.set( 0, 0, 0 );

		}
		*/

		for (f = 0, fl = this.faces.size(); f < fl; f ++ ) {
			face = this.faces.get(f);
			if (useVertexNormals && face.getVertexNormals().size() != 0) {
				cb.set(0,0,0);
				for (n = 0, nl = face.getVertexNormals().size(); n < nl; n++ ) {
					cb.addSelf( face.getVertexNormals().get(n));
				}
				cb.divideScalar(3);
				if (!cb.isZero()){
					cb.normalize();
				}
				face.getNormal().copy(cb);
			} else {
				vA = this.vertices.get(face.getA());
				vB = this.vertices.get(face.getB());
				vC = this.vertices.get(face.getC());

				cb.sub(vC.getPosition(), vB.getPosition());
				ab.sub(vA.getPosition(), vB.getPosition());
				cb.crossSelf(ab);

				if (!cb.isZero()) {
					cb.normalize();
				}
				face.getNormal().copy(cb);
			}
		}
	}
	public void computeVertexNormals(){
		// create internal buffers for reuse when calling this method repeatedly
		// (otherwise memory allocation / deallocation every frame is big resource hog)

		if (this.tempVerticles == null) {

			this.tempVerticles = new ArrayList<Vector3>(this.vertices.size());

			for (int v = 0, vl = this.vertices.size(); v < vl; v++){				this.tempVerticles.add(v, new Vector3());
			}

			for (Face3 face : this.faces) {

				if (face.getClass() == Face3.class){
					ArrayList<Vector3> normals = face.getVertexNormals();
					normals.clear();
					normals.add(new Vector3());
					normals.add(new Vector3());
					normals.add(new Vector3());
				} else if (face.getClass() == Face4.class) {
					ArrayList<Vector3> normals = face.getVertexNormals();
					normals.clear();
					normals.add(new Vector3());
					normals.add(new Vector3());
					normals.add(new Vector3());
					normals.add(new Vector3());
				}
			}
		} else {			for (int v = 0, vl = this.vertices.size(); v < vl; v++) {				this.tempVerticles.get(v).set(0,0,0);
			}
		}

		for (Face3 face : this.faces) {
			if (face.getClass() == Face3.class) {
				Face3 face3 = face;				this.tempVerticles.get(face3.getA()).addSelf(face3.getNormal());				this.tempVerticles.get(face3.getB()).addSelf(face3.getNormal());				this.tempVerticles.get(face3.getC()).addSelf(face3.getNormal());

			} else if (face.getClass() == Face4.class) {
				Face4 face4 = (Face4)face;				this.tempVerticles.get(face4.getA()).addSelf(face4.getNormal());				this.tempVerticles.get(face4.getB()).addSelf(face4.getNormal());				this.tempVerticles.get(face4.getC()).addSelf(face4.getNormal());				this.tempVerticles.get(face4.getD()).addSelf(face4.getNormal());
			}
		}

		for (int v = 0, vl = this.vertices.size(); v < vl; v ++ ) {			this.tempVerticles.get(v).normalize();
		}

		for (Face3 face : this.faces) {
			if (face.getClass() == Face3.class) {
				Face3 face3 = face;
				face3.getVertexNormals().get(0).copy(this.tempVerticles.get(face3.getA()));
				face3.getVertexNormals().get(1).copy(this.tempVerticles.get(face3.getB()));
				face3.getVertexNormals().get(2).copy(this.tempVerticles.get(face3.getC()));

			} else if (face.getClass() == Face4.class) {
				Face4 face4 = (Face4)face;
				face4.getVertexNormals().get(0).copy(this.tempVerticles.get(face4.getA()));
				face4.getVertexNormals().get(1).copy(this.tempVerticles.get(face4.getB()));
				face4.getVertexNormals().get(2).copy(this.tempVerticles.get(face4.getC()));
				face4.getVertexNormals().get(3).copy(this.tempVerticles.get(face4.getD()));
			}

		}
	}
	public void computeTangents(){
		Face3 face;
		UV[] uv = new UV[0];
		int v, vl, f, fl, i, vertexIndex;
		ArrayList<Vector3> tan1 = Lists.newArrayList(), tan2 = Lists.newArrayList();
		Vector3 tmp = new Vector3(), tmp2 = new Vector3();

		for (v = 0,vl = this.vertices.size(); v<vl; v++) {
			tan1.add(v, new Vector3());
			tan2.add(v, new Vector3());
		}
		
		for (f = 0, fl = this.faces.size(); f < fl; f++) {

			face = this.faces.get(f);
			uv = this.faceVertexUvs.get(0).get(f).toArray(uv); // use UV layer 0 for tangents

			if (face.getClass() == Face3.class) {
				handleTriangle(face.getA(), face.getB(), face.getC(), 0, 1, 2, uv, tan1, tan2);

			} else if (face.getClass() == Face4.class) {
				Face4 face4 = (Face4)face;
				handleTriangle(face4.getA(), face4.getB(), face4.getC(), 0, 1, 2, uv, tan1, tan2);
				handleTriangle(face4.getA(), face4.getB(), face4.getD(), 0, 1, 3, uv, tan1, tan2);

			}
		}

		for (f = 0, fl = this.faces.size(); f < fl; f ++ ) {

			face = this.faces.get(f);

			for (i = 0; i < face.getVertexNormals().size(); i++) {

				Vector3 n = new Vector3();
				n.copy(face.getVertexNormals().get(i));

				vertexIndex = face.getFlat()[i];

				Vector3 t = tan1.get(vertexIndex);

				// Gram-Schmidt orthogonalize

				tmp.copy(t);
				tmp.subSelf(n.multiplyScalar(n.dot(t))).normalize();

				// Calculate handedness

				tmp2.cross(face.getVertexNormals().get(i), t);
				double test = tmp2.dot(tan2.get(vertexIndex));
				double w = (test < 0.0) ? -1.0 : 1.0;
				
				face.getVertexTangents().set(i, new Vector4(tmp.x,tmp.y,tmp.z,w));
			}

		}

		this.hasTangents = true;
	}
	public void computeBoundingBox(){
		if (this.vertices.size() > 0) {
			Vector3 position = this.vertices.get(0).getPosition();
			this.boundingBox = new BoundingBox();
			boundingBox.x[0] = position.x;
			boundingBox.x[1] = position.x;
			boundingBox.y[0] = position.y;
			boundingBox.y[1] = position.y;
			boundingBox.z[0] = position.z;
			boundingBox.z[1] = position.z;
			for (int v = 1, vl = this.vertices.size(); v < vl; v++) {
				position = this.vertices.get(v).getPosition();

				if (position.getX() < this.boundingBox.x[0]) {
					this.boundingBox.x[0] = position.x;
				} else if (position.x > this.boundingBox.x[1]) {
					this.boundingBox.x[1] = position.x;
				}

				if (position.y < this.boundingBox.y[0]) {
					this.boundingBox.y[0] = position.y;
				} else if (position.y > this.boundingBox.y[1]) {
					this.boundingBox.y[1] = position.y;
				}

				if (position.z < this.boundingBox.z[0]) {
					this.boundingBox.z[0] = position.z;
				} else if (position.z > this.boundingBox.z[1]) {
					this.boundingBox.z[1] = position.z;
				}

			}

		}
	}
	public void computeBoundingSphere(){
		double radius = this.boundingSphere == null ? 0 : this.boundingSphere.radius;
		for (int v = 0, vl = this.vertices.size(); v < vl; v++) {
			radius = Math.max(radius, this.vertices.get(v).getPosition().length());
		}
		this.boundingSphere = new BoundingSphere(radius);
	}
	
	public void computeEdgeFaces(){
		HashMultimap<String, Integer> vfMap = HashMultimap.<String, Integer>create();
		for(int i = 0, il = this.faces.size(); i < il; i++) {

			Face3 face = this.faces.get(i);
			String hash;

			if (face.getClass() == Face3.class) {

				hash = getHash(face.a, face.b);
				vfMap.put(hash, i);

				hash = getHash(face.b, face.c);
				vfMap.put(hash, i);

				hash = getHash(face.a, face.c);
				vfMap.put(hash, i);

			} else if (face.getClass() == Face4.class) {

				// in WebGLRenderer quad is tesselated
				// to triangles: a,b,d / b,c,d
				// shared edge is: b,d

				// should shared edge be included?
				// comment out if not
				
				Face4 face4 = (Face4)face;

				hash = getHash(face4.b, face4.d); 
				vfMap.put(hash, i);

				hash = getHash(face4.a, face4.b);
				vfMap.put(hash, i);

				hash = getHash(face4.a, face4.d);
				vfMap.put(hash, i);

				hash = getHash(face4.b, face4.c);
				vfMap.put(hash, i);

				hash = getHash(face4.c, face4.d);
				vfMap.put(hash, i);

			}

		}

		// extract faces

		for(int i = 0, il = this.edges.size(); i < il; i++) {
			Edge edge = this.edges.get(i);

			int v1 = edge.vertexIndices[0];
			int v2 = edge.vertexIndices[1];

			edge.faceIndices = vfMap.get(getHash(v1, v2));

			for(int faceIndex : edge.faceIndices) {
				edge.faces.add(this.faces.get(faceIndex));
			}

		}
	}
	
	// ___________________
	
	private String getHash(int a, int b){
		return Math.min(a, b) + "_" + Math.max(a, b);
	}
	
	private void handleTriangle(int a, int b, int c, int ua, int ub, int uc, UV[] uv, ArrayList<Vector3> tan1, ArrayList<Vector3> tan2){
		Vector3 vA = this.vertices.get(a).getPosition();
		Vector3 vB = this.vertices.get(b).getPosition();
		Vector3 vC = this.vertices.get(c).getPosition();
		
		UV uvA = uv[ua];
		UV uvB = uv[ub];
		UV uvC = uv[uc];
		
		double x1 = vB.x - vA.x;
		double x2 = vC.x - vA.x;
		double y1 = vB.y - vA.y;
		double y2 = vC.y - vA.y;
		double z1 = vB.z - vA.z;
		double z2 = vC.z - vA.z;
		
		double s1 = uvB.getU() - uvA.getU();
		double s2 = uvC.getU() - uvA.getU();
		double t1 = uvB.getV() - uvA.getV();
		double t2 = uvC.getV() - uvA.getV();
		
		double r = 1.0/(s1*t2-s2*t1);
		
		Vector3 sdir = new Vector3();
		sdir.set((t2*x1-t1*x2)*r,
				  (t2*y1-t1*y2)*r,
				  (t2*z1-t1*z2)*r);
		
		Vector3 tdir = new Vector3();
		tdir.set((s1*x2 - s2*x1)*r,
				  (s1*y2 - s2*y1)*r,
				  (s1*z2 - s2*z1)*r);
		
		tan1.get(a).addSelf(sdir);
		tan1.get(b).addSelf(sdir);
		tan1.get(c).addSelf(sdir);

		tan2.get(a).addSelf(tdir);
		tan2.get(b).addSelf(tdir);
		tan2.get(c).addSelf(tdir);
	}

	public void setHasTangents(Boolean hasTangents) {
		this.hasTangents = hasTangents;
	}

	public Boolean getHasTangents() {
		return hasTangents;
	}

	public void setFaceUvs(ArrayList<ArrayList<UV>> faceUvs) {
		this.faceUvs = faceUvs;
	}

	public ArrayList<ArrayList<UV>> getFaceUvs() {
		return faceUvs;
	}

	public void setColors(ArrayList<Color> colors) {
		this.colors = colors;
	}

	public ArrayList<Color> getColors() {
		return colors;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setFaces(ArrayList<Face3> faces) {
		this.faces = faces;
	}

	public ArrayList<Face3> getFaces() {
		return faces;
	}

	public void setVertices(ArrayList<Vertex> vertices) {
		this.vertices = vertices;
	}

	public ArrayList<Vertex> getVertices() {
		return vertices;
	}

	public void setBoundingSphere(BoundingSphere boundingSphere) {
		this.boundingSphere = boundingSphere;
	}

	public BoundingSphere getBoundingSphere() {
		return boundingSphere;
	}

	public void setBoundingBox(BoundingBox boundingBox) {
		this.boundingBox = boundingBox;
	}

	public BoundingBox getBoundingBox() {
		return boundingBox;
	}

	public void setMorphTargets(ArrayList<DimentionObject> morphTargets) {
		this.morphTargets = morphTargets;
	}

	public ArrayList<DimentionObject> getMorphTargets() {
		return morphTargets;
	}
	
	public ArrayList<ArrayList<ArrayList<UV>>> getFaceVertexUvs(){
		return this.faceVertexUvs;
	}
}
