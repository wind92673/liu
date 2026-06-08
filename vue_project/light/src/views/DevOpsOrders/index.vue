<template>
  <div class="page">
    <el-card>
      <template #header>
        <div class="card-header">
          <div class="filters">
            <el-input v-model="query.deviceIdFuzzy" placeholder="设备ID" class="w200" clearable />
            <el-select v-model="query.state" placeholder="状态" class="w140" clearable>
              <el-option label="未处理" :value="0" />
              <el-option label="已处理" :value="1" />
            </el-select>
            <el-button type="primary" @click="load">查询</el-button>
            <el-button @click="reset">重置</el-button>
          </div>
        </div>
      </template>

      <el-table :data="rows" border>
        <el-table-column prop="id" label="工单ID" width="90" />
        <el-table-column prop="deviceId" label="设备ID" width="160" />
        <el-table-column prop="reportInfo" label="告警信息" />
        <el-table-column prop="reportTime" label="告警时间" width="180" />
        <el-table-column label="状态" width="100">
          <template #default="{ row }">
            <el-tag :type="Number(row.state) === 0 ? 'danger' : 'success'">
              {{ Number(row.state) === 0 ? '未处理' : '已处理' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="userId" label="处理人" width="120" />
        <el-table-column prop="repairTime" label="处理时间" width="180" />
        <el-table-column label="操作" width="200">
          <template #default="{ row }">
            <el-button v-if="Number(row.state) === 0" size="small" type="primary" @click="openHandle(row)">
              处理
            </el-button>
            <el-button v-else size="small" type="success" @click="openResult(row.id)">
              查看处理结果
            </el-button>
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

    <el-dialog v-model="handleVisible" title="处理工单" width="520px">
      <el-form :model="handleForm" label-width="70px">
        <el-form-item label="备注">
          <el-input v-model="handleForm.info" type="textarea" :rows="4" placeholder="请输入处理备注" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="handleVisible = false">取消</el-button>
        <el-button type="primary" :loading="handleLoading" @click="handle">确定</el-button>
      </template>
    </el-dialog>

    <el-dialog v-model="resultVisible" title="处理结果" width="520px">
      <div class="result-content">
        <div class="result-label">处理信息：</div>
        <div class="result-text">{{ resultInfo }}</div>
      </div>
      <template #footer>
        <el-button @click="resultVisible = false">关闭</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { useDevOpsOrders } from './index.js'
const { query, rows, total, load, reset, handleVisible, handleLoading, handleForm, openHandle, handle, resultVisible, resultLoading, resultInfo, openResult } = useDevOpsOrders()
</script>
<style scoped src="./index.css"></style>