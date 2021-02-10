import React from 'react'
import MovieService from '../services/MovieService'

var movies = "content";

function getSeatsLeft(tickets) {
    var sum;
    var i;
    for (i = 0; i < tickets.length; i++) {
        sum += tickets.quantity;
    }
    return 30 - (sum);
}

class HomeComponent extends React.Component {

    constructor(props){
        super(props)
        this.state = {
            movies:[]
        }
    }

    componentDidMount(){
        MovieService.getMovies().then((response) => {
            this.setState({ movies : response.data.content})
        })
    }

    render(){
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
                                                movie.showtimes.map(
                                                    showtime =>
                                                    <tr key={showtime.id}>
                                                        <button className="showtimes-button">{showtime.time}</button>
                                                        {/* <td>{getSeatsLeft(showtime.tickets)}</td> */}
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

export default HomeComponent