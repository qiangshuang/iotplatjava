(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-dcac"],{"4lY3":function(t,e,o){},BBfo:function(t,e,o){"use strict";o.r(e);var n={name:"paizhao",mixins:[],components:{},props:{},data:function(){return{timer:null,protoX:0,protoY:0,protoDownX:0,protoDownY:0,widtheigh:400,mouseDownX:0,mouseDownY:0,gap:0,mode:1,selectedCorner:"",track:""}},mounted:function(){},methods:{onTake:function(){this.getCompetence()},getCompetence:function(){var t=this;navigator.mediaDevices.getUserMedia({video:{width:400,height:400},audio:!0}).then(function(e){console.log(e),console.log(video),t.track=e,video.srcObject=e,video.play()}).catch(function(t){console.log(t.message),console.log(t)})}},watch:{},computed:{},beforeCreate:function(){},created:function(){},beforeMount:function(){},beforeUpdate:function(){},updated:function(){},beforeDestroy:function(){},destroyed:function(){}},i=(o("RO1k"),o("KHd+")),a=Object(i.a)(n,function(){var t=this.$createElement,e=this._self._c||t;return e("el-container",{attrs:{direction:"vertical"}},[e("div",[e("video",{ref:"videoCamera",staticStyle:{width:"500px",height:"400px"},attrs:{autoplay:"autoplay",id:"videoCamera"}}),this._v(" "),e("span",[e("canvas",{staticClass:"canvas",attrs:{id:"canvasCamera",width:this.videoWidth,height:this.videoHeight}})])]),this._v(" "),e("div",[e("el-button",{on:{click:this.onTake}},[e("span",[this._v("调用摄像头\n            ")])])],1)])},[],!1,null,"0a9a5167",null);a.options.__file="index.vue";e.default=a.exports},RO1k:function(t,e,o){"use strict";var n=o("4lY3");o.n(n).a}}]);