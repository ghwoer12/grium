package com.project.four.model.dto;

import java.sql.Timestamp;

public class GallaryDto {
	
	/*
	 * CREATE TABLE PHOTO_TB(
		PHOTO_ID INT AUTO_INCREMENT,
		GONE_ID VARCHAR(20) NOT NULL,
		SERVER_PHOTO VARCHAR(50) NOT NULL,
		PHOTO_PATH VARCHAR(100) NOT NULL,
		ORIGIN_PHOTO VARCHAR(50) NOT NULL,
		WRITER VARCHAR(20) NOT NULL,
		PHOTO_DT TIMESTAMP NOT NULL,
		COUNT INT,
		SECRET INT NOT NULL,
		PRIMARY KEY (PHOTO_ID),
		FOREIGN KEY (GONE_ID) REFERENCES GONE_TB(GONE_ID)
		);
	 */
	
	private int photo_id;
	private String gone_id;
	private String server_photo;
	private String photo_path;
	private String origin_photo;
	private String writer; //아마 userid이지 않을까?
	private Timestamp photo_dt;
	private int count;
	private int secret;
	public int getPhoto_id() {
		return photo_id;
	}
	public void setPhoto_id(int photo_id) {
		this.photo_id = photo_id;
	}
	public String getGone_id() {
		return gone_id;
	}
	public void setGone_id(String gone_id) {
		this.gone_id = gone_id;
	}
	public String getServer_photo() {
		return server_photo;
	}
	public void setServer_photo(String server_photo) {
		this.server_photo = server_photo;
	}
	public String getPhoto_path() {
		return photo_path;
	}
	public void setPhoto_path(String photo_path) {
		this.photo_path = photo_path;
	}
	public String getOrigin_photo() {
		return origin_photo;
	}
	public void setOrigin_photo(String origin_photo) {
		this.origin_photo = origin_photo;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Timestamp getPhoto_dt() {
		return photo_dt;
	}
	public void setPhoto_dt(Timestamp photo_dt) {
		this.photo_dt = photo_dt;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getSecret() {
		return secret;
	}
	public void setSecret(int secret) {
		this.secret = secret;
	}
	public GallaryDto(int photo_id, String gone_id, String server_photo, String photo_path, String origin_photo,
			String writer, Timestamp photo_dt, int count, int secret) {
		super();
		this.photo_id = photo_id;
		this.gone_id = gone_id;
		this.server_photo = server_photo;
		this.photo_path = photo_path;
		this.origin_photo = origin_photo;
		this.writer = writer;
		this.photo_dt = photo_dt;
		this.count = count;
		this.secret = secret;
	}
	
	@Override
	public String toString() {
		return "GallaryDto [photo_id=" + photo_id + ", gone_id=" + gone_id + ", server_photo=" + server_photo
				+ ", photo_path=" + photo_path + ", origin_photo=" + origin_photo + ", writer=" + writer + ", photo_dt="
				+ photo_dt + ", count=" + count + ", secret=" + secret + "]";
	}
	
	
	
	
	
	
	
	

}
