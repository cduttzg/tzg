(window.webpackJsonp=window.webpackJsonp||[]).push([[0],{174:function(e,t,a){e.exports=a(344)},179:function(e,t,a){},180:function(e,t,a){},271:function(e,t,a){},274:function(e,t,a){},282:function(e,t,a){},287:function(e,t,a){},288:function(e,t,a){},343:function(e,t,a){},344:function(e,t,a){"use strict";a.r(t);var r=a(0),n=a.n(r),s=a(7),l=a.n(s),i=a(18),o=a(19),c=a(22),m=a(20),u=a(23),d=a(47),p=a(46),h=(a(179),a(345)),f=a(352),g=a(348),v=a(356),E=a(346),b=a(353),y=a(27),w=a(355),j=a(351),O=a(358),S=a(10),k=a(354);a(180);function C(e){return fetch(e,{method:"GET",headers:new Headers({"Content-Type":"application/json"})}).then(function(t){return I(e,t)}).catch(function(t){return console.error("Requset failed. Url=".concat(e,". Message=").concat(t)),{error:{messge:"request failed"}}})}function q(e,t){return fetch(e,{method:"POST",headers:new Headers({"Content-Type":"application/json"}),body:JSON.stringify(t)}).then(function(t){return I(e,t)}).catch(function(t){return console.error("Requset failed. Url=".concat(e,". Message=").concat(t)),{error:{messge:"request failed"}}})}function I(e,t){return t.status<500?t.json():(console.error("Requset failed. Url=".concat(e,". Message=").concat(t.statusText)),{error:{message:"request failed due to server error"}})}var N=function(e){function t(){var e,a;Object(i.a)(this,t);for(var r=arguments.length,n=new Array(r),s=0;s<r;s++)n[s]=arguments[s];return(a=Object(c.a)(this,(e=Object(m.a)(t)).call.apply(e,[this].concat(n)))).handleSubmit=function(e){e.preventDefault();var t=O.a,r=a.props.handleSubmit;a.props.form.validateFields(function(e,a){if(!e){console.log("Received values of form: ",a);q("/api/user/login",{"\u7528\u6237\u540d":a.username,"\u5bc6\u7801":btoa(a.password)}).then(function(e){var n=e.code,s=e.message,l=e.data;200!==n&&t.error(s);var i={username:a.username,isFrozen:l["\u662f\u5426\u88ab\u51bb\u7ed3"],role:l["\u89d2\u8272"],isLogin:!0};r(i)})}})},a}return Object(u.a)(t,e),Object(o.a)(t,[{key:"render",value:function(){var e=this.props.form.getFieldDecorator;return n.a.createElement(g.a,{onSubmit:this.handleSubmit,className:"login-form"},n.a.createElement(g.a.Item,null,e("username",{rules:[{required:!0,message:"\u8bf7\u8f93\u5165\u4f60\u7684\u7528\u6237\u540d\uff01"}]})(n.a.createElement(f.a,{prefix:n.a.createElement(S.a,{type:"user",style:{color:"rgba(0,0,0,.25)"}}),placeholder:"\u7528\u6237\u540d"}))),n.a.createElement(g.a.Item,null,e("password",{rules:[{required:!0,message:"\u8bf7\u8f93\u5165\u4f60\u7684\u5bc6\u7801\uff01"}]})(n.a.createElement(f.a,{prefix:n.a.createElement(S.a,{type:"lock",style:{color:"rgba(0,0,0,.25)"}}),type:"password",placeholder:"\u4f60\u7684\u5bc6\u7801"}))),n.a.createElement(g.a.Item,null,e("remember",{valuePropName:"checked",initialValue:!0})(n.a.createElement(k.a,null,"\u8bb0\u4f4f\u5bc6\u7801")),n.a.createElement("a",{className:"login-form-forgot",href:"seefun.club"},"\u5fd8\u8bb0\u5bc6\u7801"),n.a.createElement(y.a,{type:"primary",htmlType:"submit",className:"login-form-button"},"\u767b\u9646"),"\u6216\u8005  \u70b9\u51fb\u6ce8\u518c\u6309\u94ae\u53bb\u6ce8\u518c\u4e00\u4e2a\uff1f"))}}]),t}(n.a.Component),D=a(95),L=(a(271),function(e){function t(){var e,a;Object(i.a)(this,t);for(var r=arguments.length,n=new Array(r),s=0;s<r;s++)n[s]=arguments[s];return(a=Object(c.a)(this,(e=Object(m.a)(t)).call.apply(e,[this].concat(n)))).state={confirmDirty:!1,registerButtonDisable:!0},a.handleSubmit=function(e){e.preventDefault();var t=O.a,r=a.props.handleRegister;a.props.form.validateFieldsAndScroll(function(e,a){if(!e){console.log("Received values of form: ",a);q("/api/user/register",{"\u5b66\u53f7":a.schoolNumber,"\u7528\u6237\u540d":a.nickname,"\u5bc6\u7801":btoa(a.password_1),"\u6559\u52a1\u5904\u5bc6\u7801":a.password_2,"\u624b\u673a\u53f7":a.phone,"\u90ae\u7bb1":a.email,"\u5730\u5740":a.address}).then(function(e){var n=e.code,s=e.message;e.data;200!==n&&t.error(s);var l={username:a.nickname,isLogin:!0};r(l)})}})},a.handleConfirmBlur=function(e){var t=e.target.value;a.setState({confirmDirty:a.state.confirmDirty||!!t})},a.compareToFirstPassword=function(e,t,r){var n=a.props.form;t&&t!==n.getFieldValue("password_1")?r("\u4e24\u6b21\u8f93\u5165\u7684\u5bc6\u7801\u4e0d\u4e00\u81f4\uff01"):r()},a.validateToNextPassword=function(e,t,r){var n=a.props.form;t&&a.state.confirmDirty&&n.validateFields(["confirm"],{force:!0}),r()},a}return Object(u.a)(t,e),Object(o.a)(t,[{key:"render",value:function(){var e=this,t=this.props.form.getFieldDecorator,a={wrapperCol:{xs:{span:24,offset:0},sm:{span:16,offset:8}}};return n.a.createElement(g.a,Object.assign({},{labelCol:{xs:{span:24},sm:{span:8}},wrapperCol:{xs:{span:24},sm:{span:16}}},{onSubmit:this.handleSubmit}),n.a.createElement(g.a.Item,{label:"\u7535\u5b50\u90ae\u7bb1"},t("email",{rules:[{type:"email",message:"\u8f93\u5165\u7684\u90ae\u7bb1\u683c\u5f0f\u4e0d\u89c4\u8303\uff01"},{required:!0,message:"\u8bf7\u8f93\u5165\u4f60\u7684\u7535\u5b50\u90ae\u7bb1\uff01"}]})(n.a.createElement(f.a,null))),n.a.createElement(g.a.Item,{label:"\u5bc6\u7801",hasFeedback:!0},t("password_1",{rules:[{required:!0,message:"\u8bf7\u8f93\u5165\u4f60\u7684\u5bc6\u7801\uff01"},{validator:this.validateToNextPassword}]})(n.a.createElement(f.a.Password,null))),n.a.createElement(g.a.Item,{label:"\u786e\u8ba4\u5bc6\u7801",hasFeedback:!0},t("confirm",{rules:[{required:!0,message:"\u8bf7\u786e\u8ba4\u4f60\u8f93\u5165\u7684\u5bc6\u7801\uff01"},{validator:this.compareToFirstPassword}]})(n.a.createElement(f.a.Password,{onBlur:this.handleConfirmBlur}))),n.a.createElement(g.a.Item,{label:n.a.createElement("span",null,"\u5b66\u53f7\xa0",n.a.createElement(D.a,{title:"\u7528\u4e8e\u9a8c\u8bc1\u8eab\u4efd\u7684\u5b66\u53f7"},n.a.createElement(S.a,{type:"question-circle-o"})))},t("schoolNumber",{rules:[{required:!0,message:"\u8bf7\u8f93\u5165\u4f60\u7684\u5b66\u53f7\uff01",whitespace:!0}]})(n.a.createElement(f.a,null))),n.a.createElement(g.a.Item,{label:"\u7528\u4e8e\u9a8c\u8bc1\u8eab\u4efd\u7684\u6559\u52a1\u5904\u5bc6\u7801",hasFeedback:!0},t("password_2",{rules:[{required:!0,message:"\u8bf7\u8f93\u5165\u4f60\u7684\u6559\u52a1\u5904\u5bc6\u7801\uff01"},{validator:this.validateToNextPassword}]})(n.a.createElement(f.a.Password,null))),n.a.createElement(g.a.Item,{label:n.a.createElement("span",null,"\u7528\u6237\u540d\xa0",n.a.createElement(D.a,{title:"\u8bf7\u8f93\u5165\u4f60\u7684\u7528\u6237\u540d"},n.a.createElement(S.a,{type:"question-circle-o"})))},t("nickname",{rules:[{required:!0,message:"\u8bf7\u8f93\u5165\u4f60\u7684\u7528\u6237\u540d\uff01",whitespace:!0}]})(n.a.createElement(f.a,null))),n.a.createElement(g.a.Item,{label:"\u7535\u8bdd\u53f7\u7801"},t("phone",{rules:[{required:!0,message:"\u8bf7\u8f93\u5165\u4f60\u7684\u7535\u8bdd\u53f7\u7801\uff01"}]})(n.a.createElement(f.a,{style:{width:"100%"}}))),n.a.createElement(g.a.Item,{label:n.a.createElement("span",null,"\u6536\u8d27\u5730\u5740\xa0",n.a.createElement(D.a,{title:"\u8bf7\u8f93\u5165\u4f60\u7684\u6536\u8d27\u5730\u5740"},n.a.createElement(S.a,{type:"question-circle-o"})))},t("address",{rules:[{required:!0,message:"\u8bf7\u8f93\u5165\u4f60\u7684\u6536\u8d27\u5730\u5740\uff01",whitespace:!0}]})(n.a.createElement(f.a,null))),n.a.createElement(g.a.Item,a,t("agreement",{valuePropName:"checked"})(n.a.createElement(k.a,{onChange:function(){e.setState({registerButtonDisable:!e.state.registerButtonDisable})}},"\u6211\u5df2\u7ecf\u9605\u8bfb\u5e76\u540c\u610f ",n.a.createElement("a",{href:"seefun.club"},"\u7528\u6237\u534f\u8bae")))),n.a.createElement(g.a.Item,a,n.a.createElement(y.a,{disabled:this.state.registerButtonDisable,type:"primary",htmlType:"submit"},"\u6ce8\u518c")))}}]),t}(n.a.Component)),x=(a(274),h.a.Header),F=f.a.Search,R=function(e){function t(e){var a;return Object(i.a)(this,t),(a=Object(c.a)(this,Object(m.a)(t).call(this,e))).handleLoginClick=function(){a.setState({modal:{visible:!0}})},a.handleRegisterClick=function(){a.setState({drawer:{visible:!0}})},a.handleLoginModalCancle=function(){a.setState({modal:{visible:!1}})},a.handleRegisterModalCancle=function(){a.setState({drawer:{visible:!1}})},a.handleLoginFormSubmit=function(e){console.log(e),a.setState({user:e})},a.handleRegisterSubmit=function(e){console.log(e),a.setState({user:e})},a.state={user:{role:0,username:"",isLogin:!1,avatarSrc:null,isFrozen:!1},modal:{modalText:"",visible:!1},drawer:{drawerText:"",visible:!1}},a}return Object(u.a)(t,e),Object(o.a)(t,[{key:"componentDidMount",value:function(){}},{key:"render",value:function(){var e=this,t=this.state.user,a=this.state.modal,r=this.state.drawer,s=g.a.create({name:"normal_login"})(N),l=g.a.create({name:"register"})(L),i=n.a.createElement(v.b,null,n.a.createElement(v.b.Item,{key:"0"},n.a.createElement(d.b,{to:{path:"/users/message",query:{username:"\u4e2a\u4eba\u4fe1\u606f\u7ba1\u7406"}}},"\u4e2a\u4eba\u4fe1\u606f\u7ba1\u7406")),n.a.createElement(v.b.Divider,null),n.a.createElement(v.b.Item,{key:"1"},n.a.createElement(d.b,{to:{path:"users/home",query:{username:"\u4e2a\u4eba\u4e2d\u5fc3"}}},"\u4e2a\u4eba\u4e2d\u5fc3")),n.a.createElement(v.b.Divider,null),n.a.createElement(v.b.Item,{key:"2",onClick:function(){e.setState({user:{username:"",isLogin:!1}})}},"\u9000\u51fa\u767b\u9646"));return n.a.createElement("div",{id:"header"},n.a.createElement(x,{id:"header",style:{position:"fixed",zIndex:1,width:"100%"}},n.a.createElement("div",{className:"logo"}),n.a.createElement("div",{className:"siteName"},"\u8d1d\u58f3"),t.isLogin?n.a.createElement(b.a,{overlay:i,trigger:["click"]},n.a.createElement(E.a,{className:"userAvatar",size:40,src:t.avatarSrc})):n.a.createElement(E.a,{className:"userAvatar",size:40,icon:"user"}),t.isLogin?null:n.a.createElement("div",{className:"register"},n.a.createElement(y.a,{onClick:this.handleRegisterClick},"\u6ce8\u518c"),n.a.createElement(w.a,{title:"\u6ce8\u518c\u5c5e\u4e8e\u4f60\u7684\u8d26\u6237",width:700,onClose:this.handleRegisterModalCancle,visible:r.visible},n.a.createElement(l,{handleRegister:this.handleRegisterSubmit}))),t.isLogin?null:n.a.createElement("div",{className:"login"},n.a.createElement(y.a,{onClick:this.handleLoginClick},"\u767b\u9646"),n.a.createElement(j.a,{visible:a.visible,footer:null,onCancel:this.handleLoginModalCancle,title:"\u767b\u9646"},n.a.createElement(s,{handleSubmit:this.handleLoginFormSubmit}))),n.a.createElement("div",{className:"forSearch"},n.a.createElement(F,{placeholder:"\u8f93\u5165\u4f60\u60f3\u67e5\u627e\u7684\u7269\u54c1\u540d\u79f0",onSearch:function(e){console.log(e)},enterButton:!0})),n.a.createElement(v.b,{theme:"light",mode:"horizontal",defaultSelectedKeys:["1"],style:{lineHeight:"64px"}},n.a.createElement(v.b.Item,{key:"1"},n.a.createElement(d.b,{to:"/homePage"},"\u9996\u9875")),n.a.createElement(v.b.Item,{key:"2"},n.a.createElement(d.b,{to:"/activity"},"\u798f\u5229")),1===t.role||2===t.role?n.a.createElement(v.b.Item,{key:"3"},n.a.createElement(d.b,{to:"/backstage"},"\u540e\u53f0\u7ba1\u7406")):null)))}}]),t}(r.Component),T=a(170),P=a(350),A=a(357),M=a(347),B=(a(282),P.a.Meta),z=function(e){function t(){return Object(i.a)(this,t),Object(c.a)(this,Object(m.a)(t).apply(this,arguments))}return Object(u.a)(t,e),Object(o.a)(t,[{key:"render",value:function(){var e=this.props.goods;return console.log(e),n.a.createElement("div",{className:"goodsInfo"},e.length?n.a.createElement("div",{className:"content"},n.a.createElement(M.a,{style:{fontSize:23},orientation:"left"},this.props.name),e.map(function(e,t){return n.a.createElement(P.a,{key:t,hoverable:!0,style:{width:200,float:"left",marginBottom:10,marginLeft:25,marginRight:20},cover:n.a.createElement("img",{alt:"example",src:"https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png"})},n.a.createElement(B,{title:e["\u5546\u54c1\u540d\u79f0"],description:e["\u5546\u54c1\u63cf\u8ff0"]}))})):n.a.createElement("div",null,n.a.createElement(A.a,{active:!0}),n.a.createElement(A.a,{active:!0})))}}]),t}(n.a.Component),V=(a(287),function(e){function t(e){var a;return Object(i.a)(this,t),(a=Object(c.a)(this,Object(m.a)(t).call(this,e))).state={books:[],virtuals:[],houses:[],activities:[]},a}return Object(u.a)(t,e),Object(o.a)(t,[{key:"componentDidMount",value:function(){var e=this;C("/api/home").then(function(t){t.code,t.message;var a=t.data;e.setState({books:a["\u4e66\u7c4d"],virtuals:a["\u865a\u62df"],houses:a["\u623f\u5c4b"],others:a["\u5176\u5b83"],activities:a["\u798f\u5229"]})})}},{key:"render",value:function(){var e=[],t=["\u4e66\u7c4d","\u865a\u62df","\u623f\u5c4b","\u5176\u5b83","\u798f\u5229"];for(var a in this.state)e=[].concat(Object(T.a)(e),[this.state[a]]);return n.a.createElement("div",{id:"home"},e.map(function(e,a){return n.a.createElement(z,{key:a,name:t[a],goods:e})}))}}]),t}(r.Component)),_=a(349);a(288);var U=function(e){function t(){var e,a;Object(i.a)(this,t);for(var r=arguments.length,n=new Array(r),s=0;s<r;s++)n[s]=arguments[s];return(a=Object(c.a)(this,(e=Object(m.a)(t)).call.apply(e,[this].concat(n)))).state={confirmDirty:!1,registerButtonDisable:!0,upLoading:!1,imgFile:null,user:a.props.user},a.handleSubmit=function(e){e.preventDefault();O.a,a.props.handleRegister;a.props.form.validateFieldsAndScroll(function(e,t){e||console.log("Received values of form: ",t)})},a.handleConfirmBlur=function(e){var t=e.target.value;a.setState({confirmDirty:a.state.confirmDirty||!!t})},a.compareToFirstPassword=function(e,t,r){var n=a.props.form;t&&t!==n.getFieldValue("password_1")?r("\u4e24\u6b21\u8f93\u5165\u7684\u5bc6\u7801\u4e0d\u4e00\u81f4\uff01"):r()},a.validateToNextPassword=function(e,t,r){var n=a.props.form;t&&a.state.confirmDirty&&n.validateFields(["confirm"],{force:!0}),r()},a.customRequest=function(e){if(!function(e){var t="image/png"===e.type||"image/jpeg"===e.type;if(t){var a=e.size/1024/1024>2;return a&&O.a.error("\u4f60\u4e0a\u4f20\u7684\u56fe\u7247\u6587\u4ef6\u5927\u5c0f\u8d85\u8fc7\u4e862M\u7684\u9650\u5236\uff01"),t&&!a}O.a.error("\u4f60\u53ea\u80fd\u4e0a\u4f20png\u3001jpg\u56fe\u50cf\u54e6\u3002")}(e.file))return!1;a.setState({imgFile:e.file}),function(e,t){var a=new FileReader;a.addEventListener("load",function(){t(a.result)}),a.readAsDataURL(e)}(e.file,function(e){a.setState({user:{userAvatarSrc:e}})})},a}return Object(u.a)(t,e),Object(o.a)(t,[{key:"componentDidMount",value:function(){}},{key:"render",value:function(){var e=this.props.form.getFieldDecorator;return n.a.createElement(g.a,Object.assign({},{labelCol:{xs:{span:24},sm:{span:8}},wrapperCol:{xs:{span:24},sm:{span:16}}},{onSubmit:this.handleSubmit}),n.a.createElement(M.a,{style:{fontSize:17},orientation:"left"},"\u4f60\u7684\u5934\u50cf"),n.a.createElement(_.a,{name:"userAvatar",listType:"picture-card",customRequest:this.customRequest,className:"avatar-uploader avatar-uploader-c1er",showUploadList:!1},this.state.user.userAvatarSrc?n.a.createElement("img",{id:"showAvatar-c1er",src:this.state.user.userAvatarSrc}):n.a.createElement("div",null,n.a.createElement(S.a,{type:this.state.upLoading?"loading":"plus"}),n.a.createElement("div",{className:"upLoadtext"},"\u4e0a\u4f20\u4f60\u7684\u5934\u50cf"))),n.a.createElement(M.a,{style:{fontSize:17},orientation:"left"},"\u57fa\u672c\u4fe1\u606f"),n.a.createElement(g.a.Item,{label:"\u7535\u5b50\u90ae\u7bb1"},e("email",{rules:[{type:"email",message:"\u8f93\u5165\u7684\u90ae\u7bb1\u683c\u5f0f\u4e0d\u89c4\u8303\uff01"},{required:!0,message:"\u8bf7\u8f93\u5165\u4f60\u7684\u7535\u5b50\u90ae\u7bb1\uff01"}],initialValue:this.state.user.email})(n.a.createElement(f.a,null))),n.a.createElement(g.a.Item,{label:n.a.createElement("span",null,"\u7528\u6237\u540d\xa0",n.a.createElement(D.a,{title:"\u8bf7\u4e0d\u8981\u8f93\u5165\u7a00\u5947\u53e4\u602a\u7684\u540d\u5b57"},n.a.createElement(S.a,{type:"question-circle-o"})))},e("nickname",{rules:[{required:!0,message:"\u8bf7\u8f93\u5165\u4f60\u7684\u7528\u6237\u540d\uff01",whitespace:!0}],initialValue:this.state.user.username})(n.a.createElement(f.a,null))),n.a.createElement(g.a.Item,{label:"\u7535\u8bdd\u53f7\u7801"},e("phone",{rules:[{required:!0,message:"\u8bf7\u8f93\u5165\u4f60\u7684\u7535\u8bdd\u53f7\u7801\uff01"}],initialValue:this.state.user.phone})(n.a.createElement(f.a,{style:{width:"100%"}}))),n.a.createElement(g.a.Item,{label:n.a.createElement("span",null,"\u6536\u8d27\u5730\u5740\xa0",n.a.createElement(D.a,{title:"\u8bf7\u586b\u5165\u4f60\u5c45\u4f4f\u7684\u5bdd\u5ba4\uff0c\u4fbf\u4e8e\u5356\u5bb6\u8054\u7cfb"},n.a.createElement(S.a,{type:"question-circle-o"})))},e("address",{rules:[{required:!0,message:"\u8bf7\u8f93\u5165\u4f60\u7684\u6536\u8d27\u5730\u5740\uff01",whitespace:!0}],initialValue:this.state.user.address})(n.a.createElement(f.a,null))),n.a.createElement(M.a,{style:{fontSize:17},orientation:"left"},"\u6536\u6b3e\u7801"),n.a.createElement(_.a,{name:"moneyCode",listType:"picture-card",className:"avatar-uploader avatar-uploader-c1er",showUploadList:!1},this.state.user.moneyCode?n.a.createElement("img",{id:"moneyCode-c1er",src:this.state.user.moneyCode}):n.a.createElement("div",null,n.a.createElement(S.a,{type:this.state.upLoading?"loading":"plus"}),n.a.createElement("div",{className:"upLoadtext"},"\u4e0a\u4f20\u4f60\u7684\u6536\u6b3e\u7801"))),n.a.createElement(g.a.Item,{wrapperCol:{xs:{span:24,offset:0},sm:{span:16,offset:8}}},n.a.createElement(y.a,{type:"primary",htmlType:"submit",size:"large",shape:"round"},"\u7115\u7136\u4e00\u65b0\u5427")))}}]),t}(n.a.Component),H=(a(343),function(e){function t(e){var a;return Object(i.a)(this,t),(a=Object(c.a)(this,Object(m.a)(t).call(this,e))).state={user:{username:a.props.username||"\u5f20\u4e09"}},a}return Object(u.a)(t,e),Object(o.a)(t,[{key:"componentWillMount",value:function(){var e=this,t=C("/api/user/home/message?username=".concat(this.state.user.username)),a={userAvatarSrc:"a",email:"a",username:"a",phone:"a",address:"a"};t.then(function(t){t.code,t.message,t.data;e.setState({user:a})})}},{key:"render",value:function(){console.log(this.props.location);var e=g.a.create({name:"register"})(U);return n.a.createElement("div",{id:"userMessage"},n.a.createElement(e,{user:this.state.user}))}}]),t}(r.Component)),J=function(e){function t(e){var a;return Object(i.a)(this,t),(a=Object(c.a)(this,Object(m.a)(t).call(this,e))).state={user:{name:"\u5f20\u5c71",role:0,ifLogin:!1}},a}return Object(u.a)(t,e),Object(o.a)(t,[{key:"render",value:function(){return n.a.createElement(d.a,null,n.a.createElement(p.a,{path:"/",component:R}),n.a.createElement(p.c,null,n.a.createElement(p.a,{exact:!0,path:"/homePage",component:V}),n.a.createElement(p.c,null,n.a.createElement(p.a,{path:"/users/home",component:H}),n.a.createElement(p.a,{path:"/users/message",component:H}))))}}]),t}(r.Component);l.a.render(n.a.createElement(J,null),document.getElementById("root"))}},[[174,1,2]]]);
//# sourceMappingURL=main.065aa904.chunk.js.map