import React from 'react';
import ConfessionService from "../repository/repository";

const LikeDislike = ({confession}) => {

    const [likes, setLikes] = React.useState(confession.likes);
    const [dislikes, setDislikes] = React.useState(confession.dislikes);


    const handleLikeClick = (e) => {
        e.preventDefault();
        ConfessionService.addLikeToConfession(confession.id).catch(err => console.log(err));
        setLikes(prevLikes => prevLikes + 1);
    }

    const handleDislikeClick = (e) => {
        e.preventDefault();
        ConfessionService.addDislikeToConfession(confession.id).catch(err => console.log(err));
        setDislikes(prevDislikes => prevDislikes + 1);
    }
    return (
        <div className="like-dislike d-flex mt-2">
            <form onSubmit={handleLikeClick} className="mr-2">
                <input type="hidden" value={confession.id} />
                <button type="submit" className="btn btn-success">
                    Approve
                    <span className="badge badge-light">{likes}</span>
                </button>
            </form>
            <form onSubmit={handleDislikeClick} className="mr-2" >
                <input type="hidden" value={confession.id} />
                <button type="submit" className="btn btn-danger">
                    Condemn
                    <span className="badge badge-light">{dislikes}</span>
                </button>
            </form>


        </div>
    );
};

export default LikeDislike;