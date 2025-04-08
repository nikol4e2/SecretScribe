import React from 'react';

const LikeDislike = ({confession}) => {
    return (
        <div className="like-dislike d-flex mt-2">
            <form action="" className="mr-2">
                <input type="hidden" value={confession.id} />
                <button type="submit" className="btn btn-success">
                    Approve
                    <span className="badge badge-light">{confession.likes}</span>
                </button>
            </form>
            <form action="" >
                <input type="hidden" value={confession.id} />
                <button type="submit" className="btn btn-danger">
                    Approve
                    <span className="badge badge-light">{confession.dislikes}</span>
                </button>
            </form>


        </div>
    );
};

export default LikeDislike;