import axios from 'axios';
import React, {Component} from "react"
import 'bootstrap/dist/css/bootstrap.min.css';
// import Checkbox from "./Checkbox";

const diagnosisList = [];

export default class AlertDiagnosisList extends Component{
    
    state = {
        diagnosisList: [],
        animalID : this.props.animalID,
        currUserType: localStorage.getItem("userType"),
        currUserID: localStorage.getItem("userID"),

        checkboxes: diagnosisList.reduce(
            (options, option) => ({
              ...options,
              [option]: false
            }),
            {}
          )
    };

    componentDidMount(){
        axios.get('http://localhost:8080/app/treatment/diagnosis/animalID='+102).then(
        // axios.get('http://localhost:8080/app/treatment/diagnosis/animalID='+this.state.animalID).then(
            res => {
                this.setState({diagnosisList: res.data})
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
            {this.state.diagnosisList.map(diagnosis => 
                <div className="my-2" key={diagnosis.diagnosisId}>
                    <input type="checkbox" label={diagnosis} isSelected={this.state.checkboxes[diagnosis]} 
                        onCheckboxChange={this.handleCheckboxChange}key={diagnosis}/> {diagnosis.diagnosis}
                </div>
                )
            }
            </div>
        )
    }
}