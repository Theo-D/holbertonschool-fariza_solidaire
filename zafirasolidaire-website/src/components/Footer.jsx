import { Sparkles } from "lucide-react";
import { Link } from "lucide-react";

const Footer = () => {
    return(
        <footer className="bg-gray-900 text-white py-12 px-4 sm:px-6 lg:px-8 h-48">
        <div className="max-w-7xl mx-auto text-center">
            <div className="flex items-center justify-center mb-6">
                <div className="w-10 h-10 rounded-lg flex items-center justify-center">
                    <div className="w-10 h-10 rounded-lg flex items-center justify-center">
                        <img src="/img/zafira_logo.png"></img>
                    </div>
                </div>
                <span className="ml-3 text-xl font-bold">Zafira Solidaire</span>
            </div>
            <p className="text-gray-400 mb-6">
                © 2025 VotreBrand. Tous droits réservés.
            </p>
            <div className="flex justify-center gap-6">
                <a href="#" className="text-gray-400 hover:text-[#42AAE1] transition-colors">Politique de confidentialité</a>
                <a href="#" className="text-gray-400 hover:text-[#E82B89] transition-colors">Conditions d'utilisation</a>
                <Link to = "/contatct" className="text-gray-400 hover:text-[#FCD916] transition-colors">Contact</Link>
            </div>
        </div>
      </footer>
    );
}

export default Footer;
