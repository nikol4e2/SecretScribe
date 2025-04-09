import axios from "../custom-axios/axios.js";

const ConfessionService= {
    fetchConfessions: () =>{
        return axios.get("/confession");
    },

    fetchConfessionById: (id) =>{
        return axios.get(`/confession/${id}`);
    },

    fetchPopularConfessions: () =>{
        return axios.get("/confession/popular");
    },

    addConfession: (text) =>{
        return axios.post("/confession",  text);
    },

    deleteConfession: (id) =>{
        return axios.delete(`/confession/${id}`);
    },

    fetchComments: (id) =>{
        return axios.get(`/comments/${id}`);
    }


}

export default ConfessionService;