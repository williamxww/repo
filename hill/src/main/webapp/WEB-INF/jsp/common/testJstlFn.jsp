<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="cf" uri="http://www.bow.com/taglib/common-functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href=" <%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>spring context bean list</title>
<link rel="stylesheet" type="text/css" href="static/css/common.css">
<script type="text/javascript" src="static/js/jquery.min.js"></script>
<script type="text/javascript" src="static/js/zclip/jquery.zclip.min.js" ></script>
<script>
$(function(){
	
	
	$("table tr td").bind("dblclick" , function(){
		copyToClipBoard($(this).attr("title"),$(this));
	});
	
	/* $("table tr td").zclip({
		path:"static/js/zclip/ZeroClipboard.swf",	
		copy:function(){
			return $(this).val();
		}
	}); */
});



//复制到剪切板js代码
function copyToClipBoard(s) {
    //alert(s);
    if (window.clipboardData) {
        window.clipboardData.setData("Text", s);
        alert("已经复制到剪切板！"+ "\n" + s);
    } else if (navigator.userAgent.indexOf("Opera") != -1) {
        window.location = s;
    } else if (window.netscape) {
        try {
            netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
        } catch (e) {
            alert("被浏览器拒绝！\n请在浏览器地址栏输入'about:config'并回车\n然后将'signed.applets.codebase_principal_support'设置为'true'");
        }
        var clip = Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);
        if (!clip)
            return;
        var trans = Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);
        if (!trans)
            return;
        trans.addDataFlavor('text/unicode');
        var str = new Object();
        var len = new Object();
        var str = Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);
        var copytext = s;
        str.data = copytext;
        trans.setTransferData("text/unicode", str, copytext.length * 2);
        var clipid = Components.interfaces.nsIClipboard;
        if (!clip)
            return false;
        clip.setData(trans, null, clipid.kGlobalClipboard);
        alert("已经复制到剪切板！" + "\n" + s)
    }
}
</script>
</head>
<body>
bean names:<br/>
<table class="td-border">
	<c:forEach items="${beanNames}" var="name">
		<tr>
		<td>${name}</td>
		<td>${fn: substring(name,0,5) }</td>
		<td title="${name}">${fn: substring(name,0,5) }...</td>
		</tr>
	</c:forEach>
</table>
<span>count:${beanCount}</span>

<div>date:${cf:format(date,'yyyy-MM-dd') }</div>
<input id="cnt" value="test1" />
<button id="btn" >copy</button>
</body>
</html>