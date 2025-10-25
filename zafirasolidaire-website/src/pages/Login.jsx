import { useState } from "react";
import { useAuth } from "../context/AuthContext";
import LoginInput from "../components/LoginInput";
import PageLayout from "../components/PageLayout";

function Login() {
  const [formData, setFormData] = useState({ emailAddress: "", password: "" });
  const [error, setError] = useState(null);
  const { login } = useAuth();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const result = await login(formData.emailAddress, formData.password);
      console.log("Login successful", result);
    } catch (error) {
      setError(error.message);
    }
  };

  return (
  <PageLayout>
    <div className="flex flex-col min-h-screen bg-gray-100">
      <div className="grow flex items-center justify-center">
        <div className="bg-white shadow-lg rounded-xl p-8 w-full max-w-md">
          <LoginInput
            formData={formData}
            onChange={handleChange}
            onSubmit={handleSubmit}
            error={error}
          />
        </div>
      </div>
    </div>
  </PageLayout>
);
}

export default Login;
