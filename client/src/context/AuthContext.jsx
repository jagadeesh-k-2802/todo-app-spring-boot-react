import { createContext, useContext, useState, useEffect } from 'react';
import PropTypes from 'prop-types';
import { useNavigate } from 'react-router-dom';
import API from '../services/api';

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [loading, setLoading] = useState(true);
  const [user, setUser] = useState(null);
  const [token, setToken] = useState(localStorage.getItem('token') || null);
  const navigate = useNavigate();

  useEffect(() => {
    if (token) {
      (async () => {
        try {
          const user = await API.me(token);
          setUser(user);
        } catch {
          localStorage.removeItem('token');
          setToken(null);
          setUser(null);
          navigate('/login');
        } finally {
          setLoading(false);
        }
      })();
    }
  }, [token, navigate]);

  const login = async credentials => {
    const token = await API.login(credentials);
    localStorage.setItem('token', token);
    setToken(token);
    navigate('/todos');
  };

  const logout = () => {
    localStorage.removeItem('token');
    setToken(null);
    setUser(null);
    navigate('/login');
  };

  const register = async data => {
    const token = await API.register(data);
    localStorage.setItem('token', token);
    setToken(token);
    navigate('/todos');
  };

  return (
    <AuthContext.Provider
      value={{
        loading,
        user,
        token,
        login,
        logout,
        register
      }}
    >
      {children}
    </AuthContext.Provider>
  );
};

AuthProvider.propTypes = {
  children: PropTypes.node.isRequired
};

// eslint-disable-next-line react-refresh/only-export-components
export const useAuth = () => useContext(AuthContext);
