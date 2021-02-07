import axios from "axios";
import authHeader from "./auth-header"

const baseURL = `${process.env.VUE_APP_BASE_URL}/api`;

const instance = axios.create({
    baseURL,
})

instance.interceptors.request.use(
    async config => {
        const authToken = await authHeader()
        config.headers = {
            'Authorization': `${authToken.Authorization}`
        }
        return config;
    },
    error => {
        Promise.reject(error)
    });

export default instance;