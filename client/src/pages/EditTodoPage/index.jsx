import { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import API from '../../services/api';

function EditTodoPage() {
  const { id } = useParams();
  const [form, setForm] = useState({ title: '',completed: 'false' });

  const [categories, setCategories] = useState([]);
  const [category, setCategory] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    (async () => {
      const categories = await API.getCategories();
      setCategories(categories);

      const todo = await API.getTodo(id);
      setForm({ title: todo.title, completed: todo.completed });
      setCategory(todo.category.id);
    })();
  }, [id]);

  const handleChange = e =>
    setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = async e => {
    e.preventDefault();
    await API.updateTodo(id, form);
    navigate('/todos');
  };

  return (
    <div className="md:container md:mx-auto p-10">
      <h1 className="text-3xl font-bold mb-4">Update Todo</h1>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          name="title"
          value={form.title}
          placeholder="Title"
          onChange={handleChange}
          className="w-full mb-4 p-2 border rounded"
        />

        <select
          className="w-full mb-4 p-2 border rounded"
          onChange={e => setCategory(e.target.value)}
          value={category}
        >
          <option>Select Category</option>

          {categories.map(category => (
            <option key={category.id} value={category.id}>
              {category.name}
            </option>
          ))}
        </select>

        <button
          type="submit"
          className="py-2 px-4 bg-blue-500 text-white rounded"
        >
          Update Todo
        </button>
      </form>
    </div>
  );
}

export default EditTodoPage;
