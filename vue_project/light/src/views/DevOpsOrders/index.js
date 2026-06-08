import { onMounted, reactive, ref } from 'vue'
import { ElMessage } from 'element-plus'
import { resultInfoApi, warnOrderApi } from '../../api'
import { getStoredUser } from '../../utils/auth'
import { formatDateTime } from '../../utils/date'

function useDevOpsOrders() {
  const query = reactive({
    deviceIdFuzzy: '',
    state: undefined,
    pageNo: 1,
    pageSize: 15,
  })

  const rows = ref([])
  const total = ref(0)

  const handleVisible = ref(false)
  const handleLoading = ref(false)
  const handleForm = reactive({
    id: null,
    info: '',
  })

  const resultVisible = ref(false)
  const resultLoading = ref(false)
  const resultInfo = ref('')

  function buildQueryParams() {
    return {
      deviceIdFuzzy: query.deviceIdFuzzy || undefined,
      state: query.state === undefined ? undefined : query.state,
      pageNo: query.pageNo,
      pageSize: query.pageSize,
      orderBy: 'report_time desc',
    }
  }

  async function load() {
    const data = await warnOrderApi.load(buildQueryParams())
    rows.value = data?.list || []
    total.value = data?.totalCount || 0
  }

  function reset() {
    query.deviceIdFuzzy = ''
    query.state = undefined
    query.pageNo = 1
    load()
  }

  function openHandle(row) {
    handleForm.id = row.id
    handleForm.info = ''
    handleVisible.value = true
  }

  async function handle() {
    if (!handleForm.id) return
    handleLoading.value = true
    try {
      const me = getStoredUser()
      const now = formatDateTime(new Date())
      await resultInfoApi.add({
        orderId: handleForm.id,
        userId: me?.userId || '',
        info: handleForm.info,
        repairTime: now,
      })
      await warnOrderApi.updateById({
        id: handleForm.id,
        state: 1,
        userId: me?.userId || '',
        repairTime: now,
      })
      ElMessage.success('处理成功')
      handleVisible.value = false
      load()
    } finally {
      handleLoading.value = false
    }
  }

  async function openResult(orderId) {
    resultLoading.value = true
    try {
      const data = await resultInfoApi.load({ orderId, pageNo: 1, pageSize: 1 })
      resultInfo.value = (data?.list?.[0]?.info) || '暂无处理信息'
      resultVisible.value = true
    } finally {
      resultLoading.value = false
    }
  }

  onMounted(() => {
    load()
  })

  return { query, rows, total, load, reset, handleVisible, handleLoading, handleForm, openHandle, handle, resultVisible, resultLoading, resultInfo, openResult }
}

export { useDevOpsOrders }