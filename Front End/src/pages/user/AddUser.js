import '../../styling/main.css';
import Sidebar from '../../components/Sidebar';
import UserNavbar from '../../components/UserNavbar';
import { useState } from 'react';
import Form from "react-bootstrap/Form";
import axios from 'axios';

function AddUser() {
    const [userId, setUserId] = useState();
    const [firstName, setfirstName] = useState();
    const [lastName, setLastName] = useState();
    const [position, setPosition] = useState();
    const [username, setUsername] = useState();
    const [password, setPassword] = useState();
    const [email, setEmail] = useState();
    const [phoneNum, setPhoneNum] = useState();
    const [startDate, setStartDate] = useState();

    function handleSubmit(event) {
        event.preventDefault();

        axios.post("http://localhost:8080/app/user/add-user", { id: userId, firstName: firstName, lastName: lastName,
                                                                username: username, password: password, 
                                                                userType: position, email: email, phoneNum: phoneNum, 
                                                                startDate: startDate, activeStatus: true})
        .then(res => {
          console.log(res);
          console.log(res.data);
        })
        .catch(err => console.log(err));

        alert("User added")
        window.location.reload()
    }
    
  return (
        <div className="d-flex w-100 h-100">
            <div className="sidebar">
                <Sidebar />
            </div>

            <div className="placeholder">
                <Sidebar />
            </div>

            <div className="main-container d-flex flex-column flex-grow-1">
                <UserNavbar />

                <h1 className="mt-5 ms-5">Add User</h1>
                        <form className="d-flex flex-column  align-items-start mt-5" onSubmit={handleSubmit}>
                            <div className="d-flex my-3 w-50">
                                <h5 className="w-100">User ID:</h5>
                                <input className="form-control" value={userId} onChange={e => setUserId(e.target.value)}/>
                            </div>


                            <div className="d-flex my-3 w-50">
                                <h5 className="w-100">First Name:</h5>
                                <input className="form-control" type="text" value={firstName} onChange={e => setfirstName(e.target.value)}/>
                            </div>

                            <div className="d-flex my-3 w-50">
                                <h5 className="w-100">Last Name:</h5>
                                <input className="form-control" type="text" value={lastName} onChange={e => setLastName(e.target.value)}/>
                            </div>

                            <div className="d-flex my-3 w-50">
                                <h5 className="w-100">Position:</h5>
                                <select class="form-select ms-2" value={position} onChange={e => setPosition(e.target.value)}>
                                    <option selected value="Admin">Admin</option>
                                    <option value="Animal Care Attendant">Animal Care Attendant</option>
                                    <option value="Animal Health Technician">Animal Health Technician</option>
                                    <option value="Student">Student</option>
                                    <option value="Teaching Technician">Teaching Technician</option>
                                </select>
                            </div>

                            <div className="d-flex my-3 w-50">
                                <h5 className="w-100">Username:</h5>
                                <input className="form-control" type="text" value={username} onChange={e => setUsername(e.target.value)}/>
                            </div>

                            <div className="d-flex my-3 w-50">
                                <h5 className="w-100">Password:</h5>
                                <input className="form-control" type="password" value={password} onChange={e => setPassword(e.target.value)}/>
                            </div>

                            <div className="d-flex my-3 w-50">
                                <h5 className="w-100">Email:</h5>
                                <input className="form-control" type="email" value={email} onChange={e => setEmail(e.target.value)}/>
                            </div>

                            <div className="d-flex my-3 w-50">
                                <h5 className="w-100">Phone Number:</h5>
                                <input className="form-control" type="text" value={phoneNum} onChange={e => setPhoneNum(e.target.value)}/>
                            </div>

                            <div className="d-flex my-3 w-50">
                                <h5 className="w-100">Start Date:</h5>
                                <Form.Control type="date" value={startDate} onChange={e => setStartDate(e.target.value)}></Form.Control>
                            </div>

                            <div className="d-flex w-50">
                                <div className="w-50 mx-5"></div>
                                <button className="btn btn-secondary px-4 py-2 mx-5 my-4" type="submit">Submit</button>
                            </div>

                        </form>


            </div>
        
        </div>  
    );
}

export default AddUser;
