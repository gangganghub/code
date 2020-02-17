package com.origin.web;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.origin.ben.Form;
import com.origin.service.FormService;

import net.sf.json.JSONObject;

@RestController
@RequestMapping("/interface")
public class WebController {

	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String getFoo(@RequestParam("test") String test) {
		return "i'm foo" + test + ", " + UUID.randomUUID().toString();
	}

	@Autowired
	public FormService formService = null;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public void form1(@RequestBody Form form, HttpServletResponse response) throws Exception {
		//User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "";
		if (principal instanceof UserDetails) {
		  username = ((UserDetails)principal).getUsername();
		} else {
		  username = principal.toString();
		}
		System.out.println("username:" + username);
		String time = String.valueOf(new Date().getTime());
		form.setPath("c:\\tmp\\" + time);
		form.setUserName(username);
		form.setCreateDate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
		System.out.println("form1:" + form.toString());
		Form bigestid = formService.selectBigestForm();
		// 编码规则
		if (bigestid != null && bigestid.getId() != null && bigestid.getId().length() == 12) {
			System.out.println("bigestid:" + bigestid.getId());
			int num = Integer.valueOf(bigestid.getId().replaceAll("DZ", "").replace("0", "0")) + 1;
			int len = String.valueOf(num).length();
			String tmpid = "DZ";
			for (int i = 0; i < 10 - len; i++) {
				tmpid = tmpid + "0";
			}
			tmpid = tmpid + num;
			form.setId(tmpid);
		} else {
			form.setId("DZ0000000001");
		}
		formService.insertForm(form);
		if (form.getWeight_0() != null && form.getWeight_0().length() > 0) {
			form.setWeight_0("weight_0=" + form.getWeight_0());
		}
		if (form.getWeight_1() != null && form.getWeight_1().length() > 0) {
			form.setWeight_1("weight_1=" + form.getWeight_1());
		}
		if (form.getWidth() != null && form.getWidth().length() > 0) {
			form.setWidth("width=" + form.getWidth());
		}
		if (form.getLength() != null && form.getLength().length() > 0) {
			form.setLength("length=" + form.getLength());
		}
		if (form.getTq() != null && form.getTq().length() > 0) {
			form.setTq("tq=" + form.getTq());
		}
		if (form.getWidth_x() != null && form.getWidth_x().length() > 0) {
			form.setWidth_x("width_x=" + form.getWidth_x());
		}
		if (form.getHigh_x() != null && form.getHigh_x().length() > 0) {
			form.setHigh_x("high_x=" + form.getHigh_x());
		}
		if (form.getWidth_0() != null && form.getWidth_0().length() > 0) {
			form.setWidth_0("width_0=" + form.getWidth_0());
		}
		if (form.getF0() != null && form.getF0().length() > 0) {
			form.setF0("f0=" + form.getF0());
		}
		if (form.getWeight_2() != null && form.getWeight_2().length() > 0) {
			form.setWeight_2("weight_2=" + form.getWeight_2());
		}
		if (form.getLength_1() != null && form.getLength_1().length() > 0) {
			form.setLength_1("length_1=" + form.getLength_1());
		}
		if (form.getWidth_1() != null && form.getWidth_1().length() > 0) {
			form.setWidth_1("width_1=" + form.getWidth_1());
		}
		if (form.getDoor_h() != null && form.getDoor_h().length() > 0) {
			form.setDoor_h("door_h=" + form.getDoor_h());
		}
		if (form.getGl() != null && form.getGl().length() > 0) {
			form.setGl("gl=" + form.getGl());
		}
		if (form.getY_50() != null && form.getY_50().length() > 0) {
			form.setY_50("y_50=" + form.getY_50());
		}
		if (form.getMtuk() != null && form.getMtuk().length() > 0) {
			form.setMtuk("mtuk=" + form.getMtuk());
		}
		if (form.getB_y() != null && form.getB_y().length() > 0) {
			form.setB_y("b_y=" + form.getB_y());
		}
		if (form.getYjsd() != null && form.getYjsd().length() > 0) {
			form.setYjsd("yjsd=" + form.getYjsd());
		}
		if (form.getGl5() != null && form.getGl5().length() > 0) {
			form.setGl5("gl5=" + form.getGl5());
		}
		if (form.getUkjlh() != null && form.getUkjlh().length() > 0) {
			form.setUkjlh("ukjlh=" + form.getUkjlh());
		}
		if (form.getDguk() != null && form.getDguk().length() > 0) {
			form.setDguk("dguk=" + form.getDguk());
		}
		if (form.getDgtq() != null && form.getDgtq().length() > 0) {
			form.setDgtq("dgtq=" + form.getDgtq());
		}
		File file = new File("c:\\tmp\\" + time);
		if (!file.exists()) {
			file.mkdirs();
		}
		JSONObject json = JSONObject.fromObject(form);
		System.out.println("json:" + json);
		// 调用程序
		if(!new File("c:\\JSONTest.bat").exists()) {
			file = new File("c:\\tmp\\test");
		}else {
			try {
				String cmd = "c:\\JSONTest.bat" + " \"" + json.toString().replaceAll("\"", "'") + "\"";
				System.out.println("cmd:" + cmd);
				Process exec = Runtime.getRuntime().exec(cmd);
				InputStream inputStream = exec.getInputStream();
				BufferedReader red = new BufferedReader(new InputStreamReader(inputStream));
				String line = "";
				while((line = red.readLine())!=null) {
					System.out.println(line);
				}
			} catch (Exception e) {
				throw new Exception("文件下载失败");
			}
		}
		/*for(int k = 0 ;k < 60; k++) {
			Thread.sleep(5000);
			if(file.listFiles()!=null && file.listFiles().length > 0) {
				break;
			}
		}*/
		//file = new File("c:\\tmp\\test");
		
		File[] files = file.listFiles();
		if (files != null && files.length > 0) {
			response.addHeader("Content-Disposition", "attachment;fileName=" + files[0].getName());
			response.addHeader("filename", files[0].getName());
			System.out.println("name:" + files[0].getName());
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
					System.out.println("-----------------");
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
		} else {
			throw new Exception("文件下载失败");
		}
		
		// return "form1";
	}

	@RequestMapping(value = "/selectAll", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@ResponseBody
	public ArrayList<Form> selectAll() {
		return formService.selectAll();
	}

}
