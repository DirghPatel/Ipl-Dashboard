import './App.scss';
import { TeamPage } from './pages/TeamPage';
import { MatchPage } from './pages/MatchPage';
import { HomePage } from './pages/HomePage';
import { BrowserRouter as Router, Route , Routes } from 'react-router-dom';
import {Switch} from 'react-router';

function App() {
  return (
    <div className="App">
      <Router>
        <Routes>
            <Route path='/teams/:teamName/matches/:year' element={<MatchPage />}></Route>
            <Route path='/teams/:teamName' element={<TeamPage />}></Route>
            <Route path='/' element={<HomePage />}></Route>
        </Routes>
      </Router>
    </div>
  );
}

export default App;
