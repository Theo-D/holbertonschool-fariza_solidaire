import { Sparkles, Menu, X } from "lucide-react";
import { Link } from 'react-router-dom';
import { useState } from "react";
import { useAuth } from "../context/AuthContext";
import { useNavigate } from "react-router-dom";


const NavBar = () => {
    //const [mobileMenuOpen, setMobileMenuOpen] = useState(false);
    const {user, hasRole, logout} = useAuth();
    const navigate = useNavigate();

    const handleLogin = async () => {
        await logout();
        navigate("/home");
    }

    return (
        <nav className="bg-white backdrop-blur-md shadow-sm fixed w-full top-0 z-50">
            <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
                <div className="flex justify-between items-center h-16">
                    <div className="flex items-center">
                        <div className="w-10 h-10 rounded-lg flex items-center justify-center">
                            <img src="/img/zafira_logo.png"></img>
                        </div>
                        <button
                            onClick={() => navigate("/home")}
                            className="ml-3 text-xl font-bold bg-linear-to-r from-[#42AAE1] via-[#E82B89] to-[#FCD916]
                                       bg-clip-text text-transparent
                                       hover:from-[#FCD916] hover:via-[#E82B89] hover:to-[#42AAE1]
                                        transition-all duration-300 cursor-pointer "
                        >
                            Zafira Solidaire
                        </button>
                    </div>

                    <div className="hidden md:flex space-x-8">
                        <a href="#features" className="text-gray-700 hover:text-[#42AAE1] transition-colors">Notre mission</a>
                        <a href="#about" className="text-gray-700 hover:text-[#E82B89] transition-colors">À propos</a>
                        <Link to="/blog" className="text-gray-700 hover:text-[#FCD916] transition-colors">Blogs</Link>
                         <Link to = "/contatct" className="text-gray-700 hover:text-[#42AAE1] transition-colors">Contact</Link>
                    </div>

                    { !user && (
                        <div className="hidden md:block">
                        <Link to="/login"><button className="px-6 py-2 rounded-lg text-white font-medium transition-all hover:scale-105 hover:shadow-lg cursor-pointer" style={{ backgroundColor: '#E82B89' }}>
                            Se connecter
                        </button></Link>
                        </div>
                    )}

                    { user && (hasRole(user) !== undefined) && (
                        <div className="hidden md:block">
                        <button
                            onClick={() => logout()}
                            className="px-6 py-2 rounded-lg text-white font-medium transition-all hover:scale-105 hover:shadow-lg cursor-pointer" style={{ backgroundColor: '#E82B89' }}
                        >
                            Se déconnecter
                        </button>
                        </div>
                    )}

                    {/* <button
                        className="md:hidden"
                        onClick={() => setMobileMenuOpen(!mobileMenuOpen)}
                    >
                        {mobileMenuOpen ? <X className="w-6 h-6" /> : <Menu className="w-6 h-6" />}
                    </button> */}
                </div>
            </div>

            {/* {mobileMenuOpen && (
                <div className="md:hidden bg-white border-t">
                    <div className="px-4 pt-2 pb-4 space-y-2">
                        <a href="#features" className="block px-3 py-2 rounded-lg text-gray-700 hover:bg-slate-50">Notre mission</a>
                        <a href="#about" className="block px-3 py-2 rounded-lg text-gray-700 hover:bg-slate-50">À propos</a>
                        <Link to="/blog" className="block px-3 py-2 rounded-lg text-gray-700 hover:bg-slate-50">Blogs</Link>
                        <a href="#contact" className="block px-3 py-2 rounded-lg text-gray-700 hover:bg-slate-50">Contact</a>

                        <button className="w-full px-6 py-2 rounded-lg text-white font-medium" style={{ backgroundColor: '#E82B89' }}>
                            Se connecter
                        </button>
                    </div>
                </div>
            )} */}
        </nav>
    );

}

export default NavBar;
