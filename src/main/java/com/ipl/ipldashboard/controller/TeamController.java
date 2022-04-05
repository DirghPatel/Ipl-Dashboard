package com.ipl.ipldashboard.controller;

import java.util.List;
import com.ipl.ipldashboard.dao.MatchRepo;
import com.ipl.ipldashboard.dao.TeamRepo;
import com.ipl.ipldashboard.models.Match;
import com.ipl.ipldashboard.models.Team;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TeamController {

    private TeamRepo teamRepo;
    private MatchRepo matchRepo;

    public TeamController(TeamRepo teamRepo, MatchRepo matchRepo) {
        this.teamRepo = teamRepo;
        this.matchRepo = matchRepo;
    }

    @GetMapping("team")
    public Iterable<Team> getAllTeam() {
        return this.teamRepo.findAll();
    }

    @GetMapping("team/{teamName}")
    public Team getTeam(@PathVariable String teamName) {
        Team team = this.teamRepo.findByTeamShortName(teamName.toUpperCase());
        team.setLatestMatches(this.matchRepo.findLatestMatchesByTeam(team.getTeamShortName(), 4));
        return team;
    }

    @GetMapping("team/{teamName}/matches/{year}")
    public List<Match> getMatchesByYear(@PathVariable String teamName, @PathVariable String year) {
        return matchRepo.findMatchesBySeasons(year, teamName.toUpperCase());
    }
}
