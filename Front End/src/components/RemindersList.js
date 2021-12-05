import axios from 'axios';
import React, {Component} from "react"
import 'bootstrap/dist/css/bootstrap.min.css';


export default class RemindersList extends Component{
    
    state = {
        reminders: [],
        animalID : this.props.animalID,
    };

    componentDidMount(){
        // let urlParams = new URLSearchParams(useLocation().search)
        // this.state.animalID = parseInt(urlParams.get("animalID"))
        console.log(this.state.toggleStudent)
        axios.get('http://localhost:8080/app/reminders/animal/'+this.state.animalID).then(
            res => {
                console.log(res);
                this.setState({reminders: res.data})
            }
        )
    }

    render(){
        return(
            <div className="overflow-auto">
            {this.state.reminders.map(reminder => 
                <div class="card bg-light mx-5 my-3" key={reminder.commentId} style={{width: "50rem"}}>
                <div class="card-header" >
                    {reminder.firstName} {reminder.lastName}, {reminder.userType}
                </div>
                <div class="card-body"  >
                    <p class="card-text">
                        {reminder.note}
                    </p>
                    <div class="d-flex w-100">
                        <div class="mx-3"> 
                            Status: {reminder.status}
                        </div>
                        <div class="mx-3"> 
                            Date Due: {reminder.dateDue.replace(" 00:00:00","")}
                        </div>
                        <div class="mx-3"> 
                            Date Performed: {reminder.dateDue.replace(" 00:00:00","")}
                        </div>
                    </div>
                </div>
            </div>
            )}
            </div>
        )

    }


}