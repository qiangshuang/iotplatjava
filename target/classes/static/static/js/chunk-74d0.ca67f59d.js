(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-74d0"],{Nehs:function(t,e,a){"use strict";a.r(e);var n=a("P2sY"),r=a.n(n),i={name:"iotmanedit",mixins:[a("y2a5").a],components:{},props:{paramsObj:{type:Object,default:function(){return{}}}},data:function(){return{newIotmanForm:{id:0,title:"",equdes:"",equip:"",factory:""},iotmanForm:{}}},mounted:function(){this.pObj=r()({},this.paramsObj),this.gcomm.mapLength(this.pObj)>0?(this.isInDialog=!0,this.iotmanForm=r()({},this.newIotmanForm,this.pObj)):this.iotmanForm=r()({},this.newIotmanForm,this.$route.params)},methods:{pObjChanged:function(){this.gcomm.mapLength(this.pObj)>0&&(this.isInDialog=!0,this.iotmanForm=r()({},this.newIotmanForm,this.pObj))},handleClick:function(){this.submitBasemanForm("iotmanForm")},submitBasemanForm:function(t){var e=this;this.$refs[t].validate(function(a){if(!a)return!1;e.submitBasemanFormImpl(t),console.log("哈哈哈哈哈哈",e[t].id)})},submitBasemanFormImpl:function(t){var e=this,a=void 0,n=!0,i="";this[t].id>0?(a="/act",n=!1,i="iotmanup"):(a="/act",i="iotmanadd");var o=r()({},this[t]);console.log("呵呵呵",this[t].id),o._hy=i,this.callSrvAct(a,o).then(function(t){console.log("res:",t,e.isInDialog),e.isInDialog?n?t.data&&t.data.inst?e.$emit("submitOk",t.data.inst):t.data&&e.$emit("submitOk",t.data||{}):e.$emit("submitOk",o):e.$router.push({name:"iotman"})})},goback:function(){this.$router.push({name:"iotman"})}},watch:{paramsObj:function(t){this.pObj.id!=this.paramsObj.id&&(this.pObj=r()({},this.paramsObj),this.pObjChanged())}},computed:{},beforeCreate:function(){},created:function(){},beforeMount:function(){},beforeUpdate:function(){},updated:function(){},beforeDestroy:function(){},destroyed:function(){}},o=(a("eq8a"),a("KHd+")),s=Object(o.a)(i,function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("el-container",{attrs:{direction:"vertical"}},[a("el-form",{ref:"iotmanForm",attrs:{model:t.iotmanForm}},[a("el-form-item",{attrs:{label:"设备ID",prop:""}},[a("el-input")],1),t._v(" "),a("el-form-item",{attrs:{label:"设备名称",prop:"title",rules:{required:!0,trigger:"input"}}},[a("el-input",{model:{value:t.iotmanForm.title,callback:function(e){t.$set(t.iotmanForm,"title",e)},expression:"iotmanForm.title"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"设备描述",prop:"equdes",rules:{required:!0,trigger:"input"}}},[a("el-input",{model:{value:t.iotmanForm.equdes,callback:function(e){t.$set(t.iotmanForm,"equdes",e)},expression:"iotmanForm.equdes"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"设备IP",prop:"equip",rules:{required:!0,trigger:"input"}}},[a("el-input",{model:{value:t.iotmanForm.equip,callback:function(e){t.$set(t.iotmanForm,"equip",e)},expression:"iotmanForm.equip"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"厂商",prop:"factory",rules:{required:!0,trigger:"input"}}},[a("el-input",{model:{value:t.iotmanForm.factory,callback:function(e){t.$set(t.iotmanForm,"factory",e)},expression:"iotmanForm.factory"}})],1)],1),t._v(" "),a("div",[a("div",[a("span",[t._v("创建时间：\n            ")]),t._v(" "),a("span")]),t._v(" "),a("div",[a("span",[t._v("首次通信时间：\n            ")]),t._v(" "),a("span")]),t._v(" "),a("div",[a("span",[t._v("最后通信时间：\n            ")]),t._v(" "),a("span")]),t._v(" "),a("div",[a("span",[t._v("工作时长：\n            ")]),t._v(" "),a("span")])]),t._v(" "),a("div",{staticStyle:{"text-align":"center"}},[a("el-button",{on:{click:t.goback}},[a("span",[t._v("返回\n            ")])]),t._v(" "),a("el-button",{on:{click:t.handleClick}},[a("span",[t._v("保存\n            ")])])],1)],1)},[],!1,null,"9fd4d16a",null);s.options.__file="index.vue";e.default=s.exports},Q7v3:function(t,e,a){},eq8a:function(t,e,a){"use strict";var n=a("Q7v3");a.n(n).a},y2a5:function(t,e,a){"use strict";var n=a("gDS+"),r=a.n(n),i=a("4d7F"),o=a.n(i);e.a={methods:{cb_null:function(t){return!1},cb_goBack:function(t){return this.$router.go(-1),!0},cb_success:function(t){return!(!t.status||"SUCCESS"!=t.status)||(this.$message.error(t.msg),!1)},cb_showMess:function(t){return this.$message.success(t.msg),!0},gen_sameValue:function(t,e){return function(a,n,r){n!=t?r(new Error(e)):r()}},refreshCaptcha:function(t){t.preventDefault(),t.target.src=this.hysrvport+"/captcha?"+Math.random()},add_authToken:function(t){return t.length<1?"":t=t.indexOf("?")>-1?t+"&Authorization="+this.apiSrv.token:t+"?Authorization="+this.apiSrv.token},callServer:function(t,e,a){var n=this;this.apiSrv.callServer(t,e).then(function(t){if(console.log("res ------",t),a)for(var e=0;e<a.length;e++){if(a[e])if(!a[e](t))break}}).catch(function(t){console.log("err:",t),n.$message.error(t)})},callSrvAct:function(t,e){var a=this;return new o.a(function(n,r){a.apiSrv.callServer(t,e).then(function(t){t.status&&"SUCCESS"==t.status?n(t):(console.log("res:",t),a.$message.error(t.msg))}).catch(function(t){console.log("err:",t),a.$message.error(t)})})},gen_list_cb:function(t,e){var a=this;if(!t||t.length<1)return this.cb_null;var n=t+"Data",r=t+"TotalRec",i=t+"TotalPage",o=t+"RecOfPage",s=t+"CurPage";return function(t){return e&&1==e&&t.data.cp>1?a[n]=a[n].concat(t.data.pageData||[]):a[n]=t.data.pageData||[],a[r]=t.data.totalRec,a[o]=t.data.rop,a[s]=t.data.cp,a[i]=t.data.rop>0?Math.ceil(t.data.totalRec/t.data.rop):0,t.data.detailData&&(a[n+"dData"]=t.data.detailData||[]),!0}},gen_selmulti_change:function(t){var e=this;if(!t||t.length<1)return this.cb_null;var a=t+"MultiSel";return function(t){if(e[a]){for(var n=[],r=0;r<t.length;r++)n.push(t[r].id);e[a]=n}}},refreshPage2:function(t,e,a,n,i,o){var s=t+"QueryArr",u=t+"CbPage",c=JSON.parse(r()(this[s]));if(c&&c.length>0)for(var l=c.length-1;l>-1;l--)console.log("qpsArr[i].val",c[l].val,0==c[l].val,""!=c[l].val),0==c[l].val&&(console.log("i:",l),c.splice(l,1));console.log("qpsArr:",c,this[s]);var m={qps:c||"",rop:a,cp:n};return this[t+"SortParas"]&&(m.sps=this[t+"SortParas"]),m._hy=i,this.callServer(e,m,[this.cb_success,this[u],o])},refreshPage:function(t,e,a,n){var r=t+"RecOfPage",i=t+"CurPage";this[i]<1&&(this[i]=1),this.refreshPage2(t,e,this[r],this[i],a,n)},queryPageData:function(t,e,a,n,i,o){if(!t||t.length<1)return this.cb_null;var s=t+"QueryArr",u=JSON.parse(r()(this[s]));if(u&&u.length>0)for(var c=u.length-1;c>-1;c--)0==u[c].val&&u.splice(c,1);console.log("qpsArr:",u);var l={qps:u||[],rop:a,cp:n};return this[t+"SortParas"]&&(l.sps=this[t+"SortParas"]),l._hy=i,this.callServer(e,l,o)}}}}}]);