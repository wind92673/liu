<template>
  <div class="page">
    <el-card>
      <template #header>
        <div class="card-header">
          <div>分组管理</div>
          <el-button type="primary" @click="openCreate">新建分组</el-button>
        </div>
      </template>

      <el-table :data="rows" border>
        <el-table-column prop="groupId" label="分组ID" width="140" />
        <el-table-column prop="groupName" label="分组名称" />
        <el-table-column prop="createPeople" label="创建人" width="140" />
        <el-table-column prop="createTime" label="创建时间" width="180" />
        <el-table-column label="操作" width="200">
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

    <el-dialog v-model="editVisible" :title="isCreate ? '新建分组' : '编辑分组'" width="460px">
      <el-form :model="editForm" label-width="70px">
        <el-form-item label="分组ID">
          <el-input v-model="editForm.groupId" :disabled="!isCreate" />
        </el-form-item>
        <el-form-item label="名称">
          <el-input v-model="editForm.groupName" />
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
import { useAdminGroups } from './index.js'
const { query, rows, total, load, editVisible, editLoading, isCreate, editForm, openCreate, openEdit, save, remove } = useAdminGroups()
</script>
<style scoped src="./index.css"></style>