package com.google.code.gwt.threejs.client.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.code.gwt.threejs.client.cameras.Camera;
import com.google.code.gwt.threejs.client.scenes.Scene;

public class Object3D implements DimentionObject {
	public Object3D(){
		this.parent = null;
		this.children = new ArrayList<DimentionObject>();
		this.up = new Vector3(0,1,0);
		this.position = new Vector3();
		this.rotation = new Vector3();
		this.scale = new Vector3(1,1,1);
		this.dynamic = false;
		this.rotationAutoUpdate = true;
		this.matrix = new Matrix4();
		this.matrixWorld = new Matrix4();
		this.matrixRotationWorld = new Matrix4();
		this.matrixAutoUpdate = true;
		this.matrixWorldNeedsUpdate = true;
		this.quaternion = new Quaternion();
		this.useQuaternion = false;
		this.boundRadius = 0.0;
		this.boundRadiusScale = 1.0;
		this.visible = true;
		this.vector = new Vector3();
		this.name = "";
	}
	
	protected DimentionObject parent;
	/* (non-Javadoc)
	 * @see com.google.code.gwt.threejs.client.core.DimentionObject#getParent()
	 */
	@Override
	public DimentionObject getParent(){
		return this.parent;
	};
	/* (non-Javadoc)
	 * @see com.google.code.gwt.threejs.client.core.DimentionObject#setParent(com.google.code.gwt.threejs.client.core.Object3D)
	 */
	@Override
	public void setParent(DimentionObject parent){
		this.parent = parent;
	};
	
	protected ArrayList<DimentionObject> children;
	/* (non-Javadoc)
	 * @see com.google.code.gwt.threejs.client.core.DimentionObject#getChildren()
	 */
	@Override
	public List<DimentionObject> getChildren(){
		return this.children;
	};
	/* (non-Javadoc)
	 * @see com.google.code.gwt.threejs.client.core.DimentionObject#setChildren(java.util.Collection)
	 */
	@Override
	public void setChildren(Collection<? extends DimentionObject> children){
		this.children = new ArrayList<DimentionObject>(children);
	};
	
	protected Vector3 position;
	/* (non-Javadoc)
	 * @see com.google.code.gwt.threejs.client.core.DimentionObject#getPosition()
	 */
	@Override
	public Vector3 getPosition(){
		return this.position;
	};
	/* (non-Javadoc)
	 * @see com.google.code.gwt.threejs.client.core.DimentionObject#setPosition(com.google.code.gwt.threejs.client.core.Vector3)
	 */
	@Override
	public void setPosition(Vector3 position){
		this.position = position;
	};
	
	protected Vector3 rotation;
	/* (non-Javadoc)
	 * @see com.google.code.gwt.threejs.client.core.DimentionObject#getRotation()
	 */
	@Override
	public Vector3 getRotation(){
		return this.rotation;
	};
	/* (non-Javadoc)
	 * @see com.google.code.gwt.threejs.client.core.DimentionObject#setRotation(com.google.code.gwt.threejs.client.core.Vector3)
	 */
	@Override
	public void setRotation(Vector3 rotation){
		this.rotation = rotation;
	};
	
	protected Boolean dynamic;
	/* (non-Javadoc)
	 * @see com.google.code.gwt.threejs.client.core.DimentionObject#setDynamic(java.lang.Boolean)
	 */
	@Override
	public void setDynamic(Boolean dynamic) {
		this.dynamic = dynamic;
	}
	/* (non-Javadoc)
	 * @see com.google.code.gwt.threejs.client.core.DimentionObject#getDynamic()
	 */
	@Override
	public Boolean getDynamic() {
		return dynamic;
	}

