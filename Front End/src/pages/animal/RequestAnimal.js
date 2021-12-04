import 'bootstrap/dist/css/bootstrap.min.css';
// import '../styling/Home.css';
// import Navbar from '../components/Navbar';
import Sidebar from '../../components/Sidebar';
import AnimalNavbar from '../../components/AnimalNavbar';

// Requires npm install axios --save
import axios from 'axios';
import React, {useState} from 'react'
import {useLocation, useNavigate} from 'react-router-dom'

// Requires npm install react-datepicker --save
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";


function RequestAnimal() {
    const [Authenticated, setAuth] = useState(localStorage.getItem("Authenticated"))
    // window.location.reload()
    const [checkoutDate, setCheckoutDate] = useState(new Date());
    const [returnDate, setReturnDate] = useState(new Date());
    const [reason, setReason] = useState(null);

    const urlParams = new URLSearchParams(useLocation().search)
    
    const requestID = 1 //dummy, backend assigns a new requestID
    const animalID = urlParams.get("animalID")
    let animalName = ""
    let animalSpecies = ""
    // const animalName = urlParams.get("animalName")
    // const animalSpecies = urlParams.get("animalSpecies")
    // const requesterID = urlParams.get("requesterID")
    // const requesterFirstName = urlParams.get("requesterFirstName")
    // const requesterLastName = urlParams.get("requesterLastName")
    const requesterID = localStorage.getItem("userID")
    const requesterFirstName = localStorage.getItem("userFirstName")
    const requesterLastName = localStorage.getItem("userLastName")
    const requestStatus = "Pending"

    axios.get('http://localhost:8080/app/animal/'+animalID).then(
        res => {
            console.log(res);
            animalName = res.data[0].name
            localStorage.setItem("animalName", animalName)
            animalSpecies = res.data[0].species
            localStorage.setItem("animalSpecies", animalSpecies)
            localStorage.setItem("animalStatus", res.data[0].status)
            // window.location.reload()
        }
    )
    
    function singleRefresh(event){
        event.preventDefault();
        window.location.reload()
    }
    //dummy values
    // const requestID = 1; 
    // const animalID = 1
    // const requesterID = 1
    

    let navigate = useNavigate();
    let currLocation = useLocation();

    console.log(useLocation())
        
    function getReason(message){
        setReason(message.target.value)
    }

    function clickButton(event){
        event.preventDefault();
        document.getElementById("reasonInput").value = ""
        console.log("From Clicking the button: " + reason)
        sendRequest(event)
    }

    function sendRequest(event){

        event.preventDefault();
        var rightNow = new Date();
        var formattedDay = rightNow.getDate() < 10 ? "0" + rightNow.getDate().toString() : rightNow.getDate()
        var formattedMonth = (rightNow.getMonth()+1) < 10 ? "0" + (rightNow.getMonth()+1).toString() : (rightNow.getMonth()+1)
        var requestDate = rightNow.getFullYear() + "-" + formattedMonth +"-" + formattedDay

        var checkoutDay = checkoutDate.getDate() < 10 ? "0" + checkoutDate.getDate().toString() : checkoutDate.getDate()
        var checkoutMonth = (checkoutDate.getMonth()+1) < 10 ? "0" + (checkoutDate.getMonth()+1).toString() : (checkoutDate.getMonth()+1)
        var formattedCheckoutDate = checkoutDate.getFullYear() + "-" + checkoutMonth +"-" + checkoutDay

        var returnDay = returnDate.getDate() < 10 ? "0" + returnDate.getDate().toString() : returnDate.getDate()
        var returnMonth = (returnDate.getMonth()+1) < 10 ? "0" + (returnDate.getMonth()+1).toString() : (returnDate.getMonth()+1)
        var formattedReturnDate =returnDate.getFullYear() + "-" + returnMonth +"-" + returnDay

        axios.post('http://localhost:8080/app/request/animal/', {
            animalID: parseInt(animalID),
            requestID: 1, //dummy, backend assigns a new requestID
            requesterID: parseInt(requesterID),
            requestDate: requestDate,
            checkoutDate: formattedCheckoutDate,
            returnDate: formattedReturnDate,
            reason: reason,
            requestStatus: requestStatus,
            requesterFirstName: requesterFirstName,
            requesterLastName: requesterLastName,
            animalName: animalName,
            animalSpecies: animalSpecies,
            
        }).then(
          res => {
              console.log(res);
          }
        )
        window.location.reload()
      }


  return (
      
        <div className="main-container d-flex flex-column flex-grow-1">
            { Authenticated ==="isAuthenticated" ? 
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
              <h1>Request Animal</h1>
            </div>
            
            <div class="mt-3 mx-5">
                <label> Checkout Date: </label> 
                <DatePicker selected={checkoutDate} onChange={(date) => setCheckoutDate(date)} /> <br/>
                <br/>
                <label> Return Date: </label> 
                <DatePicker minDate = {checkoutDate} selected={returnDate} onChange={(date) => setReturnDate(date)} />
            </div> 

            <div class="custom-field mt-4 mb-3 mx-5">
                <label> Reason For Request: </label> <br/>
                <textarea id="reasonInput" onChange={getReason} cols='100' rows='5' placeholder="Please enter the reason for your request.">
                </textarea>
                {/* <input id="reasonInput" type="text" required onChange={getReason}  placeholder="Please enter the reason for your request." /> */}
            </div>
            <div class="button mx-5">
                <button onClick={clickButton}>Submit</button>
            </div>
            </div>
            </div>
            : <a href="/">You are not authorized to view this page. Return to Login</a>}
        </div>
        
    );
}

export default RequestAnimal;