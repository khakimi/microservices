import axios from 'axios';
import authHeader from './auth-header';
import {User} from "../models/User";
const API_URL = 'http://localhost:9091/api/v1/user/';
class UserService {
    getUser() {
        return axios.get(API_URL + 'get', { headers: authHeader() });
    }
    saveUser(user: User){
        return axios.post(API_URL+ 'save', user, {headers: authHeader()});
    }

}
export default new UserService();