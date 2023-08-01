/******************************************************************************
Copyright (c) Microsoft Corporation.

Permission to use, copy, modify, and/or distribute this software for any
purpose with or without fee is hereby granted.

THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES WITH
REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF MERCHANTABILITY
AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT,
INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM
LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR
OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR
PERFORMANCE OF THIS SOFTWARE.
***************************************************************************** */
/* global Reflect, Promise */

var extendStatics = function(d, b) {
    extendStatics = Object.setPrototypeOf ||
        ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
        function (d, b) { for (var p in b) if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p]; };
    return extendStatics(d, b);
};

function __extends(d, b) {
    if (typeof b !== "function" && b !== null)
        throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");
    extendStatics(d, b);
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
}

var __assign = function() {
    __assign = Object.assign || function __assign(t) {
        for (var s, i = 1, n = arguments.length; i < n; i++) {
            s = arguments[i];
            for (var p in s) if (Object.prototype.hasOwnProperty.call(s, p)) t[p] = s[p];
        }
        return t;
    };
    return __assign.apply(this, arguments);
};

function __rest(s, e) {
    var t = {};
    for (var p in s) if (Object.prototype.hasOwnProperty.call(s, p) && e.indexOf(p) < 0)
        t[p] = s[p];
    if (s != null && typeof Object.getOwnPropertySymbols === "function")
        for (var i = 0, p = Object.getOwnPropertySymbols(s); i < p.length; i++) {
            if (e.indexOf(p[i]) < 0 && Object.prototype.propertyIsEnumerable.call(s, p[i]))
                t[p[i]] = s[p[i]];
        }
    return t;
}

var commonjsGlobal = typeof globalThis !== 'undefined' ? globalThis : typeof window !== 'undefined' ? window : typeof global !== 'undefined' ? global : typeof self !== 'undefined' ? self : {};

function getDefaultExportFromCjs (x) {
	return x && x.__esModule && Object.prototype.hasOwnProperty.call(x, 'default') ? x['default'] : x;
}

var react = {exports: {}};

var react_production_min = {};

/**
 * @license React
 * react.production.min.js
 *
 * Copyright (c) Facebook, Inc. and its affiliates.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */
var l=Symbol.for("react.element"),n=Symbol.for("react.portal"),p=Symbol.for("react.fragment"),q=Symbol.for("react.strict_mode"),r=Symbol.for("react.profiler"),t=Symbol.for("react.provider"),u=Symbol.for("react.context"),v=Symbol.for("react.forward_ref"),w=Symbol.for("react.suspense"),x=Symbol.for("react.memo"),y=Symbol.for("react.lazy"),z=Symbol.iterator;function A(a){if(null===a||"object"!==typeof a)return null;a=z&&a[z]||a["@@iterator"];return "function"===typeof a?a:null}
var B={isMounted:function(){return !1},enqueueForceUpdate:function(){},enqueueReplaceState:function(){},enqueueSetState:function(){}},C=Object.assign,D={};function E(a,b,e){this.props=a;this.context=b;this.refs=D;this.updater=e||B;}E.prototype.isReactComponent={};
E.prototype.setState=function(a,b){if("object"!==typeof a&&"function"!==typeof a&&null!=a)throw Error("setState(...): takes an object of state variables to update or a function which returns an object of state variables.");this.updater.enqueueSetState(this,a,b,"setState");};E.prototype.forceUpdate=function(a){this.updater.enqueueForceUpdate(this,a,"forceUpdate");};function F(){}F.prototype=E.prototype;function G(a,b,e){this.props=a;this.context=b;this.refs=D;this.updater=e||B;}var H=G.prototype=new F;
H.constructor=G;C(H,E.prototype);H.isPureReactComponent=!0;var I=Array.isArray,J=Object.prototype.hasOwnProperty,K={current:null},L={key:!0,ref:!0,__self:!0,__source:!0};
function M(a,b,e){var d,c={},k=null,h=null;if(null!=b)for(d in void 0!==b.ref&&(h=b.ref),void 0!==b.key&&(k=""+b.key),b)J.call(b,d)&&!L.hasOwnProperty(d)&&(c[d]=b[d]);var g=arguments.length-2;if(1===g)c.children=e;else if(1<g){for(var f=Array(g),m=0;m<g;m++)f[m]=arguments[m+2];c.children=f;}if(a&&a.defaultProps)for(d in g=a.defaultProps,g)void 0===c[d]&&(c[d]=g[d]);return {$$typeof:l,type:a,key:k,ref:h,props:c,_owner:K.current}}
function N(a,b){return {$$typeof:l,type:a.type,key:b,ref:a.ref,props:a.props,_owner:a._owner}}function O(a){return "object"===typeof a&&null!==a&&a.$$typeof===l}function escape(a){var b={"=":"=0",":":"=2"};return "$"+a.replace(/[=:]/g,function(a){return b[a]})}var P=/\/+/g;function Q(a,b){return "object"===typeof a&&null!==a&&null!=a.key?escape(""+a.key):b.toString(36)}
function R(a,b,e,d,c){var k=typeof a;if("undefined"===k||"boolean"===k)a=null;var h=!1;if(null===a)h=!0;else switch(k){case "string":case "number":h=!0;break;case "object":switch(a.$$typeof){case l:case n:h=!0;}}if(h)return h=a,c=c(h),a=""===d?"."+Q(h,0):d,I(c)?(e="",null!=a&&(e=a.replace(P,"$&/")+"/"),R(c,b,e,"",function(a){return a})):null!=c&&(O(c)&&(c=N(c,e+(!c.key||h&&h.key===c.key?"":(""+c.key).replace(P,"$&/")+"/")+a)),b.push(c)),1;h=0;d=""===d?".":d+":";if(I(a))for(var g=0;g<a.length;g++){k=
a[g];var f=d+Q(k,g);h+=R(k,b,e,f,c);}else if(f=A(a),"function"===typeof f)for(a=f.call(a),g=0;!(k=a.next()).done;)k=k.value,f=d+Q(k,g++),h+=R(k,b,e,f,c);else if("object"===k)throw b=String(a),Error("Objects are not valid as a React child (found: "+("[object Object]"===b?"object with keys {"+Object.keys(a).join(", ")+"}":b)+"). If you meant to render a collection of children, use an array instead.");return h}
function S(a,b,e){if(null==a)return a;var d=[],c=0;R(a,d,"","",function(a){return b.call(e,a,c++)});return d}function T(a){if(-1===a._status){var b=a._result;b=b();b.then(function(b){if(0===a._status||-1===a._status)a._status=1,a._result=b;},function(b){if(0===a._status||-1===a._status)a._status=2,a._result=b;});-1===a._status&&(a._status=0,a._result=b);}if(1===a._status)return a._result.default;throw a._result;}
var U={current:null},V={transition:null},W={ReactCurrentDispatcher:U,ReactCurrentBatchConfig:V,ReactCurrentOwner:K};react_production_min.Children={map:S,forEach:function(a,b,e){S(a,function(){b.apply(this,arguments);},e);},count:function(a){var b=0;S(a,function(){b++;});return b},toArray:function(a){return S(a,function(a){return a})||[]},only:function(a){if(!O(a))throw Error("React.Children.only expected to receive a single React element child.");return a}};react_production_min.Component=E;react_production_min.Fragment=p;
react_production_min.Profiler=r;react_production_min.PureComponent=G;react_production_min.StrictMode=q;react_production_min.Suspense=w;react_production_min.__SECRET_INTERNALS_DO_NOT_USE_OR_YOU_WILL_BE_FIRED=W;
react_production_min.cloneElement=function(a,b,e){if(null===a||void 0===a)throw Error("React.cloneElement(...): The argument must be a React element, but you passed "+a+".");var d=C({},a.props),c=a.key,k=a.ref,h=a._owner;if(null!=b){void 0!==b.ref&&(k=b.ref,h=K.current);void 0!==b.key&&(c=""+b.key);if(a.type&&a.type.defaultProps)var g=a.type.defaultProps;for(f in b)J.call(b,f)&&!L.hasOwnProperty(f)&&(d[f]=void 0===b[f]&&void 0!==g?g[f]:b[f]);}var f=arguments.length-2;if(1===f)d.children=e;else if(1<f){g=Array(f);
for(var m=0;m<f;m++)g[m]=arguments[m+2];d.children=g;}return {$$typeof:l,type:a.type,key:c,ref:k,props:d,_owner:h}};react_production_min.createContext=function(a){a={$$typeof:u,_currentValue:a,_currentValue2:a,_threadCount:0,Provider:null,Consumer:null,_defaultValue:null,_globalName:null};a.Provider={$$typeof:t,_context:a};return a.Consumer=a};react_production_min.createElement=M;react_production_min.createFactory=function(a){var b=M.bind(null,a);b.type=a;return b};react_production_min.createRef=function(){return {current:null}};
react_production_min.forwardRef=function(a){return {$$typeof:v,render:a}};react_production_min.isValidElement=O;react_production_min.lazy=function(a){return {$$typeof:y,_payload:{_status:-1,_result:a},_init:T}};react_production_min.memo=function(a,b){return {$$typeof:x,type:a,compare:void 0===b?null:b}};react_production_min.startTransition=function(a){var b=V.transition;V.transition={};try{a();}finally{V.transition=b;}};react_production_min.unstable_act=function(){throw Error("act(...) is not supported in production builds of React.");};
react_production_min.useCallback=function(a,b){return U.current.useCallback(a,b)};react_production_min.useContext=function(a){return U.current.useContext(a)};react_production_min.useDebugValue=function(){};react_production_min.useDeferredValue=function(a){return U.current.useDeferredValue(a)};react_production_min.useEffect=function(a,b){return U.current.useEffect(a,b)};react_production_min.useId=function(){return U.current.useId()};react_production_min.useImperativeHandle=function(a,b,e){return U.current.useImperativeHandle(a,b,e)};
react_production_min.useInsertionEffect=function(a,b){return U.current.useInsertionEffect(a,b)};react_production_min.useLayoutEffect=function(a,b){return U.current.useLayoutEffect(a,b)};react_production_min.useMemo=function(a,b){return U.current.useMemo(a,b)};react_production_min.useReducer=function(a,b,e){return U.current.useReducer(a,b,e)};react_production_min.useRef=function(a){return U.current.useRef(a)};react_production_min.useState=function(a){return U.current.useState(a)};react_production_min.useSyncExternalStore=function(a,b,e){return U.current.useSyncExternalStore(a,b,e)};
react_production_min.useTransition=function(){return U.current.useTransition()};react_production_min.version="18.2.0";

(function (module) {

	{
	  module.exports = react_production_min;
	}
} (react));

var React$1 = /*@__PURE__*/getDefaultExportFromCjs(react.exports);

var Button = function (_a) {
    var _b = _a.bgColor, bgColor = _b === void 0 ? 'yellow' : _b, _c = _a.textColor, textColor = _c === void 0 ? 'black' : _c, children = _a.children, rest = __rest(_a, ["bgColor", "textColor", "children"]);
    return (React$1.createElement("button", __assign({ style: { backgroundColor: bgColor, color: textColor } }, rest), children));
};

var lib = {};

var uaParser_min = {exports: {}};

/* UAParser.js v1.0.35
   Copyright © 2012-2021 Faisal Salman <f@faisalman.com>
   MIT License */

