package com.songzi.yummy.service;

import com.songzi.yummy.entity.Account;
import com.songzi.yummy.entity.Accountdetail;
import com.songzi.yummy.entity.Restaurant;

public interface AccountService {
    Account create();
    boolean update(Account account);
    Account getById(long account_id);
    Accountdetail updateDetail(Accountdetail accountdetail);
}
