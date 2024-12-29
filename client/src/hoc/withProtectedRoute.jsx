import PropTypes from 'prop-types';
import { Navigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

const withProtectedRoute = Component => {
  const ProtectedComponent = props => {
    const { user, loading } = useAuth();

    if (loading) return <div></div>;
    if (!user) return <Navigate to="/login" />;
    return <Component {...props} />;
  };

  ProtectedComponent.propTypes = {
    children: PropTypes.node
  };

  return <ProtectedComponent />;
};

export default withProtectedRoute;
