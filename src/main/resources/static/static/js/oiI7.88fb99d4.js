(window.webpackJsonp=window.webpackJsonp||[]).push([["oiI7"],{oiI7:function(t,e,a){"use strict";a.r(e);var i={name:"SidebarItem",props:{parent_id:{type:Number,default:0},isNest:{type:Boolean,default:!1}},computed:{itemData:function(){return this.gcomm.lookChilds(this.$store.state.globaldata.menuList,"parent_id",this.parent_id,[{keyname:"is_menu",value:"t"}])}},methods:{hasChildren:function(t){var e=this.gcomm.lookChilds(this.$store.state.globaldata.menuList,"parent_id",t,[{keyname:"is_menu",value:"t"}]);return!!e&&e.length>0},getChildren:function(t){return this.gcomm.lookChilds(this.$store.state.globaldata.menuList,"parent_id",t,[{keyname:"is_menu",value:"t"}])}}},n=a("KHd+"),s=Object(n.a)(i,function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"menu-wrapper"},[t._l(t.itemData,function(e){return"t"==e.is_menu&&(e.path||t.hasChildren(e.id))?[t.hasChildren(e.id)?a("el-submenu",{key:e.path||e.name,attrs:{index:e.path||e.name}},[a("template",{slot:"title"},[e.icon?a("svg-icon",{staticStyle:{"font-size":"18px"},attrs:{"icon-class":e.icon}}):t._e(),t._v(" "),e.title?a("span",{attrs:{slot:"title"},slot:"title"},[t._v(t._s(e.title))]):t._e()],1),t._v(" "),a("sidebar-item",{key:e.path||e.name,attrs:{parent_id:e.id}})],2):a("router-link",{key:e.path||e.name,attrs:{to:e.path||e.name}},[a("el-menu-item",{class:{"submenu-title-noDropdown":!t.isNest},attrs:{index:e.path||e.name}},[e.icon?a("svg-icon",{attrs:{"icon-class":e.icon}}):t._e(),t._v(" "),a("span",{attrs:{slot:"title"},slot:"title"},[t._v(t._s(e.title))])],1)],1)]:t._e()})],2)},[],!1,null,null,null);s.options.__file="SidebarItem.vue";e.default=s.exports}}]);