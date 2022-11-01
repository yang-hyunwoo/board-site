"use strict";(self["webpackChunkboard_site_front"]=self["webpackChunkboard_site_front"]||[]).push([[411],{360:function(t,e,a){a.d(e,{Z:function(){return d}});var s=a(3396);const r={class:"pagination"},i={class:"overflow-auto"},l={class:"mt-3"};function o(t,e,a,o,c,n){const u=(0,s.up)("b-pagination"),d=(0,s.up)("b-card");return(0,s.wg)(),(0,s.iD)("div",r,[(0,s.Wm)(d,{style:{"justify-content":"center"}},{default:(0,s.w5)((()=>[(0,s._)("div",i,[(0,s._)("div",l,[(0,s.Wm)(u,{modelValue:t.ex4CurrentPage,"onUpdate:modelValue":e[0]||(e[0]=e=>t.ex4CurrentPage=e),pills:"","total-rows":t.ex4Rows,onPageClick:n.pageClick,"per-page":t.perPage,currPage:a.currPage},null,8,["modelValue","total-rows","onPageClick","per-page","currPage"])])])])),_:1}),(0,s.WI)(t.$slots,"default")])}var c={props:{pageListItem:Number,pageTotal:Number,currPage:{type:Number,default:1}},data:function(){return{ex4CurrentPage:this.currPage,ex4Rows:this.pageTotal,perPage:this.pageListItem}},created(){console.log(this.currPage)},methods:{pageClick:function(t,e){this.ex4CurrentPage=e,this.$emit("pageCurrent",this.ex4CurrentPage)}}},n=a(89);const u=(0,n.Z)(c,[["render",o]]);var d=u},5033:function(t,e,a){a.d(e,{Z:function(){return u}});var s=a(3396);const r=(0,s._)("div",{class:"customdis"},[(0,s._)("div",{class:"spinner-border text-secondary",style:{width:"3rem",height:"3rem"},role:"status"},[(0,s._)("span",{class:"visually-hidden"},"Loading...")])],-1),i=[r];function l(t,e,a,r,l,o){return(0,s.wg)(),(0,s.iD)("div",{class:"black-bg",onClick:e[0]||(e[0]=e=>t.$emit("close"))},i)}var o={},c=a(89);const n=(0,c.Z)(o,[["render",l]]);var u=n},7411:function(t,e,a){a.r(e),a.d(e,{default:function(){return j}});var s=a(3396),r=a(9242),i=a(7139);const l={class:"py-5 text-center container"},o={class:"row py-5"},c={class:"col-lg-12 mx-auto"},n={class:"text-white p-5 shadow-sm rounded banner"},u=(0,s._)("h1",{class:"display-4"},"여행지 리스트",-1),d=(0,s._)("p",{class:"lead"},"나에게 맞는 여행지를 찾아보세요",-1),g=(0,s._)("option",{selected:"",value:"TITLE"},"제목",-1),h=(0,s._)("option",{value:"CITY"},"도시",-1),p=[g,h],m={class:"album py-5 bg-light"},_={class:"container"},w={class:"row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3"},y=["onClick"],b={class:"card shadow-sm"},k={key:0,class:"bd-placeholder-img card-img-top",width:"100%",height:"225",xmlns:"http://www.w3.org/2000/svg",role:"img","aria-label":"Placeholder: Thumbnail",preserveAspectRatio:"xMidYMid slice",focusable:"false"},f=(0,s._)("title",null,"Placeholder",-1),v=(0,s._)("rect",{width:"100%",height:"100%",fill:"#55595c"},null,-1),C=[f,v],P=["src"],x={class:"card-body"},T={class:"card-text"},I={class:"d-flex justify-content-between align-items-center"},L={class:"btn-group"},D={class:"text-muted"};function S(t,e,a,g,h,f){const v=(0,s.up)("BlackBg"),S=(0,s.up)("Pagination");return(0,s.wg)(),(0,s.iD)(s.HY,null,[(0,s._)("section",l,[(0,s._)("div",o,[(0,s._)("div",c,[(0,s._)("div",n,[u,d,(0,s.wy)((0,s._)("select",{class:"form-select","aria-label":"Default select example","onUpdate:modelValue":e[0]||(e[0]=e=>t.search_type=e)},p,512),[[r.bM,t.search_type]]),(0,s.wy)((0,s._)("input",{class:"form-control mr-sm-2 custombar",type:"search",placeholder:"제목을 입력해주세요.","aria-label":"Search","onUpdate:modelValue":e[1]||(e[1]=e=>t.tour_title=e),onKeyup:e[2]||(e[2]=(0,r.D2)(((...t)=>f.tourSearch&&f.tourSearch(...t)),["enter"]))},null,544),[[r.nr,t.tour_title]]),(0,s._)("button",{class:"btn btn-outline-success my-2 my-sm-0",style:{"margin-left":"1rem"},onClick:e[3]||(e[3]=(...t)=>f.tourSearch&&f.tourSearch(...t))},"Search")])])])]),(0,s._)("div",m,[(0,s._)("div",_,[(0,s._)("div",w,[((0,s.wg)(!0),(0,s.iD)(s.HY,null,(0,s.Ko)(t.tour_list,((t,e)=>((0,s.wg)(),(0,s.iD)("div",{class:"col curcus",key:e,onClick:e=>f.todoCclick(t.id)},[(0,s._)("div",b,[t.img_chk?(0,s.kq)("",!0):((0,s.wg)(),(0,s.iD)("svg",k,C)),t.img_chk?((0,s.wg)(),(0,s.iD)("img",{key:1,src:t.img,class:"bd-placeholder-img card-img-top",width:"100%",height:"225"},null,8,P)):(0,s.kq)("",!0),(0,s._)("div",x,[(0,s._)("p",T,(0,i.zw)(t.title),1),(0,s._)("div",I,[(0,s._)("div",L,[(0,s._)("p",null,"도시 명 : "+(0,i.zw)(t.city),1)]),(0,s._)("small",D,"조회 : "+(0,i.zw)(t.read_count),1)])])])],8,y)))),128))])])]),t.loading?((0,s.wg)(),(0,s.j4)(v,{key:0})):(0,s.kq)("",!0),t.pageChk?((0,s.wg)(),(0,s.j4)(S,{key:1,pageListItem:t.pageListItem,onPageCurrent:f.pageCurr,pageTotal:t.pageTotal},null,8,["pageListItem","onPageCurrent","pageTotal"])):(0,s.kq)("",!0)],64)}var Z=a(360),$=a(5033),q={data:function(){return{pageListItem:9,page:0,tour_title:"",pageTotal:0,pageChk:!1,tour_list:[],loading:!1,search_type:"TITLE"}},components:{Pagination:Z.Z,BlackBg:$.Z},created(){this.init()},methods:{init(){this.tourList()},tourList(){let t={page:this.page,searchKeyWord:this.tour_title,searchType:this.search_type};this.loading=!0,this.$axios.get("/api/trip/tour",{params:t}).then((t=>{"SUCCESS"==t.data.resultCode&&(this.pageTotal=t.data.result.totalElements,this.tour_list=[],t.data.result.content.forEach((t=>{let e=[];e.title=t.title,e.id=t.id,e.city=t.city,e.read_count=t.readCount,null==t.thumbnailId||""==t.thumbnailId?e.img_chk=!1:e.img_chk=!0,e.img="/api/common/image/thumb/"+t.thumbnailId+"/1",this.tour_list.push(e)})))})).catch((()=>{this.$swal("","잠시후 다시 이용해주세요.","error")})).finally((()=>{this.pageChk=!0,this.loading=!1}))},pageCurr(t){this.page=t-1,this.tourList()},tourSearch(){this.page=0,this.pageChk=!1,this.tourList()},todoCclick(t){this.$router.push({path:"/tourDetail",name:"tourDetail",query:{sn:t}})}}},E=a(89);const V=(0,E.Z)(q,[["render",S]]);var j=V}}]);
//# sourceMappingURL=411.78535187.js.map