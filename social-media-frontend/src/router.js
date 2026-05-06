import { createRouter, createWebHistory } from "vue-router";

import Login from "./Login.vue";
import Home from "./Home.vue"; // ⭐ 新的首頁
import Register from "./Register.vue";

const routes = [
    { path: "/", component: Login },
    { path: "/register", component: Register },
    { path: "/home", component: Home },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;