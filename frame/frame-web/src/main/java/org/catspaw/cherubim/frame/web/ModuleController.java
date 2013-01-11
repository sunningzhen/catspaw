package org.catspaw.cherubim.frame.web;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.catspaw.cherubim.frame.FrameRepository;
import org.catspaw.cherubim.frame.model.Module;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Controller
public class ModuleController {

	@Inject
	private FrameRepository	repository;

	@ResponseBody
	@RequestMapping(value = "/frame/module/module.cmd", params = "method=getModules")
	public String getModules() throws Exception {
		List<Module> list = repository.findAllModules();
		JSONObject obj = new JSONObject();
		
		return JSON.toJSONString(list);
	}
}
