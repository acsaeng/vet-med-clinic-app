import axios from 'axios';
import React, {Component} from "react"
import 'bootstrap/dist/css/bootstrap.min.css';
import { Navigate   } from 'react-router';
// import {useLocation} from 'react-router-dom'

export default class CommentsList extends Component{
    
    state = {
        comments: [],
        // animalID: 101,
        animalID : this.props.animalID,
        toggleStudent: this.props.toggleStudent,
        currUserType: localStorage.getItem("userType"),
        currUserID: localStorage.getItem("userID"),
        // navigate : useNavigate()
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
    // var navigate = useNavigate();
    cardLink = (e)=>{
        // var comment = e.target.comment
        var authorID = e.target.getAttribute('authorID')
        var commentID = e.target.getAttribute('commentID')
        var redirectPath = "";
        var test = false;
        // if (this.state.currUserType !== "Student" || this.state.currUserID === authorID){
        //     return 
        // }
        (this.state.currUserType !== "Student" || this.state.currUserID === authorID)?
            redirectPath = "/comments/single?commentID="+commentID:
            redirectPath = window.location.pathname
        console.log("redirectPath = "+ redirectPath)
        
        // navigate("'"+redirectPath+"'")
        return <Navigate  to={"'"+redirectPath+"'"}/>
        
        
       
        // return redirectPath;
    }


    
    cardLink2(comment){
        // var comment = e.target.comment
        var authorID = comment.authorID.toString()
        var commentID = comment.commentID.toString()
        var redirectPath = "";
        var test = false;
        console.log("redirectPath = "+'"'+"/comments/single?commentID="+commentID+'"')
        console.log("redirectPath = "+'"'+"/comments/single?commentID="+commentID+'"')
        if (this.state.currUserType !== "Student" || this.state.currUserID === authorID){
            return <a href={"/comments/single?commentID="+commentID}>
                {comment.firstName} {comment.lastName}, {comment.userType}
                </a>
        }else{
            return <a href="#">
                {comment.firstName} {comment.lastName}, {comment.userType}
                </a>
        }
    }


    render(){
        return(
            <div className="overflow-auto">
            {this.state.comments.map(comment => 
                //If the comment belongs to student, display card as Orange, else display card as grey
                (comment.userType === "Student" ) ?
                //If toggle students is on then no student comments are displayed
                (this.state.toggleStudent ?
                <div class="card text-white bg-warning mx-5 my-3" key={comment.commentId} style={{width: "50rem"}}>
                    {/* <a href={(e) => this.cardLink(e)}> */}
                    
                    {/* {(this.state.currUserType !== "Student" || this.state.currUserID === comment.authorID)?
                        <a href = {"/comments?commentID="+comment.commentID}/>:null
                    } */}
                    
                    <div class="card-header" >
                        {/* <a href="#" commentID={comment.commentID} authorID={comment.authorID} onClick ={(e) => this.cardLink(e)} >
                            {comment.firstName} {comment.lastName}, {comment.userType}
                        </a> */}
                        {this.cardLink2(comment)}
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
                    {/* </a> */}
                </div>:null):
                <div class="card bg-light mx-5 my-3" key={comment.commentID} style={{width: "50rem"}}>
                <div class="card-header" >
                    {/* <a href="#" commentID={comment.commentID} authorID={comment.authorID} onClick ={(e) => this.cardLink(e)} >
                            {comment.firstName} {comment.lastName}, {comment.userType}
                    </a> */}
                    {this.cardLink2(comment)}
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