<template>
  <div class="page">
    <div class="greeting-card">
      <div class="greeting-content">
        <div class="greeting-title">欢迎回来，{{ user?.nickName }}</div>
        <div class="greeting-subtitle">{{ rightLabel }} · {{ user?.userId }}</div>
      </div>
    </div>

    <div class="stats-grid">
      <div class="stat-card">
        <div class="stat-icon blue">
          <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <path d="M13 2L3 14h9l-1 8 10-12h-9l1-8z"/>
          </svg>
        </div>
        <div class="stat-info">
          <div class="stat-value">办公区照明</div>
          <div class="stat-label">智能控制系统</div>
        </div>
      </div>
      
      <div class="stat-card">
        <div class="stat-icon green">
          <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <circle cx="12" cy="12" r="10"/>
            <polyline points="12 6 12 12 16 14"/>
          </svg>
        </div>
        <div class="stat-info">
          <div class="stat-value">节能管理</div>
          <div class="stat-label">实时监测能耗</div>
        </div>
      </div>
      
      <div class="stat-card">
        <div class="stat-icon orange">
          <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <path d="M18 8A6 6 0 0 0 6 8c0 7-3 9-3 9h18s-3-2-3-9"/>
            <path d="M13.73 21a2 2 0 0 1-3.46 0"/>
          </svg>
        </div>
        <div class="stat-info">
          <div class="stat-value">设备监控</div>
          <div class="stat-label">远程管理设备</div>
        </div>
      </div>
      
      <div class="stat-card">
        <div class="stat-icon purple">
          <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <path d="M12 8v4l3 3"/>
            <circle cx="12" cy="12" r="10"/>
          </svg>
        </div>
        <div class="stat-info">
          <div class="stat-value">故障工单</div>
          <div class="stat-label">快速响应处理</div>
        </div>
      </div>
    </div>

    <div v-if="currentRight !== 0" class="info-card">
      <div class="info-header">
        <div class="info-title">系统概览</div>
        <div class="info-badge">实时更新</div>
      </div>
      <div class="info-content">
        <div class="info-item">
          <span class="info-label">系统状态</span>
          <span class="info-value success">运行正常</span>
        </div>
        <div class="info-item">
          <span class="info-label">在线设备</span>
          <span class="info-value">{{ onlineDevices }}</span>
        </div>
        <div class="info-item">
          <span class="info-label">累计节能</span>
          <span class="info-value">{{ totalEnergySaved.toFixed(2) }} 千瓦时</span>
        </div>
        <div class="info-item">
          <span class="info-label">告警数量</span>
          <span class="info-value warning">{{ unprocessedAlerts }}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { useHome } from './index.js'

const router = useRouter()
const { user, rightLabel, refreshUser, onlineDevices, totalEnergySaved, unprocessedAlerts, currentRight } = useHome()

onMounted(() => {
  refreshUser()
  router.afterEach(() => {
    refreshUser()
  })
})
</script>
<style scoped>
.page {
  padding: 20px;
}

.greeting-card {
  background: linear-gradient(135deg, var(--primary-color) 0%, var(--primary-dark) 100%);
  border-radius: var(--radius-lg);
  padding: 32px;
  margin-bottom: 20px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  color: #ffffff;
  box-shadow: var(--shadow-lg);
  transition: all var(--transition-normal);
}

.greeting-content {
  flex: 1;
}

.greeting-title {
  font-size: 28px;
  font-weight: 700;
  margin-bottom: 8px;
}

.greeting-subtitle {
  font-size: 14px;
  opacity: 0.85;
}

.stats-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(240px, 1fr));
  gap: 16px;
  margin-bottom: 20px;
}

.stat-card {
  background: var(--card-bg);
  backdrop-filter: blur(12px);
  border-radius: var(--radius-md);
  padding: 20px;
  display: flex;
  align-items: center;
  gap: 16px;
  box-shadow: var(--shadow-sm);
  border: 1px solid var(--border-light);
  transition: all var(--transition-fast);
}

.stat-card:hover {
  transform: translateY(-2px);
  box-shadow: var(--shadow-md);
}

.stat-icon {
  width: 48px;
  height: 48px;
  border-radius: var(--radius-md);
  display: flex;
  align-items: center;
  justify-content: center;
  color: #ffffff;
  flex-shrink: 0;
}

.stat-icon.blue {
  background: linear-gradient(135deg, var(--primary-color) 0%, var(--primary-dark) 100%);
}

.stat-icon.green {
  background: linear-gradient(135deg, #52c41a 0%, #389e0d 100%);
}

.stat-icon.orange {
  background: linear-gradient(135deg, #fa8c16 0%, #d46b08 100%);
}

.stat-icon.purple {
  background: linear-gradient(135deg, #722ed1 0%, #531dab 100%);
}

.stat-icon svg {
  width: 24px;
  height: 24px;
}

.stat-info {
  flex: 1;
}

.stat-value {
  font-size: 18px;
  font-weight: 600;
  color: var(--text-primary);
  margin-bottom: 4px;
}

.stat-label {
  font-size: 13px;
  color: var(--text-secondary);
}

.info-card {
  background: var(--card-bg);
  backdrop-filter: blur(12px);
  border-radius: var(--radius-md);
  padding: 24px;
  box-shadow: var(--shadow-sm);
  border: 1px solid var(--border-light);
}

.info-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.info-title {
  font-size: 16px;
  font-weight: 600;
  color: var(--text-primary);
}

.info-badge {
  font-size: 12px;
  color: var(--success-color);
  background: rgba(82, 196, 26, 0.1);
  padding: 4px 12px;
  border-radius: 20px;
}

.info-content {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: 16px;
}

.info-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 12px 16px;
  background: var(--bg-hover);
  border-radius: var(--radius-sm);
}

.info-label {
  font-size: 14px;
  color: var(--text-secondary);
}

.info-value {
  font-size: 16px;
  font-weight: 600;
  color: var(--text-primary);
}

.info-value.success {
  color: var(--success-color);
}

.info-value.warning {
  color: var(--warning-color);
}

@media (max-width: 768px) {
  .greeting-card {
    flex-direction: column;
    text-align: center;
    gap: 20px;
  }
  
  .stats-grid {
    grid-template-columns: 1fr;
  }
}
</style>