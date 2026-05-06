<template>

  <nav style="padding: 10px; border-bottom: 1px solid #ccc;">

    <!-- 未登入 -->
    <router-link v-if="!isLogin" to="/">
      登入
    </router-link>

    <!-- 已登入 -->
    <span
        v-else
        @click="logout"
        style="cursor: pointer; color: blue;"
    >
      登出
    </span>

    |

    <router-link to="/home">
      首頁
    </router-link>

  </nav>

  <router-view />

</template>

<script setup>

import { computed } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();


//  即時檢查 token
const isLogin = computed(() => {

  return !!localStorage.getItem("token");
});


//  登出
const logout = () => {

  localStorage.removeItem("token");

  alert("已登出");

  router.push("/");
};

</script>