package kz.zhabassov.project.controller;

import kz.zhabassov.project.entity.Team;
import kz.zhabassov.project.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping(value = "/teams")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<Team> showAllTeams(Model model) {
        model.asMap().put("teams", teamService.showAll());
        return teamService.showAll();
    }

    @GetMapping(value = "/new")
    public ModelAndView openFormAddNewTeam() {
        ModelAndView model = new ModelAndView("newTeam");
        model.getModelMap().addAttribute("newTeam", new Team());
        return model;

    }

    @PostMapping(value = "/submit")
    public String addNewTeam(@ModelAttribute Team newTeam) {
        teamService.insert(newTeam);
        return "redirect:/teams";
    }

    @GetMapping(value = "/{team}")
    public ModelAndView showTeam(@PathVariable String team) {
        ModelAndView model = new ModelAndView("team");
        model.getModelMap().addAttribute("team", teamService.findByTeam(team));
        return model;
    }

    @GetMapping(value = "/{team}/update")
    public ModelAndView showUpdateTeam(@PathVariable String team, @ModelAttribute Team updateTeam) {
        ModelAndView model = new ModelAndView("updateTeam");
        model.getModelMap().addAttribute("updateTeam", teamService.findByTeam(team));
        return model;
    }

    @PostMapping(value = "/{team}/submit")
    public String updateTeam(@PathVariable String team, @ModelAttribute Team updateTeam) {
        teamService.update(updateTeam);
        return "redirect:/teams";
    }

    @PostMapping(value = "/{team}/delete")
    public String showUpdateTeam(@PathVariable String team) {
        teamService.deleteTeam(team);
        return "redirect:/teams";
    }

    public void setTeamService(TeamService teamService) {
        this.teamService = teamService;
    }
}
