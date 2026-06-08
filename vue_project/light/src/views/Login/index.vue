<template>
  <div class="login-page">
    <div class="login-bg"></div>
    <div class="login-container">
      <div class="login-card">
        <div class="login-header">
          <div class="logo">
            <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <path d="M13 2L3 14h9l-1 8 10-12h-9l1-8z"/>
            </svg>
          </div>
          <div class="title">办公区照明控制系统</div>
          <div class="subtitle">智能节能 · 绿色办公</div>
        </div>

        <div class="login-form">
          <el-form :model="form" label-width="80px">
            <el-form-item label="手机号">
              <el-input 
                v-model="form.phone" 
                placeholder="请输入手机号"
                type="tel"
                :maxlength="11"
              />
            </el-form-item>
            <el-form-item label="密码">
              <el-input 
                v-model="form.password" 
                placeholder="请输入密码"
                type="password"
              />
            </el-form-item>
            <el-form-item>
              <el-button 
                type="primary" 
                class="login-btn" 
                :loading="loading"
                @click="onSubmit"
              >
                登 录
              </el-button>
            </el-form-item>
          </el-form>
        </div>

        <div class="login-footer">
          <span class="register-link" @click="toRegister">立即注册</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useLogin } from './index.js'
const { form, loading, onSubmit, toRegister } = useLogin()
</script>
<style scoped>
.login-page {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  position: relative;
  overflow: hidden;
}

.login-bg {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 50%, #f093fb 100%);
}

.login-bg::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: url("data:image/svg+xml,%3Csvg width='60' height='60' viewBox='0 0 60 60' xmlns='http://www.w3.org/2000/svg'%3E%3Cg fill='none' fill-rule='evenodd'%3E%3Cg fill='%23ffffff' fill-opacity='0.05'%3E%3Cpath d='M36 34v-4h-2v4h-4v2h4v4h2v-4h4v-2h-4zm0-30V0h-2v4h-4v2h4v4h2V6h4V4h-4zM6 34v-4H4v4H0v2h4v4h2v-4h4v-2H6zM6 4V0H4v4H0v2h4v4h2V6h4V4H6z'/%3E%3C/g%3E%3C/g%3E%3C/svg%3E");
}

.login-container {
  position: relative;
  z-index: 10;
  width: 100%;
  max-width: 400px;
  padding: 20px;
}

.login-card {
  background: rgba(255, 255, 255, 0.95);
  backdrop-filter: blur(10px);
  border-radius: 20px;
  padding: 40px;
  box-shadow: 0 20px 60px rgba(0, 0, 0, 0.2);
}

.login-header {
  text-align: center;
  margin-bottom: 32px;
}

.logo {
  width: 64px;
  height: 64px;
  margin: 0 auto 16px;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  border-radius: 16px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #fff;
}

.logo svg {
  width: 32px;
  height: 32px;
}

.title {
  font-size: 24px;
  font-weight: 700;
  color: #1f1f1f;
  margin-bottom: 8px;
}

.subtitle {
  font-size: 14px;
  color: #666;
}

.login-form {
  margin-bottom: 24px;
}

.login-btn {
  width: 100%;
  height: 44px;
  font-size: 16px;
  font-weight: 600;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  border: none;
  border-radius: 8px;
}

.login-btn:hover {
  background: linear-gradient(135deg, #5a6fd6 0%, #6a4190 100%);
}

.login-footer {
  text-align: center;
}

.register-link {
  color: #667eea;
  font-size: 14px;
  cursor: pointer;
  transition: color var(--transition-fast);
}

.register-link:hover {
  color: #5a6fd6;
  text-decoration: underline;
}

.el-form-item__label {
  font-weight: 500;
  color: #333;
}

.el-input__wrapper {
  border-radius: 8px;
  height: 44px;
}

.el-input__inner {
  height: 44px;
}
</style>