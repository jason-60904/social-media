import { createRouter, createWebHistory } from "vue-router";

import Login from "./Login.vue";
import Home from "./Home.vue"; // ⭐ 新的首頁

const routes = [
    { path: "/", component: Login },
    { path: "/home", component: Home }, // ⭐ 社群首頁
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;