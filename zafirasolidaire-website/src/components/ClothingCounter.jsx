import { useEffect, useState } from "react";
import { Shirt } from "lucide-react";
import { getClothingWeight } from "../services/clothing_services";

const useCountUp = (end, duration = 2000) => {
  const [count, setCount] = useState(0);

  useEffect(() => {
    let startTime;
    let animationFrame;

    const animate = (currentTime) => {
      if (!startTime) startTime = currentTime;
      const progress = Math.min((currentTime - startTime) / duration, 1);
      setCount(Math.floor(progress * end));
      if (progress < 1) {
        animationFrame = requestAnimationFrame(animate);
      }
    };

    animationFrame = requestAnimationFrame(animate);
    return () => cancelAnimationFrame(animationFrame);
  }, [end, duration]);

  return count;
};

export default function ClothingCounter({ Label }) {
  const [value, setValue] = useState(0);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);
  const animatedValue = useCountUp(value);

  useEffect(() => {
    const fetchData = async () => {
      try {
        setIsLoading(true);
        const response = await getClothingWeight();
        
        // Ajustez selon la structure de votre réponse
        // Option 1 : si la réponse est directement { weight: 1234 }
        setValue(response.data.weight || 0);
        
        // Option 2 : si c'est juste un nombre
        // setValue(response.data || 0);
        
        // Option 3 : si c'est dans un objet imbriqué
        // setValue(response.data.data.weight || 0);
        
        setError(null);
      } catch (err) {
        console.error('Erreur récupération clothing:', err);
        setError(err.message);
      } finally {
        setIsLoading(false);
      }
    };

    fetchData();
    const interval = setInterval(fetchData, 30000);
    return () => clearInterval(interval);
  }, []);

  if (error) {
    return (
      <div className="bg-white rounded-2xl p-8 shadow-lg">
        <div className="text-center">
          <p className="text-red-600 text-sm">⚠️ {error}</p>
        </div>
      </div>
    );
  }

  return (
    <div className="bg-white rounded-2xl p-8 shadow-lg hover:shadow-xl transition-shadow">
      <div className="flex items-center gap-4">
        <div 
          className="w-14 h-14 rounded-xl flex items-center justify-center"
          style={{ backgroundColor: '#42AAE1' }}
        >
          <Shirt className="w-7 h-7 text-white" />
        </div>
        <div>
          <p className="text-gray-600 text-sm font-medium">{Label}</p>
          {isLoading ? (
            <div className="h-10 w-24 bg-slate-200 animate-pulse rounded mt-1"></div>
          ) : (
            <p className="text-4xl font-bold text-gray-900">
              {animatedValue.toLocaleString('fr-FR')} kg
            </p>
          )}
        </div>
      </div>
    </div>
  );
}