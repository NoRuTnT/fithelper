import axios from 'axios';
import router from '@/router';
import { useAuthStore } from '@/stores/auth'; // Auth 스토어 경로


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

// 응답 인터셉터 추가
http.interceptors.response.use(
  response => {
    // 성공적인 응답 처리
    return response;
  },
  error => {
    const authStore = useAuthStore();
    // 오류 응답 처리
    if (error.response) {
      if (error.response.status === 401) {
        if (error.response.data === 'Unauthorized: Invalid Token') {
          alert('인증 오류: 로그인 시간이 만료되었습니다.');
          authStore.clearToken(); // 토큰 제거
          router.push('/login');
          return;
        } else {
          alert('인증 실패: 권한이 없습니다.');
          
          return Promise.reject(error);
        }
      }
    }
    return Promise.reject(error);
  }
);

export default http;