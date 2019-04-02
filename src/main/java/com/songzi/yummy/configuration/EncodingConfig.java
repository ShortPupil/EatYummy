package com.songzi.yummy.configuration;

import org.springframework.stereotype.Component;
/**
 * 编码格式通用常量池
 * @author qdt_PC
 */
@Component("EncodingConfig")
public class EncodingConfig {
    /**设置的编码*/
    public final static String characterEncoding="utf-8";
    /**默认的编码*/
    public final static String charEncoding="iso8859-1";
    /**controller响应编码格式*/
    public final static String produces="text/html;charset=utf-8";
}
