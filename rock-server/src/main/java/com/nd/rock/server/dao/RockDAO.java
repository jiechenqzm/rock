package com.nd.rock.server.dao;

import java.util.List;

import com.nd.rock.server.instance.DataIn;


public interface RockDAO {
	
	public int insert();
	
	public int update();
	
	public int delete();
	
	public DataIn queryIn();
	
	public List<DataIn> queryListIn();
	

}
