package org.catspaw.cherubim.frame.web;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.catspaw.cherubim.frame.model.Module;
import org.catspaw.cherubim.frame.service.ModuleService;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Controller
public class ModuleController {

	@Inject
	private ModuleService	moduleService;

	@ResponseBody
	@RequestMapping(value = "/frame/module/module.cmd", params = "method=getModules")
	public String getModules(@RequestParam("page") int page, @RequestParam("rows") int rows,
			@RequestParam("sidx") String sidx, @RequestParam("sord") String sord) throws Exception {
		Pageable pageable = new PageRequest(page - 1, rows, Direction.fromString(sord), sidx);
		Page<Module> modulePage = moduleService.findModules(pageable);
		JSONObject obj = new JSONObject();
		obj.put("page", page);
		obj.put("rows", rows);
		obj.put("sidx", sidx);
		obj.put("sord", sord);
		obj.put("total", modulePage.getTotalElements());
		obj.put("data", modulePage.getContent());
		return JSON.toJSONString(obj);
	}
}
