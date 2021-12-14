import 'bootstrap/dist/css/bootstrap.min.css';
// import '../styling/Home.css';
// import Navbar from '../components/Navbar';
import Sidebar from '../../components/Sidebar';
import  '../../styling/Scrollbox.css';

// Requires npm install axios --save
import axios from 'axios';
import React, {useState} from 'react'
import {useLocation, useNavigate} from 'react-router-dom'


import RequestViewByAdmin from '../../components/RequestViewByAdmin.js';
import RequestViewByHealthTechnician from '../../components/RequestViewByHealthTechnician.js';
import RequestViewByTeachingTechnician from '../../components/RequestViewByTeachingTechnician.js';
import jwt_decode from "jwt-decode";
import BlankNavbar from '../../components/BlankNavbar';


function ViewRequests() {
    //get URL Params
    const urlParams = new URLSearchParams(useLocation().search)
    // const userType = urlParams.get("userType").replace("%20"," ")
    // const userType = localStorage.getItem("userType")
    // const [Authenticated, setAuth] = useState(localStorage.getItem("Authenticated"))
    let token = localStorage.getItem("token")
    let decoded = jwt_decode(token)
    let userType = decoded.sub
    let allowView=false;
    if (userType !== "Student"){allowView = true}



    return(
    
    <div className="main-container d-flex flex-column flex-grow-1">
        
        <div className="d-flex w-100 h-100">
                <div className="sidebar">
                    <Sidebar />
                </div>

                <div className="placeholder">
                    <Sidebar />
                </div>

            { allowView ? 
            <div className="d-flex flex-column flex-grow-1">
                <BlankNavbar />
                <div className="d-flex ms-5 mt-5">
                    <h1>View Requests</h1>
                </div>
                
                <div class="ex1 mt-4 ms-5 w-75">
                    {userType === "Admin" ?
                        <RequestViewByAdmin/>: null
                        // <Request_ViewByHealthTechnician/>
                    }
                    {userType === "Animal Health Technician" ?
                        <RequestViewByHealthTechnician/>: null
                    }
                    {userType === "Teaching Technician" ?
                        <RequestViewByTeachingTechnician/>: null
                    }
                </div> 
            </div> : <a href="/">You are not authorized to view this page. Return to Login</a>} 
        </div>        
        </div>
    );
}

export default ViewRequests;