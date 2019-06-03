package com.bryant.aot.entity;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
@Entity
@Table(name = "AOT_MEMBER")
public class AotMember {
	/**
	 * pk userid 自動產生
	 */
	@Id
	@GeneratedValue
	@Column(name = "userid",nullable = false)
	private Long userId;
	/**
	 * unique username 會員註冊帳號 確認資料庫唯一值
	 */
	@Column(name = "username",nullable = false ,unique = true)
	private String username;


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
