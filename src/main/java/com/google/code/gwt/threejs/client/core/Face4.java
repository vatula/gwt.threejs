package com.google.code.gwt.threejs.client.core;

import java.util.ArrayList;

import com.google.code.gwt.threejs.client.materials.Material;

public class Face4 extends Face3 {
	protected int d;
	
	public Face4(int a, int b, int c, int d){
		this(a,b,c,d, (Vector3)null,null,null);
	}
	public Face4(int a, int b, int c, int d, ArrayList<Vector3> normals, ArrayList<Color> colors, ArrayList<Material> materials){
		super(a,b,c,normals,colors,materials);
		this.setD(d);
	}
	public Face4(int a, int b, int c, int d, Vector3 normal, Color color, Material material){
		super(a,b,c,normal,color,material);
		this.setD(d);
	}
	public void setD(int d) {
		this.d = d;
	}
	public int getD() {
		return d;
	}
	
	@Override
	public int[] getFlat(){
		int[] flat = new int[4];
		flat[0] = this.a;
		flat[1] = this.b;
		flat[2] = this.c;
		flat[3] = this.d;
		return flat;
	}
}
