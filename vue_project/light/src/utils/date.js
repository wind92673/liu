export function pad2(n) {
  return String(n).padStart(2, '0')
}

export function formatDate(date) {
  const d = date instanceof Date ? date : new Date(date)
  return `${d.getFullYear()}-${pad2(d.getMonth() + 1)}-${pad2(d.getDate())}`
}

export function formatDateTime(date) {
  const d = date instanceof Date ? date : new Date(date)
  return `${formatDate(d)} ${pad2(d.getHours())}:${pad2(d.getMinutes())}:${pad2(d.getSeconds())}`
}

