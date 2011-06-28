package com.google.code.gwt.threejs.client.core;

import java.util.Collection;
import java.util.List;

import com.google.code.gwt.threejs.client.cameras.Camera;

public interface DimentionObject {

	public abstract DimentionObject getParent();

	public abstract void setParent(DimentionObject parent);

	public abstract List<DimentionObject> getChildren();

	public abstract void setChildren(Collection<? extends DimentionObject> children);

	public abstract Vector3 getPosition();

	public abstract void setPosition(Vector3 position);

	public abstract Vector3 getRotation();

	public abstract void setRotation(Vector3 rotation);

	public abstract void setDynamic(Boolean dynamic);

	public abstract Boolean getDynamic();

	public abstract void setRotationAutoUpdate(Boolean rotationAutoUpdate);

	public abstract Boolean getRotationAutoUpdate();

	public abstract void setName(String name);

	public abstract String getName();

	public abstract Vector3 getScale();

	public abstract void setScale(Vector3 scale);

	public abstract Vector3 getUp();

	public abstract void setUp(Vector3 up);

	public abstract Matrix4 getMatrix();

	public abstract void setMatrix(Matrix4 matrix);

	public abstract Matrix4 getMatrixWorld();

	public abstract void setMatrixWorld(Matrix4 matrixWorld);

	public abstract Matrix4 getMatrixRotationWorld();

	public abstract void setMatrixRotationWorld(Matrix4 rotation);

	public abstract Boolean getMatrixWorldNeedsUpdate();

	public abstract void setMatrixWorldNeedsUpdate(Boolean needsUpdate);

	public abstract Boolean getMatrixAutoUpdate();

	public abstract void setMatrixAutoUpdate(Boolean autoUpdate);

	public abstract Quaternion getQuaternion();

	public abstract void setQuaternion(Quaternion quaternion);

	public abstract Boolean getUseQuaternion();

	public abstract void setUseQuaternion(Boolean use);

	public abstract double getBoundRadius();

	public abstract void setBoundRadius(double boundRadius);

	public abstract double getBoundRadiusScale();

	public abstract void setBoundRadiusScale(double scale);

	public abstract Boolean getVisible();

	public abstract void setVisible(Boolean visible);

	// Methods
	public abstract void translate(double distance, Vector3 axis);

	public abstract void translateX(double distance);

	public abstract void translateY(double distance);

	public abstract void translateZ(double distance);

	public abstract void lookAt(Vector3 vector);

	public abstract <E extends DimentionObject> void addChild(E child);

	public abstract <E extends DimentionObject> void removeChild(E child);

	public abstract void updateMatrix();

	public abstract void update(Matrix4 parentMatrixWorld, Boolean forceUpdate,
			Camera camera);

}