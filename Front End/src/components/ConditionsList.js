import axios from 'axios';
import React, {Component} from "react"
import 'bootstrap/dist/css/bootstrap.min.css';
// import {useLocation} from 'react-router-dom'

export default class ConditionssList extends Component{
    
    state = {
        diagnosisList: [],
        animalID : localStorage.getItem("animalID"),
        currUserType: localStorage.getItem("userType"),
        currUserID: localStorage.getItem("userID")
    };

    componentDidMount(){
        axios.get('http://localhost:8080/app/treatment/diagnosis/animalID='+this.state.animalID).then(
            res => {
                this.setState({diagnosisList: res.data})
            }
        )
    }

    cardLink2(diagnosis){
        // var authorID = diagnosis.authorID.toString()
        var diagnosisID = diagnosis.diagnosisID.toString()
        if (this.state.currUserType === "Animal Health Technician"){
            return <a href={"/update-diagnosis/single?diagnosisID="+diagnosisID}>
                {diagnosis.diagnosis}
                </a>
        }
        // else{
        //     return <a href="#">
        //         {diagnosis.diagnosis}
        //         </a>
        // }
    }

    render(){
        return(
            <div className="overflow-auto">
                
                <div class="card text-white bg-warning mx-5 my-3"  style={{width: "35rem"}}>
                    {this.state.diagnosisList.map(diagnosis => 
                        <div>
                            <div class="card-header" >
                                {this.cardLink2(diagnosis)}
                            </div>
                            
                            <div class="card-body"  >
                                {diagnosis.description}
                            </div>
                        </div>
                    )
                    }
                </div>
            </div>
            
        )
    }
}