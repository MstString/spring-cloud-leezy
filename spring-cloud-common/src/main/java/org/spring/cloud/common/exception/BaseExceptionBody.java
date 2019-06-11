package org.spring.cloud.common.exception;

public class BaseExceptionBody {
	private static final long serialVersionUID = 1L;
	
	private Long bussinessid;
	
	private Integer code;
	
	private String codeEN;
	
	private String message;
	
	private String errortype;

	public Long getBussinessid() {
		return bussinessid;
	}

	public void setBussinessid(Long bussinessid) {
		this.bussinessid = bussinessid;
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

	public String getErrortype() {
		return errortype;
	}

	public void setErrortype(String errortype) {
		this.errortype = errortype;
	}
	
	public BaseExceptionBody() {
		
	}
	
	public BaseExceptionBody(BaseException exception) {
		this.bussinessid = exception.getBusinessid();
		this.code = exception.getCode();
		this.codeEN = exception.getCodeEN();
		this.message = exception.getMessage();
		this.errortype = exception.getClass().getName();
	}
}
