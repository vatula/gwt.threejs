package com.google.code.gwt.threejs.client.materials.enums;

public enum Filter {
	NearestFilter(0),
	NearestMipMapNearestFilter(1),
	NearestMipMapLinearFilter(2),
	LinearFilter(3),
	LinearMipMapNearestFilter(4),
	LinearMipMapLinearFilter(5);
	
	private final int filter;
	Filter(int filter){
		this.filter = filter;
	}
	public int getFilterType() {
		return filter;
	}
}
