(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-06e0"],{OYHo:function(t,e,i){},"RU/L":function(t,e,i){i("Rqdy");var o=i("WEpk").Object;t.exports=function(t,e,i){return o.defineProperty(t,e,i)}},Rqdy:function(t,e,i){var o=i("Y7ZC");o(o.S+o.F*!i("jmDH"),"Object",{defineProperty:i("2faE").f})},SEkw:function(t,e,i){t.exports={default:i("RU/L"),__esModule:!0}},"U+t5":function(t,e,i){"use strict";i.r(e);var o=i("P2sY"),n=i.n(o),r=i("YEIV"),a=i.n(r),s={name:"orgedit",mixins:[i("y2a5").a],components:{},props:{paramsObj:{type:Object,default:function(){return{}}}},data:function(){var t;return t={newOrgForm:{id:0,title:"",type:"",parent_id:0,sort:0,data_scope:"",stop:"",memo:""},orgForm:{},orgData:[]},a()(t,"newOrgForm",{id:0,title:"",type:"部门",parent_id:0,sort:0,data_scope:"f",stop:"f",memo:""}),a()(t,"isInDialog",!1),a()(t,"pObj",{}),t},mounted:function(){console.log(" xxxxxx ------ "),this.pObj=n()({},this.paramsObj),this.gcomm.mapLength(this.pObj)>0?(this.isInDialog=!0,this.orgForm=n()({},this.newOrgForm,this.pObj)):this.orgForm=n()({},this.newOrgForm,this.$route.params)},methods:{pObjChanged:function(){this.gcomm.mapLength(this.pObj)>0&&(this.isInDialog=!0,this.orgForm=n()({},this.newOrgForm,this.pObj))},changedc6d8:function(t){for(var e=0;e<this.orgData.length;e++)this.orgData[e].id==this.orgForm.parent_id&&(this.orgForm.parent_title=this.orgData[e].title)},clickf5e8c:function(){this.submitOrgForm("orgForm")},handleClose:function(){this.isInDialog?this.$emit("submitClose"):this.$router.go(-1)},submitOrgFormImpl:function(t){var e=this,i=void 0,o=!0,r="";this[t].id>0?(i="/act",o=!1,r="deptup"):(i="/act",r="deptadd");var a=n()({},this[t]);a._hy=r,this.callSrvAct(i,a).then(function(t){console.log("res:",t,e.isInDialog),e.isInDialog?o?t.data&&t.data.inst?e.$emit("submitOk",t.data.inst):t.data&&e.$emit("submitOk",t.data||{}):e.$emit("submitOk",a):e.$router.go(-1)})},submitOrgForm:function(t){var e=this;this.$refs[t].validate(function(i){if(!i)return!1;e.submitOrgFormImpl(t)})}},watch:{paramsObj:function(t){this.pObj.id!=this.paramsObj.id&&(this.pObj=n()({},this.paramsObj),this.pObjChanged())}},computed:{},beforeCreate:function(){},created:function(){},beforeMount:function(){},beforeUpdate:function(){},updated:function(){},beforeDestroy:function(){},destroyed:function(){}},u=(i("eLV9"),i("KHd+")),c=Object(u.a)(s,function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("el-container",{attrs:{direction:"vertical"}},[i("el-form",{ref:"orgForm",staticStyle:{padding:"10px"},attrs:{model:t.orgForm,"label-width":"100px"}},[i("el-form-item",{attrs:{label:"部门名称",prop:"title",rules:{required:!0,message:"请输入正确的部门名称",trigger:"blur",min:1,max:200}}},[i("el-input",{model:{value:t.orgForm.title,callback:function(e){t.$set(t.orgForm,"title",e)},expression:"orgForm.title"}})],1),t._v(" "),t._e(),t._v(" "),t._e(),t._v(" "),t._e(),t._v(" "),t._e(),t._v(" "),t._e(),t._v(" "),t._e(),t._v(" "),i("el-row",{attrs:{type:"flex",justify:"center"}},[i("el-button",{on:{click:t.clickf5e8c}},[i("span",[t._v("确定\n                    ")])]),t._v(" "),i("el-button",{on:{click:t.handleClose}},[i("span",[t._v("取消\n                    ")])])],1)],1)],1)},[],!1,null,"a9318a8e",null);c.options.__file="index.vue";e.default=c.exports},YEIV:function(t,e,i){"use strict";e.__esModule=!0;var o=function(t){return t&&t.__esModule?t:{default:t}}(i("SEkw"));e.default=function(t,e,i){return e in t?(0,o.default)(t,e,{value:i,enumerable:!0,configurable:!0,writable:!0}):t[e]=i,t}},eLV9:function(t,e,i){"use strict";var o=i("OYHo");i.n(o).a}}]);