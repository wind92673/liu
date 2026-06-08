<template>
  <div class="login-page">
    <div class="bg-overlay"></div>
    <div class="bg-glow glow-1"></div>
    <div class="bg-glow glow-2"></div>

    <el-card class="login-card" shadow="never">
      <div class="brand-side">
        <div class="logo">MR</div>
        <h2>会议室预约与管理系统</h2>
        <p>高效预约 · 智能管理 · 数据联动</p>
      </div>

      <div class="form-side">
        <h3>{{ isRegister ? '用户注册' : '系统登录' }}</h3>
        <el-form :model="form" label-position="top" class="login-form">
          <el-form-item label="账号">
            <el-input v-model="form.username" placeholder="请输入账号">
              <i slot="prefix" class="el-input__icon el-icon-user"></i>
            </el-input>
          </el-form-item>

          <el-form-item label="密码">
            <el-input show-password v-model="form.password" placeholder="请输入密码">
              <i slot="prefix" class="el-input__icon el-icon-lock"></i>
            </el-input>
          </el-form-item>

          <el-form-item v-if="isRegister" label="姓名">
            <el-input v-model="form.realName" placeholder="请输入姓名">
              <i slot="prefix" class="el-input__icon el-icon-edit"></i>
            </el-input>
          </el-form-item>

          <el-form-item v-if="isRegister" label="角色">
            <el-select v-model="form.role" style="width:100%" placeholder="请选择角色">
              <el-option label="普通员工" value="employee" />
              <el-option label="部门管理员" value="dept_admin" />
            </el-select>
          </el-form-item>

          <el-button class="submit-btn" type="primary" @click="submit">
            {{ isRegister ? '注册' : '登录' }}
          </el-button>
        </el-form>

        <div class="switch" @click="isRegister = !isRegister">
          {{ isRegister ? '已有账号？去登录' : '没有账号？去注册' }}
        </div>
      </div>
    </el-card>
  </div>
</template>

<script>
import { login, register } from '../api';

export default {
  data() {
    return {
      isRegister: false,
      form: { username: '', password: '', realName: '', role: 'employee' }
    };
  },
  methods: {
    async submit() {
      const api = this.isRegister ? register : login;
      const { data } = await api(this.form);
      if (data.code !== 200) return this.$message.error(data.message);
      if (this.isRegister) return this.$message.success('注册成功，请登录');
      localStorage.setItem('meeting_user', JSON.stringify(data.data));
      this.$router.push('/dashboard');
    }
  }
};
</script>

<style scoped>
.login-page {
  position: relative;
  min-height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  background: url('/img/back.jpg') center/cover no-repeat;
  overflow: hidden;
}

.bg-overlay {
  position: absolute;
  inset: 0;
  background: linear-gradient(120deg, rgba(5, 31, 58, 0.78), rgba(25, 64, 102, 0.68));
}

.bg-glow {
  position: absolute;
  border-radius: 50%;
  filter: blur(8px);
}

.glow-1 {
  width: 420px;
  height: 420px;
  left: -90px;
  top: -70px;
  background: rgba(93, 186, 255, 0.25);
}

.glow-2 {
  width: 360px;
  height: 360px;
  right: -70px;
  bottom: -90px;
  background: rgba(110, 214, 237, 0.22);
}

.login-card {
  position: relative;
  z-index: 2;
  width: 900px;
  max-width: 92vw;
  border-radius: 18px;
  border: 1px solid rgba(255, 255, 255, 0.35);
  background: rgba(255, 255, 255, 0.9);
}

::v-deep .el-card__body {
  padding: 0;
  display: grid;
  grid-template-columns: 1.1fr 1fr;
  min-height: 520px;
}

.brand-side {
  color: #fff;
  padding: 46px 42px;
  background: linear-gradient(145deg, #0f2f4f, #1f4e78);
  border-radius: 18px 0 0 18px;
}

.logo {
  width: 58px;
  height: 58px;
  border-radius: 14px;
  background: rgba(255, 255, 255, 0.22);
  border: 1px solid rgba(255, 255, 255, 0.34);
  display: flex;
  align-items: center;
  justify-content: center;
  font-weight: 700;
  letter-spacing: 1px;
  margin-bottom: 18px;
}

.brand-side h2 {
  margin: 0;
  font-size: 27px;
  line-height: 1.35;
}

.brand-side p {
  margin-top: 12px;
  color: rgba(255, 255, 255, 0.85);
  font-size: 14px;
}

.form-side {
  padding: 46px 44px 28px;
}

.form-side h3 {
  margin: 0 0 18px;
  font-size: 28px;
  color: #173454;
}

.login-form {
  margin-top: 8px;
}

.submit-btn {
  width: 100%;
  height: 44px;
  margin-top: 6px;
  border: none;
  border-radius: 10px;
  background: linear-gradient(135deg, #2f7fd6, #52a8ff);
}

.switch {
  text-align: center;
  margin-top: 14px;
  color: #2f7fd6;
  cursor: pointer;
  font-size: 14px;
}

.switch:hover {
  color: #1f5f9d;
}

@media (max-width: 920px) {
  ::v-deep .el-card__body {
    grid-template-columns: 1fr;
    min-height: auto;
  }

  .brand-side {
    border-radius: 18px 18px 0 0;
    padding: 26px;
  }

  .form-side {
    padding: 26px;
  }

  .form-side h3 {
    font-size: 24px;
  }
}
</style>
