import Vue from 'vue';
import Router from 'vue-router';
import Login from '../views/Login.vue';
import Dashboard from '../views/Dashboard.vue';

Vue.use(Router);

const router = new Router({
  routes: [
    { path: '/', redirect: '/login' },
    { path: '/login', component: Login },
    { path: '/dashboard', component: Dashboard }
  ]
});

router.beforeEach((to, from, next) => {
  const user = localStorage.getItem('meeting_user');
  if (to.path !== '/login' && !user) return next('/login');
  next();
});

export default router;
