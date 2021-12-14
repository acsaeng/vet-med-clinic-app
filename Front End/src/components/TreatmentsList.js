import axios from 'axios';
import React, {Component} from "react"
import 'bootstrap/dist/css/bootstrap.min.css';
// import {useLocation} from 'react-router-dom'

export default class TreatmentsList extends Component{
    
    state = {
        treatmentList: [],
        animalID : localStorage.getItem("animalID"),
        currUserType: localStorage.getItem("userType"),
        currUserID: localStorage.getItem("userID")
    };

    componentDidMount(){
        axios.get('http://localhost:8080/app/treatment/protocol/animalID='+this.state.animalID).then(
            res => {
                this.setState({treatmentList: res.data})
            }
        )
    }

    cardLink(treatment){
        var treatmentID = treatment.treatmentID.toString()

        return <a href={"/update-treatment/single?treatmentID="+treatmentID}>{treatment.treatment}</a>
    }

    clickCompleteButton(treatment){

        var rightNow = new Date();
        var formattedDay = rightNow.getDate() < 10 ? "0" + rightNow.getDate().toString() : rightNow.getDate()
        var formattedMonth = (rightNow.getMonth()+1) < 10 ? "0" + (rightNow.getMonth()+1).toString() : (rightNow.getMonth()+1)
        var treatmentDate = rightNow.getFullYear() + "-" + formattedMonth +"-" + formattedDay + " 00:00:00"

        axios.put('http://localhost:8080/app/treatment/protocol/treatmentID='+treatment.treatmentID, {
            treatmentID: parseInt(treatment.treatmentID), 
            treatmentDate: treatmentDate,
            treatment: treatment.treatment,
            description: treatment.description,
            treatmentStatus: "Complete",
            animalID: parseInt(this.state.animalID),
            userID: parseInt(this.state.currUserID)
        }).then(
          res => {
              console.log(res);
          }
        )
        window.location.reload()
    }

    render(){
        return(
            <div className="overflow-auto">    
                <div class="card text-black bg-basic mx-5 my-3"  style={{width: "30rem"}}>
                    {(this.state.treatmentList.length === 0) ? 
                        <div class="card-header" >
                            No ongoing treatments.
                        </div>
                        :<div>{this.state.treatmentList.map(treatment => 
                            <div>
                                <div class="card-header" >
                                    {this.cardLink(treatment)}
                                </div>
                                
                                <div class="card-body"  >
                                    {treatment.description}
                                </div>
                                <div>
                                    <button className="btn btn-success mx-2 my-2" onClick={ () => this.clickCompleteButton(treatment) }>Mark as Complete</button> 
                                </div>
                            </div>)
                        }</div>
                    }
                 </div>
            </div> 
        )
    }
}