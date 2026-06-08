<template>
  <div class="dashboard">
    <el-container style="height:100%">
      <el-aside width="220px" class="aside">
        <h3>会议室预约与管理系统</h3>
        <el-menu :default-active="active" @select="active=$event" background-color="#0f2f4f" text-color="#fff" active-text-color="#6dd5ed">
          <el-menu-item v-for="m in menus" :key="m.key" :index="m.key">{{ m.name }}</el-menu-item>
        </el-menu>
      </el-aside>
      <el-container>
        <el-header class="header">
          <div>会议室预约与管理系统</div>
          <el-button type="danger" size="mini" @click="logout">退出</el-button>
        </el-header>
        <el-main>
          <home-panel v-if="current.key === 'home'" />
          <crud-panel v-else :title="current.name" :module="current.module" :columns="current.columns" :mode="current.key" :read-only="current.readOnly" :extra-params="current.extraParams || {}" />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import CrudPanel from '../components/CrudPanel.vue';
import HomePanel from '../components/HomePanel.vue';

export default {
  components: { CrudPanel, HomePanel },
  data() {
    const user = JSON.parse(localStorage.getItem('meeting_user') || '{}');
    const role = user.role || 'employee';

    const adminMenus = [
      { key: 'home', name: '首页' },
      { key: 'user', name: '员工管理', module: 'user', columns: ['username','realName','role','departmentId','phone','email','avatar','status'] },
      { key: 'department', name: '部门管理', module: 'department', columns: ['deptName','deptCode','managerId','parentId','description'] },
      { key: 'room', name: '会议室管理', module: 'room', columns: ['roomName','roomCode','location','capacity','equipment','image','status'] },
      { key: 'system-config', name: '系统参数', module: 'system-config', columns: ['configKey','configName','configType','configValue','description'] },
      { key: 'system-log', name: '系统日志', module: 'system-log', columns: ['userId','username','operation','method','ip','result','createTime'] },
      { key: 'fault', name: '故障报告', module: 'fault', columns: ['roomId','userId','faultType','faultDescription','faultImage','status','handlerId'] },
      { key: 'check-record', name: '签到记录', module: 'check-record', columns: ['bookingId','roomId','userId','checkInTime','checkOutTime','deviceId','syncStatus'] },
      { key: 'device-log', name: '设备日志', module: 'device-log', columns: ['deviceId','roomId','operationType','operationData','syncStatus','createTime'] }
    ];

    const deptMenus = [
      { key: 'home', name: '首页' },
      { key: 'department', name: '部门信息', module: 'department', columns: ['deptName','deptCode','managerId','parentId','description'] },
      { key: 'booking-plan', name: '预约安排', module: 'booking', columns: ['roomId','userId','departmentId','meetingTitle','startTime','endTime','participants','status'], readOnly: true },
      { key: 'booking-approve', name: '审核预约', module: 'booking', columns: ['roomId','userId','departmentId','meetingTitle','startTime','endTime','participants','status','approveRemark'] }
    ];

    const employeeMenus = [
      { key: 'home', name: '首页' },
      { key: 'room', name: '会议室查看', module: 'room', columns: ['roomName','roomCode','location','capacity','equipment','image','status'], readOnly: true },
      { key: 'my-booking', name: '我的预约', module: 'booking', columns: ['roomId','userId','departmentId','meetingTitle','startTime','endTime','participants','status','approveRemark'], extraParams: { userId: user.id } },
      { key: 'fault', name: '故障报告', module: 'fault', columns: ['roomId','userId','faultType','faultDescription','faultImage','status','handlerId'] }
    ];

    const menus = role === 'admin' ? adminMenus : role === 'dept_admin' ? deptMenus : employeeMenus;
    return { active: menus.length ? menus[0].key : '', menus };
  },
  computed: {
    current() {
      return this.menus.find(m => m.key === this.active) || (this.menus[0] || { key: 'home', name: '首页' });
    }
  },
  methods: {
    logout() {
      localStorage.removeItem('meeting_user');
      this.$router.push('/login');
    }
  }
};
</script>

<style scoped>
.dashboard { height: 100%; }
.aside { background: linear-gradient(180deg,#0b2a47,#113a5f); color:#fff; }
.aside h3 { text-align:center; color:#6dd5ed; padding:16px 8px; font-size: 16px; line-height: 1.4; }
.header { display:flex; justify-content:space-between; align-items:center; background:#f5f9ff; border-bottom:1px solid #e6eef8; }
</style>
