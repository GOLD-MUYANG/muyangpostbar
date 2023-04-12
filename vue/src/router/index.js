import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from "@/views/Login";
import user from "@/views/manager_view/fun/user";

Vue.use(VueRouter)


const routes = [
    {
        path: '/',
        name: 'login',
        component: Login
    },
    {
        path: '/error',
        name: 'error',
        component: ()=>import("../views/error")
    },
    {
        path: "/login",
        name: "login",
        component: Login
    },
    {
        path: "/regist",
        name: "regist",
        component: () => import("../views/registView")
    },
    {
        path: "/index",
        name: "index",
        component: () => import("../views/user_view/IndexView"),
        children: [
            {
                path: "barView",
                name: "barView",
                components: {
                    indexView: () => import("../views/user_view/bar/barView")
                }
            },
            {
                path: "new",
                name: "new",
                components: {
                    indexView: () => import("../views/user_view/fun/new")
                }
            },
            {
                path: "myBar",
                name: "myBar",
                components: {
                    indexView: () => import("../views/user_view/fun/myBar")
                }
            },
            {
                path:"hotInvitation",
                name:"hotInvitation",
                components: {
                    indexView:()=>import("../views/user_view/fun/hotInvitation")
                }
            },
            {
                name:'userSelfInfo',
                path: "userSelfInfo",
                components: {
                    indexView:()=>import("../views/user_view/fun/userSelfInfo")
                }
            },
            {
                name:'ann',
                path: "ann",
                components: {
                    indexView:()=>import("../views/user_view/fun/ann")
                }
            }
        ],
        //有了这一个代表虽然跳转到/index，但是重定向给/index/new
        redirect: "/index/ann"
    },
    {
        path: "/manage",
        name: "manage",
        component: () => import("../views/manager_view/managerView"),
        children: [
            {
                path: "welcome",
                name: "welcome",
                components: {
                    fun: () => import("../views/manager_view/fun/welcome")
                }
            },
            {
                path: "user",
                name: "user",
                components: {
                    fun: () => import("../views/manager_view/fun/user")
                }
            },
            {
                path: "ann",
                name: "ann",
                components: {
                    fun: () => import("../views/manager_view/fun/ann")
                }
            },
            {
                path: "comment",
                name: "comment",
                components: {
                    fun: () => import("../views/manager_view/fun/comment")
                }
            },
            {
                path: "bar",
                name: "bar",
                components: {
                    fun: () => import("../views/manager_view/fun/bar")
                }
            },
            {
                path: "invitation",
                name: "invitation",
                components: {
                    fun: () => import("../views/manager_view/fun/invitation")
                }
            },
        ],
        //有了这一个代表虽然跳转到/managerView，但是重定向给/managerView/fun1
        redirect: "/manage/welcome"
    },

]
const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

router.beforeEach((to, from, next) => {
    //获取token
    const managerLogging = window.sessionStorage.getItem('managerLogging')

    if (to.name === 'login') {
        next()
    }
    if ((to.path === "/manage/welcome" ||
            to.path === "/manage/user" ||
            to.path === "/manage/ann" ||
            to.path === "/manage/comment" ||
            to.path === "/manage/bar"||
            to.path === "/manage/invitation"
        ) &&
        managerLogging === null
    ) {
        next("/")
    } else {
        next()
    }

    if (to.name === "regist") {
        next()
    }
    next()
    if (to.matched.length === 0) {  // 如果未匹配到路由
        from.name ? next({ name: from.name }) : next('/error')
    } else {
        next()  // 如果匹配到正确跳转
    }
})

export default router

