package com.google.code.gwt.threejs.client.renderers.renderables;

import java.util.ArrayList;
import java.util.List;

import com.google.code.gwt.threejs.client.core.UV;
import com.google.code.gwt.threejs.client.core.Vector3;
import com.google.code.gwt.threejs.client.materials.Material;
import com.google.common.collect.Lists;

public class RenderableFace3 implements Renderable {
	protected RenderableVertex v1;
	protected RenderableVertex v2;
	protected RenderableVertex v3;
	protected Vector3 centroidWorld;
	protected Vector3 centroidScreen;
	protected Vector3 normalWorld;
	protected List<Vector3> vertexNormalsWorld;
	protected List<Material> meshMaterials;
	protected List<Material> faceMaterials;
	protected boolean overdraw;
	protected Double z;
	protected ArrayList<ArrayList<UV>> uvs;
	
	public RenderableFace3(){
		this.v1 = new RenderableVertex();
		this.v2 = new RenderableVertex();
		this.v3 = new RenderableVertex();
		
		this.centroidWorld = new Vector3();
		this.centroidScreen = new Vector3();
		
		this.normalWorld = new Vector3();
		this.vertexNormalsWorld = Lists.<Vector3>newArrayList(new Vector3(), new Vector3(), new Vector3());
		
		this.meshMaterials = null;
		this.faceMaterials = null;
		this.overdraw = false;
		
		this.uvs = Lists.<ArrayList<UV>>newArrayList();
		this.uvs.add(Lists.<UV>newArrayList());
		
		this.z = null;
	}
	public void setV1(RenderableVertex v1) {
		this.v1 = v1;
	}
	public RenderableVertex getV1() {
		return v1;
	}
	public void setV2(RenderableVertex v2) {
		this.v2 = v2;
	}
	public RenderableVertex getV2() {
		return v2;
	}
	public void setV3(RenderableVertex v3) {
		this.v3 = v3;
	}
	public RenderableVertex getV3() {
		return v3;
	}
	public void setCentroidWorld(Vector3 centroidWorld) {
		this.centroidWorld = centroidWorld;
	}
	public Vector3 getCentroidWorld() {
		return centroidWorld;
	}
	public void setCentroidScreen(Vector3 centroidScreen) {
		this.centroidScreen = centroidScreen;
	}
	public Vector3 getCentroidScreen() {
		return centroidScreen;
	}
	public void setNormalWorld(Vector3 normalWorld) {
		this.normalWorld = normalWorld;
	}
	public Vector3 getNormalWorld() {
		return normalWorld;
	}
	public void setVertexNormalsWorld(List<Vector3> vertexNormalsWorld) {
		this.vertexNormalsWorld = vertexNormalsWorld;
	}
	public List<Vector3> getVertexNormalsWorld() {
		return vertexNormalsWorld;
	}
	public void setMeshMaterials(List<Material> meshMaterials) {
		this.meshMaterials = meshMaterials;
	}
	public List<Material> getMeshMaterials() {
		return meshMaterials;
	}
	public void setFaceMaterials(List<Material> faceMaterials) {
		this.faceMaterials = faceMaterials;
	}
	public List<Material> getFaceMaterials() {
		return faceMaterials;
	}
	public void setOverdraw(boolean overdraw) {
		this.overdraw = overdraw;
	}
	public boolean isOverdraw() {
		return overdraw;
	}
	public void setUvs(ArrayList<ArrayList<UV>> uvs){
		this.uvs = uvs ;
	}
	public ArrayList<ArrayList<UV>> getUvs(){
		return this.uvs;
	}
	
	public void setZ(Double z) {
		this.z = z;
	}
	public Double getZ() {
		return z;
	}
}
