(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-1746"],{BCol:function(t,e,n){"use strict";var a=n("lFKM");n.n(a).a},lFKM:function(t,e,n){},loDg:function(t,e,n){"use strict";n.r(e);var a=n("P2sY"),r=n.n(a),o=n("EJiy"),s=n.n(o),i={name:"labelimport",mixins:[n("y2a5").a],components:{},props:{},data:function(){return{importData:[],num:0,success:0,fail:0,repeat:0}},mounted:function(){},methods:{importfxx:function(t){var e=this;e.num=0,e.success=0,e.fail=0,e.repeat=0,console.log(this.$refs.upload.value),this.file=event.currentTarget.files[0],console.log(this.file);var n=this.file,a=new FileReader;FileReader.prototype.readAsBinaryString=function(t){var n,a,r="";window.console.log(this);var o=new FileReader;o.onload=function(t){window.console.log(t);for(var s=new Uint8Array(o.result),i=s.byteLength,l=0;l<i;l++)r+=String.fromCharCode(s[l]);n=XLSX.read(r,{type:"binary"}),a=XLSX.utils.sheet_to_json(n.Sheets[n.SheetNames[0]]),window.console.log("呵呵呵",a),console.log("哈哈哈",a);for(var c=[],u=0;u<a.length;u++)c.push({name:a[u].姓名,type:a[u].类型,factory:a[u].厂商});window.console.log(c),e.importData=c,console.log(e.$refs.upload.value),console.log(document.getElementById("upload").value)},o.readAsArrayBuffer(t)},a.readAsBinaryString(n)},fixdata:function(t){for(var e="",n=0,a=10240;n<t.byteLength/a;++n)e+=String.fromCharCode.apply(null,new Uint8Array(t.slice(n*a,n*a+a)));return e+=String.fromCharCode.apply(null,new Uint8Array(t.slice(n*a)))},SizeChange:function(){},CurChange:function(){},exportExcel:function(){this.num=0,this.success=0,this.fail=0,this.repeat=0;var t=XLSX.utils.json_to_sheet([{"姓名":"","类型":"","厂商":""}]);console.log(t),this.openDownloadDialog(this.sheet2blob(t),"模板教程.xlsx")},sheet2blob:function(t,e){var n={SheetNames:[e=e||"sheet1"],Sheets:{}};n.Sheets[e]=t;var a=XLSX.write(n,{bookType:"xlsx",bookSST:!1,type:"binary"});return new Blob([function(t){for(var e=new ArrayBuffer(t.length),n=new Uint8Array(e),a=0;a!=t.length;++a)n[a]=255&t.charCodeAt(a);return e}(a)],{type:"application/octet-stream"})},openDownloadDialog:function(t,e){"object"==(void 0===t?"undefined":s()(t))&&t instanceof Blob&&(t=URL.createObjectURL(t));var n,a=document.createElement("a");a.href=t,a.download=e||"",window.MouseEvent?n=new MouseEvent("click"):(n=document.createEvent("MouseEvents")).initMouseEvent("click",!0,!1,window,0,0,0,0,0,!1,!1,!1,!1,0,null),a.dispatchEvent(n)},uploadOk:function(){var t=this;this.num=0,this.success=0,this.fail=0,this.repeat=0,this.num=this.importData.length,console.log("上传数据",this.importData);for(var e=0;e<this.importData.length;e++){var n=r()({},this.importData[e]);console.log("数据",this.importData),n._hy="tagsadd",this.callSrvAct("/act",n).then(function(e){console.log("后台返回",e),"SUCCESS"==e.status?t.success=t.success+1:"FAILED"==e.status&&(t.fail=t.fail+1)})}}},watch:{},computed:{},beforeCreate:function(){},created:function(){},beforeMount:function(){},beforeUpdate:function(){},updated:function(){},beforeDestroy:function(){},destroyed:function(){}},l=(n("BCol"),n("KHd+")),c=Object(l.a)(i,function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("el-container",{attrs:{direction:"vertical"}},[n("div",[n("el-row",{attrs:{autogenchild:!1,childcol:24}},[n("span",[t._v("提示：需保证上传的excel文件为单一文件\n            ")])]),t._v(" "),n("div",{staticStyle:{margin:"10px 0px"}},[n("span",[t._v("请先\n            ")]),t._v(" "),n("el-button",{on:{click:t.exportExcel}},[n("span",[t._v("下载模板和教程\n                ")])]),t._v(" "),n("span",[t._v("仔细阅读后，按要求填写车辆信息，并上传摄像机信息表格\n            ")])],1),t._v(" "),n("div",{staticStyle:{display:"flex","justify-content":"space-between"}},[n("div",{staticStyle:{display:"flex",height:"36px","line-height":"35px"}},[n("span",[t._v("上传的文件：\n                ")]),t._v(" "),n("el-input",{staticStyle:{width:"250px"},attrs:{placeholder:"文件路径"}}),t._v(" "),n("el-input",{ref:"upload",attrs:{type:"file",id:"upload"},on:{change:function(e){t.importfxx(this)}}}),t._v(" "),n("el-button",{on:{click:t.uploadOk}},[n("span",[t._v("确认上传\n                    ")])])],1),t._v(" "),n("div",[n("span",[t._v("上传共\n                ")]),t._v(" "),n("span",[t._v(t._s(t.num)+"\n                ")]),t._v(" "),n("span",[t._v("条  成功\n                ")]),t._v(" "),n("span",{staticStyle:{color:"green"}},[t._v(t._s(t.success)+"\n                ")]),t._v(" "),n("span",{staticStyle:{color:"green"}},[t._v("条\n                ")]),t._v(" "),n("span",[t._v("失败\n                ")]),t._v(" "),n("span",{staticStyle:{color:"red"}},[t._v(t._s(t.fail)+"\n                ")]),t._v(" "),n("span",{staticStyle:{color:"red"}},[t._v("条\n                ")]),t._v(" "),n("span",[t._v("重复\n                ")]),t._v(" "),n("span",{staticStyle:{color:"blue"}},[t._v(t._s(t.repeat)+"\n                ")]),t._v(" "),n("span",{staticStyle:{color:"blue"}},[t._v("条\n                ")])])]),t._v(" "),n("el-table",{attrs:{data:t.importData,"header-cell-style":{background:"rgba(241,246,254,1)",fontWeight:"500",color:"#333333",fontSize:"16px","text-align":"center"}}},[n("el-table-column",{attrs:{label:"姓名",prop:"name"}}),t._v(" "),n("el-table-column",{attrs:{label:"类型",prop:"type"}}),t._v(" "),n("el-table-column",{attrs:{label:"厂商",prop:"factory"}})],1)],1)])},[],!1,null,"5f21f2d6",null);c.options.__file="index.vue";e.default=c.exports},y2a5:function(t,e,n){"use strict";var a=n("gDS+"),r=n.n(a),o=n("4d7F"),s=n.n(o);e.a={methods:{cb_null:function(t){return!1},cb_goBack:function(t){return this.$router.go(-1),!0},cb_success:function(t){return!(!t.status||"SUCCESS"!=t.status)||(this.$message.error(t.msg),!1)},cb_showMess:function(t){return this.$message.success(t.msg),!0},gen_sameValue:function(t,e){return function(n,a,r){a!=t?r(new Error(e)):r()}},refreshCaptcha:function(t){t.preventDefault(),t.target.src=this.hysrvport+"/captcha?"+Math.random()},add_authToken:function(t){return t.length<1?"":t=t.indexOf("?")>-1?t+"&Authorization="+this.apiSrv.token:t+"?Authorization="+this.apiSrv.token},callServer:function(t,e,n){var a=this;this.apiSrv.callServer(t,e).then(function(t){if(console.log("res ------",t),n)for(var e=0;e<n.length;e++){if(n[e])if(!n[e](t))break}}).catch(function(t){console.log("err:",t),a.$message.error(t)})},callSrvAct:function(t,e){var n=this;return new s.a(function(a,r){n.apiSrv.callServer(t,e).then(function(t){t.status&&"SUCCESS"==t.status?a(t):(console.log("res:",t),n.$message.error(t.msg))}).catch(function(t){console.log("err:",t),n.$message.error(t)})})},gen_list_cb:function(t,e){var n=this;if(!t||t.length<1)return this.cb_null;var a=t+"Data",r=t+"TotalRec",o=t+"TotalPage",s=t+"RecOfPage",i=t+"CurPage";return function(t){return e&&1==e&&t.data.cp>1?n[a]=n[a].concat(t.data.pageData||[]):n[a]=t.data.pageData||[],n[r]=t.data.totalRec,n[s]=t.data.rop,n[i]=t.data.cp,n[o]=t.data.rop>0?Math.ceil(t.data.totalRec/t.data.rop):0,t.data.detailData&&(n[a+"dData"]=t.data.detailData||[]),!0}},gen_selmulti_change:function(t){var e=this;if(!t||t.length<1)return this.cb_null;var n=t+"MultiSel";return function(t){if(e[n]){for(var a=[],r=0;r<t.length;r++)a.push(t[r].id);e[n]=a}}},refreshPage2:function(t,e,n,a,o,s){var i=t+"QueryArr",l=t+"CbPage",c=JSON.parse(r()(this[i]));if(c&&c.length>0)for(var u=c.length-1;u>-1;u--)console.log("qpsArr[i].val",c[u].val,0==c[u].val,""!=c[u].val),0==c[u].val&&(console.log("i:",u),c.splice(u,1));console.log("qpsArr:",c,this[i]);var h={qps:c||"",rop:n,cp:a};return this[t+"SortParas"]&&(h.sps=this[t+"SortParas"]),h._hy=o,this.callServer(e,h,[this.cb_success,this[l],s])},refreshPage:function(t,e,n,a){var r=t+"RecOfPage",o=t+"CurPage";this[o]<1&&(this[o]=1),this.refreshPage2(t,e,this[r],this[o],n,a)},queryPageData:function(t,e,n,a,o,s){if(!t||t.length<1)return this.cb_null;var i=t+"QueryArr",l=JSON.parse(r()(this[i]));if(l&&l.length>0)for(var c=l.length-1;c>-1;c--)0==l[c].val&&l.splice(c,1);console.log("qpsArr:",l);var u={qps:l||[],rop:n,cp:a};return this[t+"SortParas"]&&(u.sps=this[t+"SortParas"]),u._hy=o,this.callServer(e,u,s)}}}}}]);