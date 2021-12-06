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
                <DropdownButton id="dropdown-basic-button" title="Animal Information" variant="dark">
                    <Dropdown.Item href="/">Animal Information</Dropdown.Item>
                    <Dropdown.Item href="/">Health Records</Dropdown.Item>
                    <Dropdown.Item href="/">Reminders</Dropdown.Item>
                    <Dropdown.Item href="/">Comments</Dropdown.Item>
                    <Dropdown.Item href="/">Photos</Dropdown.Item>
                    <Dropdown.Item href="/">Weight History</Dropdown.Item>
                    <Dropdown.Item href="/">Request Treatment</Dropdown.Item>
                    <Dropdown.Item href="/request-animal">Request Animal</Dropdown.Item>
                </DropdownButton>
            </div>
        </div>
    )
}

export default AnimalNavbar
