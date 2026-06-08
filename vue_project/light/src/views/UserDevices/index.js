import { onMounted, reactive, ref } from 'vue'
import { ElMessage } from 'element-plus'
import { deviceInfoApi, reportLightApi, userDeviceBoundApi } from '../../api'
import { getStoredUser } from '../../utils/auth'

function useUserDevices() {
  const rows = ref([])
  const brightnessMap = reactive({})
  const ambientLightMap = reactive({})
  const loading = ref(false)

  const editVisible = ref(false)
  const editLoading = ref(false)
  const editForm = reactive({
    deviceId: '',
    systemMode: 0,
    bright: 0,
  })

  async function loadBrightness(deviceId) {
    const data = await reportLightApi.load({ deviceId, pageNo: 1, pageSize: 1, orderBy: 'report_time desc' })
    const first = (data?.list || [])[0]
    brightnessMap[deviceId] = first?.brightNess ?? '-'
    ambientLightMap[deviceId] = first?.ambientLight ?? '-'
    return first?.brightNess ?? 0
  }

  async function loadDeviceInfo(deviceId) {
    const data = await deviceInfoApi.load({ deviceId, pageNo: 1, pageSize: 1 })
    return (data?.list || [])[0] || null
  }

  async function load() {
    const me = getStoredUser()
    const bound = await userDeviceBoundApi.load({ userId: me?.userId || '', pageNo: 1, pageSize: 200 })
    const deviceIds = (bound?.list || []).map((b) => b.deviceId).filter(Boolean)

    const infos = await Promise.allSettled(deviceIds.map((id) => loadDeviceInfo(id)))
    const list = infos.map((x) => (x.status === 'fulfilled' ? x.value : null)).filter(Boolean)

    rows.value = list
    await Promise.allSettled(deviceIds.map((id) => loadBrightness(id)))
  }

  async function openEdit(row) {
    editForm.deviceId = row.deviceId
    editForm.systemMode = Number(row.systemMode) || 0
    const bright = await loadBrightness(row.deviceId)
    editForm.bright = Number(bright) || 0
    editVisible.value = true
  }

  async function save() {
    if (!editForm.deviceId) return
    editLoading.value = true
    try {
      await deviceInfoApi.updateMode({ deviceId: editForm.deviceId, mode: editForm.systemMode })
      if (editForm.systemMode === 1) {
        await deviceInfoApi.updateBright({ deviceId: editForm.deviceId, bright: editForm.bright })
      }
      ElMessage.success('更新成功')
      editVisible.value = false
      loading.value = true
      await new Promise((resolve) => setTimeout(resolve, 1000))
      await load()
      loading.value = false
    } finally {
      editLoading.value = false
    }
  }

  onMounted(() => {
    load()
  })

  return { rows, brightnessMap, ambientLightMap, loading, load, editVisible, editLoading, editForm, openEdit, save }
}

export { useUserDevices }