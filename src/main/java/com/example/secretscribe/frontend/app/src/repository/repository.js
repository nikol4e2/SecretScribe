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
        return axios.post("/confession/add",  text,{
            headers: {
                "Content-Type": "text/plain",
            }
        });
    },

    deleteConfession: (id) =>{
        return axios.delete(`/confession/${id}`);
    },

    fetchComments: (id) =>{
        return axios.get(`/comments/${id}`);
    },

    addLikeToConfession: (id) =>{
        return axios.post(`/confession/approve/${id}`);
    },

    addDislikeToConfession: (id) =>{
        return axios.post(`/confession/condemn/${id}`);
    },

    addCommentToConfession: (id,text) =>{
        return axios.post(`/comments/add/${id}`,text,{
            headers: {
                "Content-Type": "text/plain",
            }
        });
    },

    addLikeToComment: (id) =>{
        return axios.post(`/comments/like/${id}`);
    },

    addDislikeToComment: (id) =>{
        return axios.post(`/comments/dislike/${id}`);
    }




}

export default ConfessionService;