import React from 'react';
import './Drivers.css';
import RoutesAtRiskService from '../services/RoutesAtRiskService';

class RoutesAtRiskComponent extends React.Component{

    constructor(props){
        super(props)
        this.state = {
            runs:[]
        }
    }

    componentDidMount(){
        RoutesAtRiskService.getRoutesAtRisk().then((response) => {
            this.setState({ runs : response.data})
        })
    }

    render(){
        return (
            <div className="core-div">
                <div>
                    <h2 className="routes-at-risk-header">Routes at Risk:</h2>
                </div>
                <div className="routes-at-risk-container">
                    <table className="routes-at-risk-table">
                        <thead>
                            <tr>
                                <td>Route Code</td>
                                <td>Initials</td>
                                <td>Stops Left</td>
                                <td>Packages Left</td>
                                <td>Target</td>
                                <td>Actual</td>
                                <td>Delta</td>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.runs.sort((a, b) => a.delta - b.delta).map(
                                    run =>
                                    <tr className="routes-at-risk-data" key={run.id}>
                                        <td>{run.routeCode}</td>
                                        <td>{run.driverInitials}</td>
                                        <td>{run.stopsLeft}</td>
                                        <td>{run.packagesLeft}</td>
                                        <td>{run.currentCompletion}</td>
                                        <td>{run.targetCompletion}</td>
                                        <td>{run.delta}</td>
                                        <tc><button className="routes-at-risk-button">Assign Rescue</button></tc>
                                        <td><button className="routes-at-risk-button">Post for Bid</button></td>
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

export default RoutesAtRiskComponent