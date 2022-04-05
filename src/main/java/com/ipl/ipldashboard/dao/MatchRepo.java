package com.ipl.ipldashboard.dao;

import java.util.List;

import com.ipl.ipldashboard.models.Match;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MatchRepo extends CrudRepository<Match, Long> {

    List<Match> getByTeam1OrTeam2OrderByStartDateDesc(String teamName1, String teamName2 , Pageable pageable);

    default List<Match> findLatestMatchesByTeam(String teamName , int count){
        return getByTeam1OrTeam2OrderByStartDateDesc(teamName, teamName, PageRequest.of(0 , count));
    }

    // List<Match> getBySeasonAndTeam1OrTeam2(String season , String team1 , String team2);
    @Query(value = "select * from Match m where m.season = :year AND (m.team1 = :teamName OR m.team2 = :teamName) order by start_date desc" , nativeQuery = true)
    public List<Match> findMatchesBySeasons(@Param ("year") String year , @Param ("teamName") String teamName);
}
