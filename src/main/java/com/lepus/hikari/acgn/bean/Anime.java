package com.lepus.hikari.acgn.bean;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Entity;
import org.hibernate.annotations.GenericGenerator;

import com.lepus.hikari.acgn.enums.SerialState;
import com.lepus.hikari.acgn.enums.WatchState;

/**
 * 
 * @author wangchenyu
 * @date 2017-11-3
 */
@Entity(dynamicUpdate = true)
@javax.persistence.Entity
@Table(name = "t_e_anime")
public class Anime {

	@Id
	@Column(columnDefinition = "varchar(32)")
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(strategy = "uuid", name = "system-uuid")
	private String id;
	
	@Column(columnDefinition = "varchar(64)")
	private String name;
	
	@Column(columnDefinition = "int(11) not null")
	private int serialState = SerialState.ING.codeInt();
	
	@Column(columnDefinition = "int(11) not null")
	private int curr;
	
	@Column(columnDefinition = "int(11) not null")
	private int total;
	
	@Column(columnDefinition = "varchar(32)")
	private String year;
	
	@Column(columnDefinition = "varchar(32)")
	private String month;
	
	@Column(columnDefinition = "varchar(255)")
	private String summary;
	
	public int getWatchState(){
		int s = 0;
		if(this.total != 0){
			s = this.total==this.curr?WatchState.END.codeInt():WatchState.ING.codeInt();
		}
		return s;
	}

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

	public int getSerialState() {
		return serialState;
	}

	public void setSerialState(int serialState) {
		this.serialState = serialState;
	}

	public int getCurr() {
		return curr;
	}

	public void setCurr(int curr) {
		this.curr = curr;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	
}
