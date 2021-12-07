import 'bootstrap/dist/css/bootstrap.min.css';
import Sidebar from '../../components/Sidebar';
import AnimalNavbar from '../../components/AnimalNavbar';

// Requires npm install axios --save
import axios from 'axios';
import React, {useState} from 'react'
import {useLocation} from 'react-router-dom'
// import {useNavigate} from 'react-router-dom'

// Requires npm install react-checklist
import { useChecklist } from 'react-checklist';


function SendAlert() {
    const Authenticated = localStorage.getItem("Authenticated")
    const [message, setMessage] = useState(null);

    let animalName = ""
    let animalSpecies = ""
    const animalID = localStorage.getItem("animalID")
    const userID = localStorage.getItem("userID")
    const userFirstName = localStorage.getItem("userFirstName")
    const userLastName = localStorage.getItem("userLastName")

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

    const data = []

    const diagnosisList = [
        { _id: 1, label: 'Diagnosis 1' },
        { _id: 2, label: 'Diagnosis 2' },
        { _id: 3, label: 'Diagnosis 3' },];

    const treatmentList = [
        { _id: 4, label: 'Treatment 1' },
        { _id: 5, label: 'Treatment 2' },
        { _id: 6, label: 'Treatment 3' },];

    const staffList = [
        { _id: 7, label: 'Staff 1' },
        { _id: 8, label: 'Staff 2' },
        { _id: 9, label: 'Staff 3' },];


    const { handleCheck, checkedItems } = useChecklist(data,
        {key: '_id', keyType: 'number'});
    
    function singleRefresh(event){
        event.preventDefault();
        window.location.reload()
    }

    console.log(useLocation())
        
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

        // console.log(checkedItems)

        event.preventDefault();

        axios.post('http://localhost:8080/app/request/animal/', {
            animalID: parseInt(animalID),
            requestID: 1, //dummy, backend assigns a new requestID
            userID: parseInt(userID),
            message: message,
            userFirstName: userFirstName,
            userLastName: userLastName,
            animalName: animalName,
            animalSpecies: animalSpecies,
            
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
            <div>
                <AnimalNavbar />
            </div>
            <h1 className="ms-5 mt-5 mb-5">Send Alert</h1>

            <div className="d-flex mx-5">
                        <div className="align-items-left mx-5 mb-">
                            <h5>Diagnosis List:</h5>
                            <div className="align-items-left mx-1 mt-3">
                                {diagnosisList.map((v, i) => (<div key={i}>
                                <input type="checkbox" data-key={v._id} onChange={handleCheck} checked={checkedItems.has(v._id)}/> 
                                <label>{v.label}</label>
                                </div>))}
                            </div>
                        </div>

                        <div className="align-items-left mx-5">
                            <h5>Treatment List:</h5>
                            <div className="align-items-left mx-1 mt-3">
                                {treatmentList.map((v, i) => (<div key={i}>
                                    <input type="checkbox" data-key={v._id} onChange={handleCheck} checked={checkedItems.has(v._id)}/>
                                    <label>{v.label}</label>
                                </div>))}
                            </div>
                        </div>

                        <div className="align-items-left mx-5">
                            <h5>Staff List:</h5>
                            <div className="align-items-left mx-1 mt-3">
                                {staffList.map((v, i) => (<div key={i}>
                                    <input type="checkbox" data-key={v._id} onChange={handleCheck} checked={checkedItems.has(v._id)}/>
                                    <label>{v.label}</label>
                                </div>))}
                            </div>
                        </div>
                    </div> 

                    <div class="custom-field mt-5 mb-3 mx-5">
                        <label> Message For Alert: </label> <br/>
                        <textarea className="form-control w-50" id="messageInput" onChange={getMessage} cols='100' rows='5' 
                        placeholder="Please enter the message you would like to send with this alert"> </textarea>
                    </div>
                    <div class="button mx-5">
                        <button className="btn btn-secondary mt-3" onClick={clickButton}>Submit</button>
                    </div>
                </div>
            </div>
        </div>
        
    );
}

export default SendAlert;


{/* <div className="main-container d-flex flex-column flex-grow-1">
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
                        <h1>Send Alert</h1>
                    </div>
                
                    <div className="d-flex mx-5">
                        <div className="align-items-left mx-3">
                            <h5>Diagnosis List:</h5>
                            <div className="align-items-left mx-1">
                                {disagnosisList.map((v, i) => (<div key={i}>
                                <input type="checkbox" data-key={v._id} onChange={handleCheck} checked={checkedItems.has(v._id)}/> 
                                <label>{v.label}</label>
                                </div>))}
                            </div>
                        </div>

                        <div className="align-items-left mx-5">
                            <h5>Treatment List:</h5>
                            <div className="align-items-left mx-1">
                                {treatmentList.map((v, i) => (<div key={i}>
                                    <input type="checkbox" data-key={v._id} onChange={handleCheck} checked={checkedItems.has(v._id)}/>
                                    <label>{v.label}</label>
                                </div>))}
                            </div>
                        </div>

                        <div className="align-items-left mx-5">
                            <h5>Staff List:</h5>
                            <div className="align-items-left mx-1">
                                {staffList.map((v, i) => (<div key={i}>
                                    <input type="checkbox" data-key={v._id} onChange={handleCheck} checked={checkedItems.has(v._id)}/>
                                    <label>{v.label}</label>
                                </div>))}
                            </div>
                        </div>
                    </div> 

                    <div class="custom-field mt-4 mb-3 mx-5">
                        <label> Message For Alert: </label> <br/>
                        <textarea id="messageInput" onChange={getMessage} cols='100' rows='5' 
                        placeholder="Please enter the message you would like to send with this alert."> </textarea>
                    </div>
                    <div class="button mx-5">
                        <button onClick={clickButton}>Submit</button>
                    </div>
                </div>
                </div>
        </div> */}