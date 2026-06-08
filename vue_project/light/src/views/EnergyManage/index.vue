<template>
  <div class="page">
    <el-card>
      <template #header>
        <div class="card-header">
          <div class="filters">
            <el-radio-group v-model="mode">
              <el-radio-button label="day">日</el-radio-button>
              <el-radio-button label="month">月</el-radio-button>
              <el-radio-button label="year">年</el-radio-button>
            </el-radio-group>
            <el-select v-model="deviceId" placeholder="设备（可选）" class="w200" clearable>
              <el-option v-for="d in deviceOptions" :key="d.deviceId" :label="d.deviceId" :value="d.deviceId" />
            </el-select>
            <el-date-picker
              v-if="mode === 'day'"
              v-model="day"
              type="date"
              value-format="YYYY-MM-DD"
              placeholder="选择日期"
            />
            <el-date-picker
              v-else-if="mode === 'month'"
              v-model="month"
              type="month"
              value-format="YYYY-MM"
              placeholder="选择月份"
            />
            <el-date-picker
              v-else
              v-model="year"
              type="year"
              value-format="YYYY"
              placeholder="选择年份"
            />
            <el-button type="primary" :loading="loading" @click="load">查询</el-button>
            <el-button :disabled="rows.length === 0" @click="exportExcel">导出节能统计日志</el-button>
          </div>
        </div>
      </template>

      <div class="content">
        <div class="charts">
          <div class="chart-item">
            <div class="chart-title">节电量趋势</div>
            <div ref="powerChartRef" class="chart"></div>
          </div>
          <div class="chart-item">
            <div class="chart-title">节碳量趋势</div>
            <div ref="carbonChartRef" class="chart"></div>
          </div>
        </div>
        <el-table :data="rows" border class="table">
          <el-table-column prop="label" :label="labelTitle" width="160" />
          <el-table-column prop="total_power" label="节电量" />
          <el-table-column prop="total_carbon" label="节碳量" />
        </el-table>
      </div>
    </el-card>
  </div>
</template>

<script setup>
import { useEnergyManage } from './index.js'
const { mode, deviceId, day, month, year, loading, deviceOptions, rows, powerChartRef, carbonChartRef, labelTitle, load, exportExcel } = useEnergyManage()
</script>
<style scoped src="./index.css"></style>