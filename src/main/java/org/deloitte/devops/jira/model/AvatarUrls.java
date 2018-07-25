package org.deloitte.devops.jira.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AvatarUrls implements Serializable {
	private final static long serialVersionUID = 619096601321725997L;

	@JsonProperty("48x48")
	private String _48x48;

	@JsonProperty("24x24")
	private String _24x24;

	@JsonProperty("16x16")
	private String _16x16;

	@JsonProperty("32x32")
	private String _32x32;

	@JsonProperty("48x48")
	public String get48x48() {
		return _48x48;
	}

	@JsonProperty("48x48")
	public void set48x48(String _48x48) {
		this._48x48 = _48x48;
	}

	@JsonProperty("24x24")
	public String get24x24() {
		return _24x24;
	}

	@JsonProperty("24x24")
	public void set24x24(String _24x24) {
		this._24x24 = _24x24;
	}

	@JsonProperty("16x16")
	public String get16x16() {
		return _16x16;
	}

	@JsonProperty("16x16")
	public void set16x16(String _16x16) {
		this._16x16 = _16x16;
	}

	@JsonProperty("32x32")
	public String get32x32() {
		return _32x32;
	}

	@JsonProperty("32x32")
	public void set32x32(String _32x32) {
		this._32x32 = _32x32;
	}

}
