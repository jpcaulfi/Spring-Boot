import React from 'react';
import RunsService from '../services/RunsService';
import BidsService from '../services/BidsService';
import PostBid from './PostBid';
import AssignRescue from './AssignRescue';
import RescuesService from '../services/RescuesService';

class RoutesAtRiskComponent extends React.Component{

    constructor(props){
        super(props)
        this.state = {
            runs:[],
            postBid:false,
            assignRescue:false,
            disableDec: false,
            disableInc: false,
            selectedRouteId: null,
            selectedRouteCode: "",
            selectedSubjectId: null,
            selectedSubjectInitials: "",
            selectedSaviorId: null,
            selectedSaviorInitials: "",
            stops:20
        }
        this.increment = this.increment.bind(this);
        this.decrement = this.decrement.bind(this);
    }

    componentDidMount(){
        RunsService.getRuns().then((response) => {
            this.setState({ runs : response.data})
        })
    }

    postBidOpen(routeId, routeCode, subjectId, subjectInitials) {
        this.setState({ 
            postBid: true,
            selectedRouteId: routeId,
            selectedRouteCode: routeCode,
            selectedSubjectId: subjectId,
            selectedSubjectInitials: subjectInitials
         });
    }

    postBidClose() {
        this.setState({
            postBid:false
        })
    }

    postBidCloseAndClear() {
        this.setState({
            postBid:false,
            stops: 20
        })
    }

    assignRescueOpen(routeId, routeCode, subjectId, subjectInitials) {
        this.setState({ 
            assignRescue: true,
            selectedRouteId: routeId,
            selectedRouteCode: routeCode,
            selectedSubjectId: subjectId,
            selectedSubjectInitials: subjectInitials
         });
    }

    assignRescueClose() {
        this.setState({
            assignRescue:false
        })
    }

    assignRescueCloseAndClear() {
        this.setState({
            assignRescue:false,
            stops: 20
        })
    }

    selectSavior(saviorId, saviorInitials) {
        this.setState({selectedSaviorId: saviorId, selectedSaviorInitials: saviorInitials})
    }

    increment() {
        const plusState = this.state.stops + 1;
        if (this.state.stops < 100){
          this.setState({stops: plusState});
          this.setState({disable: false});
        }
        if (this.state.stops == (100 - 1)) {
          this.setState({disableInc: true});
        }
        if (this.state.stops == 1) {
          this.setState({disableDec: false});
        }
    }
    
    decrement() {
        const minusState = this.state.stops - 1;
        if (this.state.stops > 1) {
            this.setState({stops: minusState });
            if (this.state.stops == 1 + 1) {
            this.setState({disableDec: true});
            }
        } else {
            this.setState({stops: 1});
        }
        if (this.state.stops == 20) {
            this.setState({disableInc: false});
        }
    }

    postToBid(routeId, routeCode, subjectId, subjectInitials, stops) {
        const content = {"id": null, "routeId": routeId, "routeCode": routeCode, "subjectId": subjectId, "subjectInitials": subjectInitials, "stops": stops};
        BidsService.postBid(content);
        this.postBidClose();
        setTimeout(window.location.reload(false),3000);
    }

    assignRescuePost(routeId, routeCode, subjectId, subjectInitials, saviorId, saviorInitials, stops) {
        const content = {"id": null, "routeId": routeId, "routeCode": routeCode, "subjectId": subjectId, "subjectInitials": subjectInitials, "saviorId": saviorId, "saviorInitials": saviorInitials, "stops": stops};
        RescuesService.postRescue(content);
        this.assignRescueClose();
        setTimeout(window.location.reload(false),3000);
    }

