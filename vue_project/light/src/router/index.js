import { createRouter, createWebHashHistory } from 'vue-router'
import { getStoredUser } from '../utils/auth'

const Login = () => import('../views/Login/index.vue')
const Register = () => import('../views/Register/index.vue')
const MainLayout = () => import('../views/MainLayout/index.vue')

const AdminUsers = () => import('../views/AdminUsers/index.vue')
const AdminDevices = () => import('../views/AdminDevices/index.vue')
const AdminGroups = () => import('../views/AdminGroups/index.vue')
const EnergyManage = () => import('../views/EnergyManage/index.vue')

const DevOpsOrders = () => import('../views/DevOpsOrders/index.vue')
const DevOpsMonitor = () => import('../views/DevOpsMonitor/index.vue')
const DevOpsLogs = () => import('../views/DevOpsLogs/index.vue')

const UserDevices = () => import('../views/UserDevices/index.vue')
const UserEnergy = () => import('../views/UserEnergy/index.vue')

const routes = [
  { path: '/login', component: Login, meta: { public: true } },
  { path: '/register', component: Register, meta: { public: true } },
  {
    path: '/',
    component: MainLayout,
    redirect: '/home',
    children: [
      { path: 'home', component: () => import('../views/Home/index.vue') },
      { path: 'profile', component: () => import('../views/Profile/index.vue') },
      { path: 'admin/users', component: AdminUsers, meta: { rights: [2] } },
      { path: 'admin/devices', component: AdminDevices, meta: { rights: [2] } },
      { path: 'admin/groups', component: AdminGroups, meta: { rights: [2] } },
      { path: 'energy', component: EnergyManage, meta: { rights: [2] } },
      { path: 'devops/orders', component: DevOpsOrders, meta: { rights: [1, 2] } },
      { path: 'devops/monitor', component: DevOpsMonitor, meta: { rights: [1, 2] } },
      { path: 'devops/logs', component: DevOpsLogs, meta: { rights: [1, 2] } },
      { path: 'user/devices', component: UserDevices, meta: { rights: [0] } },
      { path: 'user/energy', component: UserEnergy, meta: { rights: [0] } },
    ],
  },
]

const router = createRouter({
  history: createWebHashHistory(),
  routes,
})

router.beforeEach((to) => {
  if (to.meta.public) return true
  const user = getStoredUser()
  if (!user) return '/login'

  const rights = to.meta.rights
  if (!rights || rights.length === 0) return true

  const rightNum = Number(user.right)
  if (!rights.includes(rightNum)) return '/home'
  return true
})

export default router

