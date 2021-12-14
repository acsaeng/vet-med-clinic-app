import 'bootstrap/dist/css/bootstrap.min.css';
import Sidebar from '../../components/Sidebar';
import AnimalNavbar from '../../components/AnimalNavbar';

// Requires npm install axios --save
import axios from 'axios';
import React, {useState, useEffect} from 'react'
// import {useLocation} from 'react-router-dom'
import {useNavigate} from 'react-router-dom'

import StaffList from '../../components/StaffList';


function SendAlert() {
    // const Authenticated = localStorage.getItem("Authenticated")
    const [message, setMessage] = useState(null);
    const [staffList, setStaffList] = useState([]);
    const [selectedStaff, setSelectedStaff] = useState([]);

    const animalID = localStorage.getItem("animalID")
    const userID = localStorage.getItem("userID")
    const userFirstName = localStorage.getItem("userFirstName")
    const userLastName = localStorage.getItem("userLastName")

    let navigate = useNavigate();

    function GetStaffList(){
        useEffect(()=>{
            axios.get('http://localhost:8080/app/user/userType=Animal%20Health%20Technician').then(
                res => {
                    setStaffList(res.data)
                })
        },[])
    }
        
    function getMessage(message){
        setMessage(message.target.value)
    }

    function clickButton(event){
        event.preventDefault();
        document.getElementById("messageInput").value = ""
        console.log(selectedStaff.map( (staff) => staff ) + " " + message)
        sendRequest(event)
    }

    function sendRequest(event){

        event.preventDefault();
        var rightNow = new Date();
        var formattedDay = rightNow.getDate() < 10 ? "0" + rightNow.getDate().toString() : rightNow.getDate()
        var formattedMonth = (rightNow.getMonth()+1) < 10 ? "0" + (rightNow.getMonth()+1).toString() : (rightNow.getMonth()+1)
        var requestDate = rightNow.getFullYear() + "-" + formattedMonth +"-" + formattedDay

        // axios.post('http://localhost:8080/app/request/animal/', {
        //     animalID: parseInt(animalID),
        //     requestID: 1, //dummy, backend assigns a new requestID
        //     userID: parseInt(userID),
        //     message: message,
        //     userFirstName: userFirstName,
        //     userLastName: userLastName
            
        // }).then(
        //   res => {
        //       console.log(res);
        //   }
        // )
        navigate(`/animal-info`)
        window.location.reload()
      }

      function handleChange(e){
        let value = Array.from(e.target.selectedOptions, option => option.value);
        setSelectedStaff(value);
        console.log(selectedStaff)
    }


  return (

    <div className="main-container d-flex flex-column flex-grow-1">
        {GetStaffList()}
    <div className="d-flex w-100 h-100">
        <div className="sidebar">
            <Sidebar />
        </div>
        <div className="placeholder">
            <Sidebar />
        </div>
        <div className= "d-flex flex-column w-100">
            <AnimalNavbar />
            <h1 className="ms-5 mt-5 mb-5">Send Alert</h1>

            <div className="d-flex mx-5">

                        <div className="align-items-left">
                            <h6>Please select all health technicians you would like to alert:</h6>
                            <div className="align-items-left mx-1 mt-3"></div>
                            <select class="form-select" size='5' multiple aria-label="staffSelection" onChange={handleChange}> 
                                {staffList.map(staff =>   
                                    <option value = {staff.id} >{staff.firstName} {staff.lastName}</option>
                                )}      
                            </select> 
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