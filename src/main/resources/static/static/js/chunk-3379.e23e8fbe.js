(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-3379"],{KsdX:function(t,e,s){"use strict";var a=s("hSz7");s.n(a).a},PD6W:function(t,e,s){"use strict";s.r(e);var a=s("6IZw"),o=s("sY+N"),i={components:{Screenfull:a.a,ThemePicker:o.a},props:{textlogo:{type:String,default:""},levelList:{type:Array,default:function(){return[]}}},computed:{isCollapse:function(){return!this.$store.state.globaldata.sidebarOpen}},methods:{toggleSideBar:function(){this.$store.dispatch("toggleSidebar")},getDevPrjName:function(){return this.$store.state.globaldata.devprj?this.$store.state.globaldata.devprj.abbr||this.$store.state.globaldata.devprj.title:"未选择"},modifyInfo:function(){this.$router.push({path:"/userselfinfo"})},modifyPwd:function(){this.$router.push({path:"/modselfpwd"})},logout:function(){this.apiSrv.clearToken(),this.$store.dispatch("logoutUserInfo").then(function(){location.reload()})}}},n=(s("KsdX"),s("KHd+")),l=Object(n.a)(i,function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"navbar"},[s("div",{staticClass:"text-logo-corps",class:{"hidenav-logo":t.isCollapse}},[s("span",{staticClass:"text-logo",class:{"show-logo-text":t.isCollapse}},[t._v(t._s(t.textlogo))])]),t._v(" "),s("div",{staticClass:"hamburger-container",on:{click:t.toggleSideBar}},[s("svg-icon",{staticClass:"icon",attrs:{"icon-class":"list"}})],1),t._v(" "),s("span",{staticClass:"text-sys"},[t._v("红云研发平台 ( "+t._s(t.getDevPrjName())+" )")]),t._v(" "),t._l(t.levelList,function(e,a){return s("span",{key:e.name,staticStyle:{"font-size":"14px",color:"#fff","font-weight":"500"}},[s("span",0==a?[t._v(" --")]:[t._v("/")]),t._v("\n         "+t._s(e.title)+"\n      ")])}),t._v(" "),s("el-container",{staticClass:"right-menu",attrs:{direction:"horizontal"}},[s("el-tooltip",{attrs:{effect:"dark",content:t.$t("navbar.screenfull"),placement:"bottom"}},[s("screenfull",{staticClass:"screenfull"})],1),t._v(" "),s("el-tooltip",{attrs:{effect:"dark",content:t.$t("navbar.theme"),placement:"bottom"}},[s("theme-picker",{staticClass:"theme-switch ",attrs:{size:"mini"}})],1),t._v(" "),s("el-dropdown",{staticClass:"avatar-container",attrs:{trigger:"click"}},[s("el-container",{attrs:{direction:"horizontal"}},[s("span",{staticStyle:{color:"#fff","line-height":"30px","margin-right":"10px"}},[t._v(t._s(t.$store.state.globaldata.userInfo.name))]),t._v(" "),s("img",{staticStyle:{"border-radius":"15px"},attrs:{height:"30",width:"30",src:t.$store.state.globaldata.userInfo.avatar}}),t._v(" "),s("i",{staticClass:"el-icon-caret-bottom",staticStyle:{"line-height":"30px","margin-left":"6px"}})]),t._v(" "),s("el-dropdown-menu",{attrs:{slot:"dropdown"},slot:"dropdown"},[s("el-dropdown-item",[s("span",{staticStyle:{display:"block"},on:{click:t.modifyInfo}},[t._v(t._s(t.$t("navbar.modifyInfo")))])]),t._v(" "),s("el-dropdown-item",[s("span",{staticStyle:{display:"block"},on:{click:t.modifyPwd}},[t._v(t._s(t.$t("navbar.modifyPwd")))])]),t._v(" "),s("el-dropdown-item",{attrs:{divided:""}},[s("span",{staticStyle:{display:"block"},on:{click:t.logout}},[t._v(t._s(t.$t("navbar.logOut")))])])],1)],1)],1)],2)},[],!1,null,"717d6570",null);l.options.__file="Navbar.vue";e.default=l.exports},hSz7:function(t,e,s){}}]);