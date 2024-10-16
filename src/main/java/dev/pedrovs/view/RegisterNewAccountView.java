package dev.pedrovs.view;

import dev.pedrovs.dao.AccountDao;
import dev.pedrovs.dao.ClientDao;
import dev.pedrovs.model.Account;
import dev.pedrovs.model.Client;

import java.util.List;

public class RegisterNewAccountView {
    public static void main(String[] args) {
        try {
            AccountDao accountDao = new AccountDao();
            ClientDao clientDao = new ClientDao();

            List<Client> clients = clientDao.getAll();

            // Assuming that the first client is the one that we want to create an account for
            Account account = new Account("123456", "Conta Corrente", 1000.0f, clients.get(0).getId());

            accountDao.insert(account);
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
        }

    }


}
