package models.io;

import java.util.List;

import models.io.constants.ParamType;



public class ParameterV3IO {

	public Long id;
	public  Double minScale;
	public  Double maxScale;
	public  Double minOK;
	public  Double maxOK;
	public  String name;
	public  ParamType type;
    public  String description;
	public  String actypeName;
	public  String address;
	public Boolean isKey;

	@CollectionComponent(value=IftV3IO.class)
	public  List<IftV3IO> ifts;

}