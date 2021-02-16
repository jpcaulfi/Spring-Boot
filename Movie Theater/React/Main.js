import React from 'react';
import MovieService from './services/MovieService';
import TicketService from './services/TicketService';
import BuyTickets from './components/BuyTickets'
import Confirmation from './components/Confirmation';
import { useRowSelect } from 'react-table';

function getSeatsLeft(tickets) {
    var sum = 0;
    var i;
    for (i = 0; i < tickets.length; i++) {
        sum += tickets[i].quantity;
    }
    if (30 - sum <= 0) {
        return 0;
    } else {
        return 30 - sum;
    }
}

function generateTicketCount(quantity) {
    if (quantity == 1) {
        return quantity + " Ticket";
    } else {
        return quantity + " Tickets";
    }
}

class Main extends React.Component {

    constructor(props){
        super(props)
        this.state = {
            movies:[],
            buyTickets:false,
            confirmation: false,
            value: 1,
            disableDec: true,
            disableInc: false,
            selectedMovieId: null,
            selectedMovie: "",
            selectedShowtimeId: null,
            selectedShowtime: ""
        }
        this.increment = this.increment.bind(this);
        this.decrement = this.decrement.bind(this);
    }

    componentDidMount(){
        MovieService.getMovies().then((response) => {
            this.setState({ movies : response.data })
        })
    }

    buyTicketsOpen(movieId, movieTitle, showtimeId, showtimeTime) {
        this.setState({ 
            buyTickets: true,
            selectedMovieId: movieId,
            selectedMovie: movieTitle,
            selectedShowtimeId: showtimeId,
            selectedShowtime: showtimeTime
         });
    }

    buyTicketsClose() {
        this.setState({
            buyTickets:false
        })
    }

    buyTicketsCloseAndClear() {
        this.setState({
            buyTickets:false,
            value: 1
        })
    }

    increment() {
        const plusState = this.state.value + 1;
        if (this.state.value < 20){
          this.setState({value: plusState});
          this.setState({disable: false});
        }
        if (this.state.value == (20 - 1)) {
          this.setState({disableInc: true});
        }
        if (this.state.value == 1) {
          this.setState({disableDec: false});
        }
    }
    
    decrement() {
        const minusState = this.state.value - 1;
        if (this.state.value > 1) {
            this.setState({value: minusState });
            if (this.state.value == 1 + 1) {
            this.setState({disableDec: true});
            }
        } else {
            this.setState({value: 1});
        }
        if (this.state.value == 20) {
            this.setState({disableInc: false});
        }
    }

    confirmationOpen() {
        this.setState({ confirmation: true });
    }

    confirmationClose() {
        this.setState({
            confirmation:false,
            value: 1
        })
        window.location.reload(false);
    }

      purchaseTickets(movieId, showtimeId, quantity) {
        const content = {"id": null, "movieId": movieId, "showtimeId": showtimeId, "quantity": quantity};
        TicketService.getTickets(content);
        this.buyTicketsClose();
        this.confirmationOpen();
      }

      

    render(){
        const { disableDec, disableInc } = this.state;
        const idA = 0;
        const idB = 0;

        return(
            <div className="core-div">
                <div className="main-header-div">
                    <h1 className="main-header">Big Joe's Cinema</h1>
                </div>
                <h3 className="showtimes-header">Showings:</h3>
                <p className="showtimes-caption">Click on a showtime to buy tickets!</p>
                <table className="showtimes-table">
                    <tbody>
                        {
                            this.state.movies.map(
                                movie =>
                                <tr key={movie.id} className="table-row-image">
                                    <td><img className="movie-img" src= {movie.image} alt=""></img></td>
                                    <table className="subtable-title">
                                        <tbody>
                                            <td className="title-record"> {movie.title} </td>
                                        </tbody>
                                    </table>
                                    <table>
                                        <tbody>
                                            <td className="rating-caption">Rating:</td>
                                            <td className="rating-record"> {movie.rating} </td>
                                        </tbody>
                                    </table>
                                    <table className="table-row-showtimes">
                                        <tbody>
                                            {
                                                movie.showtimes.sort((a, b) => a.id - b.id).map(
                                                    showtime =>
                                                    <tr key={showtime.id}>
                                                        <td><button className="showtimes-button" href="javascript:;" onClick={e => this.buyTicketsOpen(movie.id, movie.title, showtime.id, showtime.time)}>{showtime.time}</button></td>
                                                        <td>
                                                            <BuyTickets show={this.state.buyTickets} handleClose={e => this.buyTicketsCloseAndClear(e)}>
                                                                <p className="buy-tickets-movie">{this.state.selectedMovie}</p>
                                                                <p>at</p>
                                                                <p className="buy-tickets-showtime">{this.state.selectedShowtime}</p>
                                                                <p className="buy-tickets-header">How Many Seats?</p>
                                                                <div className="quantity-container">
                                                                    <span className="quantity-picker">
                                                                        <button className={`${disableDec ? 'mod-disable ' : ''}quantity-modifier modifier-left`} onClick={this.decrement}>&ndash;</button>
                                                                        <input className="quantity-display" type="text" value={this.state.value} readOnly />
                                                                        <button className={`${disableInc ? 'mod-disable ' : ''}quantity-modifier modifier-right`} onClick={this.increment}>&#xff0b;</button>
                                                                    </span>
                                                                </div>
                                                                <div className="buy-tickets-button-container">
                                                                    <button className="buy-tickets-button" onClick={e => this.purchaseTickets(this.state.selectedMovieId, this.state.selectedShowtimeId, this.state.value)}>Buy</button>
                                                                </div>
                                                            </BuyTickets>
                                                            <Confirmation show={this.state.confirmation} handleClose={e => this.confirmationClose(e)}>
                                                                <h3 className="confirmation-header">Enjoy the Show!</h3>
                                                                <p className="confirmation-quantity">{generateTicketCount(this.state.value)}</p>
                                                                <p>to</p>
                                                                <p className="buy-tickets-movie">{this.state.selectedMovie}</p>
                                                                <p>at</p>
                                                                <p className="buy-tickets-showtime">{this.state.selectedShowtime}</p>
                                                                <p className="confirmation-tagline">Don't forget your popcorn!</p>
                                                            </Confirmation>
                                                        </td>
                                                        <td className="seats-left-label">Seats Left: </td>
                                                        <td>{getSeatsLeft(showtime.tickets)}</td>
                                                    </tr>
                                                )
                                            }
                                        </tbody>
                                    </table>
                                </tr>
                            )
                        }
                        
                    </tbody>
                </table>

            </div>
        )
    }
}

export default Main;