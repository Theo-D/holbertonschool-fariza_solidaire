 import { useNavigate } from "react-router-dom";

 const PartnerCard = ({ name, homepage, logoUrl}) => {
    return (
    <a
        href={homepage}
        target="_blank"
        rel="noopener noreferrer"
        className="flex flex-col items-center bg-white border border-gray-200 rounded-lg p-4 w-52 h-52 hover:scale-105 transition-transform shrink-0"
    >
        {/* Fixed-size logo container */}
        <div className="w-50 h-32 mb-2 flex items-center justify-center bg-gray-50 rounded hover:cursor-pointer">
          <img
            src={logoUrl}
            alt={`${name} logo`}
            className="object-contain w-full h-full"
            onClick={() => window.location.replace(homepage)}
          />
        </div>
        {/* Partner name */}
        <p
          className="text-sm font-semibold text-gray-800 text-center w-full"
          title={name} // shows full name on hover
        >
          {name}
        </p>
      </a>);
 }

 export default PartnerCard;
