import axios from 'axios';
import authHeader from './auth-header';
const API_URL = 'http://localhost:9091/api/v1/user/';
class UserService {
    getUser() {
        return axios.get(API_URL + 'get', { headers: authHeader() });
    }

}
export default new UserService();