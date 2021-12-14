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


import RequestViewByAdmin from '../../components/RequestViewByAdmin.js';
import RequestViewByHealthTechnician from '../../components/RequestViewByHealthTechnician.js';
import RequestViewByTeachingTechnician from '../../components/RequestViewByTeachingTechnician.js';
import jwt_decode from "jwt-decode";


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
            <Sidebar />
            { allowView ? 
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