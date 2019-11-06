package kz.zhabassov.project.controller;

import kz.zhabassov.project.service.GameService;
import kz.zhabassov.project.service.PlayerService;
import kz.zhabassov.project.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
    @Autowired
    private GameService gameService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private TeamService teamService;

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String showHomePage(Model model) {
        model.asMap().put("teams", teamService.showFirstFive());
        model.asMap().put("players", playerService.showFiveWithMaxPenalties());
        model.asMap().put("games", gameService.showLastFiveGames());
        return "home";
    }

    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }

    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }

    public void setTeamService(TeamService teamService) {
        this.teamService = teamService;
    }
}
