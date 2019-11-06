package kz.zhabassov.project.controller;

import kz.zhabassov.project.entity.Player;
import kz.zhabassov.project.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/players")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @RequestMapping(method = RequestMethod.GET)
    public String showAllTeams(Model model) {
        model.asMap().put(
                "players",
                playerService.showAllPlayers())
        ;
        return "allPlayers";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView openFormAddNewTeam() {
        ModelAndView model = new ModelAndView("newPlayer");
        model.getModelMap().addAttribute("newPlayer", new Player());
        return model;
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String addNewTeam(@ModelAttribute Player newPlayer) {
        playerService.addPlayer(newPlayer);
        return "redirect:/players";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView showTeam(@PathVariable int id) {
        ModelAndView model = new ModelAndView("player");
        model.getModelMap().addAttribute("player", playerService.showByID(id));
        return model;
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public ModelAndView showUpdateTeam(@PathVariable int id, @ModelAttribute Player updatePlayer) {
        ModelAndView model = new ModelAndView("updatePlayer");
        model.getModelMap().addAttribute("updatePlayer", playerService.showByID(id));
        return model;
    }

    @RequestMapping(value = "/{id}/submit", method = RequestMethod.POST)
    public String updateTeam(@PathVariable int id, @ModelAttribute Player updatePlayer) {
        updatePlayer.setPlayerId(id);
        playerService.updatePlayer(updatePlayer);
        return "redirect:/players";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String showUpdateTeam(@PathVariable int id) {
        playerService.deletePlayer(id);
        return "redirect:/players";
    }

    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }
}
