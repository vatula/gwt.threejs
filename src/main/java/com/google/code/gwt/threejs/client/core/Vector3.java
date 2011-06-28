package com.google.code.gwt.threejs.client.core;

public class Vector3 extends Vector2 {
	public Vector3(){
		this(0,0,0);
	}
	public Vector3(double x, double y, double z){
		super(x,y);
		this.z = z;
	}
	// Properties
	
	protected double z;
	public double getZ(){
		return this.z;
	};
	public void setZ(double z){
		this.z = z;
	};
	
	// Methods
	
	public Vector3 set(double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}
	
	public Vector3 copy(Vector3 v){
		this.x = v.x;
		this.y = v.y;
		this.z = v.z;
		return this;
		
	};
	public Vector3 add(Vector3 v1, Vector3 v2){
		this.x = v1.x + v2.x;
		this.y = v1.y + v2.y;
		this.z = v1.z + v2.z;
		return this;
	};
	public Vector3 addSelf(Vector3 v){
		return this.add(this, v);
	};
	public Vector3 addScalar(double s){
		this.x += s;
		this.y += s;
		this.z += s;
		return this;
	};
	public Vector3 sub(Vector3 v1, Vector3 v2){
		this.x = v1.x - v2.x;
		this.y = v1.y - v2.y;
		this.z = v1.z - v2.z;
		return this;
	};
	public Vector3 subSelf(Vector3 v){
		return this.sub(this, v);
	};
	public Vector3 cross(Vector3 v1, Vector3 v2){
		this.x = v1.y * v2.z - v1.z * v2.y;
		this.y = v1.z * v2.x - v1.x * v2.z;
		this.z = v1.x * v2.y - v1.y * v2.x;
		return this;
	};
	public Vector3 crossSelf(Vector3 v){
		return this.cross(this, v);
	};
	public Vector3 multiply(Vector3 v1, Vector3 v2){
		this.x = v1.x * v2.x;
		this.y = v1.y * v2.y;
		this.z = v1.z * v2.z;
		return this;
	};
	public Vector3 multiplySelf(Vector3 v){
		return this.multiply(this, v);
	};
	
	@Override
	public Vector3 multiplyScalar(double s){
		this.x *= s;
		this.y *= s;
		this.z *= s;
		return this;
	};
	
	public Vector3 divide(Vector3 v1, Vector3 v2){
		this.x = v1.x / v2.x;
		this.y = v1.y / v2.y;
		this.z = v1.z / v2.z;
		return this;
	}
	
	public Vector3 divideSelf(Vector3 v){
		return this.divide(this, v);
	};
	public Vector3 divideScalar(double s){
		this.x /= s;
		this.y /= s;
		this.z /= s;
		return this;
	};
	
	@Override
	public Vector3 negate(){
		return this.multiplyScalar(-1);
	};
	public double dot(Vector3 v){
		return this.x * v.x + this.y * v.y + this.z * v.z;
	};
	public double distanceTo(Vector3 v){
		return Math.sqrt(this.distanceToSquared(v));
	};
	public double distanceToSquared(Vector3 v){
		double dx = this.x - v.x;
		double dy = this.y - v.y;
		double dz = this.z - v.z;
		return dx*dx + dy*dy + dz*dz;
	};
	
	@Override
	public double length(){
		return Math.sqrt(this.lengthSq());
	}
	
	@Override
	public double lengthSq(){
		return this.x*this.x + this.y*this.y + this.z*this.z;
	};
	public double lengthManhattan(){
		return this.x + this.y + this.z;
	};
	public Vector3 normalize(){
		double len = this.length();
		if (len > 0){
			this.multiplyScalar(1/len);
		} else {
			this.setX(0);
			this.setY(0);
			this.setZ(0);
		}
		return this;
	};
	public void setPositionFromMatrix(Matrix4 m){
		this.x = m.n14;
		this.y = m.n24;
		this.z = m.n34;
	};
	public void setRotationFromMatrix(Matrix4 m){
		double cosY = Math.cos(this.y);
		this.y = Math.asin( m.n13 );
		if (Math.abs(cosY) > 0.00001) {
			this.x = Math.atan2(-m.n23/cosY, m.n33/cosY );
			this.z = Math.atan2(-m.n12/cosY, m.n11/cosY );
		} else {
			this.x = 0;
			this.z = Math.atan2(m.n21, m.n22);
		}
	};
	public Vector3 setLength(double l){
		return this.normalize().multiplyScalar(l);
	};
	public Boolean isZero(){
		double almostZero = 0.0001;
		return (Math.abs(this.x) < almostZero) && (Math.abs(this.y) < almostZero) && (Math.abs(this.z) < almostZero);
	};
	
	@Override
	public Vector3 clone(){
		return new Vector3(this.x, this.y, this.z);
	};
}
