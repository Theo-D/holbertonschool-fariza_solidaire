import RegisterInput from "../components/RegisterInput";
import { useState } from "react";
import { registerUser } from "../services/auth_services/authApi";
import { useNavigate } from "react-router-dom";

function Register() {
  const [formData, setFormData] = useState({
		firstName: "",
    lastName: "",
    emailAddress: "",
    password: "",
		isServiced: false,
  });
	const navigate = useNavigate();

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleRegisterUser = async (formData) => {
  console.log("Saving user via API:", formData);
  const res = await registerUser(formData);
  console.log("API response:", res);
  return res;
};


  const handleSubmit = async (e) => {
		e.preventDefault();
		console.log("Submitting user:", formData);

		try {
			const res = await handleRegisterUser(formData);

			if (res.status===201) {
				alert("Utilisateur créé avec succès, vous pouvez maintenant vous connecter!");
				navigate("/login");
			}
		} catch (error) {
			console.error(error);
			alert("Something went wrong. Please try again.");
		}
};

  return (
    <RegisterInput
      formData={formData}
      onChange={handleChange}
      onSubmit={handleSubmit}
    />
  );
}

export default Register;
