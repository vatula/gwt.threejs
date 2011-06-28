package com.google.code.gwt.threejs.client.events;

import com.google.gwt.dom.client.ImageElement;

public interface ImageUploadHandler {
	void onImageUploaded(ImageUploadEvent event);
	
	static final class ImageUploadEvent {
		private final ImageElement image;
		
		public ImageUploadEvent(ImageElement image){
			this.image = image;
		}
		
		public ImageElement getImage(){
			return this.image;
		}
	}
}
