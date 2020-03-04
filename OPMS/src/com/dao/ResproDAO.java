package com.dao;

import java.util.ArrayList;

import com.domain.Respro;

public interface ResproDAO {
	
	public boolean addpro(Respro pro);
	
	public boolean updatepro(Respro pro);
	
	public boolean updateonly(int id ,int con);
	public boolean updateprocon(int id , int con);
	
	public boolean delpro(int id);
	
	public Respro getpro(int id);
	
	public ArrayList<Respro> getAllpro(String sql);

}
