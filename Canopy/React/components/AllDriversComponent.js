import React from 'react';
import { Link } from 'react-router-dom';
import './Drivers.css';
import AllDriversService from '../services/AllDriversService';

class AllDriversComponent extends React.Component {

    constructor(props){
        super(props)
        this.state = {
            drivers:[]
        }
    }

    componentDidMount(){
        AllDriversService.getAllDrivers().then((response) => {
            this.setState({ drivers : response.data})
        })
    }

    render(){
        return(
            <div className="core-div">
                <div>
                    <h2 className="drivers-header">All Drivers:</h2>
                </div>
                <div  className="back-container">
                    <button className="back-button">
                        <Link to="/" className="back-button-content">Go Back</Link>
                    </button>
                    <table className="drivers-table">
                        <thead>
                            <tr>
                                <td>Initials</td>
                                <td>Stop Speed</td>
                                <td>Package Speed</td>
                                <td>Rescues For/Against</td>
                                <td>Attendance Score</td>
                                <td>Marks Score</td>
                                <td>Canopy Score</td>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.drivers.sort((a, b) => a.score[0].canopyScore - b.score[0].canopyScore).map(
                                    driver =>
                                    <tr key={driver.id}>
                                        <td>{driver.initials}</td>
                                        <td>{driver.score[0].stopSpeed}</td>
                                        <td>{driver.score[0].packageSpeed}</td>
                                        <td>{driver.score[0].rescueRatio}</td>
                                        <td>{driver.score[0].attendanceScore}</td>
                                        <td>{driver.score[0].markScore}</td>
                                        <td>{driver.score[0].canopyScore}</td>
                                    </tr>
                                )
                            }
                        </tbody>
                    </table>
                </div>
            </div>
        )
    }

}

export default AllDriversComponent