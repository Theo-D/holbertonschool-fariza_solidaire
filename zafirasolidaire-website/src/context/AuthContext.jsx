import React, { createContext, useContext, useState, useEffect, useCallback } from 'react'
import { loginRequest, getCurrentUser } from '../services/auth_services/authApi'
import {refreshRequest, logoutRequest} from '../services/tokenService'

const AuthContext = createContext();

export const AuthProvider = ({children}) => {
    const [user, setUser] = useState(null);
    const [accessToken, setAccessToken] = useState(null);
    const [loading, setLoading] = useState(true);

    const saveToken = function (token) {
        setAccessToken(token);

        if (token) {
            localStorage.setItem('accessToken', token);
        } else {
            localStorage.removeItem('accessToken');
        }
    };

    const loadToken = function () {
        const token = localStorage.getItem('accessToken');

        if (token) {
            setAccessToken(token);
        }
    };

    const login = async function (email, password) {
        setLoading(true);
        try {
            const token = await loginRequest(email, password);
            saveToken(token);
            const userData = await getCurrentUser();
            setUser(userData);
            return token;
        } catch (err) {
            saveToken(null);
            setUser(null);
            throw err;
        } finally {
            setLoading(false);
        }
    };



    const logout = async function () {
        await logoutRequest();

        saveToken(null);
        setUser(null);
    };

    const refresh = useCallback(async function () {
        try {
            const { accessToken: newToken } = await refreshRequest();

            saveToken(newToken);

            return newToken;
        } catch (err) {
            saveToken(null);
            setUser(null);

            throw err;
        }
    }, []);

    const hasRole = useCallback(
        (role) => {
            if (!user) return false;
            if (Array.isArray(user.roles)) {
                return user.roles.includes(role);
            }
            return user.role === role;
        },
        [user]
    );

    useEffect(() => {
        (async function init() {
            setLoading(true);
            loadToken();
            try {
                const userData = await getCurrentUser();
                setUser(userData);
            } catch (err) {
                saveToken(null);
                setUser(null);
            } finally {
                setLoading(false);
            }
        })();
    }, []);


    const value = React.useMemo(() => ({
        user,
        accessToken,
        login,
        logout,
        refresh,
        loading,
        isAuthenticated: !!user,
        hasRole
    }), [user, accessToken, loading]);


    return (
        <AuthContext.Provider value={value}>
            {children}
        </AuthContext.Provider>
    );
};


//Custom hook to use AuthContext easily

export const useAuth = () => useContext(AuthContext);
