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
        <div className="like-dislike d-flex mt-2">
            <form onSubmit={handleLikeClick} className="mr-2">
                <input type="hidden" value={confession.id} />
                <button type="submit" className="btn btn-success" disabled={hasReacted(confession.id)}>
                    Approve
                    <span className="badge badge-light">{likes}</span>
                </button>
            </form>
            <form onSubmit={handleDislikeClick} className="mr-2" >
                <input type="hidden" value={confession.id} />
                <button type="submit" className="btn btn-danger" disabled={hasReacted(confession.id)}>
                    Condemn
                    <span className="badge badge-light">{dislikes}</span>
                </button>
            </form>


        </div>
    );
};

export default LikeDislike;