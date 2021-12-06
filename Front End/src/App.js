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

import Comments from './pages/animal/Comments';
import Reminders from './pages/animal/Reminders';
import HealthRecords from './pages/animal/HealthRecords';
import Photos from './pages/animal/Photos';
import RequestTreatment from './pages/animal/RequestTreatment';
import AddDiagnosis from './pages/treatment/AddDiagnosis';
import ManageDiagnosis from './pages/treatment/ManageDiagnosis';
import AddTreatment from './pages/treatment/AddTreatment';
import ManageTreatment from './pages/treatment/ManageTreatment';
import WeightHistory from './pages/animal/WeightHistory';



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
                <Route path='/animal-comments' element={<Comments/>}/> 
                <Route path='/animal-reminders' element={<Reminders/>}/> 
                <Route path='/animal-health-records' element={<HealthRecords/>}/> 
                <Route path='/animal-photos' element={<Photos/>}/> 
                <Route path='/request-treatment' element={<RequestTreatment/>}/> 
                <Route path='/add-diagnosis' element={<AddDiagnosis/>}/> 
                <Route path='/manage-diagnosis' element={<ManageDiagnosis/>}/> 
                <Route path='/add-treatment' element={<AddTreatment/>}/> 
                <Route path='/manage-treatment' element={<ManageTreatment/>}/> 
                <Route path='/weight-history' element={<WeightHistory/>}/> 
            </Routes>
        </Router>
    );
}

export default App;