	protected Boolean rotationAutoUpdate;
	/* (non-Javadoc)
	 * @see com.google.code.gwt.threejs.client.core.DimentionObject#setRotationAutoUpdate(java.lang.Boolean)
	 */
	@Override
	public void setRotationAutoUpdate(Boolean rotationAutoUpdate) {
		this.rotationAutoUpdate = rotationAutoUpdate;
	}
	/* (non-Javadoc)
	 * @see com.google.code.gwt.threejs.client.core.DimentionObject#getRotationAutoUpdate()
	 */
	@Override
	public Boolean getRotationAutoUpdate() {
		return rotationAutoUpdate;
	}

	protected String name;
	/* (non-Javadoc)
	 * @see com.google.code.gwt.threejs.client.core.DimentionObject#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}
	/* (non-Javadoc)
	 * @see com.google.code.gwt.threejs.client.core.DimentionObject#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	protected Vector3 scale;
	/* (non-Javadoc)
	 * @see com.google.code.gwt.threejs.client.core.DimentionObject#getScale()
	 */
	@Override
	public Vector3 getScale(){
		return this.scale;
	};
	/* (non-Javadoc)
	 * @see com.google.code.gwt.threejs.client.core.DimentionObject#setScale(com.google.code.gwt.threejs.client.core.Vector3)
	 */
	@Override
	public void setScale(Vector3 scale){
		this.scale = scale;
	};
	
	protected Vector3 up;
	/* (non-Javadoc)
	 * @see com.google.code.gwt.threejs.client.core.DimentionObject#getUp()
	 */
	@Override
	public Vector3 getUp(){
		return this.up;
	};
	/* (non-Javadoc)
	 * @see com.google.code.gwt.threejs.client.core.DimentionObject#setUp(com.google.code.gwt.threejs.client.core.Vector3)
	 */
	@Override
	public void setUp(Vector3 up){
		this.up = up;
	};
	
	protected Matrix4 matrix;
	/* (non-Javadoc)
	 * @see com.google.code.gwt.threejs.client.core.DimentionObject#getMatrix()
	 */
	@Override
	public Matrix4 getMatrix(){
		return this.matrix;
	};
	/* (non-Javadoc)
	 * @see com.google.code.gwt.threejs.client.core.DimentionObject#setMatrix(com.google.code.gwt.threejs.client.core.Matrix4)
	 */
	@Override
	public void setMatrix(Matrix4 matrix){
		this.matrix = matrix;
	};
	
	protected Matrix4 matrixWorld;
	/* (non-Javadoc)
	 * @see com.google.code.gwt.threejs.client.core.DimentionObject#getMatrixWorld()
	 */
	@Override
	public Matrix4 getMatrixWorld(){
		return this.matrixWorld;
	};
	/* (non-Javadoc)
	 * @see com.google.code.gwt.threejs.client.core.DimentionObject#setMatrixWorld(com.google.code.gwt.threejs.client.core.Matrix4)
	 */
	@Override
	public void setMatrixWorld(Matrix4 matrixWorld){
		this.matrixWorld = matrixWorld;
	};
	
	protected Matrix4 matrixRotationWorld;
	/* (non-Javadoc)
	 * @see com.google.code.gwt.threejs.client.core.DimentionObject#getMatrixRotationWorld()
	 */
	@Override
	public Matrix4 getMatrixRotationWorld(){
		return this.matrixRotationWorld;
	};
	/* (non-Javadoc)
	 * @see com.google.code.gwt.threejs.client.core.DimentionObject#setMatrixRotationWorld(com.google.code.gwt.threejs.client.core.Matrix4)
	 */
	@Override
	public void setMatrixRotationWorld(Matrix4 rotation){
		this.matrixRotationWorld = rotation;
	};
	
	protected Boolean matrixWorldNeedsUpdate;
	/* (non-Javadoc)
	 * @see com.google.code.gwt.threejs.client.core.DimentionObject#getMatrixWorldNeedsUpdate()
	 */
	@Override
	public Boolean getMatrixWorldNeedsUpdate(){
		return this.matrixWorldNeedsUpdate;
	};
	/* (non-Javadoc)
	 * @see com.google.code.gwt.threejs.client.core.DimentionObject#setMatrixWorldNeedsUpdate(java.lang.Boolean)
	 */
	@Override
	public void setMatrixWorldNeedsUpdate(Boolean needsUpdate){
		this.matrixWorldNeedsUpdate = needsUpdate;
	};
	
