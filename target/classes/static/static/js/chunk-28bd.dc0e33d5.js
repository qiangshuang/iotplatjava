(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-28bd"],{"/iky":function(t,e,a){},qIWP:function(t,e,a){"use strict";var s=a("/iky");a.n(s).a},vcah:function(t,e,a){"use strict";a.r(e);var s=a("gDS+"),i=a.n(s),n=a("MQ60"),l=a.n(n),o={name:"roledata",mixins:[a("y2a5").a],components:{Draggable:l.a},data:function(){return{setuData:[],roleDataForm:{id:0,role_id:this.$route.params.id,scope_table:"",scope_ids:"",sort:1,memo:"",version:0,cs1:"",cs2:"",op_name:"",cs1_title:"",ids_title:""},scopeidDatas:[],scopeTableDatas:[],dbTableData:[],delIds:[]}},mounted:function(){var t=this;this.callSrvAct("/dbtableinfo",{_hy:"dbtableinfo",encode:"ipincloud",version:"1.0.0"}).then(function(e){console.log("db table :",e),t.dbTableData=e.data||[]}),this.getDataList()},methods:{getDataList:function(){var t=this,e={_hy:"roledatalist",qps:[{name:"role_id",val:this.$route.params.id,op:"="}]};this.callSrvAct("/actq",e).then(function(e){t.setuData=e.data||[]})},removItem:function(t,e){var a=e.id;if(a>0){for(var s=!1,i=0;i<this.delIds.length;i++)if(this.delIds[i]==a){s=!0;break}s||this.delIds.push(a)}this.setuData.splice(t,1)},changeDataRange:function(t,e){console.log("v,item:",t,e),"公司"==t&&(e.scope_ids=this.$store.state.globaldata.org.id+"",e.ids_title=this.$store.state.globaldata.org.title)},changeColRange:function(t,e){var a=t.colName,s=t.memo;e.cs1_title||(e.cs1_title="");var i=","+e.cs1+",",n=","+e.cs1_title+",";i.indexOf(","+a+",")>-1?(i=i.replace(","+a+",",","),n=n.replace(","+s+",",","),","==i[0]&&(e.cs1=i.slice(1),e.cs1_title=n.slice(1)),i.length>0&&","==i[i.length-1]&&(e.cs1=i.slice(1,i.length-1),e.cs1_title=n.slice(1,n.length-1))):(e.cs1=e.cs1.length>0?e.cs1+","+a:a,e.cs1_title=e.cs1_title.length>0?e.cs1_title+","+s:s)},showSetSel:function(t){return t==this.$store.state.globaldata.org.id+""?"公司":""},showTableMemo:function(t){for(var e=0;e<this.dbTableData.length;e++)if(t==this.dbTableData[e].name)return this.dbTableData[e].memo||t;return t},showOpnameTitle:function(t){return"read"==t?"读取":"write"==t?"保存":"del"==t?"删除":""},showCs1:function(t){return"read"==t||"write"==t},getCs1CheckDatas:function(t){for(var e=0;e<this.dbTableData.length;e++)if(t==this.dbTableData[e].name)return this.dbTableData[e].cols||[];return[]},handleClose:function(){this.$router.go(-1)},saveRoleData:function(){var t=this;this.$confirm("此操作将影响到角色的数据操作范围, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){for(var e=0;e<t.setuData.length;e++){if(!t.setuData[e].scope_ids)return void t.$message.error(t.showTableMemo(t.setuData[e].scope_table)+":"+t.showOpnameTitle(t.setuData[e].op_name)+" 未设置数据范围.");t.setuData[e].sort=e+1}var a=t.setuData,s=(t.delIds,{_hy:"roledataset",role_data:a,role_id:t.$route.params.id});console.log("upData:",s),t.callSrvAct("/act",s).then(function(e){t.delIds=[],t.getDataList(),t.$message.success("数据保存已成功.")})}).catch(function(e){t.$message.error(e)})},handleMoveStart:function(t){},handleMove:function(){return!0},hideEntityTable:function(t){if(!t)return!0;for(var e=0,a=0;a<this.setuData.length;a++)this.setuData[a].scope_table==t&&(e+=1);return 3==e||t.lastIndexOf("_attr")!=t.length-5&&t.indexOf("_")>-1},haveInSetu:function(t){for(var e=0;e<this.setuData.length;e++)if(this.setuData[e].scope_table==t)return!0;return!1},handleMoveEnd:function(t){return!0},handleItemAdd:function(t){var e=t.newIndex,a=JSON.parse(i()(this.setuData[e]));console.log("item:",a,this.setuData.length);for(var s=a.name,n=[],l="",o=0;o<e&&o<this.setuData.length;o++)this.setuData[o].scope_table==s&&(l=l+this.setuData[o].op_name+",");for(var r=e+1;r<this.setuData.length;r++)if(this.setuData[r].scope_table==s){l=l+this.setuData[r].op_name+",";break}l=","+l;for(var c,h=["read","write","del"],u=0;u<h.length;u++)l.indexOf(","+h[u]+",")<0&&n.push({id:0,role_id:this.$route.params.id,scope_table:s,scope_ids:"",sort:e+u,memo:"",cs1:"",cs2:"",version:0,op_name:h[u],cs1_title:"",ids_title:""});if(!(n.length>0))return this.setuData.splice(e,1),!0;(c=this.setuData).splice.apply(c,[e,1].concat(n))}}},r=(a("qIWP"),a("KHd+")),c=Object(r.a)(o,function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("div",{staticClass:"form-header form-header-back"},[a("svg-icon",{staticClass:"icon",attrs:{"icon-class":"account"}}),a("span",{staticStyle:{height:"24px",display:"inline-block","margin-top":"12px"}},[t._v("角色数据设置")]),t._v(" "),a("el-button",{staticClass:"header-func",on:{click:t.handleClose}},[a("svg-icon",{attrs:{"icon-class":"close"}}),t._v("返回")],1),t._v(" "),a("el-button",{staticClass:"header-func",on:{click:t.saveRoleData}},[a("svg-icon",{staticClass:"icon",attrs:{"icon-class":"save"}}),t._v("保存")],1)],1),t._v(" "),a("el-main",{staticClass:"db-main"},[a("el-container",{staticStyle:{"overflow-y":"hidden"},attrs:{direction:"horizontal"}},[a("el-aside",{staticStyle:{width:"250px",height:"100%","overflow-y":"hidden","border-right":"1px solid #eee"}},[a("el-scrollbar",{staticClass:"scrollc tblist"},[a("draggable",{attrs:{tag:"ul",list:t.dbTableData,options:{group:{name:"roledata",pull:"clone",put:!1},sort:!1,ghostClass:"ghost"},move:t.handleMove},on:{start:t.handleMoveStart}},t._l(t.dbTableData,function(e,s){return t.hideEntityTable(e.name)?t._e():a("li",{key:s,staticClass:"li_tb"},[a("span",{staticStyle:{"padding-left":"10px",width:"150px"}},[t._v(t._s(e.memo||e.name))])])})),t._v(" "),a("div",{staticStyle:{height:"2px"}})],1),t._v(" "),a("div",{staticStyle:{position:"absolute",height:"15px",bottom:"0px",left:"0px",width:"249px",background:"#fff"}})],1),t._v(" "),a("el-main",{staticStyle:{height:"100%","overflow-y":"hidden",padding:"0px",margin:"0px"}},[a("el-scrollbar",{staticClass:"scrollcode "},[a("draggable",{staticClass:"code-list",attrs:{tag:"ul",options:{group:{name:"roledata"},put:{function:"canPut"},ghostClass:"ghost"}},on:{end:t.handleMoveEnd,add:t.handleItemAdd},model:{value:t.setuData,callback:function(e){t.setuData=e},expression:"setuData"}},t._l(t.setuData,function(e,s){return a("li",{key:s,staticClass:"li_tb"},[a("span",{staticClass:"leftt"},[t._v("数据表单:")]),t._v(" "),a("span",{staticStyle:{"padding-left":"10px","font-size":"14px"}},[t._v(t._s(t.showTableMemo(e.scope_table))+"\n                ")]),t._v(" "),a("span",{staticClass:"leftt"},[t._v("动作名称:")]),t._v(" "),a("span",{staticStyle:{"padding-left":"10px","font-size":"14px"}},[t._v(t._s(t.showOpnameTitle(e.op_name)))]),t._v(" "),a("el-button",{staticStyle:{float:"right","margin-right":"10px"},attrs:{type:"text",size:"small"},on:{click:function(a){t.removItem(s,e)}}},[t._v("移除")]),t._v(" "),a("div",[a("span",{staticClass:"leftt"},[t._v("数据范围:")]),t._v(" "),a("span",{staticStyle:{"padding-left":"10px","font-size":"14px"}},[t._v(t._s(e.ids_title))]),t._v(" "),a("el-popover",{staticStyle:{float:"right","margin-right":"-26px"},attrs:{placement:"right",trigger:"click"}},[a("el-button",{attrs:{slot:"reference",type:"text",size:"small"},slot:"reference"},[t._v("设置")]),t._v(" "),a("el-radio-group",{attrs:{size:"mini",value:t.showSetSel(e.scope_ids)}},[a("el-radio",{staticStyle:{display:"block"},attrs:{label:"公司"},on:{change:function(a){t.changeDataRange("公司",e)}}}),t._v(" "),a("el-radio",{staticStyle:{display:"block"},attrs:{label:"自定义"},on:{change:function(a){t.changeDataRange("自定义",e)}}})],1)],1)],1),t._v(" "),t.showCs1(e.op_name)?a("div",[a("span",{staticClass:"leftt"},[t._v("隐藏数列:")]),t._v(" "),a("span",{staticClass:"showtitle"},[t._v(t._s(e.cs1_title))]),t._v(" "),a("el-popover",{staticStyle:{float:"right","margin-right":"10px"},attrs:{placement:"right",trigger:"click"}},[a("el-button",{attrs:{slot:"reference",type:"text",size:"small"},slot:"reference"},[t._v("设置")]),t._v(" "),a("el-checkbox-group",{attrs:{size:"mini",value:e.cs1.split(",")}},t._l(t.getCs1CheckDatas(e.scope_table),function(s,i){return a("el-checkbox",{key:s.colName,staticStyle:{display:"block"},attrs:{label:s.colName},on:{change:function(a){t.changeColRange(s,e)}}},[t._v(t._s(s.memo)+"\n                      ")])}))],1)],1):t._e()],1)})),t._v(" "),a("div",{staticStyle:{height:"2px"}})],1),t._v(" "),a("div",{staticStyle:{position:"absolute",height:"15px",bottom:"0px",left:"251px",right:"0px",background:"#fff"}})],1)],1)],1)],1)},[],!1,null,"2dc01226",null);c.options.__file="index.vue";e.default=c.exports},y2a5:function(t,e,a){"use strict";var s=a("gDS+"),i=a.n(s),n=a("4d7F"),l=a.n(n);e.a={methods:{cb_null:function(t){return!1},cb_goBack:function(t){return this.$router.go(-1),!0},cb_success:function(t){return!(!t.status||"SUCCESS"!=t.status)||(this.$message.error(t.msg),!1)},cb_showMess:function(t){return this.$message.success(t.msg),!0},gen_sameValue:function(t,e){return function(a,s,i){s!=t?i(new Error(e)):i()}},refreshCaptcha:function(t){t.preventDefault(),t.target.src=this.hysrvport+"/captcha?"+Math.random()},add_authToken:function(t){return t.length<1?"":t=t.indexOf("?")>-1?t+"&Authorization="+this.apiSrv.token:t+"?Authorization="+this.apiSrv.token},callServer:function(t,e,a){var s=this;this.apiSrv.callServer(t,e).then(function(t){if(console.log("res ------",t),a)for(var e=0;e<a.length;e++){if(a[e])if(!a[e](t))break}}).catch(function(t){console.log("err:",t),s.$message.error(t)})},callSrvAct:function(t,e){var a=this;return new l.a(function(s,i){a.apiSrv.callServer(t,e).then(function(t){t.status&&"SUCCESS"==t.status?s(t):(console.log("res:",t),a.$message.error(t.msg))}).catch(function(t){console.log("err:",t),a.$message.error(t)})})},gen_list_cb:function(t,e){var a=this;if(!t||t.length<1)return this.cb_null;var s=t+"Data",i=t+"TotalRec",n=t+"TotalPage",l=t+"RecOfPage",o=t+"CurPage";return function(t){return e&&1==e&&t.data.cp>1?a[s]=a[s].concat(t.data.pageData||[]):a[s]=t.data.pageData||[],a[i]=t.data.totalRec,a[l]=t.data.rop,a[o]=t.data.cp,a[n]=t.data.rop>0?Math.ceil(t.data.totalRec/t.data.rop):0,t.data.detailData&&(a[s+"dData"]=t.data.detailData||[]),!0}},gen_selmulti_change:function(t){var e=this;if(!t||t.length<1)return this.cb_null;var a=t+"MultiSel";return function(t){if(e[a]){for(var s=[],i=0;i<t.length;i++)s.push(t[i].id);e[a]=s}}},refreshPage2:function(t,e,a,s,n,l){var o=t+"QueryArr",r=t+"CbPage",c=JSON.parse(i()(this[o]));if(c&&c.length>0)for(var h=c.length-1;h>-1;h--)console.log("qpsArr[i].val",c[h].val,0==c[h].val,""!=c[h].val),0==c[h].val&&(console.log("i:",h),c.splice(h,1));console.log("qpsArr:",c,this[o]);var u={qps:c||"",rop:a,cp:s};return this[t+"SortParas"]&&(u.sps=this[t+"SortParas"]),u._hy=n,this.callServer(e,u,[this.cb_success,this[r],l])},refreshPage:function(t,e,a,s){var i=t+"RecOfPage",n=t+"CurPage";this[n]<1&&(this[n]=1),this.refreshPage2(t,e,this[i],this[n],a,s)},queryPageData:function(t,e,a,s,n,l){if(!t||t.length<1)return this.cb_null;var o=t+"QueryArr",r=JSON.parse(i()(this[o]));if(r&&r.length>0)for(var c=r.length-1;c>-1;c--)0==r[c].val&&r.splice(c,1);console.log("qpsArr:",r);var h={qps:r||[],rop:a,cp:s};return this[t+"SortParas"]&&(h.sps=this[t+"SortParas"]),h._hy=n,this.callServer(e,h,l)}}}}}]);