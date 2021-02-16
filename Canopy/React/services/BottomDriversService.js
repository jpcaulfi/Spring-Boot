import axios from 'axios'

const DRIVERS_REST_API_URL = 'http://localhost:8080/api/drivers'

class BottomDriversService {

    getBottomDrivers(){
        return axios.get(DRIVERS_REST_API_URL);
    }

}

export default new BottomDriversService()