(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-c4c5","chunk-0187","chunk-8367"],{"+3p/":function(e,t,i){"use strict";var a=i("3IIu");i.n(a).a},"13sV":function(e,t,i){},"3IIu":function(e,t,i){},Ax9J:function(e,t,i){"use strict";i.r(t);var a=i("gDS+"),l=i.n(a),n=i("uiSD"),r=i("QgN9"),s={name:"seleype",mixins:[i("y2a5").a],components:{Pushpeople:n.default,Configurefail:r.default},props:{},data:function(){return{algorithmaccData:[],algorithmaccTotalRec:0,algorithmaccTotalPage:0,algorithmaccRecOfPage:10,algorithmaccCurPage:1,algorithmaccCbPage:this.gen_list_cb("algorithmacc"),algorithmaccQueryArr:[],algorithmaccQueryInit:[],algorithmaccMultiSel:[],cameraData:[],cameraTotalRec:0,cameraTotalPage:0,cameraRecOfPage:10,cameraCurPage:1,cameraCbPage:this.gen_list_cb("camera"),cameraQueryArr:[],cameraQueryInit:[],cameraMultiSel:[],peopleData:[],peopleTotalRec:0,peopleTotalPage:0,peopleRecOfPage:10,peopleCurPage:1,peopleCbPage:this.gen_list_cb("people"),peopleQueryArr:[],peopleQueryInit:[],peopleMultiSel:[],show:!1,pushpeopleEdit:null,show1:!1,configurefailEdit:null,active:1,crossborderdirection:[{value:"外部",label:"外部"},{value:"内部",label:"内部"}],crossborderdirectionvalue:"",x:0,y:0,warningtime:0,whole:0,choice:1,timeslot:(new Date).getTime(),timeslotEnd:(new Date).getTime(),taskshow:!0,numArr:[],index:0,face:0,shi:1,fou:0,tableHeight:window.innerHeight-360,pagenum:1}},mounted:function(){this.refreshPage("algorithmacc","/actq","algorithmacclist"),this.refreshPage("camera","/actq","cameralist")},methods:{algorithmaccMultiSelChange:function(e){this.algorithmaccMultiSel=e},cameraMultiSelChange:function(e){this.cameraMultiSel=e},peopleMultiSelChange:function(e){this.peopleMultiSel=e},algorithmaccSizeChange:function(e){this.refreshPage2("algorithmacc","/actq",e,this.algorithmaccCurPage,"algorithmacclist")},algorithmaccCurChange:function(e){this.refreshPage2("algorithmacc","/actq",this.algorithmaccRecOfPage,e,"algorithmacclist")},cameraSizeChange:function(e){this.refreshPage2("camera","/actq",e,this.cameraCurPage,"cameralist")},cameraCurChange:function(e){this.refreshPage2("camera","/actq",this.cameraRecOfPage,e,"cameralist")},handleClick:function(){var e=this;if(2==this.active){this.numArr=[];for(var t=0;t<this.algorithmaccMultiSel.length;t++)for(var i=0;i<this.cameraMultiSel.length;i++)this.numArr.push({id:0,camera_id:this.cameraMultiSel[i].id,algorithm_id:this.algorithmaccMultiSel[t].id,accesscode:this.algorithmaccMultiSel[t].accesscode,pushtime_start:0,pushtime_end:0,algorithm_title:this.algorithmaccMultiSel[t].title,camera_title:this.cameraMultiSel[i].title,border:[],direction:"",distinguish:0,state:"",framerate:this.cameraMultiSel[i].framerate,resolution:this.cameraMultiSel[i].resolution,videoa:this.cameraMultiSel[i].videoa,pushaddress:this.cameraMultiSel[i].pushaddress,codec:this.cameraMultiSel[i].codec,algorithm_url:this.algorithmaccMultiSel[t].address,user_ids:"",user_titles:"",opentime:0,region_title:this.cameraMultiSel[i].region_title,relation_face:this.algorithmaccMultiSel[t].relation_face})}this.active=this.active+1,console.log("参数:",this.numArr),this.active>4&&(this.active=4),0==this.algorithmaccMultiSel.length?this.active=1:0==this.cameraMultiSel.length&&(this.active=2),3==this.active&&(this.index=0,console.log(this.numArr[this.index]),this.$nextTick(function(){e.video(e.numArr[e.index].pushaddress),document.getElementById("cvs").width=640,document.getElementById("cvs").height=480}))},goBack:function(){this.active=this.active-1,this.active<1&&(this.active=1)},complete:function(){var e=this;this.active=4;for(var t="",i="",a=0;a<this.peopleMultiSel.length;a++)a==this.peopleMultiSel.length-1?(t+=this.peopleMultiSel[a].title,i+=this.peopleMultiSel[a].id):(t=t+this.peopleMultiSel[a].title+",",i=i+this.peopleMultiSel[a].id+",");for(var n=0;n<this.numArr.length;n++)this.numArr[n].user_titles=t,this.numArr[n].user_ids=i,this.numArr[n].border=l()(this.numArr[n].border);var r=this.numArr;console.log("参数",r),this.callSrvAct("/algorithmopen",r).then(function(t){console.log("配置结果",t),e.configurefailEdit=t.data,e.show1=!0})},handlealgorithmChange:function(e){console.log(e),this.algorithmaccMultiSel=e,-1!=this.algorithmaccMultiSel[0].title.indexOf("越界")?this.taskshow=!0:this.taskshow=!1},handleChange:function(e){this.cameraMultiSel=e},paint:function(e){var t=document.getElementById("cvs");this.numArr[this.index].border.push([e.offsetX/t.width,e.offsetY/t.height]),console.log("canvas",e,t.width,t.height);var i=this.$refs.cvs.getContext("2d");0==this.x&&0==this.y&&(this.x=e.layerX,this.y=e.layery),i.moveTo(this.x,this.y),i.lineTo(e.layerX,e.layerY),i.strokeStyle="red",i.stroke(),this.x=e.layerX,this.y=e.layerY},handlePeople:function(){this.pushpeopleEdit={},this.show=!0},peopleChange:function(e){console.log(e),this.peopleMultiSel=e},peopledel:function(e,t){var i=this;this.$confirm("此操作将删除该项数, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){i.peopleData.splice(e,1)}).catch(function(e){i.$message.error(e)})},peopledels:function(){var e=this;this.$confirm("此操作将删除该项数, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){}).catch(function(t){e.$message.error(t)})},pushpeopleOK:function(e){console.log("传回来的",e);for(var t=0;t<e.length;t++)this.peopleData.push(e[t]);this.show=!1},pushpeopleSet:function(){},SizeChange:function(){},CurChange:function(){},enen:function(e){console.log("选中的值",this.timeslot,this.timeslotEnd),0==e?(this.numArr[this.index].pushtime_start=0,this.numArr[this.index].pushtime_end=0):(this.numArr[this.index].pushtime_start=this.timeslot,this.numArr[this.index].pushtime_end=this.timeslotEnd)},enen1:function(e){this.numArr[this.index].distinguish=0==e?0:1},peopleclose:function(){this.show=!1},configurefailclose:function(){this.show1=!1,this.$router.push({name:"algorithmm"})},configurefailOK:function(){this.algorithmaccMultiSel=[],this.cameraMultiSel=[],this.active=1,this.show1=!1},configurefailSet:function(){this.show1=!1,this.$router.push({name:"algorithmm"})},xxxxCurChange:function(e){this.warningtime=0;var t=document.getElementById("cvs");t.height=t.height,this.x=0,this.y=0,this.index=e-1,this.video(this.numArr[this.index].pushaddress)},xxxxSizeChange:function(){},video:function(e){if(console.log("参数--------------",e),flvjs.isSupported()){var t=document.getElementById("testplays");console.log(t);var i=flvjs.createPlayer({type:"flv",url:e});i.attachMediaElement(t),i.load(),i.play(),console.log("视频播放器",i)}},genDownUrls:function(e){return this.hysrvport+"/hydownload?btnEncode=headupload&relateType=face&filename="+e+"&Authorization="+this.apiSrv.token}},watch:{},computed:{},beforeCreate:function(){},created:function(){},beforeMount:function(){},beforeUpdate:function(){},updated:function(){},beforeDestroy:function(){},destroyed:function(){}},o=(i("V+se"),i("KHd+")),c=Object(o.a)(s,function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("el-container",{staticStyle:{height:"100%"},attrs:{direction:"vertical"}},[i("div",{staticStyle:{"text-align":"center"}},[i("span",[e._v("请依次进行任务配置\n        ")])]),e._v(" "),i("el-steps",{attrs:{active:e.active}},[i("el-step",{attrs:{title:"算法"}}),e._v(" "),i("el-step",{attrs:{title:"摄像机"}}),e._v(" "),i("el-step",{attrs:{title:"任务参数"}}),e._v(" "),i("el-step",{attrs:{title:"选择推送人员"}})],1),e._v(" "),1==e.active?i("div",[i("el-table",{ref:"algorithmacc",staticStyle:{margin:"10px 0px"},attrs:{height:e.tableHeight,"header-cell-style":{background:"#E6F4FF",fontWeight:"500",color:"#0A51C4",borderBottom:"2px solid #4093F7"},id:"algorithmacc",data:e.algorithmaccData},on:{"selection-change":e.handlealgorithmChange}},[i("el-table-column",{attrs:{type:"selection"}}),e._v(" "),i("el-table-column",{attrs:{label:"算法名称",prop:"title"}}),e._v(" "),i("el-table-column",{attrs:{label:"描述",prop:"describion"}})],1),e._v(" "),i("div",{staticStyle:{"text-align":"center"}},[i("el-pagination",{attrs:{total:e.algorithmaccTotalRec,"page-count":e.algorithmaccTotalPage,layout:"total, sizes, prev, pager, next"},on:{"size-change":e.algorithmaccSizeChange,"current-change":e.algorithmaccCurChange}})],1)],1):e._e(),e._v(" "),2==e.active?i("div",[i("el-table",{ref:"camera",staticStyle:{margin:"10px 0px"},attrs:{height:e.tableHeight,"header-cell-style":{background:"#E6F4FF",fontWeight:"500",color:"#0A51C4",borderBottom:"2px solid #4093F7"},id:"camera",data:e.cameraData},on:{"selection-change":e.handleChange}},[i("el-table-column",{attrs:{type:"selection"}}),e._v(" "),i("el-table-column",{attrs:{label:"摄像机名称",prop:"title"}}),e._v(" "),i("el-table-column",{attrs:{label:"位置",prop:"region_title"}}),e._v(" "),i("el-table-column",{attrs:{label:"视频地址",prop:"videoa"}}),e._v(" "),i("el-table-column",{attrs:{label:"分辨率",prop:"resolution"}}),e._v(" "),i("el-table-column",{attrs:{label:"帧率",prop:"framerate"}})],1),e._v(" "),i("div",{staticStyle:{"text-align":"center"}},[i("el-pagination",{attrs:{total:e.cameraTotalRec,"page-count":e.cameraTotalPage,layout:"total, sizes, prev, pager, next"},on:{"size-change":e.cameraSizeChange,"current-change":e.cameraCurChange}})],1)],1):e._e(),e._v(" "),3==e.active?i("div",{staticStyle:{border:"1px solid #000"}},[i("div",{staticStyle:{display:"flex","justify-content":"space-around","border-bottom":"1px solid #000"}},[i("div",{staticStyle:{position:"relative","background-color":"#000000",width:"640px",height:"480px"}},[i("video",{ref:"testplays",staticStyle:{width:"100%",height:"100%","object-fit":"fill"},attrs:{id:"testplays"}}),e._v(" "),i("span",{staticStyle:{display:"block",width:"640px",height:"480px",position:"absolute",left:"0px",top:"0px"}},[i("canvas",{ref:"cvs",staticStyle:{width:"100%",height:"100%",border:"1px solid blue"},attrs:{id:"cvs"},on:{click:e.paint}},[e._v("不支持canvas")])])]),e._v(" "),i("div",[i("el-row",{staticStyle:{"margin-top":"10px"},attrs:{autogenchild:!1,childcol:24}},[i("span",{staticStyle:{"font-size":"18px","font-weight":"500"}},[e._v(e._s(e.numArr[e.index].camera_title)+"\n                    ")])]),e._v(" "),i("el-row",{staticStyle:{"margin-top":"10px"},attrs:{autogenchild:!1,childcol:24}},[i("span",[e._v("摄像机位置：\n                    ")]),e._v(" "),i("span",[e._v(e._s(e.numArr[e.index].region_title)+"\n                    ")])]),e._v(" "),i("el-row",{staticStyle:{"margin-top":"10px"},attrs:{autogenchild:!1,childcol:24}},[i("span",[e._v("视频流地址：\n                    ")]),e._v(" "),i("span",[e._v(e._s(e.numArr[e.index].videoa)+"\n                    ")])]),e._v(" "),i("el-row",{staticStyle:{"margin-top":"10px"},attrs:{autogenchild:!1,childcol:24}},[i("span",[e._v("分辨率：\n                    ")]),e._v(" "),i("span",[e._v(e._s(e.numArr[e.index].resolution)+"\n                    ")])]),e._v(" "),i("el-row",{staticStyle:{margin:"10px 0px"},attrs:{autogenchild:!1,childcol:24}},[i("span",[e._v("帧率：\n                    ")]),e._v(" "),i("span",[e._v(e._s(e.numArr[e.index].framerate)+"\n                    ")])])],1)]),e._v(" "),i("div",{staticStyle:{display:"flex","justify-content":"space-between"}},[i("div",[-1!=e.numArr[e.index].algorithm_title.indexOf("越界")||-1!=e.numArr[e.index].algorithm_title.indexOf("人脸")?i("el-row",{staticStyle:{"margin-top":"10px"},attrs:{autogenchild:!1,childcol:24}},[i("span",{staticStyle:{"font-size":"18px","font-weight":"500","margin-left":"10px"}},[e._v("参数配置\n                    ")])]):e._e(),e._v(" "),-1!=e.numArr[e.index].algorithm_title.indexOf("越界")?i("el-row",{staticStyle:{"margin-top":"10px"},attrs:{autogenchild:!1,childcol:24}},[i("span",{staticStyle:{"margin-left":"40px"}},[e._v("越界边界：\n                    ")]),e._v(" "),i("span",{staticStyle:{color:"#aaaaaa"}},[e._v("越界检测划定的多边形界限\n                    ")])]):e._e(),e._v(" "),-1!=e.numArr[e.index].algorithm_title.indexOf("越界")?i("el-row",{staticStyle:{margin:"10px 0px"},attrs:{autogenchild:!1,childcol:24}},[i("span",{staticStyle:{"margin-left":"40px"}},[e._v("越界方向\n                    ")]),e._v(" "),i("el-select",{model:{value:e.numArr[e.index].direction,callback:function(t){e.$set(e.numArr[e.index],"direction",t)},expression:"numArr[index].direction"}},e._l(e.crossborderdirection,function(e,t){return i("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})}))],1):e._e(),e._v(" "),-1!=e.numArr[e.index].algorithm_title.indexOf("人脸")?i("el-row",{staticStyle:{margin:"10px 0px"},attrs:{autogenchild:!1,childcol:24}},[i("span",{staticStyle:{"margin-left":"40px"}},[e._v("人脸识别\n                    ")]),e._v(" "),i("el-radio-group",{on:{change:e.enen1},model:{value:e.face,callback:function(t){e.face=t},expression:"face"}},[i("el-radio",{attrs:{label:e.shi}},[i("span",[e._v("是\n                            ")])]),e._v(" "),i("el-radio",{attrs:{label:e.fou}},[i("span",[e._v("否\n                            ")])])],1)],1):e._e()],1),e._v(" "),i("div",[i("el-row",{staticStyle:{"margin-top":"10px"},attrs:{autogenchild:!1,childcol:24}},[i("span",{staticStyle:{"font-size":"18px","font-weight":"500","margin-left":"10px"}},[e._v("预警时间段\n                    ")])]),e._v(" "),i("div",{staticStyle:{display:"flex","margin-top":"16px"}},[i("div",[i("el-radio-group",{on:{change:e.enen},model:{value:e.warningtime,callback:function(t){e.warningtime=t},expression:"warningtime"}},[i("el-radio",{attrs:{label:e.whole}},[i("span",[e._v("全部\n                                ")])]),e._v(" "),i("el-radio",{staticStyle:{display:"block","margin-top":"23px"},attrs:{label:e.choice}},[i("span",[e._v("选择\n                                ")])])],1)],1),e._v(" "),i("div",{staticStyle:{"margin-top":"25px"}},[i("el-time-picker",{attrs:{"range-separator":"","value-format":"timestamp"},model:{value:e.timeslot,callback:function(t){e.timeslot=t},expression:"timeslot"}}),e._v(" "),i("span",[e._v("至\n                        ")]),e._v(" "),i("el-time-picker",{attrs:{"range-separator":"","value-format":"timestamp"},model:{value:e.timeslotEnd,callback:function(t){e.timeslotEnd=t},expression:"timeslotEnd"}})],1)])],1)])]):e._e(),e._v(" "),4==e.active?i("div",[i("div",{staticStyle:{margin:"10px 0px"}},[i("el-button",{on:{click:e.handlePeople}},[i("span",[i("i",{staticClass:"el-icon-plus"}),e._v(" 选择推送人员\n                ")])]),e._v(" "),i("el-button",{on:{click:e.peopledels}},[i("span",[e._v("批量删除\n                ")])])],1),e._v(" "),i("el-table",{ref:"people",staticStyle:{"margin-bottom":"10px"},attrs:{height:e.tableHeight,"header-cell-style":{background:"#E6F4FF",fontWeight:"500",color:"#0A51C4",borderBottom:"2px solid #4093F7"},data:e.peopleData},on:{"selection-change":e.peopleChange}},[i("el-table-column",{attrs:{type:"selection"}}),e._v(" "),i("el-table-column",{attrs:{label:"部门",prop:"parent_title"}}),e._v(" "),i("el-table-column",{attrs:{label:"姓名",prop:"title"}}),e._v(" "),i("el-table-column",{attrs:{label:"操作"},scopedSlots:e._u([{key:"default",fn:function(t){return[i("el-button",{on:{click:function(i){e.peopledel(t.$index,t.row)}}},[i("span",[e._v("删除\n                        ")])])]}}])})],1),e._v(" "),i("div",{staticStyle:{"text-align":"center"}},[i("el-pagination",{attrs:{layout:"total, sizes, prev, pager, next"},on:{"size-change":e.SizeChange,"current-change":e.CurChange}})],1)],1):e._e(),e._v(" "),3==e.active?i("div",{staticStyle:{"text-align":"center","margin-top":"10px"}},[i("el-pagination",{ref:"xxx",attrs:{"page-size":e.pagenum,total:e.numArr.length,"page-count":e.numArr.length,layout:"total, sizes, prev, pager, next,jumper","page-sizes":[1],id:"xxx"},on:{"size-change":e.xxxxSizeChange,"current-change":e.xxxxCurChange}})],1):e._e(),e._v(" "),i("div",{staticStyle:{"text-align":"center","margin-top":"20px"}},[2==e.active||3==e.active||4==e.active?i("el-button",{on:{click:e.goBack}},[i("span",[e._v("上一步\n            ")])]):e._e(),e._v(" "),1==e.active||2==e.active||3==e.active?i("el-button",{on:{click:e.handleClick}},[i("span",[e._v("下一步\n            ")])]):e._e(),e._v(" "),4==e.active?i("el-button",{on:{click:e.complete}},[i("span",[e._v("完成\n            ")])]):e._e()],1),e._v(" "),e.show?i("el-dialog",{attrs:{visible:e.show,title:"选择推送人员","destroy-on-close":!0,close:e.peopleclose},on:{"update:visible":function(t){e.show=t}}},[i("pushpeople",{attrs:{paramsObj:e.pushpeopleEdit},on:{submitOk:e.pushpeopleOK,submitClose:e.pushpeopleSet}})],1):e._e(),e._v(" "),e.show1?i("el-dialog",{attrs:{visible:e.show1,title:"配置结果","destroy-on-close":!0},on:{"update:visible":function(t){e.show1=t},close:e.configurefailclose}},[i("configurefail",{attrs:{paramsObj:e.configurefailEdit},on:{submitOk:e.configurefailOK,submitClose:e.configurefailSet}})],1):e._e()],1)},[],!1,null,"718e6eaa",null);c.options.__file="index.vue";t.default=c.exports},LEJw:function(e,t,i){},QgN9:function(e,t,i){"use strict";i.r(t);var a={name:"configurefail",mixins:[],components:{},props:{paramsObj:{type:Array,default:function(){return[]}}},data:function(){return{pArr:[]}},mounted:function(){this.pArr=this.paramsObj,console.log("数据",this.pArr)},methods:{continues:function(){this.$emit("submitOk")},close:function(){this.$emit("submitClose")},result:function(e){return"SUCCESS"==e?"成功":"失败"},reason:function(e){return"SUCCESS"==e.result?"":e.msg}},watch:{},computed:{},beforeCreate:function(){},created:function(){},beforeMount:function(){},beforeUpdate:function(){},updated:function(){},beforeDestroy:function(){},destroyed:function(){}},l=(i("+3p/"),i("KHd+")),n=Object(l.a)(a,function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("el-container",{attrs:{direction:"vertical"}},[i("el-table",{staticStyle:{margin:"10px 0px"},attrs:{data:e.pArr,"header-cell-style":{background:"rgba(241,246,254,1)",fontWeight:"500",color:"#333333",fontSize:"16px"}}},[i("el-table-column",{attrs:{label:"算法",prop:"algorithm_title"}}),e._v(" "),i("el-table-column",{attrs:{label:"摄像机",prop:"camera_title"}}),e._v(" "),i("el-table-column",{attrs:{label:"推送人员",prop:"user_titles"}}),e._v(" "),i("el-table-column",{attrs:{label:"配置结果"},scopedSlots:e._u([{key:"default",fn:function(t){return[i("span",[e._v(e._s(e.result(t.row.result))+"\n                ")])]}}])}),e._v(" "),i("el-table-column",{attrs:{label:"原因"},scopedSlots:e._u([{key:"default",fn:function(t){return[i("span",[e._v(e._s(e.reason(t.row))+"\n                ")])]}}])})],1),e._v(" "),i("div",{staticStyle:{"text-align":"center"}},[i("el-button",{on:{click:e.continues}},[i("span",[e._v("继续配置\n            ")])]),e._v(" "),i("el-button",{on:{click:e.close}},[i("span",[e._v("关闭\n            ")])])],1)],1)},[],!1,null,"2e984ae0",null);n.options.__file="index.vue";t.default=n.exports},RG45:function(e,t,i){"use strict";var a=i("LEJw");i.n(a).a},"RU/L":function(e,t,i){i("Rqdy");var a=i("WEpk").Object;e.exports=function(e,t,i){return a.defineProperty(e,t,i)}},Rqdy:function(e,t,i){var a=i("Y7ZC");a(a.S+a.F*!i("jmDH"),"Object",{defineProperty:i("2faE").f})},SEkw:function(e,t,i){e.exports={default:i("RU/L"),__esModule:!0}},"V+se":function(e,t,i){"use strict";var a=i("13sV");i.n(a).a},YEIV:function(e,t,i){"use strict";t.__esModule=!0;var a=function(e){return e&&e.__esModule?e:{default:e}}(i("SEkw"));t.default=function(e,t,i){return t in e?(0,a.default)(e,t,{value:i,enumerable:!0,configurable:!0,writable:!0}):e[t]=i,e}},uiSD:function(e,t,i){"use strict";i.r(t);var a=i("gDS+"),l=i.n(a),n=i("YEIV"),r=i.n(n),s={name:"pushpeople",mixins:[i("y2a5").a],components:{},props:{paramsObj:{type:Object,default:function(){return{}}}},data:function(){return{pushuserData:[],pushuserTotalRec:0,pushuserTotalPage:0,pushuserRecOfPage:10,pushuserCurPage:1,pushuserCbPage:this.gen_list_cb("pushuser"),pushuserQueryArr:[{name:"parent_id",val:0,tb:"user"},{name:"title",val:"",op:"like",tb:"user"}],pushuserQueryInit:[{name:"parent_id",val:0,tb:"user"},{name:"title",val:"",op:"like",tb:"user"}],pushuserMultiSel:[],pushuserSortParas:[{name:"title",tb:"user",orderV:"asc"}],departmentArr:[],pobj:{}}},mounted:function(){var e=this;this.refreshPage("pushuser","/actq","pushuserlist");var t=r()({_hy:"departlist",qps:[{name:"type",val:"部门",tb:"org"}],sps:[{name:"title",tb:"org",orderV:"asc"}]},"sps",[{name:"title",orderV:"asc"}]),i=this;this.callSrvAct("/actq",t).then(function(t){var a=t.data||[];console.log("部门数据",a),i.departmentArr=a,e.departmentArr.splice(0,0,{id:0,title:"全部"})}),this.pobj=this.paramsObj},methods:{pushuserMultiSelChange:function(e){this.pushuserMultiSel=e},pushuserQuery:function(){this.refreshPage2("pushuser","/actq",this.remoteRecOfPage,1,"pushuserlist")},pushuserResetQuery:function(){this.pushuserQueryArr=JSON.parse(l()(this.pushuserQueryInit))},pushuserSizeChange:function(e){this.refreshPage2("pushuser","/actq",e,this.pushuserCurPage,"pushuserlist")},pushuserCurChange:function(e){this.refreshPage2("pushuser","/actq",this.pushuserRecOfPage,e,"pushuserlist")},close:function(){this.$emit("submitClose")},handleChange:function(e){this.pushuserMultiSel=e,console.log(this.pushuserMultiSel)},determine:function(){if(console.log("父组件传的",this.pobj),"id"in this.pobj){var e=this.pobj;if(e.user_ids="",e.user_titles="",this.pushuserMultiSel&&this.pushuserMultiSel.length>0)for(var t=0;t<this.pushuserMultiSel.length;t++)e.user_ids&&e.user_ids.length>0&&(e.user_ids=e.user_ids+",",e.user_titles=e.user_titles+","),e.user_ids=e.user_ids+this.pushuserMultiSel[t].id,e.user_titles=e.user_titles+this.pushuserMultiSel[t].title;this.$emit("submitOk",e)}else this.$emit("submitOk",this.pushuserMultiSel)},search:function(){this.refreshPage2("pushuser","/actq",this.userRecOfPage,1,"pushuserlist")}},watch:{},computed:{},beforeCreate:function(){},created:function(){},beforeMount:function(){},beforeUpdate:function(){},updated:function(){},beforeDestroy:function(){},destroyed:function(){}},o=(i("RG45"),i("KHd+")),c=Object(o.a)(s,function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("el-container",{attrs:{direction:"vertical"}},[i("div",[i("el-select",{attrs:{placeholder:"请选择部门"},model:{value:e.pushuserQueryArr[0].val,callback:function(t){e.$set(e.pushuserQueryArr[0],"val",t)},expression:"pushuserQueryArr[0].val"}},e._l(e.departmentArr,function(e,t){return i("el-option",{key:e.id,attrs:{label:e.title,value:e.id}})})),e._v(" "),i("el-input",{staticStyle:{width:"220px",margin:"0px 10px"},attrs:{placeholder:"请输入姓名"},model:{value:e.pushuserQueryArr[1].val,callback:function(t){e.$set(e.pushuserQueryArr[1],"val",t)},expression:"pushuserQueryArr[1].val"}}),e._v(" "),i("el-button",{on:{click:e.search}},[i("span",[e._v("搜索\n            ")])])],1),e._v(" "),i("el-table",{ref:"pushuser",staticStyle:{margin:"10px 0px"},attrs:{"header-cell-style":{background:"rgba(241,246,254,1)",fontWeight:"500",color:"#333333",fontSize:"16px"},data:e.pushuserData},on:{"selection-change":e.handleChange}},[i("el-table-column",{attrs:{type:"selection"}}),e._v(" "),i("el-table-column",{attrs:{label:"姓名",prop:"title"}}),e._v(" "),i("el-table-column",{attrs:{label:"岗位",prop:"job_title"}}),e._v(" "),i("el-table-column",{attrs:{label:"手机号",prop:"mobile"}})],1),e._v(" "),i("div",{staticStyle:{"text-align":"center"}},[i("el-pagination",{attrs:{total:e.pushuserTotalRec,"page-count":e.pushuserTotalPage,"current-page":e.pushuserCurPage,layout:"total, sizes, prev, pager, next"},on:{"size-change":e.pushuserSizeChange,"current-change":e.pushuserCurChange}})],1),e._v(" "),i("div",{staticStyle:{"text-align":"right"}},[i("el-button",{on:{click:e.close}},[i("span",[e._v("取消\n            ")])]),e._v(" "),i("el-button",{on:{click:e.determine}},[i("span",[e._v("确定\n            ")])])],1)],1)},[],!1,null,"24fd9fb8",null);c.options.__file="index.vue";t.default=c.exports}}]);