import { nextTick, onBeforeUnmount, onMounted, ref } from 'vue'
import * as echarts from 'echarts'
import { saveInfoApi, userDeviceBoundApi } from '../../api'
import { getStoredUser } from '../../utils/auth'

function useUserEnergy() {
  const deviceOptions = ref([])
  const deviceId = ref('')
  const day = ref('')
  const loading = ref(false)
  const rows = ref([])

  const powerChartRef = ref(null)
  const carbonChartRef = ref(null)
  let powerChartIns = null
  let carbonChartIns = null
  let refreshTimer = null

  function ensureDefaultDay() {
    if (day.value) return
    const now = new Date()
    const y = String(now.getFullYear())
    const m = String(now.getMonth() + 1).padStart(2, '0')
    const d = String(now.getDate()).padStart(2, '0')
    day.value = `${y}-${m}-${d}`
  }

  async function loadDeviceOptions() {
    const me = getStoredUser()
    const bound = await userDeviceBoundApi.load({ userId: me?.userId || '', pageNo: 1, pageSize: 200 })
    deviceOptions.value = (bound?.list || []).map((b) => b.deviceId).filter(Boolean)
    if (!deviceId.value && deviceOptions.value.length > 0) deviceId.value = deviceOptions.value[0]
  }

  async function load() {
    ensureDefaultDay()
    loading.value = true
    try {
      const list = await saveInfoApi.getListByDay({
        deviceId: deviceId.value || undefined,
        currentTime: day.value,
      })
      rows.value = list || []
      await nextTick()
      renderCharts()
    } finally {
      loading.value = false
    }
  }

  function renderCharts() {
    const x = rows.value.map((r) => `${r.currentHour}:00`)
    const power = rows.value.map((r) => Number(r.power || 0))
    const carbon = rows.value.map((r) => Number(r.carbon || 0))

    if (powerChartRef.value) {
      if (!powerChartIns) powerChartIns = echarts.init(powerChartRef.value)
      powerChartIns.setOption({
        tooltip: { trigger: 'axis' },
        legend: { data: ['节电量'], top: 10 },
        grid: { left: 50, right: 20, top: 40, bottom: 40 },
        xAxis: {
          type: 'category',
          data: x,
          name: '时间',
          axisLabel: { rotate: 45, fontSize: 12 },
        },
        yAxis: {
          type: 'value',
          name: '节电量',
        },
        series: [
          {
            name: '节电量',
            type: 'line',
            data: power,
            smooth: true,
            symbol: 'circle',
            symbolSize: 8,
            lineStyle: { width: 3 },
            itemStyle: { borderWidth: 2 },
          },
        ],
      })
      powerChartIns.resize()
    }

    if (carbonChartRef.value) {
      if (!carbonChartIns) carbonChartIns = echarts.init(carbonChartRef.value)
      carbonChartIns.setOption({
        tooltip: { trigger: 'axis' },
        legend: { data: ['节碳量'], top: 10 },
        grid: { left: 50, right: 20, top: 40, bottom: 40 },
        xAxis: {
          type: 'category',
          data: x,
          name: '时间',
          axisLabel: { rotate: 45, fontSize: 12 },
        },
        yAxis: {
          type: 'value',
          name: '节碳量',
        },
        series: [
          {
            name: '节碳量',
            type: 'line',
            data: carbon,
            smooth: true,
            symbol: 'circle',
            symbolSize: 8,
            lineStyle: { width: 3, color: '#52c41a' },
            itemStyle: { borderWidth: 2, color: '#52c41a' },
          },
        ],
      })
      carbonChartIns.resize()
    }
  }

  onMounted(async () => {
    ensureDefaultDay()
    await loadDeviceOptions()
    await load()
    window.addEventListener('resize', renderCharts)
    
    refreshTimer = setInterval(() => {
      load()
    }, 10000)
  })

  onBeforeUnmount(() => {
    window.removeEventListener('resize', renderCharts)
    if (refreshTimer) {
      clearInterval(refreshTimer)
      refreshTimer = null
    }
    if (powerChartIns) {
      powerChartIns.dispose()
      powerChartIns = null
    }
    if (carbonChartIns) {
      carbonChartIns.dispose()
      carbonChartIns = null
    }
  })

  return { deviceOptions, deviceId, day, loading, rows, powerChartRef, carbonChartRef, load }
}

export { useUserEnergy }