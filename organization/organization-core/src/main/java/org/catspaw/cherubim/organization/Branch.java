package org.catspaw.cherubim.organization;

public interface Branch {

	String getId();

	String getParentId();

	String getName();
	
	int getLevel();
}
