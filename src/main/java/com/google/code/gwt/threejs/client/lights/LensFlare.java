package com.google.code.gwt.threejs.client.lights;

import java.util.List;
import com.google.common.collect.Lists;

import com.google.code.gwt.threejs.client.core.Object3D;
import com.google.code.gwt.threejs.client.core.Vector3;
import com.google.code.gwt.threejs.client.materials.Texture;
import com.google.code.gwt.threejs.client.materials.enums.Blending;

public final class LensFlare extends Object3D {
	final class Light {
		public Texture texture;
		public int size;
		public int distance;
		public Blending blending;
		public double x, y, z;
		public int scale;
		public double rotation;
		public double wantedRotation;
		public double opacity;
		
		public Light(Texture texture, int size, int distance, Blending blending, int x, int y, int z, int scale, double rotation, double opacity){
			this.texture = texture;
			this.size = size;
			this.distance = distance;
			this.blending = blending;
			this.x = x;
			this.y = y;
			this.z = z;
			this.rotation = rotation;
			this.opacity = opacity;
		}
	}
	
	private Vector3 positionScreen;
	private List<Light> lensFlares;
	private Object customUpdateCallback;
	
	public LensFlare(){
		this.positionScreen = new Vector3();
		this.lensFlares = Lists.newArrayList();
		this.customUpdateCallback = null;
	}
	
	public LensFlare(Texture texture, Integer size, Integer distance, Blending blending){
		this();
		if (texture != null){
			this.add(texture, size, distance, blending);
		}
	}
	
	public void add(Texture texture, Integer size, Integer distance, Blending blending){
		int s = size == null ? -1 : size.intValue();
		int d = distance == null ? 0 : distance.intValue();
		Blending b = blending == null ? Blending.BillboardBlending : blending;
		
		d = Math.min(d, Math.max(0, d));
		this.lensFlares.add(new Light(texture, s, d, b, 0, 0, 0, 1, 1, 1));
	}
	
	/*
	 * Update lens flares update positions on all flares based on the screen position
	 * Set myLensFlare.customUpdateCallback to alter the flares in your project specific way.
	 */
	public void updateLensFlares(){
		double vecX = -this.positionScreen.getX() * 2;
		double vecY = -this.positionScreen.getY() * 2; 

		for(Light flare : this.lensFlares){
			flare.x = this.positionScreen.getX() + vecX * flare.distance;
			flare.y = this.positionScreen.getY() + vecY * flare.distance;

			flare.wantedRotation = flare.x * Math.PI * 0.25;
			flare.rotation += (flare.wantedRotation - flare.rotation) * 0.25;
		}
	}

	public Object getCustomUpdateCallback() {
		return customUpdateCallback;
	}
}
