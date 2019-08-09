package com.fw.wilco.api;

import java.util.List;

import com.fw.wilco.api.constants.ParamType;



public class ParameterV3IO {

	private Long id;
	private Double minScale;
	private Double maxScale;
	private Double minOK;
	private Double maxOK;
	private String name;
	private ParamType type;
    private String description;
	private String actypeName;
	private String address;
	private Boolean isKey;

	@CollectionComponent(value=IftV3IO.class)
	private List<IftV3IO> ifts;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getMinScale() {
		return minScale;
	}

	public void setMinScale(Double minScale) {
		this.minScale = minScale;
	}

	public Double getMaxScale() {
		return maxScale;
	}

	public void setMaxScale(Double maxScale) {
		this.maxScale = maxScale;
	}

	public Double getMinOK() {
		return minOK;
	}

	public void setMinOK(Double minOK) {
		this.minOK = minOK;
	}

	public Double getMaxOK() {
		return maxOK;
	}

	public void setMaxOK(Double maxOK) {
		this.maxOK = maxOK;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ParamType getType() {
		return type;
	}

	public void setType(ParamType type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getActypeName() {
		return actypeName;
	}

	public void setActypeName(String actypeName) {
		this.actypeName = actypeName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Boolean getKey() {
		return isKey;
	}

	public void setKey(Boolean key) {
		isKey = key;
	}

	public List<IftV3IO> getIfts() {
		return ifts;
	}

	public void setIfts(List<IftV3IO> ifts) {
		this.ifts = ifts;
	}
}
