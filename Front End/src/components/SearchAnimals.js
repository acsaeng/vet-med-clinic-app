import axios from 'axios';
import React, {Component} from "react"
import { Link } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';

export default class SearchAnimals extends Component{
    // navigate = useNavigate();

    state = {
        allAvailableAnimals:[],
        // firstQuery:this.props.query === null? "":this.props.query,
        firstQuery:this.props.query,
        counter:0
    };

    // idxReq = {
    //     "animalID":0 , "requestID":1 , "requesterID":2 , "requestDate":3 , "checkoutDate":4 , "returnDate":5 , 
    //     "reason":6 , "requestStatus":7 , "requesterFirstName":8 , "requesterLastName":9 , "animalName":10 , 
    //     "animalSpecies":11 
    // }

    getAvailableAnimals(){
        if (this.props.query !== null){
            axios.get('http://localhost:8080/app/animal/search?name='+this.props.query).then(
                res => {
                    console.log(res);
                    this.setState({allAvailableAnimals: res.data})
                }
            )
        }else{
            axios.get('http://localhost:8080/app/animal/').then(
                res => {
                    console.log(res);
                    this.setState({allAvailableAnimals: res.data})
                })
        }
        
    }
    
    componentDidMount(){
        //test in console logs that the desired property was passed to this component
        console.log("mounted the searchAvailableAnimals")
        console.log(this.props.query)

        this.getAvailableAnimals()
    }

    // storeSelectedAnimalInfo(animal){
    //     localStorage.setItem("animalID", animal.id)
    //     localStorage.setItem("animalName", animal.name)
    //     localStorage.setItem("animalSpecies", animal.species)
    //     localStorage.setItem("animalBreed", animal.breed)
    //     localStorage.setItem("animalSex", animal.sex)
    //     localStorage.setItem("animalWeight", animal.weight)
    //     // localStorage.setItem("animalWeight", animal.weight)
    //     // localStorage.setItem("animalWeight", animal.weight)
    //     // localStorage.setItem("animalWeight", animal.weight)
    //     // localStorage.setItem("animalWeight", animal.weight)
    //     // localStorage.setItem("animalWeight", animal.weight)
    //     // localStorage.setItem("animalWeight", animal.weight)
    //     // localStorage.setItem("animalWeight", animal.weight)

    // }

    // clickToRedirect(){
    //     let path = "/request-animal";
    //     navigate("/request-animal")
    // }

    


    render(){
        return(
            // <div>placeholder</div>
            <div className="overflow-auto">
                {this.state.allAvailableAnimals.map(animal =>
                    // <a href = {"/request-animal?animalID="+animal.animalID} >
                    <a href = {"/animal-info?animalID="+animal.animalID} key={animal.animalID}>
                    {/* // <a href = "/request-animal" onClick={this.storeSelectedAnimalInfo(animal)}>  */}
                    <div className="card text-black bg-light my-3" key={animal.animalID} style={{width: "45rem"}}>
                        
                        <div className="card-header" >
                            {/* <h6> Request {request.requestID} by {request.requesterFirstName} {request.requesterLastName} </h6>  */}
                            <h6> Animal #{animal.animalID}: {animal.name}, {animal.breed} {animal.species} </h6>
                            {/* <Link to="/request-animal"/> */}
                        </div>
                        <div className="card-body"  >

                        </div>
                        
                    </div>
                    </a>
                )}
            </div>
        )
    }

}