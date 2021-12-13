import 'react-pro-sidebar/dist/css/styles.css';
import '../../styling/main.css';
import Sidebar from "../../components/Sidebar";
import ucvmLogo from "../../images/ucvm-logo-lg.png";
import { useState } from 'react';
import {useLocation, useNavigate} from 'react-router-dom'

function Home() {
    let navigate = useNavigate();

    const [datetime, setDatetime] = useState(new Date());
    const months= ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
    const urlParams = new URLSearchParams(useLocation().search)
    let userInfo = {
        "userID":localStorage.getItem("userID"),
        "userFirstName":localStorage.getItem("userFirstName"),
        "userLastName":localStorage.getItem("userLastName"),
        "userType":localStorage.getItem("userType"),
        // "requesterID":urlParams.get("requesterID"),
        // "requesterFirstName":urlParams.get("requesterFirstName"),
        // "requesterLastName":urlParams.get("requesterLastName"),
        // "userType":urlParams.get("userType"),
    }
    const [Authenticated, setAuth] = useState(localStorage.getItem("Authenticated"))
    // let Authenticated = localStorage.getItem("Authenticated")
    console.log("Authenticated = "+Authenticated)
    // localStorage.setItem("requesterFirstName", urlParams.get("requesterFirstName"))
    // console.log("inside Home.js, testing localStorage")
    // console.log(localStorage.getItem("requesterFirstName"))

    return (
        <div className="main-container d-flex flex-column flex-grow-1">
            {/* { Authenticated ==="isAuthenticated" ? 
            <div className="d-flex w-100 h-100">
                <div className="sidebar">
                    <Sidebar />
                </div>

                <div className="placeholder">
                    <Sidebar />
                </div>

            <div className="vertical-center d-flex flex-column align-items-center">
                <img src={ucvmLogo} alt="U of C Vet Med Logo" className="pb-5"></img>

                <h1>Welcome, {userInfo.userFirstName} {userInfo.userLastName}!</h1>
                <h4 className="fst-italic pb-5">{userInfo.userType}</h4>

                <h1 className="pt-2">{`${datetime.getHours() % 12}:${datetime.getMinutes() < 10 ? "0" + datetime.getMinutes():datetime.getMinutes()} ${datetime.getHours() <= 12? "am" : "pm"}`}</h1>
                <h4>{`${months[datetime.getMonth()]} ${datetime.getDate()}, ${datetime.getFullYear()}`}</h4>
            </div>
                </div>
            : <a href="/">You are not authorized to view this page. Return to Login</a>}
            </div> */}

            <div className="d-flex w-100 h-100">
                <div className="sidebar">
                    <Sidebar />
                </div>

                <div className="placeholder">
                    <Sidebar />
                </div>

                <div className="vertical-center d-flex flex-column align-items-center">
                    <img src={ucvmLogo} alt="U of C Vet Med Logo" className="pb-5"></img>

                    <h1>Welcome, {userInfo.userFirstName} {userInfo.userLastName}!</h1>
                    <h4 className="fst-italic pb-5">{userInfo.userType}</h4>

                    <h1 className="pt-2">{`${datetime.getHours()}:${datetime.getMinutes() < 10 ? "0" + datetime.getMinutes():datetime.getMinutes()} ${datetime.getHours() < 12? "am" : "pm"}`}</h1>
                    <h4>{`${months[datetime.getMonth()]} ${datetime.getDate()}, ${datetime.getFullYear()}`}</h4>
                </div>
            </div>
        </div>
            
        );
}

export default Home;
