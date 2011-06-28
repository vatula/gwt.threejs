package com.google.code.gwt.threejs.client.core;

public final class UV{
	protected double u, v;
	public UV(){
		this(0,0);
	}

	public UV(double u, double v){
		this.u = u;
		this.v = v;
	}

	// Properties
	
	public double getU(){
		return this.u;
	}
	public double getV(){
		return this.v;
	}
	
	// Methods
	
	public UV set(double u, double v){
		this.u = u;
		this.v = v;
		return this;
	}
	public UV copy(UV uv){
		return this.set(uv.u, uv.v);
	}
}