    render(){
        const { disableDec, disableInc } = this.state;
        return (
            <div className="core-div">
                <div>
                    <h2 className="routes-at-risk-header">Routes at Risk:</h2>
                </div>
                <div className="routes-at-risk-container">
                    <table className="routes-at-risk-table">
                        <thead>
                            <tr className="table-header">
                                <td>Route Code</td>
                                <td>Initials</td>
                                <td>Stops Left</td>
                                <td>Packages Left</td>
                                <td>Target</td>
                                <td>Actual</td>
                                <td>Delta</td>
                                <td></td>
                                <td></td>
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
                                        <td>{run.targetCompletion}</td>
                                        <td>{run.currentCompletion}</td>
                                        <td>{run.delta}</td>
                                        <td><button className="assign-rescue-table-button" href="javascript:;" onClick={e => this.assignRescueOpen(run.routeId, run.routeCode, run.driverId, run.driverInitials)}>Assign Rescue</button></td>
                                        <td><button className="post-rescue-table-button" href="javascript:;" onClick={e => this.postBidOpen(run.routeId, run.routeCode, run.driverId, run.driverInitials)}>Post for Bid</button></td>
                                    </tr>
                                )
                            }
                        </tbody>
                    </table>
                </div>
                <PostBid show={this.state.postBid} handleClose={e => this.postBidCloseAndClear(e)}>
                    <h2 className="post-bid-master-header">Post Rescue for Bid:</h2>
                    <div className="post-bid-master-div">
                        <div className="post-bid-routecode-div">
                            <p className="post-bid-routecode-caption">Route:</p>
                            <p className="post-bid-routecode">{this.state.selectedRouteCode}</p>
                        </div>
                        <div className="post-bid-initials-div">
                            <p className="post-bid-initials-caption">Driver:</p>
                            <p className="post-bid-initials">{this.state.selectedSubjectInitials}</p>
                        </div>
                    </div>
                    <div className="quantity-container">
                        <p className="quantity-stops-label">Number of Stops:</p>
                        <span className="quantity-picker">
                            <button className={`${disableDec ? 'mod-disable ' : ''}quantity-modifier modifier-left`} onClick={this.decrement}>&ndash;</button>
                            <input className="quantity-display" type="text" value={this.state.stops} readOnly />
                            <button className={`${disableInc ? 'mod-disable ' : ''}quantity-modifier modifier-right`} onClick={this.increment}>&#xff0b;</button>
                        </span>
                    </div>
                    <div className="post-bid-button-container">
                        <button className="post-bid-button" onClick={e => this.postToBid(this.state.selectedRouteId, this.state.selectedRouteCode, this.state.selectedSubjectId, this.state.selectedSubjectInitials, this.state.stops)}>Post</button>
                    </div>
                </PostBid>
                <AssignRescue show={this.state.assignRescue} handleClose={e => this.assignRescueCloseAndClear(e)}>
                    <h2 className="assign-rescue-master-header">Manually Assign Rescue:</h2>
                    <div className="assign-rescue-master-div">
                        <div className="assign-rescue-routecode-div">
                            <p className="assign-rescue-routecode-caption">Route:</p>
                            <p className="assign-rescue-routecode">{this.state.selectedRouteCode}</p>
                        </div>
                        <div className="assign-rescue-initials-div">
                            <p className="assign-rescue-initials-caption">Driver:</p>
                            <p className="assign-rescue-initials">{this.state.selectedSubjectInitials}</p>
                        </div>
                        <div className="assign-rescue-savior-div">
                            <p className="assign-rescue-savior-caption">Savior:</p>
                            <p className="assign-rescue-savior">{this.state.selectedSaviorInitials}</p>
                        </div>
                    </div>
                    <div className="available-drivers-core-div">
                        <div>
                            <p>Select a Savior:</p>
                        </div>
                        <div className="available-drivers-container">
                            <table className="available-drivers-table">
                                <thead>
                                    <tr className="table-header">
                                        <td>Route Code</td>
                                        <td>Initials</td>
                                        <td>Stops Left</td>
                                        <td>Packages Left</td>
                                        <td>Target</td>
                                        <td>Actual</td>
                                        <td>Delta</td>
                                        <td></td>
                                    </tr>
                                </thead>
                                <tbody>
                                    {
                                        this.state.runs.sort((a, b) => b.delta - a.delta).map(
                                            run =>
                                            <tr className="available-drivers-data" key={run.id}>
                                                <td>{run.routeCode}</td>
                                                <td>{run.driverInitials}</td>
                                                <td>{run.stopsLeft}</td>
                                                <td>{run.packagesLeft}</td>
                                                <td>{run.targetCompletion}</td>
                                                <td>{run.currentCompletion}</td>
                                                <td>{run.delta}</td>
                                                <td><button className="select-available-driver-button" href="javascript:;" onClick={e => this.selectSavior(run.driverId, run.driverInitials)}>Select</button></td>
                                            </tr>
                                        )
                                    }
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div className="quantity-container">
                        <p className="quantity-stops-label">Number of Stops:</p>
                        <span className="quantity-picker">
                            <button className={`${disableDec ? 'mod-disable ' : ''}quantity-modifier modifier-left`} onClick={this.decrement}>&ndash;</button>
                            <input className="quantity-display" type="text" value={this.state.stops} readOnly />
                            <button className={`${disableInc ? 'mod-disable ' : ''}quantity-modifier modifier-right`} onClick={this.increment}>&#xff0b;</button>
                        </span>
                    </div>
                    <div className="assign-rescue-button-container">
                        <button className="assign-rescue-button" onClick={e => this.assignRescuePost(this.state.selectedRouteId, this.state.selectedRouteCode, this.state.selectedSubjectId, this.state.selectedSubjectInitials, this.state.selectedSaviorId, this.state.selectedSaviorInitials, this.state.stops)}>Assign</button>
                    </div>
                </AssignRescue>
            </div>
        )
    }
}

export default RoutesAtRiskComponent