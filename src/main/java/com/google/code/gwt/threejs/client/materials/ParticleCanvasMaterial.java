package com.google.code.gwt.threejs.client.materials;

import com.google.code.gwt.threejs.client.core.Color;

public final class ParticleCanvasMaterial extends Material {
	public static interface Program {
		<T>void run(T context, Color color);
	}
	static class ParticleCanvasMaterialOptions extends Material.MaterialOptions {
		public Color color = new Color(0xffffff);
		public Program program = new Program() {
			
			@Override
			public void run(Object context, Color color) {
				// TODO Auto-generated method stub
				
			}
		};
	}
	
	public ParticleCanvasMaterial(ParticleCanvasMaterialOptions options){
		super(options);
		this.color = options.color;
		this.program = options.program;
	}
	
	private Color color;
	private Program program;

	public Color getColor() {
		return color;
	}
	public Program getProgram() {
		return program;
	}
}
