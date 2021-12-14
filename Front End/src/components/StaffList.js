import axios from 'axios';
import React, {Component} from "react"
import 'bootstrap/dist/css/bootstrap.min.css';

export default class StaffList extends Component{
    
    state = {
        staffList: [],
        currUserType: localStorage.getItem("userType"),
        currUserID: localStorage.getItem("userID")
    };

    componentDidMount(){
        axios.get('http://localhost:8080/app/user/userType=Animal%20Health%20Technician').then(
            res => {
                this.setState({staffList: res.data})
            }
        )
    }


    render(){
        return(
            <div>
                <select class="form-select" size='6' multiple aria-label="multiple select staff">
                    <option selected>Select all staff to alert</option>
                    {this.state.staffList.map(staff => 
                        <option value = {staff.id} >{staff.firstName} {staff.lastName}</option>
                        )
                    }
                </select>
            </div>
        )
    }
}