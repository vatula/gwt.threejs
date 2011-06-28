package com.google.code.gwt.threejs.client.core;

public interface SidesObject extends GeometryObject {
	Boolean getFlipSided();
	void setFlipSided(Boolean flipSided);
	
	Boolean getDoubleSided();
	void setDoubleSided(Boolean doubleSided);
}
