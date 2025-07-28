package com.jn.api_gastos.modules.account;

public record AccountDTO (
        Integer idAccount,
        String description,
        boolean state
) {};
