import { onMounted, reactive, ref } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { accountApi, deviceInfoApi, userDeviceBoundApi, userInfoApi } from '../../api'
import { formatDateTime } from '../../utils/date'
import { getStoredUser } from '../../utils/auth'

function useAdminUsers() {
  const query = reactive({
    nickNameFuzzy: '',
    phoneFuzzy: '',
    createRange: [],
    pageNo: 1,
    pageSize: 15,
  })

  const rows = ref([])
  const total = ref(0)

  const createVisible = ref(false)
  const createLoading = ref(false)
  const createForm = reactive({
    nickName: '',
    phone: '',
    password: '',
  })

  const editVisible = ref(false)
  const editLoading = ref(false)
  const editForm = reactive({
    userId: '',
    nickName: '',
  })

  const deviceOptions = ref([])
  const bindRows = ref([])
  const bindDeviceId = ref('')
  const editUserRight = ref(0)

  const rightVisible = ref(false)
  const rightLoading = ref(false)
  const rightForm = reactive({
    userId: '',
    right: 'USER',
  })

  function rightLabel(v) {
    const n = Number(v)
    if (n === 2) return '管理员'
    if (n === 1) return '运维人员'
    return '普通用户'
  }

  function buildQueryParams() {
    const [start, end] = query.createRange || []
    return {
      nickNameFuzzy: query.nickNameFuzzy || undefined,
      phoneFuzzy: query.phoneFuzzy || undefined,
      createTimeStart: start || undefined,
      createTimeEnd: end || undefined,
      pageNo: query.pageNo,
      pageSize: query.pageSize,
      orderBy: 'create_time desc',
    }
  }

  async function load() {
    const data = await userInfoApi.load(buildQueryParams())
    const me = getStoredUser()
    const list = data?.list || []
    rows.value = me ? list.filter((u) => u.userId !== me.userId) : list
    total.value = data?.totalCount || 0
  }

  function reset() {
    query.nickNameFuzzy = ''
    query.phoneFuzzy = ''
    query.createRange = []
    query.pageNo = 1
    load()
  }

  function openCreate() {
    createForm.nickName = ''
    createForm.phone = ''
    createForm.password = ''
    createVisible.value = true
  }

  async function createUser() {
    if (!createForm.nickName || !createForm.phone || !createForm.password) {
      ElMessage.warning('请完整填写用户信息')
      return
    }
    createLoading.value = true
    try {
      await accountApi.register({ ...createForm })
      ElMessage.success('创建成功')
      createVisible.value = false
      load()
    } finally {
      createLoading.value = false
    }
  }

  async function loadDeviceOptions() {
    const data = await deviceInfoApi.load({ pageNo: 1, pageSize: 200, orderBy: 'device_id asc' })
    deviceOptions.value = data?.list || []
  }

  async function openEdit(row) {
    editForm.userId = row.userId
    editForm.nickName = row.nickName
    editUserRight.value = Number(row.right)
    bindDeviceId.value = ''
    editVisible.value = true
    await loadDeviceOptions()
    await loadBinds(row.userId)
  }

  async function loadBinds(userId) {
    const data = await userDeviceBoundApi.load({ userId, pageNo: 1, pageSize: 200, orderBy: 'create_time desc' })
    bindRows.value = data?.list || []
  }

  async function saveEdit() {
    if (!editForm.userId) return
    editLoading.value = true
    try {
      await userInfoApi.updateByUserId({ userId: editForm.userId, nickName: editForm.nickName })
      ElMessage.success('保存成功')
      editVisible.value = false
      load()
    } finally {
      editLoading.value = false
    }
  }

  async function addBind() {
    if (!editForm.userId || !bindDeviceId.value) return
    const exists = bindRows.value.some((b) => b.deviceId === bindDeviceId.value)
    if (exists) {
      ElMessage.warning('该设备已绑定')
      return
    }
    await userDeviceBoundApi.add({
      userId: editForm.userId,
      deviceId: bindDeviceId.value,
      createTime: formatDateTime(new Date()),
    })
    ElMessage.success('绑定成功')
    loadBinds(editForm.userId)
  }

  async function removeBind(row) {
    await ElMessageBox.confirm('确认删除该绑定关系？', '提示', { type: 'warning' })
    await userDeviceBoundApi.deleteById({ id: row.id })
    ElMessage.success('删除成功')
    loadBinds(editForm.userId)
  }

  function openRight(row) {
    rightForm.userId = row.userId
    rightForm.right = Number(row.right) === 1 ? 'USER' : 'DEVOPS'
    rightVisible.value = true
  }

  async function saveRight() {
    rightLoading.value = true
    try {
      await accountApi.setRight({ userId: rightForm.userId, right: rightForm.right })
      ElMessage.success('调整成功')
      rightVisible.value = false
      load()
    } finally {
      rightLoading.value = false
    }
  }

  async function remove(row) {
    await ElMessageBox.confirm('确认删除该用户？', '提示', { type: 'warning' })
    await userInfoApi.deleteByUserId({ userId: row.userId })
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
    load,
    reset,
    rightLabel,
    createVisible,
    createLoading,
    createForm,
    openCreate,
    createUser,
    editVisible,
    editLoading,
    editForm,
    editUserRight,
    openEdit,
    saveEdit,
    deviceOptions,
    bindRows,
    bindDeviceId,
    addBind,
    removeBind,
    rightVisible,
    rightLoading,
    rightForm,
    openRight,
    saveRight,
    remove,
  }
}

export { useAdminUsers }