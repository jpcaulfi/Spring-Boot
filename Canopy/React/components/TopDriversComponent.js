import React from 'react';
import { Link } from 'react-router-dom'
import './Drivers.css';
import TopDriversService from '../services/TopDriversService';

class TopDriversComponent extends React.Component {

    constructor(props){
        super(props)
        this.state = {
            drivers:[]
        }
    }

    componentDidMount(){
        TopDriversService.getTopDrivers().then((response) => {
            this.setState({ drivers : response.data})
        })
    }

    render(){
        return(
            <div className="core-div">
                <div>
                    <h2 className="drivers-header">Top 10 Drivers:</h2>
                </div>
                <div className="see-all-container">
                    <button className="see-all-button">
                        <Link to="/page2" className="see-all-button-content">See All</Link>
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
                                this.state.drivers.sort((a, b) => b.score[0].canopyScore - a.score[0].canopyScore).map(
                                    driver =>
                                    <tr className="drivers-data" key={driver.id}>
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

export default TopDriversComponent