package com.google.code.gwt.threejs.client.renderers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.code.gwt.threejs.client.cameras.Camera;
import com.google.code.gwt.threejs.client.core.DimentionObject;
import com.google.code.gwt.threejs.client.core.Face3;
import com.google.code.gwt.threejs.client.core.Face4;
import com.google.code.gwt.threejs.client.core.Geometry;
import com.google.code.gwt.threejs.client.core.GeometryObject;
import com.google.code.gwt.threejs.client.core.Matrix4;
import com.google.code.gwt.threejs.client.core.UV;
import com.google.code.gwt.threejs.client.core.Vector3;
import com.google.code.gwt.threejs.client.core.Vector4;
import com.google.code.gwt.threejs.client.core.Vertex;
import com.google.code.gwt.threejs.client.materials.Material;
import com.google.code.gwt.threejs.client.objects.Line;
import com.google.code.gwt.threejs.client.objects.Mesh;
import com.google.code.gwt.threejs.client.objects.Particle;
import com.google.code.gwt.threejs.client.renderers.renderables.Renderable;
import com.google.code.gwt.threejs.client.renderers.renderables.RenderableFace3;
import com.google.code.gwt.threejs.client.renderers.renderables.RenderableFace4;
import com.google.code.gwt.threejs.client.renderers.renderables.RenderableLine;
import com.google.code.gwt.threejs.client.renderers.renderables.RenderableObject;
import com.google.code.gwt.threejs.client.renderers.renderables.RenderableParticle;
import com.google.code.gwt.threejs.client.renderers.renderables.RenderableVertex;
import com.google.code.gwt.threejs.client.scenes.Scene;
import com.google.common.collect.Lists;

public final class Projector {
	private RenderableObject object = null;
	private int objectCount = 0;
	private List<RenderableObject> objectPool = Lists.newArrayList();
	
	private RenderableVertex vertex = null;
	private int vertexCount = 0;
	private List<RenderableVertex> vertexPool = Lists.newArrayList();
	
	private RenderableFace3 face = null;
	private int face3Count = 0;
	private List<RenderableFace3> face3Pool = Lists.newArrayList();
	private int face4Count = 0;
	private List<RenderableFace4> face4Pool = Lists.newArrayList();
	
	private RenderableLine line = null;
	private int lineCount = 0;
	private List<RenderableLine> linePool = Lists.newArrayList();
	
	private RenderableParticle particle = null;
	private int particleCount = 0;
	private List<RenderableParticle> particlePool = Lists.newArrayList();
	
	private Vector3 vector3 = new Vector3();
	private Vector4 vector4 = new Vector4();
	
	private Matrix4 projScreenMatrix = new Matrix4();
	private Matrix4 projScreenObjectMatrix = new Matrix4();
	
	private List<Vector4> frustum = Lists.newArrayList(
		new Vector4(),
		new Vector4(),
		new Vector4(),
		new Vector4(),
		new Vector4(),
		new Vector4()
	);
	
	private Vector4 clippedVertex1PositionScreen = new Vector4();
	private Vector4 clippedVertex2PositionScreen = new Vector4();
	
	public Projector(){
		
	}
	
	public Vector3 projectVector(Vector3 vector, Camera camera){
		this.projScreenMatrix.multiply(camera.getProjectionMatrix(), camera.getMatrixWorldInverse());
		Vector3 result = this.projScreenMatrix.multiplyVector3(vector);
		return result;
	}
	public Vector3 unprojectVector(Vector3 vector, Camera camera){
		this.projScreenMatrix.multiply(camera.getMatrixWorld(), Matrix4.makeInvert(camera.getProjectionMatrix()));
		Vector3 result = this.projScreenMatrix.multiplyVector3(vector);
		return result;
	}
	
	public List<RenderableObject> projectObjects(Scene scene, Camera camera, boolean sort){
		List<RenderableObject> renderList = Lists.newArrayList();
		this.objectCount = 0;
		DimentionObject object;
		List<DimentionObject> objects = scene.getObjects();
		
		for (int o = 0, ol = objects.size(); o < ol; o++) {

			object = objects.get(o);

			if (!object.getVisible() || (object.getClass() == Mesh.class && !this.isInFrustum((Mesh)object))) continue;

			this.object = getNextObjectInPool();

			this.vector3.copy(object.getPosition());
			this.projScreenMatrix.multiplyVector3(this.vector3);

			this.object.setObject(object);
			this.object.setZ(this.vector3.getZ());

			renderList.add(this.object);

		}
		
		if (sort){
			Collections.sort(renderList, this.painterSort);
		}

		return renderList;
	}
	
