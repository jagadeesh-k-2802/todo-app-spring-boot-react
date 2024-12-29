import { Routes, Route } from 'react-router-dom';
import withProtectedRoute from '../../hoc/withProtectedRoute';
import LoginPage from '../../pages/LoginPage';
import RegisterPage from '../../pages/RegisterPage';
import TodosPage from '../../pages/TodosPage';
import NewTodoPage from '../../pages/NewTodoPage';
import EditTodoPage from '../../pages/EditTodoPage';
import CategoriesPage from '../../pages/CategoriesPage';
import NewCategoryPage from '../../pages/NewCategoryPage';
import EditCategoryPage from '../../pages/EditCategoryPage';

function App() {
  return (
    <div className="min-h-screen bg-gray-50">
      <Routes>
        <Route path="/login" element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />
        <Route path="/todos" element={withProtectedRoute(TodosPage)} />
        <Route path="/todos/new" element={withProtectedRoute(NewTodoPage)} />
        <Route path="/todos/:id/edit" element={withProtectedRoute(EditTodoPage)} />
        <Route path="/categories" element={withProtectedRoute(CategoriesPage)} />
        <Route path="/categories/new" element={withProtectedRoute(NewCategoryPage)} />
        <Route path="/categories/:id/edit" element={withProtectedRoute(EditCategoryPage)} />
      </Routes>
    </div>
  );
}

export default App;
