package com.google.code.gwt.threejs.client.extras;

import java.util.List;

import com.google.code.gwt.threejs.client.materials.Texture;
import com.google.code.gwt.threejs.client.materials.enums.Mapping;
import com.google.common.collect.Lists;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.user.client.ui.Image;

public final class ImageUtils {
	static interface Callback {
		void run(Image image);
	}
	public ImageUtils(){
		// Required for overlay types
	}
	
	// Methods
	
	public static Texture loadTexture(String path, Mapping mapping, final Callback callback){
		Image image = new Image();
		final Texture texture = new Texture(Lists.newArrayList(image), mapping, null, null, null, null);
		image.addLoadHandler(new LoadHandler() {
			
			@Override
			public void onLoad(LoadEvent event) {
				texture.setNeedsUpdate(true);
				if (callback != null){
					callback.run((Image)event.getSource());
				}
			}
		});
		image.setUrl(path);
		return texture;
	}
	
	public static Texture loadTextureCube(List<String> paths, Mapping mapping, final Callback callback){
		List<Image> images = Lists.newArrayList();
		final Texture texture = new Texture(images, mapping, null, null, null, null);
		
		LoadHandler loadHandler = new LoadHandler() {
			int loaded = 0;
			@Override
			public void onLoad(LoadEvent event) {
				loaded++;
				if (loaded == 6){
					texture.setNeedsUpdate(true);
				}
				if (callback != null){
					callback.run((Image)event.getSource());
				}
			}
		};
		
		for(String path : paths){
			Image img = new Image(); 
			images.add(img);
			img.addLoadHandler(loadHandler);
			img.setUrl(path);
		}
		
		return texture;
	};
}
