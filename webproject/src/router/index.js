import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'
import Index from '../views/Index.vue'
import Home from '../views/Admin/Home.vue'
import Companyaudit from '../views/Admin/Companyaudit.vue'
import Companyaudit1 from '../views/Admin/Companyaudit1.vue'
import Busmanage from '../views/Admin/Busmanage.vue'
import Blinfotrack from '../views/Admin/Blinfotrack.vue'
import Logoaudit from '../views/Admin/Logoaudit.vue'
import Portinfomanage from '../views/Admin/Portinfomanage.vue'

import Home1 from '../views/User/Home.vue'
import Accmanage from '../views/User/Accmanage.vue'
import Cominfomanage from '../views/User/Cominfomanage.vue'
import Rolemanage from '../views/User/Rolemanage.vue'
import Pinfomodify from '../views/User/Pinfomodify.vue'
import Sbaudit from '../views/User/Send/Sbaudit.vue'
import Sbapply from '../views/User/Send/Sbapply.vue'
import Logomanage from '../views/User/Send/Logomanage.vue'
import Printapply from '../views/User/Send/Printapply.vue'
import Busapply from "../views/User/Busapply.vue";
import Signaudit from "../views/User/Signature/Signaudit.vue";
import Recording from "../views/User/Receive/Recording";
import RecordApply from "../views/User/Receive/RecordApply";
import Receaudit from "../views/User/Receive/Receaudit";
import Sendapply from "../views/User/Receive/Sendapply";
import Blrece from "../views/User/Receive/Blrece";
import Record from "../views/Record";
import Voidaudit from "../views/Admin/Voidaudit";
import Record2 from "../views/Record2";
import Print from "../views/Admin/Print";
import Mail from "../views/Admin/Mail";
import Pay from "../views/Pay";
import Kong from "../views/Kong";
import DateV from "../views/DateV";
import Index1 from "../views/Index1";
import Index2 from "../views/Index2";


Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    component: Login
  },
  {
    path:'/kong',
    component:Kong
  },
  {
    path: '/Register',
    component:Register
  },
  {
    path: '/Index1',//用户
    component: Index1,
    meta: {
      isLogin: true    // 添加该字段，表示进入这个路由是需要登录的
    },//路由元
    children:[
      {
        path:'/',
        redirect:'home1'
      },
      {
        path:'companyaudit',
        component:Companyaudit
      },
      {
        path:'companyaudit1',
        component:Companyaudit1
      },
      {
        path:'busmanage',
        component:Busmanage
      },
      {
        path:'blinfotrack',
        component:Blinfotrack
      },
      {
        path:'logoaudit',
        component:Logoaudit
      },
      {
        path:'portinfomanage',
        component:Portinfomanage
      },

      {
        path:'home1',
        component:Home1
      },
      {
        path:'cominfomanage',
        component:Cominfomanage
      },
      {
        path:'accmanage',
        component:Accmanage
      },
      {
        path:'rolemanage',
        component:Rolemanage
      },
      {
        path:'pinfomodify',
        component:Pinfomodify
      },
      {
        path:'sbaudit',
        component:Sbaudit
      },
      {
        path:'sbapply',
        component:Sbapply
      },
      {
        path:'logomanage',
        component:Logomanage
      },
      {
        path:'printapply',
        component:Printapply
      },
      {
        path:'busapply',
        component:Busapply
      },
      {
        path:'signaudit',
        component:Signaudit
      },
      {
        path:'recording',
        component:Recording
      },
      {
        path:'recordapply',
        component:RecordApply
      },
      {
        path:'receaudit',
        component:Receaudit
      },
      {
        path:'sendapply',
        component:Sendapply
      },
      {
        path:'blrece',
        component:Blrece
      },
      {
        path:'record',
        component:Record
      },
      {
        path:'record2',
        component:Record2
      },
      {
        path:'voidaudit',
        component:Voidaudit
      },
      {
        path:'printaudit',
        component:Print
      },
      {
        path:'blsend',
        component:Mail
      },
      {
        path:'pay',
        component:Pay
      },
      {
        path:'datev',
        component:DateV
      }
    ]
  },
  {
    path: '/Index2',//管理员
    component: Index2,
    meta: {
      isLogin: true    // 添加该字段，表示进入这个路由是需要登录的
    },//路由元
    children:[
      {
        path:'',
        redirect:'home'
      },
      {
        path:'home',
        component:Home
      },
      {
        path:'companyaudit',
        component:Companyaudit
      },
      {
        path:'companyaudit1',
        component:Companyaudit1
      },
      {
        path:'busmanage',
        component:Busmanage
      },
      {
        path:'blinfotrack',
        component:Blinfotrack
      },
      {
        path:'logoaudit',
        component:Logoaudit
      },
      {
        path:'portinfomanage',
        component:Portinfomanage
      },

      {
        path:'home1',
        component:Home1
      },
      {
        path:'cominfomanage',
        component:Cominfomanage
      },
      {
        path:'accmanage',
        component:Accmanage
      },
      {
        path:'rolemanage',
        component:Rolemanage
      },
      {
        path:'pinfomodify',
        component:Pinfomodify
      },
      {
        path:'sbaudit',
        component:Sbaudit
      },
      {
        path:'sbapply',
        component:Sbapply
      },
      {
        path:'logomanage',
        component:Logomanage
      },
      {
        path:'printapply',
        component:Printapply
      },
      {
        path:'busapply',
        component:Busapply
      },
      {
        path:'signaudit',
        component:Signaudit
      },
      {
        path:'recording',
        component:Recording
      },
      {
        path:'recordapply',
        component:RecordApply
      },
      {
        path:'receaudit',
        component:Receaudit
      },
      {
        path:'sendapply',
        component:Sendapply
      },
      {
        path:'blrece',
        component:Blrece
      },
      {
        path:'record',
        component:Record
      },
      {
        path:'record2',
        component:Record2
      },
      {
        path:'voidaudit',
        component:Voidaudit
      },
      {
        path:'printaudit',
        component:Print
      },
      {
        path:'blsend',
        component:Mail
      },
      {
        path:'pay',
        component:Pay
      },
      {
        path:'kong',
        component:Kong
      },
      {
        path:'datev',
        component:DateV
      }
    ]
  },
  {
    path:'/Index',
    component:Index,
    meta:{
      isLogin:true    // 添加该字段，表示进入这个路由是需要登录的
    },//路由元
    children:[
      {
        path:'home',
        component:Home
      },
      {
        path:'companyaudit',
        component:Companyaudit
      },
      {
        path:'companyaudit1',
        component:Companyaudit1
      },
      {
        path:'busmanage',
        component:Busmanage
      },
      {
        path:'blinfotrack',
        component:Blinfotrack
      },
      {
        path:'logoaudit',
        component:Logoaudit
      },
      {
        path:'portinfomanage',
        component:Portinfomanage
      },

      {
        path:'home1',
        component:Home1
      },
      {
        path:'cominfomanage',
        component:Cominfomanage
      },
      {
        path:'accmanage',
        component:Accmanage
      },
      {
        path:'rolemanage',
        component:Rolemanage
      },
      {
        path:'pinfomodify',
        component:Pinfomodify
      },
      {
        path:'sbaudit',
        component:Sbaudit
      },
      {
        path:'sbapply',
        component:Sbapply
      },
      {
        path:'logomanage',
        component:Logomanage
      },
      {
        path:'printapply',
        component:Printapply
      },
      {
        path:'busapply',
        component:Busapply
      },
      {
        path:'signaudit',
        component:Signaudit
      },
      {
        path:'recording',
        component:Recording
      },
      {
        path:'recordapply',
        component:RecordApply
      },
      {
        path:'receaudit',
        component:Receaudit
      },
      {
        path:'sendapply',
        component:Sendapply
      },
      {
        path:'blrece',
        component:Blrece
      },
      {
        path:'record',
        component:Record
      },
      {
        path:'record2',
        component:Record2
      },
      {
        path:'voidaudit',
        component:Voidaudit
      },
      {
        path:'printaudit',
        component:Print
      },
      {
        path:'blsend',
        component:Mail
      },
      {
        path:'pay',
        component:Pay
      },
      {
        path:'kong',
        component:Kong
      },
      {
        path:'datev',
        component:DateV
      }
    ]
  }
];

const router = new VueRouter({
  mode: 'history',
  routes
});

router.beforeEach((to,from,next)=>{
  if(to.matched.some(res=>res.meta.isLogin)){//判断是否需要登录
    if (sessionStorage['account']) {
      next();
    }else{
      next({
        path:"/",
        query:{
          redirect:to.fullPath
        }
      });
    }
  }else{
    next()
  }
});

export default router;