	public List<Renderable> projectScene(Scene scene, Camera camera, boolean sort){
		List<Renderable> renderList = Lists.newArrayList();
		List<RenderableObject> objects;
		double near = camera.getNear(), far = camera.getFar();
		DimentionObject object;
		Matrix4 objectMatrix, objectMatrixRotation;

		this.face3Count = 0;
		this.face4Count = 0;
		this.lineCount = 0;
		this.particleCount = 0;

		if(camera.getMatrixAutoUpdate()) {
			camera.update(null, true, null);
		}

		scene.update(null, false, camera);

		this.projScreenMatrix.multiply(camera.getProjectionMatrix(), camera.getMatrixWorldInverse());
		this.computeFrustum(this.projScreenMatrix);

		objects = this.projectObjects(scene, camera, true);
		for (int o = 0, ol = objects.size(); o < ol; o++) {

			object = objects.get(o).getObject();

			if (!object.getVisible()) continue;

			objectMatrix = object.getMatrixWorld();
			objectMatrixRotation = object.getMatrixRotationWorld();

			this.vertexCount = 0;

			if (object.getClass() == Mesh.class) {
				Mesh mesh = (Mesh)object;
				List<Material> objectMaterials = mesh.getMaterials();
				Boolean objectOverdraw = mesh.getOverdraw();

				Geometry g = mesh.getGeometry();
				List<Vertex> vertices = g.getVertices();
				List<Face3> faces = g.getFaces();
				ArrayList<ArrayList<ArrayList<UV>>> faceVertexUvs = g.getFaceVertexUvs();
				for (int v = 0, vl = vertices.size(); v < vl; v++) {
					this.vertex = getNextVertexInPool();
					this.vertex.getPositionWorld().copy(vertices.get(v).getPosition());

					objectMatrix.multiplyVector3(this.vertex.getPositionWorld());

					Vector4 ps = this.vertex.getPositionScreen();
					ps.copy(this.vertex.getPositionWorld());
					//log("Before: "+ps.getX());
					//log("Suspicious row: "+this.projScreenMatrix.getN11()+","+this.projScreenMatrix.getN12()+","+this.projScreenMatrix.getN13()+","+this.projScreenMatrix.getN14());
					//log("Clear row 2: "+this.projScreenMatrix.getN31()+","+this.projScreenMatrix.getN32()+","+this.projScreenMatrix.getN33()+","+this.projScreenMatrix.getN34());
					this.projScreenMatrix.multiplyVector4(ps);
					//log("After: "+ps.getX());
					
					ps.setX(ps.getX()/ps.getW());
					ps.setY(ps.getY()/ps.getW());
					
					this.vertex.setVisible(ps.getZ() > near && ps.getZ() < far);
				}
				
				for (int f = 0, fl = faces.size(); f < fl; f++) {

					Face3 face = faces.get(f);
					
					if (face.getClass() == Face3.class) {

						RenderableVertex v1 = this.vertexPool.get(face.getA());
						RenderableVertex v2 = this.vertexPool.get(face.getB());
						RenderableVertex v3 = this.vertexPool.get(face.getC());
						
						if (v1.getVisible() && v2.getVisible() && v3.getVisible() &&
							(mesh.getDoubleSided() || ( mesh.getFlipSided() !=
							(v3.getPositionScreen().getX() - v1.getPositionScreen().getX()) * (v2.getPositionScreen().getY() - v1.getPositionScreen().getY() ) -
							(v3.getPositionScreen().getY() - v1.getPositionScreen().getY() ) * ( v2.getPositionScreen().getX() - v1.getPositionScreen().getX() ) < 0 ) ) ) {

							this.face = getNextFace3InPool();

							this.face.getV1().copy(v1);
							this.face.getV2().copy(v2);
							this.face.getV3().copy(v3);

						} else {

							continue;

						}

					} else if (face.getClass() == Face4.class) {
						Face4 face4 = (Face4)face;
						RenderableVertex v1 = this.vertexPool.get(face4.getA());
						RenderableVertex v2 = this.vertexPool.get(face4.getB());
						RenderableVertex v3 = this.vertexPool.get(face4.getC());
						RenderableVertex v4 = this.vertexPool.get(face4.getD());

						// Compare X of all v
						//log(""+v1.getPositionScreen().getX()+","+v2.getPositionScreen().getX()+","+v3.getPositionScreen().getX());
						
						if (v1.getVisible() && v2.getVisible() && v3.getVisible() && v4.getVisible() &&
							(mesh.getDoubleSided() || ( mesh.getFlipSided() !=
							((v4.getPositionScreen().getX() - v1.getPositionScreen().getX() ) * ( v2.getPositionScreen().getY() - v1.getPositionScreen().getY()) -
							(v4.getPositionScreen().getY() - v1.getPositionScreen().getY() ) * ( v2.getPositionScreen().getX() - v1.getPositionScreen().getX()) < 0 ||
							(v2.getPositionScreen().getX() - v3.getPositionScreen().getX() ) * ( v4.getPositionScreen().getY() - v3.getPositionScreen().getY()) -
							(v2.getPositionScreen().getY() - v3.getPositionScreen().getY() ) * ( v4.getPositionScreen().getX() - v3.getPositionScreen().getX()) < 0)))) {

							this.face = getNextFace4InPool();
							RenderableFace4 thisFace4 = (RenderableFace4)this.face;

							thisFace4.getV1().copy(v1);
							thisFace4.getV2().copy(v2);
							thisFace4.getV3().copy(v3);
							thisFace4.getV4().copy(v4);

						} else {

							continue;

						}

					}

					this.face.getNormalWorld().copy( face.getNormal() );
					objectMatrixRotation.multiplyVector3( this.face.getNormalWorld() );

					this.face.getCentroidWorld().copy( face.getCentroid() );
					objectMatrix.multiplyVector3( this.face.getCentroidWorld() );

					this.face.getCentroidScreen().copy( this.face.getCentroidWorld() );
					this.projScreenMatrix.multiplyVector3( this.face.getCentroidScreen() );

					List<Vector3> faceVertexNormals = face.getVertexNormals();

					for (int n = 0, nl = faceVertexNormals.size(); n < nl; n ++ ) {

						Vector3 normal = this.face.getVertexNormalsWorld().get(n);
						normal.copy(faceVertexNormals.get(n));
						objectMatrixRotation.multiplyVector3(normal);

					}

					for (int c = 0, cl = faceVertexUvs.size(); c < cl; c ++) {
						List<UV> uvs = faceVertexUvs.get(c).get(f);
						if (uvs == null) continue;
						for (int u = 0, ul = uvs.size(); u < ul; u++) {
							List<UV> faceUvs = this.face.getUvs().get(c);
							if (faceUvs.size() > u) {
								faceUvs.set(u, uvs.get(u));
							} else {
								faceUvs.add(u, uvs.get(u));
							}
						}
					}

					this.face.setMeshMaterials(objectMaterials);
					this.face.setFaceMaterials(face.getMaterials());
					this.face.setOverdraw(objectOverdraw);

					this.face.setZ(this.face.getCentroidScreen().getZ());

					renderList.add(this.face);
				}

			} else if (object.getClass() == Line.class) {
				Line line = (Line)object;

				this.projScreenObjectMatrix.multiply(this.projScreenMatrix, objectMatrix);
				List<Vertex> vertices = line.getGeometry().getVertices();

				RenderableVertex v1 = getNextVertexInPool();
				v1.getPositionScreen().copy(vertices.get(0).getPosition());
				this.projScreenObjectMatrix.multiplyVector4(v1.getPositionScreen());

				for (int v = 1, vl = vertices.size(); v < vl; v++) {

					v1 = getNextVertexInPool();
					v1.getPositionScreen().copy(vertices.get(v).getPosition());
					this.projScreenObjectMatrix.multiplyVector4(v1.getPositionScreen());

					RenderableVertex v2 = this.vertexPool.get(this.vertexCount - 2);

					this.clippedVertex1PositionScreen.copy(v1.getPositionScreen());
					this.clippedVertex2PositionScreen.copy(v2.getPositionScreen());

					if (clipLine(this.clippedVertex1PositionScreen, this.clippedVertex2PositionScreen)) {

						// Perform the perspective divide
						this.clippedVertex1PositionScreen.multiplyScalar(1 / this.clippedVertex1PositionScreen.getW());
						this.clippedVertex2PositionScreen.multiplyScalar(1 / this.clippedVertex2PositionScreen.getW());

						this.line = getNextLineInPool();
						this.line.getV1().getPositionScreen().copy(this.clippedVertex1PositionScreen);
						this.line.getV2().getPositionScreen().copy(this.clippedVertex2PositionScreen);

						this.line.setZ(Math.max(this.clippedVertex1PositionScreen.getZ(), this.clippedVertex2PositionScreen.getZ() ));

						this.line.setMaterials(line.getMaterials());

						renderList.add(this.line);
					}
				}

			} else if (object.getClass() == Particle.class) {
				Particle particle = (Particle)object;

				this.vector4.set(particle.getMatrixWorld().getN14(), particle.getMatrixWorld().getN24(), particle.getMatrixWorld().getN34(), 1);
				this.projScreenMatrix.multiplyVector4( this.vector4 );

				this.vector4.setZ(this.vector4.getZ()/this.vector4.getW());

				if ( this.vector4.getZ() > 0 && this.vector4.getZ() < 1 ) {

					this.particle = getNextParticleInPool();
					this.particle.setX(this.vector4.getX() / this.vector4.getW());
					this.particle.setY(this.vector4.getY() / this.vector4.getW());
					this.particle.setZ(this.vector4.getZ());

					this.particle.setRotation(particle.getRotation().getZ());

					this.particle.getScale().setX(particle.getScale().getX() * Math.abs( this.particle.getX() - (this.vector4.getX() + camera.getProjectionMatrix().getN11()) / (this.vector4.getW() + camera.getProjectionMatrix().getN14())));
					this.particle.getScale().setY(particle.getScale().getY() * Math.abs( this.particle.getY() - (this.vector4.getY() + camera.getProjectionMatrix().getN22()) / (this.vector4.getW() + camera.getProjectionMatrix().getN24())));

					this.particle.setMaterials(particle.getMaterials());

					renderList.add(this.particle);

				}

			}

		}

		if (sort) {
			Collections.sort(renderList, this.painterSort);
		}

		return renderList;
	}
	
