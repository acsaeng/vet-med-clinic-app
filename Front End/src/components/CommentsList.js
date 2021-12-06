import axios from 'axios';
import React, {Component} from "react"
import 'bootstrap/dist/css/bootstrap.min.css';
// import {useLocation} from 'react-router-dom'

export default class CommentsList extends Component{
    
    state = {
        comments: [],
        // animalID: 101,
        animalID : this.props.animalID,
        toggleStudent: this.props.toggleStudent
    };

    componentDidMount(){
        console.log(this.state.toggleStudent)
        axios.get('http://localhost:8080/app/comments/animal/'+this.state.animalID).then(
            res => {
                console.log(res);
                this.setState({comments: res.data})
            }
        )
    }

    render(){
        return(
            <div className="overflow-auto">
            {this.state.comments.map(comment => 
                (comment.userType === "Student" ) ?
                (this.state.toggleStudent ?
                <div class="card text-white bg-warning mx-5 my-3" key={comment.commentId} style={{width: "50rem"}}>
                    <div class="card-header" >
                        {comment.firstName} {comment.lastName}, {comment.userType}
                    </div>
                    <div class="card-body"  >
                        {/* <h5 class="card-title" ></h5> */}
                        <p class="card-text">
                            {comment.message}
                        </p>
                        <p class="card-text">
                            <small class="text-muted-white">{comment.timestamp}</small>
                        </p>
                    </div>
                </div>:null):
                <div class="card bg-light mx-5 my-3" key={comment.commentId} style={{width: "50rem"}}>
                <div class="card-header" >
                    {comment.firstName} {comment.lastName}, {comment.userType}
                </div>
                <div class="card-body"  >
                    <p class="card-text">
                        {comment.message}
                    </p>
                    <p class="card-text">
                        <small class="text-muted">{comment.timestamp}</small>
                    </p>
                </div>
            </div>

                )
            }
            </div>
            // div class="card" style={{width: "18rem;"}}>
            // <div class="card-header">
            //     Featured
            // </div>
            // <ul class="list-group list-group-flush">
                
            //     {this.state.comments.map(comment => <li key={comment.commentId}>{comment.message}</li>)}
            // </ul>
            // </div>
        )

    }


}