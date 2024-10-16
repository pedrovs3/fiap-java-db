package dev.pedrovs.model;

import java.time.LocalDate;

public class Account {
    String id;
    String accountNumber;
    String accountType;
    Float amount;
    LocalDate createdAt;
    String clientId;

    public Account(String accountNumber, String accountType, Float amount, String clientId) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.amount = amount;
        this.clientId = clientId;
    }

    public Account(String id, String accountNumber, String accountType, Float amount, LocalDate createdAt, String clientId) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.amount = amount;
        this.createdAt = createdAt;
        this.clientId = clientId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
