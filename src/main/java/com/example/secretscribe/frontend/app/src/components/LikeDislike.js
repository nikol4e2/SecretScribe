import React from 'react';
import ConfessionService from "../repository/repository";

const LikeDislike = ({confession}) => {

    const [likes, setLikes] = React.useState(confession.likes);
    const [dislikes, setDislikes] = React.useState(confession.dislikes);


    const hasReacted= (confessionId) =>{
        const reactions= JSON.parse(localStorage.getItem("confessionReactions") || "{}");
        return reactions[confessionId];
    }

    const setReacted = (confessionId,type) => {
        const reactions= JSON.parse(localStorage.getItem("confessionReactions") || "{}");
        reactions[confessionId] = type;
        localStorage.setItem("confessionReactions",JSON.stringify(reactions));
    }


    const handleLikeClick = (e) => {
        if(hasReacted(confession.id)){
            return;
        }
        e.preventDefault();
        ConfessionService.addLikeToConfession(confession.id).catch(err => console.log(err));
        setReacted(confession.id,"like");
        setLikes(prevLikes => prevLikes + 1);
    }

    const handleDislikeClick = (e) => {
        if(hasReacted(confession.id)){
            return;
        }
        e.preventDefault();
        ConfessionService.addDislikeToConfession(confession.id).catch(err => console.log(err));
        setReacted(confession.id,"dislike");
        setDislikes(prevDislikes => prevDislikes + 1);
    }
    return (
        <div className="d-flex gap-3 align-items-center mt-3">
            <form onSubmit={handleLikeClick}>
                <button type="submit" className="btn btn-outline-success d-flex align-items-center gap-2" disabled={hasReacted(confession.id)}>
                    👍 Approve
                    <span className="badge bg-success text-white">{likes}</span>
                </button>
            </form>

            <form onSubmit={handleDislikeClick}>
                <button type="submit" className="btn btn-outline-danger d-flex align-items-center gap-2" disabled={hasReacted(confession.id)}>
                    👎 Condemn
                    <span className="badge bg-danger text-white">{dislikes}</span>
                </button>
            </form>
        </div>
    );
};

export default LikeDislike;