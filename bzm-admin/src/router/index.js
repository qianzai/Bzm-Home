import Vue from "vue";
import VueRouter from "vue-router";
import Login from "../views/Login/Login";
import Home from "../views/Home";
import Test from "../components/Test";
import User from "../views/admin/User";
import Register from "../views/Login/Register";

Vue.use(VueRouter);

const routes = [
  { path: "/", redirect: "/login" },
  // { path: "/register", component: Register },

  { path: "/login", component: Login,
      children: [
          { path: "/register", component: Register },
      ],},
  {
    path: "/home",
    component: Home,
    redirect: "/test",
    children: [
      { path: "/test", component: Test },
      { path: "/user", component: User },
    ],
  },
];

const router = new VueRouter({
  routes,
});

/*挂载路由导航守卫*/
// router.beforeEach((to, from, next) => {
//     // to:将要访问的路径
//     // from:从哪里访问的路径
//     // next:之后要做的任务，是一个函数
//
// });

export default router;
