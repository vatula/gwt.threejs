package com.google.code.gwt.threejs.client.core;

import java.util.ArrayList;
import java.util.Collection;

public class Edge {
	public Vertex[] vertices;
	public int[] vertexIndices;
	public Collection<Face3> faces;
	public Collection<Integer> faceIndices;
	public Edge(Vertex v1, Vertex v2, int vi1, int vi2){
		this.vertices = new Vertex[2];
		this.vertices[0] = v1;
		this.vertices[1] = v2;
		
		this.vertexIndices = new int[2];
		this.vertexIndices[0] = vi1;
		this.vertexIndices[1] = vi2;
		
		this.faces = new ArrayList<Face3>();
		this.faceIndices = new ArrayList<Integer>();
	}
}
