(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-29d4","chunk-982c","chunk-46bf","arh/","chunk-72cc"],{"2hjp":function(t,e,a){"use strict";a.r(e);var i={components:{SidebarItem:a("arh/").default},props:{backgroundColor:String,textColor:String,activeTextColor:String,textlogo:String},computed:{isCollapse:function(){return!this.$store.state.globaldata.sidebarOpen}}},n=a("KHd+"),s=Object(n.a)(i,function(){var t=this.$createElement,e=this._self._c||t;return e("el-scrollbar",{attrs:{wrapClass:"scrollbar-wrapper"}},[e("el-menu",{attrs:{mode:"vertical","show-timeout":200,"default-active":this.$route.path,collapse:this.isCollapse}},[e("sidebar-item",{attrs:{parent_id:0}})],1)],1)},[],!1,null,null,null);s.options.__file="index.vue";e.default=s.exports},NmZX:function(t,e,a){},"arh/":function(t,e,a){"use strict";a.r(e);var i={name:"SidebarItem",props:{parent_id:{type:Number,default:0},isNest:{type:Boolean,default:!1}},computed:{itemData:function(){return this.gcomm.lookChilds(this.$store.state.globaldata.menuList,"parent_id",this.parent_id,[{keyname:"is_menu",value:"t"}])}},methods:{hasChildren:function(t){var e=this.gcomm.lookChilds(this.$store.state.globaldata.menuList,"parent_id",t,[{keyname:"is_menu",value:"t"}]);return!!e&&e.length>0},getChildren:function(t){return this.gcomm.lookChilds(this.$store.state.globaldata.menuList,"parent_id",t,[{keyname:"is_menu",value:"t"}])}}},n=a("KHd+"),s=Object(n.a)(i,function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"menu-wrapper"},[t._l(t.itemData,function(e){return"t"==e.is_menu&&(e.path||t.hasChildren(e.id))?[t.hasChildren(e.id)?a("el-submenu",{key:e.path||e.name,attrs:{index:e.path||e.name}},[a("template",{slot:"title"},[e.icon?a("svg-icon",{staticStyle:{"font-size":"18px"},attrs:{"icon-class":e.icon}}):t._e(),t._v(" "),e.title?a("span",{attrs:{slot:"title"},slot:"title"},[t._v(t._s(e.title))]):t._e()],1),t._v(" "),a("sidebar-item",{key:e.path||e.name,attrs:{parent_id:e.id}})],2):a("router-link",{key:e.path||e.name,attrs:{to:e.path||e.name}},[a("el-menu-item",{class:{"submenu-title-noDropdown":!t.isNest},attrs:{index:e.path||e.name}},[e.icon?a("svg-icon",{attrs:{"icon-class":e.icon}}):t._e(),t._v(" "),a("span",{attrs:{slot:"title"},slot:"title"},[t._v(t._s(e.title))])],1)],1)]:t._e()})],2)},[],!1,null,null,null);s.options.__file="SidebarItem.vue";e.default=s.exports},cMiD:function(t,e,a){"use strict";var i=a("kjS3");a.n(i).a},eh9z:function(t,e,a){"use strict";a.r(e);var i=a("6IZw"),n=a("sY+N"),s={components:{Screenfull:i.a,ThemePicker:n.a},props:{textlogo:{type:String,default:""},levelList:{type:Array,default:function(){return[]}}},computed:{isCollapse:function(){return!this.$store.state.globaldata.sidebarOpen}},methods:{toggleSideBar:function(){this.$store.dispatch("toggleSidebar")},getDevPrjName:function(){return this.$store.state.globaldata.devprj?this.$store.state.globaldata.devprj.abbr||this.$store.state.globaldata.devprj.title:"未选择"},modifyInfo:function(){this.$router.push({path:"/userselfinfo"})},modifyPwd:function(){this.$router.push({path:"/modselfpwd"})},logout:function(){this.apiSrv.clearToken(),this.$store.dispatch("logoutUserInfo").then(function(){location.reload()})}}},o=(a("cMiD"),a("KHd+")),l=Object(o.a)(s,function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"navbar"},[a("div",{staticClass:"text-logo-corps",class:{"hidenav-logo":t.isCollapse}},[a("span",{staticClass:"text-logo",class:{"show-logo-text":t.isCollapse}},[t._v(t._s(t.textlogo))])]),t._v(" "),a("div",{staticClass:"hamburger-container",on:{click:t.toggleSideBar}},[a("svg-icon",{staticClass:"icon",attrs:{"icon-class":"list"}})],1),t._v(" "),a("span",{staticClass:"text-sys"},[t._v("红云研发平台 ( "+t._s(t.getDevPrjName())+" )")]),t._v(" "),t._l(t.levelList,function(e,i){return a("span",{key:e.name,staticStyle:{"font-size":"14px",color:"#fff","font-weight":"500"}},[a("span",0==i?[t._v(" --")]:[t._v("/")]),t._v("\n         "+t._s(e.title)+"\n      ")])}),t._v(" "),a("el-container",{staticClass:"right-menu",attrs:{direction:"horizontal"}},[a("el-tooltip",{attrs:{effect:"dark",content:t.$t("navbar.screenfull"),placement:"bottom"}},[a("screenfull",{staticClass:"screenfull"})],1),t._v(" "),a("el-tooltip",{attrs:{effect:"dark",content:t.$t("navbar.theme"),placement:"bottom"}},[a("theme-picker",{staticClass:"theme-switch ",attrs:{size:"mini"}})],1),t._v(" "),a("el-dropdown",{staticClass:"avatar-container",attrs:{trigger:"click"}},[a("el-container",{attrs:{direction:"horizontal"}},[a("span",{staticStyle:{color:"#fff","line-height":"30px","margin-right":"10px"}},[t._v(t._s(t.$store.state.globaldata.userInfo.name))]),t._v(" "),a("img",{staticStyle:{"border-radius":"15px"},attrs:{height:"30",width:"30",src:t.$store.state.globaldata.userInfo.avatar}}),t._v(" "),a("i",{staticClass:"el-icon-caret-bottom",staticStyle:{"line-height":"30px","margin-left":"6px"}})]),t._v(" "),a("el-dropdown-menu",{attrs:{slot:"dropdown"},slot:"dropdown"},[a("el-dropdown-item",[a("span",{staticStyle:{display:"block"},on:{click:t.modifyInfo}},[t._v(t._s(t.$t("navbar.modifyInfo")))])]),t._v(" "),a("el-dropdown-item",[a("span",{staticStyle:{display:"block"},on:{click:t.modifyPwd}},[t._v(t._s(t.$t("navbar.modifyPwd")))])]),t._v(" "),a("el-dropdown-item",{attrs:{divided:""}},[a("span",{staticStyle:{display:"block"},on:{click:t.logout}},[t._v(t._s(t.$t("navbar.logOut")))])])],1)],1)],1)],2)},[],!1,null,"19a4e870",null);l.options.__file="Navbar.vue";e.default=l.exports},gbs0:function(t,e,a){"use strict";a.r(e);var i={name:"AppMain",components:{},mounted:function(){}},n=(a("o/U2"),a("KHd+")),s=Object(n.a)(i,function(){var t=this.$createElement,e=this._self._c||t;return e("el-container",{staticClass:"appmain-container"},[e("transition",{attrs:{name:"fade",mode:"out-in"}},[e("keep-alive",{attrs:{inlcude:this.$store.state.globaldata.caches,exclude:this.$store.state.globaldata.uncaches}},[e("router-view",{key:this.$route.fullpath,staticClass:"content-border"})],1)],1)],1)},[],!1,null,"74516642",null);s.options.__file="AppMain.vue";e.default=s.exports},kjS3:function(t,e,a){},nWMO:function(t,e,a){"use strict";var i=a("NmZX");a.n(i).a},"o/U2":function(t,e,a){"use strict";var i=a("x89r");a.n(i).a},wMB6:function(t,e,a){"use strict";a.r(e);var i=a("eh9z"),n=a("2hjp"),s=a("gbs0"),o=a("KEZ+"),l={watch:{$route:function(t){"mobile"==this.$store.state.globaldata.device&&this.$store.state.globaldata.sidebarOpen&&o.a.dispatch("closeSideBar",{sidebarAnimation:!1})}},beforeMount:function(){window.addEventListener("resize",this.resizeHandler)},mounted:function(){this.agentMobile()&&(o.a.dispatch("setDevice","mobile"),o.a.dispatch("closeSideBar",{withoutAnimation:!0}))},methods:{agentMobile:function(){for(var t=navigator.userAgent,e=["Android","iPhone","SymbianOS","Windows Phone","iPad","iPod"],a=!1,i=0;i<e.length;i++)if(t.indexOf(e[i])>0){a=!0;break}return a},resizeHandler:function(){document.hidden||this.agentMobile()&&o.a.dispatch("closeSideBar",{withoutAnimation:!0})}}},r=a("Y1Bl"),c=a("POwn"),d={name:"layout",components:{Navbar:i.default,Sidebar:n.default,Breadcrumb:r.a,AppMain:s.default,RescSubmenu:c.a},data:function(){return{}},mixins:[l],computed:{},methods:{handleDrpCommand:function(){this.apiSrv.clearToken(),this.$store.dispatch("logoutUserInfo").then(function(){location.reload()})},handleMpwdCommand:function(){this.$router.push({name:"userselfpwd"})},collapseSet:function(){this.$store.state.globaldata.collapse=!this.$store.state.globaldata.collapse},clickQuit:function(t){this.apiSrv.clearToken(),this.$store.dispatch("logoutUserInfo").then(function(){location.reload()})},hasChildren:function(t){var e=this.gcomm.lookChilds(this.$store.state.globaldata.menuList,"parent_id",t,[{keyname:"is_menu",value:1}]);return!!e&&e.length>0},getChildren:function(t){var e=this.gcomm.lookChilds(this.$store.state.globaldata.menuList,"parent_id",t,[{keyname:"is_menu",value:1}]);return console.log("菜单",t,e),e},breaddata:function(){var t=this.$store.state.globaldata.menuList;console.log("hhh----",t);for(var e=this.$route.path,a=[],i=this.gcomm.lookByValue(t,"path",e);i&&a.length<10;)a.unshift(i),i=this.gcomm.lookByValue(t,"id",i.parent_id);return a}}},p=(a("nWMO"),a("KHd+")),u=Object(p.a)(d,function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("el-container",{staticStyle:{height:"100%",overflow:"hidden","box-shadow":"0px 0px 10px 0px rgba(46,91,255,0.07)"},attrs:{direction:"vertical"}},[a("el-header",{staticStyle:{background:"#437CF5 url(/static/image/123.png)","background-size":"100% 100%",width:"100%",display:"table"},attrs:{height:"50px"}},[a("div",{staticStyle:{"margin-top":"7px"}},[a("img",{attrs:{src:"/static/image/symbol.png",width:"48",height:"41"}})]),t._v(" "),a("div",{staticStyle:{"margin-top":"24px",width:"100%","vertical-align":"middle",display:"table-cell","padding-left":"30px"}},[a("span",{staticStyle:{color:"#eeeeee","font-weight":"500","font-size":"30px","vertical-align":"middle","margin-left":"5px","letter-spacing":"4px"}},[t._v("工业物联网大数据\n                ")])]),t._v(" "),a("el-dropdown",{staticStyle:{"margin-bottom":"20px"},attrs:{placement:"bottom",trigger:"click"}},[a("el-container",{staticStyle:{"margin-top":"10px"},attrs:{direction:"horizontal"}},[a("img",{staticStyle:{"border-radius":"15px"},attrs:{src:t.$store.state.globaldata.userInfo.avatar,width:"30",height:"30"}}),t._v(" "),a("svg-icon",{staticStyle:{"margin-left":"6px","margin-top":"6px",width:"10px"},attrs:{"icon-class":"arrowdown",color:"#C2BEBE","font-size":"16px"}})],1),a("el-dropdown-menu",{attrs:{slot:"dropdown"},slot:"dropdown"},[a("el-dropdown-item",[a("span",{staticStyle:{display:"block"},on:{click:t.handleMpwdCommand}},[t._v("修改密码\n                \n    \n")])]),t._v(" "),a("el-dropdown-item",[a("span",{staticStyle:{display:"block"},on:{click:t.handleDrpCommand}},[t._v("退出登录\n                \n    \n")])])],1)],1)],1),t._v(" "),a("el-main",{staticStyle:{margin:"0px",padding:"0px"}},[a("el-container",{attrs:{direction:"horizontal"}},[a("el-aside",{class:{"collapse-width":t.$store.state.globaldata.collapse},staticStyle:{height:"100%"},attrs:{width:"200px"}},[a("el-main",{staticStyle:{height:"100%",margin:"0px",padding:"0px"}},[a("el-menu",{staticStyle:{height:"100%",padding:"2px"},attrs:{collapse:t.$store.state.globaldata.collapse,"text-color":"#333333","active-text-color":"#1873E3","default-active":"1"}},[a("resc-submenu",{staticStyle:{height:"100%"},attrs:{parent_id:0,menuList:t.$store.state.globaldata.menuList}})],1)],1)],1),t._v(" "),a("el-main",{class:{mainLeft60:t.$store.state.globaldata.collapse,mainLeft200:!t.$store.state.globaldata.collapse},staticStyle:{margin:"0px",padding:"0px",position:"absolute",right:"0px",bottom:"0px",top:"68px",background:"#EFF1F9"}},[a("el-container",{staticClass:"full"},[a("el-header",{staticStyle:{padding:"0px",background:"#fff"},attrs:{height:"40px"}},[a("el-container",{attrs:{direction:"horizontal"}},[a("svg-icon",{staticStyle:{margin:"10px"},attrs:{"icon-class":"line",color:"#706D6D","font-size":"20px"},nativeOn:{click:function(e){return e.stopPropagation(),t.collapseSet(e)}}}),t._v(" "),a("el-breadcrumb",{staticStyle:{margin:"14px"}},t._l(t.breaddata(),function(e,i){return a("el-breadcrumb-item",{key:e.name},[0==i&&e.icon?a("svg-icon",{attrs:{"icon-class":e.icon,color:"#1871EB","font-size":"14px"}}):t._e(),t._v(" "),0==i?a("span",{staticStyle:{color:"#1871EB"}},[t._v(t._s(e.title)+"\n                                        ")]):t._e(),t._v(" "),0!=i?a("span",[t._v(t._s(e.title)+"\n                                        ")]):t._e()],1)}))],1)],1),t._v(" "),a("el-main",{staticStyle:{margin:"10px",padding:"10px",background:"#fff"}},[a("keep-alive",{attrs:{include:t.$store.state.globaldata.caches,exclude:t.$store.state.globaldata.uncaches}},[a("router-view")],1)],1)],1)],1)],1)],1)],1)},[],!1,null,null,null);u.options.__file="index.vue";e.default=u.exports},x89r:function(t,e,a){}}]);