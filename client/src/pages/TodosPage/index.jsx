import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import NavBar from '../../components/NavBar/NavBar';
import API from '../../services/api';

function TodosPage() {
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [todos, setTodos] = useState([]);
  const [stats, setStats] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    (async () => {
      try {
        const todos = await API.getTodos();
        const stats = await API.getTodoStats();
        setTodos(todos);
        setStats(stats);
      } catch (error) {
        setError(error);
      } finally {
        setLoading(false);
      }
    })();
  }, []);

  const handleCompleted = async todo => {
    await API.updateTodo(todo.id, {
      ...todo,
      completed: !todo.completed
    });

    const todos = await API.getTodos();
    const stats = await API.getTodoStats();
    setTodos(todos);
    setStats(stats);
  };

  const handleDelete = async todo => {
    await API.deleteTodo(todo.id);

    const todos = await API.getTodos();
    const stats = await API.getTodoStats();
    setTodos(todos);
    setStats(stats);
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
          <h1 className="text-2xl font-bold">Todos</h1>

          <button
            className="px-4 py-2 bg-blue-500 text-white font-semibold rounded hover:bg-blue-600"
            onClick={() => navigate('/todos/new')}
          >
            Create Todo
          </button>
        </div>

        <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-4 mb-6">
          <div className="bg-green-100 p-4 rounded shadow text-center">
            <h3 className="text-xl font-bold">Total Todos</h3>
            <p className="text-2xl">{stats.totalTodos}</p>
          </div>
          <div className="bg-yellow-100 p-4 rounded shadow text-center">
            <h3 className="text-xl font-bold">Pending</h3>
            <p className="text-2xl">{stats.pendingTodos}</p>
          </div>
          <div className="bg-blue-100 p-4 rounded shadow text-center">
            <h3 className="text-xl font-bold">Completed</h3>
            <p className="text-2xl">{stats.completedTodos}</p>
          </div>
        </div>

        <ul>
          {todos.map(todo => (
            <li
              key={todo.id}
              className="flex items-center justify-between gap-4 mb-2"
            >
              <div className="flex items-center gap-2">
                <input
                  type="checkbox"
                  checked={todo.completed}
                  onChange={() => handleCompleted(todo)}
                  className="w-4 h-4 rounded-full border-gray-300"
                />
                <span>{todo.title}</span>
              </div>

              <div className="ml-auto flex gap-2">
                <button
                  className="bg-yellow-500 text-white px-4 py-1 rounded hover:bg-yellow-600"
                  onClick={() => navigate(`/todos/${todo.id}/edit`)}
                >
                  Edit
                </button>
                <button
                  className="bg-red-500 text-white px-4 py-1 rounded hover:bg-red-600"
                  onClick={() => handleDelete(todo)}
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

export default TodosPage;
