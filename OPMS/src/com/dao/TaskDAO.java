package com.dao;

import java.util.ArrayList;

import com.domain.Respro;
import com.domain.Task;

public interface TaskDAO {
	
	public boolean addtask(Task task);
	
	public boolean updatetask(Task task);
	
	public boolean deltask(int proid);
	
	public boolean deltask2(int taskid);
	
	public Task gettask(int id);
	
	public ArrayList<Task> getAlltask(String sql);
	
	public boolean updatetask2();
	
	public boolean updatetask3();
	
}
