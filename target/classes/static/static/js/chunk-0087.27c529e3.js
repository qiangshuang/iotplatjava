(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-0087"],{"d+KK":function(t,e,r){"use strict";r.r(e);var a=r("P2sY"),s=r.n(a),i={name:"userselfinfo",mixins:[r("y2a5").a],data:function(){var t=this.$store.state.globaldata;return{userForm:s()({},t.userInfo),rules:{name:[{required:!0,message:"请输入姓名",trigger:"blur"}],birthday:[{required:!0,message:"请选择出生日期",trigger:"change"}],parent_id:[{type:"number",min:0,message:"请选择所属部门",trigger:"change"}],mobile:[{required:!0,pattern:"^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$",message:"请输入电话号码",trigger:"blur"}],sex:[{required:!0,message:"请选择用户性别",trigger:"blur"}]},avatars:[["/static/image/img1.jpg","/static/image/img2.jpg","/static/image/img3.jpg","/static/image/img4.jpg"],["/static/image/img5.jpg","/static/image/img6.jpg","/static/image/img7.jpg","/static/image/img8.jpg"],["/static/image/img9.jpg","/static/image/img10.jpg","/static/image/img11.jpg","/static/image/img12.jpg"]],avatarsShow:!1}},mounted:function(){},methods:{selAvatar:function(t){this.userForm.avatar=t,this.avatarsShow=!1},submitForm:function(t){var e=this;this.$refs[t].validate(function(r){if(!r)return!1;e.callSrvAct("/userselfup",e[t]).then(function(r){e.$store.dispatch("upUserInfo",e[t])})})},resetForm:function(t){this.$refs[t].resetFields()}}},l=r("KHd+"),o=Object(l.a)(i,function(){var t=this,e=t.$createElement,r=t._self._c||e;return r("div",[t._m(0),t._v(" "),r("div",{staticStyle:{margin:"10px"}}),t._v(" "),r("el-form",{ref:"userForm",staticClass:"form-content",attrs:{model:t.userForm,rules:t.rules,"label-width":"100px"}},[r("el-row",[r("el-col",{attrs:{span:12}},[r("el-form-item",{attrs:{label:"用户姓名",prop:"title"}},[r("el-input",{model:{value:t.userForm.title,callback:function(e){t.$set(t.userForm,"title",e)},expression:"userForm.title"}})],1)],1),r("el-col",{attrs:{span:12}},[r("el-form-item",{attrs:{label:"用户头像",required:""}},[r("el-form-item",{attrs:{prop:"avatar"}},[r("img",{staticClass:"user-avatar",attrs:{src:t.userForm.avatar}}),t._v(" "),r("el-popover",{staticClass:"more-avatar",attrs:{placement:"right",trigger:"click"},model:{value:t.avatarsShow,callback:function(e){t.avatarsShow=e},expression:"avatarsShow"}},[r("div",{staticStyle:{"text-align":"right",margin:"0"}},t._l(t.avatars,function(e){return r("div",t._l(e,function(e){return r("el-button",{key:e,attrs:{circle:""},on:{click:function(r){t.selAvatar(e)}}},[r("img",{staticClass:"user-avatar",attrs:{src:e}})])}))})),t._v(" "),r("el-button",{attrs:{slot:"reference"},slot:"reference"},[t._v("...")])],1)],1)],1)],1)],1),t._v(" "),r("el-row",[r("el-col",{attrs:{span:12}},[r("el-form-item",{attrs:{label:"出生日期",required:""}},[r("el-col",{attrs:{span:11}},[r("el-form-item",{attrs:{prop:"birthday"}},[r("el-date-picker",{staticStyle:{width:"100%"},attrs:{type:"date",placeholder:"选择日期",format:"yyyy年MM月dd日","value-format":"timestamp"},model:{value:t.userForm.birthday,callback:function(e){t.$set(t.userForm,"birthday",e)},expression:"userForm.birthday"}})],1)],1)],1)],1),r("el-col",{attrs:{span:12}},[r("el-form-item",{attrs:{label:"手机号码",prop:"mobile"}},[r("el-col",{attrs:{span:11}},[r("el-input",{model:{value:t.userForm.mobile,callback:function(e){t.$set(t.userForm,"mobile",e)},expression:"userForm.mobile"}})],1)],1)],1)],1),t._v(" "),r("el-row",[r("el-col",{attrs:{span:12}},[r("el-form-item",{attrs:{label:"电话号码",prop:"caller"}},[r("el-col",{attrs:{span:11}},[r("el-input",{model:{value:t.userForm.caller,callback:function(e){t.$set(t.userForm,"caller",e)},expression:"userForm.caller"}})],1)],1)],1),r("el-col",{attrs:{span:12}},[r("el-form-item",{attrs:{label:"用户性别",prop:"sex"}},[r("el-radio-group",{model:{value:t.userForm.sex,callback:function(e){t.$set(t.userForm,"sex",e)},expression:"userForm.sex"}},[r("el-radio",{attrs:{label:"男"}}),t._v(" "),r("el-radio",{attrs:{label:"女"}})],1)],1)],1)],1),t._v(" "),r("el-row",[r("el-col",{attrs:{span:12}},[r("el-form-item",{attrs:{label:"所属公司",prop:"user_org_title"}},[r("span",[t._v(t._s(t.userForm.user_org_title))])])],1),r("el-col",{attrs:{span:12}},[r("el-form-item",{attrs:{label:"所属部门",prop:"user_depart_title"}},[r("span",[t._v(t._s(t.userForm.user_depart_title))])])],1)],1),t._v(" "),r("el-form-item",{attrs:{label:"个人介绍",prop:"intro"}},[r("el-input",{attrs:{type:"textarea"},model:{value:t.userForm.intro,callback:function(e){t.$set(t.userForm,"intro",e)},expression:"userForm.intro"}})],1),t._v(" "),r("el-form-item",[r("el-button",{attrs:{type:"primary"},on:{click:function(e){t.submitForm("userForm")}}},[t._v("更新")]),t._v(" "),r("el-button",{on:{click:function(e){t.resetForm("userForm")}}},[t._v("重置")])],1)],1)],1)},[function(){var t=this.$createElement,e=this._self._c||t;return e("div",{staticClass:"form-header form-header-back"},[e("span",[this._v("用户资料")])])}],!1,null,null,null);o.options.__file="index.vue";e.default=o.exports},y2a5:function(t,e,r){"use strict";var a=r("gDS+"),s=r.n(a),i=r("4d7F"),l=r.n(i);e.a={methods:{cb_null:function(t){return!1},cb_goBack:function(t){return this.$router.go(-1),!0},cb_success:function(t){return!(!t.status||"SUCCESS"!=t.status)||(this.$message.error(t.msg),!1)},cb_showMess:function(t){return this.$message.success(t.msg),!0},gen_sameValue:function(t,e){return function(r,a,s){a!=t?s(new Error(e)):s()}},refreshCaptcha:function(t){t.preventDefault(),t.target.src=this.hysrvport+"/captcha?"+Math.random()},add_authToken:function(t){return t.length<1?"":t=t.indexOf("?")>-1?t+"&Authorization="+this.apiSrv.token:t+"?Authorization="+this.apiSrv.token},callServer:function(t,e,r){var a=this;this.apiSrv.callServer(t,e).then(function(t){if(console.log("res ------",t),r)for(var e=0;e<r.length;e++){if(r[e])if(!r[e](t))break}}).catch(function(t){console.log("err:",t),a.$message.error(t)})},callSrvAct:function(t,e){var r=this;return new l.a(function(a,s){r.apiSrv.callServer(t,e).then(function(t){t.status&&"SUCCESS"==t.status?a(t):(console.log("res:",t),r.$message.error(t.msg))}).catch(function(t){console.log("err:",t),r.$message.error(t)})})},gen_list_cb:function(t,e){var r=this;if(!t||t.length<1)return this.cb_null;var a=t+"Data",s=t+"TotalRec",i=t+"TotalPage",l=t+"RecOfPage",o=t+"CurPage";return function(t){return e&&1==e&&t.data.cp>1?r[a]=r[a].concat(t.data.pageData||[]):r[a]=t.data.pageData||[],r[s]=t.data.totalRec,r[l]=t.data.rop,r[o]=t.data.cp,r[i]=t.data.rop>0?Math.ceil(t.data.totalRec/t.data.rop):0,t.data.detailData&&(r[a+"dData"]=t.data.detailData||[]),!0}},gen_selmulti_change:function(t){var e=this;if(!t||t.length<1)return this.cb_null;var r=t+"MultiSel";return function(t){if(e[r]){for(var a=[],s=0;s<t.length;s++)a.push(t[s].id);e[r]=a}}},refreshPage2:function(t,e,r,a,i,l){var o=t+"QueryArr",n=t+"CbPage",c=JSON.parse(s()(this[o]));if(c&&c.length>0)for(var u=c.length-1;u>-1;u--)console.log("qpsArr[i].val",c[u].val,0==c[u].val,""!=c[u].val),0==c[u].val&&(console.log("i:",u),c.splice(u,1));console.log("qpsArr:",c,this[o]);var m={qps:c||"",rop:r,cp:a};return this[t+"SortParas"]&&(m.sps=this[t+"SortParas"]),m._hy=i,this.callServer(e,m,[this.cb_success,this[n],l])},refreshPage:function(t,e,r,a){var s=t+"RecOfPage",i=t+"CurPage";this[i]<1&&(this[i]=1),this.refreshPage2(t,e,this[s],this[i],r,a)},queryPageData:function(t,e,r,a,i,l){if(!t||t.length<1)return this.cb_null;var o=t+"QueryArr",n=JSON.parse(s()(this[o]));if(n&&n.length>0)for(var c=n.length-1;c>-1;c--)0==n[c].val&&n.splice(c,1);console.log("qpsArr:",n);var u={qps:n||[],rop:r,cp:a};return this[t+"SortParas"]&&(u.sps=this[t+"SortParas"]),u._hy=i,this.callServer(e,u,l)}}}}}]);