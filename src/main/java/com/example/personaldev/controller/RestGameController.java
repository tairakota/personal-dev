package com.example.personaldev.controller;

import com.example.personaldev.service.GameService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestGameController {
    @Autowired
    GameService gameService;
    @Autowired
    private HttpSession session;

    @GetMapping("/playTest-score")
    public void timeUpdate(@RequestParam(name="m")String n, @RequestParam(name="s")String s, @RequestParam(name="stopWatch")String time) {
        var name = session.getAttribute("userS").toString();

        var nn = Integer.parseInt(n);
        var ss = Integer.parseInt(s);
        var timeSeconds = nn * 6 + ss;

        var i = gameService.scoreText(name, time, timeSeconds);
    }

    @GetMapping("/playTest-scoreH")
    public void timeUpdateH(@RequestParam(name="m")String n, @RequestParam(name="s")String s, @RequestParam(name="stopWatch")String time) {
        var name = session.getAttribute("userS").toString();

        var nn = Integer.parseInt(n);
        var ss = Integer.parseInt(s);
        var timeSeconds = nn * 6 + ss;

        var i = gameService.scoreTextH(name, time, timeSeconds);
    }
}
