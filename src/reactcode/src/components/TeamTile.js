import { React } from 'react';
import { Link } from 'react-router-dom';

import './TeamTile.scss';
export const TeamTile = ({ teamName , teamShortName , totalWins , totalMatches }) => {


    return (
        <div className="TeamTile">
            <h1>
                <Link to={`/teams/${teamShortName}`}>
                    {teamName}
                </Link>
            </h1>
            <h3>Total Matches {totalMatches}</h3>
            <h3>Total Wins {totalWins}</h3>
        </div>
    )
}