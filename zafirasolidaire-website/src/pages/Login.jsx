import { useState } from "react";
import {useAuth} from '../context/AuthContext'
import EmailInput from "../components/EmailInput";
import PasswordInput from "../components/PasswordInput";


function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("")
  const [error, setError] = useState(null);

  const {login} = useAuth();

  const handleSubmit = async (e) => {
    e.preventDefault();
    console.log("Email:", email);
    console.log("Password:", password);
    try {
      const result = await login(email, password);
      console.log("Login successful", result);
    } catch (error) {
      setError(error.message);
    }
  };

  return (
    <div>
      <form onSubmit={handleSubmit}>
        <h2>Login</h2>
        <EmailInput
          type="email"
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />
        <PasswordInput
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />
        <button type="submit">
          Log In
        </button>
      </form>
      <a href="./Register"> Vous n'avez pas encore de compte? Créez en un. </a>
    </div>
  );
}

export default Login
