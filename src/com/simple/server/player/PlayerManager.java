package com.simple.server.player;

import java.util.HashMap;

// 管理玩家数据
// 玩家登录时从数据库读取数据写到内存
// 玩家登出时去掉数据

public class PlayerManager {

    private static PlayerManager playerManager = new PlayerManager();
    private PlayerManager(){
        // To Do
        // read data to playerList from database
    }
    public static PlayerManager getPlayerManager() {
        return playerManager;
    }

    private HashMap<Integer, Player> playerList;

    public HashMap<Integer, Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(HashMap<Integer, Player> playerList) {
        this.playerList = playerList;
    }
}
