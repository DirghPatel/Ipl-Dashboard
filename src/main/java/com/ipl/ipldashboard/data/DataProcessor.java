package com.ipl.ipldashboard.data;

import java.time.LocalDate;

import com.ipl.ipldashboard.models.Match;

import org.springframework.batch.item.ItemProcessor;

public class DataProcessor implements ItemProcessor<MatchData, Match> {

    @Override
    public Match process(final MatchData inputData) throws Exception {

        Match match = new Match();

        match.setId(Long.parseLong(inputData.getId()));
        match.setSeason(inputData.getSeason());
        match.setName(inputData.getName());
        match.setShortName(inputData.getShort_name());
        match.setDescription(inputData.getDescription());
        match.setHomeTeam(inputData.getHome_team());
        match.setTeam1(inputData.getToss_won());

        if (inputData.getToss_won() == inputData.getHome_team() && inputData.getDecision().equals("BAT FIRST")) {
            match.setTeam1(inputData.getHome_team());
            match.setTeam2(inputData.getAway_team());
            // match.setTeam1(convertFullName(inputData.getHome_team()));
            // match.setTeam2(convertFullName(inputData.getAway_team()));
        } else if (inputData.getToss_won() == inputData.getHome_team()
                && inputData.getDecision().equals("BOWL FIRST")) {
            match.setTeam1(inputData.getAway_team());
            match.setTeam2(inputData.getHome_team());
            // match.setTeam1(convertFullName(inputData.getAway_team()));
            // match.setTeam2(convertFullName(inputData.getHome_team()));
        } else if (inputData.getToss_won() == inputData.getAway_team() && inputData.getDecision().equals("BAT FIRST")) {
            match.setTeam1(inputData.getAway_team());
            match.setTeam2(inputData.getHome_team());
            // match.setTeam1(convertFullName(inputData.getAway_team()));
            // match.setTeam2(convertFullName(inputData.getHome_team()));
        } else {
            match.setTeam1(inputData.getHome_team());
            match.setTeam2(inputData.getAway_team());
            // match.setTeam1(convertFullName(inputData.getHome_team()));
            // match.setTeam2(convertFullName(inputData.getAway_team()));
        }
        match.setTossWon(inputData.getToss_won());
        match.setDecision(inputData.getDecision());
        match.setStInningScore(inputData.getSt_inning_score());
        match.setNdInningScore(inputData.getNd_inning_score());
        match.setHomeScore(inputData.getHome_score());
        match.setAwayScore(inputData.getAway_score());
        match.setWinner(inputData.getWinner());
        match.setResult(inputData.getResult());
        match.setStartDate(LocalDate.parse(inputData.getStart_date().substring(0, 10)));
        match.setVenueName(inputData.getVenue_name());
        match.setHomeCaptain(inputData.getHome_captain());
        match.setAwayCaptain(inputData.getAway_captain());
        match.setPom(inputData.getPom());
        match.setPoints(inputData.getPoints());
        match.setSuperOver(Boolean.parseBoolean(inputData.getSuper_over()));
        match.setUmpire1(inputData.getUmpire1());
        match.setUmpire2(inputData.getUmpire2());
        match.setTvUmpire(inputData.getTv_umpire());
        match.setReferee(inputData.getReferee());
        return match;
    }

    public String convertFullName(String teamShortName){

        if(teamShortName.equals("RR"))
          return "Rajasthan Royals";
        else if(teamShortName.equals("PWI"))
            return "Pune Warriors";
        else if(teamShortName.equals("GL"))
            return "Gujarat Lions"; 
        else if(teamShortName.equals("SRH"))
            return "Sunrisers Hydrabad";
        else if(teamShortName.equals("RCB"))
            return "Royal Challengers Banglore";
        else if(teamShortName.equals("KKR"))
            return "Kolkata Knight Riders";
        else if(teamShortName.equals("Kochi"))
            return "Kochi Tuskers Kerala";
        else if(teamShortName.equals("RPS"))
            return "Rising Pune Supergiant";
        else if(teamShortName.equals("CSK"))
            return "Chennai Super Kings";
        else if(teamShortName.equals("MI"))
            return "Mumbai Indians";
        else if(teamShortName.equals("DC"))
            return "Delhi Capitals";
        else if(teamShortName.equals("PBKS"))
            return "Punjab Kings";
        else{
            return "";
        }

    }

}
