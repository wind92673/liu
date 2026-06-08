<template>
  <div>
    <el-card>
      <div slot="header" class="toolbar">
        <span>{{ title }}</span>
        <div>
          <el-input v-model="query.keyword" placeholder="请输入关键字搜索" style="width:220px;margin-right:10px" clearable @keyup.enter.native="loadData" />
          <el-button type="primary" v-if="!readOnly" @click="openDialog('add')">新增</el-button>
        </div>
      </div>

      <el-table :data="tableData" border>
        <el-table-column type="index" width="60" label="序号" />
        <el-table-column v-for="c in columns" :key="c" :prop="c" :label="columnLabel(c)" min-width="120">
          <template slot-scope="scope">
            <el-image
              v-if="isFileField(c) && scope.row[c]"
              :src="fileUrl(scope.row[c])"
              fit="cover"
              style="width:56px;height:56px;border-radius:6px"
              :preview-src-list="[fileUrl(scope.row[c])]"
            />
            <span v-else>{{ formatCell(scope.row[c], c) }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="320" v-if="!readOnly">
          <template slot-scope="scope">
            <el-button type="text" @click="openDialog('view', scope.row)">查看详情</el-button>
            <el-button type="text" @click="openDialog('edit', scope.row)">编辑</el-button>
            <el-button v-if="isApproveMode && scope.row.status === 'pending'" type="text" style="color:#67c23a" @click="openApprove(scope.row, 'approved')">通过</el-button>
            <el-button v-if="isApproveMode && scope.row.status === 'pending'" type="text" style="color:#e6a23c" @click="openApprove(scope.row, 'rejected')">驳回</el-button>
            <el-button type="text" style="color:#f56c6c" @click="remove(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <div class="pager-wrap">
        <div>共 {{ page.total }} 条，当前第 {{ page.current }} 页 / 共 {{ page.pages || 1 }} 页</div>
        <div class="pager-actions">
          <el-button size="mini" @click="jump(1)" :disabled="page.current===1">首页</el-button>
          <el-button size="mini" @click="jump(page.current-1)" :disabled="page.current===1">上一页</el-button>
          <el-button size="mini" @click="jump(page.current+1)" :disabled="page.current===page.pages || page.pages===0">下一页</el-button>
          <el-button size="mini" @click="jump(page.pages)" :disabled="page.current===page.pages || page.pages===0">末页</el-button>
          <el-select v-model="page.size" style="width:110px" @change="loadData">
            <el-option :value="10" label="10条/页" />
            <el-option :value="20" label="20条/页" />
            <el-option :value="50" label="50条/页" />
            <el-option :value="100" label="100条/页" />
          </el-select>
          <el-input-number v-model="gotoPage" :min="1" :max="page.pages || 1" size="mini" />
          <el-button size="mini" type="primary" @click="jump(gotoPage)">前往</el-button>
        </div>
      </div>
    </el-card>

    <el-dialog :title="dialogTitle" :visible.sync="dialogVisible" width="700px" :close-on-click-modal="false">
      <el-form :model="form" label-width="120px">
        <el-form-item v-for="c in editableColumns" :key="c" :label="columnLabel(c)">
          <el-upload
            v-if="isFileField(c)"
            action="/api/file/upload"
            :show-file-list="false"
            :on-success="res => uploadSuccess(res, c)"
            :before-upload="beforeUpload"
            :on-remove="() => {}"
            :on-cancel="() => {}"
          >
            <el-button type="primary" :disabled="dialogMode==='view'">上传图片</el-button>
          </el-upload>
          <el-input v-else v-model="form[c]" :disabled="dialogMode==='view'" />
          <el-image
            v-if="isFileField(c) && form[c]"
            :src="fileUrl(form[c])"
            fit="cover"
            style="margin-top:8px;width:120px;height:76px;border-radius:8px"
            :preview-src-list="[fileUrl(form[c])]"
          />
        </el-form-item>
      </el-form>
      <span slot="footer">
        <el-button @click="dialogVisible=false">取消</el-button>
        <el-button type="primary" v-if="dialogMode!=='view'" @click="save">保存</el-button>
      </span>
    </el-dialog>

    <el-dialog title="预约审批" :visible.sync="approveDialogVisible" width="460px" :close-on-click-modal="false">
      <el-form :model="approveForm" label-width="90px">
        <el-form-item label="审批结果">
          <el-tag :type="approveForm.status === 'approved' ? 'success' : 'warning'">{{ approveForm.status === 'approved' ? '通过' : '驳回' }}</el-tag>
        </el-form-item>
        <el-form-item label="审批备注">
          <el-input type="textarea" v-model="approveForm.remark" :rows="4" placeholder="请输入审批备注" />
        </el-form-item>
      </el-form>
      <span slot="footer">
        <el-button @click="approveDialogVisible=false">取消</el-button>
        <el-button type="primary" @click="doApprove">确认</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { pageApi, addApi, updateApi, deleteApi, detailApi, approveBookingApi } from '../api';

const FIELD_LABELS = {
  username: '用户名', password: '密码', realName: '真实姓名', role: '角色', departmentId: '部门ID', phone: '联系电话', email: '邮箱', avatar: '头像', status: '状态',
  deptName: '部门名称', deptCode: '部门编码', managerId: '主管ID', parentId: '上级部门ID', description: '描述',
  roomName: '会议室名称', roomCode: '会议室编号', location: '位置', capacity: '容纳人数', equipment: '设备信息', image: '会议室图片',
  roomId: '会议室ID', userId: '用户ID', meetingTitle: '会议主题', meetingContent: '会议内容', startTime: '开始时间', endTime: '结束时间', participants: '参会人数', approveUserId: '审批人ID', approveTime: '审批时间', approveRemark: '审批备注',
  faultType: '故障类型', faultDescription: '故障描述', faultImage: '故障图片', handlerId: '处理人ID', handleResult: '处理结果', handleTime: '处理时间',
  configKey: '参数键', configValue: '参数值', configName: '参数名称', configType: '参数类型',
  operation: '操作类型', method: '请求方法', params: '请求参数', ip: 'IP地址', result: '结果', errorMsg: '错误信息',
  bookingId: '预约ID', checkInTime: '签到时间', checkOutTime: '签退时间', deviceId: '设备ID', syncStatus: '同步状态',
  operationType: '设备操作类型', operationData: '设备操作数据',
  createTime: '创建时间', updateTime: '更新时间', deleted: '删除标记', id: 'ID'
};

export default {
  props: {
    title: String,
    module: String,
    columns: Array,
    readOnly: {
      type: Boolean,
      default: false
    },
    extraParams: {
      type: Object,
      default: () => ({})
    },
    mode: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      query: { keyword: '' },
      tableData: [],
      page: { current: 1, size: 10, total: 0, pages: 0 },
      gotoPage: 1,
      dialogVisible: false,
      dialogMode: 'add',
      form: {},
      approveDialogVisible: false,
      approveForm: { id: null, status: 'approved', remark: '' }
    };
  },
  computed: {
    editableColumns() {
      return this.columns.filter(i => !['id', 'createTime', 'updateTime', 'deleted'].includes(i));
    },
    dialogTitle() {
      return this.dialogMode === 'add' ? '新增' : this.dialogMode === 'edit' ? '编辑' : '查看详情';
    },
    isApproveMode() {
      return this.mode === 'booking-approve';
    }
  },
  watch: {
    module: { immediate: true, handler() { this.page.current = 1; this.loadData(); } }
  },
  methods: {
    columnLabel(key) {
      return FIELD_LABELS[key] || key;
    },
    isFileField(c) {
      return ['avatar', 'image', 'faultImage'].includes(c);
    },
    fileUrl(v) {
      if (!v) return '';
      if (String(v).startsWith('http')) return v;
      return v;
    },
    formatDate(v) {
      if (!v) return '';
      const s = String(v).replace('T', ' ');
      if (s.length >= 10) {
        const d = s.slice(0, 10).split('-');
        if (d.length === 3) return `${d[0]}年${d[1]}月${d[2]}日`;
      }
      return s;
    },
    formatCell(v, key) {
      if (['createTime','updateTime','startTime','endTime','approveTime','handleTime','checkInTime','checkOutTime'].includes(key)) {
        return this.formatDate(v);
      }
      return v;
    },
    beforeUpload(file) {
      const sizeOk = file.size / 1024 / 1024 < 10;
      if (!sizeOk) this.$message.error('文件不能超过10MB');
      return sizeOk;
    },
    uploadSuccess(res, key) {
      if (res.code !== 200) return this.$message.error(res.message);
      this.form[key] = res.data.url;
    },
    async loadData() {
      const { data } = await pageApi(this.module, { current: this.page.current, size: this.page.size, keyword: this.query.keyword, ...this.extraParams });
      if (data.code !== 200) return this.$message.error(data.message);
      this.tableData = data.data.records || [];
      this.page.total = data.data.total || 0;
      this.page.current = data.data.current || 1;
      this.page.pages = data.data.pages || 1;
      this.gotoPage = this.page.current;
    },
    jump(target) {
      const page = Math.max(1, Math.min(target || 1, this.page.pages || 1));
      this.page.current = page;
      this.loadData();
    },
    async openDialog(mode, row) {
      this.dialogMode = mode;
      this.form = {};
      if (row && row.id) {
        const { data } = await detailApi(this.module, row.id);
        this.form = data.data || {};
      }
      this.dialogVisible = true;
    },
    async save() {
      const { data } = this.form.id ? await updateApi(this.module, this.form) : await addApi(this.module, this.form);
      if (data.code !== 200) return this.$message.error(data.message);
      this.$message.success('保存成功');
      this.dialogVisible = false;
      this.loadData();
    },
    async remove(row) {
      try {
        await this.$confirm('确认删除该记录吗？', '提示', { type: 'warning' });
      } catch (e) {
        return;
      }
      const { data } = await deleteApi(this.module, row.id);
      if (data.code !== 200) return this.$message.error(data.message);
      this.$message.success('删除成功');
      this.loadData();
    },
    openApprove(row, status) {
      this.approveForm = { id: row.id, status, remark: '' };
      this.approveDialogVisible = true;
    },
    async doApprove() {
      const user = JSON.parse(localStorage.getItem('meeting_user') || '{}');
      const { data } = await approveBookingApi(this.approveForm.id, this.approveForm.status, this.approveForm.remark, user.id);
      if (data.code !== 200) return this.$message.error(data.message);
      this.$message.success('审批成功');
      this.approveDialogVisible = false;
      this.loadData();
    }
  }
};
</script>

<style scoped>
.toolbar { display:flex; justify-content:space-between; align-items:center; }
.pager-wrap { margin-top:16px; display:flex; justify-content:space-between; align-items:center; flex-wrap:wrap; gap:10px; }
.pager-actions { display:flex; align-items:center; gap:8px; }
</style>
