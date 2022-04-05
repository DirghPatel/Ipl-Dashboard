package com.ipl.ipldashboard.dao;

import com.ipl.ipldashboard.models.Team;

import org.springframework.data.repository.CrudRepository;

public interface TeamRepo extends CrudRepository<Team , Long>{

    Team findByTeamShortName(String teamName);
}