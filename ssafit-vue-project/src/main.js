import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'

const app = createApp(App)
import http from './api/http'; //  http 인스턴스를 임포트.

app.config.globalProperties.$http = http; // 전역 프로퍼티로 설정


app.use(createPinia())
app.use(router)

app.mount('#app')
