import '../../styling/main.css';
import Sidebar from '../../components/Sidebar';
import AnimalNavbar from '../../components/AnimalNavbar';

function AnimalInfo() {

    function handleSubmit(event) {
        event.preventDefault();
        return;
    }

    return (
            <div className="d-flex w-100 h-100">
                <div className="sidebar">
                    <Sidebar />
                </div>

                <div className="placeholder">
                    <Sidebar />
                </div>

                <div className="d-flex flex-column flex-grow-1">
                    <AnimalNavbar />

                    <h1 className="mt-5 ms-5">Animal Information</h1>
                        <form className="d-flex flex-column  align-items-start mt-5" onSubmit={handleSubmit}>
                            <div className="d-flex my-3 w-50">
                                <h4 className="w-100">Animal Name:</h4>
                                <input className="form-control"/>
                            </div>

                            <div className="d-flex my-3 w-50">
                                <h4 className="w-100">Species:</h4>
                                <input className="form-control"/>
                            </div>

                            <div className="d-flex my-3 w-50">
                                <h4 className="w-100">Breed:</h4>
                                <input className="form-control"/>
                            </div>

                            <div className="d-flex my-3 w-50">
                                <h4 className="w-100">Tattoo Number:</h4>
                                <input className="form-control"/>
                            </div>

                            <div className="d-flex my-3 w-50">
                                <h4 className="w-100">City Tattoo:</h4>
                                <input className="form-control"/>
                            </div>

                            <div className="d-flex my-3 w-50">
                                <h4 className="w-100">Birth Date:</h4>
                                <input className="form-control"/>
                            </div>

                            <div className="d-flex my-3 w-50">
                                <h4 className="w-100">Sex:</h4>
                                <input className="form-control"/>
                            </div>

                            <div className="d-flex my-3 w-50">
                                <h4 className="w-100">RFID:</h4>
                                <input className="form-control"/>
                            </div>

                            <div className="d-flex my-3 w-50">
                                <h4 className="w-100">Microchip:</h4>
                                <input className="form-control"/>
                            </div>

                            <div className="d-flex my-3 w-50">
                                <h4 className="w-100">Status:</h4>
                                <input className="form-control"/>
                            </div>

                            <div className="d-flex my-3 w-50">
                                <h4 className="w-100">Colour:</h4>
                                <input className="form-control"/>
                            </div>

                            <div className="d-flex my-3 w-50">
                                <h4 className="w-100">Weight:</h4>
                                <input className="form-control"/>
                            </div>

                            <div className="d-flex my-3 w-50">
                                <h4 className=" w-100">Additional Information:</h4>
                                <input className="form-control"/>
                            </div>
                            
                            <div className="align-items-center mt-3 mb-5 w-50">
                                <button className="btn btn-secondary px-4 py-2" type="submit">Submit</button>
                            </div>

                        </form>


                    </div>


                </div>
            
        );
    }

export default AnimalInfo;
