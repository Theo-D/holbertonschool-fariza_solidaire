import { useState } from "react";

function LoginInput({ formData, onChange, onSubmit, error }) {
  const [showPassword, setShowPassword] = useState(false);

  return (
    <form onSubmit={onSubmit}>
      <h2 className="text-2xl font-semibold text-center mb-6 text-gray-800">
        Veuillez vous connecter
      </h2>

      {/* Email */}
      <div className="mb-4">
        <label className="flex items-center border rounded-lg px-3 py-2 focus-within:ring-2 focus-within:ring-pink-500">
          <input
            type="email"
            name="emailAddress"
            value={formData.emailAddress}
            onChange={onChange}
            required
            placeholder="mail@site.com"
            className="w-full outline-none bg-transparent"
          />
        </label>
      </div>

      {/* Password */}
      <div className="mb-4">
        <label className="flex items-center border rounded-lg px-3 py-2 focus-within:ring-2 focus-within:ring-pink-500">
          <input
            type={showPassword ? "text" : "password"}
            name="password"
            value={formData.password}
            onChange={onChange}
            required
            placeholder="Mot de passe"
            // minLength="8"
            // pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
            className="w-full outline-none bg-transparent"
          />
        </label>
        <div className="flex items-center mt-2">
          <input
            id="showPassword"
            type="checkbox"
            checked={showPassword}
            onChange={() => setShowPassword(!showPassword)}
            className="h-4 w-4 text-pink-600 border-gray-300 rounded focus:ring-pink-500"
          />
          <label htmlFor="showPassword" className="ml-2 text-sm text-gray-600 select-none">
            Afficher le mot de passe
          </label>
        </div>
      </div>

      {error && <p className="text-red-500 text-sm mb-2">{error}</p>}

      <button
        type="submit"
        className="w-full bg-cyan-600 text-white py-2 rounded-lg hover:bg-cyan-700 transition-colors"
      >
        Se connecter
      </button>

      <p className="text-center mt-4 text-sm">
        <a href="./Register" className="text-gray-700 hover:underline">
          Vous n'avez pas encore de compte? Créez en un.
        </a>
      </p>
    </form>
  );
}

export default LoginInput;
