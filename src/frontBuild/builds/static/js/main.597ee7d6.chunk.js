(window.webpackJsonp=window.webpackJsonp||[]).push([[0],{137:function(e,a,t){e.exports=t(256)},142:function(e,a,t){},143:function(e,a,t){},245:function(e,a,t){},248:function(e,a,t){},256:function(e,a,t){"use strict";t.r(a);var n=t(1),r=t.n(n),l=t(4),c=t.n(l),o=t(28),s=t(29),i=t(31),m=t(30),u=t(32),d=t(105),f=t(40),h=(t(142),t(257)),p=t(260),b=t(259),E=t(265),v=t(258),g=t(262),y=t(21),w=t(264),j=t(261),k=t(9),O=t(263);t(143);function C(e,a){return a.status<500?a.json():(console.error("Requset failed. Url=".concat(e,". Message=").concat(a.statusText)),{error:{message:"request failed due to server error"}})}var S=function(e){function a(){var e,t;Object(o.a)(this,a);for(var n=arguments.length,r=new Array(n),l=0;l<n;l++)r[l]=arguments[l];return(t=Object(i.a)(this,(e=Object(m.a)(a)).call.apply(e,[this].concat(r)))).handleSubmit=function(e){e.preventDefault();var a=t.props.handleSubmit;t.props.form.validateFields(function(e,t){if(!e){console.log("Received values of form: ",t);(function(e,a){return fetch(e,{method:"POST",headers:new Headers({"Content-Type":"application/json"}),body:JSON.stringify(a)}).then(function(a){return C(e,a)}).catch(function(a){return console.error("Requset failed. Url=".concat(e,". Message=").concat(a)),{error:{messge:"request failed"}}})})("/api/user/login",{"\u7528\u6237\u540d":t.username,"\u5bc6\u7801":btoa(t.password)}).then(function(e){var n={username:t.username,isFrozen:e["\u662f\u5426\u88ab\u51bb\u7ed3"]};a(n)})}})},t}return Object(u.a)(a,e),Object(s.a)(a,[{key:"render",value:function(){var e=this.props.form.getFieldDecorator;return r.a.createElement(b.a,{onSubmit:this.handleSubmit,className:"login-form"},r.a.createElement(b.a.Item,null,e("username",{rules:[{required:!0,message:"\u8bf7\u8f93\u5165\u4f60\u7684\u7528\u6237\u540d\uff01"}]})(r.a.createElement(p.a,{prefix:r.a.createElement(k.a,{type:"user",style:{color:"rgba(0,0,0,.25)"}}),placeholder:"\u7528\u6237\u540d"}))),r.a.createElement(b.a.Item,null,e("password",{rules:[{required:!0,message:"\u8bf7\u8f93\u5165\u4f60\u7684\u5bc6\u7801\uff01"}]})(r.a.createElement(p.a,{prefix:r.a.createElement(k.a,{type:"lock",style:{color:"rgba(0,0,0,.25)"}}),type:"password",placeholder:"\u4f60\u7684\u5bc6\u7801"}))),r.a.createElement(b.a.Item,null,e("remember",{valuePropName:"checked",initialValue:!0})(r.a.createElement(O.a,null,"Remember me")),r.a.createElement("a",{className:"login-form-forgot",href:"seefun.club"},"\u5fd8\u8bb0\u5bc6\u7801"),r.a.createElement(y.a,{type:"primary",htmlType:"submit",className:"login-form-button"},"\u767b\u9646"),"\u6216\u8005  ",r.a.createElement("a",{href:"seefun.club"},"\u6ce8\u518c\u4e00\u4e2a")))}}]),a}(r.a.Component),I=t(135),x=(t(245),function(e){function a(){var e,t;Object(o.a)(this,a);for(var n=arguments.length,r=new Array(n),l=0;l<n;l++)r[l]=arguments[l];return(t=Object(i.a)(this,(e=Object(m.a)(a)).call.apply(e,[this].concat(r)))).state={confirmDirty:!1,autoCompleteResult:[]},t.handleSubmit=function(e){e.preventDefault(),t.props.form.validateFieldsAndScroll(function(e,a){e||console.log("Received values of form: ",a)})},t.handleConfirmBlur=function(e){var a=e.target.value;t.setState({confirmDirty:t.state.confirmDirty||!!a})},t.compareToFirstPassword=function(e,a,n){var r=t.props.form;a&&a!==r.getFieldValue("password")?n("\u4e24\u6b21\u8f93\u5165\u7684\u5bc6\u7801\u4e0d\u4e00\u81f4\uff01"):n()},t.validateToNextPassword=function(e,a,n){var r=t.props.form;a&&t.state.confirmDirty&&r.validateFields(["confirm"],{force:!0}),n()},t.handleWebsiteChange=function(e){var a;a=e?[".com",".org",".net"].map(function(a){return"".concat(e).concat(a)}):[],t.setState({autoCompleteResult:a})},t}return Object(u.a)(a,e),Object(s.a)(a,[{key:"render",value:function(){var e=this.props.form.getFieldDecorator,a={wrapperCol:{xs:{span:24,offset:0},sm:{span:16,offset:8}}};return r.a.createElement(b.a,Object.assign({},{labelCol:{xs:{span:24},sm:{span:8}},wrapperCol:{xs:{span:24},sm:{span:16}}},{onSubmit:this.handleSubmit}),r.a.createElement(b.a.Item,{label:"\u7535\u5b50\u90ae\u7bb1"},e("email",{rules:[{type:"email",message:"\u8f93\u5165\u7684\u90ae\u7bb1\u683c\u5f0f\u4e0d\u89c4\u8303\uff01"},{required:!0,message:"\u8bf7\u8f93\u5165\u4f60\u7684\u7535\u5b50\u90ae\u7bb1\uff01"}]})(r.a.createElement(p.a,null))),r.a.createElement(b.a.Item,{label:"\u5bc6\u7801",hasFeedback:!0},e("password",{rules:[{required:!0,message:"\u8bf7\u8f93\u5165\u4f60\u7684\u5bc6\u7801\uff01"},{validator:this.validateToNextPassword}]})(r.a.createElement(p.a.Password,null))),r.a.createElement(b.a.Item,{label:"\u786e\u8ba4\u5bc6\u7801",hasFeedback:!0},e("confirm",{rules:[{required:!0,message:"\u8bf7\u786e\u8ba4\u4f60\u8f93\u5165\u7684\u5bc6\u7801\uff01"},{validator:this.compareToFirstPassword}]})(r.a.createElement(p.a.Password,{onBlur:this.handleConfirmBlur}))),r.a.createElement(b.a.Item,{label:r.a.createElement("span",null,"\u7528\u6237\u540d\xa0",r.a.createElement(I.a,{title:"\u8bf7\u8f93\u5165\u4f60\u7684\u7528\u6237\u540d"},r.a.createElement(k.a,{type:"question-circle-o"})))},e("nickname",{rules:[{required:!0,message:"\u8bf7\u8f93\u5165\u4f60\u7684\u7528\u6237\u540d\uff01",whitespace:!0}]})(r.a.createElement(p.a,null))),r.a.createElement(b.a.Item,{label:"\u7535\u8bdd\u53f7\u7801"},e("phone",{rules:[{required:!0,message:"\u8bf7\u8f93\u5165\u4f60\u7684\u7535\u8bdd\u53f7\u7801\uff01"}]})(r.a.createElement(p.a,{style:{width:"100%"}}))),r.a.createElement(b.a.Item,a,e("agreement",{valuePropName:"checked"})(r.a.createElement(O.a,null,"\u6211\u5df2\u7ecf\u9605\u8bfb\u5e76\u540c\u610f",r.a.createElement("a",{href:"seefun.club"},"\u7528\u6237\u534f\u8bae")))),r.a.createElement(b.a.Item,a,r.a.createElement(y.a,{type:"primary",htmlType:"submit"},"\u6ce8\u518c")))}}]),a}(r.a.Component)),N=(t(248),h.a.Header),F=p.a.Search,T=function(e){function a(e){var t;return Object(o.a)(this,a),(t=Object(i.a)(this,Object(m.a)(a).call(this,e))).handleLoginClick=function(){t.setState({modal:{visible:!0}})},t.handleRegisterClick=function(){t.setState({drawer:{visible:!0}})},t.handleLoginModalCancle=function(){t.setState({modal:{visible:!1}})},t.handleRegisterModalCancle=function(){t.setState({drawer:{visible:!1}})},t.handleLoginFormSubmit=function(e){t.setState({user:e})},t.state={user:{role:0,username:"",isLogin:!1,avatarSrc:null,isFrozen:!1},modal:{modalText:"",visible:!1},drawer:{drawerText:"",visible:!1}},t}return Object(u.a)(a,e),Object(s.a)(a,[{key:"componentDidMount",value:function(){}},{key:"render",value:function(){var e=this.state.user,a=this.state.modal,t=this.state.drawer,n=b.a.create({name:"normal_login"})(S),l=b.a.create({name:"register"})(x),c=r.a.createElement(E.b,null,r.a.createElement(E.b.Item,{key:"0"},r.a.createElement("a",{href:"seefun.club"},"\u4e2a\u4eba\u4fe1\u606f\u7ba1\u7406")),r.a.createElement(E.b.Divider,null),r.a.createElement(E.b.Item,{key:"1"},r.a.createElement("a",{href:"seefun.club"},"\u5356\u5bb6\u4fe1\u606f\u7ba1\u7406")),r.a.createElement(E.b.Divider,null),r.a.createElement(E.b.Item,{key:"2"},r.a.createElement("a",{href:"seefun.club"},"\u67e5\u770b\u8ba2\u5355\u4fe1\u606f")));return r.a.createElement(N,{id:"header",style:{position:"fixed",zIndex:1,width:"100%"}},r.a.createElement("div",{className:"logo"}),r.a.createElement("div",{className:"siteName"},"\u8d1d\u58f3"),e.isLogin?r.a.createElement(g.a,{overlay:c,trigger:["click"]},r.a.createElement(v.a,{className:"userAvatar",size:40,src:e.avatarSrc})):r.a.createElement(v.a,{className:"userAvatar",size:40,icon:"user"}),e.isLogin?null:r.a.createElement("div",{className:"register"},r.a.createElement(y.a,{onClick:this.handleRegisterClick},"\u6ce8\u518c"),r.a.createElement(w.a,{title:"\u6ce8\u518c\u5c5e\u4e8e\u4f60\u7684\u8d26\u6237",width:700,onClose:this.handleRegisterModalCancle,visible:t.visible},r.a.createElement(l,null))),e.isLogin?null:r.a.createElement("div",{className:"login"},r.a.createElement(y.a,{onClick:this.handleLoginClick},"\u767b\u9646"),r.a.createElement(j.a,{visible:a.visible,onCancel:this.handleLoginModalCancle,title:"\u767b\u9646",okText:"\u767b\u9646",cancelText:"\u53d6\u6d88"},r.a.createElement(n,{handleSubmit:this.handleLoginFormSubmit}))),r.a.createElement("div",{className:"forSearch"},r.a.createElement(F,{placeholder:"\u8f93\u5165\u4f60\u60f3\u67e5\u627e\u7684\u7269\u54c1\u540d\u79f0",onSearch:function(e){console.log(e)},enterButton:!0})),r.a.createElement(E.b,{theme:"light",mode:"horizontal",defaultSelectedKeys:["2"],style:{lineHeight:"64px"}},r.a.createElement(E.b.Item,{key:"1"},"\u9996\u9875"),r.a.createElement(E.b.Item,{key:"2"},"\u798f\u5229"),1===e.role&&2===e.role?r.a.createElement(E.b.Item,{key:"3"},"\u540e\u53f0\u7ba1\u7406"):null))}}]),a}(n.Component),q=function(e){function a(){return Object(o.a)(this,a),Object(i.a)(this,Object(m.a)(a).apply(this,arguments))}return Object(u.a)(a,e),Object(s.a)(a,[{key:"render",value:function(){return r.a.createElement(T,null)}}]),a}(n.Component),D=function(e){function a(e){var t;return Object(o.a)(this,a),(t=Object(i.a)(this,Object(m.a)(a).call(this,e))).state={user:{name:"\u5f20\u5c71",role:0,ifLogin:!1}},t}return Object(u.a)(a,e),Object(s.a)(a,[{key:"render",value:function(){return r.a.createElement(d.a,null,r.a.createElement(f.c,null,r.a.createElement(f.a,{exact:!0,path:"/",component:q})))}}]),a}(n.Component);c.a.render(r.a.createElement(D,null),document.getElementById("root"))}},[[137,1,2]]]);
//# sourceMappingURL=main.597ee7d6.chunk.js.map