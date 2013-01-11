package org.catspaw.cherubim.frame;

import java.util.List;

import org.catspaw.cherubim.frame.model.Menu;
import org.catspaw.cherubim.frame.model.Module;

public interface FrameRepository {

	List<Module> findAllModules();

	List<Menu> findTopMenus(String moduleCode);

	List<Menu> findChildMenus(String parentMenuId);
}
