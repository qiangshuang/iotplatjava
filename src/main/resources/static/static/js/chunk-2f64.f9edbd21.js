(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-2f64"],{"/iky":function(t,e,a){},qIWP:function(t,e,a){"use strict";var s=a("/iky");a.n(s).a},vcah:function(t,e,a){"use strict";a.r(e);var s=a("gDS+"),i=a.n(s),l=a("MQ60"),n=a.n(l),o={name:"roledata",mixins:[a("y2a5").a],components:{Draggable:n.a},data:function(){return{setuData:[],roleDataForm:{id:0,role_id:this.$route.params.id,scope_table:"",scope_ids:"",sort:1,memo:"",version:0,cs1:"",cs2:"",op_name:"",cs1_title:"",ids_title:""},scopeidDatas:[],scopeTableDatas:[],dbTableData:[],delIds:[]}},mounted:function(){var t=this;this.callSrvAct("/dbtableinfo",{_hy:"dbtableinfo",encode:"ipincloud",version:"1.0.0"}).then(function(e){console.log("db table :",e),t.dbTableData=e.data||[]}),this.getDataList()},methods:{getDataList:function(){var t=this,e={_hy:"roledatalist",qps:[{name:"role_id",val:this.$route.params.id,op:"="}]};this.callSrvAct("/actq",e).then(function(e){t.setuData=e.data||[]})},removItem:function(t,e){var a=e.id;if(a>0){for(var s=!1,i=0;i<this.delIds.length;i++)if(this.delIds[i]==a){s=!0;break}s||this.delIds.push(a)}this.setuData.splice(t,1)},changeDataRange:function(t,e){console.log("v,item:",t,e),"公司"==t&&(e.scope_ids=this.$store.state.globaldata.org.id+"",e.ids_title=this.$store.state.globaldata.org.title)},changeColRange:function(t,e){var a=t.colName,s=t.memo;e.cs1_title||(e.cs1_title="");var i=","+e.cs1+",",l=","+e.cs1_title+",";i.indexOf(","+a+",")>-1?(i=i.replace(","+a+",",","),l=l.replace(","+s+",",","),","==i[0]&&(e.cs1=i.slice(1),e.cs1_title=l.slice(1)),i.length>0&&","==i[i.length-1]&&(e.cs1=i.slice(1,i.length-1),e.cs1_title=l.slice(1,l.length-1))):(e.cs1=e.cs1.length>0?e.cs1+","+a:a,e.cs1_title=e.cs1_title.length>0?e.cs1_title+","+s:s)},showSetSel:function(t){return t==this.$store.state.globaldata.org.id+""?"公司":""},showTableMemo:function(t){for(var e=0;e<this.dbTableData.length;e++)if(t==this.dbTableData[e].name)return this.dbTableData[e].memo||t;return t},showOpnameTitle:function(t){return"read"==t?"读取":"write"==t?"保存":"del"==t?"删除":""},showCs1:function(t){return"read"==t||"write"==t},getCs1CheckDatas:function(t){for(var e=0;e<this.dbTableData.length;e++)if(t==this.dbTableData[e].name)return this.dbTableData[e].cols||[];return[]},handleClose:function(){this.$router.go(-1)},saveRoleData:function(){var t=this;this.$confirm("此操作将影响到角色的数据操作范围, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){for(var e=0;e<t.setuData.length;e++){if(!t.setuData[e].scope_ids)return void t.$message.error(t.showTableMemo(t.setuData[e].scope_table)+":"+t.showOpnameTitle(t.setuData[e].op_name)+" 未设置数据范围.");t.setuData[e].sort=e+1}var a=t.setuData,s=(t.delIds,{_hy:"roledataset",role_data:a,role_id:t.$route.params.id});console.log("upData:",s),t.callSrvAct("/act",s).then(function(e){t.delIds=[],t.getDataList(),t.$message.success("数据保存已成功.")})}).catch(function(e){t.$message.error(e)})},handleMoveStart:function(t){},handleMove:function(){return!0},hideEntityTable:function(t){if(!t)return!0;for(var e=0,a=0;a<this.setuData.length;a++)this.setuData[a].scope_table==t&&(e+=1);return 3==e||t.lastIndexOf("_attr")!=t.length-5&&t.indexOf("_")>-1},haveInSetu:function(t){for(var e=0;e<this.setuData.length;e++)if(this.setuData[e].scope_table==t)return!0;return!1},handleMoveEnd:function(t){return!0},handleItemAdd:function(t){var e=t.newIndex,a=JSON.parse(i()(this.setuData[e]));console.log("item:",a,this.setuData.length);for(var s=a.name,l=[],n="",o=0;o<e&&o<this.setuData.length;o++)this.setuData[o].scope_table==s&&(n=n+this.setuData[o].op_name+",");for(var c=e+1;c<this.setuData.length;c++)if(this.setuData[c].scope_table==s){n=n+this.setuData[c].op_name+",";break}n=","+n;for(var r,d=["read","write","del"],h=0;h<d.length;h++)n.indexOf(","+d[h]+",")<0&&l.push({id:0,role_id:this.$route.params.id,scope_table:s,scope_ids:"",sort:e+h,memo:"",cs1:"",cs2:"",version:0,op_name:d[h],cs1_title:"",ids_title:""});if(!(l.length>0))return this.setuData.splice(e,1),!0;(r=this.setuData).splice.apply(r,[e,1].concat(l))}}},c=(a("qIWP"),a("KHd+")),r=Object(c.a)(o,function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("div",{staticClass:"form-header form-header-back"},[a("svg-icon",{staticClass:"icon",attrs:{"icon-class":"account"}}),a("span",{staticStyle:{height:"24px",display:"inline-block","margin-top":"12px"}},[t._v("角色数据设置")]),t._v(" "),a("el-button",{staticClass:"header-func",on:{click:t.handleClose}},[a("svg-icon",{attrs:{"icon-class":"close"}}),t._v("返回")],1),t._v(" "),a("el-button",{staticClass:"header-func",on:{click:t.saveRoleData}},[a("svg-icon",{staticClass:"icon",attrs:{"icon-class":"save"}}),t._v("保存")],1)],1),t._v(" "),a("el-main",{staticClass:"db-main"},[a("el-container",{staticStyle:{"overflow-y":"hidden"},attrs:{direction:"horizontal"}},[a("el-aside",{staticStyle:{width:"250px",height:"100%","overflow-y":"hidden","border-right":"1px solid #eee"}},[a("el-scrollbar",{staticClass:"scrollc tblist"},[a("draggable",{attrs:{tag:"ul",list:t.dbTableData,options:{group:{name:"roledata",pull:"clone",put:!1},sort:!1,ghostClass:"ghost"},move:t.handleMove},on:{start:t.handleMoveStart}},t._l(t.dbTableData,function(e,s){return t.hideEntityTable(e.name)?t._e():a("li",{key:s,staticClass:"li_tb"},[a("span",{staticStyle:{"padding-left":"10px",width:"150px"}},[t._v(t._s(e.memo||e.name))])])})),t._v(" "),a("div",{staticStyle:{height:"2px"}})],1),t._v(" "),a("div",{staticStyle:{position:"absolute",height:"15px",bottom:"0px",left:"0px",width:"249px",background:"#fff"}})],1),t._v(" "),a("el-main",{staticStyle:{height:"100%","overflow-y":"hidden",padding:"0px",margin:"0px"}},[a("el-scrollbar",{staticClass:"scrollcode "},[a("draggable",{staticClass:"code-list",attrs:{tag:"ul",options:{group:{name:"roledata"},put:{function:"canPut"},ghostClass:"ghost"}},on:{end:t.handleMoveEnd,add:t.handleItemAdd},model:{value:t.setuData,callback:function(e){t.setuData=e},expression:"setuData"}},t._l(t.setuData,function(e,s){return a("li",{key:s,staticClass:"li_tb"},[a("span",{staticClass:"leftt"},[t._v("数据表单:")]),t._v(" "),a("span",{staticStyle:{"padding-left":"10px","font-size":"14px"}},[t._v(t._s(t.showTableMemo(e.scope_table))+"\n                ")]),t._v(" "),a("span",{staticClass:"leftt"},[t._v("动作名称:")]),t._v(" "),a("span",{staticStyle:{"padding-left":"10px","font-size":"14px"}},[t._v(t._s(t.showOpnameTitle(e.op_name)))]),t._v(" "),a("el-button",{staticStyle:{float:"right","margin-right":"10px"},attrs:{type:"text",size:"small"},on:{click:function(a){t.removItem(s,e)}}},[t._v("移除")]),t._v(" "),a("div",[a("span",{staticClass:"leftt"},[t._v("数据范围:")]),t._v(" "),a("span",{staticStyle:{"padding-left":"10px","font-size":"14px"}},[t._v(t._s(e.ids_title))]),t._v(" "),a("el-popover",{staticStyle:{float:"right","margin-right":"-26px"},attrs:{placement:"right",trigger:"click"}},[a("el-button",{attrs:{slot:"reference",type:"text",size:"small"},slot:"reference"},[t._v("设置")]),t._v(" "),a("el-radio-group",{attrs:{size:"mini",value:t.showSetSel(e.scope_ids)}},[a("el-radio",{staticStyle:{display:"block"},attrs:{label:"公司"},on:{change:function(a){t.changeDataRange("公司",e)}}}),t._v(" "),a("el-radio",{staticStyle:{display:"block"},attrs:{label:"自定义"},on:{change:function(a){t.changeDataRange("自定义",e)}}})],1)],1)],1),t._v(" "),t.showCs1(e.op_name)?a("div",[a("span",{staticClass:"leftt"},[t._v("隐藏数列:")]),t._v(" "),a("span",{staticClass:"showtitle"},[t._v(t._s(e.cs1_title))]),t._v(" "),a("el-popover",{staticStyle:{float:"right","margin-right":"10px"},attrs:{placement:"right",trigger:"click"}},[a("el-button",{attrs:{slot:"reference",type:"text",size:"small"},slot:"reference"},[t._v("设置")]),t._v(" "),a("el-checkbox-group",{attrs:{size:"mini",value:e.cs1.split(",")}},t._l(t.getCs1CheckDatas(e.scope_table),function(s,i){return a("el-checkbox",{key:s.colName,staticStyle:{display:"block"},attrs:{label:s.colName},on:{change:function(a){t.changeColRange(s,e)}}},[t._v(t._s(s.memo)+"\n                      ")])}))],1)],1):t._e()],1)})),t._v(" "),a("div",{staticStyle:{height:"2px"}})],1),t._v(" "),a("div",{staticStyle:{position:"absolute",height:"15px",bottom:"0px",left:"251px",right:"0px",background:"#fff"}})],1)],1)],1)],1)},[],!1,null,"2dc01226",null);r.options.__file="index.vue";e.default=r.exports}}]);