import React, {useEffect} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css'
import CommentForm from "./CommentForm";
import ConfessionService from "../repository/repository";
import {useParams} from "react-router";
import LikeDislike from "./LikeDislike";
import { Card, Container, Row, Col, Button, Form, Badge } from 'react-bootstrap';

const ConfessionDetails = () => {
    const {confessionId}=useParams();
    const [confession, setConfession] = React.useState(null);
    const [comments, setComments] = React.useState([]);

    const hasReactedToComment= (commentId) => {
        const commentReactions = JSON.parse(localStorage.getItem("commentReactions") || "{}");
        return commentReactions[commentId];
    }

    const setReactedToComment = (commentId,type) => {
        const commentReactions = JSON.parse(localStorage.getItem("commentReactions") || "{}");
        commentReactions[commentId] = type;
        localStorage.setItem("commentReactions", JSON.stringify(commentReactions));
    }


    const onCommentAdded = () => {
        loadComments(confessionId);
    }

    useEffect(() => {
        loadConfessionById(confessionId);
        loadComments(confessionId);

    },[confessionId]);

    const loadConfessionById = (confessionId) => {

        ConfessionService.fetchConfessionById(confessionId).then(data => setConfession(data.data)).catch(err => console.log(err));
    }

    const loadComments = (confessionId) => {

       ConfessionService.fetchComments(confessionId).then(data => {setComments(data.data)}).catch(err => console.log(err));
    }

    if (!confession) {
        return <div>Loading confession...</div>
    }

    const handleAddingLikeToComment = (e,commentId) => {
        e.preventDefault();
        if(hasReactedToComment(commentId)){
            return
        }
        ConfessionService.addLikeToComment(commentId).then(()=>{
            setReactedToComment(commentId,"like");
            loadComments(confessionId);

        }).catch(err => console.log(err));
    }


    const handleAddingDislikeToComment = (e,commentId) => {
        if(hasReactedToComment(commentId)){
            return
        }
        e.preventDefault();
        ConfessionService.addDislikeToComment(commentId).then(()=>{
            setReactedToComment(commentId,"dislike");
            loadComments(confessionId);
        }).catch(err => console.log(err));
    }

    return (
        <Container className="mt-5">
            <Row className="justify-content-center">
                <Col md={8}>
                    <Card className="shadow-sm" style={{ backgroundColor: '#1e1f24', color: '#fff', border: '1px solid #333' }}>
                        <Card.Body>
                            <Card.Title className="mb-3" style={{ color: '#cc9304' }}>Anonymous Confession</Card.Title>
                            <Card.Text>{confession.text}</Card.Text>

                            <div className="mb-4">
                                <LikeDislike confession={confession} />
                            </div>

                            <CommentForm onCommentAdded={onCommentAdded} confessionId={confessionId} />

                            <h5 className="mt-5" style={{ color: '#cc9304' }}>Comments</h5>

                            {comments.length > 0 ? (
                                comments.map(comment => (
                                    <Card key={comment.id} className="mb-3 shadow-sm" style={{ backgroundColor: '#1e1f24', color: '#fff', border: '1px solid #333' }}>
                                        <Card.Body>
                                            <Card.Text>{comment.text}</Card.Text>
                                            <div className="d-flex gap-3 align-items-center">
                                                <Form onSubmit={(e) => handleAddingLikeToComment(e, comment.id)} className="d-flex align-items-center">
                                                    <Button
                                                        type="submit"
                                                        variant="success"
                                                        size="sm"
                                                        disabled={hasReactedToComment(comment.id)}
                                                        style={{ backgroundColor: '#cc9304', borderColor: '#cc9304' }}
                                                    >
                                                        Approve
                                                    </Button>
                                                    <Badge bg="light" text="dark" className="ms-2">
                                                        {comment.likes}
                                                    </Badge>
                                                </Form>

                                                <Form onSubmit={(e) => handleAddingDislikeToComment(e, comment.id)} className="d-flex align-items-center">
                                                    <Button
                                                        type="submit"
                                                        variant="danger"
                                                        size="sm"
                                                        disabled={hasReactedToComment(comment.id)}
                                                        style={{ backgroundColor: '#dc3545', borderColor: '#dc3545' }}
                                                    >
                                                        Condemn
                                                    </Button>
                                                    <Badge bg="light" text="dark" className="ms-2">
                                                        {comment.dislikes}
                                                    </Badge>
                                                </Form>
                                            </div>
                                        </Card.Body>
                                    </Card>
                                ))
                            ) : (
                                <p className="text-muted">No comments yet.</p>
                            )}
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
        </Container>
    );
};
export default ConfessionDetails;