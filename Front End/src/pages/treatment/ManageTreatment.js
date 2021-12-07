import 'bootstrap/dist/css/bootstrap.min.css';
import Sidebar from '../../components/Sidebar';
import AnimalNavbar from '../../components/AnimalNavbar';

// Requires npm install axios --save
import axios from 'axios';
import React, {useState} from 'react'
import {useLocation} from 'react-router-dom'
// import {useNavigate} from 'react-router-dom'

function ManageTreatment() {
    const Authenticated = useState(localStorage.getItem("Authenticated"))
    // window.location.reload()
    const [treatment, setTreatment] = useState(localStorage.getItem("treatment"));
    const [description, setDescription] = useState(localStorage.getItem("treatmentDescription"));
    const treatmentStatus = "Ongoing";
    
    const animalID = localStorage.getItem("animalID")
    const userID = localStorage.getItem("userID")
    const animalName = localStorage.getItem("animalName")
    const animalSpecies = localStorage.getItem("animalSpecies")
    const treatmentID = localStorage.getItem("treatmentID")

    axios.get('http://localhost:8080/app/treatment/protocol/treatmentID='+treatmentID).then(
        res => {
            console.log(res);
            localStorage.setItem("userID", res.data[0].userID)
            localStorage.setItem("animalName", res.data[0].animalName)
            localStorage.setItem("animalSpecies", res.data[0].animalSpecies)
            localStorage.setItem("animalStatus", res.data[0].status)
            localStorage.setItem("treatmentID", res.data[0].treatmentID)
            localStorage.setItem("treatment", res.data[0].treatment)
            localStorage.setItem("description", res.data[0].description)
            localStorage.setItem("treatmentStatus", res.data[0].treatmentStatus)
            // window.location.reload()
        }
    )
    
    function singleRefresh(event){
        event.preventDefault();
        window.location.reload()
    }

    console.log(useLocation())

    function getTreatment(message){
        setTreatment(message.target.value)
    }

    function getDescription(message){
        setDescription(message.target.value)
    }

    function clickUpdateButton(event){
        event.preventDefault();
        document.getElementById("descriptionInput").value = ""
        document.getElementById("treatmentInput").value = ""
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
            animalID: parseInt(animalID),
            treatmentID: parseInt(treatmentID), 
            Date: treatmentDate,
            treatment: treatment,
            description: description,
            treatmentStatus: "Complete",
            userID: userID,
            animalName: animalName,
            animalSpecies: animalSpecies,
            
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
            animalID: parseInt(animalID),
            treatmentID: parseInt(treatmentID), 
            Date: treatmentDate,
            treatment: treatment,
            description: description,
            treatmentStatus: treatmentStatus,
            userID: userID,
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
        </div>
        
    );
}

export default ManageTreatment;