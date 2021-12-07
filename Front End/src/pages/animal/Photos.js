import 'bootstrap/dist/css/bootstrap.min.css';
import Sidebar from '../../components/Sidebar';
import AnimalNavbar from '../../components/AnimalNavbar';

import axios from 'axios';
import '../../styling/Comments.css';
import CommentsList from '../../components/CommentsList'
import React, {useState} from 'react'
import shiba1a from "../../images/animal-photos/shiba1a.jpg";
import shiba2a from "../../images/animal-photos/shiba2a.jpg";
import shiba3a from "../../images/animal-photos/shiba3a.jpg";
// import ucvmLogo from "../images/ucvm-logo-sm.png";


function Photos() {
    return (
        <div className="main-container d-flex flex-column flex-grow-1">
            <div className="d-flex w-100 h-100">
                <div className="sidebar">
                    <Sidebar />
                </div>
                <div className="placeholder">
                    <Sidebar />
                </div>
                <div className= "d-flex flex-column w-100">
                    <div>
                        <AnimalNavbar />
                    </div>
                    <h1 className="ms-5 mt-5">Photos</h1>
                    <div className="d-flex flex-row w-75 mb-3 justify-content-end">  
                        <button className="btn btn-secondary p-2 ms-3">Upload Photo</button>
                        <button className="btn btn-secondary p-2 ms-3">Delete Photo</button>
                    </div>

                    <div class="ex2 mx-5 w-75">
                    <div class="d-flex flex-wrap mx-3 my-5">
                        <div class = "mx-3">
                            <img src={shiba1a} alt=""></img>
                            <p>Shiba1a.jpg</p>
                        </div>
                        <div class = "mx-3">
                            <img src={shiba2a} alt=""></img>
                            <p>shiba2a.jpg</p>
                        </div>
                        <div class = "mx-3">
                            <img src={shiba3a} alt=""></img>
                            <p>shiba3a.jpg</p>
                        </div>
                        <div class = "mx-3">
                            <img src={shiba1a} alt=""></img>
                            <p>Shiba1a.jpg</p>
                        </div>
                        <div class = "mx-3">
                            <img src={shiba2a} alt=""></img>
                            <p>shiba2a.jpg</p>
                        </div>
                        <div class = "mx-3">
                            <img src={shiba3a} alt=""></img>
                            <p>shiba3a.jpg</p>
                        </div>
                        <div class = "mx-3">
                            <img src={shiba1a} alt=""></img>
                            <p>Shiba1a.jpg</p>
                        </div>
                        
                    </div>

                    </div>
                    




                </div>
            </div>   
        </div> 
    )
}
export default Photos;