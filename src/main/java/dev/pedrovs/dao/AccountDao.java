package dev.pedrovs.dao;

import dev.pedrovs.exceptions.EntityNotFoundException;
import dev.pedrovs.factory.ConnectionFactory;
import dev.pedrovs.model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class AccountDao {

    private static final Logger LOGGER = Logger.getLogger(AccountDao.class.getName());

    public List<Account> getAll() {
        String sql = "SELECT * FROM TB_ACCOUNTS";
        List<Account> accounts = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                accounts.add(buildAccount(result));
            }

            if (accounts.isEmpty()) {
                throw new EntityNotFoundException("Nenhuma conta encontrada.");
            }

            return accounts;
        } catch (SQLException e) {
            LOGGER.severe("Erro ao buscar contas: " + e.getMessage());
            throw new RuntimeException(e);
        } catch (EntityNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(Account account) {
        String sql = "INSERT INTO TB_ACCOUNTS (ACCOUNT_NUMBER, ACCOUNT_TYPE, AMOUNT, CLIENT_ID) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, account.getAccountNumber());
            preparedStatement.setString(2, account.getAccountType());
            preparedStatement.setFloat(3, account.getAmount());
            preparedStatement.setString(4, account.getClientId());

            preparedStatement.executeUpdate();

            System.out.println("Conta criada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao inserir conta: " + e.getMessage());
        }
    }

    public void update(Account account) {
        String sql = "UPDATE TB_ACCOUNTS SET ACCOUNT_NUMBER = ?, ACCOUNT_TYPE = ?, AMOUNT = ? WHERE ID = ?";

        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, account.getAccountNumber());
            preparedStatement.setString(2, account.getAccountType());
            preparedStatement.setFloat(3, account.getAmount());
            preparedStatement.setString(4, account.getId());

            preparedStatement.executeUpdate();

            System.out.println("Conta atualizada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar conta: " + e.getMessage());
        }
    }

    public void delete(String id) {
        String sql = "DELETE FROM TB_ACCOUNTS WHERE ID = ?";

        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();

            System.out.println("Conta deletada com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao deletar conta: " + e.getMessage());
        }
    }

    public void deposit(String id, Float amount) {
        String sql = "UPDATE TB_ACCOUNTS SET AMOUNT = AMOUNT + ? WHERE ID = ?";

        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setFloat(1, amount);
            preparedStatement.setString(2, id);

            preparedStatement.executeUpdate();

            System.out.println("Depósito realizado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao realizar depósito: " + e.getMessage());
        }
    }

    public void withdraw(String id, Float amount) {
        String sql = "UPDATE TB_ACCOUNTS SET AMOUNT = AMOUNT - ? WHERE ID = ?";

        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setFloat(1, amount);
            preparedStatement.setString(2, id);

            preparedStatement.executeUpdate();

            System.out.println("Saque realizado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao realizar saque: " + e.getMessage());
        }
    }

    public void transfer(String originId, String destinationId, Float amount) {
        String sql = "UPDATE TB_ACCOUNTS SET AMOUNT = AMOUNT - ? WHERE ID = ?";

        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setFloat(1, amount);
            preparedStatement.setString(2, originId);

            preparedStatement.executeUpdate();

            sql = "UPDATE TB_ACCOUNTS SET AMOUNT = AMOUNT + ? WHERE ID = ?";

            try (PreparedStatement preparedStatement2 = connection.prepareStatement(sql)) {
                preparedStatement2.setFloat(1, amount);
                preparedStatement2.setString(2, destinationId);

                preparedStatement2.executeUpdate();

                System.out.println("Transferência realizada com sucesso!");
            } catch (SQLException e) {
                System.err.println("Erro ao realizar transferência: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.err.println("Erro ao realizar transferência: " + e.getMessage());
        }
    }

    public void payBill(String id, Float amount) {
        String sql = "UPDATE TB_ACCOUNTS SET AMOUNT = AMOUNT - ? WHERE ID = ?";

        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setFloat(1, amount);
            preparedStatement.setString(2, id);

            preparedStatement.executeUpdate();

            System.out.println("Pagamento realizado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao realizar pagamento: " + e.getMessage());
        }
    }

    private Account buildAccount(ResultSet result) throws SQLException {
        return new Account(
                result.getString("ID"),
                result.getString("ACCOUNT_NUMBER"),
                result.getString("ACCOUNT_TYPE"),
                result.getFloat("AMOUNT"),
                result.getDate("CREATED_AT").toLocalDate(),
                result.getString("CLIENT_ID")
        );
    }
}
