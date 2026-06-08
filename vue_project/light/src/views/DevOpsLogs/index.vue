<template>
  <div class="page">
    <el-card>
      <template #header>
        <div class="filters">
          <el-input v-model="query.deviceIdFuzzy" placeholder="设备ID" class="w200" clearable />
          <el-input v-model="query.eventTypeFuzzy" placeholder="操作类型" class="w160" clearable />
          <el-date-picker
            v-model="query.timeRange"
            type="daterange"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            value-format="YYYY-MM-DD"
          />
          <el-button type="primary" @click="load">查询</el-button>
          <el-button @click="reset">重置</el-button>
        </div>
      </template>

      <el-table :data="rows" border>
        <el-table-column prop="id" label="ID" width="90" />
        <el-table-column prop="eventType" label="类型" width="140" />
        <el-table-column prop="deviceId" label="设备ID" width="170" />
        <el-table-column prop="command" label="指令" />
        <el-table-column prop="timestamp" label="时间" width="180" />
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
import { useDevOpsLogs } from './index.js'
const { query, rows, total, load, reset } = useDevOpsLogs()
</script>
<style scoped src="./index.css"></style>