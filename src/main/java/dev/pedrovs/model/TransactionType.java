package dev.pedrovs.model;

public class TransactionType {
    String id;
    String name;

    public TransactionType(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public TransactionType(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "TransactionType{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
