Just a simple movie theater web app.

Any user can go on and see the showtimes, see seats available, and purchase tickets.

The data used to render the page is from a REST API using Spring Boot, and it changes as you interact with the page, such as purchasing tickets.

If you click a showtime, you are brought to the "Buy Tickets" pop-up for that specific movie and showtime.
Upon 'purchasing' the tickets, you'll see that the Seats Available will reflect a successful POST request according to the number of tickets you bought.
