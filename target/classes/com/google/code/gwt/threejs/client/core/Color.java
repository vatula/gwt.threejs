package com.google.code.gwt.threejs.client.core;

public final class Color {
	protected int hex;
	protected int r, g, b;
	protected Boolean autoUpdate;
	public Color(int hex){
		this.setHex(hex);
	}

	// Properties
	
	public void setHex(int hex) {
		this.hex = (~~hex) & 0xffffff;
		this.updateRGB();
	};
	public int getHex() {
		return this.hex;
	};
	
	public void setR(int r) {
		this.r = r;
	};
	public int getR() {
		return this.r;
	};

	public void setG(int g) {
		this.g = g;
	};
	public int getG() {
		return this.g;
	};

	public void setB(int b) {
		this.b = b;
	};
	public int getB() {
		return this.b;
	};
	
	public void setAutoUpdate(boolean autoUpdate) {
		this.autoUpdate = autoUpdate;
	};
	public boolean getAutoUpdate() {
		return this.autoUpdate;
	};
	
	// Methods
	
	public void copy(Color color) {
		this.copy(color);
	};
	public void setRGB(int r, int g, int b) {
		this.r = r;
		this.g = g;
		this.b = b;
	};
	public void setHSV(int h, int s, int v) {
		// based on MochiKit implementation by Bob Ippolito
		// h,s,v ranges are < 0.0 - 1.0 >

		int r = 0, g = 0, b = 0, i, f, p, q, t;

		if (v == 0.0) {

			r = g = b = 0;

		} else {

			i = (int) Math.floor(h*6);
			f = (h * 6) - i;
			p = v * (1 - s);
			q = v * (1 - (s * f));
			t = v * (1 - (s * (1 - f)));

			switch (i) {

				case 1: r = q; g = v; b = p; break;
				case 2: r = p; g = v; b = t; break;
				case 3: r = p; g = q; b = v; break;
				case 4: r = t; g = p; b = v; break;
				case 5: r = v; g = p; b = q; break;
				case 6: // fall through
				case 0: r = v; g = t; b = p; break;

			}

		}

		this.setRGB(r,g,b);
	};
	public void updateHex() {
		this.hex = ~~(this.r*255 ) << 16 ^~~(this.g*255 ) << 8 ^~~ (this.b*255);
	};
	public void updateRGB() {
		this.r = (this.hex >> 16 & 255)/255;
		this.g = (this.hex >> 8 & 255)/255;
		this.b = (this.hex & 255)/255;
	};
	
	public Color clone() {
		return new Color(this.hex);
	};
}
