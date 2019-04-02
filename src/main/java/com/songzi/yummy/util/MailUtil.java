package com.songzi.yummy.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import java.util.*;


/**
 *@program: bookShopping
 *@description: 发送邮箱工具类
 */
//邮箱验证
public class MailUtil {

    public static String getValidateCode(){
        String[] beforeShuffle= new String[] { "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F",
                "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a",
                "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
                "w", "x", "y", "z" };
        List list = Arrays.asList(beforeShuffle);//将数组转换为集合
        Collections.shuffle(list);  //打乱集合顺序
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)); //将集合转化为字符串
        }

        String code = sb.toString().substring(0,6);
        return code;
    }

    public static void sendmail(String email, String code) {

        try {
            SimpleEmail mail = new SimpleEmail();
            mail.setHostName("smtp.163.com");//发送邮件的服务器
            mail.setAuthentication("songzi2625@163.com", "admin1234");//登录邮箱的密码，是开启SMTP的密码
            mail.setFrom("songzi2625@163.com", "松子");  //发送邮件的邮箱和发件人
            mail.setSSLOnConnect(true); //使用安全链接
            mail.addTo(email);//接收的邮箱
            mail.setSubject("Yummy!验证码");//设置邮件的主题
            String content = "尊敬的客户您好，您的注册验证码为 " + code;
            mail.setContent(content, "text/html;charset=UTF-8");//设置邮件的内容
            mail.send();//发送
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}

