<template>
  <div class="wrap">
    <el-card class="card">
      <div class="title">注册</div>
      <el-form :model="form" label-width="70px" @keyup.enter="onSubmit">
        <el-form-item label="昵称">
          <el-input v-model="form.nickName" placeholder="请输入昵称" />
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="form.phone" placeholder="请输入手机号" />
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="form.password" placeholder="请输入密码" show-password />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" :loading="loading" @click="onSubmit">注册</el-button>
          <el-button @click="toLogin">返回登录</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { useRegister } from './index.js'
const { form, loading, onSubmit, toLogin } = useRegister()
</script>
<style scoped src="./index.css"></style>