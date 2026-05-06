<template>

  <div>

    <h2>註冊</h2>

    <input
        v-model="userName"
        placeholder="使用者名稱"
    />

    <br /><br />

    <input
        v-model="phone"
        placeholder="手機號碼"
    />

    <br /><br />

    <input
        v-model="password"
        type="password"
        placeholder="密碼"
    />

    <br /><br />

    <button @click="register">
      註冊
    </button>

  </div>

</template>

<script setup>

import { ref } from "vue";
import { useRouter } from "vue-router";
import api from "./api";

const router = useRouter();

const userName = ref("");
const phone = ref("");
const password = ref("");

const register = async () => {

  try {

    // ⭐ 先註冊
    await api.post("/api/auth/register", {

      userName: userName.value,
      phone: phone.value,
      password: password.value,
    });

    // ⭐ 再自動登入
    const loginRes = await api.post("/api/auth/login", {

      phone: phone.value,
      password: password.value,
    });

    // ⭐ 存 token
    localStorage.setItem("token", loginRes.data.token);

    alert("註冊成功");

    // ⭐ 跳首頁
    router.push("/home");

  } catch (err) {

    console.error(err);

    alert("註冊失敗");
  }
};

</script>