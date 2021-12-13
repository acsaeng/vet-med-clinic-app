import axios from 'axios';
import React, {Component} from "react"
import 'bootstrap/dist/css/bootstrap.min.css';

const staffList = [];

export default class AlertStaffList extends Component{
    
    state = {
        staffList: [],
        animalID : this.props.animalID,
        currUserType: localStorage.getItem("userType"),
        currUserID: localStorage.getItem("userID"),

        checkboxes: staffList.reduce(
            (options, option) => ({
              ...options,
              [option]: false
            }),
            {}
          )
    };

    componentDidMount(){
        axios.get('http://localhost:8080/app/user/userType=Animal%20Health%20Technician').then(
            res => {
                this.setState({staffList: res.data})
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
            {this.state.staffList.map(staff => 
                <div className="my-2" key={staff.id}>
                    <input type="checkbox" label={staff} isSelected={this.state.checkboxes[staff]} 
                        onCheckboxChange={this.handleCheckboxChange}key={staff}/> 
                        {staff.firstName} {staff.lastName}
                </div>
                )
            }
            </div>
        )
    }
}