(function (module, exports) {
	(function(window,undefined$1){var LIBVERSION="1.0.35",EMPTY="",UNKNOWN="?",FUNC_TYPE="function",UNDEF_TYPE="undefined",OBJ_TYPE="object",STR_TYPE="string",MAJOR="major",MODEL="model",NAME="name",TYPE="type",VENDOR="vendor",VERSION="version",ARCHITECTURE="architecture",CONSOLE="console",MOBILE="mobile",TABLET="tablet",SMARTTV="smarttv",WEARABLE="wearable",EMBEDDED="embedded",UA_MAX_LENGTH=350;var AMAZON="Amazon",APPLE="Apple",ASUS="ASUS",BLACKBERRY="BlackBerry",BROWSER="Browser",CHROME="Chrome",EDGE="Edge",FIREFOX="Firefox",GOOGLE="Google",HUAWEI="Huawei",LG="LG",MICROSOFT="Microsoft",MOTOROLA="Motorola",OPERA="Opera",SAMSUNG="Samsung",SHARP="Sharp",SONY="Sony",XIAOMI="Xiaomi",ZEBRA="Zebra",FACEBOOK="Facebook",CHROMIUM_OS="Chromium OS",MAC_OS="Mac OS";var extend=function(regexes,extensions){var mergedRegexes={};for(var i in regexes){if(extensions[i]&&extensions[i].length%2===0){mergedRegexes[i]=extensions[i].concat(regexes[i]);}else {mergedRegexes[i]=regexes[i];}}return mergedRegexes},enumerize=function(arr){var enums={};for(var i=0;i<arr.length;i++){enums[arr[i].toUpperCase()]=arr[i];}return enums},has=function(str1,str2){return typeof str1===STR_TYPE?lowerize(str2).indexOf(lowerize(str1))!==-1:false},lowerize=function(str){return str.toLowerCase()},majorize=function(version){return typeof version===STR_TYPE?version.replace(/[^\d\.]/g,EMPTY).split(".")[0]:undefined$1},trim=function(str,len){if(typeof str===STR_TYPE){str=str.replace(/^\s\s*/,EMPTY);return typeof len===UNDEF_TYPE?str:str.substring(0,UA_MAX_LENGTH)}};var rgxMapper=function(ua,arrays){var i=0,j,k,p,q,matches,match;while(i<arrays.length&&!matches){var regex=arrays[i],props=arrays[i+1];j=k=0;while(j<regex.length&&!matches){if(!regex[j]){break}matches=regex[j++].exec(ua);if(!!matches){for(p=0;p<props.length;p++){match=matches[++k];q=props[p];if(typeof q===OBJ_TYPE&&q.length>0){if(q.length===2){if(typeof q[1]==FUNC_TYPE){this[q[0]]=q[1].call(this,match);}else {this[q[0]]=q[1];}}else if(q.length===3){if(typeof q[1]===FUNC_TYPE&&!(q[1].exec&&q[1].test)){this[q[0]]=match?q[1].call(this,match,q[2]):undefined$1;}else {this[q[0]]=match?match.replace(q[1],q[2]):undefined$1;}}else if(q.length===4){this[q[0]]=match?q[3].call(this,match.replace(q[1],q[2])):undefined$1;}}else {this[q]=match?match:undefined$1;}}}}i+=2;}},strMapper=function(str,map){for(var i in map){if(typeof map[i]===OBJ_TYPE&&map[i].length>0){for(var j=0;j<map[i].length;j++){if(has(map[i][j],str)){return i===UNKNOWN?undefined$1:i}}}else if(has(map[i],str)){return i===UNKNOWN?undefined$1:i}}return str};var oldSafariMap={"1.0":"/8",1.2:"/1",1.3:"/3","2.0":"/412","2.0.2":"/416","2.0.3":"/417","2.0.4":"/419","?":"/"},windowsVersionMap={ME:"4.90","NT 3.11":"NT3.51","NT 4.0":"NT4.0",2e3:"NT 5.0",XP:["NT 5.1","NT 5.2"],Vista:"NT 6.0",7:"NT 6.1",8:"NT 6.2",8.1:"NT 6.3",10:["NT 6.4","NT 10.0"],RT:"ARM"};var regexes={browser:[[/\b(?:crmo|crios)\/([\w\.]+)/i],[VERSION,[NAME,"Chrome"]],[/edg(?:e|ios|a)?\/([\w\.]+)/i],[VERSION,[NAME,"Edge"]],[/(opera mini)\/([-\w\.]+)/i,/(opera [mobiletab]{3,6})\b.+version\/([-\w\.]+)/i,/(opera)(?:.+version\/|[\/ ]+)([\w\.]+)/i],[NAME,VERSION],[/opios[\/ ]+([\w\.]+)/i],[VERSION,[NAME,OPERA+" Mini"]],[/\bopr\/([\w\.]+)/i],[VERSION,[NAME,OPERA]],[/(kindle)\/([\w\.]+)/i,/(lunascape|maxthon|netfront|jasmine|blazer)[\/ ]?([\w\.]*)/i,/(avant |iemobile|slim)(?:browser)?[\/ ]?([\w\.]*)/i,/(ba?idubrowser)[\/ ]?([\w\.]+)/i,/(?:ms|\()(ie) ([\w\.]+)/i,/(flock|rockmelt|midori|epiphany|silk|skyfire|bolt|iron|vivaldi|iridium|phantomjs|bowser|quark|qupzilla|falkon|rekonq|puffin|brave|whale(?!.+naver)|qqbrowserlite|qq|duckduckgo)\/([-\w\.]+)/i,/(heytap|ovi)browser\/([\d\.]+)/i,/(weibo)__([\d\.]+)/i],[NAME,VERSION],[/(?:\buc? ?browser|(?:juc.+)ucweb)[\/ ]?([\w\.]+)/i],[VERSION,[NAME,"UC"+BROWSER]],[/microm.+\bqbcore\/([\w\.]+)/i,/\bqbcore\/([\w\.]+).+microm/i],[VERSION,[NAME,"WeChat(Win) Desktop"]],[/micromessenger\/([\w\.]+)/i],[VERSION,[NAME,"WeChat"]],[/konqueror\/([\w\.]+)/i],[VERSION,[NAME,"Konqueror"]],[/trident.+rv[: ]([\w\.]{1,9})\b.+like gecko/i],[VERSION,[NAME,"IE"]],[/ya(?:search)?browser\/([\w\.]+)/i],[VERSION,[NAME,"Yandex"]],[/(avast|avg)\/([\w\.]+)/i],[[NAME,/(.+)/,"$1 Secure "+BROWSER],VERSION],[/\bfocus\/([\w\.]+)/i],[VERSION,[NAME,FIREFOX+" Focus"]],[/\bopt\/([\w\.]+)/i],[VERSION,[NAME,OPERA+" Touch"]],[/coc_coc\w+\/([\w\.]+)/i],[VERSION,[NAME,"Coc Coc"]],[/dolfin\/([\w\.]+)/i],[VERSION,[NAME,"Dolphin"]],[/coast\/([\w\.]+)/i],[VERSION,[NAME,OPERA+" Coast"]],[/miuibrowser\/([\w\.]+)/i],[VERSION,[NAME,"MIUI "+BROWSER]],[/fxios\/([-\w\.]+)/i],[VERSION,[NAME,FIREFOX]],[/\bqihu|(qi?ho?o?|360)browser/i],[[NAME,"360 "+BROWSER]],[/(oculus|samsung|sailfish|huawei)browser\/([\w\.]+)/i],[[NAME,/(.+)/,"$1 "+BROWSER],VERSION],[/(comodo_dragon)\/([\w\.]+)/i],[[NAME,/_/g," "],VERSION],[/(electron)\/([\w\.]+) safari/i,/(tesla)(?: qtcarbrowser|\/(20\d\d\.[-\w\.]+))/i,/m?(qqbrowser|baiduboxapp|2345Explorer)[\/ ]?([\w\.]+)/i],[NAME,VERSION],[/(metasr)[\/ ]?([\w\.]+)/i,/(lbbrowser)/i,/\[(linkedin)app\]/i],[NAME],[/((?:fban\/fbios|fb_iab\/fb4a)(?!.+fbav)|;fbav\/([\w\.]+);)/i],[[NAME,FACEBOOK],VERSION],[/(kakao(?:talk|story))[\/ ]([\w\.]+)/i,/(naver)\(.*?(\d+\.[\w\.]+).*\)/i,/safari (line)\/([\w\.]+)/i,/\b(line)\/([\w\.]+)\/iab/i,/(chromium|instagram)[\/ ]([-\w\.]+)/i],[NAME,VERSION],[/\bgsa\/([\w\.]+) .*safari\//i],[VERSION,[NAME,"GSA"]],[/musical_ly(?:.+app_?version\/|_)([\w\.]+)/i],[VERSION,[NAME,"TikTok"]],[/headlesschrome(?:\/([\w\.]+)| )/i],[VERSION,[NAME,CHROME+" Headless"]],[/ wv\).+(chrome)\/([\w\.]+)/i],[[NAME,CHROME+" WebView"],VERSION],[/droid.+ version\/([\w\.]+)\b.+(?:mobile safari|safari)/i],[VERSION,[NAME,"Android "+BROWSER]],[/(chrome|omniweb|arora|[tizenoka]{5} ?browser)\/v?([\w\.]+)/i],[NAME,VERSION],[/version\/([\w\.\,]+) .*mobile\/\w+ (safari)/i],[VERSION,[NAME,"Mobile Safari"]],[/version\/([\w(\.|\,)]+) .*(mobile ?safari|safari)/i],[VERSION,NAME],[/webkit.+?(mobile ?safari|safari)(\/[\w\.]+)/i],[NAME,[VERSION,strMapper,oldSafariMap]],[/(webkit|khtml)\/([\w\.]+)/i],[NAME,VERSION],[/(navigator|netscape\d?)\/([-\w\.]+)/i],[[NAME,"Netscape"],VERSION],[/mobile vr; rv:([\w\.]+)\).+firefox/i],[VERSION,[NAME,FIREFOX+" Reality"]],[/ekiohf.+(flow)\/([\w\.]+)/i,/(swiftfox)/i,/(icedragon|iceweasel|camino|chimera|fennec|maemo browser|minimo|conkeror|klar)[\/ ]?([\w\.\+]+)/i,/(seamonkey|k-meleon|icecat|iceape|firebird|phoenix|palemoon|basilisk|waterfox)\/([-\w\.]+)$/i,/(firefox)\/([\w\.]+)/i,/(mozilla)\/([\w\.]+) .+rv\:.+gecko\/\d+/i,/(polaris|lynx|dillo|icab|doris|amaya|w3m|netsurf|sleipnir|obigo|mosaic|(?:go|ice|up)[\. ]?browser)[-\/ ]?v?([\w\.]+)/i,/(links) \(([\w\.]+)/i,/panasonic;(viera)/i],[NAME,VERSION],[/(cobalt)\/([\w\.]+)/i],[NAME,[VERSION,/master.|lts./,""]]],cpu:[[/(?:(amd|x(?:(?:86|64)[-_])?|wow|win)64)[;\)]/i],[[ARCHITECTURE,"amd64"]],[/(ia32(?=;))/i],[[ARCHITECTURE,lowerize]],[/((?:i[346]|x)86)[;\)]/i],[[ARCHITECTURE,"ia32"]],[/\b(aarch64|arm(v?8e?l?|_?64))\b/i],[[ARCHITECTURE,"arm64"]],[/\b(arm(?:v[67])?ht?n?[fl]p?)\b/i],[[ARCHITECTURE,"armhf"]],[/windows (ce|mobile); ppc;/i],[[ARCHITECTURE,"arm"]],[/((?:ppc|powerpc)(?:64)?)(?: mac|;|\))/i],[[ARCHITECTURE,/ower/,EMPTY,lowerize]],[/(sun4\w)[;\)]/i],[[ARCHITECTURE,"sparc"]],[/((?:avr32|ia64(?=;))|68k(?=\))|\barm(?=v(?:[1-7]|[5-7]1)l?|;|eabi)|(?=atmel )avr|(?:irix|mips|sparc)(?:64)?\b|pa-risc)/i],[[ARCHITECTURE,lowerize]]],device:[[/\b(sch-i[89]0\d|shw-m380s|sm-[ptx]\w{2,4}|gt-[pn]\d{2,4}|sgh-t8[56]9|nexus 10)/i],[MODEL,[VENDOR,SAMSUNG],[TYPE,TABLET]],[/\b((?:s[cgp]h|gt|sm)-\w+|sc[g-]?[\d]+a?|galaxy nexus)/i,/samsung[- ]([-\w]+)/i,/sec-(sgh\w+)/i],[MODEL,[VENDOR,SAMSUNG],[TYPE,MOBILE]],[/(?:\/|\()(ip(?:hone|od)[\w, ]*)(?:\/|;)/i],[MODEL,[VENDOR,APPLE],[TYPE,MOBILE]],[/\((ipad);[-\w\),; ]+apple/i,/applecoremedia\/[\w\.]+ \((ipad)/i,/\b(ipad)\d\d?,\d\d?[;\]].+ios/i],[MODEL,[VENDOR,APPLE],[TYPE,TABLET]],[/(macintosh);/i],[MODEL,[VENDOR,APPLE]],[/\b(sh-?[altvz]?\d\d[a-ekm]?)/i],[MODEL,[VENDOR,SHARP],[TYPE,MOBILE]],[/\b((?:ag[rs][23]?|bah2?|sht?|btv)-a?[lw]\d{2})\b(?!.+d\/s)/i],[MODEL,[VENDOR,HUAWEI],[TYPE,TABLET]],[/(?:huawei|honor)([-\w ]+)[;\)]/i,/\b(nexus 6p|\w{2,4}e?-[atu]?[ln][\dx][012359c][adn]?)\b(?!.+d\/s)/i],[MODEL,[VENDOR,HUAWEI],[TYPE,MOBILE]],[/\b(poco[\w ]+)(?: bui|\))/i,/\b; (\w+) build\/hm\1/i,/\b(hm[-_ ]?note?[_ ]?(?:\d\w)?) bui/i,/\b(redmi[\-_ ]?(?:note|k)?[\w_ ]+)(?: bui|\))/i,/\b(mi[-_ ]?(?:a\d|one|one[_ ]plus|note lte|max|cc)?[_ ]?(?:\d?\w?)[_ ]?(?:plus|se|lite)?)(?: bui|\))/i],[[MODEL,/_/g," "],[VENDOR,XIAOMI],[TYPE,MOBILE]],[/\b(mi[-_ ]?(?:pad)(?:[\w_ ]+))(?: bui|\))/i],[[MODEL,/_/g," "],[VENDOR,XIAOMI],[TYPE,TABLET]],[/; (\w+) bui.+ oppo/i,/\b(cph[12]\d{3}|p(?:af|c[al]|d\w|e[ar])[mt]\d0|x9007|a101op)\b/i],[MODEL,[VENDOR,"OPPO"],[TYPE,MOBILE]],[/vivo (\w+)(?: bui|\))/i,/\b(v[12]\d{3}\w?[at])(?: bui|;)/i],[MODEL,[VENDOR,"Vivo"],[TYPE,MOBILE]],[/\b(rmx[12]\d{3})(?: bui|;|\))/i],[MODEL,[VENDOR,"Realme"],[TYPE,MOBILE]],[/\b(milestone|droid(?:[2-4x]| (?:bionic|x2|pro|razr))?:?( 4g)?)\b[\w ]+build\//i,/\bmot(?:orola)?[- ](\w*)/i,/((?:moto[\w\(\) ]+|xt\d{3,4}|nexus 6)(?= bui|\)))/i],[MODEL,[VENDOR,MOTOROLA],[TYPE,MOBILE]],[/\b(mz60\d|xoom[2 ]{0,2}) build\//i],[MODEL,[VENDOR,MOTOROLA],[TYPE,TABLET]],[/((?=lg)?[vl]k\-?\d{3}) bui| 3\.[-\w; ]{10}lg?-([06cv9]{3,4})/i],[MODEL,[VENDOR,LG],[TYPE,TABLET]],[/(lm(?:-?f100[nv]?|-[\w\.]+)(?= bui|\))|nexus [45])/i,/\blg[-e;\/ ]+((?!browser|netcast|android tv)\w+)/i,/\blg-?([\d\w]+) bui/i],[MODEL,[VENDOR,LG],[TYPE,MOBILE]],[/(ideatab[-\w ]+)/i,/lenovo ?(s[56]000[-\w]+|tab(?:[\w ]+)|yt[-\d\w]{6}|tb[-\d\w]{6})/i],[MODEL,[VENDOR,"Lenovo"],[TYPE,TABLET]],[/(?:maemo|nokia).*(n900|lumia \d+)/i,/nokia[-_ ]?([-\w\.]*)/i],[[MODEL,/_/g," "],[VENDOR,"Nokia"],[TYPE,MOBILE]],[/(pixel c)\b/i],[MODEL,[VENDOR,GOOGLE],[TYPE,TABLET]],[/droid.+; (pixel[\daxl ]{0,6})(?: bui|\))/i],[MODEL,[VENDOR,GOOGLE],[TYPE,MOBILE]],[/droid.+ (a?\d[0-2]{2}so|[c-g]\d{4}|so[-gl]\w+|xq-a\w[4-7][12])(?= bui|\).+chrome\/(?![1-6]{0,1}\d\.))/i],[MODEL,[VENDOR,SONY],[TYPE,MOBILE]],[/sony tablet [ps]/i,/\b(?:sony)?sgp\w+(?: bui|\))/i],[[MODEL,"Xperia Tablet"],[VENDOR,SONY],[TYPE,TABLET]],[/ (kb2005|in20[12]5|be20[12][59])\b/i,/(?:one)?(?:plus)? (a\d0\d\d)(?: b|\))/i],[MODEL,[VENDOR,"OnePlus"],[TYPE,MOBILE]],[/(alexa)webm/i,/(kf[a-z]{2}wi|aeo[c-r]{2})( bui|\))/i,/(kf[a-z]+)( bui|\)).+silk\//i],[MODEL,[VENDOR,AMAZON],[TYPE,TABLET]],[/((?:sd|kf)[0349hijorstuw]+)( bui|\)).+silk\//i],[[MODEL,/(.+)/g,"Fire Phone $1"],[VENDOR,AMAZON],[TYPE,MOBILE]],[/(playbook);[-\w\),; ]+(rim)/i],[MODEL,VENDOR,[TYPE,TABLET]],[/\b((?:bb[a-f]|st[hv])100-\d)/i,/\(bb10; (\w+)/i],[MODEL,[VENDOR,BLACKBERRY],[TYPE,MOBILE]],[/(?:\b|asus_)(transfo[prime ]{4,10} \w+|eeepc|slider \w+|nexus 7|padfone|p00[cj])/i],[MODEL,[VENDOR,ASUS],[TYPE,TABLET]],[/ (z[bes]6[027][012][km][ls]|zenfone \d\w?)\b/i],[MODEL,[VENDOR,ASUS],[TYPE,MOBILE]],[/(nexus 9)/i],[MODEL,[VENDOR,"HTC"],[TYPE,TABLET]],[/(htc)[-;_ ]{1,2}([\w ]+(?=\)| bui)|\w+)/i,/(zte)[- ]([\w ]+?)(?: bui|\/|\))/i,/(alcatel|geeksphone|nexian|panasonic(?!(?:;|\.))|sony(?!-bra))[-_ ]?([-\w]*)/i],[VENDOR,[MODEL,/_/g," "],[TYPE,MOBILE]],[/droid.+; ([ab][1-7]-?[0178a]\d\d?)/i],[MODEL,[VENDOR,"Acer"],[TYPE,TABLET]],[/droid.+; (m[1-5] note) bui/i,/\bmz-([-\w]{2,})/i],[MODEL,[VENDOR,"Meizu"],[TYPE,MOBILE]],[/(blackberry|benq|palm(?=\-)|sonyericsson|acer|asus|dell|meizu|motorola|polytron)[-_ ]?([-\w]*)/i,/(hp) ([\w ]+\w)/i,/(asus)-?(\w+)/i,/(microsoft); (lumia[\w ]+)/i,/(lenovo)[-_ ]?([-\w]+)/i,/(jolla)/i,/(oppo) ?([\w ]+) bui/i],[VENDOR,MODEL,[TYPE,MOBILE]],[/(kobo)\s(ereader|touch)/i,/(archos) (gamepad2?)/i,/(hp).+(touchpad(?!.+tablet)|tablet)/i,/(kindle)\/([\w\.]+)/i,/(nook)[\w ]+build\/(\w+)/i,/(dell) (strea[kpr\d ]*[\dko])/i,/(le[- ]+pan)[- ]+(\w{1,9}) bui/i,/(trinity)[- ]*(t\d{3}) bui/i,/(gigaset)[- ]+(q\w{1,9}) bui/i,/(vodafone) ([\w ]+)(?:\)| bui)/i],[VENDOR,MODEL,[TYPE,TABLET]],[/(surface duo)/i],[MODEL,[VENDOR,MICROSOFT],[TYPE,TABLET]],[/droid [\d\.]+; (fp\du?)(?: b|\))/i],[MODEL,[VENDOR,"Fairphone"],[TYPE,MOBILE]],[/(u304aa)/i],[MODEL,[VENDOR,"AT&T"],[TYPE,MOBILE]],[/\bsie-(\w*)/i],[MODEL,[VENDOR,"Siemens"],[TYPE,MOBILE]],[/\b(rct\w+) b/i],[MODEL,[VENDOR,"RCA"],[TYPE,TABLET]],[/\b(venue[\d ]{2,7}) b/i],[MODEL,[VENDOR,"Dell"],[TYPE,TABLET]],[/\b(q(?:mv|ta)\w+) b/i],[MODEL,[VENDOR,"Verizon"],[TYPE,TABLET]],[/\b(?:barnes[& ]+noble |bn[rt])([\w\+ ]*) b/i],[MODEL,[VENDOR,"Barnes & Noble"],[TYPE,TABLET]],[/\b(tm\d{3}\w+) b/i],[MODEL,[VENDOR,"NuVision"],[TYPE,TABLET]],[/\b(k88) b/i],[MODEL,[VENDOR,"ZTE"],[TYPE,TABLET]],[/\b(nx\d{3}j) b/i],[MODEL,[VENDOR,"ZTE"],[TYPE,MOBILE]],[/\b(gen\d{3}) b.+49h/i],[MODEL,[VENDOR,"Swiss"],[TYPE,MOBILE]],[/\b(zur\d{3}) b/i],[MODEL,[VENDOR,"Swiss"],[TYPE,TABLET]],[/\b((zeki)?tb.*\b) b/i],[MODEL,[VENDOR,"Zeki"],[TYPE,TABLET]],[/\b([yr]\d{2}) b/i,/\b(dragon[- ]+touch |dt)(\w{5}) b/i],[[VENDOR,"Dragon Touch"],MODEL,[TYPE,TABLET]],[/\b(ns-?\w{0,9}) b/i],[MODEL,[VENDOR,"Insignia"],[TYPE,TABLET]],[/\b((nxa|next)-?\w{0,9}) b/i],[MODEL,[VENDOR,"NextBook"],[TYPE,TABLET]],[/\b(xtreme\_)?(v(1[045]|2[015]|[3469]0|7[05])) b/i],[[VENDOR,"Voice"],MODEL,[TYPE,MOBILE]],[/\b(lvtel\-)?(v1[12]) b/i],[[VENDOR,"LvTel"],MODEL,[TYPE,MOBILE]],[/\b(ph-1) /i],[MODEL,[VENDOR,"Essential"],[TYPE,MOBILE]],[/\b(v(100md|700na|7011|917g).*\b) b/i],[MODEL,[VENDOR,"Envizen"],[TYPE,TABLET]],[/\b(trio[-\w\. ]+) b/i],[MODEL,[VENDOR,"MachSpeed"],[TYPE,TABLET]],[/\btu_(1491) b/i],[MODEL,[VENDOR,"Rotor"],[TYPE,TABLET]],[/(shield[\w ]+) b/i],[MODEL,[VENDOR,"Nvidia"],[TYPE,TABLET]],[/(sprint) (\w+)/i],[VENDOR,MODEL,[TYPE,MOBILE]],[/(kin\.[onetw]{3})/i],[[MODEL,/\./g," "],[VENDOR,MICROSOFT],[TYPE,MOBILE]],[/droid.+; (cc6666?|et5[16]|mc[239][23]x?|vc8[03]x?)\)/i],[MODEL,[VENDOR,ZEBRA],[TYPE,TABLET]],[/droid.+; (ec30|ps20|tc[2-8]\d[kx])\)/i],[MODEL,[VENDOR,ZEBRA],[TYPE,MOBILE]],[/smart-tv.+(samsung)/i],[VENDOR,[TYPE,SMARTTV]],[/hbbtv.+maple;(\d+)/i],[[MODEL,/^/,"SmartTV"],[VENDOR,SAMSUNG],[TYPE,SMARTTV]],[/(nux; netcast.+smarttv|lg (netcast\.tv-201\d|android tv))/i],[[VENDOR,LG],[TYPE,SMARTTV]],[/(apple) ?tv/i],[VENDOR,[MODEL,APPLE+" TV"],[TYPE,SMARTTV]],[/crkey/i],[[MODEL,CHROME+"cast"],[VENDOR,GOOGLE],[TYPE,SMARTTV]],[/droid.+aft(\w)( bui|\))/i],[MODEL,[VENDOR,AMAZON],[TYPE,SMARTTV]],[/\(dtv[\);].+(aquos)/i,/(aquos-tv[\w ]+)\)/i],[MODEL,[VENDOR,SHARP],[TYPE,SMARTTV]],[/(bravia[\w ]+)( bui|\))/i],[MODEL,[VENDOR,SONY],[TYPE,SMARTTV]],[/(mitv-\w{5}) bui/i],[MODEL,[VENDOR,XIAOMI],[TYPE,SMARTTV]],[/Hbbtv.*(technisat) (.*);/i],[VENDOR,MODEL,[TYPE,SMARTTV]],[/\b(roku)[\dx]*[\)\/]((?:dvp-)?[\d\.]*)/i,/hbbtv\/\d+\.\d+\.\d+ +\([\w\+ ]*; *([\w\d][^;]*);([^;]*)/i],[[VENDOR,trim],[MODEL,trim],[TYPE,SMARTTV]],[/\b(android tv|smart[- ]?tv|opera tv|tv; rv:)\b/i],[[TYPE,SMARTTV]],[/(ouya)/i,/(nintendo) ([wids3utch]+)/i],[VENDOR,MODEL,[TYPE,CONSOLE]],[/droid.+; (shield) bui/i],[MODEL,[VENDOR,"Nvidia"],[TYPE,CONSOLE]],[/(playstation [345portablevi]+)/i],[MODEL,[VENDOR,SONY],[TYPE,CONSOLE]],[/\b(xbox(?: one)?(?!; xbox))[\); ]/i],[MODEL,[VENDOR,MICROSOFT],[TYPE,CONSOLE]],[/((pebble))app/i],[VENDOR,MODEL,[TYPE,WEARABLE]],[/(watch)(?: ?os[,\/]|\d,\d\/)[\d\.]+/i],[MODEL,[VENDOR,APPLE],[TYPE,WEARABLE]],[/droid.+; (glass) \d/i],[MODEL,[VENDOR,GOOGLE],[TYPE,WEARABLE]],[/droid.+; (wt63?0{2,3})\)/i],[MODEL,[VENDOR,ZEBRA],[TYPE,WEARABLE]],[/(quest( 2| pro)?)/i],[MODEL,[VENDOR,FACEBOOK],[TYPE,WEARABLE]],[/(tesla)(?: qtcarbrowser|\/[-\w\.]+)/i],[VENDOR,[TYPE,EMBEDDED]],[/(aeobc)\b/i],[MODEL,[VENDOR,AMAZON],[TYPE,EMBEDDED]],[/droid .+?; ([^;]+?)(?: bui|\) applew).+? mobile safari/i],[MODEL,[TYPE,MOBILE]],[/droid .+?; ([^;]+?)(?: bui|\) applew).+?(?! mobile) safari/i],[MODEL,[TYPE,TABLET]],[/\b((tablet|tab)[;\/]|focus\/\d(?!.+mobile))/i],[[TYPE,TABLET]],[/(phone|mobile(?:[;\/]| [ \w\/\.]*safari)|pda(?=.+windows ce))/i],[[TYPE,MOBILE]],[/(android[-\w\. ]{0,9});.+buil/i],[MODEL,[VENDOR,"Generic"]]],engine:[[/windows.+ edge\/([\w\.]+)/i],[VERSION,[NAME,EDGE+"HTML"]],[/webkit\/537\.36.+chrome\/(?!27)([\w\.]+)/i],[VERSION,[NAME,"Blink"]],[/(presto)\/([\w\.]+)/i,/(webkit|trident|netfront|netsurf|amaya|lynx|w3m|goanna)\/([\w\.]+)/i,/ekioh(flow)\/([\w\.]+)/i,/(khtml|tasman|links)[\/ ]\(?([\w\.]+)/i,/(icab)[\/ ]([23]\.[\d\.]+)/i,/\b(libweb)/i],[NAME,VERSION],[/rv\:([\w\.]{1,9})\b.+(gecko)/i],[VERSION,NAME]],os:[[/microsoft (windows) (vista|xp)/i],[NAME,VERSION],[/(windows) nt 6\.2; (arm)/i,/(windows (?:phone(?: os)?|mobile))[\/ ]?([\d\.\w ]*)/i,/(windows)[\/ ]?([ntce\d\. ]+\w)(?!.+xbox)/i],[NAME,[VERSION,strMapper,windowsVersionMap]],[/(win(?=3|9|n)|win 9x )([nt\d\.]+)/i],[[NAME,"Windows"],[VERSION,strMapper,windowsVersionMap]],[/ip[honead]{2,4}\b(?:.*os ([\w]+) like mac|; opera)/i,/ios;fbsv\/([\d\.]+)/i,/cfnetwork\/.+darwin/i],[[VERSION,/_/g,"."],[NAME,"iOS"]],[/(mac os x) ?([\w\. ]*)/i,/(macintosh|mac_powerpc\b)(?!.+haiku)/i],[[NAME,MAC_OS],[VERSION,/_/g,"."]],[/droid ([\w\.]+)\b.+(android[- ]x86|harmonyos)/i],[VERSION,NAME],[/(android|webos|qnx|bada|rim tablet os|maemo|meego|sailfish)[-\/ ]?([\w\.]*)/i,/(blackberry)\w*\/([\w\.]*)/i,/(tizen|kaios)[\/ ]([\w\.]+)/i,/\((series40);/i],[NAME,VERSION],[/\(bb(10);/i],[VERSION,[NAME,BLACKBERRY]],[/(?:symbian ?os|symbos|s60(?=;)|series60)[-\/ ]?([\w\.]*)/i],[VERSION,[NAME,"Symbian"]],[/mozilla\/[\d\.]+ \((?:mobile|tablet|tv|mobile; [\w ]+); rv:.+ gecko\/([\w\.]+)/i],[VERSION,[NAME,FIREFOX+" OS"]],[/web0s;.+rt(tv)/i,/\b(?:hp)?wos(?:browser)?\/([\w\.]+)/i],[VERSION,[NAME,"webOS"]],[/watch(?: ?os[,\/]|\d,\d\/)([\d\.]+)/i],[VERSION,[NAME,"watchOS"]],[/crkey\/([\d\.]+)/i],[VERSION,[NAME,CHROME+"cast"]],[/(cros) [\w]+(?:\)| ([\w\.]+)\b)/i],[[NAME,CHROMIUM_OS],VERSION],[/panasonic;(viera)/i,/(netrange)mmh/i,/(nettv)\/(\d+\.[\w\.]+)/i,/(nintendo|playstation) ([wids345portablevuch]+)/i,/(xbox); +xbox ([^\);]+)/i,/\b(joli|palm)\b ?(?:os)?\/?([\w\.]*)/i,/(mint)[\/\(\) ]?(\w*)/i,/(mageia|vectorlinux)[; ]/i,/([kxln]?ubuntu|debian|suse|opensuse|gentoo|arch(?= linux)|slackware|fedora|mandriva|centos|pclinuxos|red ?hat|zenwalk|linpus|raspbian|plan 9|minix|risc os|contiki|deepin|manjaro|elementary os|sabayon|linspire)(?: gnu\/linux)?(?: enterprise)?(?:[- ]linux)?(?:-gnu)?[-\/ ]?(?!chrom|package)([-\w\.]*)/i,/(hurd|linux) ?([\w\.]*)/i,/(gnu) ?([\w\.]*)/i,/\b([-frentopcghs]{0,5}bsd|dragonfly)[\/ ]?(?!amd|[ix346]{1,2}86)([\w\.]*)/i,/(haiku) (\w+)/i],[NAME,VERSION],[/(sunos) ?([\w\.\d]*)/i],[[NAME,"Solaris"],VERSION],[/((?:open)?solaris)[-\/ ]?([\w\.]*)/i,/(aix) ((\d)(?=\.|\)| )[\w\.])*/i,/\b(beos|os\/2|amigaos|morphos|openvms|fuchsia|hp-ux|serenityos)/i,/(unix) ?([\w\.]*)/i],[NAME,VERSION]]};var UAParser=function(ua,extensions){if(typeof ua===OBJ_TYPE){extensions=ua;ua=undefined$1;}if(!(this instanceof UAParser)){return new UAParser(ua,extensions).getResult()}var _navigator=typeof window!==UNDEF_TYPE&&window.navigator?window.navigator:undefined$1;var _ua=ua||(_navigator&&_navigator.userAgent?_navigator.userAgent:EMPTY);var _uach=_navigator&&_navigator.userAgentData?_navigator.userAgentData:undefined$1;var _rgxmap=extensions?extend(regexes,extensions):regexes;var _isSelfNav=_navigator&&_navigator.userAgent==_ua;this.getBrowser=function(){var _browser={};_browser[NAME]=undefined$1;_browser[VERSION]=undefined$1;rgxMapper.call(_browser,_ua,_rgxmap.browser);_browser[MAJOR]=majorize(_browser[VERSION]);if(_isSelfNav&&_navigator&&_navigator.brave&&typeof _navigator.brave.isBrave==FUNC_TYPE){_browser[NAME]="Brave";}return _browser};this.getCPU=function(){var _cpu={};_cpu[ARCHITECTURE]=undefined$1;rgxMapper.call(_cpu,_ua,_rgxmap.cpu);return _cpu};this.getDevice=function(){var _device={};_device[VENDOR]=undefined$1;_device[MODEL]=undefined$1;_device[TYPE]=undefined$1;rgxMapper.call(_device,_ua,_rgxmap.device);if(_isSelfNav&&!_device[TYPE]&&_uach&&_uach.mobile){_device[TYPE]=MOBILE;}if(_isSelfNav&&_device[MODEL]=="Macintosh"&&_navigator&&typeof _navigator.standalone!==UNDEF_TYPE&&_navigator.maxTouchPoints&&_navigator.maxTouchPoints>2){_device[MODEL]="iPad";_device[TYPE]=TABLET;}return _device};this.getEngine=function(){var _engine={};_engine[NAME]=undefined$1;_engine[VERSION]=undefined$1;rgxMapper.call(_engine,_ua,_rgxmap.engine);return _engine};this.getOS=function(){var _os={};_os[NAME]=undefined$1;_os[VERSION]=undefined$1;rgxMapper.call(_os,_ua,_rgxmap.os);if(_isSelfNav&&!_os[NAME]&&_uach&&_uach.platform!="Unknown"){_os[NAME]=_uach.platform.replace(/chrome os/i,CHROMIUM_OS).replace(/macos/i,MAC_OS);}return _os};this.getResult=function(){return {ua:this.getUA(),browser:this.getBrowser(),engine:this.getEngine(),os:this.getOS(),device:this.getDevice(),cpu:this.getCPU()}};this.getUA=function(){return _ua};this.setUA=function(ua){_ua=typeof ua===STR_TYPE&&ua.length>UA_MAX_LENGTH?trim(ua,UA_MAX_LENGTH):ua;return this};this.setUA(_ua);return this};UAParser.VERSION=LIBVERSION;UAParser.BROWSER=enumerize([NAME,VERSION,MAJOR]);UAParser.CPU=enumerize([ARCHITECTURE]);UAParser.DEVICE=enumerize([MODEL,VENDOR,TYPE,CONSOLE,MOBILE,SMARTTV,TABLET,WEARABLE,EMBEDDED]);UAParser.ENGINE=UAParser.OS=enumerize([NAME,VERSION]);{if(module.exports){exports=module.exports=UAParser;}exports.UAParser=UAParser;}var $=typeof window!==UNDEF_TYPE&&(window.jQuery||window.Zepto);if($&&!$.ua){var parser=new UAParser;$.ua=parser.getResult();$.ua.get=function(){return parser.getUA()};$.ua.set=function(ua){parser.setUA(ua);var result=parser.getResult();for(var prop in result){$.ua[prop]=result[prop];}};}})(typeof window==="object"?window:commonjsGlobal);
} (uaParser_min, uaParser_min.exports));

Object.defineProperty(lib, '__esModule', { value: true });

function _interopDefault (ex) { return (ex && (typeof ex === 'object') && 'default' in ex) ? ex['default'] : ex; }

var React = react.exports;
var React__default = _interopDefault(React);

var UAParser = uaParser_min.exports;

var ClientUAInstance = new UAParser();
var browser = ClientUAInstance.getBrowser();
var cpu = ClientUAInstance.getCPU();
var device = ClientUAInstance.getDevice();
var engine = ClientUAInstance.getEngine();
var os = ClientUAInstance.getOS();
var ua = ClientUAInstance.getUA();
var setUa = function setUa(userAgentString) {
  return ClientUAInstance.setUA(userAgentString);
};
var parseUserAgent = function parseUserAgent(userAgent) {
  if (!userAgent) {
    console.error('No userAgent string was provided');
    return;
  }

  var UserAgentInstance = new UAParser(userAgent);
  return {
    UA: UserAgentInstance,
    browser: UserAgentInstance.getBrowser(),
    cpu: UserAgentInstance.getCPU(),
    device: UserAgentInstance.getDevice(),
    engine: UserAgentInstance.getEngine(),
    os: UserAgentInstance.getOS(),
    ua: UserAgentInstance.getUA(),
    setUserAgent: function setUserAgent(userAgentString) {
      return UserAgentInstance.setUA(userAgentString);
    }
  };
};

var UAHelper = /*#__PURE__*/Object.freeze({
  ClientUAInstance: ClientUAInstance,
  browser: browser,
  cpu: cpu,
  device: device,
  engine: engine,
  os: os,
  ua: ua,
  setUa: setUa,
  parseUserAgent: parseUserAgent
});

function ownKeys(object, enumerableOnly) {
  var keys = Object.keys(object);

  if (Object.getOwnPropertySymbols) {
    var symbols = Object.getOwnPropertySymbols(object);

    if (enumerableOnly) {
      symbols = symbols.filter(function (sym) {
        return Object.getOwnPropertyDescriptor(object, sym).enumerable;
      });
    }

    keys.push.apply(keys, symbols);
  }

  return keys;
}

function _objectSpread2(target) {
  for (var i = 1; i < arguments.length; i++) {
    var source = arguments[i] != null ? arguments[i] : {};

    if (i % 2) {
      ownKeys(Object(source), true).forEach(function (key) {
        _defineProperty(target, key, source[key]);
      });
    } else if (Object.getOwnPropertyDescriptors) {
      Object.defineProperties(target, Object.getOwnPropertyDescriptors(source));
    } else {
      ownKeys(Object(source)).forEach(function (key) {
        Object.defineProperty(target, key, Object.getOwnPropertyDescriptor(source, key));
      });
    }
  }

  return target;
}

function _typeof(obj) {
  "@babel/helpers - typeof";

  if (typeof Symbol === "function" && typeof Symbol.iterator === "symbol") {
    _typeof = function (obj) {
      return typeof obj;
    };
  } else {
    _typeof = function (obj) {
      return obj && typeof Symbol === "function" && obj.constructor === Symbol && obj !== Symbol.prototype ? "symbol" : typeof obj;
    };
  }

  return _typeof(obj);
}

function _classCallCheck(instance, Constructor) {
  if (!(instance instanceof Constructor)) {
    throw new TypeError("Cannot call a class as a function");
  }
}

function _defineProperties(target, props) {
  for (var i = 0; i < props.length; i++) {
    var descriptor = props[i];
    descriptor.enumerable = descriptor.enumerable || false;
    descriptor.configurable = true;
    if ("value" in descriptor) descriptor.writable = true;
    Object.defineProperty(target, descriptor.key, descriptor);
  }
}

function _createClass(Constructor, protoProps, staticProps) {
  if (protoProps) _defineProperties(Constructor.prototype, protoProps);
  if (staticProps) _defineProperties(Constructor, staticProps);
  return Constructor;
}

function _defineProperty(obj, key, value) {
  if (key in obj) {
    Object.defineProperty(obj, key, {
      value: value,
      enumerable: true,
      configurable: true,
      writable: true
    });
  } else {
    obj[key] = value;
  }

  return obj;
}

function _extends() {
  _extends = Object.assign || function (target) {
    for (var i = 1; i < arguments.length; i++) {
      var source = arguments[i];

      for (var key in source) {
        if (Object.prototype.hasOwnProperty.call(source, key)) {
          target[key] = source[key];
        }
      }
    }

    return target;
  };

  return _extends.apply(this, arguments);
}

function _inherits(subClass, superClass) {
  if (typeof superClass !== "function" && superClass !== null) {
    throw new TypeError("Super expression must either be null or a function");
  }

  subClass.prototype = Object.create(superClass && superClass.prototype, {
    constructor: {
      value: subClass,
      writable: true,
      configurable: true
    }
  });
  if (superClass) _setPrototypeOf(subClass, superClass);
}

function _getPrototypeOf(o) {
  _getPrototypeOf = Object.setPrototypeOf ? Object.getPrototypeOf : function _getPrototypeOf(o) {
    return o.__proto__ || Object.getPrototypeOf(o);
  };
  return _getPrototypeOf(o);
}

function _setPrototypeOf(o, p) {
  _setPrototypeOf = Object.setPrototypeOf || function _setPrototypeOf(o, p) {
    o.__proto__ = p;
    return o;
  };

  return _setPrototypeOf(o, p);
}

function _objectWithoutPropertiesLoose(source, excluded) {
  if (source == null) return {};
  var target = {};
  var sourceKeys = Object.keys(source);
  var key, i;

  for (i = 0; i < sourceKeys.length; i++) {
    key = sourceKeys[i];
    if (excluded.indexOf(key) >= 0) continue;
    target[key] = source[key];
  }

  return target;
}

function _objectWithoutProperties(source, excluded) {
  if (source == null) return {};

  var target = _objectWithoutPropertiesLoose(source, excluded);

  var key, i;

  if (Object.getOwnPropertySymbols) {
    var sourceSymbolKeys = Object.getOwnPropertySymbols(source);

    for (i = 0; i < sourceSymbolKeys.length; i++) {
      key = sourceSymbolKeys[i];
      if (excluded.indexOf(key) >= 0) continue;
      if (!Object.prototype.propertyIsEnumerable.call(source, key)) continue;
      target[key] = source[key];
    }
  }

  return target;
}

function _assertThisInitialized(self) {
  if (self === void 0) {
    throw new ReferenceError("this hasn't been initialised - super() hasn't been called");
  }

  return self;
}

function _possibleConstructorReturn(self, call) {
  if (call && (typeof call === "object" || typeof call === "function")) {
    return call;
  } else if (call !== void 0) {
    throw new TypeError("Derived constructors may only return object or undefined");
  }

  return _assertThisInitialized(self);
}

function _slicedToArray(arr, i) {
  return _arrayWithHoles(arr) || _iterableToArrayLimit(arr, i) || _unsupportedIterableToArray(arr, i) || _nonIterableRest();
}

function _arrayWithHoles(arr) {
  if (Array.isArray(arr)) return arr;
}

function _iterableToArrayLimit(arr, i) {
  var _i = arr == null ? null : typeof Symbol !== "undefined" && arr[Symbol.iterator] || arr["@@iterator"];

  if (_i == null) return;
  var _arr = [];
  var _n = true;
  var _d = false;

  var _s, _e;

  try {
    for (_i = _i.call(arr); !(_n = (_s = _i.next()).done); _n = true) {
      _arr.push(_s.value);

      if (i && _arr.length === i) break;
    }
  } catch (err) {
    _d = true;
    _e = err;
  } finally {
    try {
      if (!_n && _i["return"] != null) _i["return"]();
    } finally {
      if (_d) throw _e;
    }
  }

  return _arr;
}

function _unsupportedIterableToArray(o, minLen) {
  if (!o) return;
  if (typeof o === "string") return _arrayLikeToArray(o, minLen);
  var n = Object.prototype.toString.call(o).slice(8, -1);
  if (n === "Object" && o.constructor) n = o.constructor.name;
  if (n === "Map" || n === "Set") return Array.from(o);
  if (n === "Arguments" || /^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(n)) return _arrayLikeToArray(o, minLen);
}

function _arrayLikeToArray(arr, len) {
  if (len == null || len > arr.length) len = arr.length;

  for (var i = 0, arr2 = new Array(len); i < len; i++) arr2[i] = arr[i];

  return arr2;
}

function _nonIterableRest() {
  throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.");
}

var DeviceTypes = {
  Mobile: 'mobile',
  Tablet: 'tablet',
  SmartTv: 'smarttv',
  Console: 'console',
  Wearable: 'wearable',
  Embedded: 'embedded',
  Browser: undefined
};
var BrowserTypes = {
  Chrome: 'Chrome',
  Firefox: 'Firefox',
  Opera: 'Opera',
  Yandex: 'Yandex',
  Safari: 'Safari',
  InternetExplorer: 'Internet Explorer',
  Edge: 'Edge',
  Chromium: 'Chromium',
  Ie: 'IE',
  MobileSafari: 'Mobile Safari',
  EdgeChromium: 'Edge Chromium',
  MIUI: 'MIUI Browser',
  SamsungBrowser: 'Samsung Browser'
};
var OsTypes = {
  IOS: 'iOS',
  Android: 'Android',
  WindowsPhone: 'Windows Phone',
  Windows: 'Windows',
  MAC_OS: 'Mac OS'
};
var InitialDeviceTypes = {
  isMobile: false,
  isTablet: false,
  isBrowser: false,
  isSmartTV: false,
  isConsole: false,
  isWearable: false
};

var checkDeviceType = function checkDeviceType(type) {
  switch (type) {
    case DeviceTypes.Mobile:
      return {
        isMobile: true
      };

    case DeviceTypes.Tablet:
      return {
        isTablet: true
      };

    case DeviceTypes.SmartTv:
      return {
        isSmartTV: true
      };

    case DeviceTypes.Console:
      return {
        isConsole: true
      };

    case DeviceTypes.Wearable:
      return {
        isWearable: true
      };

    case DeviceTypes.Browser:
      return {
        isBrowser: true
      };

    case DeviceTypes.Embedded:
      return {
        isEmbedded: true
      };

    default:
      return InitialDeviceTypes;
  }
};
var setUserAgent = function setUserAgent(userAgent) {
  return setUa(userAgent);
};
var setDefaults = function setDefaults(p) {
  var d = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : 'none';
  return p ? p : d;
};
var getNavigatorInstance = function getNavigatorInstance() {
  if (typeof window !== 'undefined') {
    if (window.navigator || navigator) {
      return window.navigator || navigator;
    }
  }

  return false;
};
var isIOS13Check = function isIOS13Check(type) {
  var nav = getNavigatorInstance();
  return nav && nav.platform && (nav.platform.indexOf(type) !== -1 || nav.platform === 'MacIntel' && nav.maxTouchPoints > 1 && !window.MSStream);
};

var browserPayload = function browserPayload(isBrowser, browser, engine, os, ua) {
  return {
    isBrowser: isBrowser,
    browserMajorVersion: setDefaults(browser.major),
    browserFullVersion: setDefaults(browser.version),
    browserName: setDefaults(browser.name),
    engineName: setDefaults(engine.name),
    engineVersion: setDefaults(engine.version),
    osName: setDefaults(os.name),
    osVersion: setDefaults(os.version),
    userAgent: setDefaults(ua)
  };
};
var mobilePayload = function mobilePayload(type, device, os, ua) {
  return _objectSpread2({}, type, {
    vendor: setDefaults(device.vendor),
    model: setDefaults(device.model),
    os: setDefaults(os.name),
    osVersion: setDefaults(os.version),
    ua: setDefaults(ua)
  });
};
var smartTvPayload = function smartTvPayload(isSmartTV, engine, os, ua) {
  return {
    isSmartTV: isSmartTV,
    engineName: setDefaults(engine.name),
    engineVersion: setDefaults(engine.version),
    osName: setDefaults(os.name),
    osVersion: setDefaults(os.version),
    userAgent: setDefaults(ua)
  };
};
var consolePayload = function consolePayload(isConsole, engine, os, ua) {
  return {
    isConsole: isConsole,
    engineName: setDefaults(engine.name),
    engineVersion: setDefaults(engine.version),
    osName: setDefaults(os.name),
    osVersion: setDefaults(os.version),
    userAgent: setDefaults(ua)
  };
};
var wearablePayload = function wearablePayload(isWearable, engine, os, ua) {
  return {
    isWearable: isWearable,
    engineName: setDefaults(engine.name),
    engineVersion: setDefaults(engine.version),
    osName: setDefaults(os.name),
    osVersion: setDefaults(os.version),
    userAgent: setDefaults(ua)
  };
};
var embeddedPayload = function embeddedPayload(isEmbedded, device, engine, os, ua) {
  return {
    isEmbedded: isEmbedded,
    vendor: setDefaults(device.vendor),
    model: setDefaults(device.model),
    engineName: setDefaults(engine.name),
    engineVersion: setDefaults(engine.version),
    osName: setDefaults(os.name),
    osVersion: setDefaults(os.version),
    userAgent: setDefaults(ua)
  };
};

function deviceDetect(userAgent) {
  var _ref = userAgent ? parseUserAgent(userAgent) : UAHelper,
      device = _ref.device,
      browser = _ref.browser,
      engine = _ref.engine,
      os = _ref.os,
      ua = _ref.ua;

  var type = checkDeviceType(device.type);
  var isBrowser = type.isBrowser,
      isMobile = type.isMobile,
      isTablet = type.isTablet,
      isSmartTV = type.isSmartTV,
      isConsole = type.isConsole,
      isWearable = type.isWearable,
      isEmbedded = type.isEmbedded;

  if (isBrowser) {
    return browserPayload(isBrowser, browser, engine, os, ua);
  }

  if (isSmartTV) {
    return smartTvPayload(isSmartTV, engine, os, ua);
  }

  if (isConsole) {
    return consolePayload(isConsole, engine, os, ua);
  }

  if (isMobile) {
    return mobilePayload(type, device, os, ua);
  }

  if (isTablet) {
    return mobilePayload(type, device, os, ua);
  }

  if (isWearable) {
    return wearablePayload(isWearable, engine, os, ua);
  }

  if (isEmbedded) {
    return embeddedPayload(isEmbedded, device, engine, os, ua);
  }
}

var isMobileType = function isMobileType(_ref) {
  var type = _ref.type;
  return type === DeviceTypes.Mobile;
};
var isTabletType = function isTabletType(_ref2) {
  var type = _ref2.type;
  return type === DeviceTypes.Tablet;
};
var isMobileAndTabletType = function isMobileAndTabletType(_ref3) {
  var type = _ref3.type;
  return type === DeviceTypes.Mobile || type === DeviceTypes.Tablet;
};
var isSmartTVType = function isSmartTVType(_ref4) {
  var type = _ref4.type;
  return type === DeviceTypes.SmartTv;
};
var isBrowserType = function isBrowserType(_ref5) {
  var type = _ref5.type;
  return type === DeviceTypes.Browser;
};
var isWearableType = function isWearableType(_ref6) {
  var type = _ref6.type;
  return type === DeviceTypes.Wearable;
};
var isConsoleType = function isConsoleType(_ref7) {
  var type = _ref7.type;
  return type === DeviceTypes.Console;
};
var isEmbeddedType = function isEmbeddedType(_ref8) {
  var type = _ref8.type;
  return type === DeviceTypes.Embedded;
};
var getMobileVendor = function getMobileVendor(_ref9) {
  var vendor = _ref9.vendor;
  return setDefaults(vendor);
};
var getMobileModel = function getMobileModel(_ref10) {
  var model = _ref10.model;
  return setDefaults(model);
};
var getDeviceType = function getDeviceType(_ref11) {
  var type = _ref11.type;
  return setDefaults(type, 'browser');
}; // os types

var isAndroidType = function isAndroidType(_ref12) {
  var name = _ref12.name;
  return name === OsTypes.Android;
};
var isWindowsType = function isWindowsType(_ref13) {
  var name = _ref13.name;
  return name === OsTypes.Windows;
};
var isMacOsType = function isMacOsType(_ref14) {
  var name = _ref14.name;
  return name === OsTypes.MAC_OS;
};
var isWinPhoneType = function isWinPhoneType(_ref15) {
  var name = _ref15.name;
  return name === OsTypes.WindowsPhone;
};
var isIOSType = function isIOSType(_ref16) {
  var name = _ref16.name;
  return name === OsTypes.IOS;
};
var getOsVersion = function getOsVersion(_ref17) {
  var version = _ref17.version;
  return setDefaults(version);
};
var getOsName = function getOsName(_ref18) {
  var name = _ref18.name;
  return setDefaults(name);
}; // browser types

var isChromeType = function isChromeType(_ref19) {
  var name = _ref19.name;
  return name === BrowserTypes.Chrome;
};
var isFirefoxType = function isFirefoxType(_ref20) {
  var name = _ref20.name;
  return name === BrowserTypes.Firefox;
};
var isChromiumType = function isChromiumType(_ref21) {
  var name = _ref21.name;
  return name === BrowserTypes.Chromium;
};
var isEdgeType = function isEdgeType(_ref22) {
  var name = _ref22.name;
  return name === BrowserTypes.Edge;
};
var isYandexType = function isYandexType(_ref23) {
  var name = _ref23.name;
  return name === BrowserTypes.Yandex;
};
var isSafariType = function isSafariType(_ref24) {
  var name = _ref24.name;
  return name === BrowserTypes.Safari || name === BrowserTypes.MobileSafari;
};
var isMobileSafariType = function isMobileSafariType(_ref25) {
  var name = _ref25.name;
  return name === BrowserTypes.MobileSafari;
};
var isOperaType = function isOperaType(_ref26) {
  var name = _ref26.name;
  return name === BrowserTypes.Opera;
};
var isIEType = function isIEType(_ref27) {
  var name = _ref27.name;
  return name === BrowserTypes.InternetExplorer || name === BrowserTypes.Ie;
};
var isMIUIType = function isMIUIType(_ref28) {
  var name = _ref28.name;
  return name === BrowserTypes.MIUI;
};
var isSamsungBrowserType = function isSamsungBrowserType(_ref29) {
  var name = _ref29.name;
  return name === BrowserTypes.SamsungBrowser;
};
var getBrowserFullVersion = function getBrowserFullVersion(_ref30) {
  var version = _ref30.version;
  return setDefaults(version);
};
var getBrowserVersion = function getBrowserVersion(_ref31) {
  var major = _ref31.major;
  return setDefaults(major);
};
var getBrowserName = function getBrowserName(_ref32) {
  var name = _ref32.name;
  return setDefaults(name);
}; // engine types

var getEngineName = function getEngineName(_ref33) {
  var name = _ref33.name;
  return setDefaults(name);
};
var getEngineVersion = function getEngineVersion(_ref34) {
  var version = _ref34.version;
  return setDefaults(version);
};
var isElectronType = function isElectronType() {
  var nav = getNavigatorInstance();
  var ua = nav && nav.userAgent && nav.userAgent.toLowerCase();
  return typeof ua === 'string' ? /electron/.test(ua) : false;
};
var isEdgeChromiumType = function isEdgeChromiumType(ua) {
  return typeof ua === 'string' && ua.indexOf('Edg/') !== -1;
};
var getIOS13 = function getIOS13() {
  var nav = getNavigatorInstance();
  return nav && (/iPad|iPhone|iPod/.test(nav.platform) || nav.platform === 'MacIntel' && nav.maxTouchPoints > 1) && !window.MSStream;
};
var getIPad13 = function getIPad13() {
  return isIOS13Check('iPad');
};
var getIphone13 = function getIphone13() {
  return isIOS13Check('iPhone');
};
var getIPod13 = function getIPod13() {
  return isIOS13Check('iPod');
};
var getUseragent = function getUseragent(userAg) {
  return setDefaults(userAg);
};

function buildSelectorsObject(options) {
  var _ref = options ? options : UAHelper,
      device = _ref.device,
      browser = _ref.browser,
      os = _ref.os,
      engine = _ref.engine,
      ua = _ref.ua;

  return {
    isSmartTV: isSmartTVType(device),
    isConsole: isConsoleType(device),
    isWearable: isWearableType(device),
    isEmbedded: isEmbeddedType(device),
    isMobileSafari: isMobileSafariType(browser) || getIPad13(),
    isChromium: isChromiumType(browser),
    isMobile: isMobileAndTabletType(device) || getIPad13(),
    isMobileOnly: isMobileType(device),
    isTablet: isTabletType(device) || getIPad13(),
    isBrowser: isBrowserType(device),
    isDesktop: isBrowserType(device),
    isAndroid: isAndroidType(os),
    isWinPhone: isWinPhoneType(os),
    isIOS: isIOSType(os) || getIPad13(),
    isChrome: isChromeType(browser),
    isFirefox: isFirefoxType(browser),
    isSafari: isSafariType(browser),
    isOpera: isOperaType(browser),
    isIE: isIEType(browser),
    osVersion: getOsVersion(os),
    osName: getOsName(os),
    fullBrowserVersion: getBrowserFullVersion(browser),
    browserVersion: getBrowserVersion(browser),
    browserName: getBrowserName(browser),
    mobileVendor: getMobileVendor(device),
    mobileModel: getMobileModel(device),
    engineName: getEngineName(engine),
    engineVersion: getEngineVersion(engine),
    getUA: getUseragent(ua),
    isEdge: isEdgeType(browser) || isEdgeChromiumType(ua),
    isYandex: isYandexType(browser),
    deviceType: getDeviceType(device),
    isIOS13: getIOS13(),
    isIPad13: getIPad13(),
    isIPhone13: getIphone13(),
    isIPod13: getIPod13(),
    isElectron: isElectronType(),
    isEdgeChromium: isEdgeChromiumType(ua),
    isLegacyEdge: isEdgeType(browser) && !isEdgeChromiumType(ua),
    isWindows: isWindowsType(os),
    isMacOs: isMacOsType(os),
    isMIUI: isMIUIType(browser),
    isSamsungBrowser: isSamsungBrowserType(browser)
  };
}

var isSmartTV = isSmartTVType(device);
var isConsole = isConsoleType(device);
var isWearable = isWearableType(device);
var isEmbedded = isEmbeddedType(device);
var isMobileSafari = isMobileSafariType(browser) || getIPad13();
var isChromium = isChromiumType(browser);
var isMobile = isMobileAndTabletType(device) || getIPad13();
var isMobileOnly = isMobileType(device);
var isTablet = isTabletType(device) || getIPad13();
var isBrowser = isBrowserType(device);
var isDesktop = isBrowserType(device);
var isAndroid = isAndroidType(os);
var isWinPhone = isWinPhoneType(os);
var isIOS = isIOSType(os) || getIPad13();
var isChrome = isChromeType(browser);
var isFirefox = isFirefoxType(browser);
var isSafari = isSafariType(browser);
var isOpera = isOperaType(browser);
var isIE = isIEType(browser);
var osVersion = getOsVersion(os);
var osName = getOsName(os);
var fullBrowserVersion = getBrowserFullVersion(browser);
var browserVersion = getBrowserVersion(browser);
var browserName = getBrowserName(browser);
var mobileVendor = getMobileVendor(device);
var mobileModel = getMobileModel(device);
var engineName = getEngineName(engine);
var engineVersion = getEngineVersion(engine);
var getUA = getUseragent(ua);
var isEdge = isEdgeType(browser) || isEdgeChromiumType(ua);
var isYandex = isYandexType(browser);
var deviceType = getDeviceType(device);
var isIOS13 = getIOS13();
var isIPad13 = getIPad13();
var isIPhone13 = getIphone13();
var isIPod13 = getIPod13();
var isElectron = isElectronType();
var isEdgeChromium = isEdgeChromiumType(ua);
var isLegacyEdge = isEdgeType(browser) && !isEdgeChromiumType(ua);
var isWindows = isWindowsType(os);
var isMacOs = isMacOsType(os);
var isMIUI = isMIUIType(browser);
var isSamsungBrowser = isSamsungBrowserType(browser);
var getSelectorsByUserAgent = function getSelectorsByUserAgent(userAgent) {
  if (!userAgent || typeof userAgent !== 'string') {
    console.error('No valid user agent string was provided');
    return;
  }

  var _UAHelper$parseUserAg = parseUserAgent(userAgent),
      device = _UAHelper$parseUserAg.device,
      browser = _UAHelper$parseUserAg.browser,
      os = _UAHelper$parseUserAg.os,
      engine = _UAHelper$parseUserAg.engine,
      ua = _UAHelper$parseUserAg.ua;

  return buildSelectorsObject({
    device: device,
    browser: browser,
    os: os,
    engine: engine,
    ua: ua
  });
};

var AndroidView = function AndroidView(_ref) {
  var renderWithFragment = _ref.renderWithFragment,
      children = _ref.children,
      props = _objectWithoutProperties(_ref, ["renderWithFragment", "children"]);

  return isAndroid ? renderWithFragment ? React__default.createElement(React.Fragment, null, children) : React__default.createElement("div", props, children) : null;
};
var BrowserView = function BrowserView(_ref2) {
  var renderWithFragment = _ref2.renderWithFragment,
      children = _ref2.children,
      props = _objectWithoutProperties(_ref2, ["renderWithFragment", "children"]);

  return isBrowser ? renderWithFragment ? React__default.createElement(React.Fragment, null, children) : React__default.createElement("div", props, children) : null;
};
var IEView = function IEView(_ref3) {
  var renderWithFragment = _ref3.renderWithFragment,
      children = _ref3.children,
      props = _objectWithoutProperties(_ref3, ["renderWithFragment", "children"]);

  return isIE ? renderWithFragment ? React__default.createElement(React.Fragment, null, children) : React__default.createElement("div", props, children) : null;
};
var IOSView = function IOSView(_ref4) {
  var renderWithFragment = _ref4.renderWithFragment,
      children = _ref4.children,
      props = _objectWithoutProperties(_ref4, ["renderWithFragment", "children"]);

  return isIOS ? renderWithFragment ? React__default.createElement(React.Fragment, null, children) : React__default.createElement("div", props, children) : null;
};
var MobileView = function MobileView(_ref5) {
  var renderWithFragment = _ref5.renderWithFragment,
      children = _ref5.children,
      props = _objectWithoutProperties(_ref5, ["renderWithFragment", "children"]);

  return isMobile ? renderWithFragment ? React__default.createElement(React.Fragment, null, children) : React__default.createElement("div", props, children) : null;
};
var TabletView = function TabletView(_ref6) {
  var renderWithFragment = _ref6.renderWithFragment,
      children = _ref6.children,
      props = _objectWithoutProperties(_ref6, ["renderWithFragment", "children"]);

  return isTablet ? renderWithFragment ? React__default.createElement(React.Fragment, null, children) : React__default.createElement("div", props, children) : null;
};
var WinPhoneView = function WinPhoneView(_ref7) {
  var renderWithFragment = _ref7.renderWithFragment,
      children = _ref7.children,
      props = _objectWithoutProperties(_ref7, ["renderWithFragment", "children"]);

  return isWinPhone ? renderWithFragment ? React__default.createElement(React.Fragment, null, children) : React__default.createElement("div", props, children) : null;
};
var MobileOnlyView = function MobileOnlyView(_ref8) {
  var renderWithFragment = _ref8.renderWithFragment,
      children = _ref8.children;
      _ref8.viewClassName;
      _ref8.style;
      var props = _objectWithoutProperties(_ref8, ["renderWithFragment", "children", "viewClassName", "style"]);

  return isMobileOnly ? renderWithFragment ? React__default.createElement(React.Fragment, null, children) : React__default.createElement("div", props, children) : null;
};
var SmartTVView = function SmartTVView(_ref9) {
  var renderWithFragment = _ref9.renderWithFragment,
      children = _ref9.children,
      props = _objectWithoutProperties(_ref9, ["renderWithFragment", "children"]);

  return isSmartTV ? renderWithFragment ? React__default.createElement(React.Fragment, null, children) : React__default.createElement("div", props, children) : null;
};
var ConsoleView = function ConsoleView(_ref10) {
  var renderWithFragment = _ref10.renderWithFragment,
      children = _ref10.children,
      props = _objectWithoutProperties(_ref10, ["renderWithFragment", "children"]);

  return isConsole ? renderWithFragment ? React__default.createElement(React.Fragment, null, children) : React__default.createElement("div", props, children) : null;
};
var WearableView = function WearableView(_ref11) {
  var renderWithFragment = _ref11.renderWithFragment,
      children = _ref11.children,
      props = _objectWithoutProperties(_ref11, ["renderWithFragment", "children"]);

  return isWearable ? renderWithFragment ? React__default.createElement(React.Fragment, null, children) : React__default.createElement("div", props, children) : null;
};
var CustomView = function CustomView(_ref12) {
  var renderWithFragment = _ref12.renderWithFragment,
      children = _ref12.children;
      _ref12.viewClassName;
      _ref12.style;
      var condition = _ref12.condition,
      props = _objectWithoutProperties(_ref12, ["renderWithFragment", "children", "viewClassName", "style", "condition"]);

  return condition ? renderWithFragment ? React__default.createElement(React.Fragment, null, children) : React__default.createElement("div", props, children) : null;
};

function withOrientationChange(WrappedComponent) {
  return /*#__PURE__*/function (_React$Component) {
    _inherits(_class, _React$Component);

    function _class(props) {
      var _this;

      _classCallCheck(this, _class);

      _this = _possibleConstructorReturn(this, _getPrototypeOf(_class).call(this, props));
      _this.isEventListenerAdded = false;
      _this.handleOrientationChange = _this.handleOrientationChange.bind(_assertThisInitialized(_this));
      _this.onOrientationChange = _this.onOrientationChange.bind(_assertThisInitialized(_this));
      _this.onPageLoad = _this.onPageLoad.bind(_assertThisInitialized(_this));
      _this.state = {
        isLandscape: false,
        isPortrait: false
      };
      return _this;
    }

    _createClass(_class, [{
      key: "handleOrientationChange",
      value: function handleOrientationChange() {
        if (!this.isEventListenerAdded) {
          this.isEventListenerAdded = true;
        }

        var orientation = window.innerWidth > window.innerHeight ? 90 : 0;
        this.setState({
          isPortrait: orientation === 0,
          isLandscape: orientation === 90
        });
      }
    }, {
      key: "onOrientationChange",
      value: function onOrientationChange() {
        this.handleOrientationChange();
      }
    }, {
      key: "onPageLoad",
      value: function onPageLoad() {
        this.handleOrientationChange();
      }
    }, {
      key: "componentDidMount",
      value: function componentDidMount() {
        if ((typeof window === "undefined" ? "undefined" : _typeof(window)) !== undefined && isMobile) {
          if (!this.isEventListenerAdded) {
            this.handleOrientationChange();
            window.addEventListener("load", this.onPageLoad, false);
          } else {
            window.removeEventListener("load", this.onPageLoad, false);
          }

          window.addEventListener("resize", this.onOrientationChange, false);
        }
      }
    }, {
      key: "componentWillUnmount",
      value: function componentWillUnmount() {
        window.removeEventListener("resize", this.onOrientationChange, false);
      }
    }, {
      key: "render",
      value: function render() {
        return React__default.createElement(WrappedComponent, _extends({}, this.props, {
          isLandscape: this.state.isLandscape,
          isPortrait: this.state.isPortrait
        }));
      }
    }]);

    return _class;
  }(React__default.Component);
}

function useMobileOrientation() {
  var _useState = React.useState(function () {
    var orientation = window.innerWidth > window.innerHeight ? 90 : 0;
    return {
      isPortrait: orientation === 0,
      isLandscape: orientation === 90,
      orientation: orientation === 0 ? 'portrait' : 'landscape'
    };
  }),
      _useState2 = _slicedToArray(_useState, 2),
      state = _useState2[0],
      setState = _useState2[1];

  var handleOrientationChange = React.useCallback(function () {
    var orientation = window.innerWidth > window.innerHeight ? 90 : 0;
    var next = {
      isPortrait: orientation === 0,
      isLandscape: orientation === 90,
      orientation: orientation === 0 ? 'portrait' : 'landscape'
    };
    state.orientation !== next.orientation && setState(next);
  }, [state.orientation]);
  React.useEffect(function () {
    if ((typeof window === "undefined" ? "undefined" : _typeof(window)) !== undefined && isMobile) {
      handleOrientationChange();
      window.addEventListener("load", handleOrientationChange, false);
      window.addEventListener("resize", handleOrientationChange, false);
    }

    return function () {
      window.removeEventListener("resize", handleOrientationChange, false);
      window.removeEventListener("load", handleOrientationChange, false);
    };
  }, [handleOrientationChange]);
  return state;
}

function useDeviceData(userAgent) {
  var hookUserAgent = userAgent ? userAgent : window.navigator.userAgent;
  return parseUserAgent(hookUserAgent);
}

function useDeviceSelectors(userAgent) {
  var hookUserAgent = userAgent ? userAgent : window.navigator.userAgent;
  var deviceData = useDeviceData(hookUserAgent);
  var selectors = buildSelectorsObject(deviceData);
  return [selectors, deviceData];
}

lib.AndroidView = AndroidView;
lib.BrowserTypes = BrowserTypes;
lib.BrowserView = BrowserView;
lib.ConsoleView = ConsoleView;
lib.CustomView = CustomView;
lib.IEView = IEView;
lib.IOSView = IOSView;
lib.MobileOnlyView = MobileOnlyView;
lib.MobileView = MobileView;
lib.OsTypes = OsTypes;
lib.SmartTVView = SmartTVView;
lib.TabletView = TabletView;
lib.WearableView = WearableView;
lib.WinPhoneView = WinPhoneView;
lib.browserName = browserName;
lib.browserVersion = browserVersion;
lib.deviceDetect = deviceDetect;
lib.deviceType = deviceType;
lib.engineName = engineName;
lib.engineVersion = engineVersion;
lib.fullBrowserVersion = fullBrowserVersion;
lib.getSelectorsByUserAgent = getSelectorsByUserAgent;
lib.getUA = getUA;
lib.isAndroid = isAndroid;
lib.isBrowser = isBrowser;
lib.isChrome = isChrome;
lib.isChromium = isChromium;
lib.isConsole = isConsole;
lib.isDesktop = isDesktop;
lib.isEdge = isEdge;
lib.isEdgeChromium = isEdgeChromium;
lib.isElectron = isElectron;
lib.isEmbedded = isEmbedded;
lib.isFirefox = isFirefox;
lib.isIE = isIE;
lib.isIOS = isIOS;
lib.isIOS13 = isIOS13;
lib.isIPad13 = isIPad13;
lib.isIPhone13 = isIPhone13;
lib.isIPod13 = isIPod13;
lib.isLegacyEdge = isLegacyEdge;
lib.isMIUI = isMIUI;
lib.isMacOs = isMacOs;
lib.isMobile = isMobile;
var isMobileOnly_1 = lib.isMobileOnly = isMobileOnly;
lib.isMobileSafari = isMobileSafari;
lib.isOpera = isOpera;
lib.isSafari = isSafari;
lib.isSamsungBrowser = isSamsungBrowser;
lib.isSmartTV = isSmartTV;
lib.isTablet = isTablet;
lib.isWearable = isWearable;
lib.isWinPhone = isWinPhone;
lib.isWindows = isWindows;
lib.isYandex = isYandex;
lib.mobileModel = mobileModel;
lib.mobileVendor = mobileVendor;
lib.osName = osName;
lib.osVersion = osVersion;
lib.parseUserAgent = parseUserAgent;
lib.setUserAgent = setUserAgent;
lib.useDeviceData = useDeviceData;
lib.useDeviceSelectors = useDeviceSelectors;
lib.useMobileOrientation = useMobileOrientation;
lib.withOrientationChange = withOrientationChange;

/**
 * @author [author]
 * @email [example@mail.com]
 * @create date 2023-02-20 08:06:34
 * @modify date 2023-02-20 08:06:34
 * @desc [description]
 */
var RowType;
(function (RowType) {
    RowType[RowType["NONE"] = 0] = "NONE";
    RowType[RowType["INTERNAL_LINK"] = 1] = "INTERNAL_LINK";
    RowType[RowType["EXTERNAL_LINK"] = 2] = "EXTERNAL_LINK";
    RowType[RowType["DOWNLOAD_FILE"] = 3] = "DOWNLOAD_FILE";
    RowType[RowType["OPEN_DIALOG"] = 4] = "OPEN_DIALOG";
    RowType[RowType["SHOW_ADDITIONAL_INFO"] = 5] = "SHOW_ADDITIONAL_INFO";
    RowType[RowType["OTHER_INFO"] = 6] = "OTHER_INFO";
})(RowType || (RowType = {}));

var RowTypeUtils = /** @class */ (function () {
    function RowTypeUtils() {
    }
    RowTypeUtils.getIcon = function (rowType, color) {
        if (color === void 0) { color = true; }
        var colorStyle;
        if (color == true) {
            colorStyle = { color: "#32814B" };
        }
        else {
            colorStyle = {};
        }
        switch (rowType) {
            case RowType.INTERNAL_LINK:
                return React$1.createElement("i", { className: "fas fa-external-link-square-alt fa-lg", style: colorStyle });
            case RowType.EXTERNAL_LINK:
                return React$1.createElement("i", { className: "fas fa-external-link-alt fa-lg", style: colorStyle });
            case RowType.DOWNLOAD_FILE:
                return React$1.createElement("i", { className: "fas fa-file-download fa-lg", style: colorStyle });
            case RowType.OPEN_DIALOG:
                return React$1.createElement("i", { className: "far fa-window-maximize fa-lg", style: colorStyle });
            case RowType.SHOW_ADDITIONAL_INFO:
                return React$1.createElement("i", { className: "fas fa-caret-square-down fa-lg", style: colorStyle });
            case RowType.OTHER_INFO:
                return React$1.createElement("i", { className: "fas fa-info-circle fa-lg", style: colorStyle });
            case RowType.NONE:
            default:
                return React$1.createElement(React$1.Fragment, null);
        }
    };
    RowTypeUtils.getLabel = function (rowType, i18n) {
        switch (rowType) {
            case RowType.INTERNAL_LINK:
                return i18n.t("rowtype.internal_link");
            case RowType.EXTERNAL_LINK:
                return i18n.t("rowtype.external_link");
            case RowType.DOWNLOAD_FILE:
                return i18n.t("rowtype.download_file");
            case RowType.OPEN_DIALOG:
                return i18n.t("rowtype.open_dialog");
            case RowType.SHOW_ADDITIONAL_INFO:
                return i18n.t("rowtype.show_additional_info");
            case RowType.OTHER_INFO:
                return i18n.t("rowtype.other_info");
            case RowType.NONE:
            default:
                return "";
        }
    };
    return RowTypeUtils;
}());

/**
 * @author fbosch, anadal
 * @create date 2022-11-23 10:30:40
 * @modify date 2022-11-23 10:30:40
 * @desc [description]
 */
var RenderTableMobile = /** @class */ (function (_super) {
    __extends(RenderTableMobile, _super);
    function RenderTableMobile(props) {
        return _super.call(this, props) || this;
    }
    RenderTableMobile.prototype.render = function () {
        var _this = this;
        console.log("Render OK: Imprimint Data RENDER TABLE MOBILE...!");
        var rowType = (this.props.rowType == undefined ? RowType.NONE : this.props.rowType);
        var data = this.props.tableData; // Aquest valor sera this.props.dades
        var columnsNom = this.props.columnNames;
        //console.log("MOBILE[NOM] => " + columnsNom.length);
        //console.log("MOBILE[columnNamesAdditionals] => " + this.props.columnNamesAdditionals);
        if (this.props.columnNamesAdditionals) {
            //console.log("MOBILE[columnNamesAdditionals.len] => " + this.props.columnNamesAdditionals.length);
            columnsNom = columnsNom.concat(this.props.columnNamesAdditionals);
        }
        //console.log("MOBILE[NOM + ADDICIO] => " + columnsNom.length);
        var titols = this.props.columnTitles;
        //console.log("MOBILE[TITOLS] => " + titols.length);
        if (this.props.columnTitlesAdditionals) {
            titols = titols.concat(this.props.columnTitlesAdditionals);
        }
        //console.log("MOBILE[TITOLS + ADDICIO] => " + columnsNom.length);
        var content;
        content = (React$1.createElement(React$1.Fragment, null,
            React$1.createElement("div", null, data.map(function (dataInfo, i) {
                var cardsMobile = [];
                {
                    columnsNom.forEach(function (clau, c) {
                        cardsMobile.push(React$1.createElement("div", { className: "col-sm-10 float-right", onClick: function () {
                                if (_this.props.onClickRow) {
                                    _this.props.onClickRow(i, _this.props.tableData[i]);
                                }
                            } },
                            React$1.createElement("div", { className: "card-text pl-1 mt-0", style: { color: "rgb(102, 102, 102)" } },
                                React$1.createElement("b", null, titols[c]),
                                " ",
                                titols[c] == "" ? "" : ":",
                                " ",
                                dataInfo[clau])));
                    });
                }
                return (React$1.createElement(React$1.Fragment, null,
                    React$1.createElement("div", { className: "col-lg-4 col-md-4 col-sm-4 pl-2 pt-5 pb-5  visioMobil cardAppVerd", key: i, tabIndex: 511 + i },
                        (rowType != RowType.NONE && rowType != RowType.SHOW_ADDITIONAL_INFO) && React$1.createElement("div", { style: { position: "absolute", right: "10px" } }, RowTypeUtils.getIcon(rowType)),
                        React$1.createElement("div", { className: "col-sm-1 float-left" },
                            React$1.createElement("span", { className: "oi ".concat(_this.props.mobileIcon ? _this.props.mobileIcon : "oi-key", " iconaFormApp"), style: { verticalAlign: "sub" } })),
                        cardsMobile)));
            }))));
        return React$1.createElement(React$1.Fragment, null, content);
    };
    return RenderTableMobile;
}(React$1.Component));

/**
 * @author fbosch, anadal
 * @create date 2022-11-23 10:30:40
 * @modify date 2022-11-23 10:30:40
 * @desc [description]
 */
var RenderTableDesktop = /** @class */ (function (_super) {
    __extends(RenderTableDesktop, _super);
    function RenderTableDesktop(props) {
        var _this = _super.call(this, props) || this;
        _this.onClickTableRow = _this.onClickTableRow.bind(_this);
        _this.mostrarMesInfo = _this.mostrarMesInfo.bind(_this);
        return _this;
    }
    RenderTableDesktop.prototype.componentDidMount = function () { };
    RenderTableDesktop.prototype.onClickTableRow = function (i) {
        if (this.props.columnNamesAdditionals == undefined) {
            if (this.props.onClickRow) {
                this.props.onClickRow(i, this.props.tableData[i]);
            }
        }
        else {
            // Mostrar fila addicional
            this.mostrarMesInfo("additional_row_" + i);
        }
    };
    RenderTableDesktop.prototype.mostrarMesInfo = function (row) {
        var element = document.getElementById(row);
        if (element) {
            if (element.style.display === "none") {
                element.style.display = "table-row";
            }
            else if (element.style.display === "table-row") {
                element.style.display = "none";
            }
        }
    };
    RenderTableDesktop.prototype.render = function () {
        var _this = this;
        console.log("RenderTableDesktop::render() Start ...");
        var data = this.props.tableData; // Aquest valor sera this.props.dades
        var rowType = this.props.rowType == undefined ? RowType.NONE : this.props.rowType;
        var columnsNom = this.props.columnNames;
        var titols = this.props.columnTitles;
        var capTaula = [];
        {
            titols.forEach(function (clau, c) {
                //console.log(" HEADER::[" + c + "] -> " + clau + " => " + titols[c]);
                capTaula.push(React$1.createElement("th", { key: "header_" + c }, clau));
            });
            if (rowType != RowType.NONE) {
                capTaula.push(React$1.createElement("th", { key: "header_RowType" }, "\u00A0"));
            }
        }
        return (React$1.createElement(React$1.Fragment, null,
            React$1.createElement("div", null,
                React$1.createElement("table", { id: "tableId", style: { width: "99%", border: "0px" }, className: "table table-striped table-bordered table-hover" },
                    React$1.createElement("thead", { className: "table-success" },
                        React$1.createElement("tr", null, capTaula)),
                    React$1.createElement("tbody", null, data.map(function (dataInfo, i) {
                        var fila = [];
                        columnsNom.forEach(function (clau, c) {
                            var valor = dataInfo[clau];
                            fila.push(React$1.createElement("td", { key: i + "_" + c }, valor));
                        });
                        var columnsLength = columnsNom.length;
                        if (rowType != RowType.NONE) {
                            fila.push(React$1.createElement("td", { key: i + "RowType", title: RowTypeUtils.getLabel(rowType, _this.props.i18n), style: { textAlign: "center" } }, RowTypeUtils.getIcon(rowType)));
                            columnsLength++;
                        }
                        var filaAddicional = null;
                        if (_this.props.columnNamesAdditionals != undefined && _this.props.columnTitlesAdditionals != undefined) {
                            var filaAddicionalContent_1 = [];
                            var columnsNomAddicionals = _this.props.columnNamesAdditionals;
                            var columnsTitolsAddicionals_1 = _this.props.columnTitlesAdditionals;
                            columnsNomAddicionals.forEach(function (clauA, ca) {
                                var valor = (React$1.createElement(React$1.Fragment, null,
                                    React$1.createElement("b", { key: i + "_" + ca + "_add" }, columnsTitolsAddicionals_1[ca]),
                                    columnsTitolsAddicionals_1[ca] == "" ? "" : ": ",
                                    dataInfo[clauA],
                                    " ",
                                    React$1.createElement("br", null)));
                                filaAddicionalContent_1.push(valor);
                            });
                            filaAddicional = (React$1.createElement("tr", { key: "add_row_" + i, style: { display: "none" }, id: "additional_row_" + i },
                                React$1.createElement("td", { key: "add_col_" + i, colSpan: columnsLength }, filaAddicionalContent_1)));
                        }
                        else {
                            filaAddicional = React$1.createElement(React$1.Fragment, null);
                        }
                        var rowStyle = {};
                        switch (rowType) {
                            case RowType.INTERNAL_LINK:
                            case RowType.EXTERNAL_LINK:
                            case RowType.DOWNLOAD_FILE:
                            case RowType.OTHER_INFO:
                                rowStyle = { cursor: "pointer" };
                                break;
                            case RowType.SHOW_ADDITIONAL_INFO:
                                rowStyle = { cursor: "zoom-in" };
                                break;
                            case RowType.OPEN_DIALOG:
                                rowStyle = { cursor: "context-menu" };
                            default:
                                rowStyle = {};
                        }
                        return (React$1.createElement(React$1.Fragment, null,
                            React$1.createElement("tr", { key: "fila_" + i, tabIndex: 511 + i * 2 - 1, style: rowStyle, onClick: function () {
                                    _this.onClickTableRow(i);
                                }, onKeyPress: function () {
                                    _this.onClickTableRow(i);
                                } }, fila),
                            filaAddicional));
                    }))))));
    };
    return RenderTableDesktop;
}(react.exports.Component));

/**
 * @author fbosch, anadal
 * @create date 2022-11-23 10:30:40
 * @modify date 2022-11-23 10:30:40
 * @desc [description]
 */
var RenderTable = /** @class */ (function (_super) {
    __extends(RenderTable, _super);
    function RenderTable(props) {
        var _this = _super.call(this, props) || this;
        _this.state = {
            isLoaded: false,
            tableData: null,
            error: null,
        };
        _this.updateTableData = _this.updateTableData.bind(_this);
        //this.carregarDadesAsync = this.carregarDadesAsync.bind(this);
        console.log("  CONSTRUCTOR RenderTable !!!!!");
        return _this;
    }
    RenderTable.prototype.componentDidMount = function () {
        if (this.props.loadData != undefined) {
            var returnData = {
                returnDataFunction: this.updateTableData
            };
            this.props.loadData(returnData);
        }
    };
    RenderTable.prototype.updateTableData = function (dades) {
        if (dades == null) {
            console.log("RenderTable::updateTableData(CARREGANT...)");
            this.setState({ isLoaded: false, tableData: null, error: null });
        }
        else {
            console.log("RenderTable::updateTableData(CARREGAR DADES ...)");
            this.setState({ isLoaded: true, tableData: dades.tableData, error: dades.error });
        }
    };
    /*
    async carregarDadesAsync() {
      if (this.props.automaticLoadData == true) {
        let dades: RenderTableData = this.props.loadData();
        this.setState({ isLoaded: true, tableData: dades.tableData, error: dades.error });
      }
    }
    */
    RenderTable.prototype.render = function () {
        console.log("RenderTable::render() => Start");
        if (this.state.error != null) {
            return (React$1.createElement("div", { className: "alert alert-danger", role: "alert" }, this.state.error));
        }
        var tableData = this.state.tableData;
        if (!this.state.isLoaded || tableData == undefined) {
            if (tableData == undefined) {
                console.log("RenderTable::render()  ===========================================");
                console.log("RenderTable::render() => this.state.tableData == undefined !!!!!!!");
                console.log("RenderTable::render() => Esperam a veure si tornam a carregar ....");
                console.log("RenderTable::render()  ===========================================");
            }
            return (React$1.createElement("div", { id: "carregant", className: "loader-container centrat " },
                React$1.createElement("div", { className: "loader" })));
        }
        if (!Array.isArray(tableData)) {
            console.error("RenderTable::render() => this.state.tableData = NO ES UN ARRAY !!!!!!");
        }
        if (tableData) {
            console.log("RenderTable::render() => this.state.tableData.length = " + tableData.length);
        }
        if (tableData == null || (tableData != null && tableData.length == 0)) {
            return (React$1.createElement("div", { className: "alert alert-warning", role: "alert" }, this.props.i18n.t("taulaBuida")));
        }
        /*
        let currentProps: RenderInternalTableProps = {
          columnNames: this.props.columnNames,
          columnTitles: this.props.columnTitles,
          columnNamesAdditionals: this.props.columnNamesAdditionals,
          columnTitlesAdditionals: this.props.columnTitlesAdditionals,
          onClickRow: this.props.onClickRow,
          mobileIcon: this.props.mobileIcon,
          rowType: this.props.rowType,
          i18n: this.props.i18n,
          //loadData: this.props.loadData,
          automaticLoadData: false,
          tableData: this.state.tableData,
        };
        */
        // OK Tenim dades
        if (!isMobileOnly_1) {
            return React$1.createElement(RenderTableDesktop, __assign({}, this.props, { tableData: tableData }));
        }
        else {
            return React$1.createElement(RenderTableMobile, __assign({}, this.props, { tableData: tableData }));
        }
    };
    return RenderTable;
}(React$1.Component));

var tornar$2 = "Back";
var paginacioLabel$2 = "Mostrando {{current}} entradas de {{total}} (De la posición {{from}} hasta la {{to}})";
var elementPerPaginaMostra$2 = "Show  ";
var elementPerPaginaElements$2 = "  elements";
var taulaBuida$2 = "No hay elementos para mostrar";
var enviar$2 = "Enviar";
var translationEN = {
	tornar: tornar$2,
	paginacioLabel: paginacioLabel$2,
	elementPerPaginaMostra: elementPerPaginaMostra$2,
	elementPerPaginaElements: elementPerPaginaElements$2,
	taulaBuida: taulaBuida$2,
	"pagination.primerapagina": "Ir a la primera página",
	"pagination.darrerapagina": "Ir a la última página",
	"pagination.avancar": "Avanzar a la página {{pagina}}",
	"pagination.retrocedir": "Retroceder a la página {{pagina}}",
	"pagination.seguent": "Página seguiente",
	"pagination.anterior": "Página anterior",
	enviar: enviar$2,
	"rowtype.internal_link": "Accedeix a una altra pàgina amb més informació d'aquest element",
	"rowtype.external_link": "Obre una altra finestra per accedir a més informació d'aquest element",
	"rowtype.download_file": "Descarrega un fitxer",
	"rowtype.open_dialog": "Obre un diàleg amb més informació",
	"rowtype.show_additional_info": "Mostra més informació en un desplegable en la mateixa pàgina",
	"rowtype.other_info": "Mostra més informació"
};

var tornar$1 = "Volver";
var paginacioLabel$1 = "Elementos del {{from}} al {{to}} de un total de {{total}}";
var elementPerPaginaMostra$1 = "";
var elementPerPaginaElements$1 = "  elementos por página";
var enviar$1 = "Buscar";
var taulaBuida$1 = "No hay elementos para mostrar";
var translationES = {
	tornar: tornar$1,
	paginacioLabel: paginacioLabel$1,
	elementPerPaginaMostra: elementPerPaginaMostra$1,
	elementPerPaginaElements: elementPerPaginaElements$1,
	"pagination.primerapagina": "Ir a la primera página",
	"pagination.darrerapagina": "Ir a la última página",
	"pagination.avancar": "Avanzar a la página {{pagina}}",
	"pagination.retrocedir": "Retroceder a la página {{pagina}}",
	"pagination.seguent": "Página siguiente",
	"pagination.anterior": "Página anterior",
	enviar: enviar$1,
	taulaBuida: taulaBuida$1,
	"rowtype.internal_link": "Accede a otra página con más información de esta entrada",
	"rowtype.external_link": "Abre otra ventana para acceder a más información de esta entrada",
	"rowtype.download_file": "Descarga un fichero",
	"rowtype.open_dialog": "Abre un diálogo con más información",
	"rowtype.show_additional_info": "Muestra más información en un desplegable en la misma página",
	"rowtype.other_info": "Muestra más información"
};

var tornar = "Tornau";
var paginacioLabel = "Elements del {{from}} al {{to}} d'un total de {{total}}";
var elementPerPaginaMostra = "";
var elementPerPaginaElements = "  elements per pàgina";
var enviar = "Cercau";
var taulaBuida = "No hi ha elements a mostrar";
var translationCA = {
	tornar: tornar,
	paginacioLabel: paginacioLabel,
	elementPerPaginaMostra: elementPerPaginaMostra,
	elementPerPaginaElements: elementPerPaginaElements,
	"pagination.primerapagina": "Anar a la primera pàgina",
	"pagination.darrerapagina": "Anar a la darrera pàgina",
	"pagination.avancar": "Avançar a la pàgina {{pagina}}",
	"pagination.retrocedir": "Retrocedir a la pàgina {{pagina}}",
	"pagination.seguent": "Pàgina següent",
	"pagination.anterior": "Pàgina anterior",
	enviar: enviar,
	taulaBuida: taulaBuida,
	"rowtype.internal_link": "Accedeix a una altra pàgina amb més informació d'aquest element",
	"rowtype.external_link": "Obre una altra finestra per accedir a més informació d'aquest element",
	"rowtype.download_file": "Descarrega un fitxer",
	"rowtype.open_dialog": "Obre un diàleg amb més informació",
	"rowtype.show_additional_info": "Mostra més informació en un desplegable en la mateixa pàgina",
	"rowtype.other_info": "Mostra més informació"
};

/**
 * @author anadal
 * @create date 2023-01-03 11:22:46
 * @modify date 2023-01-03 11:22:46
 * @desc [description]
 */
function initTranslation(i18n) {
    i18n.addResourceBundle("ca", "translation", translationCA, true, false);
    i18n.addResourceBundle("es", "translation", translationES, true, false);
    i18n.addResourceBundle("en", "translation", translationEN, true, false);
}

/**
 * @author anadal
 * @create date 2023-01-11 07:56:19
 * @modify date 2023-01-11 07:56:19
 * @desc [description]
 */
var PaginationItemCarpeta = /** @class */ (function (_super) {
    __extends(PaginationItemCarpeta, _super);
    function PaginationItemCarpeta(props) {
        return _super.call(this, props) || this;
    }
    PaginationItemCarpeta.prototype.render = function () {
        var _this = this;
        return (React$1.createElement("li", { key: "item_" + this.props.value, className: "page-item ".concat(this.props.active ? "active" : "") },
            React$1.createElement("a", { className: "page-link", role: "button", title: this.props.title ? this.props.title : "", href: undefined, onClick: function () {
                    if (_this.props.onClick) {
                        _this.props.onClick(_this.props.value);
                    }
                } }, this.props.children)));
    };
    return PaginationItemCarpeta;
}(React$1.Component));

/**
 * @author [anadal]
 * @create date 2022-12-28 11:36:00
 * @modify date 2022-12-28 11:36:00
 * @desc [description]
 */
var PaginationCarpeta = /** @class */ (function (_super) {
    __extends(PaginationCarpeta, _super);
    function PaginationCarpeta(props) {
        var _this = _super.call(this, props) || this;
        console.log("  CONSTRUCTOR PaginationCarpeta !!!!!");
        /*
        console.log("PaginationCarpeta().paginaActual  => " + this.props.paginationInfo.paginaActual);
        console.log("PaginationCarpeta().elementsPerPagina  => " + this.props.paginationInfo.elementsPerPagina);
        console.log("PaginationCarpeta().totalPagines  => " + this.props.paginationInfo.totalPagines);
        console.log("PaginationCarpeta().registresRetornats  => " + this.props.paginationInfo.registresRetornats);
        console.log("PaginationCarpeta().totalRegistres  => " + this.props.paginationInfo.totalRegistres);
        */
        initTranslation(props.i18n);
        _this.updatePaginationInfo = _this.updatePaginationInfo.bind(_this);
        _this.state = {
            paginationInfo: null,
        };
        return _this;
    }
    PaginationCarpeta.prototype.updatePaginationInfo = function (paginationInfo) {
        if (paginationInfo == null) {
            console.log("PaginationCarpeta::updatePaginationInfo(CARREGANT...)");
        }
        else {
            console.log("PaginationCarpeta::updatePaginationInfo(CARREGAR DADES ...)");
        }
        this.setState({ paginationInfo: paginationInfo });
    };
    PaginationCarpeta.prototype.render = function () {
        console.log("PaginationCarpeta::render() =>  START");
        var t = this.props.i18n.t;
        var paginationInfo = this.state.paginationInfo;
        if (paginationInfo == null || paginationInfo.totalElements == 0) {
            //console.log("PaginationCarpeta::render() =>  paginationInfo == null");
            return React$1.createElement(React$1.Fragment, null);
        }
        var total = paginationInfo.totalElements;
        var from = paginationInfo.paginaActual * paginationInfo.elementsPerPagina + 1;
        var to = Math.min(paginationInfo.totalElements, (1 + paginationInfo.paginaActual) * paginationInfo.elementsPerPagina);
        /* Mostrant elements del {{from}} al {{to}} d'un total de {{total}} elements*/
        //console.log("PaginationCarpeta::render() =>  Desplegable elementsPerPàgina ...");
        var message = t("paginacioLabel", {
            total: total,
            from: from,
            to: to,
        });
        var onClick = this.props.onClickPagination;
        var onClickSelectElementsByPage = undefined;
        var pagination = React$1.createElement(React$1.Fragment, null);
        var numElements = React$1.createElement(React$1.Fragment, null);
        if (paginationInfo.totalPagines != 1) {
            //console.log("PaginationCarpeta::render() =>  selectElementsByPage ...");
            onClickSelectElementsByPage = this.props.onClickSelectElementsByPage;
            var maxCasellesPerMostrar = void 0;
            var w = window.innerWidth;
            console.log("PaginationCarpeta::render() => window.innerWidth = " + w);
            // Serveix per ajustar la funció de càlcul de casseles a pintar
            var ajust = isMobileOnly_1 ? 0 : -2;
            if (w < 920) {
                maxCasellesPerMostrar = 5;
            }
            else if (w > 1400) {
                maxCasellesPerMostrar = 13 + ajust;
            }
            else {
                maxCasellesPerMostrar = Math.floor((9 * w - 5880) / 480) + ajust;
            }
            console.log("PaginationCarpeta::render() => caselles = " + maxCasellesPerMostrar);
            //TODO FALTA MOBILE
            // XYZ ZZZ
            var start = void 0;
            var end = void 0;
            var showPoints = void 0;
            console.log("PaginationCarpeta::render() => paginationInfo.totalPagines = " + paginationInfo.totalPagines);
            if (paginationInfo.totalPagines < maxCasellesPerMostrar) {
                start = 0;
                end = paginationInfo.totalPagines;
                showPoints = false;
            }
            else {
                // start = Math.max(0, paginationInfo.paginaActual - Math.floor(maxCasellesPerMostrar / 2));
                // end = Math.min(start + maxCasellesPerMostrar, paginationInfo.totalPagines);
                start = paginationInfo.paginaActual - Math.floor(maxCasellesPerMostrar / 2);
                end = paginationInfo.paginaActual + Math.floor(maxCasellesPerMostrar / 2);
                if (start < 0) {
                    // Passam els numeros negatius a end
                    end = end - start;
                    start = 0;
                }
                else {
                    // Passam els numeros que passen de totalPagines a start
                    if (end > paginationInfo.totalPagines) {
                        start = start - (end - paginationInfo.totalPagines);
                        end = paginationInfo.totalPagines;
                    }
                }
                start = Math.max(0, start);
                end = Math.min(end, paginationInfo.totalPagines);
                showPoints = true;
            }
            console.log("PaginationCarpeta::render() => start = " + start);
            console.log("PaginationCarpeta::render() => end = " + end);
            console.log("PaginationCarpeta::render() => showPoints = " + showPoints);
            //console.log("PaginationCarpeta::render() =>  Functions ...");
            //Si el parametre onClickSelectElementsByPage te valor, existeix una funcio per canviar el nº de elements
            // per tant s'ha de incloure l'element per canviar el nº de elements.
            // Si el parametre elementsByPage te valors, agafarlos, si no, agafar 5, 10 i 25 per defecte.
            //console.log("PaginationCarpeta::render() =>  pagines ...");
            var pagines = [];
            // First |<
            if (showPoints && start != 0) {
                pagines.push(React$1.createElement(PaginationItemCarpeta, { value: 0, onClick: onClick, title: t("pagination.primerapagina") },
                    React$1.createElement("div", { style: PaginationCarpeta.ROTATE_LEFT_STYLE }, " \u22BC")));
            }
            // Retrocedir <<
            var retrocedir = Math.floor(start / 2);
            var retrocedirTitle = t("pagination.retrocedir", { pagina: retrocedir });
            if (showPoints && start != 0) {
                pagines.push(React$1.createElement(PaginationItemCarpeta, { key: -1, value: retrocedir, title: retrocedirTitle, onClick: onClick }, "\u226A"));
            }
            // Previous <
            if (start != 0) {
                pagines.push(React$1.createElement(PaginationItemCarpeta, { value: Math.max(0, paginationInfo.paginaActual - 1), title: t("pagination.anterior"), onClick: onClick }, "<"));
            }
            // ... a l'esquerra
            /*
            if (showPoints && start != 0) {
              pagines.push(
                <Item key={-1} value={retrocedir} title={retrocedirTitle} onClick={onClick}>
                  {"…"}
                </Item>
              );
            }
            */
            // All pagination numbers
            for (var i_1 = start; i_1 < end; i_1++) {
                if (i_1 >= 0 && i_1 <= paginationInfo.totalPagines) {
                    pagines.push(React$1.createElement(PaginationItemCarpeta, { key: i_1, value: i_1, onClick: onClick, active: paginationInfo.paginaActual == i_1 }, "" + (i_1 + 1)));
                }
            }
            // ... a la dreta
            var avancar = end + Math.floor((paginationInfo.totalPagines - end) / 2);
            var avancarTitle = t("pagination.avancar", { pagina: avancar });
            /*
            if (showPoints && end != paginationInfo.totalPagines) {
              pagines.push(
                <Item key={-2} onClick={onClick} value={avancar} title={avancarTitle}>
                  {"…"}
                </Item>
              );
            }
            */
            // Seguent >
            if (end != paginationInfo.totalPagines) {
                pagines.push(React$1.createElement(PaginationItemCarpeta, { value: Math.min(paginationInfo.paginaActual + 1, paginationInfo.totalPagines - 1), onClick: onClick, title: t("pagination.seguent") }, ">"));
            }
            // Avançar >>
            if (showPoints && end != paginationInfo.totalPagines) {
                pagines.push(React$1.createElement(PaginationItemCarpeta, { key: -2, onClick: onClick, value: avancar, title: avancarTitle }, "\u226B"));
            }
            // Last  >|
            if (showPoints && end != paginationInfo.totalPagines) {
                pagines.push(React$1.createElement(PaginationItemCarpeta, { value: paginationInfo.totalPagines - 1, onClick: onClick, title: t("pagination.darrerapagina") },
                    React$1.createElement("div", { style: PaginationCarpeta.ROTATE_RIGHT_STYLE }, "\u22BC")));
            }
            //Options per numero de elements a pintar per pagina
            //console.log("PaginationCarpeta::render() =>  onClickSelectElementsByPage ...");
            var selectElementsByPage = this.props.selectElementsByPage;
            if (selectElementsByPage == undefined) {
                selectElementsByPage = RenderPaginationTable$1.DEFAULT_SELECT_ELEMENTS_BY_PAGE;
            }
            if (selectElementsByPage != null) {
                var numElementOptions = [];
                //this.props.paginationInfo.elementsByPage.map((element) => numElementOptions.push(<option>{element}</option>));
                /*for (var i in this.props.paginationInfo.elementsByPage) {
                console.log("XYZ ZZZ Assignant valor numElements.");
                numElementOptions.push(
                  <option key={i}>{this.props.paginationInfo.elementsByPage[i]}</option>
                );
              }
              */
                for (var i = 0; i < selectElementsByPage.length; i++) {
                    numElementOptions.push(React$1.createElement("option", { key: i, value: selectElementsByPage[i] }, selectElementsByPage[i]));
                }
                numElements = (React$1.createElement("div", { className: "pagination", style: {
                        float: "none",
                        alignItems: "center",
                        justifyContent: "center",
                        marginRight: "10px",
                    } },
                    this.props.i18n.t("elementPerPaginaMostra"),
                    " \u00A0",
                    React$1.createElement("select", { defaultValue: paginationInfo.elementsPerPagina, onChange: function (e) {
                            if (onClickSelectElementsByPage) {
                                onClickSelectElementsByPage(e.target.value);
                            }
                        } }, numElementOptions),
                    "\u00A0",
                    this.props.i18n.t("elementPerPaginaElements")));
            }
            pagination = (React$1.createElement("nav", { "aria-label": "Page navigation" },
                React$1.createElement("ul", { key: "pn_1", className: "pagination", style: {
                        float: "none",
                        alignItems: "center",
                        justifyContent: "center",
                    } }, pagines)));
        }
        if (isMobileOnly_1) {
            if (onClickSelectElementsByPage) {
                return (React$1.createElement("center", null,
                    React$1.createElement("div", { className: "container-fluid mt-1" }, message),
                    React$1.createElement("br", null),
                    React$1.createElement("div", { className: "container-fluid mt-1" }, pagination),
                    React$1.createElement("br", null),
                    React$1.createElement("div", { className: "container-fluid mt-1" }, numElements)));
            }
            else {
                return (React$1.createElement("center", null,
                    message,
                    React$1.createElement("br", null),
                    React$1.createElement("div", { className: "pagination justify-content-center" }, pagination)));
            }
        }
        else {
            if (onClickSelectElementsByPage) {
                return (React$1.createElement(React$1.Fragment, null,
                    React$1.createElement("div", { className: "text-nowrap" },
                        React$1.createElement("div", { className: "row d-flex" },
                            React$1.createElement("div", { className: "col", style: { marginRight: "0px", marginLeft: "0px", paddingLeft: "20px" } }, message),
                            React$1.createElement("div", { className: "pagination col justify-content-center", style: { marginRight: "0px", marginLeft: "0px" } }, pagination),
                            React$1.createElement("div", { className: "pagination col justify-content-end", style: { marginRight: "0px", marginLeft: "0px" } }, numElements)))));
            }
            else {
                return (React$1.createElement(React$1.Fragment, null,
                    React$1.createElement("div", { style: { float: "left", marginTop: "9px", width: "50%" } }, message),
                    pagination));
            }
        }
    };
    PaginationCarpeta.ROTATE_RIGHT_STYLE = {
        transform: "rotate(90deg)",
    };
    PaginationCarpeta.ROTATE_LEFT_STYLE = {
        transform: "rotate(-90deg)",
        /* Legacy vendor prefixes that you probably don't need... */
        // Safari
        //-webkit-transform:"rotate(-90deg)",
        /*
          // Firefox
          -moz-transform: "rotate(-90deg)"
        
          // IE
          -ms-transform: "rotate(-90deg)",
        
          // Opera
          -o-transform: "rotate(-90deg)",
        
          // Internet Explorer
          filter: "progid:DXImageTransform.Microsoft.BasicImage(rotation=3)"
          */
    };
    return PaginationCarpeta;
}(React$1.Component));

/**
 * @author anadal
 * @create date 2022-12-28 13:46:48
 * @modify date 2022-12-28 13:46:48
 * @desc [description]
 */
// XYZ ZZZ import { RenderTableData } from "./RenderTableProps";
var RenderPaginationTable = /** @class */ (function (_super) {
    __extends(RenderPaginationTable, _super);
    function RenderPaginationTable(props) {
        var _this = _super.call(this, props) || this;
        _this.childRenderTable = React$1.createRef();
        _this.childRenderPagination = React$1.createRef();
        console.log("RenderPaginationTable() Entra CONSTRUCTOR.");
        //let elementsByPage;
        if (_this.props.selectElementsByPage == undefined) {
            _this.elementsByPage = 5;
        }
        else {
            _this.elementsByPage = _this.props.selectElementsByPage[0];
        }
        console.log("RenderPaginationTable() => elementsByPage= " + _this.elementsByPage);
        _this.page = 0;
        _this.onClickSelectElementsByPage = _this.onClickSelectElementsByPage.bind(_this);
        _this.onClickPagination = _this.onClickPagination.bind(_this);
        _this.loadDataAsync = _this.loadDataAsync.bind(_this);
        _this.returnDataFunction = _this.returnDataFunction.bind(_this);
        return _this;
    }
    RenderPaginationTable.prototype.componentDidMount = function () {
        this.loadDataAsync(this.page, this.elementsByPage);
    };
    RenderPaginationTable.prototype.componentDidUpdate = function () {
        console.log("RenderPaginationTable::componentDidUpdate()  => this.props.selectElementsByPage: " +
            this.props.selectElementsByPage);
        if (this.props.selectElementsByPage == undefined) {
            this.elementsByPage = RenderPaginationTable.DEFAULT_SELECT_ELEMENTS_BY_PAGE[0];
        }
        else {
            this.elementsByPage = this.props.selectElementsByPage[0];
        }
        this.page = 0;
        this.loadDataAsync(this.page, this.elementsByPage);
    };
    RenderPaginationTable.prototype.returnDataFunction = function (data) {
        var _a, _b;
        console.log("RenderPaginationTable::returnDataFunction() => Rebudes noves dades ...");
        (_a = this.childRenderTable.current) === null || _a === void 0 ? void 0 : _a.updateTableData(data);
        (_b = this.childRenderPagination.current) === null || _b === void 0 ? void 0 : _b.updatePaginationInfo(data.paginationInfo);
    };
    RenderPaginationTable.prototype.loadDataAsync = function (page, elementsByPage) {
        var _a, _b;
        console.log("RenderPaginationTable::loadDataAsync() Entra ...");
        // Avisam que estam carregant les dades de la Taula
        (_a = this.childRenderTable.current) === null || _a === void 0 ? void 0 : _a.updateTableData(null);
        // Avisam al la paginació que estam carregant dades
        (_b = this.childRenderPagination.current) === null || _b === void 0 ? void 0 : _b.updatePaginationInfo(null);
        var returnData = {
            page: page,
            elementsByPage: elementsByPage,
            returnDataFunction: this.returnDataFunction,
        };
        this.props.loadPaginatedData(returnData);
        console.log("RenderPaginationTable::loadDataAsync() Surt ...");
    };
    // Aquest mètode mai s'ha de cridar ja que les dades les enviam a RenderTable
    /* XYZ
    loadData(): RenderTableData {
      let msg: string = "Mai s'ha de cridar a la funció loadData()";
      console.error(msg);
  
      let rtd: RenderTableData = {
        tableData: null,
        error: msg,
      };
  
      return rtd;
    }
    */
    RenderPaginationTable.prototype.onClickPagination = function (page) {
        console.log("RenderPaginationTable::onClickPagination(" + page + ") Entra ..." + new Date());
        this.loadDataAsync(page, this.elementsByPage);
        console.log("RenderPaginationTable::onClickPagination(" + page + ") Surt ..." + new Date());
    };
    RenderPaginationTable.prototype.onClickSelectElementsByPage = function (elementsByPage) {
        this.elementsByPage = elementsByPage;
        this.loadDataAsync(0, elementsByPage);
    };
    RenderPaginationTable.prototype.render = function () {
        console.log("RenderPaginationTable::render() Entra ... ");
        console.log("RenderPaginationTable::render() this.props.selectElementsByPage: " + this.props.selectElementsByPage);
        return (React$1.createElement(React$1.Fragment, null,
            React$1.createElement("div", { className: "infoNoMenu" },
                React$1.createElement(RenderTable, __assign({ ref: this.childRenderTable }, this.props))),
            React$1.createElement(PaginationCarpeta, __assign({ ref: this.childRenderPagination }, this.props, { onClickPagination: this.onClickPagination, onClickSelectElementsByPage: this.onClickSelectElementsByPage, selectElementsByPage: this.props.selectElementsByPage }))));
    };
    RenderPaginationTable.DEFAULT_SELECT_ELEMENTS_BY_PAGE = [5, 10, 25];
    return RenderPaginationTable;
}(React$1.Component));
var RenderPaginationTable$1 = RenderPaginationTable;

/**
 * @author anadal
 * @create date 2022-12-28 13:46:48
 * @modify date 2022-12-28 13:46:48
 * @desc [description]
 */
var TemplatePageCarpeta = /** @class */ (function (_super) {
    __extends(TemplatePageCarpeta, _super);
    function TemplatePageCarpeta(props) {
        var _this = _super.call(this, props) || this;
        console.log("  CONSTRUCTOR TemplatePageCarpeta !!!!!");
        initTranslation(_this.props.i18n);
        return _this;
    }
    TemplatePageCarpeta.prototype.render = function () {
        //console.log("TemplatePageCarpeta::render() Start");
        var i18n = this.props.i18n;
        var language = i18n.language;
        var content;
        content = (React$1.createElement(React$1.Fragment, null,
            React$1.createElement("div", { className: "tab-pane fade show active", id: "TemplatePageCarpeta", role: "tabpanel", "aria-labelledby": "home-tab", style: { minHeight: "370px" } },
                this.props.children,
                React$1.createElement("div", { className: "col-md-12 border-0 float-left p-0", id: "botoTornarDadesP", style: { marginTop: "20px" } },
                    React$1.createElement("button", { type: "button", "data-toggle": "modal", onClick: function () {
                            window.location.href = sessionStorage.getItem("pagTornar");
                            sessionStorage.setItem("pagTornar", sessionStorage.getItem("contextPath"));
                        }, className: "botoSuport botoTornauApp", tabIndex: 520, "aria-labelledby": "botoTornarDadesP" }, i18n.t("tornar"))))));
        if (!isMobileOnly_1) {
            return (React$1.createElement(React$1.Fragment, null,
                React$1.createElement("div", { className: "infoNoMenu" },
                    React$1.createElement("h2", { className: "titol h2" }, this.props.titles[language]),
                    React$1.createElement("div", { className: "col-md-12 border-0 float-left p-0" },
                        React$1.createElement("p", { className: "lh15" },
                            this.props.subtitles[language],
                            " "),
                        content))));
        }
        else {
            return (React$1.createElement(React$1.Fragment, null,
                React$1.createElement("div", { className: "titolPaginaApp" }, this.props.titles[language]),
                React$1.createElement("div", { className: "infoNoMenu" },
                    React$1.createElement("div", { className: "col-md-12 border-0 float-left p-0" }, content))));
        }
    };
    return TemplatePageCarpeta;
}(React$1.Component));

var CarpetaDatePicker = /** @class */ (function (_super) {
    __extends(CarpetaDatePicker, _super);
    function CarpetaDatePicker(props) {
        var _this = _super.call(this, props) || this;
        console.log("CarpetaDatePicker::CONSTRUCTOR => Inici ");
        _this.onChangeDateCarpetaDatePicker = _this.onChangeDateCarpetaDatePicker.bind(_this);
        _this.canviatIdioma = _this.canviatIdioma.bind(_this);
        _this.props.i18n.on("languageChanged", _this.canviatIdioma);
        var base = Math.floor(Math.random() * 10000000);
        _this.datePickerName = base + _this.props.basename + "DatePicker";
        return _this;
    }
    CarpetaDatePicker.prototype.canviatIdioma = function (lang) {
        console.log("CarpetaDatePicker::canviatIdioma(" + lang + ") ...");
        //@ts-ignore
        $("#" + this.datePickerName).datetimepicker("locale", lang);
        this.forceUpdate();
    };
    CarpetaDatePicker.prototype.componentDidMount = function () {
        //console.log("CarpetaDatePicker::componentDidMount() => Inici ... XXXXXXXXXXXX " + this.props.i18n.language);
        var theName = this.datePickerName;
        //@ts-ignore
        $("#" + theName).datetimepicker({
            format: "DD/MM/YYYY",
            locale: this.props.i18n.language,
        });
        //@ts-ignore
        $("#" + theName).on("change.datetimepicker", this.onChangeDateCarpetaDatePicker);
    };
    CarpetaDatePicker.prototype.onChangeDateCarpetaDatePicker = function (e) {
        //console.log("change event fired   ************************************************** " + new Date().toString());
        console.log("e.date: => " + new Date(e.date));
        //console.log("e.oldDate: => " + new Date(e.oldDate));
        if (this.props.onChangeDate) {
            if (!this.props.onChangeDate(new Date(e.date), new Date(e.oldDate))) ;
        }
    };
    CarpetaDatePicker.prototype.render = function () {
        console.log("CarpetaDatePicker::render() " + this.props.i18n.language);
        var lang = this.props.i18n.language;
        return (React$1.createElement("div", { className: "form-group" },
            isMobileOnly_1 && React$1.createElement("style", { dangerouslySetInnerHTML: { __html: ".bootstrap-datetimepicker-widget { transform:scale(2.5,2.5); transform-origin:top left; }" } }),
            React$1.createElement("div", { className: "input-group date", id: this.datePickerName, "data-target-input": "nearest" },
                React$1.createElement("input", { "data-toggle": "datetimepicker", className: "form-control datetimepicker-input", "data-target": "#" + this.datePickerName, style: { padding: "0px 0px 0px 10px" }, id: this.datePickerName + "Input", readOnly: false, value: this.props.defaultValue.toLocaleString(lang, {
                        year: "numeric",
                        month: "2-digit",
                        day: "2-digit",
                    }) }),
                React$1.createElement("div", { className: "input-group-append", "data-target": "#" + this.datePickerName, "data-toggle": "datetimepicker" },
                    React$1.createElement("div", { className: "input-group-text" },
                        React$1.createElement("i", { className: "fas fa-calendar" }))))));
    };
    return CarpetaDatePicker;
}(React$1.Component));

/**
 * @author anadal
 * @create date 2023-03-20 13:04:34
 * @modify date 2023-03-20 13:04:34
 * @desc [description]
 */
var CarpetaInputText = /** @class */ (function (_super) {
    __extends(CarpetaInputText, _super);
    function CarpetaInputText(props) {
        var _this = _super.call(this, props) || this;
        console.log("CarpetaInputText::CONSTRUCTOR => Inici ");
        _this.onKeyDownInput = _this.onKeyDownInput.bind(_this);
        _this.onKeyUpInput = _this.onKeyUpInput.bind(_this);
        return _this;
    }
    CarpetaInputText.prototype.onKeyDownInput = function (event) {
        if (event.keyCode == 13) {
            event.preventDefault();
            return false;
        }
        return true;
    };
    CarpetaInputText.prototype.onKeyUpInput = function (event) {
        if (event.keyCode == 13) {
            event.preventDefault();
            return false;
        }
        if (this.props.onChangedText) {
            this.props.onChangedText(event.target.value);
        }
        return true;
    };
    CarpetaInputText.prototype.render = function () {
        return (React$1.createElement("input", { placeholder: this.props.placeHolder, maxLength: 25, tabIndex: this.props.tabIndex, type: "text", id: this.props.id, readOnly: false, onKeyDown: this.onKeyDownInput, onKeyUp: this.onKeyUpInput, className: "form-control form-control-sm focusIn font1App", defaultValue: this.props.defaultValue == undefined ? "" : this.props.defaultValue }));
    };
    return CarpetaInputText;
}(React$1.Component));

/**
 * @author anadal
 * @create date 2023-03-20 13:04:34
 * @modify date 2023-03-20 13:04:34
 * @desc [description]
 */
var CarpetaFormulariDeFiltre = /** @class */ (function (_super) {
    __extends(CarpetaFormulariDeFiltre, _super);
    function CarpetaFormulariDeFiltre(props) {
        var _this = _super.call(this, props) || this;
        console.log("CarpetaFormulariDeFiltre::CONSTRUCTOR => Inici ");
        _this.handleSubmitSearcher = _this.handleSubmitSearcher.bind(_this);
        return _this;
    }
    CarpetaFormulariDeFiltre.prototype.handleSubmitSearcher = function (e) {
        if (this.props.handleSubmitSearcher) {
            return this.props.handleSubmitSearcher(e);
        }
        return true;
    };
    CarpetaFormulariDeFiltre.prototype.setError = function () {
    };
    CarpetaFormulariDeFiltre.prototype.render = function () {
        var _this = this;
        return (React$1.createElement("form", { id: "formulariDeFiltre", style: { marginBottom: "20px" } },
            React$1.createElement("div", { style: { width: "95%", paddingLeft: "0px", margin: "0px" }, className: "ampleTotalApp container" },
                React$1.createElement("div", { className: "row" }, this.props.children),
                React$1.createElement("div", { className: "col-md-3 pl-3 row botoFormApp", style: { zIndex: "4" } },
                    React$1.createElement("button", { type: "submit", className: "btn btn-primary carpeta-btn mt-2", onClick: function (e) {
                            _this.handleSubmitSearcher(e);
                        }, tabIndex: 505 }, this.props.i18n.t("enviar"))))));
    };
    return CarpetaFormulariDeFiltre;
}(React$1.Component));

/**
 * @author anadal
 * @create date 2023-03-20 13:04:34
 * @modify date 2023-03-20 13:04:34
 * @desc [description]
 */
var CarpetaFormulariDeFiltreItem = /** @class */ (function (_super) {
    __extends(CarpetaFormulariDeFiltreItem, _super);
    function CarpetaFormulariDeFiltreItem(props) {
        return _super.call(this, props) || this;
    }
    CarpetaFormulariDeFiltreItem.prototype.render = function () {
        return (React$1.createElement("div", { className: "col-xs-12 campFormApp col" },
            React$1.createElement("div", null,
                this.props.label && React$1.createElement("label", { className: "form-label" }, this.props.label),
                this.props.children)));
    };
    return CarpetaFormulariDeFiltreItem;
}(React$1.Component));

export { Button, CarpetaDatePicker, CarpetaFormulariDeFiltre, CarpetaFormulariDeFiltreItem, CarpetaInputText, RenderPaginationTable$1 as RenderPaginationTable, RenderTable, RowType, RowTypeUtils, TemplatePageCarpeta };
//# sourceMappingURL=index.es.js.map
