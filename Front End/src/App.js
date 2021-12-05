import { BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import Login from "./pages/app-portal/Login";
import Home from "./pages/app-portal/Home";
import ManageAnimals from "./pages/app-portal/ManageAnimals";
import RequestAnimal from "./pages/animal/RequestAnimal";
import ViewRequests from './pages/animal/ViewRequests';
import Comments from './pages/animal/Comments';
import Reminders from './pages/animal/Reminders';


function App() {
  return (
        <Router>
            <Routes>
                <Route path='/' element={<Login/>}/>
                <Route path='/home' element={<Home />}/>
                <Route path='/manage-animal' element={<ManageAnimals/>}/>
                <Route path='/request-animal' element={<RequestAnimal/>}/>
                <Route path='/view-requests' element={<ViewRequests/>}/>
                <Route path='/animal-comments' element={<Comments/>}/> 
                <Route path='/animal-reminders' element={<Reminders/>}/> 
            </Routes>
        </Router>
    );
}

export default App;
