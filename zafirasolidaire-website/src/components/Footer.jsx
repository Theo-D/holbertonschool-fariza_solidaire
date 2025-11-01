import { Sparkles } from "lucide-react";
import { Link } from "lucide-react";
import MailBoxSVG from "./MailBoxSvg";
import ClockSvg from "./ClockSvg";

const Footer = () => {
    return(
        <footer className="relative bg-gray-900 text-white py-4 px-4 sm:px-6 lg:px-8 h-52">
        <div className="max-w-7xl mx-auto text-center ">
            <div className="grid grid-cols-1 sm:grid-cols-3 gap-x-12">

                <div className="flex items-center justify-center mb-6">
                    <div className="h-10 rounded-lg flex flex-col text-left">
                        <div className="flex items-center mb-1">
                            <img className="w-8 h-8" src="/img/zafira_logo.png"></img>
                            <span className="ml-3 text-xl font-bold">Zafira Solidaire</span>
                        </div>
                        <p className="text-xs font-bold w-48">Chez Zafira Solidaire, chaque message compte.</p>
                        <p className="text-xs w-48 pt-2">Que vous soyez bénéficiaire, bénévole, donateur ou partenaire, vous êtes au bon endroit pour commencer l’aventure avec nous</p>

                    </div>
                </div>


                <div className="flex items-center justify-center mb-6">
                    <div className="h-10 rounded-lg flex flex-col text-left">
                        <div className="flex items-center mb-1">
                            <MailBoxSVG/>
                            <p className="font-bold text-xl ml-2">Coordonnées:</p>
                        </div>
                        <p className="text-xs"><b>Adresse :</b> Mission Locale de Villepinte</p>
                        <p className="text-xs"><b>Email :</b> contact@zafirasolidaire.org</p>
                        <p className="text-xs"><b>Réseaux Sociaux :</b></p>
                        <ul className="">
                            <li className="text-xs pl-2">Instagram : <a className="underline text-gray-300" href="https://www.instagram.com/zafirasolidaire/">@ZafiraSolidaire</a>
                            </li>
                            <li className="text-xs pl-2">Facebook : <a className="underline text-gray-300" href="https://www.facebook.com/login/?next=https%3A%2F%2Fwww.facebook.com%2Fzafirasolidaire">Zafira Solidaire</a>
                            </li>
                            <li className="text-xs pl-2">Linkedin : <a className="underline text-gray-300" href = "https://linkedin.com/company/zafirasolidaire">Zafira Solidaire</a>
                            </li>
                        </ul>
                    </div>
                </div>

                <div className="flex items-center justify-center mb-6">
                    <div className="h-10 rounded-lg flex flex-col text-left">
                        <div className="flex items-center mb-1">
                            <ClockSvg/>
                            <p className="font-bold text-xl ml-2">Horaires</p>
                        </div>
                        <p className="text-xs"><b>Horaires d'accueil :</b></p>
                        <ul className="pt-2">
                            <li className="text-xs">Du lundi au vendredi - 9h à 17h
                            </li>
                            <li className="text-xs w-48 pt-1">Selon disponibilité et sur rendez-vous via la mission locale
                            </li>
                        </ul>
                    </div>
                </div>

            </div>
            <div className="absolute bottom-4 left-0 right-0 flex justify-center gap-6">
                <p className="text-gray-400">
                    © 2025 VotreBrand. Tous droits réservés.
                </p>
                <a href="#" className="text-gray-400 hover:text-[#42AAE1] transition-colors">
                    Politique de confidentialité
                </a>
                <a href="#" className="text-gray-400 hover:text-[#E82B89] transition-colors">
                    Conditions d'utilisation
                </a>
                <Link
                    to="/contact"
                    className="text-gray-400 hover:text-[#FCD916] transition-colors"
                >
                    Contact
                </Link>
                </div>
        </div>
      </footer>
    );
}

export default Footer;
