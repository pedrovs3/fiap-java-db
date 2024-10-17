package dev.pedrovs.view;

import dev.pedrovs.dao.TransactionDao;
import dev.pedrovs.model.Transaction;

public class GetAllTransactions {
    public static void main(String[] args) {
        TransactionDao transactionDao = new TransactionDao();

        for (Transaction transaction : transactionDao.getAll()) {
            System.out.println(transaction);
        }

    }
}
