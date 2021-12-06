import '../../styling/main.css';
import Sidebar from '../../components/Sidebar';
import AnimalNavbar from '../../components/AnimalNavbar';
import Table from 'react-bootstrap/Table'
import Form from "react-bootstrap/Form";


function WeightHistory() {

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

                    <h1 className="mt-5 ms-5">Weight History</h1>

                    <div className="d-flex flex-column ms-5">

                        <form className="d-flex flex-column align-items-start my-5" onSubmit={handleSubmit}>
                            <div className="d-flex my-2 ms-5 w-25">
                                <h4 className="w-100">Weight:</h4>
                                <input className="form-control"/>
                            </div>

                            <div className="d-flex my-2 ms-5 w-25">
                                <h4 type="date" className="w-100">Date</h4>
                                <Form.Control type="date" name='date_of_birth'></Form.Control>
                            </div>
                        </form>

                        <Table striped bordered hover center className="w-50 ms-5">
                            <thead>
                                <tr>
                                <th>Date</th>
                                <th>Weight Recorded (lbs)</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                   <td>2021-05-19</td>
                                <td>26.2</td>
                                </tr>
                                <tr>
                                <td>2021-05-25</td>
                                <td>24.7</td>
                                </tr>
                                <tr>
                                <td>2021-05-30</td>
                                <td>25.6</td>
                                </tr>
                            </tbody>
                            </Table>

                        </div>

                    </div>


                </div>
            
        );
    }

export default WeightHistory;
