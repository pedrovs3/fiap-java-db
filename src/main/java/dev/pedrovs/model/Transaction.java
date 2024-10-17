package dev.pedrovs.model;

import java.time.LocalDate;

public class Transaction {
    String id;
    Float value;
    String type;
    LocalDate createdAt;
    String description;
    String accountId;
    String transactionTypeId;

    private String transactionTypeName;

    public Transaction(String id, Float value, String type, LocalDate createdAt, String description, String accountId, String transactionTypeId) {
        this.id = id;
        this.value = value;
        this.type = type;
        this.createdAt = createdAt;
        this.description = description;
        this.accountId = accountId;
        this.transactionTypeId = transactionTypeId;
    }

    public Transaction(String id, Float value, String type, LocalDate createdAt, String description, String accountId, String transactionTypeId, String transactionTypeName) {
        this.id = id;
        this.value = value;
        this.type = type;
        this.createdAt = createdAt;
        this.description = description;
        this.accountId = accountId;
        this.transactionTypeId = transactionTypeId;
        this.transactionTypeName = transactionTypeName;

    }

    public Transaction(Float value, String type, String description, String accountId, String transactionTypeId) {
        this.value = value;
        this.type = type;
        this.description = description;
        this.accountId = accountId;
        this.transactionTypeId = transactionTypeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getTransactionTypeId() {
        return transactionTypeId;
    }

    public void setTransactionTypeId(String transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
    }

    public String getTransactionTypeName() {
        return transactionTypeName;
    }

    public void setTransactionTypeName(String transactionTypeName) {
        this.transactionTypeName = transactionTypeName;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", value=" + value +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", accountId='" + accountId + '\'' +
                ", transactionTypeName='" + transactionTypeName + '\'' +
                '}';
    }
}
