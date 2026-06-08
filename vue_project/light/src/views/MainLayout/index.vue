<template>
  <el-container class="layout">
    <el-header class="header">
      <div class="title">办公区灯光照度监测与节能调节管理系统</div>
      <div class="user">
        <button class="theme-toggle" @click="changeTheme" :title="isDark ? '切换到亮色模式' : '切换到暗色模式'">
          <svg v-if="!isDark" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <path d="M20.354 15.354A9 9 0 0 1 8.646 3.646 9.003 9.003 0 0 0 12 21a9.003 9.003 0 0 0 8.354-5.646z"/>
          </svg>
          <svg v-else viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <circle cx="12" cy="12" r="5"/>
            <line x1="12" y1="1" x2="12" y2="3"/>
            <line x1="12" y1="21" x2="12" y2="23"/>
            <line x1="4.22" y1="4.22" x2="5.64" y2="5.64"/>
            <line x1="18.36" y1="18.36" x2="19.78" y2="19.78"/>
            <line x1="1" y1="12" x2="3" y2="12"/>
            <line x1="21" y1="12" x2="23" y2="12"/>
            <line x1="4.22" y1="19.78" x2="5.64" y2="18.36"/>
            <line x1="18.36" y1="5.64" x2="19.78" y2="4.22"/>
          </svg>
        </button>
        <span class="name">{{ user?.nickName }}</span>
        <el-button size="small" @click="logout">退出</el-button>
      </div>
    </el-header>
    <el-container class="body">
      <el-aside width="220px" class="aside">
        <el-menu :default-active="activeMenu" router class="menu">
          <el-menu-item index="/home">
            <svg class="menu-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <path d="M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"/>
              <polyline points="9 22 9 12 15 12 15 22"/>
            </svg>
            <span>首页</span>
          </el-menu-item>
          <el-menu-item v-for="item in menuItems" :key="item.path" :index="item.path">
            <svg class="menu-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <path v-if="item.icon === 'users'" d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"/>
              <circle v-if="item.icon === 'users'" cx="9" cy="7" r="4"/>
              <path v-if="item.icon === 'users'" d="M23 21v-2a4 4 0 0 0-3-3.87"/>
              <path v-if="item.icon === 'users'" d="M16 3.13a4 4 0 0 1 0 7.75"/>
              <rect v-if="item.icon === 'monitor'" x="2" y="3" width="20" height="14" rx="2" ry="2"/>
              <path v-if="item.icon === 'monitor'" d="M8 21h8M12 17v4"/>
              <path v-if="item.icon === 'folder'" d="M22 19a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h5l2 3h9a2 2 0 0 1 2 2z"/>
              <path v-if="item.icon === 'trending-up'" d="M18 20V10M9 14.5l3 3 3-3M12 20V4"/>
              <path v-if="item.icon === 'user'" d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"/>
              <circle v-if="item.icon === 'user'" cx="12" cy="7" r="4"/>
              <circle v-if="item.icon === 'alert-circle'" cx="12" cy="12" r="10"/>
              <path v-if="item.icon === 'alert-circle'" d="M12 8v4M12 16h.01"/>
              <path v-if="item.icon === 'activity'" d="M12 22s8-4 8-10V5l-8-3-8 3v7c0 6 8 10 8 10z"/>
              <path v-if="item.icon === 'file-text'" d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z"/>
              <polyline v-if="item.icon === 'file-text'" points="14 2 14 8 20 8"/>
              <line v-if="item.icon === 'file-text'" x1="16" y1="13" x2="8" y2="13"/>
              <line v-if="item.icon === 'file-text'" x1="16" y1="17" x2="8" y2="17"/>
              <line v-if="item.icon === 'file-text'" x1="10" y1="9" x2="8" y2="9"/>
              <circle v-if="item.icon === 'sun'" cx="12" cy="12" r="5"/>
              <line v-if="item.icon === 'sun'" x1="12" y1="1" x2="12" y2="3"/>
              <line v-if="item.icon === 'sun'" x1="12" y1="21" x2="12" y2="23"/>
              <line v-if="item.icon === 'sun'" x1="4.22" y1="4.22" x2="5.64" y2="5.64"/>
              <line v-if="item.icon === 'sun'" x1="18.36" y1="18.36" x2="19.78" y2="19.78"/>
              <line v-if="item.icon === 'sun'" x1="1" y1="12" x2="3" y2="12"/>
              <line v-if="item.icon === 'sun'" x1="21" y1="12" x2="23" y2="12"/>
              <line v-if="item.icon === 'sun'" x1="4.22" y1="19.78" x2="5.64" y2="18.36"/>
              <line v-if="item.icon === 'sun'" x1="18.36" y1="5.64" x2="19.78" y2="4.22"/>
              <path v-if="item.icon === 'lightbulb'" d="M9 19c-5 1.5-5-2.5-7-3m14 6v-3.86a2 2 0 0 0-.88-1.65l-3.58-2.23a2 2 0 0 0-2.94 0l-3.58 2.23a2 2 0 0 0-.88 1.65V19"/>
            </svg>
            <span>{{ item.label }}</span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-main class="main">
        <router-view v-slot="{ Component }">
          <transition name="slide-up">
            <component :is="Component" />
          </transition>
        </router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup>
import { useMainLayout } from './index.js'
const { user, menuItems, activeMenu, logout, isDark, changeTheme } = useMainLayout()
</script>
<style scoped src="./index.css"></style>