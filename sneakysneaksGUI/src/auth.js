import React, { createContext, useContext, useEffect, useState } from 'react';

const AuthContext = createContext({ user: null, loading: true, refresh: () => {} });

export function AuthProvider({ children }) {
    const [user, setUser] = useState(null);
    const [loading, setLoading] = useState(true);

    const refresh = async () => {
        try {
            const res = await fetch('/api/me', { credentials: 'include' });
            setUser(res.ok ? (await res.json()).name : null);
        } catch {
            setUser(null);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => { refresh(); }, []);

    return (
        <AuthContext.Provider value={{ user, loading, refresh }}>
            {children}
        </AuthContext.Provider>
    );
}

export function useAuth() {
    return useContext(AuthContext);
}
