import axios from 'axios';

const baseURL = 'http://localhost:8080';

const http = axios.create({
  baseURL: baseURL,
  headers: {
    'Content-Type': 'application/json',
    
  }
});

http.interceptors.request.use(
  config => {
    const token = sessionStorage.getItem('access-token');
    if (token) {
      config.headers['Authorization'] = `Bearer ${token}`;
    }
    return config;
  },
  error => {
    return Promise.reject(error);
  }
);

export default http;