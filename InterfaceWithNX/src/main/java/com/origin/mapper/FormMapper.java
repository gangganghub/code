package com.origin.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.origin.ben.Form;

@Mapper
public interface FormMapper {
	public void insertForm(Form form);
	public ArrayList<Form> selectAll();
	public Form selectBigestForm();
}