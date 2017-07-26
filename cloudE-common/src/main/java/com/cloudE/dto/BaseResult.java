package com.cloudE.dto;

import java.io.Serializable;

/**
 * @author vangao1989
 * @Description: 返回值统一封装类
 * @date 2017年7月26日
 */
public class BaseResult<T extends Object> implements Serializable {


    private static final long serialVersionUID = 1368407471638948451L;

    public Boolean success;

    private T content;

    private String message;

    private String code;

    public BaseResult() {
    }

    public BaseResult(T content) {
        this.content = content;
        success = true;
    }

    public BaseResult(String code, String message) {
        this.message = message;
        this.code = code;
        success = false;
    }

    public BaseResult(Boolean success, String message) {
        this.message = message;
        this.success = success;
    }

    public BaseResult(Boolean success, String code, String message) {
        this.message = message;
        this.code = code;
        this.success = success;
    }

    public BaseResult(Boolean success, T content, String code, String message) {
        this.message = message;
        this.code = code;
        this.content = content;
        this.success = success;
    }

    public BaseResult<T> success(String message) {
        this.setSuccess(true);
        this.setMessage(message);
        return this;
    }

    public BaseResult<T> failure(String message) {
        this.setSuccess(false);
        this.setMessage(message);
        return this;
    }

    public BaseResult<T> content(T t) {
        this.setContent(t);
        return this;
    }

    public BaseResult<T> code(String code) {
        this.setCode(code);
        return this;
    }

    public BaseResult<T> successContent(T t) {
        this.content(t);
        this.success = true;
        return this;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
