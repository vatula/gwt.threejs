package com.google.code.gwt.threejs.client.materials;

import com.google.code.gwt.threejs.client.materials.enums.Shading;

public final class MeshShaderMaterial extends Material {
	
	static class MeshShaderMaterialOptions extends Material.MaterialOptions {
		public String fragmentShader = "void main(){}";
		public String vertexShader = "void main(){}";
		public Object uniforms = new Object(); // TODO guess the type
		public Object attributes = null;
		
		public Shading shading = Shading.SmoothShading;
		public boolean wireframe = false;
		public int wireframeLinewidth = 1;
		
		public boolean fog = false;
		public boolean lights = false;

		public boolean vertexColors = false;
		public boolean skinning = false;
		public boolean morphTargets = false;
	}
	
	private String fragmentShader;
	private String vertexShader;
	private Object uniforms; // TODO guess the type
	private Object attributes;
	
	private Shading shading;
	private boolean wireframe;
	private int wireframeLinewidth;
	
	private boolean fog;
	private boolean lights;

	private boolean vertexColors;
	private boolean skinning;
	private boolean morphTargets;

	public MeshShaderMaterial(MeshShaderMaterialOptions options){
		super(options);
		this.fragmentShader = options.fragmentShader;
		this.vertexShader = options.vertexShader;
		this.uniforms = options.uniforms;
		this.attributes = options.attributes;
		this.shading = options.shading;
		this.wireframe = options.wireframe;
		this.wireframeLinewidth = options.wireframeLinewidth;
		this.fog = options.fog;
		this.lights = options.lights;
		this.vertexColors = options.vertexColors;
		this.skinning = options.skinning;
		this.morphTargets = options.morphTargets;
	}

	public Shading getShading(){
		return this.shading;
	}

	public Boolean getWireframe(){
		return this.wireframe;
	}

	public double getWireframeLinewidth(){
		return this.wireframeLinewidth;
	}

	public Boolean getVertexColors(){
		return this.vertexColors;
	}

	public Boolean getSkinning(){
		return this.skinning;
	}

	public boolean isMorphTargets() {
		return morphTargets;
	}

	public String getFragmentShader() {
		return fragmentShader;
	}

	public String getVertexShader() {
		return vertexShader;
	}

	public Object getUniforms() {
		return uniforms;
	}

	public Object getAttributes() {
		return attributes;
	}

	public boolean isFog() {
		return fog;
	}

	public boolean isLights() {
		return lights;
	}
}
