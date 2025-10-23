import { useEffect, useState } from "react";
import { Users } from "lucide-react";
import { countUsers } from "../services/user_services/userApi";

export default function ServicedCounter({ Label }) {
  const [count, setCount] = useState(0);
  const duration = 2000; // durée de l’animation (ms)

  useEffect(() => {
  async function fetchCount() {
    const res = await countUsers();
    const data = await res.json();
    animateCount(data.count);
  }
  
  function animateCount(target) {
    let start = 0;
    const increment = target / (duration / 50);
    const interval = setInterval(() => {
      start += increment;
      if (start >= target) {
        start = target;
        clearInterval(interval);
      }
      setCount(Math.floor(start));
    }, 50);
  }

  fetchCount();
}, []);


  return (
    <div className="flex flex-col items-center justify-center bg-white dark:bg-gray-900 shadow-lg rounded-2xl p-6 w-64">
      <Users className="w-12 h-12 text-indigo-500 mb-2" />
      <h2 className="text-2xl font-bold text-gray-800 dark:text-white">
        {count.toLocaleString()}
      </h2>
      <p className="text-gray-500 dark:text-gray-400 text-sm">
        {Label}
      </p>
    </div>
  );
}
