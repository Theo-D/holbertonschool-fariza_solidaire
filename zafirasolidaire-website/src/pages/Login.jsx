import { useState } from "react";
import { useAuth } from "../context/AuthContext";
import LoginInput from "../components/LoginInput";
import PageLayout from "../components/PageLayout";
import { useNavigate } from "react-router-dom";
import { jwtDecode } from "jwt-decode";

function Login() {
  const [formData, setFormData] = useState({ emailAddress: "", password: "" });
  const [error, setError] = useState(null);
  const { login, loading } = useAuth();
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const result = await login(formData.emailAddress, formData.password);
      const userRole = jwtDecode(result).roles[0];
      console.log("Login successful", userRole);
      if(result && userRole == 'ROLE_ADMIN') {
        navigate('/admin');
      } else if (result && userRole == 'ROLE_USER') {
        navigate('/home');
      }
    } catch (error) {
      setError(error.message);
    }
  };

  return (
  <PageLayout>
    <div className="relative flex flex-col min-h-screen bg-gray-100 ">
      <div className={`grow flex items-center justify-center ${loading ? "filter blur-sm pointer-events-none" : ""}`}>
        <div className="grow flex items-center justify-center ">
          <div className="bg-white shadow-lg rounded-xl p-8 w-full max-w-md ">
            <LoginInput
              formData={formData}
              onChange={handleChange}
              onSubmit={handleSubmit}
              error={error}
            />
          </div>
      </div>
      </div>
      {loading && (
          <div className="absolute inset-0 flex items-center justify-center bg-gray/30 z-50">
            <div className="animate-spin rounded-full h-16 w-16 border-b-4 border-cyan-500"></div>
          </div>
      )}
    </div>
  </PageLayout>
);
}

export default Login;