	// Private Methods
	
	private RenderableObject getNextObjectInPool(){
		RenderableObject o;
		if (this.objectPool.size() > this.objectCount){
			o = this.objectPool.get(this.objectCount);
		} else {
			o = new RenderableObject();
			this.objectPool.add(this.objectCount, o);
		}
		this.objectCount++;
		return o;
	}
	
	private RenderableFace3 getNextFace3InPool(){
		RenderableFace3 f;
		if (this.face3Pool.size() > this.face3Count){
			f = this.face3Pool.get(this.face3Count);
		} else {
			f = new RenderableFace3();
			this.face3Pool.add(this.face3Count, f);
		}
		this.face3Count++;
		return f;
	}

	private RenderableFace4 getNextFace4InPool(){
		RenderableFace4 f;
		if (this.face4Pool.size() > this.face4Count){
			f = this.face4Pool.get(this.face4Count);
		} else {
			f = new RenderableFace4();
			this.face4Pool.add(this.face4Count, f);
		}
		this.face4Count++;
		return f;
	}

	private RenderableVertex getNextVertexInPool(){
		RenderableVertex v;
		if (this.vertexPool.size() > this.vertexCount){
			v = this.vertexPool.get(this.vertexCount);
		} else {
			v = new RenderableVertex();
			this.vertexPool.add(this.vertexCount, v);
		}
		this.vertexCount++;
		return v;
	}
	
