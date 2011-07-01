package com.google.code.gwt.threejs.client.objects;

import java.util.HashMap;
import java.util.List;

import com.google.code.gwt.threejs.client.core.DimentionObject;
import com.google.code.gwt.threejs.client.core.Geometry;
import com.google.code.gwt.threejs.client.core.Object3D;
import com.google.code.gwt.threejs.client.core.SidesObject;
import com.google.code.gwt.threejs.client.materials.Material;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gwt.core.client.GWT;

public class Mesh extends Object3D implements SidesObject {
	private Geometry geometry;
	private List<Material> materials;
	private Boolean flipSided;
	private Boolean doubleSided;
	private Boolean overdraw;
	private Integer morphTargetBase = null;
	private List<Integer> morphTargetInfluences;
	private HashMap<String, Integer> morphTargetDictionary;
	
	public Mesh(Geometry geometry, Material material){
		this(geometry, Lists.newArrayList(material));
	}
	public Mesh(Geometry geometry, List<Material> materials){
		super();
		this.morphTargetInfluences = Lists.newArrayList();
		this.geometry = geometry;
		this.materials = materials;
		this.flipSided = false;
		this.doubleSided = false;
		this.overdraw = false; // TODO: Move to material?
		if (this.geometry != null){
			// calc bound radius
			if(this.geometry.getBoundingSphere() == null){
				this.geometry.computeBoundingSphere();
			}
			this.boundRadius = this.geometry.getBoundingSphere().radius;
			//GWT.log("Sphere radius: "+this.boundRadius);
			// setup morph targets
			if(this.geometry.getMorphTargets().size() != 0) {

				this.morphTargetBase = -1;
				//this.morphTargetForcedOrder = Lists.newArrayList();
				this.morphTargetInfluences = Lists.newArrayList();
				this.morphTargetDictionary = Maps.newHashMap();

				List<DimentionObject> morphTargets = this.geometry.getMorphTargets();
				for(int m = 0; m < morphTargets.size(); m++) {
					this.morphTargetInfluences.add(0);
					this.morphTargetDictionary.put(morphTargets.get(m).getName(), m);
				}
			}
		}
	}

	// Properties
	
	public Geometry getGeometry(){
		return this.geometry;
	};
	public void setGeometry(Geometry geometry){
		this.geometry = geometry;
	};
	
	public List<Material> getMaterials(){
		return this.materials;
	};
	public void setMaterials(List<Material> materials){
		this.materials = materials;
	};
	
	public Boolean getFlipSided(){
		return this.flipSided;
	};
	public void setFlipSided(Boolean flipSided){
		this.flipSided = flipSided;
	};
	
	public Boolean getDoubleSided(){
		return this.doubleSided;
	};
	public void setDoubleSided(Boolean doubleSided){
		this.doubleSided = doubleSided;
	};
	
	public Boolean getOverdraw(){
		return this.overdraw;
	};
	public void setOverdraw(Boolean overdraw){
		this.overdraw = overdraw;
	};
	
	// Methods
	
	/**
	 * Get Morph Target Index by Name
	 */
	public int getMorphTargetIndexByName(String name){
		if (this.morphTargetDictionary.containsKey(name)) {
			return this.morphTargetDictionary.get(name);
		}
		GWT.log("Mesh.getMorphTargetIndexByName: morph target " + name + " does not exist. Returning 0.");
		return 0;
	}

	public Integer getMorphTargetBase() {
		return morphTargetBase;
	}
}
