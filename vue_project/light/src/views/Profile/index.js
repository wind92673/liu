import { computed, onMounted, reactive, ref } from 'vue'
import { ElMessage } from 'element-plus'
import { accountApi, userInfoApi } from '../../api'
import { getStoredUser, setStoredUser } from '../../utils/auth'

function useProfile() {
  const userData = ref(getStoredUser())

  const user = computed(() => userData.value)

  const editVisible = ref(false)
  const loading = ref(false)
  const editForm = reactive({
    nickName: '',
    oldPassword: '',
    newPassword: '',
    confirmPassword: '',
  })

  const rightLabel = computed(() => {
    const r = Number(user.value?.right)
    if (r === 2) return '管理员'
    if (r === 1) return '运维人员'
    return '普通用户'
  })

  async function loadUserInfo() {
    if (!userData.value?.userId) return
    try {
      const data = await userInfoApi.load({ userId: userData.value.userId, pageNo: 1, pageSize: 1 })
      if (data?.list?.[0]) {
        const fullUser = { ...userData.value, ...data.list[0] }
        userData.value = fullUser
        setStoredUser(fullUser)
      }
    } catch (e) {
      console.error('加载用户信息失败', e)
    }
  }

  function openEditModal() {
    editForm.nickName = user.value?.nickName || ''
    editForm.oldPassword = ''
    editForm.newPassword = ''
    editForm.confirmPassword = ''
    editVisible.value = true
  }

  async function saveProfile() {
    if (!editForm.nickName.trim()) {
      ElMessage.warning('请输入昵称')
      return
    }
    
    if (editForm.newPassword) {
      if (!editForm.oldPassword) {
        ElMessage.warning('请输入原密码')
        return
      }
      if (editForm.newPassword !== editForm.confirmPassword) {
        ElMessage.warning('两次输入的密码不一致')
        return
      }
    }

    loading.value = true
    try {
      if (editForm.newPassword) {
        await accountApi.changePassword({
          userId: user.value.userId,
          oldPassword: editForm.oldPassword,
          newPassword: editForm.newPassword,
        })
      }

      if (editForm.nickName !== user.value?.nickName) {
        await userInfoApi.updateByUserId({ 
          userId: user.value.userId, 
          nickName: editForm.nickName,
        })
      }

      const updatedUser = { ...userData.value, nickName: editForm.nickName }
      userData.value = updatedUser
      setStoredUser(updatedUser)
      
      ElMessage.success('保存成功')
      editVisible.value = false
    } finally {
      loading.value = false
    }
  }

  onMounted(async () => {
    await loadUserInfo()
  })

  return {
    user,
    rightLabel,
    editVisible,
    loading,
    editForm,
    openEditModal,
    saveProfile,
  }
}

export { useProfile }