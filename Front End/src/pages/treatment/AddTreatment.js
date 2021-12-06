import 'bootstrap/dist/css/bootstrap.min.css';
import Sidebar from '../../components/Sidebar';
import NavbarNoDropdown from '../../components/NavbarNoDropdown';

// Requires npm install axios --save
import axios from 'axios';
import React, {useState} from 'react'
import {useLocation, useNavigate} from 'react-router-dom'

function AddTreatment() {
    const [Authenticated, setAuth] = useState(localStorage.getItem("Authenticated"))
    // window.location.reload()
    const [treatment, setTreatment] = useState(null);
    const [description, setDescription] = useState(null);
    const treatmentStatus = "Ongoing";
    
    // const animalID = urlParams.get("animalID")
    // let userID = ""
    // let animalName = ""
    // let animalSpecies = ""
    const animalID = localStorage.getItem("animalID")
    const userID = localStorage.getItem("userID")
    const animalName = localStorage.getItem("animalName")
    const animalSpecies = localStorage.getItem("animalSpecies")

    axios.get('http://localhost:8080/app/animal/'+animalID).then(
        res => {
            console.log(res);
            userID = res.data[0].userID
            localStorage.setItem("userID", userID)
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

    let navigate = useNavigate();
    let currLocation = useLocation();

    console.log(useLocation())

    function getTreatment(message){
        setTreatment(message.target.value)
    }

    function getDescription(message){
        setDescription(message.target.value)
    }

    function clickButton(event){
        event.preventDefault();
        document.getElementById("descriptionInput").value = ""
        document.getElementById("treatmentInput").value = ""
        console.log("From Clicking the button: " + treatment)
        sendRequest(event)
    }

    function sendRequest(event){

        event.preventDefault();
        var rightNow = new Date();
        var formattedDay = rightNow.getDate() < 10 ? "0" + rightNow.getDate().toString() : rightNow.getDate()
        var formattedMonth = (rightNow.getMonth()+1) < 10 ? "0" + (rightNow.getMonth()+1).toString() : (rightNow.getMonth()+1)
        var treatmentDate = rightNow.getFullYear() + "-" + formattedMonth +"-" + formattedDay

        axios.post('http://localhost:8080/app/treatment/animal/', {
            animalID: parseInt(animalID),
            treatmentID: 1, //dummy, backend assigns a new requestID
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
      

    <div className="d-flex w-100 h-100">
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
              <h1>Add Treatment Protocol</h1>
            </div>

            <div class="custom-field mt-4 mb-3 mx-5">
                <label> Treatment Protocol: </label> <br/>
                <textarea id="treatmentInput" onChange={getTreatment} cols='100' rows='1' placeholder="Please enter the treatment protocol.">
                </textarea>
            </div>

            <div class="custom-field mt-4 mb-3 mx-5">
                <label> Description: </label> <br/>
                <textarea id="descriptionInput" onChange={getDescription} cols='100' rows='5' placeholder="Please enter the description of the treatment protocol.">
                </textarea>
            </div>
            <div class="button mx-5">
                <button onClick={clickButton}>Submit</button>
            </div>
            </div>
            </div>
            : <a href="/">You are not authorized to view this page. Return to Login</a>} */}

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
                <NavbarNoDropdown />
            </div>
            <div className="d-flex mx-3">
              <h1>Add Treatment Protocol</h1>
            </div>

            <div class="custom-field mt-4 mb-3 mx-5">
                <label> Treatment Protocol: </label> <br/>
                <textarea id="treatmentInput" onChange={getTreatment} cols='100' rows='1' placeholder="Please enter the treatment protocol.">
                </textarea>
            </div>

            <div class="custom-field mt-4 mb-3 mx-5">
                <label> Description: </label> <br/>
                <textarea id="descriptionInput" onChange={getDescription} cols='100' rows='5' placeholder="Please enter the description of the treatment protocol.">
                </textarea>
            </div>
            <div class="button mx-5">
                <button onClick={clickButton}>Submit</button>
            </div>
            </div>
            </div>
        </div>
        
    );
}

export default AddTreatment;