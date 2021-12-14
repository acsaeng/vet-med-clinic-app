import 'bootstrap/dist/css/bootstrap.min.css';
import Sidebar from '../../components/Sidebar';
import AnimalNavbar from '../../components/AnimalNavbar';

// Requires npm install axios --save
import axios from 'axios';
import React, {useState} from 'react'
import {useLocation, useNavigate} from 'react-router-dom'

import StaffList from '../../components/StaffList';

function RequestTreatment() {
    const [Authenticated, setAuth] = useState(localStorage.getItem("Authenticated"))

    const [message, setMessage] = useState(null);

    const animalID = localStorage.getItem("animalID")
    const requesterID = localStorage.getItem("userID")
    const requesterFirstName = localStorage.getItem("userFirstName")
    const requesterLastName = localStorage.getItem("userLastName")
    const requestStatus = "Pending"
        
    function getMessage(message){
        setMessage(message.target.value)
    }

    function clickButton(event){
        event.preventDefault();
        document.getElementById("messageInput").value = ""
        console.log("From Clicking the button: " + message)
        sendRequest(event)
    }

    function sendRequest(event){

        event.preventDefault();
        var rightNow = new Date();
        var formattedDay = rightNow.getDate() < 10 ? "0" + rightNow.getDate().toString() : rightNow.getDate()
        var formattedMonth = (rightNow.getMonth()+1) < 10 ? "0" + (rightNow.getMonth()+1).toString() : (rightNow.getMonth()+1)
        var requestDate = rightNow.getFullYear() + "-" + formattedMonth +"-" + formattedDay

        axios.post('http://localhost:8080/app/request/animal/', {
            animalID: parseInt(animalID),
            requestID: 1, //dummy, backend assigns a new requestID
            requesterID: parseInt(requesterID),
            requestDate: requestDate,
            message: message,
            requestStatus: requestStatus,
            requesterFirstName: requesterFirstName,
            requesterLastName: requesterLastName,
            
        }).then(
          res => {
              console.log(res);
          }
        )
        window.location.reload()
      }


  return (

    <div className="main-container d-flex flex-column flex-grow-1">
    <div className="d-flex w-100 h-100">
        <div className="sidebar">
            <Sidebar />
        </div>
        <div className="placeholder">
            <Sidebar />
        </div>
        <div className= "d-flex flex-column w-100">
            <AnimalNavbar />
            <h1 className="ms-5 mt-5 mb-5">Request Treatment</h1>

            <div className="ms-5">
             <div className="d-flex mb-3">
                 <h6>This request will be sent from {requesterFirstName} {requesterLastName}. </h6> 
             </div>
            
             <div className="d-flex align-items-left">
                <div>
                <h5>Health Technicians:</h5>
                <div className="align-items-left mx-1 mt-3"></div>
                    <StaffList/>
                </div>
            </div>

            <div class="custom-field mt-4 mb-3">
                <label className="mb-2"> Message: </label> <br/>
                <textarea className="form-control w-50" id="messageInput" onChange={getMessage} cols='100' rows='5' placeholder="Please enter a message">
                </textarea>
            </div>
            <div className="mt-4 button">
                <button className="btn btn-secondary" onClick={clickButton}>Submit</button>
            </div>
            </div>
            </div>
            </div>
        </div>
    );
}

export default RequestTreatment;

            
            {/* { Authenticated ==="isAuthenticated" ? 
            <div className="d-flex w-100 h-100">
                {(event) => singleRefresh(event)}
                <div className="sidebar">
                    <Sidebar />
                </div>

                <div className="placeholder">
                    <Sidebar />
                </div>
            <div className= "d-flex flex-column">
            <div>
                <AnimalNavbar />
            </div>
            <div className="d-flex mx-3">
              <h1>Request Treatment</h1>
            </div>

            <div className="d-flex mt-3 mx-3">
                <h6> This request will be sent from {requesterFirstName} {requesterLastName}. </h6> 
            </div>
            
            <div className="px-3 py-2">
                <label> Animal Health Technician Requested: </label> <br/>
                    <textarea id="requestForInput" onChange={getRequestFor} cols='100' rows='1' 
                    placeholder="Please enter the animal health technician you would like to send a request to.">
                </textarea>
            </div> 

            <div class="custom-field mt-4 mb-3 mx-3">
                <label> Message: </label> <br/>
                <textarea id="messageInput" onChange={getMessage} cols='100' rows='5' placeholder="Please enter the message for your request.">
                </textarea>
            </div>
            <div class="button mx-3">
                <button onClick={clickButton}>Submit</button>
            </div>
            </div>
            </div>
            : <a href="/">You are not authorized to view this page. Return to Login</a>} */}