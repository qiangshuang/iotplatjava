(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-4d2c"],{PDX7:function(t,e,i){"use strict";i.r(e);var n={name:"viewdetails",mixins:[i("y2a5").a],components:{},props:{paramsObj:{type:Object,default:function(){return{}}}},data:function(){return{countData:[],countID:"",pObj:{},policeData:[]}},mounted:function(){var t=this;this.pObj=this.paramsObj,this.countID=this.pObj.algorithm_id;this.callSrvAct("/actq",{_hy:"algorithmacclist",qps:[{name:"title",val:"",op:"like"}]}).then(function(e){t.countData=e.data.pageData||[],t.countData.splice(0,0,{id:-1,title:"全部"})}),this.callSrvAct("/alarmdtl",this.pObj.algorithm_id).then(function(e){t.policeData=e.data||[],t.setCvsWH(),t.paint(t.pObj.border,t.pObj.box,t.pObj.orgName+":"+t.pObj.personName,t.pObj.personId+"")})},methods:{countChange:function(){var t=this;console.log(this.countID),this.callSrvAct("/alarmdtl",this.countID).then(function(e){t.policeData=e.data||[],t.policeData&&t.policeData.length>0?(t.pObj=t.policeData[0],t.countID=t.pObj.algorithm_id,t.setCvsWH(),t.paint(t.pObj.border,t.pObj.box,t.pObj.orgName+":"+t.pObj.personName,t.pObj.personId+"")):(t.pObj=null,t.countID=-1,t.setCvsWH())})},handlie:function(t){this.pObj=t,this.setCvsWH(),this.paint(this.pObj.border,this.pObj.box,this.pObj.orgName+":"+this.pObj.personName,this.pObj.personId+"")},time:function(t){var e=new Date(1e3*t);return e.getFullYear()+"-"+(e.getMonth()+1)+"-"+e.getDate()+"  "+(e.getHours()<10?"0"+e.getHours():e.getHours())+":"+(e.getMinutes()<10?"0"+e.getMinutes():e.getMinutes())+":"+(e.getSeconds()<10?"0"+e.getSeconds():e.getSeconds())},falsePositive:function(){var t=this;this.callSrvAct("/misstate",[this.pObj.id]).then(function(e){t.callSrvAct("/alarmdtl",t.countID).then(function(e){t.policeData=e.data,null==t.policeData?(t.pObj.camera_name="",t.pObj.algorithm_name="",t.pObj.alarm_time=""):t.pObj=t.policeData[0],t.setCvsWH(),t.pObj&&(t.countID=t.pObj.algorithm_id,t.paint(t.pObj.border,t.pObj.box,t.pObj.orgName+":"+t.pObj.personName,t.pObj.personId+""))})})},end:function(){var t=this;this.callSrvAct("/confirmState",[this.pObj.id]).then(function(e){t.callSrvAct("/alarmdtl",t.countID).then(function(e){t.policeData=e.data,null==t.policeData?(t.pObj.camera_name="",t.pObj.algorithm_name="",t.pObj.alarm_time=""):t.pObj=t.policeData[0],t.setCvsWH(),t.pObj&&(t.countID=t.pObj.algorithm_id,t.paint(t.pObj.border,t.pObj.box,t.pObj.orgName+":"+t.pObj.personName,t.pObj.personId+""))})})},ends:function(){for(var t=this,e=[],i=0;i<this.policeData.length;i++)e.push(this.policeData[i].id);this.callSrvAct("/confirmState",e).then(function(e){console.log("返回值1",e),t.callSrvAct("/alarmdtl",t.countID).then(function(e){console.log("返回值2",e),t.policeData=e.data,console.log("返回值3",t.policeData),null==t.policeData?(t.pObj.camera_name="",t.pObj.algorithm_name="",t.pObj.alarm_time=""):t.pObj=t.policeData[0],t.setCvsWH(),t.pObj&&(t.countID=t.pObj.algorithm_id,t.paint(t.pObj.border,t.pObj.box,t.pObj.orgName+":"+t.pObj.personName,t.pObj.personId+""))})})},isUndef:function(t){return void 0===t||"undefined"==t||null==t},setCvsWH:function(){var t=document.getElementById("cvsdiv"),e=window.getComputedStyle(t,null).getPropertyValue("height"),i=window.getComputedStyle(t,null).getPropertyValue("width");e&&e.length>2&&(e=0|Number(e.substring(0,e.length-2))),i&&i.length>2&&(i=0|Number(i.substring(0,i.length-2)));var n=document.getElementById("cvs2");n.width=i,n.height=e},paint:function(t,e,i,n){var a=[];t&&t.length>0&&(a=JSON.parse(t));var o=[],l=[],s=document.getElementById("cvs2");if(e&&e.length>0){var c=JSON.parse(e);if(c&&c.length)for(var r=0;r<c.length-1;r+=2)o.push(c[r+1]),o.push(c[r]);l=[[o[0]*s.width|0,o[1]*s.height|0],[o[2]*s.width|0,o[1]*s.height|0],[o[2]*s.width|0,o[3]*s.height|0],[o[0]*s.width|0,o[3]*s.height|0],[o[0]*s.width|0,o[1]*s.height|0]]}var p=document.getElementById("cvs2").getContext("2d");if(!this.isUndef(a)&&a.length>0){p.lineWidth=1,p.strokeStyle="#ffdc35",p.beginPath(),p.moveTo(a[0][0]*s.width,a[0][1]*s.height);for(var h=0;h<a.length;h++)h+1<a.length&&(p.lineTo(a[h+1][0]*s.width|0,a[h+1][1]*s.height|0),p.stroke());p.closePath()}if(!this.isUndef(l)&&l.length>0){p.lineWidth=1,p.strokeStyle="#f9f900",p.beginPath(),p.moveTo(l[0][0],l[0][1]);for(var d=0;d<l.length-1;d++)p.lineTo(l[d+1][0],l[d+1][1]),p.stroke();p.closePath()}"-1"!=n&&""!=n&&("0"==n||n.length>0&&i&&i.length>0&&(p.font="bold 21px Arial",p.textAlign="end",p.textBaseAlign="top",p.fillStyle="#F56C6C",p.fillText(i,s.width-6,20)))},genDownUrl:function(t){if(!t||t.length<1)return"";var e=t.split(";");return!e||e.length<2?"":this.hysrvport+"/hydownload?btnEncode=headupload&relateType=headimage&filename="+e[1]+"&Authorization="+this.apiSrv.token}},watch:{},computed:{}},a=(i("sCUJ"),i("KHd+")),o=Object(a.a)(n,function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("el-container",{attrs:{direction:"vertical"}},[i("div",{staticStyle:{display:"flex","justify-content":"space-between"}},[i("div",[i("div",[i("span",[t._v("摄像机名称：\n                ")]),t._v(" "),i("span",[t._v(t._s(t.pObj.camera_name)+"\n                ")])]),t._v(" "),i("div",{staticStyle:{margin:"10px 0px"}},[i("span",[t._v("算法类型：\n                ")]),t._v(" "),i("el-select",{on:{change:t.countChange},model:{value:t.countID,callback:function(e){t.countID=e},expression:"countID"}},t._l(t.countData,function(t,e){return i("el-option",{key:t.id,attrs:{label:t.title,value:t.id}})}))],1)]),t._v(" "),i("div",[i("el-button",{staticStyle:{"background-color":"red"},on:{click:t.falsePositive}},[i("span",{staticStyle:{color:"#ffffff"}},[t._v("误报\n                ")])]),t._v(" "),i("el-button",{on:{click:t.end}},[i("span",[t._v("结束当前预警\n                ")])])],1)]),t._v(" "),i("div",{staticStyle:{display:"flex","justify-content":"space-between"}},[i("div",{staticStyle:{width:"65%",height:"400px","background-color":"#E0E0E0",padding:"10px"}},[i("div",[i("span",[t._v(t._s(t.pObj.camera_name+"-"+t.pObj.algorithm_name)+"\n                ")]),t._v(" "),i("span",{staticStyle:{float:"right",color:"#BEBEBE"}},[t._v(t._s(t.gcomm.v2datetimestr(t.pObj.alarm_time))+"\n                ")])]),t._v(" "),i("div",{ref:"cvsdiv",staticStyle:{width:"100%",height:"360px",border:"1px solid #ADADAD","margin-top":"6px",position:"relative"},attrs:{id:"cvsdiv"}},[i("img",{attrs:{src:t.genDownUrl(t.pObj.alarm_img),width:"100%",height:"360"}}),t._v(" "),i("div",{staticStyle:{display:"block",width:"100%",height:"360px",position:"absolute",left:"0px",top:"0px"}},[i("canvas",{ref:"cvs2",staticStyle:{width:"100%",height:"100%"},attrs:{id:"cvs2"}},[t._v("不支持canvas")])])])]),t._v(" "),i("div",{staticStyle:{width:"30%",height:"400px",border:"1px solid #ADADAD",padding:"10px","background-color":"#E0E0E0"}},[i("div",{staticStyle:{"border-bottom":"1px solid #ADADAD","padding-bottom":"5px"}},[i("span",{staticStyle:{color:"#000000"}},[t._v("报警列表\n                ")]),t._v(" "),i("span",{staticStyle:{color:"blue",float:"right"},on:{click:t.ends}},[t._v("全部结束\n                ")])]),t._v(" "),i("div",{staticStyle:{width:"100%",height:"360px","overflow-y":"auto"}},t._l(t.policeData,function(e,n){return i("div",{key:e.id,staticStyle:{"border-bottom":"1px solid #ADADAD","line-height":"30px","text-align":"center"},on:{click:function(i){t.handlie(e)}}},[i("span",[t._v(t._s(t.gcomm.v2datetimestr(e.alarm_time))+"\n                    ")])])}))])])])},[],!1,null,"51b29cba",null);o.options.__file="index.vue";e.default=o.exports},cUhY:function(t,e,i){},sCUJ:function(t,e,i){"use strict";var n=i("cUhY");i.n(n).a}}]);