package com.google.code.gwt.threejs.client.core;

import com.google.gwt.core.client.JavaScriptObject;

public class SimpleJson extends JavaScriptObject {
	protected SimpleJson(){
		// Required for overlay types
	}
	final static native SimpleJson create()/*-{
		return {};
	}-*/;
	final public native void add(String key, int value)/*-{
		this[key] = value;
	}-*/;
	final public native void add(String key, double value)/*-{
		this[key] = value;
	}-*/;
	final public native void add(String key, String value)/*-{
		this[key] = value;
	}-*/;
	final public native void add(String key, JavaScriptObject value)/*-{
		this[key] = value;
	}-*/;
	final public native <E extends Object> E get(String key)/*-{
		return this[key];
	}-*/;
}
