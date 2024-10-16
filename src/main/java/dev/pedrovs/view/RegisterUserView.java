package dev.pedrovs.view;

import dev.pedrovs.dao.ClientDao;
import dev.pedrovs.model.Client;

import java.time.LocalDate;

public class RegisterUserView {

    public static void main(String[] args) {
        try {
            ClientDao clientDao = new ClientDao();
            Client client = new Client("Teste", "Teste2", LocalDate.of(2005, 8, 3), "Teste@hotmail.com", "11988220444", "1234567");

            clientDao.insert(client);
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
        }

    }
}
