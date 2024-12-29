import axios from 'axios';

class API {
  static baseURL = 'http://localhost:8080';

  static async login(credentials) {
    const res = await axios.post(`${this.baseURL}/api/auth/login`, credentials);
    const { data: token } = res.data;
    return token;
  }

  static async register(data) {
    const res = await axios.post(`${this.baseURL}/api/auth/register`, data);
    const { data: token } = res.data;
    return token;
  }

  static async me(token) {
    const res = await axios.get(`${this.baseURL}/api/auth/me`, {
      headers: { Authorization: `Bearer ${token}` }
    });

    return res.data;
  }

  static async getTodos() {
    const token = localStorage.getItem('token');
    const res = await axios.get(`${this.baseURL}/api/todos`, {
      headers: { Authorization: `Bearer ${token}` }
    });

    const { data } = res.data;
    return data;
  }

  static async getTodo(id) {
    const token = localStorage.getItem('token');
    const res = await axios.get(`${this.baseURL}/api/todos/${id}`, {
      headers: { Authorization: `Bearer ${token}` }
    });

    const { data } = res.data;
    return data;
  }

  static async createTodo(todo, categoryId) {
    const token = localStorage.getItem('token');

    const res = await axios.post(
      `${this.baseURL}/api/todos?categoryId=${categoryId}`,
      todo,
      { headers: { Authorization: `Bearer ${token}` } }
    );

    const { data } = res.data;
    return data;
  }

  static async updateTodo(id, todo) {
    const token = localStorage.getItem('token');

    const res = await axios.put(`${this.baseURL}/api/todos/${id}`, todo, {
      headers: { Authorization: `Bearer ${token}` }
    });

    const { data } = res.data;
    return data;
  }

  static async deleteTodo(id) {
    const token = localStorage.getItem('token');

    const res = await axios.delete(`${this.baseURL}/api/todos/${id}`, {
      headers: { Authorization: `Bearer ${token}` }
    });

    const { data } = res.data;
    return data;
  }

  static async getTodoStats() {
    const token = localStorage.getItem('token');
    const res = await axios.get(`${this.baseURL}/api/stats`, {
      headers: { Authorization: `Bearer ${token}` }
    });

    const { data } = res.data;
    return data;
  }

  static async getCategories() {
    const token = localStorage.getItem('token');
    const res = await axios.get(`${this.baseURL}/api/categories`, {
      headers: { Authorization: `Bearer ${token}` }
    });

    const { data } = res.data;
    return data;
  }

  static async getCategory(id) {
    const token = localStorage.getItem('token');
    const res = await axios.get(`${this.baseURL}/api/categories/${id}`, {
      headers: { Authorization: `Bearer ${token}` }
    });

    const { data } = res.data;
    return data;
  }

  static async createCategory(category) {
    const token = localStorage.getItem('token');

    const res = await axios.post(`${this.baseURL}/api/categories`, category, {
      headers: { Authorization: `Bearer ${token}` }
    });

    const { data } = res.data;
    return data;
  }

  static async updateCategory(id, category) {
    const token = localStorage.getItem('token');

    const res = await axios.put(
      `${this.baseURL}/api/categories/${id}`,
      category,
      {
        headers: { Authorization: `Bearer ${token}` }
      }
    );

    const { data } = res.data;
    return data;
  }

  static async deleteCategory(id) {
    const token = localStorage.getItem('token');

    const res = await axios.delete(`${this.baseURL}/api/categories/${id}`, {
      headers: { Authorization: `Bearer ${token}` }
    });

    const { data } = res.data;
    return data;
  }
}

export default API;
