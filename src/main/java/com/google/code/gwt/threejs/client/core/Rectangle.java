package com.google.code.gwt.threejs.client.core;

public final class Rectangle {
	protected double left, top, right, bottom, width, height;
	protected boolean isEmpty;
	
	public Rectangle(){
		this(0,0,0,0);
	}
	public Rectangle(int left, double top, double right, double bottom){
		this.set(left,top,right,bottom);
	}
	public Rectangle set(double left, double top, double right, double bottom){
		this.isEmpty = false;
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
		return resize();
	}
	
	// Properties
	
	public double getWidth(){
		return this.width;
	}
	public double getHeight(){
		return this.height;
	}
	public double getLeft(){
		return this.left;
	}
	public double getTop(){
		return this.top;
	}
	public double getRight(){
		return this.right;
	}
	public double getBottom(){
		return this.bottom;
	}
	
	// Methods
	
	public Rectangle resize(){
		this.width = this.right-this.left;
		this.height = this.bottom-this.top;
		return this;
	}
	
	public void addPoint(double x, double y){
		if (this.isEmpty){
			this.isEmpty = false;
			this.left = x;
			this.top = y;
			this.right = x;
			this.bottom = y;
			resize();
		} else {
			this.left = Math.min(this.left, x);
			this.top = Math.min(this.top, y);
			this.right = Math.max(this.right, x);
			this.bottom = Math.max(this.bottom, y);
			resize();
		}
	}
	
	public void add3Points(double x1, double y1, double x2, double y2, double x3, double y3){
		if (this.isEmpty) {
			this.isEmpty = false;
			this.left = x1 < x2 ? (x1 < x3 ? x1 : x3) : (x2 < x3 ? x2 : x3);
			this.top = y1 < y2 ? (y1 < y3 ? y1 : y3) : (y2 < y3 ? y2 : y3);
			this.right = x1 > x2 ? (x1 > x3 ? x1 : x3) : (x2 > x3 ? x2 : x3);
			this.bottom = y1 > y2 ? (y1 > y3 ? y1 : y3) : (y2 > y3 ? y2 : y3);
			resize();
		} else {
			this.left = x1 < x2 ? (x1 < x3 ? (x1 < this.left ? x1 : this.left) : (x3 < this.left ? x3 : this.left)) : (x2 < x3 ? (x2 < this.left ? x2 : this.left) : (x3 < this.left ? x3 : this.left));
			this.top = y1 < y2 ? (y1 < y3 ? (y1 < this.top ? y1 : this.top) : (y3 < this.top ? y3 : this.top)) : (y2 < y3 ? (y2 < this.top ? y2 : this.top) : (y3 < this.top ? y3 : this.top));
			this.right = x1 > x2 ? (x1 > x3 ? (x1 > this.right ? x1 : this.right) : (x3 > this.right ? x3 : this.right)) : (x2 > x3 ? (x2 > this.right ? x2 : this.right) : (x3 > this.right ? x3 : this.right));
			this.bottom = y1 > y2 ? (y1 > y3 ? (y1 > this.bottom ? y1 : this.bottom) : (y3 > this.bottom ? y3 : this.bottom)) : (y2 > y3 ? (y2 > this.bottom ? y2 : this.bottom) : (y3 > this.bottom ? y3 : this.bottom));
			resize();

		};
	}
	
	public void addRectangle(Rectangle r){
		if (this.isEmpty) {
			this.isEmpty = false;
			this.left = r.left;
			this.top = r.top;
			this.right = r.right;
			this.bottom = r.bottom;
			resize();
		} else {
			this.left = Math.min(this.left, r.left);
			this.top = Math.min(this.top, r.top);
			this.right = Math.max(this.right, r.right);
			this.bottom = Math.max(this.bottom, r.bottom);
			resize();
		}
	}
	
	public void inflate(double v){
		this.left -= v;
		this.top -= v;
		this.right += v;
		this.bottom += v;
		resize();
	}
	
	public void minSelf(Rectangle r){
		this.left = Math.max(this.left, r.left);
		this.top = Math.max(this.top, r.top);
		this.right = Math.min(this.right, r.right);
		this.bottom = Math.min(this.bottom, r.bottom);

		resize();
	}
	
	public Boolean intersects(Rectangle r){
		return Math.min(this.right, r.right) - Math.max(this.left, r.left) >= 0
				&& Math.min(this.bottom, r.bottom) - Math.max(this.top, r.top) >= 0;
	}
	
	public void empty(){
		this.isEmpty = true;
		this.left = this.right = this.top = this.bottom = 0;
		resize();
	}
	
	public boolean isEmpty(){
		return this.isEmpty;
	}
}
