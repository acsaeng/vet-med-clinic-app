import axios from 'axios';
import React, {Component} from "react"
import 'bootstrap/dist/css/bootstrap.min.css';
// import {useLocation} from 'react-router-dom'

export default class ConditionssList extends Component{
    
    state = {
        comments: [],
        // animalID: 101,
        animalID : this.props.animalID,
        toggleStudent: this.props.toggleStudent
    };

    // componentDidMount(){
    //     console.log(this.state.toggleStudent)
    //     axios.get('http://localhost:8080/app/comments/animal/'+this.state.animalID).then(
    //         res => {
    //             console.log(res);
    //             this.setState({comments: res.data})
    //         }
    //     )
    // }
    render(){
        return(
            <div className="overflow-auto">
            {/* {this.state.comments.map(comment =>  */}
                
                <div class="card text-white bg-warning mx-5 my-3"  style={{width: "35rem"}}>
                    <div class="card-header" >
                        Diagnosis 1
                        {/* {comment.firstName} {comment.lastName}, {comment.userType} */}
                    </div>
                    <div class="card-body"  >
                        {/* <h5 class="card-title" ></h5> */}
                        <p class="card-text">
                            {/* {comment.message} */}
                            Description
                        </p>
                        {/* <p class="card-text">
                            <small class="text-muted-white">{comment.timestamp}</small>
                        </p> */}
                    </div>
                </div>
                {/* ) } */}
            </div>
            
        )

    }


}