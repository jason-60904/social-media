<template>
  <div>
    <h2>發文</h2>
    <input v-model="content" placeholder="內容" />
    <button @click="createPost">發文</button>
  </div>
</template>

<script setup>
import { ref } from "vue";
import api from "./api";

const content = ref("");

const createPost = async () => {
  await api.post("/api/posts", {
    content: content.value,
  });
  alert("發文成功");
};
</script>