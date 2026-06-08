import { onMounted, reactive, ref } from 'vue'
import { commandLogApi } from '../../api'

function useDevOpsLogs() {
  const query = reactive({
    deviceIdFuzzy: '',
    eventTypeFuzzy: '',
    timeRange: [],
    pageNo: 1,
    pageSize: 15,
  })

  const rows = ref([])
  const total = ref(0)

  function buildQueryParams() {
    const [start, end] = query.timeRange || []
    return {
      deviceIdFuzzy: query.deviceIdFuzzy || undefined,
      eventTypeFuzzy: query.eventTypeFuzzy || undefined,
      timestampStart: start || undefined,
      timestampEnd: end || undefined,
      pageNo: query.pageNo,
      pageSize: query.pageSize,
      orderBy: 'timestamp desc',
    }
  }

  async function load() {
    const data = await commandLogApi.load(buildQueryParams())
    rows.value = data?.list || []
    total.value = data?.totalCount || 0
  }

  function reset() {
    query.deviceIdFuzzy = ''
    query.eventTypeFuzzy = ''
    query.timeRange = []
    query.pageNo = 1
    load()
  }

  onMounted(() => {
    load()
  })

  return { query, rows, total, load, reset }
}

export { useDevOpsLogs }