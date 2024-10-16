package dev.pedrovs.view;

import dev.pedrovs.dao.AccountDao;
import dev.pedrovs.model.Account;

import java.util.List;

public class GetAllAccountsView {
    public static void main(String[] args) {
        try {
            AccountDao accountDao = new AccountDao();
            List<Account> accounts = accountDao.getAll();

            if (accounts.isEmpty()) {
                System.out.println("Nenhuma conta cadastrada.");
            } else {
                displayAccounts(accounts);
            }
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
        }
    }

    private static void displayAccounts(List<Account> accounts) {
        accounts.forEach(account -> {
            System.out.println("ID: " + account.getId());
            System.out.println("Número da conta: " + account.getAccountNumber());
            System.out.println("Tipo da conta: " + account.getAccountType());
            System.out.println("Saldo: " + account.getAmount());
            System.out.println("ID do cliente: " + account.getClientId());
            System.out.println("-------------------------------------------------");
        });
    }
}
