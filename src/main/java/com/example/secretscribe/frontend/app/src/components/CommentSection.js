import React from 'react';
import { Link } from "react-router-dom";
import CommentForm from "./CommentForm";
const CommentSection = ({confessionId}) => {
    return (
        <div className="comment-section mt-3">
            <h5>
            <Link to={`/comment/${confessionId}`}>Comments</Link>
            </h5>
            <CommentForm confessionId={confessionId} />
        </div>
    );
};

export default CommentSection;