	protected Boolean matrixAutoUpdate;
	/* (non-Javadoc)
	 * @see com.google.code.gwt.threejs.client.core.DimentionObject#getMatrixAutoUpdate()
	 */
	@Override
	public Boolean getMatrixAutoUpdate(){
		return this.matrixAutoUpdate;
	};
	/* (non-Javadoc)
	 * @see com.google.code.gwt.threejs.client.core.DimentionObject#setMatrixAutoUpdate(java.lang.Boolean)
	 */
	@Override
	public void setMatrixAutoUpdate(Boolean autoUpdate){
		this.matrixAutoUpdate = autoUpdate;
	};
	
	protected Quaternion quaternion;
	/* (non-Javadoc)
	 * @see com.google.code.gwt.threejs.client.core.DimentionObject#getQuaternion()
	 */
	@Override
	public Quaternion getQuaternion(){
		return this.quaternion;
	};
	/* (non-Javadoc)
	 * @see com.google.code.gwt.threejs.client.core.DimentionObject#setQuaternion(com.google.code.gwt.threejs.client.core.Quaternion)
	 */
	@Override
	public void setQuaternion(Quaternion quaternion){
		this.quaternion = quaternion;
	};
	
	protected Boolean useQuaternion;
	/* (non-Javadoc)
	 * @see com.google.code.gwt.threejs.client.core.DimentionObject#getUseQuaternion()
	 */
	@Override
	public Boolean getUseQuaternion(){
		return this.useQuaternion;
	};
	/* (non-Javadoc)
	 * @see com.google.code.gwt.threejs.client.core.DimentionObject#setUseQuaternion(java.lang.Boolean)
	 */
	@Override
	public void setUseQuaternion(Boolean use){
		this.useQuaternion = use;
	};
	
	protected double boundRadius;
	/* (non-Javadoc)
	 * @see com.google.code.gwt.threejs.client.core.DimentionObject#getBoundRadius()
	 */
	@Override
	public double getBoundRadius(){
		return this.boundRadius;
	};
	/* (non-Javadoc)
	 * @see com.google.code.gwt.threejs.client.core.DimentionObject#setBoundRadius(double)
	 */
	@Override
	public void setBoundRadius(double boundRadius){
		this.boundRadius = boundRadius;
	};
	
	protected double boundRadiusScale;
	/* (non-Javadoc)
	 * @see com.google.code.gwt.threejs.client.core.DimentionObject#getBoundRadiusScale()
	 */
	@Override
	public double getBoundRadiusScale(){
		return this.boundRadiusScale;
	};
	/* (non-Javadoc)
	 * @see com.google.code.gwt.threejs.client.core.DimentionObject#setBoundRadiusScale(double)
	 */
	@Override
	public void setBoundRadiusScale(double scale){
		this.boundRadiusScale = scale;
	};
	
	protected Boolean visible;
	/* (non-Javadoc)
	 * @see com.google.code.gwt.threejs.client.core.DimentionObject#getVisible()
	 */
	@Override
	public Boolean getVisible(){
		return this.visible;
	};
	/* (non-Javadoc)
	 * @see com.google.code.gwt.threejs.client.core.DimentionObject#setVisible(java.lang.Boolean)
	 */
	@Override
	public void setVisible(Boolean visible){
		this.visible = visible;
	};
	
	private Vector3 vector;
	
