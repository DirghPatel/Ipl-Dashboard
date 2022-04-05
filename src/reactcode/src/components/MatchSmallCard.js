import { React } from 'react';
import {Link } from 'react-router-dom';
import "./MatchSmallCard.scss";

export const MatchSmallCard = ({ latestMatches , teamName }) => {
    const otherTeam = latestMatches.team1 === teamName ? latestMatches.team2 : latestMatches.team1 ;
    const teamRoute = `/teams/${otherTeam}`;
    const isMatchWon = teamName === latestMatches.winner;

    return (
        <div className={isMatchWon ? 'MatchSmallCard won-small-card' : 'MatchSmallCard lost-small-card'}>
            <h2 className='team-small-inner'>vs&nbsp;
                <span className='team-small-name'>
                    <Link to= {teamRoute}>
                        { otherTeam }
                    </Link>
                </span> 
            </h2>
            <h3 className='team-small-inner'>{latestMatches.result}</h3>
        </div>
    );
}

