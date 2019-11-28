package org.spring.cloud.common.exception;

public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private Long businessid;
	
	private Integer code;
	
	private String codeEN;
	
	private String message;

	public Long getBusinessid() {
		return businessid;
	}

	public void setBusinessid(Long businessid) {
		this.businessid = businessid;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getCodeEN() {
		return codeEN;
	}

	public void setCodeEN(String codeEN) {
		this.codeEN = codeEN;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public BaseException() {

	}
	public BaseException(Integer code, String codeEN, String message, Long businessid) {
		this(code, codeEN, message, businessid, null);
	}

	public BaseException(Integer code, String codeEN, String message, Long businessid, Throwable t) {
		super(message, t);
		this.businessid = businessid;
		this.code = code;
		this.codeEN = codeEN;
		this.message = message;
	}
	
}
