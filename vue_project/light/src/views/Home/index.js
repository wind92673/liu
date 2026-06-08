import { computed, onBeforeUnmount, onMounted, ref, watch } from 'vue'
import { getRight, getStoredUser } from '../../utils/auth'
import { deviceInfoApi, saveInfoApi, warnOrderApi } from '../../api'

function useHome() {
  const userData = ref(getStoredUser())
  const currentRight = ref(getRight())
  
  const onlineDevices = ref(0)
  const totalEnergySaved = ref(0)
  const unprocessedAlerts = ref(0)
  let refreshTimer = null

  const user = computed(() => userData.value)

  const rightLabel = computed(() => {
    const r = currentRight.value
    if (r === 2) return '管理员'
    if (r === 1) return '运维人员'
    return '普通用户'
  })

  async function loadOverview() {
    try {
      const [onlineRes, energyRes, alertRes] = await Promise.all([
        deviceInfoApi.countOnline({}),
        saveInfoApi.getYearlySummary({ year: new Date().getFullYear() }),
        warnOrderApi.countUnprocessed({}),
      ])
      
      onlineDevices.value = onlineRes?.count || 0
      
      const energyList = energyRes || []
      totalEnergySaved.value = energyList.reduce((sum, item) => sum + (Number(item.total_power) || 0), 0)
      
      unprocessedAlerts.value = alertRes?.count || 0
    } catch (error) {
      console.error('Failed to load overview data:', error)
    }
  }

  function refreshUser() {
    userData.value = getStoredUser()
    currentRight.value = getRight()
  }

  onMounted(() => {
    loadOverview()
    
    refreshTimer = setInterval(() => {
      loadOverview()
    }, 10000)
  })

  onBeforeUnmount(() => {
    if (refreshTimer) {
      clearInterval(refreshTimer)
      refreshTimer = null
    }
  })

  return { 
    user, 
    rightLabel, 
    refreshUser,
    onlineDevices,
    totalEnergySaved,
    unprocessedAlerts,
    loadOverview,
    currentRight
  }
}

export { useHome }