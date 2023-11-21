import { defineStore } from 'pinia';

export const useAuthStore = defineStore('auth', {
  state: () => ({
    token: sessionStorage.getItem('access-token') || null,
  }),
  actions: {
    setToken(newToken) {
      this.token = newToken;
      sessionStorage.setItem('access-token', newToken);
    },
    clearToken() {
      this.token = null;
      sessionStorage.removeItem('access-token');
    }
  }
});