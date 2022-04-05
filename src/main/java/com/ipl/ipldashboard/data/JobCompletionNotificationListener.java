package com.ipl.ipldashboard.data;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.ipl.ipldashboard.models.Team;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

  private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

  private final EntityManager em;

  @Autowired
  public JobCompletionNotificationListener(EntityManager em) {
    this.em = em;
  }

  @Override
  @Transactional
  public void afterJob(JobExecution jobExecution) {
    if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
      log.info("!!! JOB FINISHED! Time to verify the results");

      Map<String, Team> teamData = new HashMap<>();

      em.createQuery("select m.team1 , count(*) from Match m group by m.team1", Object[].class)
          .getResultList()
          .stream()
          .map(e -> new Team((String) e[0], (long) e[1]))
          .forEach(team -> teamData.put(team.getTeamShortName(), team));

      em.createQuery("select m.team1 , count(*) from Match m group by m.team1", Object[].class)
          .getResultList()
          .stream()
          .forEach(e -> {
            Team team = teamData.get((String) e[0]);
            team.setTotalMatches(team.getTotalMatches() + (long) e[1]);
          });

      em.createQuery("select m.winner , count(*) from Match m group by m.winner", Object[].class)
          .getResultList()
          .stream()
          .forEach(e -> {
            Team team = teamData.get((String) e[0]);
            if (team != null) {
              team.setTotalWins((long) e[1]);
            }
          });

      teamData.forEach((k, v) -> {
        Team team = teamData.get(k);
        if (k.equals("RR"))
          team.setTeamName("Rajasthan Royals");
        else if (k.equals("PWI"))
          team.setTeamName("Pune Warriors");
        else if (k.equals("GL"))
          team.setTeamName("Gujarat Lions");
        else if (k.equals("SRH"))
          team.setTeamName("Sunrisers Hydrabad");
        else if (k.equals("RCB"))
          team.setTeamName("Royal Challengers Banglore");
        else if (k.equals("KKR"))
          team.setTeamName("Kolkata Knight Riders");
        else if (k.equals("Kochi"))
          team.setTeamName("Kochi Tuskers Kerala");
        else if (k.equals("RPS"))
          team.setTeamName("Rising Pune Supergiant");
        else if (k.equals("CSK"))
          team.setTeamName("Chennai Super Kings");
        else if (k.equals("MI"))
          team.setTeamName("Mumbai Indians");
        else if (k.equals("DC"))
          team.setTeamName("Delhi Capitals");
        else if (k.equals("PBKS"))
          team.setTeamName("Punjab Kings");
        else {
          team.setTeamName("");
        }
      });

      teamData.values().forEach(team -> em.persist(team));
      teamData.values().forEach(team -> System.out.println(team));

      // System.out.println("-------");
      // teamData.forEach((k, v) -> {
      // Team team = teamData.get(k);
      // team.setTeamName();
      // System.out.println(team);
      // });
    }
  }
}

// season,id,name,short_name,description,home_team,away_team,toss_won,decision,st_inning_score,nd_inning_score,home_score,away_score,winner,result,start_date,end_date,venue_id,venue_name,home_captain,away_captain,pom,points,super_over,home_overs,home_runs,home_wickets,home_boundaries,away_overs,away_runs,away_wickets,away_boundaries,highlights,home_key_batsman,home_key_bowler,home_playx1,away_playx1,away_key_batsman,away_key_bowler,match_days,umpire1,umpire2,tv_umpire,referee,reserve_umpire