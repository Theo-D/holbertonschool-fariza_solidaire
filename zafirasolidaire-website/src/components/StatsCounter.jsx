import React, { useState, useEffect } from 'react';
import { Users, TrendingUp } from 'lucide-react';

// Hook personnalisé pour animer les compteurs
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

// Composant de compteur individuel
const CounterCard = ({ icon: Icon, label, value, color, isLoading }) => {
  const animatedValue = useCountUp(value);

  return (
    <div className="bg-white rounded-2xl p-8 shadow-lg hover:shadow-xl transition-shadow">
      <div className="flex items-center gap-4">
        <div 
          className="w-14 h-14 rounded-xl flex items-center justify-center"
          style={{ backgroundColor: color }}
        >
          <Icon className="w-7 h-7 text-white" />
        </div>
        <div>
          <p className="text-gray-600 text-sm font-medium">{label}</p>
          {isLoading ? (
            <div className="h-10 w-24 bg-slate-200 animate-pulse rounded mt-1"></div>
          ) : (
            <p className="text-4xl font-bold text-gray-900">
              {animatedValue.toLocaleString('fr-FR')}
            </p>
          )}
        </div>
      </div>
    </div>
  );
};

// Composant principal des compteurs
export default function StatsCounters() {
  const [stats, setStats] = useState({ users: 0, revenue: 0 });
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchStats = async () => {
      try {
        setIsLoading(true);
        
        // Remplacez par votre URL d'API
        const response = await fetch('https://votre-api.com/api/stats');
        
        if (!response.ok) {
          throw new Error('Erreur lors de la récupération des données');
        }
        
        const data = await response.json();
        setStats({
          users: data.users || 0,
          revenue: data.revenue || 0
        });
        
        setError(null); // Reset l'erreur en cas de succès
      } catch (err) {
        setError(err.message);
        console.error('Erreur:', err);
      } finally {
        setIsLoading(false);
      }
    };

    // Premier appel immédiat
    fetchStats();
    
    // Rafraîchissement automatique toutes les 30 secondes
    const interval = setInterval(fetchStats, 30000);
    
    // Cleanup: arrête l'interval quand le composant se démonte
    return () => clearInterval(interval);
  }, []); // Tableau de dépendances vide = s'exécute une seule fois au montage

  if (error) {
    return (
      <div className="text-center p-8 bg-red-50 rounded-2xl">
        <p className="text-red-600 font-medium">❌ Erreur: {error}</p>
        <button 
          onClick={() => window.location.reload()} 
          className="mt-4 px-4 py-2 bg-red-600 text-white rounded-lg hover:bg-red-700 transition-colors"
        >
          Réessayer
        </button>
      </div>
    );
  }

  return (
    <div className="grid md:grid-cols-2 gap-6 max-w-4xl mx-auto">
      <CounterCard
        icon={Users}
        label="Personnes accompagnées"
        value={stats.users}
        color="#42AAE1"
        isLoading={isLoading}
      />
      <CounterCard
        icon={TrendingUp}
        label="Poids de vêtements donnés"
        value={stats.revenue}
        color="#E82B89"
        isLoading={isLoading}
      />
    </div>
  );
}