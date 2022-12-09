(function(){"use strict";var e={8723:function(e,t,n){var o=n(9242),r=n(3396);function a(e,t,n,o,a,i){const s=(0,r.up)("router-view");return(0,r.wg)(),(0,r.j4)(s)}var i={name:"App",beforeMount(){window.addEventListener("load",this.onLoad),window.addEventListener("beforeunload",this.onUnload)},beforeDestroy(){window.removeEventListener("load",this.onLoad),window.removeEventListener("beforeunload",this.onUnload)},methods:{onLoad(e){null!=sessionStorage.getItem("token")&&(console.log("::::"+sessionStorage.getItem("token")),window.localStorage.setItem("token",sessionStorage.getItem("token")))},onUnload(e){window.localStorage.removeItem("token")}}},s=n(89);const c=(0,s.Z)(i,[["render",a]]);var l=c,u=n(936),f=n(1373);const d=(0,f.Z)();var b={install(e){e.config.globalProperties.emitter=d}},p=n(2483);const m=()=>(e,t,n)=>(null==sessionStorage.getItem("token")&&n("/login"),n()),h=[{path:"/",name:"defaultLayout",component:()=>n.e(941).then(n.bind(n,9184)),children:[{path:"/",name:"home",component:()=>n.e(364).then(n.bind(n,6938))},{path:"/travelList",name:"travelList",component:()=>n.e(70).then(n.bind(n,1070))},{path:"/travelDetail",name:"travelDetail",component:()=>Promise.all([n.e(142),n.e(672),n.e(108)]).then(n.bind(n,4971))},{path:"/travelPaymentIng",name:"travelPaymentIng",component:()=>n.e(242).then(n.bind(n,3242))},{path:"/article",name:"article",component:()=>n.e(206).then(n.bind(n,2206))},{path:"/articleDetail",name:"articleDetail",component:()=>Promise.all([n.e(142),n.e(672),n.e(666)]).then(n.bind(n,233))},{path:"/articleWrite",name:"articleWrite",component:()=>Promise.all([n.e(142),n.e(171),n.e(327)]).then(n.bind(n,8327))},{path:"/articleModify",name:"articleModify",component:()=>Promise.all([n.e(142),n.e(171),n.e(367)]).then(n.bind(n,6367))},{path:"/agencyList",name:"agencyList",component:()=>n.e(241).then(n.bind(n,8241))},{path:"/agencyDetail",name:"agencyDetail",component:()=>Promise.all([n.e(142),n.e(672),n.e(32)]).then(n.bind(n,2195))},{path:"/purchaseHistory",name:"purchaseHistory",component:()=>n.e(12).then(n.bind(n,4012)),beforeEnter:m()},{path:"/chatList",name:"chatList",component:()=>Promise.all([n.e(113),n.e(686)]).then(n.bind(n,7794)),beforeEnter:m()},{path:"/chatDetail",name:"chatDetail",component:()=>Promise.all([n.e(113),n.e(68)]).then(n.bind(n,3710))},{path:"/myPage",name:"myPage",component:()=>n.e(618).then(n.bind(n,9618)),beforeEnter:m()},{path:"/tourList",name:"tourList",component:()=>n.e(562).then(n.bind(n,2562))},{path:"/tourDetail",name:"tourDetail",component:()=>Promise.all([n.e(142),n.e(672),n.e(502)]).then(n.bind(n,7672))},{path:"/myHeart",name:"myHeart",component:()=>n.e(823).then(n.bind(n,2823)),beforeEnter:m()}]},{path:"/",name:"emptyLayout",component:()=>n.e(208).then(n.bind(n,208)),children:[{path:"/login",name:"login",component:()=>n.e(560).then(n.bind(n,5611))},{path:"/join",name:"join",component:()=>n.e(46).then(n.bind(n,8046))},{path:"/loginSuccess",name:"loginSuccess",component:()=>n.e(646).then(n.bind(n,9646))},{path:"/travelPaymentSuccess",name:"travelPaymentSuccess",component:()=>n.e(650).then(n.bind(n,6650))},{path:"/naverLoginCallback",name:"naverLoginCallback",component:()=>n.e(965).then(n.bind(n,9965))},{path:"/kakaoLoginCallback",name:"kakaoLoginCallback",component:()=>n.e(504).then(n.bind(n,7504))}]}],g=(0,p.p7)({history:(0,p.PO)(),routes:h});var v=g,y=n(6265),P=n.n(y),k=n(8541),w={install(e){e.config.globalProperties.$emailValidation=function(e){var t=/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;return!!t.test(e)},e.config.globalProperties.$passwordValidation=function(e){var t=/^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/i;return!!t.test(e)},e.config.globalProperties.$phoneNumberValidation=function(e){var t=/^\d{3}-\d{3,4}-\d{4}$/i;return!!t.test(e)},e.config.globalProperties.$phoneNumberGetMask=function(e){let t="";return e.length<8?t=e.length>=4?e.substr(0,3)+"-"+e.substr(3,3)+e.substr(6):e:8==e.length||9==e.length||10==e.length?t=e.substr(0,3)+"-"+e.substr(3,3)+"-"+e.substr(6):e.length>10&&(t=e.substr(0,3)+"-"+e.substr(3,4)+"-"+e.substr(7)),t},e.config.globalProperties.$tokenCheck=function(){let e=sessionStorage.getItem("token");return null!=e&&0!=e.length&&"null"!=e&&"undefined"!=e&&void 0!=e},e.config.globalProperties.$splitDateHyphen=function(e){return e.substr(0,4)+"."+e.substr(4,2)+"."+e.substr(6)},e.config.globalProperties.$splitDateHyphenLo=function(e){return e.substr(0,4)+"."+e.substr(5,2)+"."+e.substr(8,2)},e.config.globalProperties.$splitDateHyphenTime=function(e){return e.substr(0,4)+"."+e.substr(5,2)+"."+e.substr(8,2)+" "+e.substr(11,5)},e.config.globalProperties.$numberWithCommas=function(e){return e.toString().replace(/\B(?=(\d{3})+(?!\d))/g,",")},e.config.globalProperties.$emptyChk=function(e){var t=/^\s+|\s+$/g;return""==e.replace(t,"")||(""==e||null==e||void 0==e||null!=e&&"object"==typeof value&&!Object.keys(e).length)}}},L=n(6553),S=n.n(L);const C=(0,o.ri)(l);C.config.globalProperties.$axios=P(),C.use(u.ZP),C.use(b),C.use((0,k.fP)({sameSite:"none",secure:!0})),C.use(v),C.use(w),C.use(S()),C.mount("#app")}},t={};function n(o){var r=t[o];if(void 0!==r)return r.exports;var a=t[o]={exports:{}};return e[o].call(a.exports,a,a.exports,n),a.exports}n.m=e,function(){var e=[];n.O=function(t,o,r,a){if(!o){var i=1/0;for(u=0;u<e.length;u++){o=e[u][0],r=e[u][1],a=e[u][2];for(var s=!0,c=0;c<o.length;c++)(!1&a||i>=a)&&Object.keys(n.O).every((function(e){return n.O[e](o[c])}))?o.splice(c--,1):(s=!1,a<i&&(i=a));if(s){e.splice(u--,1);var l=r();void 0!==l&&(t=l)}}return t}a=a||0;for(var u=e.length;u>0&&e[u-1][2]>a;u--)e[u]=e[u-1];e[u]=[o,r,a]}}(),function(){n.n=function(e){var t=e&&e.__esModule?function(){return e["default"]}:function(){return e};return n.d(t,{a:t}),t}}(),function(){n.d=function(e,t){for(var o in t)n.o(t,o)&&!n.o(e,o)&&Object.defineProperty(e,o,{enumerable:!0,get:t[o]})}}(),function(){n.f={},n.e=function(e){return Promise.all(Object.keys(n.f).reduce((function(t,o){return n.f[o](e,t),t}),[]))}}(),function(){n.u=function(e){return"js/"+e+"."+{12:"8aa9ad4e",32:"eac8bb05",46:"c513b7d2",68:"09df5466",70:"c8af63b9",108:"7cb38ecf",113:"0474d5a1",142:"0611655d",171:"e65ed55e",206:"b75227fe",208:"15e28b69",241:"f174503c",242:"63eeeb11",327:"00150ad8",364:"dd6e5556",367:"cf95a920",502:"09da2653",504:"4388a910",560:"6c646c35",562:"10df569c",618:"d39c684d",646:"20e9ca2a",650:"9e98879e",666:"074b08f6",672:"0a3b54f7",686:"39b6f2f7",823:"11817220",941:"38a055e2",965:"7bf10103"}[e]+".js"}}(),function(){n.miniCssF=function(e){return"css/"+e+"."+{12:"fc613688",32:"4761f6c0",46:"6fb69354",68:"805db903",70:"bdaf4180",108:"e0ddb9e3",142:"aac69711",206:"f838b427",241:"0ee892ac",242:"5b2636aa",364:"232b8055",367:"f2bbd408",502:"b018ee41",504:"eefa04e6",560:"4affcbb7",562:"13e74046",618:"6a6b9f8c",646:"eefa04e6",650:"eefa04e6",666:"bcd28e37",686:"cc1fab7c",823:"2680a36f",941:"3377d28b",965:"eefa04e6"}[e]+".css"}}(),function(){n.g=function(){if("object"===typeof globalThis)return globalThis;try{return this||new Function("return this")()}catch(e){if("object"===typeof window)return window}}()}(),function(){n.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)}}(),function(){var e={},t="board-site-front:";n.l=function(o,r,a,i){if(e[o])e[o].push(r);else{var s,c;if(void 0!==a)for(var l=document.getElementsByTagName("script"),u=0;u<l.length;u++){var f=l[u];if(f.getAttribute("src")==o||f.getAttribute("data-webpack")==t+a){s=f;break}}s||(c=!0,s=document.createElement("script"),s.charset="utf-8",s.timeout=120,n.nc&&s.setAttribute("nonce",n.nc),s.setAttribute("data-webpack",t+a),s.src=o),e[o]=[r];var d=function(t,n){s.onerror=s.onload=null,clearTimeout(b);var r=e[o];if(delete e[o],s.parentNode&&s.parentNode.removeChild(s),r&&r.forEach((function(e){return e(n)})),t)return t(n)},b=setTimeout(d.bind(null,void 0,{type:"timeout",target:s}),12e4);s.onerror=d.bind(null,s.onerror),s.onload=d.bind(null,s.onload),c&&document.head.appendChild(s)}}}(),function(){n.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})}}(),function(){n.p="/"}(),function(){var e=function(e,t,n,o){var r=document.createElement("link");r.rel="stylesheet",r.type="text/css";var a=function(a){if(r.onerror=r.onload=null,"load"===a.type)n();else{var i=a&&("load"===a.type?"missing":a.type),s=a&&a.target&&a.target.href||t,c=new Error("Loading CSS chunk "+e+" failed.\n("+s+")");c.code="CSS_CHUNK_LOAD_FAILED",c.type=i,c.request=s,r.parentNode.removeChild(r),o(c)}};return r.onerror=r.onload=a,r.href=t,document.head.appendChild(r),r},t=function(e,t){for(var n=document.getElementsByTagName("link"),o=0;o<n.length;o++){var r=n[o],a=r.getAttribute("data-href")||r.getAttribute("href");if("stylesheet"===r.rel&&(a===e||a===t))return r}var i=document.getElementsByTagName("style");for(o=0;o<i.length;o++){r=i[o],a=r.getAttribute("data-href");if(a===e||a===t)return r}},o=function(o){return new Promise((function(r,a){var i=n.miniCssF(o),s=n.p+i;if(t(i,s))return r();e(o,s,r,a)}))},r={143:0};n.f.miniCss=function(e,t){var n={12:1,32:1,46:1,68:1,70:1,108:1,142:1,206:1,241:1,242:1,364:1,367:1,502:1,504:1,560:1,562:1,618:1,646:1,650:1,666:1,686:1,823:1,941:1,965:1};r[e]?t.push(r[e]):0!==r[e]&&n[e]&&t.push(r[e]=o(e).then((function(){r[e]=0}),(function(t){throw delete r[e],t})))}}(),function(){var e={143:0};n.f.j=function(t,o){var r=n.o(e,t)?e[t]:void 0;if(0!==r)if(r)o.push(r[2]);else if(142!=t){var a=new Promise((function(n,o){r=e[t]=[n,o]}));o.push(r[2]=a);var i=n.p+n.u(t),s=new Error,c=function(o){if(n.o(e,t)&&(r=e[t],0!==r&&(e[t]=void 0),r)){var a=o&&("load"===o.type?"missing":o.type),i=o&&o.target&&o.target.src;s.message="Loading chunk "+t+" failed.\n("+a+": "+i+")",s.name="ChunkLoadError",s.type=a,s.request=i,r[1](s)}};n.l(i,c,"chunk-"+t,t)}else e[t]=0},n.O.j=function(t){return 0===e[t]};var t=function(t,o){var r,a,i=o[0],s=o[1],c=o[2],l=0;if(i.some((function(t){return 0!==e[t]}))){for(r in s)n.o(s,r)&&(n.m[r]=s[r]);if(c)var u=c(n)}for(t&&t(o);l<i.length;l++)a=i[l],n.o(e,a)&&e[a]&&e[a][0](),e[a]=0;return n.O(u)},o=self["webpackChunkboard_site_front"]=self["webpackChunkboard_site_front"]||[];o.forEach(t.bind(null,0)),o.push=t.bind(null,o.push.bind(o))}();var o=n.O(void 0,[998],(function(){return n(8723)}));o=n.O(o)})();
//# sourceMappingURL=app.d4a68ff2.js.map