import { BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import Login from "./pages/app-portal/Login";
import Home from "./pages/app-portal/Home";
import ManageAnimals from "./pages/app-portal/ManageAnimals";
import RequestAnimal from "./pages/animal/RequestAnimal";
import ViewRequests from './pages/animal/ViewRequests';
import AddAnimal from "./pages/app-portal/AddAnimal";
import AddUser from "./pages/user/AddUser";
import EditUser from "./pages/user/EditUser";
import BlockUser from "./pages/user/BlockUser";


function App() {
  return (
        <Router>
            <Routes>
                <Route path='/' element={<Login/>}/>
                <Route path='/home' element={<Home />}/>
                <Route path='/add-animal' element={<AddAnimal/>}/>
                <Route path='/manage-animal' element={<ManageAnimals/>}/>
                <Route path='/request-animal' element={<RequestAnimal/>}/>
                <Route path='/view-requests' element={<ViewRequests/>}/>
                <Route path='/add-user' element={<AddUser/>}/>
                <Route path='/edit-user' element={<EditUser/>}/>
                <Route path='/block-user' element={<BlockUser/>}/>
            </Routes>
        </Router>
    );
}

export default App;
