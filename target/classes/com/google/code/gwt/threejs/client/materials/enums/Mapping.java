package com.google.code.gwt.threejs.client.materials.enums;

public enum Mapping {
	CubeReflectionMapping(0),
	CubeRefractionMapping(1),
	LatitudeReflectionMapping(2),
	LatitudeRefractionMapping(3),
	SphericalReflectionMapping(4),
	SphericalRefractionMapping(5),
	UVMapping(6);
	
	private final int mapping;
	Mapping(int mapping){
		this.mapping = mapping;
	}
	public int getMappingType() {
		return mapping;
	}
}
