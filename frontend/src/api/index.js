import request from '../utils/request';

export const login = data => request.post('/auth/login', data);
export const register = data => request.post('/auth/register', data);
export const pageApi = (module, params) => request.get(`/${module}/page`, { params });
export const addApi = (module, data) => request.post(`/${module}`, data);
export const updateApi = (module, data) => request.put(`/${module}`, data);
export const deleteApi = (module, id) => request.delete(`/${module}/${id}`);
export const detailApi = (module, id) => request.get(`/${module}/${id}`);
export const homeStatsApi = () => request.get('/home/stats');
export const approveBookingApi = (id, status, remark, approveUserId) => request.post(`/booking/approve/${id}`, null, { params: { status, remark, approveUserId } });
