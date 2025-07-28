package com.jn.api_gastos.modules.account;

import com.jn.api_gastos.config.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("${api.base.path}")
@RequiredArgsConstructor
public class AccountController {
    private static final String NAME_ENDPOINT = "/account";
    private static final String NAME_ENDPOINT_ID = "/account/{id}";

    @Autowired
    private IAccountService accountService;

    @GetMapping(value = NAME_ENDPOINT)
    public ResponseEntity<List<AccountDTO>> getAccounts() {
        List<AccountDTO> accounts = accountService.listAccounts();

        if (accounts == null || accounts.isEmpty()) {
            throw new ResourceNotFoundException("Accounts");
        }

        return ResponseEntity.ok(accounts);
    }

    @PostMapping(value = NAME_ENDPOINT)
    public ResponseEntity<Account> addAccount(@Valid @RequestBody Account account) {
        Account saved = accountService.saveAccount(account);
        return ResponseEntity.ok(saved);
    }

    @PutMapping(value = NAME_ENDPOINT_ID)
    public ResponseEntity<Account> updateAccount(@PathVariable Integer id, @RequestBody Account getAccount) {
        Account account = accountService.getAccountById(id);

        if (account == null) {
            throw new ResourceNotFoundException("Accounts", "idAccount", id);
        }

        account.setDescription(getAccount.getDescription());
        account.setState(getAccount.isState());
        accountService.saveAccount(account);

        return ResponseEntity.ok(account);
    }
}
