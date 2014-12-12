package com.qait.mathlay.enums;

public enum USER_STATUS_TYPE {

	ONLINE("Online"), OFFLINE("Offline");
	
	String value;

	private USER_STATUS_TYPE(String val) {
		this.value = val;
	}
}
