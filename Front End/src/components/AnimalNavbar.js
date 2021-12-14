import "../styling/Navbar.css";
import profilePhoto from "../images/profile.png";
import DropdownButton from 'react-bootstrap/DropdownButton'
import Dropdown from 'react-bootstrap/Dropdown'
import jwt_decode from "jwt-decode";
import { useState } from 'react';

const AnimalNavbar = () => {
    
    // window.onload = function() {
        
    //     if(!window.location.hash) {
    //         window.location = window.location + '#';
    //         window.location.reload();
    //     }
    // }
    let token = localStorage.getItem("token")
    let decoded = jwt_decode(token);
    const [activeUserType, setType] = useState(decoded.sub)
    // let activeUserType = decoded.sub
    // console.log(activeUserType)


    return (
        <div className="navbar d-flex justify-content-start">
            {/* <img src={profilePhoto} alt="Animal Profile" className="p-4"></img> */}
            {/* {window.location.reload()} */}
            <div className="d-flex flex-column justify-content-start align-items-center py-4 animal-info mx-5">
                <h3 className="mt-2 fw-bold">{localStorage.getItem("animalName")}</h3>
                <p className="species-text">{localStorage.getItem("animalSpecies")}</p>
                {/* <p className="availability-text">{localStorage.getItem("animalStatus")}</p> */}
                <p className="status-text">{localStorage.getItem("healthStatus")}</p>
            </div>

            <div className="px-4 dropdown" style={{paddingBottom: "35px"}}>
                <DropdownButton id="dropdown-basic-button" title="Select an option..." variant="dark">
                    <Dropdown.Item href="/animal-info">Animal Information</Dropdown.Item>
                    <Dropdown.Item href="/health-records">Health Records</Dropdown.Item>
                    <Dropdown.Item href="/reminders">Reminders</Dropdown.Item>
                    <Dropdown.Item href="/comments?withStudents=true">Comments</Dropdown.Item>
                    <Dropdown.Item href="/photos">Photos</Dropdown.Item>
                    <Dropdown.Item href="/weight-history">Weight History</Dropdown.Item>
                    {activeUserType === "Teaching Technician" ? 
                        <Dropdown.Item href="/request-animal">Request Animal</Dropdown.Item>:null
                    }
                    
                    <Dropdown.Item href="/send-alert">Send Alert</Dropdown.Item>
                </DropdownButton>
            </div>
        </div>
    )
}

export default AnimalNavbar
