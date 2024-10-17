package dev.pedrovs.dao;

import dev.pedrovs.factory.ConnectionFactory;
import dev.pedrovs.model.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransactionDao {
    private static final Logger LOGGER = Logger.getLogger(TransactionDao.class.getName());

    public void insert(Transaction transaction) {
        String sql = "INSERT INTO TB_TRANSACTIONS (VALUE, TYPE, DESCRIPTION, ACCOUNT_ID, TYPE_ID) " +
                "VALUES (?, ?, ?, ?, ?)";

        if (transaction.getValue() <= 0) {
            throw new IllegalArgumentException("O valor da transação deve ser positivo.");
        }
        if (transaction.getAccountId() == null || transaction.getAccountId().isEmpty()) {
            throw new IllegalArgumentException("O ID da conta é obrigatório.");
        }
        if (transaction.getTransactionTypeId() == null || transaction.getTransactionTypeId().isEmpty()) {
            throw new IllegalArgumentException("O ID do tipo de transação é obrigatório.");
        }
        
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setDouble(1, transaction.getValue());
            preparedStatement.setString(2, transaction.getType());
            preparedStatement.setString(3, transaction.getDescription());
            preparedStatement.setString(4, transaction.getAccountId());
            preparedStatement.setString(5, transaction.getTransactionTypeId());

            preparedStatement.executeUpdate();

            LOGGER.info("Transação cadastrada com sucesso! Valor: " + transaction.getValue() +
                    ", Conta: " + transaction.getAccountId() +
                    ", Tipo: " + transaction.getType());

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao cadastrar transação. Conta: " + transaction.getAccountId() +
                    ", Tipo: " + transaction.getType(), e);
        }
    }
}
