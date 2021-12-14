import 'bootstrap/dist/css/bootstrap.min.css';
import Sidebar from '../../components/Sidebar';
import AnimalNavbar from '../../components/AnimalNavbar';

import axios from 'axios';
import '../../styling/Comments.css';
import CommentsList from '../../components/CommentsList'
import React, {useState} from 'react'
import {useLocation, useNavigate} from 'react-router-dom'

import jwt_decode from "jwt-decode";


//sample url: http://localhost:3000/animal-comments?animalID=101&withStudents=true
//this link works
//windows.hash issue was in the AnimalNavBar

function Comments() {
    const [message, setData] = useState(null);
    const [originalVal, setOriginalVal] = useState(null);
    // const [showStudents, setStudentToggle] = useState(true);

    //resource: https://www.youtube.com/watch?v=Jppuj6M1sJ4
    const urlParams = new URLSearchParams(useLocation().search)
    const commentID = 1; //dummy value, commentID is updated on the backEnd before being saved
    // const animalID = urlParams.get("animalID")
    const showStudents = urlParams.get("withStudents")==="true" ? true:false
    // const authorID = urlParams.get("authorID")
    // const firstName = urlParams.get("firstName").replace("%20"," ")
    // const lastName = urlParams.get("lastName").replace("%20"," ")
    // const activeUserType = urlParams.get("userType").replace("%20"," ")

    const animalID = localStorage.getItem("animalID")
    const authorID = localStorage.getItem("userID")
    const firstName = localStorage.getItem("userFirstName")
    const lastName = localStorage.getItem("userLastName")
    // const activeUserType = localStorage.getItem("userType")
    let token = localStorage.getItem("token")
    let decoded = jwt_decode(token, { header: true });
    const [activeUserType, setType] = useState(decoded.sub)
    

    

    //https://stackoverflow.com/questions/62861269/attempted-import-error-usehistory-is-not-exported-from-react-router-dom
    let navigate = useNavigate();
    let currLocation = useLocation();

    // console.log(useLocation())

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
          postNewComment(event)
        }
      }
    
      function clickButton(event){
        event.preventDefault();
        setData(originalVal)
        document.getElementById("commentInput").value = ""
        console.log("From Clicking the button: "+message)
        postNewComment(event)
      }

      function postNewComment(event){
        event.preventDefault();
        var rightNow = new Date();
        var formatedDay = rightNow.getDate()<10 ? "0"+rightNow.getDate().toString():rightNow.getDate()
        var formatedMonth = (rightNow.getMonth()+1)<10 ? "0"+(rightNow.getMonth()+1).toString():(rightNow.getMonth()+1)
        console.log(rightNow.getHours())
        var hours12 = rightNow.getHours() >=12 ? rightNow.getHours()-12:rightNow.getHours()
        var AMPM = rightNow.getHours() >=12 ? "PM":"AM"
        var formattedMinutes = rightNow.getMinutes() < 10 ? "0"+rightNow.getMinutes().toString() : rightNow.getMinutes().toString()
        var dateTime = rightNow.getFullYear() + "-" + formatedMonth +"-" + formatedDay +" "+ hours12 +":"+ formattedMinutes +" "+ AMPM

        // const comment = {
        //   id:  parseInt(animalID),
        //   commentId: commentID,
        //   authorId:  parseInt(authorID),
        //   timestamp: dateTime,
        //   message: message,
        //   firstName: firstName,
        //   lastName: lastName,
        //   userType: activeUserType
        // }

        // console.log(JSON.stringify(comment))

        axios.post('http://localhost:8080/app/comments/', {
          animalID:  parseInt(animalID),
          commentID: commentID,
          authorID:  parseInt(authorID),
          timestamp: dateTime,
          message: message,
          firstName: firstName,
          lastName: lastName,
          userType: activeUserType  

        }).then(
          res => {
              console.log(res);
          } )
      window.location.reload()
      }

      function toggle(){
        console.log("inside toggle()")

        console.log(showStudents)
        // navigate(currLocation.search.replace("withStudents=false", "withStudents=true"))
        if (showStudents){
          navigate(currLocation.search.replace("withStudents=true", "withStudents=false"))
        }
        else {
          navigate(currLocation.search.replace("withStudents=false", "withStudents=true"))
        }
        window.location.reload()
        // <Redicret push to={window.location.href.replace("withStudents=true", "withStudents=false")}>
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
        
        <div className="d-flex mt-5 mb-4 mx-5 w-75">
              <h1>Comments</h1>
              <div className="d-flex align-items-center mx-5 ps-5 pt-5">
                {showStudents === true ?
                <input type="checkbox" id="toggleStudents" onChange={toggle} />:
                <input type="checkbox" id="toggleStudents" onChange={toggle} checked/>}
                <label>Toggle Students</label>
              </div>
        </div>      
        
        <div className="d-flex flex-column flex-grow-1 align-items-left mx-5 ps-5 w-75">
            <div class="ex1">
            <CommentsList animalID={animalID} toggleStudent={showStudents} />
              {/* {showStudents === true ?
                <CommentsList animalID={animalID} toggleStudent={showStudents} />:
                null} */}
            </div>
            <label className="d-flex flex-row custom-field">
                <input className="form-control" id="commentInput" type="text" required onChange={getData} onKeyDown={handleKeyDown} size="100" placeholder="Enter a message" />
                <button className="btn btn-secondary ms-2" onClick={clickButton}>Submit</button>
            </label>
        </div>
        </div>
        </div>
    </div>
    );
}

export default Comments;