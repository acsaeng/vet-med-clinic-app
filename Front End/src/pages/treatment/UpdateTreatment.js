import 'bootstrap/dist/css/bootstrap.min.css';
import Sidebar from '../../components/Sidebar';
import AnimalNavbar from '../../components/AnimalNavbar';

// Requires npm install axios --save
import axios from 'axios';
import React, {useState} from 'react'
import {useLocation} from 'react-router-dom'
// import {useNavigate} from 'react-router-dom'

function ManageTreatment() {
    // const Authenticated = useState(localStorage.getItem("Authenticated"))
    // window.location.reload()
    const [treatment, setTreatment] = useState(localStorage.getItem("treatment"));
    const [description, setDescription] = useState(localStorage.getItem("description"));
    
    const animalID = localStorage.getItem("animalID")
    const treatmentID = localStorage.getItem("treatmentID")
    const treatmentStatus = localStorage.getItem("treatmentStatus")

    // axios.get('http://localhost:8080/app/treatment/protocol/treatmentID='+treatmentID).then(
        axios.get('http://localhost:8080/app/treatment/protocol/treatmentID='+5).then(
        res => {
            // console.log(res);
            localStorage.setItem("treatmentID", res.data[0].treatmentID)
            localStorage.setItem("treatment", res.data[0].treatment)
            localStorage.setItem("description", res.data[0].description)
            localStorage.setItem("treatmentStatus", res.data[0].treatmentStatus)
            localStorage.setItem("animalID", res.data[0].animalID)
            // window.location.reload()
        }
    )
    
    // function singleRefresh(event){
    //     event.preventDefault();
    //     window.location.reload()
    // }

    console.log(useLocation())

    function getTreatment(treatment){
        setTreatment(treatment.target.value)
    }

    function getDescription(treatmentDescription){
        setDescription(treatmentDescription.target.value)
    }

    function clickUpdateButton(event){
        event.preventDefault();
        document.getElementById("treatmentInput").value = ""
        document.getElementById("descriptionInput").value = ""
        console.log("From Clicking the update button: " + treatment)
        sendRequest(event)
    }

    function clickCompleteButton(event){

        event.preventDefault();
        var rightNow = new Date();
        var formattedDay = rightNow.getDate() < 10 ? "0" + rightNow.getDate().toString() : rightNow.getDate()
        var formattedMonth = (rightNow.getMonth()+1) < 10 ? "0" + (rightNow.getMonth()+1).toString() : (rightNow.getMonth()+1)
        var treatmentDate = rightNow.getFullYear() + "-" + formattedMonth +"-" + formattedDay

        event.preventDefault();
        document.getElementById("descriptionInput").value = ""
        document.getElementById("treatmentInput").value = ""
        console.log("From Clicking the complete button: " + treatment)

        axios.put('http://localhost:8080/app/treatment/protocol/treatmentID='+treatmentID, {
            treatmentID: parseInt(treatmentID), 
            treatment: treatment,
            description: description,
            treatmentDate: treatmentDate,
            treatmentStatus: "Complete",
            animalID: parseInt(animalID),
            userID: 1
            // userID: parseInt(userID)
            
        }).then(
          res => {
              console.log(res);
          }
        )
        window.location.reload()
        
    }

    function sendRequest(event){

        event.preventDefault();
        var rightNow = new Date();
        var formattedDay = rightNow.getDate() < 10 ? "0" + rightNow.getDate().toString() : rightNow.getDate()
        var formattedMonth = (rightNow.getMonth()+1) < 10 ? "0" + (rightNow.getMonth()+1).toString() : (rightNow.getMonth()+1)
        var treatmentDate = rightNow.getFullYear() + "-" + formattedMonth +"-" + formattedDay

        axios.put('http://localhost:8080/app/treatment/protocol/treatmentID='+treatmentID, {
            treatmentID: parseInt(treatmentID), 
            treatment: treatment,
            description: description,
            treatmentDate: treatmentDate,
            treatmentStatus: treatmentStatus,
            animalID: parseInt(animalID),
            userID: 1
            // userID: parseInt(userID)
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
            <div>
                <AnimalNavbar />
            </div>
            <h1 className="ms-5 mt-5">Update Treatment</h1>


            <div class="custom-field mt-4 mb-3 mx-5">
                <label className="mt-4 mb-2"> Treatment Protocol: </label> <br/>
                <textarea className="form-control w-25" id="treatmentInput" onChange={getTreatment} cols='100' rows='1'>
                    {treatment}
                </textarea>
            </div>

            <div class="custom-field mt-4 mb-3 mx-5">
                <label className="mb-2"> Description: </label> <br/>
                <textarea className="form-control w-50" id="descriptionInput" onChange={getDescription} cols='100' rows='5'>
                    {description}
                </textarea>
            </div>
            <div class="button mx-5 mt-3">
                <button className="btn btn-secondary me-3" onClick={clickUpdateButton}>Update</button>
                <button className="btn btn-secondary" onClick={clickCompleteButton}>Complete</button>
            </div>
            </div>
            </div>
        </div>
        
    );
}

export default ManageTreatment;


{/* <div className="main-container d-flex flex-column flex-grow-1">
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
  <h1>Update Treatment Protocol</h1>
</div>

<div class="custom-field mt-4 mb-3 mx-5">
    <label> Treatment Protocol: </label> <br/>
    <textarea id="treatmentInput" onChange={getTreatment} cols='100' rows='1'>
    </textarea>
</div>

<div class="custom-field mt-4 mb-3 mx-5">
    <label> Description: </label> <br/>
    <textarea id="descriptionInput" onChange={getDescription} cols='100' rows='5'>
    </textarea>
</div>
<div class="button mx-5">
    <button onClick={clickUpdateButton}>Update</button>
    <button onClick={clickCompleteButton}>Complete</button>
</div>
</div>
</div>
: <a href="/">You are not authorized to view this page. Return to Login</a>}
</div> */}