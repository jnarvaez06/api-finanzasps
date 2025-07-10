package com.jn.api_gastos.user.dto;

public record UserDTO(
        Integer idUser,
        String name,
        String lastname,
        String username
) {}