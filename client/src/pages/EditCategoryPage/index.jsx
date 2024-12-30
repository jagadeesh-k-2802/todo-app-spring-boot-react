import { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import NavBar from '../../components/NavBar/NavBar';
import API from '../../services/api';

function EditCategoryPage() {
  const { id } = useParams();
  const [form, setForm] = useState({ name: '' });
  const navigate = useNavigate();

  useEffect(() => {
    (async () => {
      const category = await API.getCategory(id);
      setForm({ name: category.name });
    })();
  }, [id]);

  const handleChange = e =>
    setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = async e => {
    e.preventDefault();
    await API.updateCategory(id, form);
    navigate('/categories');
  };

  return (
    <>
      <NavBar />

      <div className="md:container md:mx-auto p-10">
        <h1 className="text-3xl font-bold mb-4">Update Category</h1>
        <form onSubmit={handleSubmit}>
          <input
            type="text"
            name="name"
            value={form.name}
            placeholder="Name"
            onChange={handleChange}
            className="w-full mb-4 p-2 border rounded"
          />

          <button
            type="submit"
            className="py-2 px-4 bg-blue-500 text-white rounded"
          >
            Update Category
          </button>
        </form>
      </div>
    </>
  );
}

export default EditCategoryPage;
