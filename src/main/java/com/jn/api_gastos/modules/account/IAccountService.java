package com.jn.api_gastos.modules.account;

import java.util.List;

public interface IAccountService {
    public List<AccountDTO> listAccounts();

    public Account saveAccount(Account account);

    public Account getAccountById(Integer idAccount);
}
