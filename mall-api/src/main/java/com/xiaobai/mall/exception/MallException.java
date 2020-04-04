package com.xiaobai.mall.exception;

/**
 * @author 江湖人称白玉汤
 * @program 毕业-设计
 * @description 商城异常总类
 * @create 2020-03-27 15:46
 **/
public class MallException extends RuntimeException{


        private String msg;

        public MallException(String msg){
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
