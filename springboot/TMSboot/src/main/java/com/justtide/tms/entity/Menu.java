package com.justtide.tms.entity;

import lombok.Data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sys_menu")
@Data
public class Menu implements Serializable{
	@Id
	@GeneratedValue()
	private Integer id;
	private Integer pid;
	private String img;
	private String name;
	private String enName;
	private String url;
	private int state;
	private int sortid;
	
	public Menu(){
	}
	
	public Menu(int id, int pid, String img, String name, String enName,
			String url, int state, int sortid) {
		super();
		this.id = id;
		this.pid = pid;
		this.img = img;
		this.name = name;
		this.enName = enName;
		this.url = url;
		this.state = state;
		this.sortid = sortid;
	}
}
