package dev.pedrovs.view;

import dev.pedrovs.dao.TransactionTypeDao;
import dev.pedrovs.model.TransactionType;

public class RegisterNewTransactionType {
    public static void main(String[] args) {
        try {
            TransactionTypeDao transactionTypeDao = new TransactionTypeDao();
            TransactionType transactionType = new TransactionType("Depósito");
            TransactionType transactionType2 = new TransactionType("Saque");

            transactionTypeDao.insert(transactionType);
            transactionTypeDao.insert(transactionType2);
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
        }
    }
}
