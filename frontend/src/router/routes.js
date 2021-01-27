import store from "@/store";

import DashboardLayout from "@/layout/dashboard/DashboardLayout.vue";

// GeneralViews
import NotFound from "@/pages/NotFoundPage.vue";

// Admin pages
import Dashboard from "@/pages/Dashboard.vue";

// Q&A pages
import QnaList from '@/pages/qna/QnaList.vue';
import QuestionForm from '@/pages/qna/QuestionForm';
import QnaRead from '@/pages/qna/QnaRead';
import QuestionDelete from '@/pages/qna/QuestionDelete';
import QuestionDetail from '@/components/qna/QuestionDetail';
import AnswerDetail from '@/components/qna/AnswerDetail';
import QuestionUpdate from '@/pages/qna/QuestionUpdate';

// search pages
import SearchHouse from '@/pages/search/House';

// user pages
import Login from "@/pages/Login.vue";
import Register from "@/pages/user/Register.vue";
import Profile from "@/pages/user/Profile.vue";
import Update from "@/pages/user/Update.vue";


// https://router.vuejs.org/kr/guide/advanced/navigation-guards.html
const requireAuth = () => (to, from, next) => {
  const nextRoute = to.path;

  if (store.getters.getAccessToken || sessionStorage.getItem('auto-token')) {
    return next();
  } else next("/login");
};

const routes = [
  {
    path: "/",
    component: DashboardLayout,
    redirect: "/dashboard",
    children: [
      
  {
    path: "/login",
    name: "Login",
    component: Login,
    // beforeEnter: requireAuth()
      },
      
  {
    path: "/login/:nextRoute",
    name: "LoginNextRoute",
    component: Login
  },
      {
        path: "dashboard",
        name: "dashboard",
        component: Dashboard
      },
      {
        path: '/qna',
        name: 'QnaList',
        redirect: '/qna/list',
        component: QnaList,
      },
      {
        path: '/qna/list',
        name: 'QnaList',
        component: QnaList,
      },
      {
        path: '/qna/read',
        name: 'QnaRead',
        component: QnaRead,
      },
      {
        path: '/qna/regist',
        name: 'QuestionForm',
        component: QuestionForm,
      },
      {
        path: '/qna/qdetail',
        name: 'QuestionDetail',
        component: QuestionDetail,
      },
      {
        path: '/qna/delete',
        name: 'QuestionDelete',
        component: QuestionDelete,
      },
      {
        path: '/qna/update',
        name: 'QuestionUpdate',
        component: QuestionUpdate,
      },
      {
        path: '/qna/adetail',
        name: 'AnswerDetail',
        component: AnswerDetail,
      },
      {
        path: '/search/house',
        name: 'SearchHouse',
        component: SearchHouse,
      },
      {
        path: "/user/register",
        name: "Register",
        component: Register,
      },
      {
        path: "/user/profile",
        name: "Profile",
        component: Profile,
        beforeEnter: requireAuth()
      },      {
        path: "/user/update",
        name: "Update",
        component: Update,
        beforeEnter: requireAuth()
      },
    ]
  },
  { path: "*", component: NotFound }
];

/**
 * Asynchronously load view (Webpack Lazy loading compatible)
 * The specified component must be inside the Views folder
 * @param  {string} name  the filename (basename) of the view to load.
function view(name) {
   var res= require('../components/Dashboard/Views/' + name + '.vue');
   return res;
};**/

export default routes;
