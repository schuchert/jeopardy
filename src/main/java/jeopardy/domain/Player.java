package jeopardy.domain;

import java.io.Serializable;

public class Player implements Serializable {
    private static final long serialVersionUID = -1489630667301687259L;
    private String name;
    private int balance;

    public Player() {
    }
    
    public Player(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }
        

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void receive(int value) {
        balance += value;
    }

    public void pay(int value) {
        balance -= value;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Player) {
            Player rhs = (Player) obj;
            return rhs.getName().equals(getName());
        }

        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public String toString() {
        return String.format("Player{%s, %d}", name, balance);
    }
}
