package com.google.code.gwt.threejs.client.materials.enums;

public enum Operation {
	MultiplyOperation(0),
	MixOperation(1);
	
	private final int operation;
	Operation(int operation){
		this.operation = operation;
	}
	public int getOperationType() {
		return operation;
	}
}
