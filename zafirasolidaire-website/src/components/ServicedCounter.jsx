import { useEffect, useState } from "react";
import { Rss, Users } from "lucide-react";
import { countServicedUsers } from "../services/user_services/userApi";

export default function ServicedCounter({ Label }) {

  const [count, setCount] = useState([]); 

  useEffect(() => {
  async function fetchCount() {
    const res = await countServicedUsers();
    console.log("CECI EST MON COMPTE D'UTILISATEURS: ", res);
    setCount(res.data);
  }
  fetchCount();
}, []);


  return (
    <div className="flex flex-col items-center justify-center bg-white dark:bg-gray-900 shadow-lg rounded-2xl p-6 w-64">
      <Users className="w-12 h-12 text-indigo-500 mb-2" />
      <h2 className="text-2xl font-bold text-gray-800 dark:text-white">
        {count}
      </h2>
      <p className="text-gray-500 dark:text-gray-400 text-sm">
        {Label}
      </p>
    </div>
  );
}