	// Methods
	/* (non-Javadoc)
	 * @see com.google.code.gwt.threejs.client.core.DimentionObject#translate(double, com.google.code.gwt.threejs.client.core.Vector3)
	 */
	@Override
	public void translate(double distance, Vector3 axis){
		this.matrix.rotateAxis(axis);
		this.position.addSelf(axis.multiplyScalar(distance));
	}
	/* (non-Javadoc)
	 * @see com.google.code.gwt.threejs.client.core.DimentionObject#translateX(double)
	 */
	@Override
	public void translateX(double distance){
		this.translate(distance, this.vector.set(1,0,0));
	}
	/* (non-Javadoc)
	 * @see com.google.code.gwt.threejs.client.core.DimentionObject#translateY(double)
	 */
	@Override
	public void translateY(double distance){
		this.translate(distance, this.vector.set(0,1,0));
	}
	/* (non-Javadoc)
	 * @see com.google.code.gwt.threejs.client.core.DimentionObject#translateZ(double)
	 */
	@Override
	public void translateZ(double distance){
		this.translate(distance, this.vector.set(0,0,1));
	}
	/* (non-Javadoc)
	 * @see com.google.code.gwt.threejs.client.core.DimentionObject#lookAt(com.google.code.gwt.threejs.client.core.Vector3)
	 */
	@Override
	public void lookAt(Vector3 vector){
		// TODO: Add hierarchy support.
		this.matrix.lookAt(vector, this.position, this.up);
		if (this.rotationAutoUpdate){
			this.rotation.setRotationFromMatrix(this.matrix);
		}
	}
	/* (non-Javadoc)
	 * @see com.google.code.gwt.threejs.client.core.DimentionObject#addChild(E)
	 */
	@Override
	public <E extends DimentionObject> void addChild(E child){
		if (this.children.indexOf(child) == -1) {
			if(child.getParent() != null ) {
				child.getParent().removeChild(child);
			}

			child.setParent(this);
			this.children.add(child);

			// add to scene

			DimentionObject scene = this;

			while (scene.getParent() != null){
				scene = scene.getParent();
			}

			if (scene != null && scene.getClass() == Scene.class){
				((Scene)scene).addSceneItem(child);
			}
		}
	};
	/* (non-Javadoc)
	 * @see com.google.code.gwt.threejs.client.core.DimentionObject#removeChild(E)
	 */
	@Override
	public <E extends DimentionObject> void removeChild(E child){
		int index = this.children.indexOf(child);
		if (index != -1) {
			child.setParent(null);
			this.children.remove(index);
		}
	};
	/* (non-Javadoc)
	 * @see com.google.code.gwt.threejs.client.core.DimentionObject#updateMatrix()
	 */
	@Override
	public void updateMatrix(){
		this.matrix.setPosition(this.position);

		if (this.useQuaternion)  {
			this.matrix.setRotationFromQuaternion(this.quaternion);
		} else {
			this.matrix.setRotationFromEuler(this.rotation);
		}

		if ( this.scale.x != 1 || this.scale.y != 1 || this.scale.z != 1) {

			this.matrix.scale(this.scale);
			this.boundRadiusScale = Math.max(this.scale.x, Math.max( this.scale.y, this.scale.z));
		}

		this.matrixWorldNeedsUpdate = true;
	};
	/* (non-Javadoc)
	 * @see com.google.code.gwt.threejs.client.core.DimentionObject#update(com.google.code.gwt.threejs.client.core.Matrix4, java.lang.Boolean, com.google.code.gwt.threejs.client.cameras.Camera)
	 */
	@Override
	public void update(Matrix4 parentMatrixWorld, Boolean forceUpdate, Camera camera){
		if (this.matrixAutoUpdate){
			this.updateMatrix();
		}
		
		// update matrixWorld
		
		if (this.matrixWorldNeedsUpdate || forceUpdate) {

			if (parentMatrixWorld != null) {
				this.matrixWorld.multiply(parentMatrixWorld, this.matrix);
			} else {
				this.matrixWorld.copy(this.matrix);
			}
			this.matrixRotationWorld.extractRotation( this.matrixWorld, this.scale );
			this.matrixWorldNeedsUpdate = false;
			forceUpdate = true;
		}

		// update children

		for (int i = 0, l = this.children.size(); i < l; i++) {
			this.children.get(i).update(this.matrixWorld, forceUpdate, camera);

		}
	};
}
