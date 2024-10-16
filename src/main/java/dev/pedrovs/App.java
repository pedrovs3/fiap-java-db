package dev.pedrovs;

import dev.pedrovs.factory.ConnectionFactory;

import java.sql.Connection;

public class App {
    public static void main(String[] args) {
        try {
            Connection connection = ConnectionFactory.getInstance().getConnection();

            System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
        } catch (Exception e) {
            System.err.println(e.getMessage());

        }
    }
}
