package com.dao;

import java.util.ArrayList;

import com.domain.Operecord;
import com.domain.Respro;

public interface OperecordDAO {
	
	public boolean addope(Operecord ope);
	
	public ArrayList<Operecord> getAllope(String sql);

}
