import React from 'react';

const CommentForm = ({confessionId}) => {
    return (
        <form action="" className="mt-2">
            <input type="hidden" value={confessionId} />
            <div className="form-group">
                <textarea name="text"  rows="2" placeholder="Add a comment"></textarea>
            </div>
            <button type="submit" className="btn btn-primary">Submit</button>

        </form>
    );
};

export default CommentForm;