import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import NavBar from '../../components/NavBar/NavBar';
import API from '../../services/api';

function NewCategoryPage() {
  const [form, setForm] = useState({ name: '' });
  const navigate = useNavigate();

  const handleChange = e =>
    setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = async e => {
    e.preventDefault();
    await API.createCategory(form);
    navigate('/categories');
  };

  return (
    <>
      <NavBar />

      <div className="md:container md:mx-auto p-10">
        <h1 className="text-3xl font-bold mb-4">New Category</h1>
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
            Create Category
          </button>
        </form>
      </div>
    </>
  );
}

export default NewCategoryPage;
