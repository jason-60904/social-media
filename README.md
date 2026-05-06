# Social Media System

簡易社群媒體平台，使用 Vue.js + Spring Boot + MySQL 建立。

---

# 專案介紹

本專案為前後端分離的社群媒體系統，使用 JWT 進行身份驗證，並實作貼文、留言、熱門貼文排序等功能。

前端使用 Vue.js 建立 Feed 社群頁面，後端使用 Spring Boot 提供 RESTful API。

---

# 功能介紹

## 使用者功能

- 手機號碼註冊
- JWT 登入驗證
- 註冊後自動登入
- 登出功能

---

## 貼文功能

- 新增貼文
- 顯示最新貼文
- 顯示熱門貼文
- 編輯貼文
- 刪除貼文
- Enter 快捷發文

---

## 留言功能

- 新增留言
- 顯示留言列表
- Enter 快捷留言

---

## Feed 功能

- 社群首頁 Feed
- 最新 / 熱門貼文切換
- 即時刷新留言
- JWT 自動帶入 userId
- 留言局部刷新避免 Feed 跳動

---

# 技術架構

## Frontend

- Vue.js
- Vue Router
- Axios

---

## Backend

- Spring Boot
- Spring Security
- JWT Authentication
- JPA (Hibernate)
- RESTful API

---

## Database

- MySQL
- Stored Procedure

---

# 安全機制

- JWT 身份驗證
- XSS 防護（HtmlEscape）
- Validation 驗證
- Transaction 管理
- 不信任前端 userId（從 JWT 取得）

---

# 系統架構

Frontend (Vue.js)

↓

RESTful API

↓

Spring Boot

↓

MySQL

---

# API 範例

## Auth

### 註冊

POST /api/auth/register

### 登入

POST /api/auth/login

---

## Posts

### 取得全部貼文

GET /api/posts

### 熱門貼文

GET /api/posts/hot

### 發文

POST /api/posts

### 修改貼文

PUT /api/posts/{postId}

### 刪除貼文

DELETE /api/posts/{postId}

---

## Comments

### 新增留言

POST /api/comments

### 查詢貼文留言

GET /api/comments/post/{postId}

---

# 資料庫

DB 資料夾內包含：

- schema.sql
- data.sql

data.sql 可直接初始化測試資料與測試帳號，也可以透過下面的測試帳號於前端登入。

---

# 測試帳號

系統提供測試資料，可直接登入：

| phone | password |
|---|---|
| 0912345678 | 123456 |
| 11111111111 | 123456 |
| 0123456789 | 123456 |

---

# 專案特色

- 前後端分離架構
- JWT 驗證
- Feed 社群流
- 熱門貼文排序
- 留言系統
- Stored Procedure 實作
- RESTful API 設計
- Vue Router 單頁應用
- Axios JWT 自動攜帶
- 社群 Feed UX 設計
- Enter 快捷發文 / 留言
- JWT 自動控制 userId
- 熱門貼文使用 Stored Procedure 計算

---

# 執行方式

## Backend

```bash
mvn spring-boot:run
```
## Frontend
```bash
cd social-media-frontend

npm install

npm run dev
```