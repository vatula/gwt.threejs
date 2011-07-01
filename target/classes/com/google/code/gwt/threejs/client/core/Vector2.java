package com.google.code.gwt.threejs.client.core;

public class Vector2 {
	public Vector2(){
		this(0,0);
	}
	public Vector2(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	public double getX() {
		return x;
	}

	public void setY(double y) {
		this.y = y;
	}
	public double getY() {
		return y;
	}

	protected double x;
	protected double y;
	
	// Methods
	
	public Vector2 copy(Vector2 v){
		this.x = v.x;
		this.y = v.y;
		return this;
	}
	
	public Vector2 addSelf(Vector2 v){
		return this.add(this, v);
	}
	
	public Vector2 add(Vector2 v1, Vector2 v2){
		this.x = v1.x + v2.x;
		this.y = v1.y + v2.y;
		return this;
	}
	
	public Vector2 subSelf(Vector2 v){
		return this.sub(this, v);
	}
	public Vector2 sub(Vector2 v1, Vector2 v2){
		this.x = v1.x - v2.x;
		this.y = v1.y - v2.y;
		return this;
	}
	public Vector2 multiplyScalar(double s){
		this.x *= s;
		this.y *= s;
		return this;
	}
	public Vector2 negate(){
		return this.multiplyScalar(-1);
	}
	public Vector2 unit(){
		return this.multiplyScalar(1/this.length());
	}
	public double length(){
		return Math.sqrt(this.lengthSq());
	}
	public double lengthSq() {
		return this.x * this.x + this.y * this.y;
	}
	
	public Vector2 clone(){
		return new Vector2(this.x, this.y);
	}
	
}
