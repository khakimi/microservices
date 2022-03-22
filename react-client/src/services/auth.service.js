import axios from "axios";
const API_URL = "http://localhost:9091/api/v1/auth/";
class AuthService {
    sendOtp(phoneNumber) {
        return axios
            .post(API_URL + "send-otp", {
                phoneNumber
            }).then(response =>{
                console.log(response.data)
            });
    }
    verifyOtp(phoneNumber, otp) {
        return axios.post(API_URL + "verify-otp", {
            phoneNumber,
            otp
        }).then(response => {
            if (response.data) {
                localStorage.setItem("user", response.data);
            }
            return response.data;
        });
    }
    logout() {
        localStorage.removeItem("user");
    }
    getCurrentUser() {
        return JSON.parse(localStorage.getItem('user'));;
    }
}
export default new AuthService();