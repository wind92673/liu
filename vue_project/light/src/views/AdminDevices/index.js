import { onMounted, reactive, ref } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { deviceGroupBoundApi, deviceInfoApi, groupInfoApi, reportLightApi } from '../../api'
import { formatDateTime } from '../../utils/date'

function useAdminDevices() {
  const query = reactive({
    deviceIdFuzzy: '',
    online: undefined,
    groupId: '',
    pageNo: 1,
    pageSize: 15,
  })

  const rows = ref([])
  const total = ref(0)

  const groupOptions = ref([])
  const groupMap = reactive({})
  const brightnessMap = reactive({})

  const editVisible = ref(false)
  const editLoading = ref(false)
  const isCreate = ref(false)
  const editForm = reactive({
    deviceId: '',
    systemMode: 0,
    lightThreshold: 400,
    dayStart: 8,
    dayEnd: 18,
    online: 0,
    reportInterval: 60,
    groupId: '',
  })

  function groupName(groupId) {
    if (!groupId) return '-'
    const g = groupOptions.value.find((x) => x.groupId === groupId)
    return g?.groupName || groupId
  }

  function buildQueryParams() {
    return {
      deviceIdFuzzy: query.deviceIdFuzzy || undefined,
      online: query.online === undefined ? undefined : query.online,
      pageNo: query.pageNo,
      pageSize: query.pageSize,
      orderBy: 'device_id asc',
    }
  }

  async function loadGroups() {
    const data = await groupInfoApi.load({ pageNo: 1, pageSize: 200, orderBy: 'create_time desc' })
    groupOptions.value = data?.list || []
  }

  async function loadGroupMap() {
    const data = await deviceGroupBoundApi.load({ pageNo: 1, pageSize: 500, orderBy: 'create_time desc' })
    const list = data?.list || []
    for (const k of Object.keys(groupMap)) delete groupMap[k]
    for (const item of list) {
      if (item.deviceId && item.groupId && groupMap[item.deviceId] === undefined) {
        groupMap[item.deviceId] = item.groupId
      }
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
    await loadGroups()
    await loadGroupMap()
    const data = await deviceInfoApi.load(buildQueryParams())
    let list = data?.list || []
    if (query.groupId) {
      list = list.filter((d) => groupMap[d.deviceId] === query.groupId)
    }
    rows.value = list
    total.value = data?.totalCount || 0
    await loadBrightness(list)
  }

  function reset() {
    query.deviceIdFuzzy = ''
    query.online = undefined
    query.groupId = ''
    query.pageNo = 1
    load()
  }

  function openCreate() {
    isCreate.value = true
    editForm.deviceId = ''
    editForm.systemMode = 0
    editForm.lightThreshold = 400
    editForm.dayStart = 8
    editForm.dayEnd = 18
    editForm.online = 0
    editForm.reportInterval = 60
    editForm.groupId = ''
    editVisible.value = true
  }

  function openEdit(row) {
    isCreate.value = false
    editForm.deviceId = row.deviceId
    editForm.systemMode = Number(row.systemMode) || 0
    editForm.lightThreshold = Number(row.lightThreshold) || 0
    editForm.dayStart = Number(row.dayStart) || 0
    editForm.dayEnd = Number(row.dayEnd) || 0
    editForm.online = Number(row.online) || 0
    editForm.reportInterval = Number(row.reportInterval) || 0
    editForm.groupId = groupMap[row.deviceId] || ''
    editVisible.value = true
  }

  async function updateGroupBound(deviceId, groupId) {
    const data = await deviceGroupBoundApi.load({ deviceId, pageNo: 1, pageSize: 50 })
    const list = data?.list || []
    for (const item of list) {
      await deviceGroupBoundApi.deleteById({ id: item.id })
    }
    if (groupId) {
      await deviceGroupBoundApi.add({
        deviceId,
        groupId,
        createTime: formatDateTime(new Date()),
      })
    }
  }

  async function save() {
    if (!editForm.deviceId) {
      ElMessage.warning('设备ID不能为空')
      return
    }
    editLoading.value = true
    try {
      const payload = {
        deviceId: editForm.deviceId,
        systemMode: editForm.systemMode,
        lightThreshold: editForm.lightThreshold,
        dayStart: editForm.dayStart,
        dayEnd: editForm.dayEnd,
        reportInterval: editForm.reportInterval,
      }
      if (isCreate.value) {
        await deviceInfoApi.add({ ...payload, online: 0 })
      } else {
        await deviceInfoApi.updateByDeviceId(payload)
      }
      await updateGroupBound(editForm.deviceId, editForm.groupId)
      ElMessage.success('保存成功')
      editVisible.value = false
      load()
    } finally {
      editLoading.value = false
    }
  }

  async function remove(row) {
    await ElMessageBox.confirm('确认删除该设备？', '提示', { type: 'warning' })
    await deviceInfoApi.deleteByDeviceId({ deviceId: row.deviceId })
    await updateGroupBound(row.deviceId, '')
    ElMessage.success('删除成功')
    load()
  }

  onMounted(() => {
    load()
  })

  return {
    query,
    rows,
    total,
    groupOptions,
    groupMap,
    brightnessMap,
    load,
    reset,
    groupName,
    editVisible,
    editLoading,
    isCreate,
    editForm,
    openCreate,
    openEdit,
    save,
    remove,
  }
}

export { useAdminDevices }