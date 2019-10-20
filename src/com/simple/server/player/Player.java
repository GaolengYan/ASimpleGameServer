package com.simple.server.player;

import java.net.Socket;

public class Player {
    private int accName;
    private int id;
    private String name;
    private int x;
    private int y;
    private Socket socket;

    public static Player login(int accName){
        // 从数据库中读数据出来给Player
        int id = 1;
        String name = "Test";
        int x = 0;
        int y = 0;

        Player player = new Player();
        player.setAccName(accName);
        player.setId(id);
        player.setName(name);
        player.setX(x);
        player.setY(y);

        return player;
    }

    public int getAccName() {
        return accName;
    }

    public void setAccName(int accName) {
        this.accName = accName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
