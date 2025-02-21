package com.filipetavares.tests.dto;


public record CheckoutDTO(
        Integer totalItems,
        String fullName,
        String address,
        String zipCode,
        String city,
        String country,
        String cardHolder,
        String cardNumber,
        String expirationDate,
        String securityCode
) {}


