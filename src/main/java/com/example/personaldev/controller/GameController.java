package com.example.personaldev.controller;

import com.example.personaldev.entity.PlayerData;
import com.example.personaldev.form.AddForm;
import com.example.personaldev.form.ChangeForm;
import com.example.personaldev.form.LoginForm;
import com.example.personaldev.service.GameService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GameController {

    @Autowired
    private GameService gameService;
    @Autowired
    private HttpSession session;

    @GetMapping("/title")
    public String title() {
        return "title";
    }

    @GetMapping("/login")
    public String login(@ModelAttribute("loginForm") LoginForm loginForm) {
        return "login";
    }
    @PostMapping("/login")
    public String loginCheck(@ModelAttribute("loginForm") LoginForm loginForm) {
        var user = gameService.login(loginForm.getLoginName(), loginForm.getLoginPass());
        if(user == null) {
            return "redirect:/title";
        }
        session.setAttribute("userS", user.name());
        System.out.println(session.getAttribute("userS"));
        return "redirect:/menu";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/title";
    }

    @GetMapping("/add")
    public String add() {
        return "add";
    }
//    @PostMapping("/add")
//    public String addCheck(@Validated @ModelAttribute("addForm") AddForm addForm, BindingResult bindingResult) {
//        if(bindingResult.hasErrors()) {
//            return "add";
//        }
//        var i = gameService.add(addForm.getAddName(), addForm.getAddPass());
//        System.out.println(i);
//        return "redirect:/title";
//    }

    @GetMapping("/menu")
    public String menu() {
        if(session.getAttribute("userS") == null) {
            return "redirect:/title";
        }
        return "menu";
    }

    @GetMapping("/level")
    public String level() {
        if(session.getAttribute("userS") == null) {
            return "redirect:/title";
        }
        return "level";
    }
    @PostMapping("/level")
    public String levelCheck(@RequestParam(name="select")String level) {
        System.out.println(level);
        session.setAttribute("level", level);
        return "redirect:/play";
    }

    @GetMapping("/play")
    public String play(Model model) {
        if(session.getAttribute("level") == null) {
            return "redirect:/level";
        }
        var level = session.getAttribute("level").toString();
        model.addAttribute("level", level);
        return "play";
    }

    @GetMapping("/playerData")
    public String playerData(@ModelAttribute("playerData")PlayerData playerData) {
        if(session.getAttribute("userS") == null) {
            return "redirect:/title";
        }
        var name = session.getAttribute("userS");
        var data = gameService.data(name.toString());
        playerData.setPlayerName(data.name());
        playerData.setPlayerTime_n(data.time_normal());
        playerData.setPlayerTime_h(data.time_hard());
        return "playerData";
    }
    @GetMapping("/change")
    public String change(@ModelAttribute("changeForm") ChangeForm changeForm) {
        if(session.getAttribute("userS") == null) {
            return "redirect:/title";
        }

        return "change";
    }
    @PostMapping("/change")
    public String changeCheck(@Validated @ModelAttribute("changeForm") ChangeForm changeForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "change";
        }
        var before = session.getAttribute("userS").toString();
        var after = changeForm.getChangeAfter();
        var i = gameService.change(before, after);
        session.setAttribute("userS", after);
        return "redirect:/playerData";
    }

    @GetMapping("/ranking")
    public String ranking(Model model) {
        if(session.getAttribute("userS") == null) {
            return "redirect:/title";
        }
        var rankN = gameService.rankingN();
        var rankH = gameService.rankingH();
        model.addAttribute("N", rankN);
        model.addAttribute("H", rankH);
        return "ranking";
    }
}
