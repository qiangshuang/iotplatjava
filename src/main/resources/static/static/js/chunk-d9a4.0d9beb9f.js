(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-d9a4"],{R2zS:function(t,e,i){},qQ1a:function(t,e,i){"use strict";i.r(e);var a={name:"alarminformation",mixins:[i("y2a5").a],components:{},props:{},data:function(){return{algorithmAlarm:{}}},mounted:function(){var t=this,e=this.$route.params.id;if(console.log("view id:",e),e){var i={_hy:"alarmlist",qps:[{name:"id",val:e,op:"="}]};this.callSrvAct("/actq",i).then(function(e){console.log("警告消息",e),e.data&&e.data.pageData&&e.data.pageData.length>0&&(t.algorithmAlarm=e.data.pageData[0],t.$nextTick(function(){t.setCvsWH(),t.paint(t.algorithmAlarm.border,t.algorithmAlarm.box,t.algorithmAlarm.orgName+":"+t.algorithmAlarm.personName,t.algorithmAlarm.personId+"")}))})}},methods:{genDownUrl:function(t){if(!t||t.length<1)return"";var e=t.split(";");return!e||e.length<2?"":this.hysrvport+"/hydownload?btnEncode=headupload&relateType=camera&filename="+e[1]+"&Authorization="+this.apiSrv.token},isUndef:function(t){return void 0===t||"undefined"==t||null==t},setCvsWH:function(){var t=document.getElementById("cvsdiv"),e=window.getComputedStyle(t,null).getPropertyValue("height"),i=window.getComputedStyle(t,null).getPropertyValue("width");e&&e.length>2&&(e=0|Number(e.substring(0,e.length-2))),i&&i.length>2&&(i=0|Number(i.substring(0,i.length-2)));var a=document.getElementById("cvs2");a.width=i,a.height=e},paint:function(t,e,i,a){var n=[];t&&t.length>0&&(n=JSON.parse(t));var l=[],r=[],o=document.getElementById("cvs2");if(e&&e.length>0){var s=JSON.parse(e);if(s&&s.length)for(var h=0;h<s.length-1;h+=2)l.push(s[h+1]),l.push(s[h]);r=[[l[0]*o.width|0,l[1]*o.height|0],[l[2]*o.width|0,l[1]*o.height|0],[l[2]*o.width|0,l[3]*o.height|0],[l[0]*o.width|0,l[3]*o.height|0],[l[0]*o.width|0,l[1]*o.height|0]]}var c=document.getElementById("cvs2").getContext("2d");if(!this.isUndef(n)&&n.length>0){c.lineWidth=1,c.strokeStyle="#ffdc35",c.beginPath(),c.moveTo(n[0][0]*o.width,n[0][1]*o.height);for(var d=0;d<n.length;d++)d+1<n.length&&(c.lineTo(n[d+1][0]*o.width|0,n[d+1][1]*o.height|0),c.stroke());c.closePath()}if(!this.isUndef(r)&&r.length>0){c.lineWidth=1,c.strokeStyle="#f9f900",c.beginPath(),c.moveTo(r[0][0],r[0][1]);for(var g=0;g<r.length-1;g++)c.lineTo(r[g+1][0],r[g+1][1]),c.stroke();c.closePath()}"-1"!=a&&""!=a&&("0"==a?(c.textAlign="end",c.textBaseAlign="top",c.fillStyle="#F56C6C",c.fillText("未识别此人",o.width-6,16)):a.length>0&&i&&i.length>0&&(c.textAlign="end",c.textBaseAlign="top",c.fillStyle="#F56C6C",c.fillText(i,o.width-6,16)))},falsePositive:function(){var t=this;this.callSrvAct("/misstate",[this.algorithmAlarm.id]).then(function(e){t.state="误报",t.$router.push({name:"warnlist"})})},ending:function(){var t=this;this.callSrvAct("/confirmState",[this.algorithmAlarm.id]).then(function(e){t.state="已确认",t.$router.push({name:"warnlist"})})}},watch:{},computed:{}},n=(i("uPbm"),i("KHd+")),l=Object(n.a)(a,function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("el-container",{attrs:{direction:"vertical"}},[i("div",{staticStyle:{width:"534px"}},[i("div",{staticStyle:{display:"flex","justify-content":"space-between"}},[i("div",{staticStyle:{"line-height":"35.6px"}},[i("span",[t._v(t._s(t.algorithmAlarm.algorithm_name)+"\n                ")]),t._v(" "),i("span",{staticStyle:{color:"#BEBEBE","margin-left":"50px"}},[t._v(t._s(t.gcomm.v2datetimestr(t.algorithmAlarm.alarm_time))+"\n                ")])]),t._v(" "),i("div",["未确认"==t.algorithmAlarm.state?i("el-button",{staticStyle:{"background-color":"#D9001B"},on:{click:t.falsePositive}},[i("span",{staticStyle:{color:"#ffffff"}},[t._v("误报\n                    ")])]):t._e(),t._v(" "),"已确认"==t.algorithmAlarm.state?i("el-button",{attrs:{type:"warning"}},[i("span",[t._v("误报\n                    ")])]):t._e(),t._v(" "),"已确认"==t.algorithmAlarm.state?i("el-button",{staticStyle:{"background-color":"#02A7F0"}},[i("span",{staticStyle:{color:"#ffffff"}},[t._v("已确认\n                    ")])]):t._e(),t._v(" "),"未确认"==t.algorithmAlarm.state?i("el-button",{on:{click:t.ending}},[i("span",[t._v("结束当前预警\n                    ")])]):t._e()],1)]),t._v(" "),i("div",{staticStyle:{display:"flex","justify-content":"space-between",margin:"10px 0px"}},[i("div",[i("span",[t._v(t._s(t.algorithmAlarm.camera_name)+"\n                ")])]),t._v(" "),i("div",[i("span",[t._v(t._s(t.algorithmAlarm.personName)+"\n                ")])])]),t._v(" "),i("div",{ref:"cvsdiv",staticStyle:{display:"block",width:"100%",height:"300px",border:"1px solid #BEBEBE",margin:"10px 0px",position:"relative"},attrs:{id:"cvsdiv"}},[i("img",{staticStyle:{width:"100%",height:"100%"},attrs:{src:t.genDownUrl(t.algorithmAlarm.alarm_img),width:"534",height:"300"}}),t._v(" "),i("div",{staticStyle:{display:"block",width:"534px",height:"300px",position:"absolute",left:"0px",top:"0px"}},[i("canvas",{ref:"cvs2",staticStyle:{width:"100%",height:"100%"},attrs:{id:"cvs2"}},[t._v("不支持canvas")])])])])])},[],!1,null,"7ed4ac3e",null);l.options.__file="index.vue";e.default=l.exports},uPbm:function(t,e,i){"use strict";var a=i("R2zS");i.n(a).a}}]);