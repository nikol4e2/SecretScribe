import React from 'react';
import ConfessionService from "../repository/repository";
import {useNavigate} from "react-router-dom";
import {Button, Form} from "react-bootstrap";
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




        <Form onSubmit={handleSubmit} className="mt-3">
            <Form.Group controlId="commentText">
                <Form.Label>Add a comment</Form.Label>
                <Form.Control as="textarea" rows={3} value={commentText} onChange={(e)=>setCommentText(e.target.value)}></Form.Control>
            </Form.Group>
            <Button variant="primary" type="submit" className="mt-2">Submit</Button>

        </Form>
    );
};

export default CommentForm;