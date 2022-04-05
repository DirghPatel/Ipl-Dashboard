package com.ipl.ipldashboard.data;

import javax.sql.DataSource;

import com.ipl.ipldashboard.models.Match;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private final String[] FIELD_NAMES = new String[] {
            "season", "id", "name", "short_name", "description", "home_team", "away_team", "toss_won", "decision",
            "st_inning_score", "nd_inning_score", "home_score", "away_score", "winner", "result", "start_date",
            "end_date", "venue_id", "venue_name", "home_captain", "away_captain", "pom", "points", "super_over",
            "home_overs", "home_runs", "home_wickets", "home_boundaries", "away_overs", "away_runs", "away_wickets",
            "away_boundaries", "highlights", "home_key_batsman", "home_key_bowler", "home_playx1", "away_playx1",
            "away_key_batsman", "away_key_bowler", "match_days", "umpire1", "umpire2", "tv_umpire", "referee",
            "reserve_umpire"
    };

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<MatchData> reader() {
        return new FlatFileItemReaderBuilder<MatchData>()
                .name("personItemReader")
                .resource(new ClassPathResource("all_season_summary.csv"))
                .delimited()
                .names(FIELD_NAMES)
                .fieldSetMapper(new BeanWrapperFieldSetMapper<MatchData>() {
                    {
                        setTargetType(MatchData.class);
                    }
                })
                .build();
    }

    @Bean
    public DataProcessor processor() {
        return new DataProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Match> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Match>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO match (id,season,name,short_name,description,home_team,team1,team2,toss_won,decision,st_inning_score,nd_inning_score,home_score,away_score,winner,result,start_date,venue_name,home_captain,away_captain,pom,points,super_over,umpire1,umpire2,tv_umpire,referee) VALUES (:id,:season,:name,:shortName,:description,:homeTeam,:team1,:team2,:tossWon,:decision,:stInningScore,:ndInningScore,:homeScore,:awayScore,:winner,:result,:startDate,:venueName,:homeCaptain,:awayCaptain,:pom,:points,:superOver,:umpire1,:umpire2,:tvUmpire,:referee)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<Match> writer) {
        return stepBuilderFactory.get("step1")
                .<MatchData, Match>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }

}
