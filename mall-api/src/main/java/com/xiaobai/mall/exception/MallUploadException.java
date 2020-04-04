package com.xiaobai.mall.exception;

/**
 * @author 江湖人称白玉汤
 * @program 毕业-设计
 * @description 图片上传异常类
 * @create 2020-03-27 15:47
 **/
public class MallUploadException extends RuntimeException{

    private String msg;

    public MallUploadException(String msg){
        super(msg);
        this.msg=msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
