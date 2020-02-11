package com.origin.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.origin.ben.Form;
import com.origin.entity.User;
import com.origin.service.FormService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/interface")
public class InterfaceController {
	
	@Autowired
	public FormService formService = null;
//	,method = {RequestMethod.POST},produces="application/json;charset=UTF-8"
	@RequestMapping(value="/create")
	public void form1(Form form,HttpServletResponse response) throws Exception {
		User principal = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("id:"+principal.getUsername());
		String time = String.valueOf(new Date().getTime());
		File file = new File("c:\\tmp\\" + time);
		if (!file.exists()) {
			file.mkdirs();
		}
		form.setPath("c:\\tmp\\" + time);
		form.setUserName(principal.getUsername());
		form.setCreateDate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
		System.out.println("form1:" + form.toString());
		Form bigestid = formService.selectBigestForm();
		//编码规则
		if(bigestid!=null && bigestid.getId() != null && bigestid.getId().length()==12) {
			System.out.println("bigestid:" + bigestid.getId());
			int num = Integer.valueOf(bigestid.getId().replaceAll("DZ", "").replace("0", "0"))+1;
			int len = String.valueOf(num).length();
			String tmpid = "DZ";
			for(int i=0;i<10-len;i++) {
				tmpid = tmpid + "0";
			}
			tmpid = tmpid + num;
			form.setId(tmpid);
		}else {
			form.setId("DZ0000000001");
		}
		formService.insertForm(form);
		form.setWeight_0("cpx=" + form.getCpx());
		form.setWeight_0("cpxh=" + form.getCpxh());
		form.setWeight_0("cpbm=" + form.getCpbm());
		form.setWeight_0("pl=" + form.getPl());
		form.setWeight_0("weight_0=" + form.getWeight_0());
		form.setWeight_1("weight_1=" + form.getWeight_1());
		form.setWidth("width=" + form.getWidth());
		form.setLength("length=" + form.getLength());
		form.setTq("tq=" + form.getTq());
		form.setWidth_x("width_x=" + form.getWidth_x());
		form.setHigh_x("high_x=" + form.getHigh_x());
		form.setWidth_0("width_0=" + form.getWidth_0());
		form.setF0("f0=" + form.getF0());
		form.setWeight_2("weight_2=" + form.getWeight_2());
		form.setLength_1("length_1=" + form.getLength_1());
		form.setWidth_1("width_1=" + form.getWidth_1());
		form.setDoor_h("door_h=" + form.getDoor_h());
		form.setGl("gl=" + form.getGl());
		form.setY_50("y_50=" + form.getY_50());
		form.setMtuk("mtuk=" + form.getMtuk());
		form.setB_y("b_y=" + form.getB_y());
		form.setYjsd("yjsd=" + form.getYjsd());
		form.setGl5("gl5=" + form.getGl5());
		form.setUkjlh("ukjlh=" + form.getUkjlh());
		form.setDguk("dguk=" + form.getDguk());
		form.setDgtq("dgtq=" + form.getDgtq());
		form.setWeight_0("dbsfytq=" + form.getDbsfytq());
		JSONObject json = JSONObject.fromObject(form);
		System.out.println("json:" + json);
		// 调用程序
		try {
			String cmd = "d:\\JSONTest.exe" + " \"" + json.toString().replaceAll("\"", "'") + "\"";
			System.out.println("cmd:" + cmd);
			Process process = Runtime.getRuntime().exec(cmd);
		} catch (Exception e) {
			throw new Exception("文件下载失败");
		}
		File[] files = file.listFiles();
		if (files != null && files.length > 0) {
			response.addHeader("Content-Disposition", "attachment;fileName=" + files[0].getName());
			response.setContentType("application/force-download");// 设置强制下载不打开            
			byte[] buffer = new byte[1024];
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			try {
				fis = new FileInputStream(files[0]);
				bis = new BufferedInputStream(fis);
				OutputStream outputStream = response.getOutputStream();
				int i = bis.read(buffer);
				while (i != -1) {
					outputStream.write(buffer, 0, i);
					i = bis.read(buffer);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (bis != null) {
					try {
						bis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}else {
			throw new Exception("文件下载失败");
		}
		//return "form1";
	}
	
	@RequestMapping(value="/selectAll",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ArrayList<Form> selectAll(String aa){
		return formService.selectAll();
	}
	
	@RequestMapping(value="/downloadFiletest",method = {RequestMethod.POST})
    public ResponseEntity<byte[]> downloadFile(@PathVariable(required = false) Integer id) throws IOException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(new File("C:\\tmp\\1580567400509\\login.png")),
                httpHeaders, HttpStatus.OK);
    }
}
