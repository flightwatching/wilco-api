package com.fw.wilco.api;

import com.fw.wilco.api.constants.Severity;

public class IftV3IO {

	private String formula;
	private String[] variables;
	private Severity sev;
	private String code;
	private String acFamilly;
	private Boolean skip;
	private Long id;
	private String name;
	private String srcId;
	private String srcKind;
	private String language;

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public String[] getVariables() {
		return variables;
	}

	public void setVariables(String[] variables) {
		this.variables = variables;
	}

	public Severity getSev() {
		return sev;
	}

	public void setSev(Severity sev) {
		this.sev = sev;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAcFamilly() {
		return acFamilly;
	}

	public void setAcFamilly(String acFamilly) {
		this.acFamilly = acFamilly;
	}

	public Boolean getSkip() {
		return skip;
	}

	public void setSkip(Boolean skip) {
		this.skip = skip;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSrcId() {
		return srcId;
	}

	public void setSrcId(String srcId) {
		this.srcId = srcId;
	}

	public String getSrcKind() {
		return srcKind;
	}

	public void setSrcKind(String srcKind) {
		this.srcKind = srcKind;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
}
