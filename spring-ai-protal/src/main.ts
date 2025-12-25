import './assets/main.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'

// 1. 初始化 user 到 localStorage（仅当不存在时）
function initUserInLocalStorage() {
    const existingUser = localStorage.getItem('userId');
    if (!existingUser) {
        // 生成随机字符串（简单版）
        const randomUser = 'user_' + Math.random().toString(36).slice(2, 9);
        // 或使用更健壮的 UUID（需引入库，或用 crypto）
        // const randomUser = crypto.randomUUID(); // 浏览器原生（安全上下文）
        localStorage.setItem('userId', randomUser);
        console.log('✅ 初始化 user:', randomUser);
    }
}

initUserInLocalStorage();

const app = createApp(App)

app.use(createPinia())
app.use(router)

app.mount('#app')
