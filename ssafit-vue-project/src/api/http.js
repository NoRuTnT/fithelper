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
    // 로그인 및 회원가입 요청에 대한 경로를 배열로 정의합니다.
    const authRoutes = ['/login', '/signup'];

    // 현재 요청의 URL이 로그인 또는 회원가입 요청인지 확인합니다.
    const isAuthRoute = authRoutes.some(route => config.url.includes(route));

    // 로그인 또는 회원가입 요청이 아닌 경우에만 토큰을 헤더에 추가합니다.
    if (!isAuthRoute) {
      const token = sessionStorage.getItem('access-token');
      if (token) {
        config.headers['Authorization'] = `Bearer ${token}`;
      }
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