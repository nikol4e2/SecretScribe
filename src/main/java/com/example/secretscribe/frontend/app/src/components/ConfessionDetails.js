import React, {useEffect} from 'react';

import CommentForm from "./CommentForm";
import ConfessionService from "../repository/repository";
import {useParams} from "react-router";
import LikeDislike from "./LikeDislike";

const ConfessionDetails = () => {
    const id=useParams();
    const [confession, setConfession] = React.useState(null);
    const [comments, setComments] = React.useState([]);

    const onCommentAdded = () => {
        loadComments(id);
    }

    useEffect(() => {
        loadConfessionById(id);
        loadComments(id);

    },[id]);

    const loadConfessionById = (idObj) => {

        ConfessionService.fetchConfessionById(idObj.confessionId).then(data => setConfession(data.data)).catch(err => console.log(err));
    }

    const loadComments = (confessionIdObj) => {

       ConfessionService.fetchComments(confessionIdObj.confessionId).then(data => {setComments(data.data)}).catch(err => console.log(err));
    }

    if (!confession) {
        return <div>Loading confession...</div>
    }

    return (
        <div className="container mt-5">
            <div className="row">
                <div className="col-md-8 offset-md-2">
                    <div className="confession">
                        <p>{confession.text}</p>
                        <LikeDislike confession={confession} />

                        <div className="comment-section mt-4">

                            <CommentForm onCommentAdded={onCommentAdded} confessionId={confession.id} />
                            <h5>Comments</h5>
                            {comments.length > 0 ?(
                                comments.map(comment =>(
                                    <div key={comment.id} className="comment mb-3">
                                        <p>{comment.text}</p>
                                        <div className="like-dislike">
                                            <form >
                                                <input type="hidden" value={comment.id} name="commentId"/>
                                                <input type="hidden" value={confession.id} name="confessionId"/>
                                                <button type="submit" className="btn btn-success mr-2">Approve</button>

                                                <span className="">{comment.likes}</span>
                                            </form>
                                            <form >
                                                <input type="hidden" value={comment.id} name="commentId" />
                                                <input type="hidden" value={confession.id} name="confessionID" />
                                                <button type="submit" className="btn btn-danger">
                                                    Condemn
                                                </button>
                                                <span className="">{comment.dislikes}</span>
                                            </form>
                                        </div>
                                    </div>
                                ))
                            ):(
                                <p>No comments yet.</p>
                            )}


                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default ConfessionDetails;