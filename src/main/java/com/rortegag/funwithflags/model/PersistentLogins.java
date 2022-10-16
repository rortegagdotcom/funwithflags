package com.rortegag.funwithflags.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "persistent_logins")
public class PersistentLogins
{

	@Id
	@Column(name = "series")
	private String series;
	
	@Column(name = "username")
	private String userName;
	
	@Column(name = "token")
	private String token;
	
	@Column(name = "last_used")
	private Timestamp lastUsed;
	
}
