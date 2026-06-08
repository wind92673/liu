<template>
  <div class="page">
    <el-card>
      <template #header>
        <div class="card-header">
          <div class="filters">
            <el-input v-model="query.deviceIdFuzzy" placeholder="设备ID" class="w200" clearable />
            <el-select v-model="query.online" placeholder="在线状态" class="w140" clearable>
              <el-option label="在线" :value="0" />
              <el-option label="离线" :value="1" />
            </el-select>
            <el-select v-model="query.groupId" placeholder="分组" class="w160" clearable>
              <el-option v-for="g in groupOptions" :key="g.groupId" :label="g.groupName" :value="g.groupId" />
            </el-select>
            <el-button type="primary" @click="load">查询</el-button>
            <el-button @click="reset">重置</el-button>
          </div>
          <el-button type="primary" @click="openCreate">新建设备</el-button>
        </div>
      </template>

      <el-table :data="rows" border>
        <el-table-column prop="deviceId" label="设备ID" width="170" />
        <el-table-column label="分组" width="140">
          <template #default="{ row }">{{ groupName(groupMap[row.deviceId]) }}</template>
        </el-table-column>
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
        <el-table-column prop="lightThreshold" label="光照阈值" width="120" />
        <el-table-column prop="reportInterval" label="上报间隔(s)" width="120" />
        <el-table-column label="操作" width="220">
          <template #default="{ row }">
            <el-button size="small" @click="openEdit(row)">编辑</el-button>
            <el-button size="small" type="danger" @click="remove(row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <div class="pager">
        <el-pagination
          v-model:current-page="query.pageNo"
          v-model:page-size="query.pageSize"
          layout="total, prev, pager, next, sizes"
          :total="total"
          @current-change="load"
          @size-change="load"
        />
      </div>
    </el-card>

    <el-dialog v-model="editVisible" :title="isCreate ? '新建设备' : '编辑设备'" width="560px">
      <el-form :model="editForm" label-width="90px">
        <el-form-item label="设备ID">
          <el-input v-model="editForm.deviceId" :disabled="!isCreate" />
        </el-form-item>
        <el-form-item label="分组">
          <el-select v-model="editForm.groupId" class="w200" clearable>
            <el-option v-for="g in groupOptions" :key="g.groupId" :label="g.groupName" :value="g.groupId" />
          </el-select>
        </el-form-item>
        <el-form-item label="运行模式">
          <el-select v-model="editForm.systemMode" class="w200">
            <el-option label="自动" :value="0" />
            <el-option label="手动" :value="1" />
          </el-select>
        </el-form-item>
        <el-form-item label="光照阈值">
          <el-input-number v-model="editForm.lightThreshold" :min="0" :max="2000" />
        </el-form-item>
        <el-form-item label="白天开始">
          <el-input-number v-model="editForm.dayStart" :min="0" :max="23" />
        </el-form-item>
        <el-form-item label="夜晚开始">
          <el-input-number v-model="editForm.dayEnd" :min="0" :max="23" />
        </el-form-item>
        <el-form-item label="在线状态">
          <el-tag :type="Number(editForm.online) === 0 ? 'success' : 'info'">
            {{ Number(editForm.online) === 0 ? '在线' : '离线' }}
          </el-tag>
          <span class="text-gray ml-2">（由系统自动更新）</span>
        </el-form-item>
        <el-form-item label="上报间隔(s)">
          <el-input-number v-model="editForm.reportInterval" :min="1" :max="3600" />
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
import { useAdminDevices } from './index.js'
const { query, rows, total, groupOptions, groupMap, brightnessMap, load, reset, groupName, editVisible, editLoading, isCreate, editForm, openCreate, openEdit, save, remove } = useAdminDevices()
</script>
<style scoped src="./index.css"></style>