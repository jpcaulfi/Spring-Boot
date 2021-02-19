import axios from 'axios'

const RESCUES_REST_API_URL = 'http://localhost:8080/api/rescues'

class RescuesService {

    getRescues(){
        return axios.get(RESCUES_REST_API_URL);
    }

    postRescue(content){
        return axios.post(RESCUES_REST_API_URL, content);
    }

}

export default new RescuesService()