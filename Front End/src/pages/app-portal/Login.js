import { useState } from "react";
import { useNavigate } from "react-router-dom";
import '../../styling/login.css';
import ucvmLogo from "../../images/ucvm-logo-lg.png";
import axios from 'axios';


const Login = () => {
    const [usernameEntered, setUsername] = useState("");
    const [passwordEntered, setPassword] = useState("");
    localStorage.setItem("Authenticated", false)
    
    let navigate = useNavigate();

    const dummyData = [{
        userId: 10,
        firstName: "Hacker",
        lastName: "One",
        username: "Instructor_1",
        password: "pt@123",
        userType: "Teaching Technician"
    },
    {
        userId: 11,
        firstName: "Hacker",
        lastName: "Two",
        username: "Admin_1",
        password: "pa",
        userType: "Admin"
    },
    {
        userId: 12,
        firstName: "Hacker",
        lastName: "Three",
        username: "Technician",
        password: "pe",
        userType: "Animal Health Technician"
    }]

    function handleSubmit(event) {
        event.preventDefault();

        axios.get('http://localhost:8080/app/login/', {params: { 
            userName: usernameEntered,
            password: passwordEntered
            }}).then(
              res => {
                  console.log(res.data);
                let user = res.data
                // console.log(user[0].firstName)
                if (user[0].firstName === null){ //if null
                    alert("Invalid username and/or password");
                }else{
                    localStorage.setItem("userID", user[0].id)
                    localStorage.setItem("userFirstName", user[0].firstName)
                    localStorage.setItem("userLastName", user[0].lastName)
                    localStorage.setItem("userType", user[0].userType)
                    localStorage.setItem("email", user[0].email)
                    localStorage.setItem("phoneNum", user[0].phoneNum)
                    localStorage.setItem("Authenticated", "isAuthenticated")
                    navigate(`/home`)
                    // navigate(`/home?requesterID=${user[0].id}&requesterFirstName=${user[0].firstName}&requesterLastName=${user[0].lastName}&userType=${user[0].userType.replace(" ", "%20")}`)
                }
                

              } )
        // window.location.reload()


        // for (let i = 0; i < dummyData.length; i++) {
        //     if (usernameEntered === dummyData[i].username && passwordEntered === dummyData[i].password) {
        //         navigate(`/home`);
        //         return;
        //     }
        // }

        // alert("Invalid username and/or password");
    }


    return (
        <div className="d-flex flex-column vertical-center border border-4 border-dark bg-light shadow">

            <img src={ucvmLogo} alt="U of C Vet Med Logo" className="m-5"></img>

            <form className="mx-4" onSubmit={handleSubmit}>
                <h1 className="mb-4">Login</h1>
                <input type="username" className="form-control mb-2" placeholder="Username" value={usernameEntered} onChange={e => setUsername(e.target.value)} />
                <input type="password" className="form-control mb-4" placeholder="Password" value={passwordEntered} onChange={e => setPassword(e.target.value)}/>
                <button className="btn btn-secondary mb-4" type="submit">Log In</button>
            </form>
        </div>
    )
}

export default Login
