import axios from "axios";

const baseURL = `${process.env.VUE_APP_BASE_URL}/api`;

export default axios.create({
    baseURL
})