package com.google.code.gwt.threejs.client.materials;

import com.google.code.gwt.threejs.client.core.Color;
import com.google.gwt.canvas.dom.client.Context2d.LineCap;
import com.google.gwt.canvas.dom.client.Context2d.LineJoin;

public final class LineBasicMaterial extends Material {

	static class LineBasicMaterialOptions extends Material.MaterialOptions {
		public Color color = new Color(0xffffff);
		public int linewidth = 1;
		public LineCap linecap = LineCap.ROUND;
		public LineJoin linejoin = LineJoin.ROUND;
		public boolean vertexColors = false;
	}
	
	private Color color;
	private int linewidth;
	private LineCap linecap;
	private LineJoin linejoin;
	private boolean vertexColors;
	
	public LineBasicMaterial(LineBasicMaterialOptions options){
		super(options);
		this.color = options.color;
		this.linewidth = options.linewidth;
		this.linecap = options.linecap;
		this.linejoin = options.linejoin;
		this.vertexColors = options.vertexColors;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public void setLinewidth(int linewidth) {
		this.linewidth = linewidth;
	}

	public int getLinewidth() {
		return linewidth;
	}

	public void setLinecap(LineCap linecap) {
		this.linecap = linecap;
	}

	public LineCap getLinecap() {
		return linecap;
	}

	public void setLinejoin(LineJoin linejoin) {
		this.linejoin = linejoin;
	}

	public LineJoin getLinejoin() {
		return linejoin;
	}

	public void setVertexColors(boolean vertexColors) {
		this.vertexColors = vertexColors;
	}

	public boolean isVertexColors() {
		return vertexColors;
	}
}
