package edu.sjsu.cmpe.library.domain;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class Authors
{
	private int id;
	private String name;
	
    //ArrayList<Authors> authors = new ArrayList<Authors>();
    //authors.add("madhu");
    //authors.add("manoz");
	
	/*
    public Authors() 
    {
	 ++authorid;
    }
	*/
	
	public int getId() 
    {
	return id;
    }

    public void seid(int id) 
    {
	this.id = id;
    }
    
  
    public String getName() 
    {
	return name;
    }

    public void setName(String name) 
    {
	this.name = name;
    }
  
}
