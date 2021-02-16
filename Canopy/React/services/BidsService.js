import axios from 'axios'

const BIDS_REST_API_URL = 'http://localhost:8080/api/bids'

class BidsService {

    getBids(){
        return axios.get(BIDS_REST_API_URL);
    }

    postBid(content){
        return axios.post(BIDS_REST_API_URL, content);
    }

}

export default new BidsService()