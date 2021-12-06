import "../styling/Navbar.css";
import profilePhoto from "../images/profile.png";

const Navbar = () => {
    return (
        <div className="navbar d-flex justify-content-start">
            <img src={profilePhoto} alt="Animal Profile" className="p-4"></img>
            
            <div className="d-flex flex-column justify-content-start align-items-center pt-3 animal-info">
                <h3>Animal Name</h3>
                <p className="species-text">Species</p>
                <p className="status-text px-5">Status</p>
            </div>

            <div></div>
        </div>
    )
}

export default Navbar
