import { defineStore } from 'pinia';

export const useAuthStore = defineStore('auth', {
  state: () => ({
    token: sessionStorage.getItem('access-token') || null,
    email: null,
  }),
  actions: {
    setToken(newToken) {
      this.token = newToken;
      sessionStorage.setItem('access-token', newToken);
    },
    clearToken() {
      this.token = null;
      sessionStorage.removeItem('access-token');
    },
    updateEmailFromToken() {
        if (this.token) {
          const parts = this.token.split('.');
          if (parts.length === 3) {
            const payload = parts[1];
            const decodedPayload = b64_to_utf8(payload);
            this.email = JSON.parse(decodedPayload).email || null;
          }
        } else {
          this.email = null;
        }
      }
  }
});