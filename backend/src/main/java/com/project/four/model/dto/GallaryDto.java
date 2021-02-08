package com.project.four.model.dto;

import java.sql.Timestamp;
import java.util.List;

public class GallaryDto {

	/*
	 * CREATE TABLE PHOTO_TB( PHOTO_ID INT AUTO_INCREMENT, GONE_ID VARCHAR(20) NOT
	 * NULL, SERVER_PHOTO VARCHAR(50) NOT NULL, PHOTO_PATH VARCHAR(100) NOT NULL,
	 * ORIGIN_PHOTO VARCHAR(50) NOT NULL, WRITER VARCHAR(20) NOT NULL, PHOTO_DT
	 * TIMESTAMP NOT NULL, COUNT INT, SECRET INT NOT NULL, PRIMARY KEY (PHOTO_ID),
	 * FOREIGN KEY (GONE_ID) REFERENCES GONE_TB(GONE_ID) );
	 */

	private int photo_id;
	private String gone_id;
	private String writer; // 아마 userid이지 않을까?
	private Timestamp photo_dt;
	private int count;
	private int secret;
	private List<GaddressDto> gaddress;

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

	public List<GaddressDto> getGaddress() {
		return gaddress;
	}

	public void setGaddress(List<GaddressDto> gaddress) {
		this.gaddress = gaddress;
	}

	public GallaryDto() {
		// TODO Auto-generated constructor stub
	}

	public GallaryDto(int photo_id, String gone_id, String writer, Timestamp photo_dt, int count, int secret,
			List<GaddressDto> gaddress) {
		super();
		this.photo_id = photo_id;
		this.gone_id = gone_id;
		this.writer = writer;
		this.photo_dt = photo_dt;
		this.count = count;
		this.secret = secret;
		this.gaddress = gaddress;
	}

	@Override
	public String toString() {
		return "GallaryDto [photo_id=" + photo_id + ", gone_id=" + gone_id + ", writer=" + writer + ", photo_dt="
				+ photo_dt + ", count=" + count + ", secret=" + secret + ", gaddress=" + gaddress + "]";
	}

}
