package org.catspaw.cherubim.organization;


public interface Department {

	String getId();

	String getParentId();

	String getBranchId();
	
	String getName();
	
	int getLevel();
}
