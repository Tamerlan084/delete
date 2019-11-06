package kz.zhabassov.project.controller;

import kz.zhabassov.project.entity.Game;
import kz.zhabassov.project.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/games")
public class GameController {
    @Autowired
    private GameService gameService;

    @RequestMapping(method = RequestMethod.GET)
    public String showAllGames(Model model) {
        model.asMap().put("games", gameService.showAllGames());
        return "allGames";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView openFormAddNewGame() {
        ModelAndView model = new ModelAndView("newGame");
        model.getModelMap().addAttribute("newGame", new Game());
        return model;
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String addNewGame(@ModelAttribute Game newGame) {
        gameService.addGame(newGame);
        return "redirect:/games";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView showTeam(@PathVariable int id) {
        ModelAndView model = new ModelAndView("game");
        model.getModelMap().addAttribute("game", gameService.showByID(id));
        return model;
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public ModelAndView showUpdateTeam(@PathVariable int id, @ModelAttribute Game updateGame) {
        ModelAndView model = new ModelAndView("updateGame");
        model.getModelMap().addAttribute("updateGame", gameService.showByID(id));
        return model;
    }

    @RequestMapping(value = "/{id}/submit", method = RequestMethod.POST)
    public String updateTeam(@PathVariable int id, @ModelAttribute Game updateGame) {
        updateGame.setGameId(id);
        gameService.updateScore(updateGame);
        return "redirect:/games";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String showUpdateTeam(@PathVariable int id) {
        gameService.deleteGame(id);
        return "redirect:/games";
    }

    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }
}
