import axios from 'axios'

const TICKETS_REST_API_URL = "http://localhost:8080/api/tickets";

class TicketService {

    getTickets(content){
        return axios.post(TICKETS_REST_API_URL, content);
    }

}

export default new TicketService()