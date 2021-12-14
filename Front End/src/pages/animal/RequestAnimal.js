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

    // const animalID = localStorage.getItem("animalID")
    const requesterID = localStorage.getItem("userID")
    const requestStatus = "Pending"

    // axios.get('http://localhost:8080/app/animal/'+animalID).then(
    axios.get('http://localhost:8080/app/animal/'+102).then(
        res => {
            // localStorage.setItem("animalID", res.data[0].animalID)
            // localStorage.setItem("animalName", res.data[0].animalName)
            // localStorage.setItem("animalSpecies", res.data[0].animalSpecies)
            // localStorage.setItem("animalStatus", res.data[0].animalStatus)
        }
    )

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
        var requestDate = rightNow.getFullYear() + "-" + formattedMonth +"-" + formattedDay + " 00:00:00"

        var checkoutDay = checkoutDate.getDate() < 10 ? "0" + checkoutDate.getDate().toString() : checkoutDate.getDate()
        var checkoutMonth = (checkoutDate.getMonth()+1) < 10 ? "0" + (checkoutDate.getMonth()+1).toString() : (checkoutDate.getMonth()+1)
        var formattedCheckoutDate = checkoutDate.getFullYear() + "-" + checkoutMonth +"-" + checkoutDay + " 00:00:00"

        var returnDay = returnDate.getDate() < 10 ? "0" + returnDate.getDate().toString() : returnDate.getDate()
        var returnMonth = (returnDate.getMonth()+1) < 10 ? "0" + (returnDate.getMonth()+1).toString() : (returnDate.getMonth()+1)
        var formattedReturnDate =returnDate.getFullYear() + "-" + returnMonth +"-" + returnDay + " 00:00:00"

        axios.post('http://localhost:8080/app/request/', {
            animalID: 102,
            // animalID: parseInt(animalID),
            requestID: null, //dummy, backend assigns a new requestID
            requesterID: parseInt(requesterID),
            requestDate: requestDate,
            checkoutDate: formattedCheckoutDate,
            returnDate: formattedReturnDate,
            reason: reason,
            requestStatus: requestStatus
            
        }).then(
          res => {
              console.log(res);
          }
        )
        // window.location.reload()
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
            <h1 className="ms-5 mt-5 mb-4">Request Animal</h1>
            <div class="mt-3 mx-5">
            <label className="mb-2"> Checkout Date: </label> 
            <DatePicker className="form-control w-25" selected={checkoutDate} onChange={(date) => setCheckoutDate(date)} /> <br/>
            <br/>
            <label className="mb-2"> Return Date: </label> 
            <DatePicker className="form-control w-25" minDate = {checkoutDate} selected={returnDate} onChange={(date) => setReturnDate(date)} />
        </div> 

        <div class="custom-field mt-4 mb-3 mx-5">
            <label className="mb-2"> Reason For Request: </label> <br/>
            <textarea className="form-control w-50"  id="reasonInput" onChange={getReason} cols='100' rows='5' placeholder="Please enter the reason for your request.">
            </textarea>
            {/* <input id="reasonInput" type="text" required onChange={getReason}  placeholder="Please enter the reason for your request." /> */}
        </div>
        <div class="button mx-5 mt-3">
            <button className="btn btn-secondary" onClick={clickButton}>Submit</button>
        </div>


            </div>
            </div>
        </div>
        
    );
}

export default RequestAnimal;


// { Authenticated ==="isAuthenticated" ? 
// <div className="d-flex w-100 h-100">
//     {(event) => singleRefresh(event)}
//     <div className="sidebar">
//         <Sidebar />
//     </div>

//     <div className="placeholder">
//         <Sidebar />
//     </div>
// <div className= "d-flex flex-column">
// <div>
//     <AnimalNavbar />
// </div>
// <div className="d-flex mx-3">
//   <h1>Request Animal</h1>
// </div>

// <div class="mt-3 mx-5">
//     <label> Checkout Date: </label> 
//     <DatePicker selected={checkoutDate} onChange={(date) => setCheckoutDate(date)} /> <br/>
//     <br/>
//     <label> Return Date: </label> 
//     <DatePicker minDate = {checkoutDate} selected={returnDate} onChange={(date) => setReturnDate(date)} />
// </div> 

// <div class="custom-field mt-4 mb-3 mx-5">
//     <label> Reason For Request: </label> <br/>
//     <textarea id="reasonInput" onChange={getReason} cols='100' rows='5' placeholder="Please enter the reason for your request.">
//     </textarea>
//     {/* <input id="reasonInput" type="text" required onChange={getReason}  placeholder="Please enter the reason for your request." /> */}
// </div>
// <div class="button mx-5">
//     <button onClick={clickButton}>Submit</button>
// </div>
// </div>
// </div>
// : <a href="/">You are not authorized to view this page. Return to Login</a>}