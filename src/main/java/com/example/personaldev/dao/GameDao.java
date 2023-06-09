package com.example.personaldev.dao;

import com.example.personaldev.entity.RankingH;
import com.example.personaldev.entity.RankingN;
import com.example.personaldev.entity.User;
import com.example.personaldev.entity.UserRecord;

import java.util.List;

public interface GameDao {
    public User login(String name, String password);
    public int add(String name, String password);
    public UserRecord data(String name);
    public int change(String before, String after);
    public List<RankingN> rankingN();
    public List<RankingH> rankingH();
    public int scoreText(String name, String time, int timeS);
    public int scoreTextH(String name, String time, int timeS);
}
