import React from 'react';
import Confession from "./Confession";
import {useState} from "react";
import {useEffect} from "react";
import ConfessionService from "../repository/repository";

const ConfessionList = ({type}) => {

    const [confessions, setConfessions] = useState([]);

    useEffect(() => {
        if(type === "popular") {
            loadPopularConfession()
        }else {
            loadConfessions();
        }
    })



    const loadConfessions= () =>{
        ConfessionService.fetchConfessions().then(data => setConfessions(data.data)).catch(err => console.log(err));
    }

    const loadPopularConfession= () =>{
        ConfessionService.fetchPopularConfessions().then(data => setConfessions(data.data)).catch(err => console.log(err));
    }



    return (
        <div className="container mt-5">
            <div className="row">
                <div className="col-md-8 offset-md-2">
                    {confessions.length > 0 ? (
                    confessions.map(confession =>(
                        <Confession key={confession.id} confession={confession} />
                    ))
                        ) :(
                            <div className="text-center mt-5">
                                <p className="text-muted">No confessions yet</p>
                            </div>
                        )}
                </div>
            </div>
        </div>
    );
};

export default ConfessionList;