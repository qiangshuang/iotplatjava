(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-8a2e"],{FTi5:function(t,e,a){"use strict";var s=a("bmT0");a.n(s).a},bmT0:function(t,e,a){},s5af:function(t,e,a){"use strict";a.r(e);var s=a("6IZw"),o=a("sY+N"),i={components:{Screenfull:s.a,ThemePicker:o.a},props:{textlogo:{type:String,default:""},levelList:{type:Array,default:function(){return[]}}},computed:{isCollapse:function(){return!this.$store.state.globaldata.sidebarOpen}},methods:{toggleSideBar:function(){this.$store.dispatch("toggleSidebar")},getDevPrjName:function(){return this.$store.state.globaldata.devprj?this.$store.state.globaldata.devprj.abbr||this.$store.state.globaldata.devprj.title:"未选择"},modifyInfo:function(){this.$router.push({path:"/userselfinfo"})},modifyPwd:function(){this.$router.push({path:"/modselfpwd"})},logout:function(){this.apiSrv.clearToken(),this.$store.dispatch("logoutUserInfo").then(function(){location.reload()})}}},n=(a("FTi5"),a("KHd+")),l=Object(n.a)(i,function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"navbar"},[a("div",{staticClass:"text-logo-corps",class:{"hidenav-logo":t.isCollapse}},[a("span",{staticClass:"text-logo",class:{"show-logo-text":t.isCollapse}},[t._v(t._s(t.textlogo))])]),t._v(" "),a("div",{staticClass:"hamburger-container",on:{click:t.toggleSideBar}},[a("svg-icon",{staticClass:"icon",attrs:{"icon-class":"list"}})],1),t._v(" "),a("span",{staticClass:"text-sys"},[t._v("红云研发平台 ( "+t._s(t.getDevPrjName())+" )")]),t._v(" "),t._l(t.levelList,function(e,s){return a("span",{key:e.name,staticStyle:{"font-size":"14px",color:"#fff","font-weight":"500"}},[a("span",0==s?[t._v(" --")]:[t._v("/")]),t._v("\n         "+t._s(e.title)+"\n      ")])}),t._v(" "),a("el-container",{staticClass:"right-menu",attrs:{direction:"horizontal"}},[a("el-tooltip",{attrs:{effect:"dark",content:t.$t("navbar.screenfull"),placement:"bottom"}},[a("screenfull",{staticClass:"screenfull"})],1),t._v(" "),a("el-tooltip",{attrs:{effect:"dark",content:t.$t("navbar.theme"),placement:"bottom"}},[a("theme-picker",{staticClass:"theme-switch ",attrs:{size:"mini"}})],1),t._v(" "),a("el-dropdown",{staticClass:"avatar-container",attrs:{trigger:"click"}},[a("el-container",{attrs:{direction:"horizontal"}},[a("span",{staticStyle:{color:"#fff","line-height":"30px","margin-right":"10px"}},[t._v(t._s(t.$store.state.globaldata.userInfo.name))]),t._v(" "),a("img",{staticStyle:{"border-radius":"15px"},attrs:{height:"30",width:"30",src:t.$store.state.globaldata.userInfo.avatar}}),t._v(" "),a("i",{staticClass:"el-icon-caret-bottom",staticStyle:{"line-height":"30px","margin-left":"6px"}})]),t._v(" "),a("el-dropdown-menu",{attrs:{slot:"dropdown"},slot:"dropdown"},[a("el-dropdown-item",[a("span",{staticStyle:{display:"block"},on:{click:t.modifyInfo}},[t._v(t._s(t.$t("navbar.modifyInfo")))])]),t._v(" "),a("el-dropdown-item",[a("span",{staticStyle:{display:"block"},on:{click:t.modifyPwd}},[t._v(t._s(t.$t("navbar.modifyPwd")))])]),t._v(" "),a("el-dropdown-item",{attrs:{divided:""}},[a("span",{staticStyle:{display:"block"},on:{click:t.logout}},[t._v(t._s(t.$t("navbar.logOut")))])])],1)],1)],1)],2)},[],!1,null,"9bf03594",null);l.options.__file="Navbar.vue";e.default=l.exports}}]);