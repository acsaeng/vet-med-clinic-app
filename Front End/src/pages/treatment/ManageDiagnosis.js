import 'bootstrap/dist/css/bootstrap.min.css';
import Sidebar from '../../components/Sidebar';
import AnimalNavbar from '../../components/AnimalNavbar';

// Requires npm install axios --save
import axios from 'axios';
import React, {useState} from 'react'
import {useLocation, useNavigate} from 'react-router-dom'

function ManageDiagnosis() {
    const [Authenticated, setAuth] = useState(localStorage.getItem("Authenticated"))
    // window.location.reload()
    const [diagnosis, setDiagnosis] = useState(diagnosis);
    const [description, setDescription] = useState(description);
    const diagnosisStatus = "Ongoing";
    
    const animalID = urlParams.get("animalID")
    let userID = ""
    let animalName = ""
    let animalSpecies = ""
    const userID = localStorage.getItem("userID")
    const animalName = localStorage.getItem("animalName")
    const animalSpecies = localStorage.getItem("animalSpecies")
    const diagnosisID = localStorage.getItem("diagnosisID")
    const diagnosis = localStorage.getItem("diagnosis")
    const description = localStorage.getItem("description")

    axios.get('http://localhost:8080/app/animal/'+animalID+'/'+diagnosisID).then(
        res => {
            console.log(res);
            userID = res.data[0].userID
            localStorage.setItem("userID", userID)
            animalName = res.data[0].name
            localStorage.setItem("animalName", animalName)
            animalSpecies = res.data[0].species
            localStorage.setItem("animalSpecies", animalSpecies)
            localStorage.setItem("animalStatus", res.data[0].status)
            localStorage.setItem("diagnosisID", res.data[0].diagnosisID)
            localStorage.setItem("diagnosis", res.data[0].diagnosis)
            localStorage.setItem("description", res.data[0].description)
            localStorage.setItem("diagnosisStatus", res.data[0].diagnosisStatus)
            // window.location.reload()
        }
    )
    
    function singleRefresh(event){
        event.preventDefault();
        window.location.reload()
    }

    let navigate = useNavigate();
    let currLocation = useLocation();

    console.log(useLocation())

    function getDiagnosis(message){
        setDiagnosis(message.target.value)
    }

    function getDescription(message){
        setDescription(message.target.value)
    }

    function clickUpdateButton(event){
        event.preventDefault();
        document.getElementById("descriptionInput").value = ""
        document.getElementById("diagnosisInput").value = ""
        console.log("From Clicking the update button: " + diagnosis)
        sendRequest(event)
    }

    function clickCompleteButton(event){

        event.preventDefault();
        var rightNow = new Date();
        var formattedDay = rightNow.getDate() < 10 ? "0" + rightNow.getDate().toString() : rightNow.getDate()
        var formattedMonth = (rightNow.getMonth()+1) < 10 ? "0" + (rightNow.getMonth()+1).toString() : (rightNow.getMonth()+1)
        var diagnosisDate = rightNow.getFullYear() + "-" + formattedMonth +"-" + formattedDay

        event.preventDefault();
        document.getElementById("descriptionInput").value = ""
        document.getElementById("diagnosisInput").value = ""
        console.log("From Clicking the complete button: " + diagnosis)

        axios.put('http://localhost:8080/app/diagnosis/animal/'+diagnosisID, {
            animalID: parseInt(animalID),
            diagnosisID: parseInt(diagnosisID), 
            Date: diagnosisDate,
            diagnosis: diagnosis,
            description: description,
            diagnosisStatus: "Complete",
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
        var diagnosisDate = rightNow.getFullYear() + "-" + formattedMonth +"-" + formattedDay

        axios.put('http://localhost:8080/app/diagnosis/animal/'+diagnosisID, {
            animalID: parseInt(animalID),
            diagnosisID: parseInt(diagnosisID), 
            Date: diagnosisDate,
            diagnosis: diagnosis,
            description: description,
            diagnosisStatus: diagnosisStatus,
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
              <h1>Update Diagnosis</h1>
            </div>

            <div class="custom-field mt-4 mb-3 mx-5">
                <label> Diagnosis: </label> <br/>
                <textarea id="diagnosisInput" onChange={getDiagnosis} cols='100' rows='1'>
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

export default ManageDiagnosis;