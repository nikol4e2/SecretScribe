import React from 'react';
import Confession from "./Confession";

const ConfessionList = ({confessions}) => {
    return (
        <div className="container mt-5">
            <div className="row">
                <div className="col-md-8 offset-md-2">
                    {confessions.map(confession =>(
                        <Confession key={confession.id} confession={confession} />
                    ))}
                </div>
            </div>
        </div>
    );
};

export default ConfessionList;