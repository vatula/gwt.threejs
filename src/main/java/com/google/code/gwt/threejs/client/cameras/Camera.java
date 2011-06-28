package com.google.code.gwt.threejs.client.cameras;

import com.google.code.gwt.threejs.client.core.DimentionObject;
import com.google.code.gwt.threejs.client.core.Matrix4;
import com.google.code.gwt.threejs.client.core.Object3D;
import com.google.code.gwt.threejs.client.core.Vector3;

public final class Camera extends Object3D {
	public Camera(double fov, double aspect, double near, double far, DimentionObject target){
		super();
		this.fov = fov;
		this.aspect = aspect;
		this.near = near;
		this.far = far;
		this.target = target == null ? new Object3D() : target;
		this.useTarget = true;
		this.matrixWorldInverse = new Matrix4();
		this.projectionMatrix = null;
		this.updateProjectionMatrix();
	}

	// Properties
	
	protected double fov;
	public double getFov(){
		return this.fov;
	};
	public void setFov(double fov){
		this.fov = fov;
	};
	
	protected double aspect;
	public double getAspect(){
		return this.aspect;
	};
	public void setAspect(double aspect){
		this.aspect = aspect;
	};
	
	protected double near;
	public double getNear(){
		return this.near;
	};
	public void setNear(double near){
		this.near = near;
	};
	
	protected double far;
	public double getFar(){
		return this.far;
	};
	public void setFar(double far){
		this.far = far;
	};
	
	protected DimentionObject target;
	public DimentionObject getTarget(){
		return this.target;
	};
	public void setTarget(DimentionObject target){
		this.target = target;
	};
	
	protected Boolean useTarget;
	public Boolean getUseTarget(){
		return this.useTarget;
	};
	public void setUseTarget(Boolean useTarget){
		this.useTarget = useTarget ;
	};
	
	protected Matrix4 matrixWorldInverse;
	public Matrix4 getMatrixWorldInverse(){
		return this.matrixWorldInverse;
	};
	public void setMatrixWorldInverse(Matrix4 matrixWorldInverse){
		this.matrixWorldInverse = matrixWorldInverse;
	};
	
	protected Matrix4 projectionMatrix;
	public Matrix4 getProjectionMatrix(){
		return this.projectionMatrix;
	};
	public void setProjectionMatrix(Matrix4 projectionMatrix){
		this.projectionMatrix = projectionMatrix;
	};
	
	// Methods
	
	@Override
	public void translate(double distance, Vector3 axis){
		this.matrix.rotateAxis(axis);
		this.position.addSelf(axis.multiplyScalar(distance));
		this.target.getPosition().addSelf(axis.multiplyScalar(distance));
	}
	
	public void updateProjectionMatrix(){
		this.projectionMatrix = Matrix4.makePerspective(this.fov, this.aspect, this.near, this.far);
	}
	
	@Override
	public void update(Matrix4 parentMatrixWorld, Boolean forceUpdate, Camera camera){
		if ( this.useTarget ) {

			// local

			this.matrix.lookAt(this.position, this.target.getPosition(), this.up);
			this.matrix.setPosition(this.position);

			// global

			if (parentMatrixWorld != null) {
				this.matrixWorld.multiply(parentMatrixWorld, this.matrix);
			} else {
				this.matrixWorld.copy(this.matrix);
			}

			Matrix4.makeInvert( this.matrixWorld, this.matrixWorldInverse );
			forceUpdate = true;

		} else {
			if (this.matrixAutoUpdate){
				this.updateMatrix();
			}

			if (forceUpdate || this.matrixWorldNeedsUpdate) {

				if (parentMatrixWorld != null) {
					this.matrixWorld.multiply(parentMatrixWorld, this.matrix);
				} else {
					this.matrixWorld.copy(this.matrix);
				}

				this.matrixWorldNeedsUpdate = false;
				forceUpdate = true;

				Matrix4.makeInvert(this.matrixWorld, this.matrixWorldInverse);
			}
		}

		// update children

		for(DimentionObject child : children){
			child.update(this.matrixWorld, forceUpdate, camera);
		}
	};
}
