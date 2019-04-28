package com.xust.service;

/**
 * 通用服务接口结构
 * @author: Luo Daiyang
 * @description:
 * @date Created in 17:20 2019/4/28
 * @modified By:
 */
public class ServiceResult<T> {
    private  boolean successs;
    private  String message;
    private  T result;

    public ServiceResult(boolean successs) {
        this.successs = successs;
    }

    public  ServiceResult(Boolean successs ,String message){
        this.successs=successs;
        this.message=message;

    }

    public ServiceResult(boolean successs, String message, T result) {
        this.successs = successs;
        this.message = message;
        this.result = result;
    }

    public boolean isSuccesss() {
        return successs;
    }

    public void setSuccesss(boolean successs) {
        this.successs = successs;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
    public static <T> ServiceResult<T> success() {
        return new ServiceResult<>(true);
    }

    public static <T> ServiceResult<T> of(T result) {
        ServiceResult<T> serviceResult = new ServiceResult<>(true);
        serviceResult.setResult(result);
        return serviceResult;
    }

    public static <T> ServiceResult<T> notFound() {
        return new ServiceResult<>(false, Message.NOT_FOUND.getValue());
    }

    public enum Message {
        NOT_FOUND("Not Found Resource!"),
        NOT_LOGIN("User not login!");

        private String value;

        Message(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }



}
