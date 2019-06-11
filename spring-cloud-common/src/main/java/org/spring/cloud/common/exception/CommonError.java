package org.spring.cloud.common.exception;

import org.spring.cloud.common.util.ExceptionUtil;

public enum CommonError {
	
	USER_EMPTY_ERROR(10001, "This User is null...");
	
	private Integer code;
	
	private String message;
	
	CommonError(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getCodeEn() {
		return ExceptionUtil.errorToCodeEN(this);
	}
}
