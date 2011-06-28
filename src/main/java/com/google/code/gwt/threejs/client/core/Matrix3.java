package com.google.code.gwt.threejs.client.core;

public final class Matrix3 {
	protected double[] m;
	public Matrix3(){
		m = new double[8];
	}
	
	
	// Methods
	
	public Matrix3 transpose(){
		double tmp; double[] m = this.m;

		tmp = m[1]; m[1] = m[3]; m[3] = tmp;
		tmp = m[2]; m[2] = m[6]; m[6] = tmp;
		tmp = m[5]; m[5] = m[7]; m[7] = tmp;

		return this;
	};
	public Matrix3 transposeIntoArray(double[] r){
		double[] m = this.m;

		r[0] = m[0];
		r[1] = m[3];
		r[2] = m[6];
		r[3] = m[1];
		r[4] = m[4];
		r[5] = m[7];
		r[6] = m[2];
		r[7] = m[5];
		r[8] = m[8];

		return this;
	};
}
