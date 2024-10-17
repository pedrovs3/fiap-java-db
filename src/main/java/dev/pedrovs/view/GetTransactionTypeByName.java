package dev.pedrovs.view;

import dev.pedrovs.dao.TransactionTypeDao;
import dev.pedrovs.model.TransactionType;

import java.util.Optional;

public class GetTransactionTypeByName {
    public static void main(String[] args) {
        TransactionTypeDao transactionTypeDao = new TransactionTypeDao();
        Optional<TransactionType> transactionType = transactionTypeDao.getByName("Depósito");

        if (transactionType.isPresent()) {
            System.out.println(transactionType.get());
        } else {
            System.out.println("Tipo de transação não encontrado!");
        }
    }
}
