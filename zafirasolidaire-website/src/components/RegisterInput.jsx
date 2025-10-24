import { useState } from "react";

function RegisterInput({ formData, onChange, onSubmit }) {
  const [showPassword, setShowPassword] = useState(false);

  return (
    <div className="flex items-center justify-center min-h-screen bg-gray-100">
      <div className="bg-white shadow-lg rounded-xl p-8 w-full max-w-md">
        <h2 className="text-2xl font-semibold text-center mb-6 text-gray-800">
          Créer un compte
        </h2>

        <form onSubmit={onSubmit} className="space-y-5">
          {/* Last Name */}
          <div>
            <label className="flex items-center border rounded-lg px-3 py-2 focus-within:ring-2 focus-within:ring-pink-500">
              <svg
                className="h-5 w-5 text-gray-400 mr-2"
                xmlns="http://www.w3.org/2000/svg"
                viewBox="0 0 24 24"
              >
                <g
                  strokeLinejoin="round"
                  strokeLinecap="round"
                  strokeWidth="2"
                  fill="none"
                  stroke="currentColor"
                >
                  <path d="M19 21v-2a4 4 0 0 0-4-4H9a4 4 0 0 0-4 4v2" />
                  <circle cx="12" cy="7" r="4" />
                </g>
              </svg>
              <input
                type="text"
                name="lastName"
                required
                value={formData.lastName}
                onChange={onChange}
                placeholder="Nom"
                pattern="[A-Za-z]*"
                minLength="3"
                maxLength="30"
                title="Only letters, numbers or dash"
                className="w-full outline-none bg-transparent"
              />
            </label>
            <p className="text-xs text-gray-500 mt-1">
              3 à 30 caractères. <br /> Ne doit contenir que des lettres.
            </p>
          </div>

          {/* First Name */}
          <div>
            <label className="flex items-center border rounded-lg px-3 py-2 focus-within:ring-2 focus-within:ring-pink-500">
              <svg
                className="h-5 w-5 text-gray-400 mr-2"
                xmlns="http://www.w3.org/2000/svg"
                viewBox="0 0 24 24"
              >
                <g
                  strokeLinejoin="round"
                  strokeLinecap="round"
                  strokeWidth="2"
                  fill="none"
                  stroke="currentColor"
                >
                  <path d="M19 21v-2a4 4 0 0 0-4-4H9a4 4 0 0 0-4 4v2" />
                  <circle cx="12" cy="7" r="4" />
                </g>
              </svg>
              <input
                type="text"
                name="firstName"
                required
                value={formData.firstName}
                onChange={onChange}
                placeholder="Prénom"
                pattern="[A-Za-z][A-Za-z0-9-]*"
                minLength="3"
                maxLength="30"
                title="Only letters, numbers or dash"
                className="w-full outline-none bg-transparent"
              />
            </label>
            <p className="text-xs text-gray-500 mt-1">
              3 à 30 caractères. <br /> Ne doit contenir que des lettres.
            </p>
          </div>

          {/* Email */}
          <div>
            <label className="flex items-center border rounded-lg px-3 py-2 focus-within:ring-2 focus-within:ring-pink-500">
              <svg
                className="h-5 w-5 text-gray-400 mr-2"
                xmlns="http://www.w3.org/2000/svg"
                viewBox="0 0 24 24"
              >
                <g
                  strokeLinejoin="round"
                  strokeLinecap="round"
                  strokeWidth="2"
                  fill="none"
                  stroke="currentColor"
                >
                  <rect width="20" height="16" x="2" y="4" rx="2" />
                  <path d="m22 7-8.97 5.7a1.94 1.94 0 0 1-2.06 0L2 7" />
                </g>
              </svg>
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
            <p className="text-xs text-gray-500 mt-1">
              Entrez une adresse e-mail valide.
            </p>
          </div>

          {/* Password */}
          <div>
            <label className="flex items-center border rounded-lg px-3 py-2 focus-within:ring-2 focus-within:ring-pink-500">
              <svg
                className="h-5 w-5 text-gray-400 mr-2"
                xmlns="http://www.w3.org/2000/svg"
                viewBox="0 0 24 24"
              >
                <g
                  strokeLinejoin="round"
                  strokeLinecap="round"
                  strokeWidth="2"
                  fill="none"
                  stroke="currentColor"
                >
                  <path d="M2.586 17.414A2 2 0 0 0 2 18.828V21a1 1 0 0 0 1 1h3a1 1 0 0 0 1-1v-1a1 1 0 0 1 1-1h1a1 1 0 0 0 1-1v-1a1 1 0 0 1 1-1h.172a2 2 0 0 0 1.414-.586l.814-.814a6.5 6.5 0 1 0-4-4z" />
                  <circle cx="16.5" cy="7.5" r=".5" fill="currentColor" />
                </g>
              </svg>
              <input
                type={showPassword ? "text" : "password"}
                name="password"
                required
                value={formData.password}
                onChange={onChange}
                placeholder="Mot de passe"
                minLength="8"
                pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
                title="Doit contenir 8 caractères minimum, incluant majuscule, minuscule et chiffre"
                className="w-full outline-none bg-transparent"
              />
            </label>

            {/* Show password toggle */}
            <div className="flex items-center mt-2">
              <input
                id="showPassword"
                type="checkbox"
                checked={showPassword}
                onChange={() => setShowPassword(!showPassword)}
                className="h-4 w-4 text-pink-600 border-gray-300 rounded focus:ring-pink-500"
              />
              <label
                htmlFor="showPassword"
                className="ml-2 text-sm text-gray-600 select-none"
              >
                Afficher le mot de passe
              </label>
            </div>

            <p className="text-xs text-gray-500 mt-1">
              Doit contenir au moins 8 caractères incluant :
              <br /> - un chiffre
              <br /> - une lettre minuscule
              <br /> - une majuscule
            </p>
          </div>

          {/* Submit button */}
          <button
            type="submit"
            className="w-full bg-cyan-600 text-white py-2 rounded-lg hover:bg-cyan-700 transition-colors"
          >
            S'inscrire
          </button>
        </form>
      </div>
    </div>
  );
}

export default RegisterInput;
