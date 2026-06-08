<template>
  <div class="page">
    <el-card>
      <template #header>
        <div class="filters">
          <el-input v-model="query.deviceIdFuzzy" placeholder="设备ID" class="w200" clearable />
          <el-select v-model="query.online" placeholder="在线状态" class="w140" clearable>
            <el-option label="在线" :value="0" />
            <el-option label="离线" :value="1" />
          </el-select>
          <el-button type="primary" @click="load">查询</el-button>
          <el-button @click="reset">重置</el-button>
        </div>
      </template>

      <el-table :data="rows" border>
        <el-table-column prop="deviceId" label="设备ID" width="170" />
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
  </div>
</template>

<script setup>
import { useDevOpsMonitor } from './index.js'
const { query, rows, total, brightnessMap, load, reset } = useDevOpsMonitor()
</script>
<style scoped src="./index.css"></style>