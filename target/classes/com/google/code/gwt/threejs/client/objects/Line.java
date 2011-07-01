package com.google.code.gwt.threejs.client.objects;

import java.util.List;

import com.google.code.gwt.threejs.client.core.Geometry;
import com.google.code.gwt.threejs.client.core.GeometryObject;
import com.google.code.gwt.threejs.client.core.Object3D;
import com.google.code.gwt.threejs.client.materials.Material;
import com.google.common.collect.Lists;

public class Line extends Object3D implements GeometryObject {
	
	static enum LineType {
		LineStrip(0),
		LinePieces(1);
		
		private final int type;
		LineType(int type){
			this.type = type;
		}
		public int getLineType() {
			return type;
		}
	}
	
	public Line(Geometry geometry, Material material, LineType type){
		this(geometry, Lists.newArrayList(material), type);
	}
	
	public Line(Geometry geometry, List<Material> materials, LineType type){
		super();
		this.geometry = geometry;
		this.materials = materials;
		this.type = type == null ? LineType.LineStrip : type;
	}
	
	private LineType type;
	private List<Material> materials;
	private Geometry geometry;

	@Override
	public Geometry getGeometry() {
		// TODO Auto-generated method stub
		return this.geometry;
	}

	@Override
	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	public void setMaterials(List<Material> materials) {
		this.materials = materials;
	}

	public List<Material> getMaterials() {
		return materials;
	}

	public void setType(LineType type) {
		this.type = type;
	}

	public LineType getType() {
		return type;
	}

}
