package com.jn.api_gastos.modules.account;

import com.jn.api_gastos.auth.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService implements IAccountService{

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AuthenticatedUser authenticatedUser;

    @Override
    public List<AccountDTO> listAccounts() {
        String username = authenticatedUser.getUsername();

        return accountRepository.findAllByUser(username)
                .stream()
                .map(account -> new AccountDTO(
                    account.getIdAccount(),
                    account.getDescription(),
                    account.isState()
                ))
                .toList();
    }

    @Override
    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account getAccountById(Integer idAccount) {
        return accountRepository.findById(idAccount).orElse(null);
    }
}
