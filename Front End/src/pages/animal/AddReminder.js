import '../../styling/main.css';
import Sidebar from '../../components/Sidebar';
import AnimalNavbar from '../../components/AnimalNavbar';
import {useNavigate} from 'react-router-dom'
import {useState} from 'react'
import axios from 'axios';
import Form from "react-bootstrap/Form";

function AddReminder() {
    const [animalName, setName] = useState(localStorage.getItem('animalName'));
    const [species, setSpecies] = useState(localStorage.getItem('animalSpecies'));
    const [reminderNote, setNote] = useState(null);
    const [reminderDueDate, setReminderDueDate] = useState(null);

    let navigate = useNavigate();

    function getMessage(message){
        setNote(message.target.value)
    }

    function getDate(date){
        setReminderDueDate(date.target.value)
        // console.log("date.target.value = "+date.target.value)
    }

    function handleSubmit(event) {
        event.preventDefault();

        axios.post('http://localhost:8080/app/reminders/', {
          animalID:  parseInt(localStorage.getItem('animalID')),
          reminderID: 1,
          status:"Incomplete",
        //   dateDue: document.getElementById('calDueDate').textContent,
          dateDue: reminderDueDate,
        //   dateDue: 'TEMP DATE',
          datePerformed: "PENDING",
          authorID: parseInt(localStorage.getItem("userID")),
          note: reminderNote,
          firstName: localStorage.getItem("firstName"),
          lastName: localStorage.getItem("lastName"),
          userType: localStorage.getItem("userType"),

        }).then(
          res => {
              console.log(res);
          } )

        navigate('/reminders');
        window.location.reload();
    }

    return (
            <div className="d-flex w-100 h-100">
                <div className="sidebar">
                    <Sidebar />
                </div>

                <div className="placeholder">
                    <Sidebar />
                </div>

                <div className="d-flex flex-column flex-grow-1">
                    <AnimalNavbar />

                    <h1 className="mt-5 ms-5">Add a Reminder</h1>

                    <form className="d-flex flex-column  align-items-start mt-2" onSubmit={handleSubmit}>
                        <div className="d-flex my-3 w-75">
                            <h4 className="w-100">Animal Name:</h4>
                            <input className="form-control" defaultValue={ animalName }/>
                        </div>

                        <div className="d-flex my-3 w-75">
                            <h4 className="w-100">Species:</h4>
                            <input className="form-control" defaultValue={species}/>
                        </div>

                        <div className="d-flex my-2 my-3 w-75">
                            <h4 type="date" className="w-100">Due Date</h4>
                            <Form.Control type="date" name='date_of_birth' id="calDueDate" onChange={getDate}></Form.Control>
                        </div>

                        <div className="d-flex my-3 w-75">
                            <h4 className="w-100">Note</h4>
                            <textarea className="form-control " id="messageInput" onChange={getMessage} cols='100' rows='10' />
                        </div>
                        
                    </form>

                    <div className="d-flex flex-row-reverse w-75">
                        <button className="btn btn-primary mx-5"  style={{width:"200px"}} onClick={handleSubmit}>Submit</button>
                    </div>

                    </div>


                </div>
            
        );
    }

export default AddReminder;
