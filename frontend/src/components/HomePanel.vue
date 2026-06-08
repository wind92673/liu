<template>
  <div class="home-wrap">
    <el-row :gutter="16">
      <el-col :span="6" v-for="item in cards" :key="item.key">
        <el-card class="home-card">
          <div class="title">{{ item.title }}</div>
          <div class="num">{{ stats[item.key] || 0 }}</div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { homeStatsApi } from '../api';

export default {
  data() {
    return {
      stats: {},
      cards: [
        { key: 'userCount', title: '员工总数' },
        { key: 'departmentCount', title: '部门总数' },
        { key: 'roomCount', title: '会议室总数' },
        { key: 'bookingCount', title: '预约总数' },
        { key: 'pendingBookingCount', title: '待审核预约' },
        { key: 'faultCount', title: '故障总数' },
        { key: 'pendingFaultCount', title: '待处理故障' }
      ]
    };
  },
  async created() {
    const { data } = await homeStatsApi();
    if (data.code === 200) this.stats = data.data || {};
  }
};
</script>

<style scoped>
.home-wrap { padding: 4px; }
.home-card { margin-bottom: 16px; }
.title { color: #666; font-size: 14px; }
.num { margin-top: 8px; font-size: 28px; color: #0f2f4f; font-weight: 600; }
</style>
