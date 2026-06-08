import { reactive, ref } from 'vue'
import { ElMessage } from 'element-plus'
import { useRouter } from 'vue-router'
import { accountApi } from '../../api'
import { setStoredUser } from '../../utils/auth'

function useLogin() {
  const router = useRouter()

  const form = reactive({
    phone: '',
    password: '',
  })

  const loading = ref(false)

  async function onSubmit() {
    if (!form.phone || !form.password) {
      ElMessage.warning('请输入手机号和密码')
      return
    }
    loading.value = true
    try {
      const user = await accountApi.login({ phone: form.phone, password: form.password })
      setStoredUser(user)
      router.replace('/home')
    } finally {
      loading.value = false
    }
  }

  function toRegister() {
    router.push('/register')
  }

  return { form, loading, onSubmit, toRegister }
}

export { useLogin }