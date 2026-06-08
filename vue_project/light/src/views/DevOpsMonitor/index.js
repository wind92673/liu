import { onMounted, reactive, ref } from 'vue'
import { deviceInfoApi, reportLightApi } from '../../api'

function useDevOpsMonitor() {
  const query = reactive({
    deviceIdFuzzy: '',
    online: undefined,
    pageNo: 1,
    pageSize: 15,
  })

  const rows = ref([])
  const total = ref(0)
  const brightnessMap = reactive({})

  function buildQueryParams() {
    return {
      deviceIdFuzzy: query.deviceIdFuzzy || undefined,
      online: query.online === undefined ? undefined : query.online,
      pageNo: query.pageNo,
      pageSize: query.pageSize,
      orderBy: 'device_id asc',
    }
  }

  async function loadBrightness(devices) {
    const tasks = (devices || []).map(async (d) => {
      const data = await reportLightApi.load({
        deviceId: d.deviceId,
        pageNo: 1,
        pageSize: 1,
        orderBy: 'report_time desc',
      })
      const first = (data?.list || [])[0]
      brightnessMap[d.deviceId] = first?.brightNess ?? '-'
    })
    await Promise.allSettled(tasks)
  }

  async function load() {
    const data = await deviceInfoApi.load(buildQueryParams())
    const list = data?.list || []
    rows.value = list
    total.value = data?.totalCount || 0
    await loadBrightness(list)
  }

  function reset() {
    query.deviceIdFuzzy = ''
    query.online = undefined
    query.pageNo = 1
    load()
  }

  onMounted(() => {
    load()
  })

  return { query, rows, total, brightnessMap, load, reset }
}

export { useDevOpsMonitor }