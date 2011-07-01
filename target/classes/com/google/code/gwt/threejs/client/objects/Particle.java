package com.google.code.gwt.threejs.client.objects;

import java.util.List;

import com.google.code.gwt.threejs.client.core.Object3D;
import com.google.code.gwt.threejs.client.materials.Material;
import com.google.common.collect.Lists;

public final class Particle extends Object3D {
	private List<Material> materials;
	
	public Particle(Material material){
		this(Lists.newArrayList(material));
	}
	public Particle(List<Material> materials){
		super();
		this.materials = materials;
	}
	
	public void setMaterials(List<Material> materials) {
		this.materials = materials;
	}

	public List<Material> getMaterials() {
		return materials;
	}

}
