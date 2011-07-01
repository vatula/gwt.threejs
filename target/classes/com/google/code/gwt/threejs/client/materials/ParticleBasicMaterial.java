package com.google.code.gwt.threejs.client.materials;

import com.google.code.gwt.threejs.client.core.Color;

public final class ParticleBasicMaterial extends Material {
	
	static class ParticleBasicMaterialOptions extends Material.MaterialOptions {
		public Color color = new Color(0xffffff);
		public Texture map = null;
		public int size = 1;
		public boolean sizeAttenuation = true;
		public boolean vertexColors = false;
	}
	
	private Color color;
	private Texture map;
	private int size;
	private boolean sizeAttenuation;
	private boolean vertexColors;

	public ParticleBasicMaterial(ParticleBasicMaterialOptions options){
		super(options);
		this.color = options.color;
		this.map = options.map;
		this.size = options.size;
		this.sizeAttenuation = options.sizeAttenuation;
		this.vertexColors = options.vertexColors;
	}

	public Color getColor(){
		return this.color;
	}
	
	public Texture getMap(){
		return this.map;
	}
	
	public Boolean getVertexColors(){
		return this.vertexColors;
	}

	public int getSize() {
		return size;
	}

	public boolean isSizeAttenuation() {
		return sizeAttenuation;
	}

}
