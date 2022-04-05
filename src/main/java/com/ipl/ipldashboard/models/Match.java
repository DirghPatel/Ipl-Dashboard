package com.ipl.ipldashboard.models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Match {

    @Id
    private long id;
    private String season;
    private String name;
    private String shortName;
    private String description;
    private String homeTeam;
    private String team1;
    private String team2;
    private String tossWon;
    private String decision;
    private String stInningScore;
    private String ndInningScore;
    private String homeScore;
    private String awayScore;
    private String winner;
    private String result;
    private LocalDate startDate;
    private String venueName;
    private String homeCaptain;
    private String awayCaptain;
    private String pom;
    private String points;
    private Boolean superOver;
    private String umpire1;
    private String umpire2;
    private String tvUmpire;
    private String referee;

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getTossWon() {
        return tossWon;
    }

    public void setTossWon(String tossWon) {
        this.tossWon = tossWon;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getStInningScore() {
        return stInningScore;
    }

    public void setStInningScore(String stInningScore) {
        this.stInningScore = stInningScore;
    }

    public String getNdInningScore() {
        return ndInningScore;
    }

    public void setNdInningScore(String ndInningScore) {
        this.ndInningScore = ndInningScore;
    }

    public String getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(String homeScore) {
        this.homeScore = homeScore;
    }

    public String getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(String awayScore) {
        this.awayScore = awayScore;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getHomeCaptain() {
        return homeCaptain;
    }

    public void setHomeCaptain(String homeCaptain) {
        this.homeCaptain = homeCaptain;
    }

    public String getAwayCaptain() {
        return awayCaptain;
    }

    public void setAwayCaptain(String awayCaptain) {
        this.awayCaptain = awayCaptain;
    }

    public String getPom() {
        return pom;
    }

    public void setPom(String pom) {
        this.pom = pom;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public Boolean getSuperOver() {
        return superOver;
    }

    public void setSuperOver(Boolean superOver) {
        this.superOver = superOver;
    }

    public String getUmpire1() {
        return umpire1;
    }

    public void setUmpire1(String umpire1) {
        this.umpire1 = umpire1;
    }

    public String getUmpire2() {
        return umpire2;
    }

    public void setUmpire2(String umpire2) {
        this.umpire2 = umpire2;
    }

    public String getTvUmpire() {
        return tvUmpire;
    }

    public void setTvUmpire(String tvUmpire) {
        this.tvUmpire = tvUmpire;
    }

    public String getReferee() {
        return referee;
    }

    public void setReferee(String referee) {
        this.referee = referee;
    }

}
