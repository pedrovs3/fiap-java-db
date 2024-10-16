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

public class ClientDao {
    private final Connection connection;

    public ClientDao() throws SQLException {
        this.connection = ConnectionFactory.getInstance().getConnection();
    }

    public void insert(Client client) throws SQLException {
        String sql = "INSERT INTO TB_CLIENTS (NAME, LAST_NAME, BIRTHDATE, EMAIL, PHONE, PASSWORD) " +
                "VALUES (?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?, ?)";

        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {

            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getLastName());
            preparedStatement.setString(3, client.getBirthdate().toString());
            preparedStatement.setString(4, client.getEmail());
            preparedStatement.setString(5, client.getPhone());
            preparedStatement.setString(6, client.getPassword());

            preparedStatement.executeUpdate();

            System.out.println("Usuário cadastrado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }

    public List<Client> getAll() throws SQLException, EntityNotFoundException {
        String sql = "SELECT * FROM TB_CLIENTS";

        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            ResultSet result = preparedStatement.executeQuery();

            List<Client> clients = new ArrayList<>();

            while (result.next()) {
                Client client = new Client(
                        result.getString("ID"),
                        result.getString("NAME"),
                        result.getString("LAST_NAME"),
                        result.getDate("BIRTHDATE").toLocalDate(),
                        result.getString("EMAIL"),
                        result.getString("PHONE"),
                        result.getString("PASSWORD"),
                        result.getTimestamp("CREATED_AT").toLocalDateTime().toLocalDate()
                );

                clients.add(client);
            }

            return clients;
        } catch (SQLException e) {
            throw new EntityNotFoundException("Erro ao buscar usuários:" + e.getMessage());
        }
    }
}
