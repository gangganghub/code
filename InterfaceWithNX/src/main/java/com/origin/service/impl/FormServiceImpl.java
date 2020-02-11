package com.origin.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.origin.ben.Form;
import com.origin.mapper.FormMapper;
import com.origin.service.FormService;

@Service
public class FormServiceImpl implements FormService{
	@Autowired
	public FormMapper formMapper = null;
	
	public void insertForm(Form form) {
		formMapper.insertForm(form);
	}
	
	public ArrayList<Form> selectAll(){
		return formMapper.selectAll();
	}
	
	public Form selectBigestForm() {
		return formMapper.selectBigestForm();
	}
}
