import axios from 'axios'

const RUNS_REST_API_URL = 'http://localhost:8080/api/runs'

class RunsService {

    getRuns(){
        return axios.get(RUNS_REST_API_URL);
    }

}

export default new RunsService()