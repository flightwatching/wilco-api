package com.fw.wilco.api;

import java.util.List;

public class IeiV3IO {

	public String code;

	public Long idxMin;
	public Long idxMax;

	public Long length;

	public String timeOffsetFormula;
	public String timeLabel;

	@CollectionComponent(value=IeiParamV3IO.class)
	public List<IeiParamV3IO> param;

	public Boolean isHeader;

}
