export const USER_STORAGE_KEY = 'light_user'

export function getStoredUser() {
  const raw = localStorage.getItem(USER_STORAGE_KEY)
  if (!raw) return null
  try {
    return JSON.parse(raw)
  } catch {
    return null
  }
}

export function setStoredUser(user) {
  localStorage.setItem(USER_STORAGE_KEY, JSON.stringify(user))
}

export function clearStoredUser() {
  localStorage.removeItem(USER_STORAGE_KEY)
}

export function getToken() {
  const user = getStoredUser()
  return user?.token || ''
}

export function getRight() {
  const user = getStoredUser()
  const raw = user?.right
  const n = raw === undefined || raw === null ? NaN : Number(raw)
  return Number.isNaN(n) ? null : n
}

