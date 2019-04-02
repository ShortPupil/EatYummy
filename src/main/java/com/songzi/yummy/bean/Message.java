package com.songzi.yummy.bean;

import java.util.HashMap;
import java.util.Map;

public class Message {
    //状态码
    private int code;
    //提示信息
    private String msg;
    //返回给浏览器的数据
    private Map<String,Object> extend = new HashMap<String,Object>();

    //成功返回
    public static Message success(){
        Message result = new Message();
        result.setCode(100);
        result.setMsg("处理成功");
        return result;
    }
    //返回失败
    public static Message fail(){
        Message result = new Message();
        result.setCode(200);
        result.setMsg("处理失败");
        return result;
    }
    //链式添加信息的办法
    public Message add(String key,Object value){
        this.getExtend().put(key, value);
        return this;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }
}
