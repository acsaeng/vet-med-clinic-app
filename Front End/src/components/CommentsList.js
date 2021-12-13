import axios from 'axios';
import React, {Component} from "react"
import 'bootstrap/dist/css/bootstrap.min.css';

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
    


    
    cardLink2(comment){
        var authorID = comment.authorID.toString()
        var commentID = comment.commentID.toString()
        // console.log("redirectPath = "+'"'+"/comments/single?commentID="+commentID+'"')
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
                <div className="card text-white bg-warning mx-5 my-3" key={comment.commentId} style={{width: "50rem"}}>
                    <div className="card-header" >
                        {this.cardLink2(comment)}
                    </div>
                    <div className="card-body"  >
                        <p className="card-text">
                            {comment.message}
                        </p>
                        <p className="card-text">
                            <small className="text-muted-white">{comment.timestamp}</small>
                        </p>
                    </div>
                    {/* </a> */}
                </div>:null):
                <div className="card bg-light mx-5 my-3" key={comment.commentID} style={{width: "50rem"}}>
                <div className="card-header" >
                    {this.cardLink2(comment)}
                </div>
                <div className="card-body"  >
                    <p className="card-text">
                        {comment.message}
                    </p>
                    <p className="card-text">
                        <small className="text-muted">{comment.timestamp}</small>
                    </p>
                </div>
            </div>

                )
            }
            </div>
        )

    }


}