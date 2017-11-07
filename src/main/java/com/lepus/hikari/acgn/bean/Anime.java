package com.lepus.hikari.acgn.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.lepus.hikari.acgn.enums.SerialState;
import com.lepus.hikari.acgn.enums.WatchState;
import com.lepus.hikari.framework.build.BaseEntity;

/**
 * 
 * @author wangchenyu
 * @date 2017-11-3
 */
@org.hibernate.annotations.Entity(dynamicUpdate = true)
@Entity
@Table(name = "t_e_anime")
public class Anime extends BaseEntity{
	private static final long serialVersionUID = 4104312292631474689L;

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
	
	@Column(columnDefinition = "datetime not null", updatable = false)
	private Date createTime = new Date();

	@Column(columnDefinition = "datetime not null", updatable = true)
	private Date updateTime = new Date();
	
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
