package com.lepus.hikari.acgn.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@org.hibernate.annotations.Entity(dynamicUpdate = true)
@Entity
@Table(name = "t_e_group")
public class Group {

	@Id
	@Column(columnDefinition = "varchar(32)")
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(strategy = "uuid", name = "system-uuid")
	private String id;
	
	@Column(columnDefinition = "varchar(64)")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "textId")
	private Text text;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
