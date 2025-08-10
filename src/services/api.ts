import axios from 'axios';

// A URL base da sua API.
const api = axios.create({
  baseURL: 'http://localhost:8081/api',
});

export default api;
