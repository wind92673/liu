import { computed, ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { clearStoredUser, getRight, getStoredUser } from '../../utils/auth'
import { getTheme, toggleTheme } from '../../utils/theme'

function useMainLayout() {
  const router = useRouter()
  const route = useRoute()
  const isDark = ref(getTheme() === 'dark')

  const user = computed(() => getStoredUser())
  const right = computed(() => getRight())

  const menuItems = computed(() => {
    const r = right.value
    if (r === 2) {
      return [
        { path: '/admin/users', label: '用户管理', icon: 'users' },
        { path: '/admin/devices', label: '设备管理', icon: 'monitor' },
        { path: '/admin/groups', label: '分组管理', icon: 'folder' },
        { path: '/energy', label: '节能管理', icon: 'trending-up' },
        { path: '/profile', label: '个人中心', icon: 'user' },
      ]
    }
    if (r === 1) {
      return [
        { path: '/devops/orders', label: '故障工单', icon: 'alert-circle' },
        { path: '/devops/monitor', label: '监控设备', icon: 'activity' },
        { path: '/devops/logs', label: '历史日志', icon: 'file-text' },
        { path: '/profile', label: '个人中心', icon: 'user' },
      ]
    }
    return [
      { path: '/user/devices', label: '亮度查询', icon: 'sun' },
      { path: '/user/energy', label: '节能管理', icon: 'lightbulb' },
      { path: '/profile', label: '个人中心', icon: 'user' },
    ]
  })

  const activeMenu = computed(() => route.path)

  function logout() {
    clearStoredUser()
    router.replace('/login')
  }

  function changeTheme() {
    isDark.value = toggleTheme() === 'dark'
  }

  return { user, menuItems, activeMenu, logout, isDark, changeTheme }
}

export { useMainLayout }