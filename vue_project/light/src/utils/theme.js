const THEME_KEY = 'light_theme'

export const themes = {
  LIGHT: 'light',
  DARK: 'dark',
}

export function getTheme() {
  const stored = localStorage.getItem(THEME_KEY)
  return stored || themes.LIGHT
}

export function setTheme(theme) {
  localStorage.setItem(THEME_KEY, theme)
  applyTheme(theme)
}

export function applyTheme(theme) {
  document.documentElement.setAttribute('data-theme', theme)
}

export function toggleTheme() {
  const current = getTheme()
  const next = current === themes.LIGHT ? themes.DARK : themes.LIGHT
  setTheme(next)
  return next
}