import React from 'react';


const Login = () => {

    const [formData, setFormData] = React.useState({username: '', password: ''});
    const [showPassword, setShowPassword] = React.useState(false);
    const [error, setError] = React.useState("");


    const handleChange = (e) =>{
        setFormData({...formData, [e.target.name]: e.target.value});
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        if(!formData.username || !formData.password)
        {
            setError("Please enter username and password");
            return
        }

        setError("");
        //TODO Call API

    }

    return (
        <div className="login-container">
            <div className="login-card">
                <h2 className="login-title">Welcome back</h2>
                <form action="">
                    <input type="text" placeholder="username" className="login-input"  name="username" onChange={handleChange} value={formData.username} required />
                    <input type={showPassword ? "text" : "password"} placeholder="password" className="login-input"  name="password" onChange={handleChange} value={formData.password} required />
                    <div onClick={e => setShowPassword(!showPassword)}>Show password</div>
                    <button type="submit" className="login-button">Login</button>
                </form>
            </div>

        </div>
    );
};

export default Login;