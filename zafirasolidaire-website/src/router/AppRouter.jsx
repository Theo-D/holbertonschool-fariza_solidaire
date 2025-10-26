import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { AuthProvider } from "../context/AuthContext";
import Login from "../pages/Login";
import Home from "../pages/Home";
import ProtectedRoute from "../components/ProtectedRoute";
import Unauthorized from "../pages/Unauthorized";
import Admin from "../pages/Admin";
import Register from "../pages/Register";
import Blog from "../pages/Blog";

export default function AppRouter() {
  return (
    <AuthProvider>
      <Router>
        <Routes>
          <Route path="/home" element={<Home />} />
          <Route
            path="/admin"
            element = {
              <ProtectedRoute requiredRole={"ROLE_ADMIN"}>
                <Admin/>
              </ProtectedRoute>
            }
          />
          <Route path="/login" element={<Login />} />
          <Route path="/unauthorized" element={<Unauthorized />} />
          <Route path="/register" element={<Register/>}/>
          <Route path="/blog/:documentId" element={<Blog/>}/>
        </Routes>
      </Router>
    </AuthProvider>
  );
}
