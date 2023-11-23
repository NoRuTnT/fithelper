import { defineStore } from 'pinia';
const b64_to_utf8 = function ( str ) {
  return decodeURIComponent(escape(window.atob( str )));
}

export const useAuthStore = defineStore('auth', {
  state: () => {
    return {
      token: sessionStorage.getItem('access-token') || null,
      userId: null,
      role: null,
      email: null,
      cash: null,
      totalcash: null,
    }
  },
  actions: {

    setToken(newToken) {
      this.token = newToken;
      sessionStorage.setItem('access-token', newToken);
    },
    clearToken() {
      this.token = null;
      sessionStorage.removeItem('access-token');
    },
    updateUserIdFromToken() {
      if (this.token) {
        const parts = this.token.split('.');
        if (parts.length === 3) {
          const payload = parts[1];
          const decodedPayload = b64_to_utf8(payload);
          this.userId = JSON.parse(decodedPayload).userId || null;
        }
      } else {
        this.userId = null;
      }
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
    },
    updateRoleFromToken() {
      if (this.token) {
        const parts = this.token.split('.');
        if (parts.length === 3) {
          const payload = parts[1];
          const decodedPayload = b64_to_utf8(payload);
          this.role = JSON.parse(decodedPayload).role[0] || null;
        }
      } else {
        this.role = null;
      }
    },
    updatecashFromToken() {
      console.log("욥")
      if (this.token) {
        const parts = this.token.split('.');
        if (parts.length === 3) {
          const payload = parts[1];
          const decodedPayload = b64_to_utf8(payload);
          console.log(JSON.parse(decodedPayload).cash)
          this.cash = JSON.parse(decodedPayload).cash;
        }
      } else {
        this.cash = null;
      }
    },
    updateTotalcashFromToken() {
      if (this.token) {
        const parts = this.token.split('.');
        if (parts.length === 3) {
          const payload = parts[1];
          const decodedPayload = b64_to_utf8(payload);
          this.totalcash = JSON.parse(decodedPayload).totalcash || null;
        }
      } else {
        this.totalcash = null;
      }
    }
  },

});