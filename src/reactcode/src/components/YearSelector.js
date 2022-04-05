import { React } from 'react';
import { Link } from 'react-router-dom';
import "./YearSelector.scss";

export const YearSelector = ({teamName}) => {
    
    let years = [];
    // const startYear = 2008;
    // const endYear = 2021;

    const startYear = process.env.REACT_APP_DATA_START_YEAR;
    const endYear = process.env.REACT_APP_DATA_END_YEAR;

    for(let i = startYear ; i <= endYear ; i++){
        years.push(i);
    }
    return (
        <div className='YearSelector'>
            <h3> Select Year </h3>
            <ol className='YearSelectorOL'>
                {years.map(year => (
                    <li>
                        <Link to = {`/teams/${teamName}/matches/${year}`}>
                            {year}
                        </Link>
                    </li>

                ))}
            </ol>
        </div>
    );
}

