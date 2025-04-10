import React from 'react';
import {Link} from "react-router-dom";
const SuccessPage = () => {
    return (
        <div className="container mt-4">
            <h2>✅ Confession Submitted!</h2>
            <h5>Thank you for submitting your confession. Your confession  will be posted after being approved by moderator.</h5>
            <Link to="/" ><p>Go back to Home Page.</p></Link>

        </div>
    );
};

export default SuccessPage;