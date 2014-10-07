package vn.edu.vnu.ai.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ResponseModel<T> implements Serializable{
	private T model;
	private String code;
	private String message;
	private boolean hasError;
	
	public T getModel() {
		return model;
	}

	public void setModel(T model) {
		this.model = model;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isHasError() {
		return hasError;
	}

	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}
	
}
