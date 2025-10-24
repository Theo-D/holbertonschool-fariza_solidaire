import React from "react";
import { Link } from "react-router-dom";

function Navbar() {
  return (
    <div className="navbar bg-base-200 shadow-md px-6">
      <div className="flex-0 bg-42AAE1"/>
      <div className="flex-0 bg-E82B89"/>
      <div className="flex-0 bg-FCD916"/>
      <div className="flex-1">
        <Link to="/" className="text-xl font-bold text-primary">
          Zafira Solidaire
        </Link>
      </div>
      <div className="flex-none">
        <ul className="menu menu-horizontal px-1">
          <li><Link to="/">Accueil</Link></li>
          <li><Link to="/about">A propos</Link></li>
          <li><Link to="/contact">Contact</Link></li>
          <li><button className="button bg-blue-600 text-white hover:bg-blue-800"><Link to="/login">Se connecter</Link></button></li>
        </ul>
      </div>
    </div>
  );
}

export default Navbar;
