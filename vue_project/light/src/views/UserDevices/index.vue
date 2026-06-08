<template>
  <div class="page">
    <el-card>
      <template #header>
        <div class="card-header">
          <div>亮度查询与调节</div>
          <el-button type="primary" :loading="loading" @click="load">刷新</el-button>
        </div>
      </template>

      <el-table :data="rows" border v-loading="loading">
        <el-table-column prop="deviceId" label="设备ID" width="180" />
        <el-table-column label="在线" width="90">
          <template #default="{ row }">
            <el-tag :type="Number(row.online) === 0 ? 'success' : 'info'">
              {{ Number(row.online) === 0 ? '在线' : '离线' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="模式" width="120">
          <template #default="{ row }">{{ Number(row.systemMode) === 0 ? '自动' : '手动' }}</template>
        </el-table-column>
        <el-table-column label="当前亮度" width="120">
          <template #default="{ row }">{{ brightnessMap[row.deviceId] ?? '-' }}</template>
        </el-table-column>
        <el-table-column label="环境亮度" width="120">
          <template #default="{ row }">{{ ambientLightMap[row.deviceId] ?? '-' }}</template>
        </el-table-column>
        <el-table-column label="操作" width="140">
          <template #default="{ row }">
            <el-button size="small" :disabled="Number(row.online) !== 0" @click="openEdit(row)">编辑</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-dialog v-model="editVisible" title="调整设备" width="520px">
      <el-form :model="editForm" label-width="70px">
        <el-form-item label="设备">
          <el-input v-model="editForm.deviceId" disabled />
        </el-form-item>
        <el-form-item label="模式">
          <el-radio-group v-model="editForm.systemMode">
            <el-radio :value="0">自动</el-radio>
            <el-radio :value="1">手动</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="亮度">
          <el-slider v-model="editForm.bright" :min="0" :max="100" :disabled="editForm.systemMode === 0" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="editVisible = false">取消</el-button>
        <el-button type="primary" :loading="editLoading" @click="save">确定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { useUserDevices } from './index.js'
const { rows, brightnessMap, ambientLightMap, loading, load, editVisible, editLoading, editForm, openEdit, save } = useUserDevices()
</script>
<style scoped src="./index.css"></style>