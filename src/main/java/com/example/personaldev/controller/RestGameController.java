package com.example.personaldev.controller;

import com.example.personaldev.service.GameService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class RestGameController {
    @Autowired
    GameService gameService;
    @Autowired
    private HttpSession session;

    @GetMapping("/addCheck")
    public String addCheck(@RequestParam(name="name")String name, @RequestParam("password")String pass) {
        if(pass.length() < 4 || pass.length() > 20 || name.length() > 10) {
            return "error";
        }
        try {
            var i = gameService.add(name, pass);
            System.out.println(i);
            return "ok!";
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/playTest-score")
    public void timeUpdate(@RequestParam(name="m")String m, @RequestParam(name="s")String s, @RequestParam(name="stopWatch")String time) {
        var name = session.getAttribute("userS").toString();

        var nn = Integer.parseInt(m);
        var ss = Integer.parseInt(s);
        var timeSeconds = nn * 6 + ss;

        var i = gameService.scoreText(name, time, timeSeconds);
    }

    @GetMapping("/playTest-scoreH")
    public void timeUpdateH(@RequestParam(name="m")String m, @RequestParam(name="s")String s, @RequestParam(name="stopWatch")String time) {
        var name = session.getAttribute("userS").toString();

        var nn = Integer.parseInt(m);
        var ss = Integer.parseInt(s);
        var timeSeconds = nn * 6 + ss;

        var i = gameService.scoreTextH(name, time, timeSeconds);
    }
}
