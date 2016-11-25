package com.ust.userwebapp.web.dto;

public class RoleDto implements IDto{

	
	private Long id;
	
	private String name;

	@Override
	public Long getId() {
		
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoleDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RoleDto(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
  	
	
}
