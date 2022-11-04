"use strict";(self["webpackChunkboard_site_front"]=self["webpackChunkboard_site_front"]||[]).push([[562],{360:function(e,t,a){a.d(t,{Z:function(){return d}});var s=a(3396);const r={class:"pagination"},l={class:"overflow-auto"},i={class:"mt-3"};function o(e,t,a,o,c,n){const u=(0,s.up)("b-pagination"),d=(0,s.up)("b-card");return(0,s.wg)(),(0,s.iD)("div",r,[(0,s.Wm)(d,{style:{"justify-content":"center"}},{default:(0,s.w5)((()=>[(0,s._)("div",l,[(0,s._)("div",i,[(0,s.Wm)(u,{modelValue:e.ex4CurrentPage,"onUpdate:modelValue":t[0]||(t[0]=t=>e.ex4CurrentPage=t),pills:"","total-rows":e.ex4Rows,onPageClick:n.pageClick,"per-page":e.perPage,currPage:a.currPage},null,8,["modelValue","total-rows","onPageClick","per-page","currPage"])])])])),_:1}),(0,s.WI)(e.$slots,"default")])}var c={props:{pageListItem:Number,pageTotal:Number,currPage:{type:Number,default:1}},data:function(){return{ex4CurrentPage:this.currPage,ex4Rows:this.pageTotal,perPage:this.pageListItem}},created(){console.log(this.currPage)},methods:{pageClick:function(e,t){this.ex4CurrentPage=t,this.$emit("pageCurrent",this.ex4CurrentPage)}}},n=a(89);const u=(0,n.Z)(c,[["render",o]]);var d=u},5033:function(e,t,a){a.d(t,{Z:function(){return u}});var s=a(3396);const r=(0,s._)("div",{class:"customdis"},[(0,s._)("div",{class:"spinner-border text-secondary",style:{width:"3rem",height:"3rem"},role:"status"},[(0,s._)("span",{class:"visually-hidden"},"Loading...")])],-1),l=[r];function i(e,t,a,r,i,o){return(0,s.wg)(),(0,s.iD)("div",{class:"black-bg",onClick:t[0]||(t[0]=t=>e.$emit("close"))},l)}var o={},c=a(89);const n=(0,c.Z)(o,[["render",i]]);var u=n},2562:function(e,t,a){a.r(t),a.d(t,{default:function(){return j}});var s=a(3396),r=a(9242),l=a(7139);const i={class:"py-5 text-center container"},o={class:"row py-5"},c={class:"col-lg-12 mx-auto"},n={class:"text-white p-5 shadow-sm rounded banner"},u=(0,s._)("h1",{class:"display-4"},"여행지 리스트",-1),d=(0,s._)("p",{class:"lead"},"나에게 맞는 여행지를 찾아보세요",-1),g=(0,s._)("option",{selected:"",value:"TITLE"},"제목",-1),h=(0,s._)("option",{value:"CITY"},"도시",-1),p=[g,h],m={class:"album py-5 bg-light"},_={class:"container"},w={class:"row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3"},f=["onClick"],y={class:"card shadow-sm"},k={key:0,class:"bd-placeholder-img card-img-top",width:"100%",height:"225",xmlns:"http://www.w3.org/2000/svg",role:"img","aria-label":"Placeholder: Thumbnail",preserveAspectRatio:"xMidYMid slice",focusable:"false"},v=(0,s._)("title",null,"Placeholder",-1),C=(0,s._)("rect",{width:"100%",height:"100%",fill:"#55595c"},null,-1),b=[v,C],P=["src"],x={class:"card-body"},T={class:"card-text"},L={class:"d-flex justify-content-between align-items-center"},D={class:"btn-group"},I={class:"text-muted"};function S(e,t,a,g,h,v){const C=(0,s.up)("BlackBg"),S=(0,s.up)("Pagination");return(0,s.wg)(),(0,s.iD)(s.HY,null,[(0,s._)("section",i,[(0,s._)("div",o,[(0,s._)("div",c,[(0,s._)("div",n,[u,d,(0,s.wy)((0,s._)("select",{class:"form-select","aria-label":"Default select example","onUpdate:modelValue":t[0]||(t[0]=t=>e.search_type=t)},p,512),[[r.bM,e.search_type]]),(0,s.wy)((0,s._)("input",{class:"form-control mr-sm-2 custombar",type:"search",placeholder:"제목을 입력해주세요.","aria-label":"Search","onUpdate:modelValue":t[1]||(t[1]=t=>e.tour_title=t),onKeyup:t[2]||(t[2]=(0,r.D2)(((...e)=>v.tourSearch&&v.tourSearch(...e)),["enter"]))},null,544),[[r.nr,e.tour_title]]),(0,s._)("button",{class:"btn btn-outline-success my-2 my-sm-0",style:{"margin-left":"1rem"},onClick:t[3]||(t[3]=(...e)=>v.tourSearch&&v.tourSearch(...e))},"Search")])])])]),(0,s._)("div",m,[(0,s._)("div",_,[(0,s._)("div",w,[((0,s.wg)(!0),(0,s.iD)(s.HY,null,(0,s.Ko)(e.tour_list,((e,t)=>((0,s.wg)(),(0,s.iD)("div",{class:"col curcus",key:t,onClick:t=>v.todoCclick(e.id)},[(0,s._)("div",y,[e.img_chk?(0,s.kq)("",!0):((0,s.wg)(),(0,s.iD)("svg",k,b)),e.img_chk?((0,s.wg)(),(0,s.iD)("img",{key:1,src:e.img,class:"bd-placeholder-img card-img-top",width:"100%",height:"225"},null,8,P)):(0,s.kq)("",!0),(0,s._)("div",x,[(0,s._)("p",T,(0,l.zw)(e.title),1),(0,s._)("div",L,[(0,s._)("div",D,[(0,s._)("p",null,"도시 명 : "+(0,l.zw)(e.city),1)]),(0,s._)("small",I,"조회 : "+(0,l.zw)(e.read_count),1)])])])],8,f)))),128))])])]),e.loading?((0,s.wg)(),(0,s.j4)(C,{key:0})):(0,s.kq)("",!0),e.pageChk?((0,s.wg)(),(0,s.j4)(S,{key:1,pageListItem:e.pageListItem,onPageCurrent:v.pageCurr,pageTotal:e.pageTotal},null,8,["pageListItem","onPageCurrent","pageTotal"])):(0,s.kq)("",!0)],64)}var Z=a(360),$=a(5033),q={data:function(){return{pageListItem:9,page:0,tour_title:"",pageTotal:0,pageChk:!1,tour_list:[],loading:!1,search_type:"TITLE"}},components:{Pagination:Z.Z,BlackBg:$.Z},created(){this.init()},methods:{init(){this.tourList()},tourList(){let e={page:this.page,searchKeyWord:this.tour_title,searchType:this.search_type};this.loading=!0,this.$axios.get("/api/trip/tour",{params:e}).then((e=>{"SUCCESS"==e.data.resultCode&&(this.pageTotal=e.data.result.totalElements,this.tour_list=[],e.data.result.content.forEach((e=>{let t=[];t.title=e.title,t.id=e.id,t.city=e.city,t.read_count=e.readCount,null==e.filePath||""==e.filePath?t.img_chk=!1:(console.log("111"),t.img_chk=!0,t.img=e.filePath),this.tour_list.push(t)})))})).catch((()=>{this.$swal("","잠시후 다시 이용해주세요.","error")})).finally((()=>{this.pageChk=!0,this.loading=!1}))},pageCurr(e){this.page=e-1,this.tourList()},tourSearch(){this.page=0,this.pageChk=!1,this.tourList()},todoCclick(e){this.$router.push({path:"/tourDetail",name:"tourDetail",query:{sn:e}})}}},E=a(89);const V=(0,E.Z)(q,[["render",S]]);var j=V}}]);
//# sourceMappingURL=562.10df569c.js.map