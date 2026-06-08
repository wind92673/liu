<template>
  <div class="page">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>个人中心</span>
          <el-button @click="openEditModal">编辑</el-button>
        </div>
      </template>

      <div class="profile-info">
        <div class="info-item">
          <span class="label">昵称</span>
          <span class="value">{{ user?.nickName }}</span>
        </div>
        <div class="info-item">
          <span class="label">手机号</span>
          <span class="value">{{ user?.phone || '--' }}</span>
        </div>
        <div class="info-item">
          <span class="label">权限</span>
          <span class="value">{{ rightLabel }}</span>
        </div>
        <div class="info-item">
          <span class="label">创建时间</span>
          <span class="value">{{ user?.createTime || '--' }}</span>
        </div>
      </div>
    </el-card>

    <el-dialog v-model="editVisible" title="编辑资料" width="420px">
      <el-form :model="editForm" label-width="70px">
        <el-form-item label="昵称">
          <el-input v-model="editForm.nickName" placeholder="请输入昵称" />
        </el-form-item>
        <el-divider />
        <el-form-item label="原密码">
          <el-input v-model="editForm.oldPassword" type="password" placeholder="不修改密码请留空" />
        </el-form-item>
        <el-form-item label="新密码">
          <el-input v-model="editForm.newPassword" type="password" placeholder="不修改密码请留空" />
        </el-form-item>
        <el-form-item label="确认密码">
          <el-input v-model="editForm.confirmPassword" type="password" placeholder="不修改密码请留空" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="editVisible = false">取消</el-button>
        <el-button type="primary" :loading="loading" @click="saveProfile">确定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { useProfile } from './index.js'
const { user, rightLabel, editVisible, loading, editForm, openEditModal, saveProfile } = useProfile()
</script>
<style scoped>
.page {
  padding: 20px;
}
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 16px;
  font-weight: bold;
}
.profile-info {
  padding: 20px;
}
.info-item {
  display: flex;
  align-items: center;
  padding: 15px 0;
  border-bottom: 1px solid var(--border-light);
}
.info-item:last-child {
  border-bottom: none;
}
.label {
  width: 100px;
  font-weight: bold;
  color: var(--text-secondary);
}
.value {
  flex: 1;
  color: var(--text-primary);
}
.el-divider {
  margin: 16px 0;
}
</style>