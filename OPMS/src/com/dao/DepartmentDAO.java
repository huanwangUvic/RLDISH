package com.dao;

import java.util.ArrayList;

import com.domain.Department;
import com.domain.Respro;

public interface DepartmentDAO {
	public boolean adddep(Department dep);
	
	public boolean deldep(int num);
	
	public boolean updatedep(Department dep);
	
	public Department getdep(int num);
	
	public ArrayList<Department> getAlldep(String sql);
	
	

}
