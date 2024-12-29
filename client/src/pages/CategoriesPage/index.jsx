import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import NavBar from '../../components/NavBar/NavBar';
import API from '../../services/api';

function CategoriesPage() {
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [categories, setCategories] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    (async () => {
      try {
        const categories = await API.getCategories();
        setCategories(categories);
      } catch (error) {
        setError(error);
      } finally {
        setLoading(false);
      }
    })();
  }, []);

  const handleDelete = async category => {
    await API.deleteCategory(category.id);
    const categories = await API.getCategories();
    setCategories(categories);
  };

  if (loading) return <div></div>;

  if (error)
    return (
      <div className="md:container md:mx-auto p-10">
        <h1 className="text-2xl font-bold">Error Fetching Data</h1>
        <p className="text-red-500">{error.toString()}</p>
      </div>
    );

  return (
    <>
      <NavBar />
      <div className="md:container md:mx-auto p-10">
        <div className="flex items-center justify-between mb-4">
          <h1 className="text-2xl font-bold">Categories</h1>

          <button
            className="px-4 py-2 bg-blue-500 text-white font-semibold rounded hover:bg-blue-600"
            onClick={() => navigate('/categories/new')}
          >
            Create Category
          </button>
        </div>

        <ul>
          {categories.map(category => (
            <li
              key={category.id}
              className="flex items-center justify-between gap-4 mb-2"
            >
              <div className="flex items-center gap-2">
                <span>{category.name}</span>
              </div>

              <div className="ml-auto flex gap-2">
                <button
                  className="bg-yellow-500 text-white px-4 py-1 rounded hover:bg-yellow-600"
                  onClick={() => navigate(`/categories/${category.id}/edit`)}
                >
                  Edit
                </button>
                <button
                  className="bg-red-500 text-white px-4 py-1 rounded hover:bg-red-600"
                  onClick={() => handleDelete(category)}
                >
                  Delete
                </button>
              </div>
            </li>
          ))}
        </ul>
      </div>
    </>
  );
}

export default CategoriesPage;
