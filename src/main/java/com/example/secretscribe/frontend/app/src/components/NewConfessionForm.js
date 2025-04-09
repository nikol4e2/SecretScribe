import React from 'react';

const NewConfessionForm = () => {
    return (
        <div className="container">
            <div className="row">
                <div className="col-md-12">

                    <div className="form-container">
                        <h2>Add Confession</h2>
                        <form action="" method="">
                            <div className="form-group">
                                <label htmlFor="text">Confession Text:</label>
                                <textarea className="form-control" id="text" rows="3" required name="text"></textarea>
                            </div>
                            <button type="submit" className="btn btn-primary mt-3">Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default NewConfessionForm;