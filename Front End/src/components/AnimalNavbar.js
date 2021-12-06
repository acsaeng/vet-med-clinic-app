import "../styling/Navbar.css";
import profilePhoto from "../images/profile.png";
import DropdownButton from 'react-bootstrap/DropdownButton'
import Dropdown from 'react-bootstrap/Dropdown'


const AnimalNavbar = () => {
    
    // window.onload = function() {
        
    //     if(!window.location.hash) {
    //         window.location = window.location + '#';
    //         window.location.reload();
    //     }
    // }

    return (
        <div className="navbar d-flex justify-content-start">
            {/* <img src={profilePhoto} alt="Animal Profile" className="p-4"></img> */}
            {/* {window.location.reload()} */}
            <div className="d-flex flex-column justify-content-start align-items-center py-4 animal-info mx-5">
                <h3 className="mt-2">{localStorage.getItem("animalName")}</h3>
                <p className="species-text">{localStorage.getItem("animalSpecies")}</p>
                <p className="status-text px-5">{localStorage.getItem("animalStatus")}</p>
            </div>

            <div className="px-4 py-2">
                <DropdownButton id="dropdown-basic-button" title="Select an option..." variant="dark">
                    <Dropdown.Item href="/animal-info">Animal Information</Dropdown.Item>
                    <Dropdown.Item href="/health-records">Health Records</Dropdown.Item>
                    <Dropdown.Item href="/reminders">Reminders</Dropdown.Item>
                    <Dropdown.Item href="/comments">Comments</Dropdown.Item>
                    <Dropdown.Item href="/photos">Photos</Dropdown.Item>
                    <Dropdown.Item href="/weight-history">Weight History</Dropdown.Item>
                    <Dropdown.Item href="/request-treatment">Request Treatment</Dropdown.Item>
                    <Dropdown.Item href="/request-animal">Request Animal</Dropdown.Item>
                </DropdownButton>
            </div>
        </div>
    )
}

export default AnimalNavbar
