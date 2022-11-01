"use strict";(self["webpackChunkboard_site_front"]=self["webpackChunkboard_site_front"]||[]).push([[941],{9184:function(a,t,l){l.r(t),l.d(t,{default:function(){return K}});var s=l(3396);function e(a,t,l,e,o,n){const i=(0,s.up)("Header"),c=(0,s.up)("router-view"),r=(0,s.up)("Footer");return(0,s.wg)(),(0,s.iD)("div",null,[(0,s.Wm)(i),(0,s.Wm)(c),(0,s.Wm)(r)])}const o={class:"navbar navbar-expand-lg stick_header"},n={class:"container-fluid",style:{margin:"0 10vw"}},i=(0,s.Uk)("프로젝트 이름"),c=(0,s._)("button",{class:"navbar-toggler",type:"button","data-bs-toggle":"collapse","data-bs-target":"#navbarSupportedContent","aria-controls":"navbarSupportedContent","aria-expanded":"false","aria-label":"Toggle navigation"},[(0,s._)("span",{class:"navbar-toggler-icon"})],-1),r={class:"collapse navbar-collapse nav",id:"navbarSupportedContent"},u={class:"navbar-nav me-auto mb-2 mb-lg-0 nav__list"},d={class:"nav-item"},m=(0,s.Uk)("관광지"),v={class:"nav-item"},g=(0,s.Uk)("여행사"),p={class:"nav-item"},f=(0,s.Uk)("여행 리스트"),k={class:"nav-item"},b=(0,s.Uk)("자유 게시판"),h={class:"nav-item"},_=(0,s.Uk)("구매 내역"),w={class:"nav-item"},x=(0,s.Uk)("좋아요"),C={class:"nav-item"},y=(0,s.Uk)("채팅방"),W={class:"nav-item"},U=(0,s.Uk)("마이 페이지"),S={class:"nav__login"},O=["src"],D=["src"];function H(a,t,l,e,H,L){const Z=(0,s.up)("router-link");return(0,s.wg)(),(0,s.iD)("nav",o,[(0,s._)("div",n,[(0,s.Wm)(Z,{class:"navbar-brand",to:"/"},{default:(0,s.w5)((()=>[i])),_:1}),c,(0,s._)("div",r,[(0,s._)("ul",u,[(0,s._)("li",d,[(0,s.Wm)(Z,{class:"nav-link font-color",to:"/tourList"},{default:(0,s.w5)((()=>[m])),_:1})]),(0,s._)("li",v,[(0,s.Wm)(Z,{class:"nav-link font-color",to:"/agencyList"},{default:(0,s.w5)((()=>[g])),_:1})]),(0,s._)("li",p,[(0,s.Wm)(Z,{class:"nav-link font-color",to:"/travelList"},{default:(0,s.w5)((()=>[f])),_:1})]),(0,s._)("li",k,[(0,s.Wm)(Z,{class:"nav-link font-color",to:"/article"},{default:(0,s.w5)((()=>[b])),_:1})]),(0,s._)("li",h,[(0,s.Wm)(Z,{class:"nav-link font-color",to:"/purchaseHistory"},{default:(0,s.w5)((()=>[_])),_:1})]),(0,s._)("li",w,[(0,s.Wm)(Z,{class:"nav-link font-color",to:"/myHeart"},{default:(0,s.w5)((()=>[x])),_:1})]),(0,s._)("li",C,[(0,s.Wm)(Z,{class:"nav-link font-color",to:"/chatList"},{default:(0,s.w5)((()=>[y])),_:1})]),(0,s._)("li",W,[(0,s.Wm)(Z,{class:"nav-link font-color",to:"/myPage"},{default:(0,s.w5)((()=>[U])),_:1})])]),(0,s._)("div",S,[a.authChk?((0,s.wg)(),(0,s.iD)("img",{key:0,class:"nav__login__profile",src:a.login,style:{width:"32px"},onClick:t[0]||(t[0]=(...a)=>L.loginClick&&L.loginClick(...a))},null,8,O)):(0,s.kq)("",!0),(0,s._)("img",{src:a.logOut,style:{width:"32px"},onClick:t[1]||(t[1]=(...a)=>L.logOutClick&&L.logOutClick(...a))},null,8,D)])])])])}var L=l.p+"img/person-circle.a7696bfc.svg",Z=l.p+"img/box-arrow-right.75a7e0f5.svg",F={data:function(){return{login:L,logOut:Z,authChk:!0}},created(){null!=sessionStorage.getItem("token")?this.authChk=!1:this.authChk=!0},methods:{loginClick(){this.$router.push("/login")},logOutClick(){localStorage.clear(),sessionStorage.clear(),this.authChk=!0,this.$router.push("/")}}},$=l(89);const j=(0,$.Z)(F,[["render",H]]);var q=j;const A={class:"text-center text-lg-start bg-light text-muted footer-under wrap"},B=(0,s.uE)('<section class=""><div class="container text-center text-md-start mt-5"><div class="row mt-3"><div class="col-md-3 col-lg-4 col-xl-3 mx-auto mb-4"><h6 class="text-uppercase fw-bold mb-4"><i class="fas fa-gem me-3"></i>Company name </h6><p> 현우의 국내 여행 프로젝트 입니다. </p></div><div class="col-md-2 col-lg-2 col-xl-2 mx-auto mb-4"><h6 class="text-uppercase fw-bold mb-4"> programming language </h6><p> vue.js </p><p> spring Boot </p></div><div class="col-md-4 col-lg-3 col-xl-3 mx-auto mb-md-0 mb-4"><h6 class="text-uppercase fw-bold mb-4">Contact</h6><p><i class="fas fa-home me-3"></i> 서울특별시</p><p><i class="fas fa-envelope me-3"></i> gus5162@naver.com </p><p><i class="fas fa-phone me-3"></i> + 82 9064 7358</p></div></div></div></section><div class="text-center p-4" style="background-color:rgba(0, 0, 0, 0.05);"> © 2022 Copyright: <a class="text-reset fw-bold" href="#">gusdn</a></div>',2),E=[B];function I(a,t){return(0,s.wg)(),(0,s.iD)("footer",A,E)}const P={},T=(0,$.Z)(P,[["render",I]]);var z=T,G={name:"App",components:{Header:q,Footer:z}};const J=(0,$.Z)(G,[["render",e]]);var K=J}}]);
//# sourceMappingURL=941.38a055e2.js.map