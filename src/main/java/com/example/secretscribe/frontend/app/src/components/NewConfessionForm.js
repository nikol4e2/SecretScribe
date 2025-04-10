import React from 'react';
import { useNavigate } from 'react-router-dom';
import ConfessionService from "../repository/repository";
const NewConfessionForm = () => {




    const [confessionText, setConfessionText] = React.useState('');
    const navigate = useNavigate();

    const handleSubmit = (e) => {
        e.preventDefault();
        ConfessionService.addConfession(confessionText)
            .then(()=>{
                setConfessionText('');
                navigate('/success');
            }).catch(err => console.log(err));

        setConfessionText('');


    }
    return (
        <div className="container">
            <div className="row">
                <div className="col-md-12">

                    <div className="form-container">
                        <h2>Add Confession</h2>
                        <form onSubmit={handleSubmit}>
                            <div className="form-group">
                                <label htmlFor="text">Confession Text:</label>
                                <textarea className="form-control" id="text" rows="3" required name="text" value={confessionText} onChange={(e)=>setConfessionText(e.target.value)}></textarea>
                            </div>
                            <button type="submit"  className="btn btn-primary mt-3">Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default NewConfessionForm;