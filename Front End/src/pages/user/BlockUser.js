import '../../styling/main.css';
import Sidebar from '../../components/Sidebar';
import UserNavbar from '../../components/UserNavbar';

function BlockUser() {

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

            <div className="main-container d-flex flex-column flex-grow-1">
                <UserNavbar />

                <h1 className="mt-5 ms-5">Block User</h1>
                    <form className="d-flex flex-column  align-items-start mt-5" onSubmit={handleSubmit}>
                        <div className="d-flex my-3 w-50">
                            <h4 className="w-100">First Name:</h4>
                            <input className="form-control"/>
                        </div>

                        <div className="d-flex my-3 w-50">
                            <h4 className="w-100">Last Name:</h4>
                            <input className="form-control"/>
                        </div>

                        <div className="d-flex my-3 w-50">
                            <h4 className="w-100">Type:</h4>
                            <input className="form-control"/>
                        </div>

                        <div className="d-flex my-3 w-50">
                            <h4 className="w-100">Email:</h4>
                            <input className="form-control"/>
                        </div>

                        <div className="d-flex my-3 w-50">
                            <h4 className="w-100">Phone Number:</h4>
                            <input className="form-control"/>
                        </div>

                        <div className="d-flex my-3 w-50">
                            <h4 className="w-100">Password:</h4>
                            <input type="password" className="form-control"/>
                        </div>

                        <div className="d-flex my-3 w-50">
                            <h4 className="w-100">Start Date:</h4>
                            <input className="form-control"/>
                        </div>

                        <div className="d-flex my-3 w-50">
                            <h4 className="w-100">User Status</h4>
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
                        
                        <div className="align-items-center mt-3 mb-5 w-50">
                            <button className="btn btn-secondary px-4 py-2" type="submit">Submit</button>
                        </div>

                    </form>


            </div>
        
        </div>  
    );
}

export default BlockUser;
