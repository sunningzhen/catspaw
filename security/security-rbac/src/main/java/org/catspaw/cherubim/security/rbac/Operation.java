package org.catspaw.cherubim.security.rbac;

/**
 * 操作，如增删改查、打印、审核等
 * @author sunnz
 */
public interface Operation extends Action {

	String getActionString();
}
