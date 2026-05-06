<template>
  <div style="max-width: 600px; margin: auto;">

    <h2>社群首頁</h2>

    <!-- ⭐ 切換排序 -->
    <div style="margin-bottom: 20px;">

      <button @click="setMode('latest')">
        最新貼文
      </button>

      <button @click="setMode('hot')" style="margin-left: 10px;">
        熱門貼文
      </button>

    </div>

    <!-- 發文 -->
    <div style="border: 1px solid #ccc; padding: 10px; margin-bottom: 20px;">

      <input
          v-model="content"
          placeholder="你在想什麼..."
          style="width: 100%;"
      />

      <button @click="createPost">
        發文
      </button>

    </div>

    <!-- 貼文列表 -->
    <div
        v-for="post in posts"
        :key="post.postId"
        style="border: 1px solid #ddd; padding: 10px; margin-bottom: 20px;"
    >

      <!-- 貼文 -->
      <p>{{ post.content }}</p>

      <small>userId: {{ post.userId }}</small>

      <!-- ⭐ 熱門模式顯示留言數 -->
      <div v-if="mode === 'hot'" style="margin-top: 5px;">
        🔥 留言數：{{ post.commentCount }}
      </div>

      <hr />

      <!-- ⭐ 留言列表 -->
      <div
          v-for="comment in commentsMap[post.postId]"
          :key="comment.commentId"
          style="margin-top: 5px; padding-left: 10px;"
      >
        💬 {{ comment.content }}
      </div>

      <!-- ⭐ 留言輸入 -->
      <div style="margin-top: 10px;">

        <input
            v-model="commentMap[post.postId]"
            placeholder="留言..."
        />

        <button @click="createComment(post.postId)">
          送出
        </button>

      </div>

    </div>

  </div>
</template>

<script setup>

import { ref, onMounted } from "vue";
import api from "./api";

const posts = ref([]);

// ⭐ 排序模式
const mode = ref("latest");

const content = ref("");

// ⭐ 每篇貼文的留言輸入框
const commentMap = ref({});

// ⭐ 每篇貼文的留言列表
const commentsMap = ref({});


// ⭐ 切換模式
const setMode = async (newMode) => {

  mode.value = newMode;

  await fetchPosts();
};


// ⭐ 取得留言
const fetchComments = async (postId) => {

  const res = await api.get(`/api/comments/post/${postId}`);

  commentsMap.value[postId] = res.data;
};


// ⭐ 取得貼文
const fetchPosts = async () => {

  let res;

  // ⭐ 最新貼文
  if (mode.value === "latest") {

    res = await api.get("/api/posts");

  } else {

    // ⭐ 熱門貼文
    res = await api.get("/api/posts/hot");
  }

  posts.value = res.data;
  console.log(posts.value);
  // ⭐ 每篇貼文載留言
  for (const post of posts.value) {

    await fetchComments(post.postId);
  }
};


// ⭐ 發文
const createPost = async () => {

  if (!content.value) return;

  await api.post("/api/posts", {
    content: content.value,
  });

  content.value = "";

  await fetchPosts();
};


// ⭐ 留言
const createComment = async (postId) => {

  const text = commentMap.value[postId];

  if (!text) return;

  await api.post("/api/comments", {
    postId: postId,
    content: text,
  });

  // ⭐ 清空輸入框
  commentMap.value[postId] = "";

  // ⭐ 重新載入留言
  await fetchComments(postId);

  // ⭐ 熱門模式下同步刷新留言數
  const createComment = async (postId) => {

    const text = commentMap.value[postId];

    if (!text) return;

    await api.post("/api/comments", {
      postId: postId,
      content: text,
    });

    // ⭐ 清空輸入框
    commentMap.value[postId] = "";

    // ⭐ 只刷新留言列表
    await fetchComments(postId);
  };
};


// ⭐ 頁面載入
onMounted(fetchPosts);

</script>