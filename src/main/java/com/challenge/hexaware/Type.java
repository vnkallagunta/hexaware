package com.challenge.hexaware;

public enum Type {
	INTEGER((byte)0),
	WORD((byte)1);
	
	byte value;
	Type(byte b) {
		value = b;
	}
	
	public byte type() {
		return value;
	}
}