	private RenderableLine getNextLineInPool(){
		RenderableLine l;
		if (this.linePool.size() > this.lineCount){
			l = this.linePool.get(this.lineCount);
		} else {
			l = new RenderableLine();
			this.linePool.add(this.lineCount, l);
		}
		this.lineCount++;
		return l;
	}
	
	private RenderableParticle getNextParticleInPool(){
		RenderableParticle p;
		if (this.particlePool.size() > this.particleCount){
			p = this.particlePool.get(this.particleCount);
		} else {
			p = new RenderableParticle();
			this.particlePool.add(this.particleCount, p);
		}
		this.particleCount++;
		return p;
	}
	
	private Comparator<Renderable> painterSort = new Comparator<Renderable>(){

		@Override
		public int compare(Renderable a, Renderable b) {
			return (int) ((b.getZ()-a.getZ())*1000);
		}
	};
	
	private void computeFrustum(Matrix4 m){
		this.frustum.get(0).set(m.getN41() - m.getN11(), m.getN42() - m.getN12(), m.getN43() - m.getN13(), m.getN44() - m.getN14() );
		this.frustum.get(1).set(m.getN41() + m.getN11(), m.getN42() + m.getN12(), m.getN43() + m.getN13(), m.getN44() + m.getN14());
		this.frustum.get(2).set(m.getN41() + m.getN21(), m.getN42() + m.getN22(), m.getN43() + m.getN23(), m.getN44() + m.getN24());
		this.frustum.get(3).set(m.getN41() - m.getN21(), m.getN42() - m.getN22(), m.getN43() - m.getN23(), m.getN44() - m.getN24());
		this.frustum.get(4).set(m.getN41() - m.getN31(), m.getN42() - m.getN32(), m.getN43() - m.getN33(), m.getN44() - m.getN34());
		this.frustum.get(5).set(m.getN41() + m.getN31(), m.getN42() + m.getN32(), m.getN43() + m.getN33(), m.getN44() + m.getN34());

		for (int i = 0; i < 6; i ++) {
			Vector4 plane = this.frustum.get(i);
			plane.divideScalar(Math.sqrt(plane.getX() * plane.getX() - plane.getY() * plane.getY() + plane.getZ() * plane.getZ()));
		}
	}
	
