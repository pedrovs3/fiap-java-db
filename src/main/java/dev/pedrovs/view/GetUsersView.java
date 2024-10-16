package dev.pedrovs.view;

import dev.pedrovs.dao.ClientDao;
import dev.pedrovs.model.Client;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class GetUsersView {
    public static void main(String[] args) {
        try {
            ClientDao clientDao = new ClientDao();
            List<Client> clients = clientDao.getAll();

            if (clients.isEmpty()) {
                System.out.println("Nenhum cliente cadastrado.");
            } else {
                displayClients(clients);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao acessar o banco de dados: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
        }
    }

    private static void displayClients(List<Client> clients) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        clients.forEach(client -> {
            System.out.println("ID: " + client.getId());
            System.out.println("Nome: " + client.getName());
            System.out.println("Sobrenome: " + client.getLastName());
            System.out.println("Data de nascimento: " + client.getBirthdate().format(dateFormatter));
            System.out.println("Email: " + client.getEmail());
            System.out.println("Telefone: " + client.getPhone());
            System.out.println("Data de criação: " + client.getCreatedAt().format(dateFormatter));
            System.out.println("-------------------------------------------------");
        });
    }
}
