import React from 'react';
import RescuesService from '../services/RescuesService';

class RescuesComponent extends React.Component{

    constructor(props){
        super(props)
        this.state = {
            rescues:[]
        }
    }

    componentDidMount(){
        RescuesService.getRescues().then((response) => {
            this.setState({ rescues : response.data})
        })
    }

    render(){
        return (
            <div className="core-div">
                <div>
                    <h2 className="routes-at-risk-header">Rescues In Progress:</h2>
                </div>
                <div className="routes-at-risk-container">
                    <table className="rescues-table">
                        <thead>
                            <tr className="table-header">
                                <td>Route Code</td>
                                <td>Subject</td>
                                <td>Rescued By</td>
                                <td>Stops</td>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.rescues.map(
                                    rescue =>
                                    <tr className="rescues-data" key={rescue.id}>
                                        <td>{rescue.routeCode}</td>
                                        <td>{rescue.subjectInitials}</td>
                                        <td>{rescue.saviorInitials}</td>
                                        <td>{rescue.stops}</td>
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

export default RescuesComponent