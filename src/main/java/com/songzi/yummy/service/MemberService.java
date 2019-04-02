package com.songzi.yummy.service;

import com.songzi.yummy.entity.Address;
import com.songzi.yummy.entity.Level;
import com.songzi.yummy.entity.Member;

import java.util.List;

public interface MemberService {

    boolean update(Member member);
    List<Member> searchByName(String name);
    Member get(long id);
    List<Member> getAllMembers();
    boolean checkMemberName(String name);
    /**登录
     * @param email 邮件
     * @param password 密码*/
    public Member login(String email, String password);

    /**更新基本信息
     * @param newName 新姓名
     * @param newPhoneNumber 新电话号码
     * */
    public Member updateInfo(String newName, String newPhoneNumber);

    /**未定*/
    public Address addAddress();

    /**修改优先地址*/
    public Address updateAddressPriority();

    long getTotal();

    Member processRegister(String name, String password, String email, String validateCode);

    String sendEmail(String email);

    Member getMemberByEmail(String email);

    Level getLevelByMemId(long mem_id);

    List<Member> getUndeletedMember(List<Member> memberList);
}
