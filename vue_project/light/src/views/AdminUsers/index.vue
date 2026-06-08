<template>
  <div class="page">
    <el-card>
      <template #header>
        <div class="card-header">
          <div class="filters">
            <el-input v-model="query.nickNameFuzzy" placeholder="昵称" class="w160" clearable />
            <el-input v-model="query.phoneFuzzy" placeholder="手机号" class="w160" clearable />
            <el-date-picker
              v-model="query.createRange"
              type="daterange"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              value-format="YYYY-MM-DD"
            />
            <el-button type="primary" @click="load">查询</el-button>
            <el-button @click="reset">重置</el-button>
          </div>
          <el-button type="primary" @click="openCreate">新建用户</el-button>
        </div>
      </template>

      <el-table :data="rows" border>
        <el-table-column prop="userId" label="ID" width="120" />
        <el-table-column prop="nickName" label="昵称" />
        <el-table-column prop="phone" label="手机" width="140" />
        <el-table-column prop="right" label="权限" width="120">
          <template #default="{ row }">{{ rightLabel(row.right) }}</template>
        </el-table-column>
        <el-table-column prop="createTime" label="创建时间" width="180" />
        <el-table-column label="操作" width="260">
          <template #default="{ row }">
            <el-button size="small" @click="openEdit(row)">编辑</el-button>
            <el-button size="small" @click="openRight(row)">调整权限</el-button>
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

    <el-dialog v-model="createVisible" title="新建用户" width="460px">
      <el-form :model="createForm" label-width="70px">
        <el-form-item label="昵称">
          <el-input v-model="createForm.nickName" />
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="createForm.phone" />
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="createForm.password" show-password />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="createVisible = false">取消</el-button>
        <el-button type="primary" :loading="createLoading" @click="createUser">确定</el-button>
      </template>
    </el-dialog>

    <el-dialog v-model="editVisible" title="编辑用户" width="680px">
      <el-form :model="editForm" label-width="70px">
        <el-form-item label="昵称">
          <el-input v-model="editForm.nickName" />
        </el-form-item>
      </el-form>

      <template v-if="editUserRight !== 1">
        <el-divider />
        <div class="bind-header">
          <div>绑定设备</div>
          <div class="bind-actions">
            <el-select v-model="bindDeviceId" placeholder="选择设备" class="w200" clearable>
              <el-option v-for="d in deviceOptions" :key="d.deviceId" :label="d.deviceId" :value="d.deviceId" />
            </el-select>
            <el-button type="primary" :disabled="!bindDeviceId" @click="addBind">绑定</el-button>
          </div>
        </div>
        <el-table :data="bindRows" border height="260">
          <el-table-column prop="deviceId" label="设备ID" />
          <el-table-column prop="createTime" label="绑定时间" width="180" />
          <el-table-column label="操作" width="120">
            <template #default="{ row }">
              <el-button size="small" type="danger" @click="removeBind(row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </template>

      <template #footer>
        <el-button @click="editVisible = false">关闭</el-button>
        <el-button type="primary" :loading="editLoading" @click="saveEdit">保存</el-button>
      </template>
    </el-dialog>

    <el-dialog v-model="rightVisible" title="调整权限" width="420px">
      <el-form :model="rightForm" label-width="70px">
        <el-form-item label="权限">
          <el-select v-model="rightForm.right" class="w200">
            <el-option label="普通用户" value="USER" />
            <el-option label="运维人员" value="DEVOPS" />
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="rightVisible = false">取消</el-button>
        <el-button type="primary" :loading="rightLoading" @click="saveRight">确定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { useAdminUsers } from './index.js'
const { query, rows, total, load, reset, rightLabel, createVisible, createLoading, createForm, openCreate, createUser, editVisible, editLoading, editForm, editUserRight, openEdit, saveEdit, deviceOptions, bindRows, bindDeviceId, addBind, removeBind, rightVisible, rightLoading, rightForm, openRight, saveRight, remove } = useAdminUsers()
</script>
<style scoped src="./index.css"></style>