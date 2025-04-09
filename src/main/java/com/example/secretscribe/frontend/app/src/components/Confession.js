import React from 'react';

import LikeDislike from './LikeDislike';
import CommentSection from "./CommentSection";
const Confession = ({confession}) => {


    return (
        <div className="confession">
            <div className="confession-text">{confession.text}</div>
            <LikeDislike confession={confession.id} />
            <CommentSection confessionId={confession.id} />

        </div>
    );
};

export default Confession;