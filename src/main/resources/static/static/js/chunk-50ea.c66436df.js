(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-50ea"],{FQD7:function(e,t,r){},GDWZ:function(e,t,r){"use strict";var o=r("FQD7");r.n(o).a},"RU/L":function(e,t,r){r("Rqdy");var o=r("WEpk").Object;e.exports=function(e,t,r){return o.defineProperty(e,t,r)}},Rqdy:function(e,t,r){var o=r("Y7ZC");o(o.S+o.F*!r("jmDH"),"Object",{defineProperty:r("2faE").f})},SEkw:function(e,t,r){e.exports={default:r("RU/L"),__esModule:!0}},YEIV:function(e,t,r){"use strict";t.__esModule=!0;var o=function(e){return e&&e.__esModule?e:{default:e}}(r("SEkw"));t.default=function(e,t,r){return t in e?(0,o.default)(e,t,{value:r,enumerable:!0,configurable:!0,writable:!0}):e[t]=r,e}},bHE8:function(e,t,r){"use strict";r.r(t);var o=r("P2sY"),i=r.n(o),s=r("YEIV"),n=r.n(s),a={name:"personedit",mixins:[r("y2a5").a],inject:["routerRefresh"],components:{},props:{paramsObj:{type:Object,default:function(){return{}}}},data:function(){var e;return e={newUserForm:{id:0,parent_id:0,title:"",job_title:"",idnumber:"",user_name:"",mobile:"",gender:"",cardnumber:0,updated:(new Date).getTime(),image:""},userForm:{},orgDatas:[]},n()(e,"newUserForm",{id:0,parent_id:0,parent_title:"",title:"",job_title:"",idnumber:"",user_name:"",mobile:"",gender:"",cardnumber:0,updated:(new Date).getTime(),photo:""}),n()(e,"userForm",{}),n()(e,"orgDatas",[]),n()(e,"org_title",""),n()(e,"rules",{idnumber:[{required:!0,message:"请输入身份证号码",trigger:"blur"},{pattern:/(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$)/,message:"证件号码格式有误！",trigger:"blur"}],mobile:[{required:!0,message:"请输入手机号",trigger:"blur"},{pattern:/^((13[0-9])|(17[0-1,6-8])|(15[^4,\\D])|(18[0-9]))\d{8}$/,message:"手机号码格式有误！",trigger:"blur"}],parent_id:[{required:!0,message:"请输入手机号",trigger:"blur"}]}),e},mounted:function(){var e=this;this.pObj=i()({},this.paramsObj),this.gcomm.mapLength(this.pObj)>0?(this.isInDialog=!0,this.userForm=i()({},this.newUserForm,this.pObj),console.log("2 ---- ",this.userForm)):(this.userForm=i()({},this.newUserForm,this.$route.params),console.log("3 ---- ",this.userForm));this.callSrvAct("/actq",{_hy:"departlist",qps:[{name:"type",val:"部门",tb:"org"}],sps:[{name:"title",tb:"org",orderV:"asc"}]}).then(function(t){e.orgDatas=t.data||[],e.orgDatas.unshift({id:0,title:"-- 请选择 --"}),console.log("test....",e.orgDatas)}),console.log("传过来的",this.params)},methods:{pObjChanged:function(){this.gcomm.mapLength(this.pObj)>0?(this.isInDialog=!0,this.userForm=i()({},this.newUserForm,this.pObj)):(this.userForm=i()({},this.newUserForm,this.$route.params),console.log("3 ---- ",this.userForm))},submitPersonFormImpl:function(e){var t=this,r=void 0,o="";this[e].id>0?(r="/act",!1,o="userup"):(r="/act",o="useradd");var s=i()({},this[e]);s._hy=o,this.callSrvAct(r,s).then(function(e){t.$router.go(-1)})},handleClick:function(e){var t=this;this[e].parent_id<1?this.$message.info("请选择用户所属部门"):(this.$refs[e].validate(function(r){if(!r)return!1;t.submitPersonFormImpl(e)}),console.log("结果",this.userForm))},handleReset:function(e){this.$refs[e].resetFields()},handleSuccess:function(e,t){console.log("res:",e),this.userForm.photo=e.data},genDownUrl:function(e){if(!e||e.length<1)return"";var t=e.split(";");return!t||t.length<2?"":this.hysrvport+"/hydownload?btnEncode=headupload&relateType=headimage&filename="+t[1]+"&Authorization="+this.apiSrv.token},genUploadUrl:function(e){return this.hysrvport+"/hyupload?btnEncode=headupload&relateType=headimage&fi=add&Authorization="+this.apiSrv.token},beforeRemove:function(){},close:function(){this.userForm.photo="",this.$refs.box.clearFiles()},orgChange:function(e){console.log(e)},beforeAvatarUpload:function(e){var t="image/jpeg"===e.type,r=e.size/1024<200;return t||this.$message.error("上传头像图片只能是 JPG 格式!"),r||this.$message.error("上传头像图片大小不能超过 200kb!"),t&&r}},watch:{paramsObj:function(e){this.pObj.id!=this.paramsObj.id&&(this.pObj=i()({},this.paramsObj),this.pObjChanged())}},computed:{},beforeCreate:function(){},created:function(){},beforeMount:function(){},beforeUpdate:function(){},updated:function(){},beforeDestroy:function(){},destroyed:function(){}},l=(r("GDWZ"),r("KHd+")),u=Object(l.a)(a,function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("el-container",{attrs:{direction:"vertical"}},[r("el-form",{ref:"userForm",staticStyle:{"margin-top":"20px"},attrs:{model:e.userForm,"label-width":"120px",rules:e.rules}},[r("el-form-item",{attrs:{label:"选择部门：",prop:"parent_id"}},[r("el-select",{staticStyle:{width:"300px"},on:{change:e.orgChange},model:{value:e.userForm.parent_id,callback:function(t){e.$set(e.userForm,"parent_id",t)},expression:"userForm.parent_id"}},e._l(e.orgDatas,function(e){return r("el-option",{key:e.id,attrs:{label:e.title,value:e.id}})}))],1),e._v(" "),r("el-form-item",{attrs:{label:"姓名：",prop:"title",rules:{required:!0,message:"请输入姓名",trigger:"blue"}}},[r("el-input",{staticStyle:{width:"300px"},attrs:{placeholder:"请输入姓名"},model:{value:e.userForm.title,callback:function(t){e.$set(e.userForm,"title",t)},expression:"userForm.title"}})],1),e._v(" "),e._e(),e._v(" "),r("el-form-item",{attrs:{label:"身份证号：",prop:"idnumber"}},[r("el-input",{staticStyle:{width:"300px"},attrs:{placeholder:"请输入身份证号"},model:{value:e.userForm.idnumber,callback:function(t){e.$set(e.userForm,"idnumber",t)},expression:"userForm.idnumber"}})],1),e._v(" "),e._e(),e._v(" "),r("el-form-item",{attrs:{label:"手机号：",prop:"mobile"}},[r("el-input",{staticStyle:{width:"300px"},attrs:{placeholder:"请输入手机号"},model:{value:e.userForm.mobile,callback:function(t){e.$set(e.userForm,"mobile",t)},expression:"userForm.mobile"}})],1),e._v(" "),r("el-form-item",{attrs:{label:"性别：",prop:"gender",rules:{required:!0,message:"请选择性别",trigger:"blur",max:12}}},[r("el-radio-group",{model:{value:e.userForm.gender,callback:function(t){e.$set(e.userForm,"gender",t)},expression:"userForm.gender"}},[r("el-radio",{attrs:{label:"男"}},[r("span",[e._v("男\n                    ")])]),e._v(" "),r("el-radio",{attrs:{label:"女"}},[r("span",[e._v("女\n                    ")])])],1)],1),e._v(" "),e._e(),e._v(" "),e._e(),e._v(" "),r("el-form-item",{attrs:{label:"上传照片：",prop:"image"}},[r("el-upload",{ref:"box",staticStyle:{width:"178px",height:"178px",border:"1px dashed #d9d9d9","line-height":"178px","text-align":"center",position:"relative"},attrs:{action:e.genUploadUrl(),multiple:!1,"show-file-list":!1,"on-remove":e.beforeRemove,"on-success":e.handleSuccess,limit:1,id:"box","before-upload":e.beforeAvatarUpload}},[e.userForm.photo?r("img",{staticStyle:{width:"178px",height:"178px",display:"block"},attrs:{src:e.genDownUrl(e.userForm.photo)}}):e._e(),e._v(" "),e.userForm.photo?e._e():r("span",{staticStyle:{"font-size":"24px",color:"#7F7F7F","font-weight":"800"}},[r("i",{staticClass:"el-icon-plus"})]),e._v(" "),e.userForm.photo?r("span",{ref:"clos",staticStyle:{position:"absolute",top:"0px",right:"0px"},attrs:{id:"clos"}},[r("i",{staticClass:"el-icon-close",staticStyle:{position:"absolute",top:"0px",right:"0px"},on:{click:function(t){return t.stopPropagation(),e.close(t)}}})]):e._e()])],1)],1),e._v(" "),r("div",{staticStyle:{"text-align":"center"}},[r("el-button",{on:{click:function(t){e.handleClick("userForm")}}},[r("span",[e._v("提交\n            ")])]),e._v(" "),r("el-button",{staticStyle:{"margin-left":"40px"},on:{click:function(t){e.handleReset("userForm")}}},[r("span",[e._v("重置\n            ")])])],1)],1)},[],!1,null,"234c9286",null);u.options.__file="index.vue";t.default=u.exports}}]);