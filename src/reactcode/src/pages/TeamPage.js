import { React, useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import { MatchDetailCard } from '../components/MatchDetailsCard';
import { MatchSmallCard } from '../components/MatchSmallCard';
import { PieChart } from 'react-minimal-pie-chart';
import "./TeamPage.scss";

export const TeamPage = () => {

    const [team, setTeam] = useState({ latestMatches: [] });
    const { teamName } = useParams();
    useEffect(
        () => {
            const fetchMatches = async () => {
                const response = await fetch(`http://localhost:8080/team/${teamName}`);
                const data = await response.json();
                setTeam(data);
            };
            fetchMatches();
        }, [teamName]
    );

    if(!team || !team.teamName){
        return <h1>Team not found</h1>
    }
    return (
        <div className="TeamPage">
            <div className='team-name-section'>
                <h1 className='team-name'>{team.teamName}</h1>
            </div>
            <div className='win-loss-section'>Wins/Losses
                <PieChart
                    data={[
                        { title: 'Losses', value: team.totalMatches - team.totalWins, color: '#58ff7f' },
                        { title: 'Wins', value: team.totalWins, color: '#ff5d5d' },
                    ]}
                />
            </div>
            <div className="match-detail-section">
                <h3>Latest Matches</h3>
                <MatchDetailCard match={team.latestMatches[0]} teamName = {team.teamShortName} />
            </div>
            {team.latestMatches.slice(1).map(latestMatches => <MatchSmallCard latestMatches={latestMatches} teamName = {team.teamShortName} key={team.id}/>)}
            <div className="more-link">
                {/* <a href="" >more..</a> */}
                <Link to={`/teams/${teamName}/matches/${process.env.REACT_APP_DATA_END_YEAR}`}>More...</Link>
            </div>
        </div>
    );
}

