package com.google.code.gwt.threejs.client.core;

public class Vector4 extends Vector3 {
	public Vector4(){
		this(0,0,0,1);
	}
	public Vector4(double x, double y, double z, double w){
		super(x,y,z);
		this.w = w;
	}
	
	protected double w;
	
	public Vector4 set(double x, double y, double z, double w){
		super.set(x, y, z);
		this.w = w;
		return this;
	}
	public void setW(double w) {
		this.w = w;
	}
	public double getW() {
		return w;
	}
	
	@Override
	public Vector4 copy(Vector3 v){
		return this.set(v.x, v.y, v.z, 1);
	}
	public Vector4 copy(Vector4 v){
		return this.set(v.x, v.y, v.z, v.w);
	}
	
	public Vector4 add(Vector4 v1, Vector4 v2){
		this.w = v1.w + v2.w;
		super.add(v1, v2);
		return this;
	};
	
	public Vector4 addSelf(Vector4 v){
		return this.add(this, v);
	};
	
	@Override
	public Vector4 addScalar(double s){
		this.w += s;
		super.addScalar(s);
		return this;
	};
	public Vector4 sub(Vector4 v1, Vector4 v2){
		this.w = v1.w - v2.w;
		super.sub(v1, v2);
		return this;
	};
	public Vector4 subSelf(Vector4 v){
		return this.sub(this, v);
	};
	public Vector4 multiply(Vector4 v1, Vector4 v2){
		this.w = v1.w * v2.w;
		super.multiply(v1, v2);
		return this;
	};

	public Vector4 multiplySelf(Vector4 v){
		return this.multiply(this, v);
	};
	
	@Override
	public Vector4 multiplyScalar(double s){
		this.w *= s;
		super.multiplyScalar(s);
		return this;
	};
	
	public Vector4 divide(Vector4 v1, Vector4 v2){
		this.w  = v1.w / v2.w;
		super.divide(v1, v2);
		return this;
	}
	
	public Vector4 divideSelf(Vector4 v){
		return this.divide(this, v);
	};
	
	@Override
	public Vector4 divideScalar(double s){
		this.w /= s;
		super.divideScalar(s);
		return this;
	};

	public Vector4 lerpSelf(Vector4 v, double alpha){
		return this.set(
			this.x + (v.x - this.x) * alpha,
			this.y + (v.y - this.y) * alpha,
			this.z + (v.z - this.z) * alpha,
			this.w + (v.w - this.w) * alpha
		);
	}

	@Override
	public Vector4 clone(){
		return new Vector4(this.x, this.y, this.z, this.w);
	}
}
