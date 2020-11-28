package com.luminous.integration.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

/**
 * 
 * @author Ahlam-issa
 *
 */
@JsonIgnoreProperties
@Entity
@Table(name = "favorite")
public class Favorite {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "Name")
	@JsonProperty(access = Access.READ_WRITE,value = "name")
	private String name;
	@JsonProperty(access = Access.READ_WRITE,value = "img")
	@Column(name = "Image")
	private String img;
	@JsonProperty(access = Access.READ_WRITE,value = "level")
	@Column(name = "Level")
	private String level;

	public Favorite() {

	}

	public Favorite(String name, String img, String level) {
		super();
		this.name = name;
		this.img = img;
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "Digimon [name=" + name + ", img=" + img + ", level=" + level + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
