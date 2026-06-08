import axios from 'axios'
import { ElMessage } from 'element-plus'
import router from '../router'
import { clearStoredUser, getToken } from '../utils/auth'

const http = axios.create({
  baseURL: 'http://39.106.201.158:5050/api',
  timeout: 15000,
})

http.interceptors.request.use((config) => {
  const token = getToken()
  if (token) {
    config.headers = config.headers || {}
    config.headers.token = token
  }
  return config
})

http.interceptors.response.use(
  (res) => {
    if (res?.config?.responseType === 'blob') return res.data
    const body = res?.data
    if (!body) return body
    if (body.status === 'success') return body.data

    const code = body.code
    const info = body.info || '请求失败'
    if (code === 901) {
      clearStoredUser()
      if (router.currentRoute.value.path !== '/login') {
        router.replace('/login')
      }
    }
    ElMessage.error(info)
    return Promise.reject(body)
  },
  (err) => {
    ElMessage.error('网络错误')
    return Promise.reject(err)
  },
)

export function postParams(url, params) {
  return http.post(url, null, { params })
}

export function get(url, params) {
  return http.get(url, { params })
}

export function download(url, params) {
  return http.get(url, { params, responseType: 'blob' })
}

export default http
