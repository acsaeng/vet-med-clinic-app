import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from 'axios';
import '../../styling/login.css';
import ucvmLogo from "../../images/ucvm-logo-lg.png";


const Login = () => {
    const [usernameEntered, setUsername] = useState("");
    const [passwordEntered, setPassword] = useState("");
    window.localStorage.clear();
    // localStorage.setItem("Authenticated", false)
    
    let navigate = useNavigate();

    function handleSubmit(event) {
        event.preventDefault();

        axios.get('http://localhost:8080/app/authenticate/', {params: { 
            userName: usernameEntered,
            password: passwordEntered
            }}).then(
                res=>{try{
                    console.log(res.data)
                    localStorage.setItem("token", res.data)
                    window.location.reload()
                    }catch{
                        window.location.reload()
                    }
                }
            )

        axios.interceptors.request.use(
                config =>{
                    config.headers.authorization = `Bearer ${localStorage.getItem("token")}`
                    return config;
                }
            )



        axios.get('http://localhost:8080/app/login/', {params: { 
            userName: usernameEntered,
            password: passwordEntered
            }}).then(
              res => {
                  console.log(res.data);
                let user = res.data
                // console.log(user.firstName)
                if (user.firstName === ""){ //if null
                    alert("Invalid username and/or password");
                } else{
                    localStorage.setItem("userID", user.id)
                    localStorage.setItem("userFirstName", user.firstName)
                    localStorage.setItem("userLastName", user.lastName)
                    // localStorage.setItem("userType", user.userType)
                    localStorage.setItem("email", user.email)
                    localStorage.setItem("phoneNum", user.phoneNum)
                    // localStorage.setItem("Authenticated", "isAuthenticated")
                    // navigate(`/home?requesterID=${user.id}&requesterFirstName=${user.firstName}&requesterLastName=${user.lastName}&userType=${user.userType.replace(" ", "%20")}`)
                    navigate(`/home`)
                    window.location.reload()
                }
            })
        
        

        
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
