(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-3687"],{"+uOk":function(t,e,a){},Ftel:function(t,e,a){"use strict";var i=a("+uOk");a.n(i).a},c9YV:function(t,e,a){"use strict";a.r(e);var i={name:"alarmdetails",mixins:[a("y2a5").a],components:{},props:{paramsObj:{type:Object,default:function(){return{}}}},data:function(){return{countData:[],countID:"",pObj:{},policeData:[]}},mounted:function(){var t=this;this.pObj=this.paramsObj,console.log("传过来的",this.pObj);this.callSrvAct("/actq",{_hy:"algorithmacclist",qps:[{name:"title",val:"",op:"like"}]}).then(function(e){console.log("类型数据",e),t.countData=e.data.pageData||[],t.countData.splice(0,0,{id:-1,title:"全部"})}),this.callSrvAct("/realalarmdtl",{algorithm_id:this.paramsObj.algorithm_id,camera_id:this.paramsObj.camera_id}).then(function(e){console.log("当前数据",e);var a=document.getElementById("cvs");a.height=a.height,t.policeData=e.data||[],0==t.policeData.length?(t.pObj.region="",t.pObj.camera_name="",t.pObj.algorithm_name="",t.pObj.alarm_time=""):(t.pObj=t.policeData[0],t.paint(t.pObj.border,t.pObj.box)),t.countID=t.pObj.algorithm_name,console.log(t.pObj)})},methods:{countChange:function(){var t=this;console.log(this.countID);var e=document.getElementById("cvs");e.height=e.height,this.callSrvAct("/realalarmdtl",{algorithm_id:this.countID,camera_id:this.paramsObj.camera_id}).then(function(e){console.log("返回值",e),t.policeData=e.data||[],0==t.policeData.length?(t.pObj.region="",t.pObj.camera_name="",t.pObj.algorithm_name="",t.pObj.alarm_time=""):(t.pObj=t.policeData[0],t.paint(t.pObj.border,t.pObj.box))})},handlie:function(t){this.pObj=t,console.log(t);var e=document.getElementById("cvs");e.height=e.height,this.paint(this.pObj.border,this.pObj.box)},paint:function(t,e){var a=document.getElementById("cvs"),i=this.$refs.cvs.getContext("2d"),n=this.$refs.cvs.getContext("2d");if(void 0==t);else{var o=JSON.parse(t);if(o&&0!=o.length)for(var c=0;c<o.length;c++)c+1<o.length&&(i.moveTo(o[c][0]*a.width,o[c][1]*a.height),i.lineTo(o[c+1][0]*a.width,o[c+1][1]*a.height),i.strokeStyle="red",i.stroke())}if(void 0==e);else for(var l=JSON.parse(e),r=[[l[0]*a.width,l[1]*a.height],[l[2]*a.width,l[1]*a.height],[l[2]*a.width,l[3]*a.height],[l[0]*a.width,l[3]*a.height],[l[0]*a.width,l[1]*a.height]],s=0;s<r.length-1;s++)n.moveTo(r[s][0],r[s][1]),n.lineTo(r[s+1][0],r[s+1][1]),n.strokeStyle="red",n.stroke()},end:function(t){var e=this,a=document.getElementById("cvs");a.height=a.height,this.callSrvAct("/confirmState",[t.id]).then(function(t){console.log("返回值1",t),e.callSrvAct("/realalarmdtl",{algorithm_id:e.countID,camera_id:e.paramsObj.camera_id}).then(function(t){console.log("返回值",t),e.policeData=t.data||[],0==e.policeData.length?(e.pObj.region="",e.pObj.camera_name="",e.pObj.algorithm_name="",e.pObj.alarm_time=""):(e.pObj=e.policeData[0],e.paint(e.pObj.border,e.pObj.box))})})},ends:function(){var t=this,e=[],a=document.getElementById("cvs");a.height=a.height;for(var i=0;i<this.policeData.length;i++)e.push(this.policeData[i].id);this.callSrvAct("/confirmState",e).then(function(e){console.log("返回值1",e),t.callSrvAct("/realalarmdtl",{algorithm_id:t.countID,camera_id:t.paramsObj.camera_id}).then(function(e){console.log("返回值",e),t.policeData=e.data||[],0==t.policeData.length?(t.pObj.region="",t.pObj.camera_name="",t.pObj.algorithm_name="",t.pObj.alarm_time=""):t.pObj=t.policeData[0]})})}},watch:{},computed:{},beforeCreate:function(){},created:function(){},beforeMount:function(){},beforeUpdate:function(){},updated:function(){},beforeDestroy:function(){},destroyed:function(){}},n=(a("Ftel"),a("KHd+")),o=Object(n.a)(i,function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("el-container",{attrs:{direction:"vertical"}},[a("div",{staticStyle:{display:"flex","justify-content":"space-between"}},[a("div",[a("div",[a("span",[t._v("监控位置：\n                ")]),t._v(" "),a("span",[t._v(t._s(t.pObj.region)+"\n                ")])]),t._v(" "),a("div",{staticStyle:{margin:"10px 0px"}},[a("span",[t._v("任务类型：\n                ")]),t._v(" "),a("el-select",{on:{change:t.countChange},model:{value:t.countID,callback:function(e){t.countID=e},expression:"countID"}},t._l(t.countData,function(t,e){return a("el-option",{key:t.id,attrs:{label:t.title,value:t.id}})}))],1)]),t._v(" "),a("div",[a("el-button",{attrs:{type:"danger"},on:{click:t.ends}},[a("span",[t._v("结束全部预警\n                ")])])],1)]),t._v(" "),a("div",{staticStyle:{display:"flex","justify-content":"space-between"}},[a("div",{staticStyle:{width:"65%",height:"400px","background-color":"#E0E0E0",padding:"10px"}},[a("div",[a("span",[t._v(t._s(t.pObj.camera_name+"-"+t.pObj.algorithm_name)+"\n                ")]),t._v(" "),a("span",{staticStyle:{float:"right",color:"#BEBEBE"}},[t._v(t._s(t.gcomm.v2datetimestr(t.pObj.alarm_time))+"\n                ")])]),t._v(" "),a("div",{staticStyle:{width:"100%",height:"360px",border:"20px solid #ADADAD",position:"relative"}},[a("img",{attrs:{src:t.pObj.alarm_img,width:"100%",height:"100%"}}),t._v(" "),a("span",{staticStyle:{display:"block",width:"100%",height:"100%",position:"absolute",left:"0px",top:"0px","z-index":"3"}},[a("canvas",{ref:"cvs",staticStyle:{width:"100%",height:"100%"},attrs:{id:"cvs"}},[t._v("不支持canvas")])])])]),t._v(" "),a("div",{staticStyle:{width:"30%",height:"400px",border:"1px solid #ADADAD",padding:"10px","background-color":"#E0E0E0"}},[a("div",{staticStyle:{"border-bottom":"1px solid #ADADAD","padding-bottom":"5px"}},[a("span",{staticStyle:{color:"#000000"}},[t._v("报警列表\n                ")])]),t._v(" "),a("div",{staticStyle:{width:"100%",height:"360px","overflow-y":"auto"}},t._l(t.policeData,function(e,i){return a("div",{key:e.id,staticStyle:{"border-bottom":"1px solid #ADADAD","line-height":"30px"}},[a("span",{on:{click:function(a){t.handlie(e)}}},[t._v(t._s(t.gcomm.v2datetimestr(e.alarm_time))+"\n                    ")]),t._v(" "),a("span",{staticStyle:{float:"right","margin-right":"10px"},on:{click:function(a){t.end(e)}}},[t._v("X\n                    ")])])}))])])])},[],!1,null,"a15b72c0",null);o.options.__file="index.vue";e.default=o.exports}}]);