package com.example.personaldev.dao;

import com.example.personaldev.entity.RankingH;
import com.example.personaldev.entity.RankingN;
import com.example.personaldev.entity.User;
import com.example.personaldev.entity.UserRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PgGameDao implements GameDao{
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public User login(String name, String password) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("name", name);
        param.addValue("pass", password);
        var list = jdbcTemplate.query("select name from users where name = :name and pass = :pass", param, new DataClassRowMapper<>(User.class));
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public int add(String name, String password) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("name", name);
        param.addValue("pass", password);
        var ok = jdbcTemplate.update("insert into users (name, pass, time_normal, time_normals, time_hard, time_hards) values (:name, :pass, '99:99', 693, '99:99', 693)", param);
        return ok;
    }

    @Override
    public UserRecord data(String name) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("name", name);
        var list = jdbcTemplate.query("select name, time_normal, time_hard from users where name = :name", param, new DataClassRowMapper<>(UserRecord.class));
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public int change(String before, String after) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("before", before);
        param.addValue("after", after);
        var ok = jdbcTemplate.update("update users set name = :after where name = :before", param);
        return ok;
    }

    @Override
    public List<RankingN> rankingN() {
        var list = jdbcTemplate.query("select name, time_normal from users where time_normal is not null order by time_normals limit 5", new DataClassRowMapper<>(RankingN.class));
        return list;
    }

    @Override
    public List<RankingH> rankingH() {
        var list = jdbcTemplate.query("select name, time_hard from users where time_hard is not null order by time_hards limit 5", new DataClassRowMapper<>(RankingH.class));
        return list;
    }

    @Override
    public int scoreText(String name, String time, int timeS) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("name", name);
        param.addValue("time", time);
        param.addValue("timeS", timeS);
        var ok = jdbcTemplate.update("update users set time_normal = :time, time_normals = :timeS where name = :name and time_normals > :timeS", param);
        return ok;
    }

    @Override
    public int scoreTextH(String name, String time, int timeS) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("name", name);
        param.addValue("time", time);
        param.addValue("timeS", timeS);
        var ok = jdbcTemplate.update("update users set time_hard = :time, time_hards = :timeS where name = :name and time_hards > :timeS", param);
        return ok;
    }

}
