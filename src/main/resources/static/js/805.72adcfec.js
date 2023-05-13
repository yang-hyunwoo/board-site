"use strict";(self["webpackChunkboard_site_front"]=self["webpackChunkboard_site_front"]||[]).push([[805],{4051:function(t,a,o){o.d(a,{Z:function(){return c}});var l=o(3396);const e={class:"c-popup"};function i(t,a,o,i,n,s){return(0,l.wg)(),(0,l.iD)("div",null,[(0,l._)("div",{class:"black-bg",onClick:a[0]||(a[0]=a=>t.$emit("close"))}),(0,l._)("div",e,[(0,l.WI)(t.$slots,"cont")])])}var n={},s=o(89);const r=(0,s.Z)(n,[["render",i]]);var c=r},5033:function(t,a,o){o.d(a,{Z:function(){return d}});var l=o(3396);const e=(0,l._)("div",{class:"customdis"},[(0,l._)("div",{class:"spinner-border text-secondary",style:{width:"3rem",height:"3rem"},role:"status"},[(0,l._)("span",{class:"visually-hidden"},"Loading...")])],-1),i=[e];function n(t,a,o,e,n,s){return(0,l.wg)(),(0,l.iD)("div",{class:"black-bg",onClick:a[0]||(a[0]=a=>t.$emit("close"))},i)}var s={},r=o(89);const c=(0,r.Z)(s,[["render",n]]);var d=c},4805:function(t,a,o){o.r(a),o.d(a,{default:function(){return I}});var l=o(3396),e=o(9242);const i={class:"ares"},n=(0,l._)("img",{class:"mb-4",src:"/docs/5.2/assets/brand/bootstrap-logo.svg",alt:"",width:"72",height:"57"},null,-1),s=(0,l._)("h1",{class:"h3 mb-3 fw-normal"},"로그인",-1),r=(0,l._)("div",null,null,-1),c={class:"form-floating"},d=(0,l._)("label",{for:"floatingInput"},"Email address",-1),p={class:"form-floating"},u=(0,l._)("label",{for:"floatingPassword"},"Password",-1),h={class:"checkbox mb-4 matop",style:{display:"flex"}},m=(0,l._)("a",{style:{"text-decoration":"underline",cursor:"pointer"}},"회원이 아니신가요?",-1),g=[m],w=(0,l._)("a",{style:{"text-decoration":"underline",cursor:"pointer"}},"비밀번호 찾기",-1),k=[w],f=(0,l._)("h2",null,"비밀번호 찾기",-1),_=(0,l._)("p",null,"회원가입하신 이메일로 비밀번호가 전송됩니다.",-1),b={class:"form-floating",style:{"margin-bottom":"1rem"}},v=(0,l._)("label",{for:"floatingInput"},"이름",-1),y={class:"form-floating",style:{"margin-bottom":"1rem"}},C=(0,l._)("label",{for:"floatingInput"},"Email address",-1);function $(t,a,o,m,w,$){const P=(0,l.up)("BlackBg"),L=(0,l.up)("Dialog");return(0,l.wg)(),(0,l.iD)(l.HY,null,[(0,l._)("div",i,[(0,l._)("div",null,[n,s,r,(0,l._)("div",c,[(0,l.wy)((0,l._)("input",{type:"email",class:"form-control",id:"floatingInput",placeholder:"name@example.com","onUpdate:modelValue":a[0]||(a[0]=a=>t.email=a)},null,512),[[e.nr,t.email]]),d]),(0,l._)("div",p,[(0,l.wy)((0,l._)("input",{type:"password",class:"form-control",id:"floatingPassword",placeholder:"Password","onUpdate:modelValue":a[1]||(a[1]=a=>t.password=a)},null,512),[[e.nr,t.password]]),u]),(0,l._)("div",h,[(0,l._)("button",{onClick:a[2]||(a[2]=(...t)=>$.naverLoginClick&&$.naverLoginClick(...t)),style:{border:"0"}},"네이버 로그인"),(0,l._)("button",{onClick:a[3]||(a[3]=(...t)=>$.kakaoLoginClick&&$.kakaoLoginClick(...t)),style:{border:"0"}},"카카오 로그인")]),(0,l._)("button",{class:"w-100 btn btn-lg btn-primary",onClick:a[4]||(a[4]=(...t)=>$.login&&$.login(...t))},"로그인"),(0,l._)("p",{class:"mt-3",onClick:a[5]||(a[5]=(...t)=>$.join&&$.join(...t))},g),(0,l._)("p",{class:"mt-3",onClick:a[6]||(a[6]=(...t)=>$.pwChk&&$.pwChk(...t))},k)])]),t.loading?((0,l.wg)(),(0,l.j4)(P,{key:0})):(0,l.kq)("",!0),t.pwPop?((0,l.wg)(),(0,l.j4)(L,{key:1,onClose:a[10]||(a[10]=a=>t.pwPop=!1)},{cont:(0,l.w5)((()=>[f,_,(0,l._)("div",b,[(0,l.wy)((0,l._)("input",{type:"text",class:"form-control",id:"floatingInput",placeholder:"이름","onUpdate:modelValue":a[7]||(a[7]=a=>t.pwName=a)},null,512),[[e.nr,t.pwName]]),v]),(0,l._)("div",y,[(0,l.wy)((0,l._)("input",{type:"email",class:"form-control",id:"floatingInput",placeholder:"name@example.com","onUpdate:modelValue":a[8]||(a[8]=a=>t.pwEmail=a)},null,512),[[e.nr,t.pwEmail]]),C]),(0,l._)("button",{class:"w-100 btn btn-lg btn-primary",onClick:a[9]||(a[9]=(...t)=>$.pwEmailSend&&$.pwEmailSend(...t))},"찾기")])),_:1})):(0,l.kq)("",!0)],64)}var P=o(5033),L=o(4051),S={components:{BlackBg:P.Z,Dialog:L.Z},data:function(){return{email:"",password:"",loading:!1,callbackUrl:"",client_id:"",pwPop:!1,pwName:"",pwEmail:""}},methods:{async kakaoLoginClick(){await this.$axios.get("api/kakao/kakao-client").then((t=>{if("SUCCESS"==t.data.resultCode){let a=t.data.result.clientId,o=t.data.result.callbackUrl;this.kakaoLoginPop(a,o)}})).catch((()=>{this.$swal("","잠시후 다시 이용해주세요.","error")})).finally((()=>{}))},async naverLoginClick(){await this.$axios.get("api/naver/naver-client").then((t=>{if("SUCCESS"==t.data.resultCode){let a=t.data.result.clientId,o=t.data.result.callbackUrl;console.log("client_id ="+a),console.log("callbackUrl ="+o),this.naverLoginPop(a,o)}})).catch((()=>{this.$swal("","잠시후 다시 이용해주세요.","error")})).finally((()=>{}))},join(){this.$router.push("/join")},login(){if(this.$emptyChk(this.email))return void this.$swal("","아이디를 입력해주세요.","waring");if(this.$emptyChk(this.password))return void this.$swal("","비밀번호를 입력해주세요.","waring");let t={email:this.email,password:this.password};this.loading=!0,this.$axios.post("/api/trip/users/login",t).then((t=>{sessionStorage.setItem("token",t.data.result.token),localStorage.setItem("token",t.data.result.token),this.$router.push("/")})).catch((t=>{this.$swal("",t.response.data.result,"error")})).finally((()=>{this.loading=!1}))},pwChk(){this.pwPop=!0,console.log(this.pwPop)},naverLoginPop(t,a){let o="https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id="+t+"&state=NAVER_LOGIN_TEST&redirect_uri="+a;window.open(o,"Naver Login Test PopupScreen","width=500, height=600")},kakaoLoginPop(t,a){let o="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id="+t+"&redirect_uri="+a;window.open(o,"Kakao Login Test PopupScreen","width=500, height=600")},pwEmailSend(){let t={name:this.pwName,email:this.pwEmail};this.$axios.post("/api/trip/users/pw-find",t).then((t=>{alert("입력하신 이메일로 비밀번호가 전송 되었습니다."),this.pwPop=!1})).catch((t=>{this.$swal("",t.response.data.result,"error")})).finally((()=>{this.loading=!1}))}}},E=o(89);const x=(0,E.Z)(S,[["render",$]]);var I=x}}]);
//# sourceMappingURL=805.72adcfec.js.map