import { download, get, postParams } from './http'

export const accountApi = {
  login: (params) => postParams('/account/login', params),
  register: (params) => postParams('/account/register', params),
  setRight: (params) => postParams('/account/setRight', params),
  changePassword: (params) => postParams('/account/changePassword', params),
}

export const userInfoApi = {
  load: (params) => get('/userInfo/loadDataList', params),
  updateByUserId: (params) => postParams('/userInfo/updateUserInfoByUserId', params),
  deleteByUserId: (params) => postParams('/userInfo/deleteUserInfoByUserId', params),
}

export const deviceInfoApi = {
  load: (params) => get('/deviceInfo/loadDataList', params),
  add: (params) => postParams('/deviceInfo/add', params),
  updateByDeviceId: (params) => postParams('/deviceInfo/updateDeviceInfoByDeviceId', params),
  deleteByDeviceId: (params) => postParams('/deviceInfo/deleteDeviceInfoByDeviceId', params),
  updateMode: (params) => postParams('/deviceInfo/updateDeviceModeByDeviceId', params),
  updateBright: (params) => postParams('/deviceInfo/updateDeviceBrightByDeviceId', params),
  countOnline: (params) => get('/deviceInfo/countOnline', params),
}

export const reportLightApi = {
  load: (params) => get('/reportLight/loadDataList', params),
}

export const groupInfoApi = {
  load: (params) => get('/groupInfo/loadDataList', params),
  add: (params) => postParams('/groupInfo/add', params),
  updateByGroupId: (params) => postParams('/groupInfo/updateGroupInfoByGroupId', params),
  deleteByGroupId: (params) => postParams('/groupInfo/deleteGroupInfoByGroupId', params),
}

export const deviceGroupBoundApi = {
  load: (params) => get('/deviceGroupBound/loadDataList', params),
  add: (params) => postParams('/deviceGroupBound/add', params),
  deleteById: (params) => postParams('/deviceGroupBound/deleteDeviceGroupBoundById', params),
}

export const userDeviceBoundApi = {
  load: (params) => get('/userDeviceBound/loadDataList', params),
  add: (params) => postParams('/userDeviceBound/add', params),
  deleteById: (params) => postParams('/userDeviceBound/deleteUserDeviceBoundById', params),
}

export const saveInfoApi = {
  getListByDay: (params) => get('/saveInfo/getListByDay', params),
  getMonthlySummary: (params) => get('/saveInfo/getMonthlySummary', params),
  getYearlySummary: (params) => get('/saveInfo/getYearlySummary', params),
  exportExcel: (params) => download('/saveInfo/exportExcel', params),
}

export const warnOrderApi = {
  load: (params) => get('/warnOrder/loadDataList', params),
  updateById: (params) => postParams('/warnOrder/updateWarnOrderById', params),
  countUnprocessed: (params) => get('/warnOrder/countUnprocessed', params),
}

export const resultInfoApi = {
  add: (params) => postParams('/resultInfo/add', params),
  load: (params) => get('/resultInfo/loadDataList', params),
}

export const commandLogApi = {
  load: (params) => get('/commandLog/loadDataList', params),
}

