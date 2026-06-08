import { computed, nextTick, onBeforeUnmount, onMounted, ref, watch } from 'vue'
import { ElMessage } from 'element-plus'
import * as echarts from 'echarts'
import { deviceInfoApi, saveInfoApi } from '../../api'

function useEnergyManage() {
  const mode = ref('month')
  const deviceId = ref('')
  const day = ref('')
  const month = ref('')
  const year = ref('')
  const loading = ref(false)

  const deviceOptions = ref([])

  const rows = ref([])
  const powerChartRef = ref(null)
  const carbonChartRef = ref(null)
  let powerChartIns = null
  let carbonChartIns = null
  let refreshTimer = null

  const labelTitle = computed(() => {
    if (mode.value === 'year') return '月份'
    if (mode.value === 'month') return '日期'
    return '小时'
  })

  function ensureDefaultDate() {
    const now = new Date()
    const y = String(now.getFullYear())
    const m = String(now.getMonth() + 1).padStart(2, '0')
    const d = String(now.getDate()).padStart(2, '0')
    if (!day.value) day.value = `${y}-${m}-${d}`
    if (!month.value) month.value = `${y}-${m}`
    if (!year.value) year.value = y
  }

  async function loadDevices() {
    const data = await deviceInfoApi.load({ pageNo: 1, pageSize: 200, orderBy: 'device_id asc' })
    deviceOptions.value = data?.list || []
  }

  function normalizeSummary(list, labelKey) {
    return (list || []).map((item) => ({
      label: item[labelKey],
      total_power: item.total_power,
      total_carbon: item.total_carbon,
    }))
  }

  function normalizeDay(list) {
    return (list || []).map((item) => ({
      label: String(item.currentHour),
      total_power: item.power,
      total_carbon: item.carbon,
    }))
  }

  async function load() {
    ensureDefaultDate()
    loading.value = true
    try {
      if (mode.value === 'day') {
        const list = await saveInfoApi.getListByDay({ deviceId: deviceId.value || undefined, currentTime: day.value })
        rows.value = normalizeDay(list)
      } else if (mode.value === 'month') {
        const [y, m] = month.value.split('-')
        const list = await saveInfoApi.getMonthlySummary({
          deviceId: deviceId.value || undefined,
          year: Number(y),
          month: Number(m),
        })
        rows.value = normalizeSummary(list, 'date')
      } else {
        const list = await saveInfoApi.getYearlySummary({
          deviceId: deviceId.value || undefined,
          year: Number(year.value),
        })
        rows.value = normalizeSummary(list, 'month')
      }
      await nextTick()
      renderCharts()
    } finally {
      loading.value = false
    }
  }

  function renderCharts() {
    const x = rows.value.map((r) => r.label)
    const power = rows.value.map((r) => Number(r.total_power || 0))
    const carbon = rows.value.map((r) => Number(r.total_carbon || 0))

    if (powerChartRef.value) {
      if (!powerChartIns) {
        powerChartIns = echarts.init(powerChartRef.value)
      }
      powerChartIns.setOption({
        tooltip: { trigger: 'axis' },
        grid: { left: 40, right: 20, top: 30, bottom: 40 },
        xAxis: { type: 'category', data: x },
        yAxis: { type: 'value' },
        series: [
          { 
            name: '节电量', 
            type: 'line', 
            data: power,
            smooth: true,
            symbol: 'circle',
            lineStyle: { color: '#409EFF' },
            itemStyle: { color: '#409EFF' }
          },
        ],
      })
      powerChartIns.resize()
    }

    if (carbonChartRef.value) {
      if (!carbonChartIns) {
        carbonChartIns = echarts.init(carbonChartRef.value)
      }
      carbonChartIns.setOption({
        tooltip: { trigger: 'axis' },
        grid: { left: 40, right: 20, top: 30, bottom: 40 },
        xAxis: { type: 'category', data: x },
        yAxis: { type: 'value' },
        series: [
          { 
            name: '节碳量', 
            type: 'line', 
            data: carbon,
            smooth: true,
            symbol: 'circle',
            lineStyle: { color: '#67C23A' },
            itemStyle: { color: '#67C23A' }
          },
        ],
      })
      carbonChartIns.resize()
    }
  }

  async function exportExcel() {
    ensureDefaultDate()
    const params = { deviceId: deviceId.value || undefined }
    if (mode.value === 'day') {
      params.currentTimeStart = day.value
      params.currentTimeEnd = day.value
    } else if (mode.value === 'month') {
      const [y, m] = month.value.split('-')
      params.year = Number(y)
      params.month = Number(m)
    } else {
      params.year = Number(year.value)
    }
    const blobRes = await saveInfoApi.exportExcel(params)
    const blob = blobRes
    if (!(blob instanceof Blob)) {
      ElMessage.error('导出失败')
      return
    }
    const url = URL.createObjectURL(blob)
    const a = document.createElement('a')
    a.href = url
    a.download = '节能统计.xlsx'
    a.click()
    URL.revokeObjectURL(url)
  }

  watch(mode, () => {
    rows.value = []
    nextTick(() => renderCharts())
  })

  onMounted(async () => {
    ensureDefaultDate()
    await loadDevices()
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

  return { mode, deviceId, day, month, year, loading, deviceOptions, rows, powerChartRef, carbonChartRef, labelTitle, load, exportExcel }
}

export { useEnergyManage }