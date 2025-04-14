import React from 'react';
import Confession from "./Confession";
import {useState} from "react";
import {useEffect} from "react";
import ConfessionService from "../repository/repository";
import { Container, Row, Col, Spinner, Alert } from 'react-bootstrap';
const ConfessionList = ({type}) => {

    const [confessions, setConfessions] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        if(type === "popular") {
            loadPopularConfession()
        }else {
            loadConfessions();
        }
    },[type])



    const loadConfessions= () =>{
        setLoading(true);
        ConfessionService.fetchConfessions().then(data => {setConfessions(data.data)
        setLoading(false)}).catch(err => {console.log(err); setLoading(false)});
    }

    const loadPopularConfession= () =>{
        ConfessionService.fetchPopularConfessions().then(data => setConfessions(data.data)).catch(err => console.log(err));
    }



    return (
        <Container className="mt-5">
            <Row className="justify-content-center">
                <Col md={8}>
                    {loading ? (
                        <div className="text-center mt-5">
                            <Spinner animation="border" role="status" />
                        </div>
                    ) : confessions.length > 0 ? (
                        confessions.map((confession) => (
                            <Confession key={confession.id} confession={confession} />
                        ))
                    ) : (
                        <Alert variant="light" className="text-center mt-5">
                            No confessions yet.
                        </Alert>
                    )}
                </Col>
            </Row>
        </Container>
    );
};

export default ConfessionList;