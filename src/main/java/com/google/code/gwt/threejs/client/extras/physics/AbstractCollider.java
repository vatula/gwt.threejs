package com.google.code.gwt.threejs.client.extras.physics;

abstract class AbstractCollider implements Collider {
	public double distance;
	public int faceIndex;
	
	@Override
	public double getDistance(){
		return this.distance;
	}
	
	@Override
	public void setDistance(double distance){
		this.distance = distance;
	}

	@Override
	public double getFaceIndex(){
		return this.faceIndex;
	}
	
	@Override
	public void setFaceIndex(int faceIndex){
		this.faceIndex = faceIndex;
	}
}