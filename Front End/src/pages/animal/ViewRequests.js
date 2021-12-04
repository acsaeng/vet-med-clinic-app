import 'bootstrap/dist/css/bootstrap.min.css';
// import '../styling/Home.css';
// import Navbar from '../components/Navbar';
import Sidebar from '../../components/Sidebar';
import AnimalNavbar from '../../components/AnimalNavbar';
import  '../../styling/Scrollbox.css';

// Requires npm install axios --save
import axios from 'axios';
import React, {useState} from 'react'
import {useLocation, useNavigate} from 'react-router-dom'


import Request_ViewByAdmin from '../../components/Request_ViewByAdmin.js';
import Request_ViewByHealthTechnician from '../../components/Request_ViewByHealthTechnician.js';
import Request_ViewByTeachingTechnician from '../../components/Request_ViewByTeachingTechnician.js';



function ViewRequests() {
    //get URL Params
    const urlParams = new URLSearchParams(useLocation().search)
    // const userType = urlParams.get("userType").replace("%20"," ")
    const userType = localStorage.getItem("userType")
    const [Authenticated, setAuth] = useState(localStorage.getItem("Authenticated"))

    return(
    
    <div className="main-container d-flex flex-column flex-grow-1">
        { Authenticated ==="isAuthenticated" ? 
        <div className="d-flex w-100 h-100">
            <Sidebar />
            
            <div className="d-flex flex-column flex-grow-1 align-items-left mt-5 mx-5">
                 
                <div className="d-flex">
                    {userType === "Admin"?
                        <h1>All Pending Requests waiting for Admin</h1>: null
                        // <h1>All Accepted Requests waiting for Health Technician</h1>
                    }
                    {userType === "Animal Health Technician"?
                        <h1>All Accepted Requests waiting for Health Technician</h1>: null
                    } 
                    {userType === "Teaching Technician"?
                        <h1>All Pending or Accepted Requests you submitted</h1>: null
                    } 
                </div>
                
                <div class="ex1 mt-3 mx-3">
                    {userType === "Admin" ?
                        <Request_ViewByAdmin/>: null
                        // <Request_ViewByHealthTechnician/>
                    }
                    {userType === "Animal Health Technician" ?
                        <Request_ViewByHealthTechnician/>: null
                    }
                    {userType === "Teaching Technician" ?
                        <Request_ViewByTeachingTechnician/>: null
                    }
                </div>  
            </div>
        </div>: <a href="/">You are not authorized to view this page. Return to Login</a>}         
        </div>
    );
}

export default ViewRequests;