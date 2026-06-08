<template>
  <div class="page">
    <el-card>
      <template #header>
        <div class="filters">
          <el-select v-model="deviceId" placeholder="选择设备" class="w200" clearable>
            <el-option v-for="d in deviceOptions" :key="d" :label="d" :value="d" />
          </el-select>
          <el-date-picker v-model="day" type="date" value-format="YYYY-MM-DD" placeholder="选择日期" />
          <el-button type="primary" :loading="loading" @click="load">查询</el-button>
        </div>
      </template>

      <div class="content">
        <div class="chart-container">
          <div class="chart-title">节电量趋势</div>
          <div ref="powerChartRef" class="chart"></div>
        </div>
        <div class="chart-container">
          <div class="chart-title">节碳量趋势</div>
          <div ref="carbonChartRef" class="chart"></div>
        </div>
        <el-table :data="rows" border>
          <el-table-column prop="currentHour" label="小时" width="90" />
          <el-table-column prop="power" label="节电量" />
          <el-table-column prop="carbon" label="节碳量" />
        </el-table>
      </div>
    </el-card>
  </div>
</template>

<script setup>
import { useUserEnergy } from './index.js'
const { deviceOptions, deviceId, day, loading, rows, powerChartRef, carbonChartRef, load } = useUserEnergy()
</script>
<style scoped src="./index.css"></style>