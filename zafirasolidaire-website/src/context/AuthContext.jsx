import React, { createContext, useContext, useState, useEffect, useCallback } from 'react'
import { loginRequest, getCurrentUser } from '../services/auth'
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
        const token = await loginRequest(email, password);

        saveToken(token);
        const userData = await getCurrentUser();
        setUser(userData);
        return token
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

    useEffect(function () {
        (async function ()  {
            try {
                loadToken();
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
    }), [user, accessToken, loading]);


    return (
        <AuthContext.Provider value={value}>
            {children}
        </AuthContext.Provider>
    );
};


//Custom hook to use AuthContext easily

export const useAuth = () => useContext(AuthContext);
