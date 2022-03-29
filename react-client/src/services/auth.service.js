import axios from "axios";

const API_URL = "http://localhost:9091/api/v1/auth/";

class AuthService {

    signIn(phoneNumber) {
        return axios
            .post(API_URL + "test-login", {
                phoneNumber
            }).then(response => {
                if (response.data) {
                    localStorage.setItem("user", JSON.stringify(response.data));
                }
                return response.data;
            });
    }

    sendOtp(phoneNumber) {
        return axios
            .post(API_URL + "send-otp", {
                phoneNumber
            }).then(response => {
                console.log(response.data)
            });
    }

    verifyOtp(phoneNumber, otp) {
        return axios.post(API_URL + "verify-otp", {
            phoneNumber,
            otp
        }).then(response => {
            if (response.data) {
                localStorage.setItem("user", JSON.stringify(response.data));
            }
            return response.data;
        });
    }

    logout() {
        localStorage.removeItem("user");
    }

}

export default new

AuthService();