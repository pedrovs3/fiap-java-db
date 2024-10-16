package dev.pedrovs.dao;

import dev.pedrovs.exceptions.EntityNotFoundException;
import dev.pedrovs.factory.ConnectionFactory;
import dev.pedrovs.model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientDao {
    private static final Logger LOGGER = Logger.getLogger(ClientDao.class.getName());

    public void insert(Client client) {
        String sql = "INSERT INTO TB_CLIENTS (NAME, LAST_NAME, BIRTHDATE, EMAIL, PHONE, PASSWORD) " +
                "VALUES (?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?, ?)";

        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            setClientData(preparedStatement, client);
            preparedStatement.executeUpdate();

            LOGGER.info("Usuário cadastrado com sucesso!");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao cadastrar usuário: ", e);
            throw new RuntimeException(e);
        }
    }

    public List<Client> getAll() throws EntityNotFoundException {
        String sql = "SELECT * FROM TB_CLIENTS";
        List<Client> clients = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet result = preparedStatement.executeQuery()) {

            while (result.next()) {
                clients.add(buildClient(result));
            }

            if (clients.isEmpty()) {
                throw new EntityNotFoundException("Nenhum cliente encontrado.");
            }

            return clients;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao buscar usuários: ", e);
            throw new RuntimeException(e);
        }
    }

    public void delete(String id) {
        String sql = "DELETE FROM TB_CLIENTS WHERE ID = ?";

        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();

            LOGGER.info("Usuário deletado com sucesso!");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao deletar usuário: ", e);
            throw new RuntimeException(e);
        }
    }

    public void update(Client client) {
        String sql = "UPDATE TB_CLIENTS SET NAME = ?, LAST_NAME = ?, BIRTHDATE = TO_DATE(?, 'YYYY-MM-DD'), " +
                "EMAIL = ?, PHONE = ?, PASSWORD = ? WHERE ID = ?";

        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            setClientData(preparedStatement, client);
            preparedStatement.setString(7, client.getId());
            preparedStatement.executeUpdate();

            LOGGER.info("Usuário atualizado com sucesso!");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao atualizar usuário: ", e);
            throw new RuntimeException(e);
        }
    }

    public Optional<Client> getById(String id) {
        String sql = "SELECT * FROM TB_CLIENTS WHERE ID = ?";

        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, id);
            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                return Optional.of(buildClient(result));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao buscar usuário: ", e);
        }
        return Optional.empty();
    }

    public Optional<Client> getByEmail(String email) {
        String sql = "SELECT * FROM TB_CLIENTS WHERE EMAIL = ?";

        try (Connection connection = ConnectionFactory.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, email);
            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                return Optional.of(buildClient(result));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao buscar usuário por email: ", e);
        }
        return Optional.empty();
    }

    private Client buildClient(ResultSet result) throws SQLException {
        return new Client(
                result.getString("ID"),
                result.getString("NAME"),
                result.getString("LAST_NAME"),
                result.getDate("BIRTHDATE").toLocalDate(),
                result.getString("EMAIL"),
                result.getString("PHONE"),
                result.getString("PASSWORD"),
                result.getTimestamp("CREATED_AT").toLocalDateTime().toLocalDate()
        );
    }

    private void setClientData(PreparedStatement preparedStatement, Client client) throws SQLException {
        preparedStatement.setString(1, client.getName());
        preparedStatement.setString(2, client.getLastName());
        preparedStatement.setString(3, client.getBirthdate().toString());
        preparedStatement.setString(4, client.getEmail());
        preparedStatement.setString(5, client.getPhone());
        preparedStatement.setString(6, client.getPassword());
    }
}
