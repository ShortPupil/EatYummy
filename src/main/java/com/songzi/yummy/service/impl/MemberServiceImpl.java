package com.songzi.yummy.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.ServiceException;
import com.songzi.yummy.entity.Account;
import com.songzi.yummy.entity.Address;
import com.songzi.yummy.entity.Level;
import com.songzi.yummy.entity.Member;
import com.songzi.yummy.repository.AccountRepository;
import com.songzi.yummy.repository.AddressRepository;
import com.songzi.yummy.repository.LevelRepository;
import com.songzi.yummy.repository.MemberRepository;
import com.songzi.yummy.service.MemberService;
import com.songzi.yummy.util.MD5Util;
import com.songzi.yummy.util.MailUtil;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.rowset.serial.SerialException;
import java.text.ParseException;
import java.util.*;

@Service
public class MemberServiceImpl implements MemberService{

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private LevelRepository levelRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AccountRepository accountRepository;

    //@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean update(Member member){
        memberRepository.saveAndFlush(member);
        return true;
    }

    @Override
    public List<Member> searchByName(String name){
        return memberRepository.searchMemberByName(name);
    }

    @Override
    public Member get(long id){
        return memberRepository.getOne(id);
    }

    @Override
    public Member login(String email, String password){
        Member member = memberRepository.findMemberByEmail(email);

        System.out.println(email);
        //System.out.println(member.getId());
        if(member.equals(null)) return null;
        if(member.getPassword().equals(password)){
            return member;
        }
        return null;
    }

    @Override
    public Member updateInfo(String newName, String newPhoneNumber){

        return new Member();
    }

    @Override
    public Address addAddress(){
        return new Address();
    }

    @Override
    public Address updateAddressPriority(){
        return new Address();
    }

    @Override
    public long getTotal(){
        return memberRepository.count();
    }

    @Override
    public List<Member> getAllMembers(){return memberRepository.findAll();}

    @Override
    public boolean checkMemberName(String name){
        List<Member> members = memberRepository.findAll();
        for(Member m : members){
            if(m.getName().equals(name)) return false;
        }
        return true;
    }

    @Override
    public String sendEmail(String email){
        String validateCode = MailUtil.getValidateCode();
        //发送邮件
        MailUtil.sendmail(email, validateCode);
        //System.out.println(validateCode);
        return validateCode;
    }

    /**
     * 处理注册
     */
    @Override
    public Member processRegister(String name, String password, String email, String validateCode){

        /*Long memId, Integer priority, String coordinate, String localAddres*/
        Address originalAddress = addressRepository.getOne((long)1);
        Account account = new Account();

        /**初始100*/
        account.setBalance(Double.valueOf(100.0));
        accountRepository.saveAndFlush(account);
        //String email, String name, String password, int state, String validateCode
        Member member = new Member(email, name, password, 1, validateCode, account);
        Member m = memberRepository.saveAndFlush(member);
        Address address = new Address(m.getId(), 1, originalAddress.getCoordinate(), originalAddress.getLocalAddress());
        addressRepository.saveAndFlush(address);
        member.setPreferredAddress(address.getLocalAddress());
        memberRepository.saveAndFlush(m);

        return m;
    }

    @Override
    public Member getMemberByEmail(String email){
        return memberRepository.findMemberByEmail(email);
    }

    @Override
    public Level getLevelByMemId(long mem_id){
        Member member = memberRepository.getOne(mem_id);
        int level_point = member.getLevelPoint();
        List<Level> levelList = levelRepository.getAll();
        for(Level level : levelList){
            if(level.getLowerLimit() <= level_point && level.getUpperLimit() >= level_point){
                return level;
            }
        }
        return null;
    }

    @Override
    public List<Member> getUndeletedMember(List<Member> memberList){
        List<Member> res = new ArrayList<Member>();
        for(Member member : memberList){
            if(member.getState() != 0){
                res.add(member);
            }
        }
        return res;
    }
}
