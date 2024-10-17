package dev.pedrovs.view;

import dev.pedrovs.dao.AccountDao;
import dev.pedrovs.dao.TransactionDao;
import dev.pedrovs.dao.TransactionTypeDao;
import dev.pedrovs.model.Account;
import dev.pedrovs.model.Transaction;
import dev.pedrovs.model.TransactionType;

public class RegisterNewTransaction {
    public static void main(String[] args) {
        try {
            TransactionTypeDao transactionTypeDao = new TransactionTypeDao();
            TransactionDao transactionDao = new TransactionDao();
            AccountDao accountDao = new AccountDao();

            Account account = accountDao.getAll().get(0);

            TransactionType transactionType = transactionTypeDao.getByName("Depósito").orElseThrow(
                    () -> new RuntimeException("Tipo de transação não encontrado")
            );

            Transaction transaction = new Transaction(150.0f, "Depósito", "Depósito de dinheiro", account.getId(), transactionType.getId());

            transactionDao.insert(transaction);
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
        }

    }
}
