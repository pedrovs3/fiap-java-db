package dev.pedrovs.dao;

import dev.pedrovs.factory.ConnectionFactory;
import dev.pedrovs.model.TransactionType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransactionTypeDao {
    private static final Logger LOGGER = Logger.getLogger(TransactionTypeDao.class.getName());

    public boolean insert(TransactionType transactionType) {
        String sql = "INSERT INTO TB_TRANSACTION_TYPE (NAME) VALUES (?)";

        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, transactionType.getName());
            int rowsAffected = preparedStatement.executeUpdate();

            LOGGER.info("Tipo de transação '" + transactionType.getName() + "' cadastrado com sucesso!");
            return rowsAffected > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao cadastrar tipo de transação: ", e);
            return false;
        }
    }

    public Optional<TransactionType> getById(String id) {
        String sql = "SELECT * FROM TB_TRANSACTION_TYPE WHERE ID = ?";
        return getTransactionType(sql, id);
    }

    public Optional<TransactionType> getByName(String name) {
        String sql = "SELECT * FROM TB_TRANSACTION_TYPE WHERE NAME = ?";
        return getTransactionType(sql, name);
    }

    private Optional<TransactionType> getTransactionType(String sql, String param) {
        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, param);
            try (ResultSet result = preparedStatement.executeQuery()) {
                if (result.next()) {
                    return Optional.of(buildTransactionType(result));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao buscar tipo de transação: ", e);
            throw new RuntimeException(e);
        }
    }

    public boolean delete(String id) {
        String sql = "DELETE FROM TB_TRANSACTION_TYPE WHERE ID = ?";

        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, id);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                LOGGER.info("Tipo de transação com ID '" + id + "' deletado com sucesso!");
                return true;
            } else {
                LOGGER.warning("Nenhum tipo de transação encontrado para deletar com ID: " + id);
                return false;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao deletar tipo de transação: ", e);
            throw new RuntimeException(e);
        }
    }

    private TransactionType buildTransactionType(ResultSet result) throws SQLException {
        return new TransactionType(
                result.getString("ID"),
                result.getString("NAME")
        );
    }
}
