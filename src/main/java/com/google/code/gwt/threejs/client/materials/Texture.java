package com.google.code.gwt.threejs.client.materials;

import java.util.List;

import com.google.code.gwt.threejs.client.core.Vector2;
import com.google.code.gwt.threejs.client.materials.enums.Filter;
import com.google.code.gwt.threejs.client.materials.enums.Mapping;
import com.google.code.gwt.threejs.client.materials.enums.Wrapping;
import com.google.common.collect.Lists;
import com.google.gwt.user.client.ui.Image;

public final class Texture {
	
	private List<Image> images;
	private Mapping mapping;
	private Wrapping wrapS;
	private Wrapping wrapT;
	private Filter magFilter;
	private Filter minFilter;
	private Boolean needsUpdate;
	private Vector2 offset;
	private Vector2 repeat;

	public Texture(Image image){
		this(Lists.newArrayList(image), null, null, null, null, null);
	}
	
	public Texture(List<Image> images, Mapping mapping, Wrapping wrapS, Wrapping wrapT, Filter magFilter, Filter minFilter){
		if (images != null){
			this.images = images;
		} else {
			this.images = Lists.newArrayList();
		}
		this.mapping = mapping == null ? Mapping.UVMapping : mapping;
		this.wrapS = wrapS == null ? Wrapping.ClampToEdgeWrapping : wrapS;
		this.wrapT = wrapT == null ? Wrapping.ClampToEdgeWrapping : wrapT;
		this.magFilter = magFilter == null ? Filter.LinearFilter : magFilter;
		this.minFilter = minFilter == null ? Filter.LinearMipMapLinearFilter : minFilter;
		
		this.offset = new Vector2(0,0);
		this.repeat = new Vector2(1,1);
		
		this.needsUpdate = false;
	}
	// Properties
	
	public Mapping getMapping(){
		return this.mapping;
	}

	public Wrapping getWrapS(){
		return this.wrapS;
	}

	public Wrapping getWrapT(){
		return this.wrapT;
	}

	public Filter getMagFilter(){
		return this.magFilter;
	}

	public Filter getMinFilter(){
		return this.minFilter;
	}
	
	public Boolean getNeedsUpdate(){
		return this.needsUpdate;
	}
	
	public void setNeedsUpdate(Boolean needsUpdate){
		this.needsUpdate = needsUpdate;
	}

	// Methods
	
	public void setOffset(Vector2 offset) {
		this.offset = offset;
	}

	public Vector2 getOffset() {
		return offset;
	}

	public void setRepeat(Vector2 repeat) {
		this.repeat = repeat;
	}

	public Vector2 getRepeat() {
		return repeat;
	}
	
	public List<Image> getAllImages(){
		return this.images;
	}
	
	public Image getImage(){
		return this.images.size() > 0 ? this.images.get(0) : null;
	}
	
	public void addImages(Image... images){
		for (int i = 0; i < images.length; i++){
			this.images.add(images[i]);
		}
	}

	public Texture clone(){
		return new Texture(this.images, this.mapping, this.wrapS, this.wrapT, this.magFilter, this.minFilter);
	}
}
