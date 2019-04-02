package com.songzi.yummy.service.impl;

import com.songzi.yummy.entity.Account;
import com.songzi.yummy.entity.Accountdetail;
import com.songzi.yummy.repository.AccountRepository;
import com.songzi.yummy.repository.AccountdetailRepository;
import com.songzi.yummy.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountdetailRepository accountdetailRepository;

    @Override
    public Account create(){
        Account account = new Account();
        accountRepository.saveAndFlush(account);
        return account;
    }

    @Override
    public boolean update(Account account){
        Account account1 = accountRepository.saveAndFlush(account);
        if(!account1.equals(null)) return true;
        return false;
    }

    @Override
    public Account getById(long account_id){
        return accountRepository.getOne(account_id);
    }

    @Override
    public Accountdetail updateDetail(Accountdetail accountdetail){
        return accountdetailRepository.saveAndFlush(accountdetail);
    }
}
