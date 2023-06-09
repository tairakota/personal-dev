package com.example.personaldev.service;

import com.example.personaldev.dao.GameDao;
import com.example.personaldev.entity.RankingH;
import com.example.personaldev.entity.RankingN;
import com.example.personaldev.entity.User;
import com.example.personaldev.entity.UserRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PgGameService implements GameService{
    @Autowired
    private GameDao gameDao;

    @Override
    public User login(String name, String password) {
        return gameDao.login(name, password);
    }

    @Override
    public int add(String name, String password) {
        return gameDao.add(name, password);
    }

    @Override
    public UserRecord data(String name) {
        return gameDao.data(name);
    }

    @Override
    public int change(String before, String after) {
        return gameDao.change(before, after);
    }

    @Override
    public List<RankingN> rankingN() {
        return gameDao.rankingN();
    }

    @Override
    public List<RankingH> rankingH() {
        return gameDao.rankingH();
    }

    @Override
    public int scoreText(String name, String time, int timeS) {
        return gameDao.scoreText(name, time, timeS);
    }

    @Override
    public int scoreTextH(String name, String time, int timeS) {
        return gameDao.scoreTextH(name, time, timeS);
    }
}
