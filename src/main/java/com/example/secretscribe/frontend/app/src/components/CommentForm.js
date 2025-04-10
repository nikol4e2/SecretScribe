import React from 'react';
import ConfessionService from "../repository/repository";
import {useNavigate} from "react-router-dom";
const CommentForm = ({confessionId, onCommentAdded}) => {

    const navigate = useNavigate();

    const [commentText, setCommentText] = React.useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
           await ConfessionService.addCommentToConfession(confessionId, commentText).catch(e => console.log(e));
            setCommentText('');
            onCommentAdded();
        } catch (error) {
            console.log(error);
        }





    }
    return (


        <form  className="mt-2" onSubmit={handleSubmit}>

            <div className="form-group">
                <textarea name="text"  rows="2" placeholder="Add a comment" value={commentText} onChange={(e)=>setCommentText(e.target.value)}></textarea>
            </div>
            <button type="submit" className="btn btn-primary">Submit</button>

        </form>
    );
};

export default CommentForm;