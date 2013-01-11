package org.catspaw.cherubim.frame.web;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.catspaw.cherubim.frame.FrameRepository;
import org.catspaw.cherubim.frame.model.Menu;

import com.alibaba.fastjson.JSON;

@Controller
public class TreeController {

	@Inject
	private FrameRepository	repository;

	@ResponseBody
	@RequestMapping(value = "/tree.cmd", params = "method=getMenus")
	public String getMenus(@RequestParam("moduleCode") String moduleCode,
			@RequestParam(value = "parentId", required = false) String parentId) throws Exception {
		List<Menu> menus;
		if (parentId == null) {
			menus = repository.findTopMenus(moduleCode);
		} else {
			menus = repository.findChildMenus(parentId);
		}
		return JSON.toJSONString(menus);
	}
}
