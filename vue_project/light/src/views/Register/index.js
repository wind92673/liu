import { reactive, ref } from 'vue'
import { ElMessage } from 'element-plus'
import { useRouter } from 'vue-router'
import { accountApi } from '../../api'

function useRegister() {
  const router = useRouter()

  const form = reactive({
    nickName: '',
    phone: '',
    password: '',
  })

  const loading = ref(false)

  async function onSubmit() {
    if (!form.nickName || !form.phone || !form.password) {
      ElMessage.warning('请完整填写注册信息')
      return
    }
    loading.value = true
    try {
      await accountApi.register({
        nickName: form.nickName,
        phone: form.phone,
        password: form.password,
      })
      ElMessage.success('注册成功，请登录')
      router.replace('/login')
    } finally {
      loading.value = false
    }
  }

  function toLogin() {
    router.push('/login')
  }

  return { form, loading, onSubmit, toLogin }
}

export { useRegister }