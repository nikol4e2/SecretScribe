import React from 'react';
import ConfessionService from "../repository/repository";
const CommentForm = ({confessionId, onCommentAdded}) => {

    const [commentText, setCommentText] = React.useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        ConfessionService.addCommentToConfession(confessionId, commentText).catch(e => console.log(e));
        setCommentText('');
        onCommentAdded();

    }
    return (


        <form  className="mt-2" onSubmit={handleSubmit}>
            <input type="hidden" value={confessionId} />
            <div className="form-group">
                <textarea name="text"  rows="2" placeholder="Add a comment" value={commentText} onChange={(e)=>setCommentText(e.target.value)}></textarea>
            </div>
            <button type="submit" className="btn btn-primary">Submit</button>

        </form>
    );
};

export default CommentForm;