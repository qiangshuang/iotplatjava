(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-6bc7"],{JYr9:function(t,r,e){"use strict";e.r(r);var s=e("P2sY"),o=e.n(s),a={name:"restart",mixins:[e("y2a5").a],components:{},props:{paramsObj:{type:Object,default:function(){return{}}}},data:function(){return{pObj:{},progresss:0,barcolors:"",pArr:[],reason:!1}},mounted:function(){this.pObj=o()({},this.paramsObj),this.pObj.progress=0,this.pObj.barcolor="",this.pObj.barstate="",this.pArr.push(this.pObj),this.startup()},methods:{startup:function(){var t=this,r=this,e={_hy:"algorithmrestart",qps:[{name:"id",val:[this.pObj.id],op:"in"}]};console.log("upData:",e),this.callSrvAct("/algorithmrestart",e).then(function(e){console.log("后台返回值",e),"FAILD"==e.status?(t.count("FAILD",e.data),r.barcolors="red",t.pArr[0].barcolor="red"):"SUCCESS"==e.data[0].result?(t.count("SUCCESS",e.data),r.barcolors="green",t.pArr[0].barcolor="green",t.$emit("submitOk")):"FAILD"==e.data[0].result&&(t.count("FAILD",e.data),r.barcolors="red",t.pArr[0].barcolor="red")})},count:function(t,r){var e=this,s=this;s.progress=0,this.interval=window.setInterval(function(){s.progresss+=40,e.pArr[0].progress+=40,s.$set(s.pArr,0,s.pArr[0]),s.progresss>=100&&(s.progresss=100,s.pArr[0].progress=100,"SUCCESS"==t?e.pArr[0].barstate="success":"FAILD"==t&&(e.pArr[0].barstate="exception",e.pArr[0].msg=r[0].msg,e.reason=!0),window.clearInterval(s.interval),console.log(t))},500)},colse:function(){this.reason=!1,this.$emit("submitClose")}},watch:{},computed:{},beforeCreate:function(){},created:function(){},beforeMount:function(){},beforeUpdate:function(){},updated:function(){},beforeDestroy:function(){this.reason=!1,window.clearInterval(this.interval)},destroyed:function(){}},n=(e("aLxM"),e("KHd+")),l=Object(n.a)(a,function(){var t=this,r=t.$createElement,e=t._self._c||r;return e("el-container",{attrs:{direction:"vertical"}},[e("div",{staticStyle:{display:"flex",margin:"10px 0px"}},[e("span",[t._v("总进度：\n        ")]),t._v(" "),e("el-progress",{staticStyle:{width:"200px"},attrs:{percentage:t.progresss,color:t.barcolors}})],1),t._v(" "),e("el-table",{attrs:{data:t.pArr,"header-cell-style":{background:"rgba(241,246,254,1)",fontWeight:"500",color:"#333333",fontSize:"16px"}}},[e("el-table-column",{attrs:{label:"算法名称",prop:"title"}}),t._v(" "),e("el-table-column",{attrs:{label:"摄像机名称",prop:"cameratitle"}}),t._v(" "),e("el-table-column",{attrs:{label:"启动时间"},scopedSlots:t._u([{key:"default",fn:function(r){return[e("span",[t._v(t._s(t.gcomm.v2datetimestr(r.row.opentime))+"\n                ")])]}}])}),t._v(" "),e("el-table-column",{attrs:{label:"重启进度"},scopedSlots:t._u([{key:"default",fn:function(t){return[e("el-progress",{attrs:{percentage:t.row.progress,status:t.row.barstate,color:t.row.barcolor}})]}}])}),t._v(" "),t.reason?e("el-table-column",{attrs:{label:"失败原因",prop:"msg"}}):t._e()],1),t._v(" "),e("div",{staticStyle:{"text-align":"right","margin-top":"10px"}},[e("el-button",{on:{click:t.colse}},[e("span",[t._v("关闭\n            ")])])],1)],1)},[],!1,null,"20c81b16",null);l.options.__file="index.vue";r.default=l.exports},ZTru:function(t,r,e){},aLxM:function(t,r,e){"use strict";var s=e("ZTru");e.n(s).a}}]);