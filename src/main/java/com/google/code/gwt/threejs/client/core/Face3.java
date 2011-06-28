package com.google.code.gwt.threejs.client.core;

import java.util.ArrayList;

import com.google.code.gwt.threejs.client.materials.Material;

public class Face3{
	protected int a;
	protected int b;
	protected int c;
	protected Vector3 normal;
	protected ArrayList<Vector3> vertexNormals;
	protected Color color;
	protected ArrayList<Color> vertexColors;
	protected ArrayList<Material> materials;
	protected ArrayList<Vector3> vertexTangents;
	protected Vector3 centroid;
	
	public Face3(int a, int b, int c, ArrayList<Vector3> normals, ArrayList<Color> colors, ArrayList<Material> materials){
		this(a,b,c);
		
		this.setNormal(new Vector3());
		this.setVertexNormals(normals);
		
		this.setColor(new Color(0x000000));
		this.setVertexColors(new ArrayList<Color>());
		
		if (materials != null){
			this.materials = materials;
		}
	}
	
	public Face3(int a, int b, int c, Vector3 normal, Color color, Material material){
		this(a,b,c);
		this.setNormal(normal instanceof Vector3 ? normal : new Vector3());
		this.setVertexNormals(new ArrayList<Vector3>());
		
		this.setColor(color);
		this.setVertexColors(new ArrayList<Color>());
		
		if (material != null){
			this.materials.add(material);
		}
	}
	Face3(int a, int b, int c){
		this.setA(a);
		this.setB(b);
		this.setC(c);
		this.setVertexTangents(new ArrayList<Vector3>());
		this.setCentroid(new Vector3());
		this.materials = new ArrayList<Material>();
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getA() {
		return a;
	}

	public void setB(int b) {
		this.b = b;
	}

	public int getB() {
		return b;
	}

	public void setC(int c) {
		this.c = c;
	}

	public int getC() {
		return c;
	}
	
	public int[] getFlat(){
		int[] flat = new int[3];
		flat[0] = this.a;
		flat[1] = this.b;
		flat[2] = this.c;
		return flat;
	}

	public void setNormal(Vector3 normal) {
		this.normal = normal;
	}

	public Vector3 getNormal() {
		return normal;
	}

	public void setVertexNormals(ArrayList<Vector3> vertexNormals) {
		this.vertexNormals = vertexNormals;
	}

	public ArrayList<Vector3> getVertexNormals() {
		return vertexNormals;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public void setVertexColors(ArrayList<Color> vertexColors) {
		this.vertexColors = vertexColors;
	}

	public ArrayList<Color> getVertexColors() {
		return vertexColors;
	}

	public void setVertexTangents(ArrayList<Vector3> vertexTangents) {
		this.vertexTangents = vertexTangents;
	}

	public ArrayList<Vector3> getVertexTangents() {
		return vertexTangents;
	}

	public void setCentroid(Vector3 centroid) {
		this.centroid = centroid;
	}

	public Vector3 getCentroid() {
		return centroid;
	}
	
	public ArrayList<Material> getMaterials(){
		return this.materials;
	}

}
