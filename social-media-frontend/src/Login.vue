<template>
  <div>
    <h2>登入</h2>

    <input v-model="phone" placeholder="phone" />

    <input
        v-model="password"
        placeholder="password"
        type="password"
    />

    <button @click="login">
      登入
    </button>

    <br /><br />

    <router-link to="/register">
      沒有帳號？前往註冊
    </router-link>

  </div>
</template>

<script setup>

import { ref } from "vue";
import { useRouter } from "vue-router";
import api from "./api";

const phone = ref("");
const password = ref("");

const router = useRouter();

const login = async () => {

  try {

    const res = await api.post("/api/auth/login", {

      phone: phone.value,
      password: password.value,
    });

    //  存 JWT
    localStorage.setItem("token", res.data.token);

    alert("登入成功");

    //  跳首頁
    router.push("/home");

  } catch (err) {

    console.error(err);

    alert("登入失敗");
  }
};

</script>