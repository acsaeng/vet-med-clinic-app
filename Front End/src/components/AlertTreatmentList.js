import axios from 'axios';
import React, {Component} from "react"
import 'bootstrap/dist/css/bootstrap.min.css';
// import Checkbox from "./Checkbox";

const treatmentList = [];

export default class AlertTreatmentList extends Component{
    
    state = {
        treatmentList: [],
        animalID : this.props.animalID,
        currUserType: localStorage.getItem("userType"),
        currUserID: localStorage.getItem("userID"),

        checkboxes: treatmentList.reduce(
            (options, option) => ({
              ...options,
              [option]: false
            }),
            {}
          )
    };

    componentDidMount(){
        axios.get('http://localhost:8080/app/treatment/protocol/animalID='+102).then(
        // axios.get('http://localhost:8080/app/treatment/treatment/animalID='+this.state.animalID).then(
            res => {
                this.setState({treatmentList: res.data})
            }
        )
    }

    handleCheckboxChange = changeEvent => {
        const { name } = changeEvent.target;
    
        this.setState(prevState => ({
          checkboxes: {
            ...prevState.checkboxes,
            [name]: !prevState.checkboxes[name]
          }
        }));
    };


    render(){
        return(
            <div className="overflow-auto">
            {this.state.treatmentList.map(treatment => 
                <div className="my-2" key={treatment.treatmentId}>
                    <input type="checkbox" label={treatment} isSelected={this.state.checkboxes[treatment]} 
                        onCheckboxChange={this.handleCheckboxChange}key={treatment}/> {treatment.treatment}
                </div>
                )
            }
            </div>
        )
    }
}