package com.origin.service;

import java.util.ArrayList;

import com.origin.ben.Form;

public interface FormService {
	public void insertForm(Form form);
	public ArrayList<Form> selectAll();
	public Form selectBigestForm();
}