	private boolean isInFrustum(GeometryObject object){
		boolean result = true;
		double distance;
		Matrix4 matrix = object.getMatrixWorld();
		double radius = - object.getGeometry().getBoundingSphere().radius * Math.max(object.getScale().getX(), Math.max( object.getScale().getY(), object.getScale().getZ()));

		for (int i = 0; i < 6; i++) {
			Vector4 f = this.frustum.get(i);
			distance = f.getX() * matrix.getN14() + f.getY() * matrix.getN24() + f.getZ() * matrix.getN34() + f.getW();
			if (distance <= radius){
				result = false;
			}
		}
		return result;
	}
	
	private boolean clipLine(Vector4 s1, Vector4 s2){
		boolean result;
		double alpha1 = 0, alpha2 = 1,

		// Calculate the boundary coordinate of each vertex for the near and far clip planes,
		// Z = -1 and Z = +1, respectively.
		bc1near =  s1.getZ() + s1.getW(),
		bc2near =  s2.getZ() + s2.getW(),
		bc1far =  - s1.getZ() + s1.getW(),
		bc2far =  - s2.getZ() + s2.getW();

		if (bc1near >= 0 && bc2near >= 0 && bc1far >= 0 && bc2far >= 0) {

			// Both vertices lie entirely within all clip planes.
			result = true;

		} else if ((bc1near < 0 && bc2near < 0) || (bc1far < 0 && bc2far < 0)) {

			// Both vertices lie entirely outside one of the clip planes.
			result = false;

		} else {

			// The line segment spans at least one clip plane.

			if ( bc1near < 0 ) {

				// v1 lies outside the near plane, v2 inside
				alpha1 = Math.max( alpha1, bc1near / ( bc1near - bc2near ) );

			} else if ( bc2near < 0 ) {

				// v2 lies outside the near plane, v1 inside
				alpha2 = Math.min( alpha2, bc1near / ( bc1near - bc2near ) );

			}

			if ( bc1far < 0 ) {

				// v1 lies outside the far plane, v2 inside
				alpha1 = Math.max( alpha1, bc1far / ( bc1far - bc2far ) );

			} else if ( bc2far < 0 ) {

				// v2 lies outside the far plane, v2 inside
				alpha2 = Math.min( alpha2, bc1far / ( bc1far - bc2far ) );

			}

			if ( alpha2 < alpha1 ) {

				// The line segment spans two boundaries, but is outside both of them.
				// (This can't happen when we're only clipping against just near/far but good
				//  to leave the check here for future usage if other clip planes are added.)
				result = false;

			} else {

				// Update the s1 and s2 vertices to match the clipped line segment.
				s1.lerpSelf( s2, alpha1 );
				s2.lerpSelf( s1, 1 - alpha2 );

				result = true;
			}
		}
		return result;
	}

}
