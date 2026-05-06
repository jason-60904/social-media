<template>
  <div>
    <h2>留言</h2>
    <input v-model="postId" placeholder="postId" />
    <input v-model="content" placeholder="留言內容" />
    <button @click="createComment">留言</button>
  </div>
</template>

<script setup>
import { ref } from "vue";
import api from "./api";

const postId = ref("");
const content = ref("");

const createComment = async () => {
  await api.post("/api/comments", {
    postId: postId.value,
    content: content.value,
  });
  alert("留言成功");
};
</script>