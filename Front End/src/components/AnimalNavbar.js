import "../styling/Navbar.css";
import profilePhoto from "../images/profile.png";


const Navbar = () => {
    
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
            <div className="d-flex flex-column justify-content-start align-items-center pt-3 animal-info mx-5">
                <h3>{localStorage.getItem("animalName")}</h3>
                <p className="species-text">{localStorage.getItem("animalSpecies")}</p>
                <p className="status-text px-5">{localStorage.getItem("animalStatus")}</p>
            </div>

            <div></div>
        </div>
    )
}

export default Navbar