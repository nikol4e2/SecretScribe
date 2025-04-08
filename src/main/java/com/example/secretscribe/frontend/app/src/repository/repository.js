import axios from "../custom-axios/axios.js";

const ConfessionService= {
    fetchConfessions: () =>{
        return axios.get("/confession");
    },

    fetchPopularConfessions: () =>{
        return axios.get("/confession/popular");
    }



}

export default ConfessionService;