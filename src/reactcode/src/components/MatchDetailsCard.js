import { React } from 'react';
import { Link } from 'react-router-dom';
import "./MatchDetailsCard.scss";

export const MatchDetailCard = ({ match, teamName }) => {
    if (!match) return null;
    const otherTeam = match.team1 === teamName ? match.team2 : match.team1;
    const teamRoute = `/teams/${otherTeam}`;
    const isMatchWon = teamName === match.winner;
    return (
        <div className={isMatchWon ? 'MatchDetailCard won-card' : 'MatchDetailCard lost-card'}>
            <div className="match-data">
                <span className="vs">vs</span>
                <h1 ><Link to={teamRoute} className='team-small-name'>{otherTeam}</Link></h1>
                <h2 className="match-date">{match.startDate}</h2>
                <h3 className="match-venue">at {match.venueName}</h3>
                <h3 className="match-result">{match.result}</h3>
            </div>
            <div className="additional-detail">
                <h3>First Innings</h3>
                <p>{match.team1}</p>
                <h3>Second Innings</h3>
                <p>{match.team2}</p>
                <h3>Man of the match</h3>
                <p>{match.pom}</p>
                <h3>Umpires</h3>
                <p>{match.umpire1}, {match.umpire2}</p>
            </div>
        </div>
    );
}

