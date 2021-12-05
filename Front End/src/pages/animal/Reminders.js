import 'bootstrap/dist/css/bootstrap.min.css';
// import '../styling/Home.css';
import AnimalNavbar from '../../components/AnimalNavbar';
import Sidebar from '../../components/Sidebar';

import axios from 'axios';
import '../../styling/Comments.css';
import RemindersList from '../../components/RemindersList'
import React, {useState} from 'react'
import {useLocation, useNavigate} from 'react-router-dom'


function Reminders() {
    const [message, setData] = useState(null);
    const [originalVal, setOriginalVal] = useState(null);
    // const [showStudents, setStudentToggle] = useState(true);

    //resource: https://www.youtube.com/watch?v=Jppuj6M1sJ4
    const urlParams = new URLSearchParams(useLocation().search)
    const reminderID = 1; //dummy value, reminderID is updated on the backEnd before being saved
    const animalID = urlParams.get("animalID")
    // const authorID = urlParams.get("authorID")
    // const firstName = urlParams.get("firstName").replace("%"," ")
    // const lastName = urlParams.get("lastName").replace("%"," ")
    // const activeUserType = urlParams.get("userType").replace("%"," ")
    const authorID = localStorage.getItem("userID")
    const firstName = localStorage.getItem("userFirstName")
    const lastName = localStorage.getItem("userLastName")
    const activeUserType = localStorage.getItem("userType")
    


    console.log(useLocation())
    // console.log(urlParams.get("animalID"))
    // console.log("localStorage: if")
    // console.log(localStorage.getItem("toggleState") ?  true:localStorage.getItem("toggleState"))
    // console.log("localStorage.getItem('toggleState') = "+localStorage.getItem("toggleState"))

        
    function getData(val){
        setData(val.target.value)
        setOriginalVal(val.target.value)
        // console.warn(val.target.value)
      }
      
      function handleKeyDown(event){
        
        if (event.key === 'Enter'){ 
          setData(originalVal)
          document.getElementById("commentInput").value = ""
          console.log("From hitting the enter key: "+message)
          // event.preventDefault();
          postNewReminder(event)
        }
      }
    
      function clickButton(event){
        event.preventDefault();
        setData(originalVal)
        document.getElementById("commentInput").value = ""
        console.log("From Clicking the button: "+message)
        postNewReminder(event)
      }

      function postNewReminder(event){
        event.preventDefault();
        var rightNow = new Date();
        var formatedDay = rightNow.getDate()<10 ? "0"+rightNow.getDate().toString():rightNow.getDate()
        var formatedMonth = (rightNow.getMonth()+1)<10 ? "0"+(rightNow.getMonth()+1).toString():(rightNow.getMonth()+1)
        console.log(rightNow.getHours())
        var hours12 = rightNow.getHours() >=12 ? rightNow.getHours()-12:rightNow.getHours()
        var AMPM = rightNow.getHours() >=12 ? "PM":"AM"
        var dateTime = rightNow.getFullYear() + "-" + formatedMonth +"-" + formatedDay +" "+ hours12 +":"+ rightNow.getMinutes() +" "+ AMPM


        axios.post('http://localhost:8080/app/comments/animal/', {
          id:  parseInt(animalID),
          reminderID: reminderID,
          authorId:  parseInt(authorID),
          timestamp: dateTime,
          message: message,
          firstName: firstName,
          lastName: lastName,
          userType: activeUserType  

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
            <div className="d-flex my-5 mx-5">
                <h1>Reminders for Animal</h1>
            </div> 
            <div className="d-flex flex-column flex-grow-1 align-items-left mx-5 mt-5">
                <div class="ex1">
                <RemindersList animalID={animalID} />
                </div>
                <label class="custom-field">
                    <input id="reminderInput" type="text" required onChange={getData} onKeyDown={handleKeyDown} size="100" placeholder="Enter a message" />
                    <button onClick={clickButton}>Submit</button>
                </label>
            </div>
        </div>
        </div>
    </div>
    );
}

export default Reminders;