import { onMounted, reactive, ref } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { groupInfoApi } from '../../api'
import { formatDateTime } from '../../utils/date'
import { getStoredUser } from '../../utils/auth'

function useAdminGroups() {
  const query = reactive({
    pageNo: 1,
    pageSize: 15,
  })

  const rows = ref([])
  const total = ref(0)

  const editVisible = ref(false)
  const editLoading = ref(false)
  const isCreate = ref(false)
  const editForm = reactive({
    groupId: '',
    groupName: '',
  })

  async function load() {
    const data = await groupInfoApi.load({ pageNo: query.pageNo, pageSize: query.pageSize, orderBy: 'create_time desc' })
    rows.value = data?.list || []
    total.value = data?.totalCount || 0
  }

  function openCreate() {
    isCreate.value = true
    editForm.groupId = ''
    editForm.groupName = ''
    editVisible.value = true
  }

  function openEdit(row) {
    isCreate.value = false
    editForm.groupId = row.groupId
    editForm.groupName = row.groupName
    editVisible.value = true
  }

  async function save() {
    if (!editForm.groupId || !editForm.groupName) {
      ElMessage.warning('请填写分组ID和名称')
      return
    }
    editLoading.value = true
    try {
      if (isCreate.value) {
        const me = getStoredUser()
        await groupInfoApi.add({
          groupId: editForm.groupId,
          groupName: editForm.groupName,
          createPeople: me?.userId || '',
          createTime: formatDateTime(new Date()),
        })
      } else {
        await groupInfoApi.updateByGroupId({
          groupId: editForm.groupId,
          groupName: editForm.groupName,
        })
      }
      ElMessage.success('保存成功')
      editVisible.value = false
      load()
    } finally {
      editLoading.value = false
    }
  }

  async function remove(row) {
    await ElMessageBox.confirm('确认删除该分组？', '提示', { type: 'warning' })
    await groupInfoApi.deleteByGroupId({ groupId: row.groupId })
    ElMessage.success('删除成功')
    load()
  }

  onMounted(() => {
    load()
  })

  return { query, rows, total, load, editVisible, editLoading, isCreate, editForm, openCreate, openEdit, save, remove }
}

export { useAdminGroups }