import React from 'react';
import BidsService from '../services/BidsService';

class BidsComponent extends React.Component{

    constructor(props){
        super(props)
        this.state = {
            bids:[]
        }
    }

    componentDidMount(){
        BidsService.getBids().then((response) => {
            this.setState({ bids : response.data})
        })
    }

    render(){
        return (
            <div className="core-div">
                <div>
                    <h2 className="routes-at-risk-header">Rescues Up For Bid:</h2>
                </div>
                <div className="routes-at-risk-container">
                    <table className="bids-table">
                        <thead>
                            <tr className="table-header">
                                <td>Route Code</td>
                                <td>Initials</td>
                                <td>Stops</td>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.bids.map(
                                    bid =>
                                    <tr className="bids-data" key={bid.id}>
                                        <td>{bid.routeCode}</td>
                                        <td>{bid.subjectInitials}</td>
                                        <td>{bid.stops}</td>
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

export default BidsComponent