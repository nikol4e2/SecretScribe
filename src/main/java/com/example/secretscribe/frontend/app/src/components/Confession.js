import React from 'react';

import LikeDislike from './LikeDislike';
import CommentSection from "./CommentSection";
const Confession = ({confession}) => {


    return (
        <div
            className="card mb-4"
            style={{ backgroundColor: '#1e1f24', color: '#fff', border: '1px solid #333' }}
        >
            <div className="card-body">
                <p className="card-text fs-5">{confession.text}</p>

                <LikeDislike confession={confession} />

                <hr className="my-4" style={{ borderColor: '#333' }} />

                <CommentSection confessionId={confession.id} />
            </div>
        </div>
    );
};

export default Confession;