<!--?xml version="1.0" encoding="utf-8"?-->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en-US"><head>
<meta http-equiv="content-type" content="text/html; charset=windows-1252">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Transform.java</title>
<script language="JavaScript" type="text/javascript">
//<![CDATA[
var urlParam; 
var codeSampleURL;
var codeSampleName;
var fileType;

function getCodeSampleUrl() {
 var queryStrNV = document.location.search.split("=");
 urlParam = decodeURI(queryStrNV[1]);
 var origURL = urlParam;
 
  if (!beginsWith("http://docs.oracle.com", urlParam) && 
      !beginsWith("https://docs.oracle.com", urlParam))
  {
     urlParam = "";
     fileType = " java" + " gutter: false;"
     return;
  } 
 
 
 if ((!endsWith(".jnlp", urlParam)) && (!endsWith(".css", urlParam)) && (!endsWith(".java", urlParam))) {
     urlParam = "";
     fileType = " java" + " gutter: false;"
     return;
 }
 
 urlParam = removeBadChrs(urlParam);
 if (urlParam == origURL) {
     codeSampleURL = urlParam;
 }
 
 codeSampleName = urlParam.split("/").pop();
 fileType = codeSampleName.split(".").pop();
 if (fileType == "jnlp") {
     fileType = " xml";
 } else if (fileType == "css") { 
     fileType = " css";
 } else {
     fileType = " java";
 }
 fileType = fileType + " gutter: false;"
 document.title = codeSampleName;
 return codeSampleURL;
}

// new prototype defintion
document.include = function () {
 if ('undefined' == typeof(codeSampleURL)) return false;
 var p,rnd;
 if (document.all){
   // For IE, create an ActiveX Object instance
   p = new ActiveXObject("Microsoft.XMLHTTP");
 }
 else {
   // For mozilla, create an instance of XMLHttpRequest.
   p = new XMLHttpRequest();
 }
 // Prevent browsers from caching the included page
 // by appending a random  number (optional)
 rnd = Math.random().toString().substring(2);
 codeSampleURL = codeSampleURL.indexOf('?')>-1 ? codeSampleURL+'&rnd='+rnd : codeSampleURL+'?rnd='+rnd;
 // Open the codeSampleURL and write out the response
 p.open("GET",codeSampleURL,false);
 p.send(null);
 var code =  encodeSpecialChars(p.responseText);
 document.write( code );
}

var HTML_ESCAPE_CHARS = [["&", "&amp;"], ["<", "&lt;"], [">", "&gt;"]];
function encodeSpecialChars(code) {
    var re;
    for (i=0; i <3; i++) {
        re = new RegExp(HTML_ESCAPE_CHARS[i][0], "g");
        code = code.replace(re, HTML_ESCAPE_CHARS[i][1]); 
    }
    return code;
}

function beginsWith(partStr, fullStr) {
    return (fullStr.substr(0, partStr.length) == partStr);
}

function endsWith(partStr, fullStr) {    
    var lastIndex = fullStr.lastIndexOf(partStr);
    return (lastIndex != -1) && (lastIndex + partStr.length == fullStr.length);
}

function removeBadChrs(str) {    
    str = str.replace(/\<|\>|\"|\'|\%|\;|\(|\)|\&|\+|\-|\[|\]/g,""); 
    return str;
} 

//]]>
</script>

<script type="text/javascript" src="Transform_files/shCore.js">
</script>

<script type="text/javascript" src="Transform_files/shBrushJava.js">
</script>

<script type="text/javascript" src="Transform_files/shBrushXml.js">
</script>

<script type="text/javascript" src="Transform_files/shBrushCss.js">
</script>

<link type="text/css" rel="stylesheet" href="Transform_files/shCoreDefault.css">
<script type="text/javascript">
SyntaxHighlighter.all();
</script>

<style type="text/css">
/*<![CDATA[*/
/* styles specific to this page */

.syntaxhighlighter { overflow-y: hidden !important; }

.homepagediv {
float: right;
margin: 0px 15px 10px 3em;
}
.clear {
clear:both;
}
/* styles specific to this page end */
/* other standard tutorial styles start */
body {
        margin-left:10px;
        margin-right:10px;
        line-height: 1.5;
        FONT-FAMILY: Arial, Helvetica, sans-serif; 
        font-size: 0.8em;
    }
    
    a:link{text-decoration:none; color:#09569d;}
    a:visited{text-decoration:none; color: #3a87cf;}
    a:hover{text-decoration:underline; }
        
    code{
        font-size:12px;
        font-family:Monaco,Courier,"Courier New";
    }
    
    .header-container {
        background-color: #fff;
        border-bottom: 1px solid #C1CFDA;
        -webkit-box-shadow: 0 2px 2px rgba(117, 163, 231, 0.1);
        box-shadow: 0 2px 2px rgba(117, 163, 231, 0.1);
    }
    
    .bookwrapper {
        width: auto;
        margin: auto;
    }
    
    .clearfix {
    }
    
    .clearfloat {
        clear: both;
        overflow: auto;
        height: 0px;
        font-size: 1px;
        line-height: 0px;
    }
    
    #brandProdName {
        width: auto;
        height: auto;
    }
    
    #logocover {
        display: block;
        background: transparent url(images/oracle-java-logo.png) 0px 0px no-repeat;
        height: 50px;
        width: 229px;
        float: left;
    }
    
    #productName {
        font-size: 16px;
        position: relative;
        top: 19px;
        padding-left: 3px;
        color: #457798;
        white-space: nowrap;
        width: 340px;
    }



    div#TopBar_bl {        
        width: 100%;
        height: 60px;
    }
    div#TopBar_br {
        width: 100%;
        height: 60px;
    }
    div#TopBar_tl {
        margin-left: -110px;
        margin-right: -100px;
		align: left;
        height: 60px;
    }
    div#TopBar_tr {
        width: 100%;
        height: 60px;
    }
    div#TopBar {
        min-width:700px;
        padding:25px 100px 10px;
        margin-bottom:25px;
        clear:both;
        
        border-bottom:1px solid #d2dde5;
        border-radius: 3px;
    
        background:#efefef; /* Old browsers */
        /* IE9 SVG, needs conditional override of 'filter' to 'none' */
        background: url(data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiA/Pgo8c3ZnIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgd2lkdGg9IjEwMCUiIGhlaWdodD0iMTAwJSIgdmlld0JveD0iMCAwIDEgMSIgcHJlc2VydmVBc3BlY3RSYXRpbz0ibm9uZSI+CiAgPGxpbmVhckdyYWRpZW50IGlkPSJncmFkLXVjZ2ctZ2VuZXJhdGVkIiBncmFkaWVudFVuaXRzPSJ1c2VyU3BhY2VPblVzZSIgeDE9IjAlIiB5MT0iMCUiIHgyPSIwJSIgeTI9IjEwMCUiPgogICAgPHN0b3Agb2Zmc2V0PSIwJSIgc3RvcC1jb2xvcj0iI2ZmZmZmZiIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjEwMCUiIHN0b3AtY29sb3I9IiNlMmVmZjkiIHN0b3Atb3BhY2l0eT0iMSIvPgogIDwvbGluZWFyR3JhZGllbnQ+CiAgPHJlY3QgeD0iMCIgeT0iMCIgd2lkdGg9IjEiIGhlaWdodD0iMSIgZmlsbD0idXJsKCNncmFkLXVjZ2ctZ2VuZXJhdGVkKSIgLz4KPC9zdmc+);
        background: -moz-linear-gradient(top,  #ffffff 0%, #e2eff9 100%); /* FF3.6+ */
        background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#ffffff), color-stop(100%,#e2eff9)); /* Chrome,Safari4+ */
        background: -webkit-linear-gradient(top,  #ffffff 0%,#e2eff9 100%); /* Chrome10+,Safari5.1+ */
        background: -o-linear-gradient(top,  #ffffff 0%,#e2eff9 100%); /* Opera 11.10+ */
        background: -ms-linear-gradient(top,  #ffffff 0%,#e2eff9 100%); /* IE10+ */
        background: linear-gradient(to bottom,  #ffffff 0%,#e2eff9 100%); /* W3C */
        filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#ffffff', endColorstr='#e2eff9',GradientType=0 ); /* IE6-8 */
        
    }
    #TopBar_left {
        line-height: 14px;
        position: absolute;
        padding-top: 30px;
        padding-right: 30px;
        padding-left: 30px;
        text-align: left;
        font: 13px/20px Arial, Helvetica, sans-serif;
        font-weight: bold;
        font-size: 20px;
        color: #333;
    }
    
    #TopBar_right {
        line-height: 12px;
        float: right;
        padding-top: 10px;
        padding-right: 30px;
        text-align: left;
    }
    
    #TopBar_right a {
        font-size: 10px;
        margin: 3px;
        padding: 0;
    }
   
    div#PageTitle {          
        margin: 0 5px 0.5em 0;
        color: #F90000;
        float: left;
    }

   
    div#Footer {
        padding-top: 10px;
        padding-left: 10px;
        margin-right: 10px;   
    }
  
    .footertext {
        font-size: 11px;
        color: #454545
    }
    
    div#TagNotes {
        margin-top: 10px;
        color: #35556B;
    }
    @media print {
        span#TagNotes a:visited, span#TagNotes a:link {
            text-decoration: none;
        }
    }
    div#TutBody {
        margin: 10px 3em 10px 3em;
    }
   
    
    h1, h2, h3, h4, h5 {
        color: #333;
        
    }

    h2 {
        font-weight: bold;
        font-size: 17px;
    }

    h3 {
        font-weight: bold;
        font-size: 14px;
    }

    h4 {
        font-size: 15px;
    }

    h5 {
        font-size: 12px;
    }

    .thickhr {
        color:#000000;
        background-color:#000000;
        height:3px;
        border:none;
    }
/*]]>*/
</style>
</head>
<body>
<!-- tutorial boiler plate start -->
<div class="header-container">
    <div class="bookwrapper  clearfix">       
        <div id="brandProdName">
            <div id="logocover"></div>
            <div id="productName">Documentation</div>
        </div> 
        <br class="clearfloat">
    </div>
</div>
<div id="TopBar">
<div id="TopBar_tr">
<div id="TopBar_tl">
<div id="TopBar_br">
<div id="TopBar_bl">
<div id="TopBar_left">
The Java™ Tutorials
</div>
<div id="TopBar_right"><a href="https://docs.oracle.com/javase/tutorial/search.html" target="_top">Search</a><br>
<a href="https://docs.oracle.com/javase/feedback.html">Feedback</a></div>
</div>
</div>
</div>
</div>
</div>
<!-- tutorial boiler plate end -->

<div id="PageTitle">
<h1>Java Tutorials Code Sample – 

<script language="javascript" type="text/javascript">
    getCodeSampleUrl();
    document.write(codeSampleName);
</script>Transform.java
</h1>
</div>

<div class="homepagediv">
<p><a href="https://docs.oracle.com/javase/tutorial/index.html">Home Page</a></p>
</div>
<div class="clear"></div>

<div id="TutBody">
<script language="javascript" type="text/javascript">
/* <![CDATA[ */

    document.write("<p><a href='" + codeSampleURL + "'>Download<\/a> this sample.<\/p>");
    document.write("<pre class='brush:" + fileType + "; '>");
    document.include();
    document.write("<\/pre>");
/* ]]> */  
</script><p><a href="https://docs.oracle.com/javase/tutorial/2d/advanced/examples/Transform.java">Download</a> this sample.</p><div><div id="highlighter_975758" class="syntaxhighlighter nogutter  java"><div class="toolbar"><span><a href="#" class="toolbar_item command_help help">?</a></span></div><table cellspacing="0" cellpadding="0" border="0"><tbody><tr><td class="code"><div class="container"><div class="line number1 index0 alt2"><code class="java comments">/*</code></div><div class="line number2 index1 alt1"><code class="java spaces">&nbsp;</code><code class="java comments">* Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.</code></div><div class="line number3 index2 alt2"><code class="java spaces">&nbsp;</code><code class="java comments">*</code></div><div class="line number4 index3 alt1"><code class="java spaces">&nbsp;</code><code class="java comments">* Redistribution and use in source and binary forms, with or without</code></div><div class="line number5 index4 alt2"><code class="java spaces">&nbsp;</code><code class="java comments">* modification, are permitted provided that the following conditions</code></div><div class="line number6 index5 alt1"><code class="java spaces">&nbsp;</code><code class="java comments">* are met:</code></div><div class="line number7 index6 alt2"><code class="java spaces">&nbsp;</code><code class="java comments">*</code></div><div class="line number8 index7 alt1"><code class="java spaces">&nbsp;</code><code class="java comments">*&nbsp;&nbsp; - Redistributions of source code must retain the above copyright</code></div><div class="line number9 index8 alt2"><code class="java spaces">&nbsp;</code><code class="java comments">*&nbsp;&nbsp;&nbsp;&nbsp; notice, this list of conditions and the following disclaimer.</code></div><div class="line number10 index9 alt1"><code class="java spaces">&nbsp;</code><code class="java comments">*</code></div><div class="line number11 index10 alt2"><code class="java spaces">&nbsp;</code><code class="java comments">*&nbsp;&nbsp; - Redistributions in binary form must reproduce the above copyright</code></div><div class="line number12 index11 alt1"><code class="java spaces">&nbsp;</code><code class="java comments">*&nbsp;&nbsp;&nbsp;&nbsp; notice, this list of conditions and the following disclaimer in the</code></div><div class="line number13 index12 alt2"><code class="java spaces">&nbsp;</code><code class="java comments">*&nbsp;&nbsp;&nbsp;&nbsp; documentation and/or other materials provided with the distribution.</code></div><div class="line number14 index13 alt1"><code class="java spaces">&nbsp;</code><code class="java comments">*</code></div><div class="line number15 index14 alt2"><code class="java spaces">&nbsp;</code><code class="java comments">*&nbsp;&nbsp; - Neither the name of Oracle or the names of its</code></div><div class="line number16 index15 alt1"><code class="java spaces">&nbsp;</code><code class="java comments">*&nbsp;&nbsp;&nbsp;&nbsp; contributors may be used to endorse or promote products derived</code></div><div class="line number17 index16 alt2"><code class="java spaces">&nbsp;</code><code class="java comments">*&nbsp;&nbsp;&nbsp;&nbsp; from this software without specific prior written permission.</code></div><div class="line number18 index17 alt1"><code class="java spaces">&nbsp;</code><code class="java comments">*</code></div><div class="line number19 index18 alt2"><code class="java spaces">&nbsp;</code><code class="java comments">* THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS</code></div><div class="line number20 index19 alt1"><code class="java spaces">&nbsp;</code><code class="java comments">* IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,</code></div><div class="line number21 index20 alt2"><code class="java spaces">&nbsp;</code><code class="java comments">* THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR</code></div><div class="line number22 index21 alt1"><code class="java spaces">&nbsp;</code><code class="java comments">* PURPOSE ARE DISCLAIMED.&nbsp; IN NO EVENT SHALL THE COPYRIGHT OWNER OR</code></div><div class="line number23 index22 alt2"><code class="java spaces">&nbsp;</code><code class="java comments">* CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,</code></div><div class="line number24 index23 alt1"><code class="java spaces">&nbsp;</code><code class="java comments">* EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,</code></div><div class="line number25 index24 alt2"><code class="java spaces">&nbsp;</code><code class="java comments">* PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR</code></div><div class="line number26 index25 alt1"><code class="java spaces">&nbsp;</code><code class="java comments">* PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF</code></div><div class="line number27 index26 alt2"><code class="java spaces">&nbsp;</code><code class="java comments">* LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING</code></div><div class="line number28 index27 alt1"><code class="java spaces">&nbsp;</code><code class="java comments">* NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS</code></div><div class="line number29 index28 alt2"><code class="java spaces">&nbsp;</code><code class="java comments">* SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.</code></div><div class="line number30 index29 alt1"><code class="java spaces">&nbsp;</code><code class="java comments">*/</code></div><div class="line number31 index30 alt2">&nbsp;</div><div class="line number32 index31 alt1">&nbsp;</div><div class="line number33 index32 alt2"><code class="java keyword">import</code> <code class="java plain">java.lang.Integer;</code></div><div class="line number34 index33 alt1"><code class="java keyword">import</code> <code class="java plain">java.awt.*;</code></div><div class="line number35 index34 alt2"><code class="java keyword">import</code> <code class="java plain">java.awt.event.*;</code></div><div class="line number36 index35 alt1"><code class="java keyword">import</code> <code class="java plain">java.awt.font.*;</code></div><div class="line number37 index36 alt2"><code class="java keyword">import</code> <code class="java plain">java.awt.geom.*;</code></div><div class="line number38 index37 alt1"><code class="java keyword">import</code> <code class="java plain">java.awt.image.*;</code></div><div class="line number39 index38 alt2"><code class="java keyword">import</code> <code class="java plain">java.awt.event.ItemListener;</code></div><div class="line number40 index39 alt1"><code class="java keyword">import</code> <code class="java plain">java.awt.event.ItemEvent;</code></div><div class="line number41 index40 alt2"><code class="java keyword">import</code> <code class="java plain">java.awt.event.ActionListener;</code></div><div class="line number42 index41 alt1"><code class="java keyword">import</code> <code class="java plain">java.awt.event.ActionEvent;</code></div><div class="line number43 index42 alt2"><code class="java keyword">import</code> <code class="java plain">javax.swing.*;</code></div><div class="line number44 index43 alt1">&nbsp;</div><div class="line number45 index44 alt2"><code class="java comments">/*</code></div><div class="line number46 index45 alt1"><code class="java spaces">&nbsp;</code><code class="java comments">* This applet renders a shape, selected by the user, with a paint,stroke, and rendering method,</code></div><div class="line number47 index46 alt2"><code class="java spaces">&nbsp;</code><code class="java comments">* also selected by the user.</code></div><div class="line number48 index47 alt1"><code class="java comments">*/</code></div><div class="line number49 index48 alt2">&nbsp;</div><div class="line number50 index49 alt1"><code class="java keyword">public</code> <code class="java keyword">class</code> <code class="java plain">Transform </code><code class="java keyword">extends</code> <code class="java plain">JApplet </code><code class="java keyword">implements</code> <code class="java plain">ItemListener,</code></div><div class="line number51 index50 alt2"><code class="java plain">ActionListener {</code></div><div class="line number52 index51 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">JLabel primLabel, lineLabel, paintLabel, transLabel, strokeLabel;</code></div><div class="line number53 index52 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">TransPanel display;</code></div><div class="line number54 index53 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java keyword">static</code> <code class="java plain">JComboBox primitive, line, paint, trans, stroke;</code></div><div class="line number55 index54 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">JButton redraw;</code></div><div class="line number56 index55 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java keyword">public</code> <code class="java keyword">static</code> <code class="java keyword">boolean</code> <code class="java plain">no2D = </code><code class="java keyword">false</code><code class="java plain">;</code></div><div class="line number57 index56 alt2">&nbsp;</div><div class="line number58 index57 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java keyword">public</code> <code class="java keyword">void</code> <code class="java plain">init() {</code></div><div class="line number59 index58 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">GridBagLayout layOut = </code><code class="java keyword">new</code> <code class="java plain">GridBagLayout();</code></div><div class="line number60 index59 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">getContentPane().setLayout(layOut);</code></div><div class="line number61 index60 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">GridBagConstraints c = </code><code class="java keyword">new</code> <code class="java plain">GridBagConstraints();</code></div><div class="line number62 index61 alt1">&nbsp;</div><div class="line number63 index62 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">c.weightx = </code><code class="java value">1.0</code><code class="java plain">;</code></div><div class="line number64 index63 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">c.fill = GridBagConstraints.BOTH;</code></div><div class="line number65 index64 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">primLabel = </code><code class="java keyword">new</code> <code class="java plain">JLabel();</code></div><div class="line number66 index65 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">primLabel.setText(</code><code class="java string">"Primitive"</code><code class="java plain">);</code></div><div class="line number67 index66 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">Font newFont = getFont().deriveFont(</code><code class="java value">1</code><code class="java plain">);</code></div><div class="line number68 index67 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">primLabel.setFont(newFont);</code></div><div class="line number69 index68 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">primLabel.setHorizontalAlignment(JLabel.CENTER);</code></div><div class="line number70 index69 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">layOut.setConstraints(primLabel, c);</code></div><div class="line number71 index70 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">getContentPane().add(primLabel);</code></div><div class="line number72 index71 alt1">&nbsp;</div><div class="line number73 index72 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">lineLabel = </code><code class="java keyword">new</code> <code class="java plain">JLabel();</code></div><div class="line number74 index73 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">lineLabel.setText(</code><code class="java string">"Lines"</code><code class="java plain">);</code></div><div class="line number75 index74 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">lineLabel.setFont(newFont);</code></div><div class="line number76 index75 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">lineLabel.setHorizontalAlignment(JLabel.CENTER);</code></div><div class="line number77 index76 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">layOut.setConstraints(lineLabel, c);</code></div><div class="line number78 index77 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">getContentPane().add(lineLabel);</code></div><div class="line number79 index78 alt2">&nbsp;</div><div class="line number80 index79 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">paintLabel = </code><code class="java keyword">new</code> <code class="java plain">JLabel();</code></div><div class="line number81 index80 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">paintLabel.setText(</code><code class="java string">"Paints"</code><code class="java plain">);</code></div><div class="line number82 index81 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">paintLabel.setFont(newFont);</code></div><div class="line number83 index82 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">paintLabel.setHorizontalAlignment(JLabel.CENTER);</code></div><div class="line number84 index83 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">layOut.setConstraints(paintLabel, c);</code></div><div class="line number85 index84 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">getContentPane().add(paintLabel);</code></div><div class="line number86 index85 alt1">&nbsp;</div><div class="line number87 index86 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">c.gridwidth = GridBagConstraints.RELATIVE;</code></div><div class="line number88 index87 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">transLabel = </code><code class="java keyword">new</code> <code class="java plain">JLabel();&nbsp; </code></div><div class="line number89 index88 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">transLabel.setText(</code><code class="java string">"Transforms"</code><code class="java plain">);</code></div><div class="line number90 index89 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">transLabel.setFont(newFont);</code></div><div class="line number91 index90 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">transLabel.setHorizontalAlignment(JLabel.CENTER);</code></div><div class="line number92 index91 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">layOut.setConstraints(transLabel, c);</code></div><div class="line number93 index92 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">getContentPane().add(transLabel);</code></div><div class="line number94 index93 alt1">&nbsp;</div><div class="line number95 index94 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">c.gridwidth = GridBagConstraints.REMAINDER;</code></div><div class="line number96 index95 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">strokeLabel = </code><code class="java keyword">new</code> <code class="java plain">JLabel();</code></div><div class="line number97 index96 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">strokeLabel.setText(</code><code class="java string">"Rendering"</code><code class="java plain">);</code></div><div class="line number98 index97 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">strokeLabel.setFont(newFont);</code></div><div class="line number99 index98 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">strokeLabel.setHorizontalAlignment(JLabel.CENTER); </code></div><div class="line number100 index99 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">layOut.setConstraints(strokeLabel, c);</code></div><div class="line number101 index100 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">getContentPane().add(strokeLabel);</code></div><div class="line number102 index101 alt1">&nbsp;</div><div class="line number103 index102 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">GridBagConstraints ls = </code><code class="java keyword">new</code> <code class="java plain">GridBagConstraints();</code></div><div class="line number104 index103 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">ls.weightx = </code><code class="java value">1.0</code><code class="java plain">;</code></div><div class="line number105 index104 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">ls.fill = GridBagConstraints.BOTH;</code></div><div class="line number106 index105 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">primitive = </code><code class="java keyword">new</code> <code class="java plain">JComboBox( </code><code class="java keyword">new</code> <code class="java plain">Object []{</code></div><div class="line number107 index106 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java string">"rectangle"</code><code class="java plain">,</code></div><div class="line number108 index107 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java string">"ellipse"</code><code class="java plain">,</code></div><div class="line number109 index108 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java string">"text"</code><code class="java plain">});</code></div><div class="line number110 index109 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">primitive.addItemListener(</code><code class="java keyword">this</code><code class="java plain">);</code></div><div class="line number111 index110 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">newFont = newFont.deriveFont(</code><code class="java value">0</code><code class="java plain">, </code><code class="java value">14</code><code class="java plain">.0f);</code></div><div class="line number112 index111 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">primitive.setFont(newFont);</code></div><div class="line number113 index112 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">layOut.setConstraints(primitive, ls);</code></div><div class="line number114 index113 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">getContentPane().add(primitive);</code></div><div class="line number115 index114 alt2">&nbsp;</div><div class="line number116 index115 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">line = </code><code class="java keyword">new</code> <code class="java plain">JComboBox( </code><code class="java keyword">new</code> <code class="java plain">Object []{&nbsp; </code></div><div class="line number117 index116 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java string">"thin"</code><code class="java plain">,</code></div><div class="line number118 index117 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java string">"thick"</code><code class="java plain">,</code></div><div class="line number119 index118 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java string">"dashed"</code><code class="java plain">});</code></div><div class="line number120 index119 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">line.addItemListener(</code><code class="java keyword">this</code><code class="java plain">);</code></div><div class="line number121 index120 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">line.setFont(newFont);</code></div><div class="line number122 index121 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">layOut.setConstraints(line, ls);</code></div><div class="line number123 index122 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">getContentPane().add(line);</code></div><div class="line number124 index123 alt1">&nbsp;</div><div class="line number125 index124 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">paint = </code><code class="java keyword">new</code> <code class="java plain">JComboBox( </code><code class="java keyword">new</code> <code class="java plain">Object[]{</code></div><div class="line number126 index125 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java string">"solid"</code><code class="java plain">,</code></div><div class="line number127 index126 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java string">"gradient"</code><code class="java plain">,</code></div><div class="line number128 index127 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java string">"polka"</code><code class="java plain">});</code></div><div class="line number129 index128 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">paint.addItemListener(</code><code class="java keyword">this</code><code class="java plain">);</code></div><div class="line number130 index129 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">paint.setFont(newFont);</code></div><div class="line number131 index130 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">layOut.setConstraints(paint, ls);</code></div><div class="line number132 index131 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">getContentPane().add(paint);</code></div><div class="line number133 index132 alt2">&nbsp;</div><div class="line number134 index133 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">ls.gridwidth = GridBagConstraints.RELATIVE;</code></div><div class="line number135 index134 alt2">&nbsp;</div><div class="line number136 index135 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">trans = </code><code class="java keyword">new</code> <code class="java plain">JComboBox( </code><code class="java keyword">new</code> <code class="java plain">Object[]{</code></div><div class="line number137 index136 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java string">"Identity"</code><code class="java plain">,</code></div><div class="line number138 index137 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java string">"rotate"</code><code class="java plain">,</code></div><div class="line number139 index138 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java string">"scale"</code><code class="java plain">,</code></div><div class="line number140 index139 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java string">"shear"</code><code class="java plain">});</code></div><div class="line number141 index140 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">trans.addItemListener(</code><code class="java keyword">this</code><code class="java plain">);</code></div><div class="line number142 index141 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">trans.setFont(newFont);</code></div><div class="line number143 index142 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">layOut.setConstraints(trans, ls);</code></div><div class="line number144 index143 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">getContentPane().add(trans);</code></div><div class="line number145 index144 alt2">&nbsp;</div><div class="line number146 index145 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">ls.gridwidth = GridBagConstraints.REMAINDER;</code></div><div class="line number147 index146 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">stroke = </code><code class="java keyword">new</code> <code class="java plain">JComboBox( </code><code class="java keyword">new</code> <code class="java plain">Object[]{</code></div><div class="line number148 index147 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java string">"Stroke"</code><code class="java plain">,</code></div><div class="line number149 index148 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java string">"Fill"</code><code class="java plain">,</code></div><div class="line number150 index149 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java string">"Stroke &amp; Fill"</code><code class="java plain">}); </code></div><div class="line number151 index150 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">stroke.addItemListener(</code><code class="java keyword">this</code><code class="java plain">);</code></div><div class="line number152 index151 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">stroke.setFont(newFont);</code></div><div class="line number153 index152 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">layOut.setConstraints(stroke, ls);</code></div><div class="line number154 index153 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">getContentPane().add(stroke);</code></div><div class="line number155 index154 alt2">&nbsp;</div><div class="line number156 index155 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">GridBagConstraints button = </code><code class="java keyword">new</code> <code class="java plain">GridBagConstraints();</code></div><div class="line number157 index156 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">button.gridwidth = GridBagConstraints.REMAINDER;</code></div><div class="line number158 index157 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">redraw = </code><code class="java keyword">new</code> <code class="java plain">JButton(</code><code class="java string">"Redraw"</code><code class="java plain">);</code></div><div class="line number159 index158 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">redraw.addActionListener(</code><code class="java keyword">this</code><code class="java plain">);</code></div><div class="line number160 index159 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">redraw.setFont(newFont);</code></div><div class="line number161 index160 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">layOut.setConstraints(redraw, button);</code></div><div class="line number162 index161 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">getContentPane().add(redraw);</code></div><div class="line number163 index162 alt2">&nbsp;</div><div class="line number164 index163 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">GridBagConstraints tP = </code><code class="java keyword">new</code> <code class="java plain">GridBagConstraints();</code></div><div class="line number165 index164 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">tP.fill = GridBagConstraints.BOTH;</code></div><div class="line number166 index165 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">tP.weightx = </code><code class="java value">1.0</code><code class="java plain">;</code></div><div class="line number167 index166 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">tP.weighty = </code><code class="java value">1.0</code><code class="java plain">;</code></div><div class="line number168 index167 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">tP.gridwidth = GridBagConstraints.REMAINDER;</code></div><div class="line number169 index168 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">display = </code><code class="java keyword">new</code> <code class="java plain">TransPanel();</code></div><div class="line number170 index169 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">layOut.setConstraints(display, tP);</code></div><div class="line number171 index170 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">display.setBackground(Color.white);</code></div><div class="line number172 index171 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">getContentPane().add(display);</code></div><div class="line number173 index172 alt2">&nbsp;</div><div class="line number174 index173 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">validate();</code></div><div class="line number175 index174 alt2">&nbsp;</div><div class="line number176 index175 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">}</code></div><div class="line number177 index176 alt2">&nbsp;</div><div class="line number178 index177 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java keyword">public</code> <code class="java keyword">void</code> <code class="java plain">itemStateChanged(ItemEvent e){}</code></div><div class="line number179 index178 alt2">&nbsp;</div><div class="line number180 index179 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;</code><code class="java keyword">public</code> <code class="java keyword">void</code> <code class="java plain">actionPerformed(ActionEvent e) {</code></div><div class="line number181 index180 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">display.setTrans(trans.getSelectedIndex());</code></div><div class="line number182 index181 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">display.renderShape();</code></div><div class="line number183 index182 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;</code><code class="java plain">}</code></div><div class="line number184 index183 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code>&nbsp;</div><div class="line number185 index184 alt2">&nbsp;</div><div class="line number186 index185 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java keyword">public</code> <code class="java keyword">static</code> <code class="java keyword">void</code> <code class="java plain">main( String[] argv ) {</code></div><div class="line number187 index186 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java keyword">if</code> <code class="java plain">( argv.length &gt; </code><code class="java value">0</code> <code class="java plain">&amp;&amp; argv[</code><code class="java value">0</code><code class="java plain">].equals( </code><code class="java string">"-no2d"</code> <code class="java plain">) ) {</code></div><div class="line number188 index187 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">Transform.no2D = </code><code class="java keyword">true</code><code class="java plain">;</code></div><div class="line number189 index188 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">}</code></div><div class="line number190 index189 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code>&nbsp;</div><div class="line number191 index190 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">JFrame frame = </code><code class="java keyword">new</code> <code class="java plain">JFrame( </code><code class="java string">"Transform"</code> <code class="java plain">);</code></div><div class="line number192 index191 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">frame.addWindowListener( </code><code class="java keyword">new</code> <code class="java plain">WindowAdapter(){</code></div><div class="line number193 index192 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java keyword">public</code> <code class="java keyword">void</code> <code class="java plain">windowClosing( WindowEvent e ){</code></div><div class="line number194 index193 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">System.exit( </code><code class="java value">0</code> <code class="java plain">);</code></div><div class="line number195 index194 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">}</code></div><div class="line number196 index195 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">});&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </code></div><div class="line number197 index196 alt2">&nbsp;</div><div class="line number198 index197 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">JApplet applet = </code><code class="java keyword">new</code> <code class="java plain">Transform();</code></div><div class="line number199 index198 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">frame.getContentPane().add( BorderLayout.CENTER, applet );</code></div><div class="line number200 index199 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code>&nbsp;</div><div class="line number201 index200 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">applet.init();</code></div><div class="line number202 index201 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code>&nbsp;</div><div class="line number203 index202 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">frame.setSize( </code><code class="java value">550</code><code class="java plain">, </code><code class="java value">400</code> <code class="java plain">);</code></div><div class="line number204 index203 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">frame.setVisible(</code><code class="java keyword">true</code><code class="java plain">);</code></div><div class="line number205 index204 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;</code><code class="java plain">}</code></div><div class="line number206 index205 alt1">&nbsp;</div><div class="line number207 index206 alt2"><code class="java plain">}</code></div><div class="line number208 index207 alt1">&nbsp;</div><div class="line number209 index208 alt2"><code class="java keyword">class</code> <code class="java plain">TransPanel </code><code class="java keyword">extends</code> <code class="java plain">JPanel {</code></div><div class="line number210 index209 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">AffineTransform at = </code><code class="java keyword">new</code> <code class="java plain">AffineTransform();</code></div><div class="line number211 index210 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java keyword">int</code> <code class="java plain">w, h;</code></div><div class="line number212 index211 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">Shape shapes[] = </code><code class="java keyword">new</code> <code class="java plain">Shape[</code><code class="java value">3</code><code class="java plain">];</code></div><div class="line number213 index212 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">BufferedImage bi;</code></div><div class="line number214 index213 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java keyword">boolean</code> <code class="java plain">firstTime = </code><code class="java keyword">true</code><code class="java plain">;</code></div><div class="line number215 index214 alt2">&nbsp;</div><div class="line number216 index215 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java keyword">public</code> <code class="java plain">TransPanel(){</code></div><div class="line number217 index216 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">setBackground(Color.white);</code></div><div class="line number218 index217 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">shapes[</code><code class="java value">0</code><code class="java plain">] = </code><code class="java keyword">new</code> <code class="java plain">Rectangle(</code><code class="java value">0</code><code class="java plain">, </code><code class="java value">0</code><code class="java plain">, </code><code class="java value">100</code><code class="java plain">, </code><code class="java value">100</code><code class="java plain">);</code></div><div class="line number219 index218 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">shapes[</code><code class="java value">1</code><code class="java plain">] = </code><code class="java keyword">new</code> <code class="java plain">Ellipse2D.Double(</code><code class="java value">0.0</code><code class="java plain">, </code><code class="java value">0.0</code><code class="java plain">, </code><code class="java value">100.0</code><code class="java plain">, </code><code class="java value">100.0</code><code class="java plain">);</code></div><div class="line number220 index219 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">TextLayout textTl = </code><code class="java keyword">new</code> <code class="java plain">TextLayout(</code><code class="java string">"Text"</code><code class="java plain">, </code><code class="java keyword">new</code> <code class="java plain">Font(</code><code class="java string">"Helvetica"</code><code class="java plain">, </code><code class="java value">1</code><code class="java plain">, </code><code class="java value">96</code><code class="java plain">), </code><code class="java keyword">new</code> <code class="java plain">FontRenderContext(</code><code class="java keyword">null</code><code class="java plain">, </code><code class="java keyword">false</code><code class="java plain">, </code><code class="java keyword">false</code><code class="java plain">));</code></div><div class="line number221 index220 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">AffineTransform textAt = </code><code class="java keyword">new</code> <code class="java plain">AffineTransform();</code></div><div class="line number222 index221 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">textAt.translate(</code><code class="java value">0</code><code class="java plain">, (</code><code class="java keyword">float</code><code class="java plain">)textTl.getBounds().getHeight());</code></div><div class="line number223 index222 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">shapes[</code><code class="java value">2</code><code class="java plain">] = textTl.getOutline(textAt);</code></div><div class="line number224 index223 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">}</code></div><div class="line number225 index224 alt2">&nbsp;</div><div class="line number226 index225 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java keyword">public</code> <code class="java keyword">void</code> <code class="java plain">setTrans(</code><code class="java keyword">int</code> <code class="java plain">transIndex) {</code></div><div class="line number227 index226 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java comments">// Sets the AffineTransform.</code></div><div class="line number228 index227 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java keyword">switch</code> <code class="java plain">( transIndex ) {</code></div><div class="line number229 index228 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java keyword">case</code> <code class="java value">0</code> <code class="java plain">: at.setToIdentity();</code></div><div class="line number230 index229 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">at.translate(w/</code><code class="java value">2</code><code class="java plain">, h/</code><code class="java value">2</code><code class="java plain">); </code><code class="java keyword">break</code><code class="java plain">;</code></div><div class="line number231 index230 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java keyword">case</code> <code class="java value">1</code> <code class="java plain">: at.rotate(Math.toRadians(</code><code class="java value">45</code><code class="java plain">)); </code><code class="java keyword">break</code><code class="java plain">;</code></div><div class="line number232 index231 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java keyword">case</code> <code class="java value">2</code> <code class="java plain">: at.scale(</code><code class="java value">0.5</code><code class="java plain">, </code><code class="java value">0.5</code><code class="java plain">); </code><code class="java keyword">break</code><code class="java plain">;</code></div><div class="line number233 index232 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java keyword">case</code> <code class="java value">3</code> <code class="java plain">: at.shear(</code><code class="java value">0.5</code><code class="java plain">, </code><code class="java value">0.0</code><code class="java plain">); </code><code class="java keyword">break</code><code class="java plain">;</code></div><div class="line number234 index233 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">}</code></div><div class="line number235 index234 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">}</code></div><div class="line number236 index235 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code>&nbsp;</div><div class="line number237 index236 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java keyword">public</code> <code class="java keyword">void</code> <code class="java plain">renderShape() {</code></div><div class="line number238 index237 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">repaint();</code></div><div class="line number239 index238 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">}</code></div><div class="line number240 index239 alt1">&nbsp;</div><div class="line number241 index240 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java keyword">public</code> <code class="java keyword">void</code> <code class="java plain">paintComponent(Graphics g) {</code></div><div class="line number242 index241 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java keyword">super</code><code class="java plain">.paintComponent(g);</code></div><div class="line number243 index242 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code>&nbsp;</div><div class="line number244 index243 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java keyword">if</code> <code class="java plain">( !Transform.no2D ) {</code></div><div class="line number245 index244 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">Graphics2D g2 = (Graphics2D) g;</code></div><div class="line number246 index245 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">Dimension d = getSize();</code></div><div class="line number247 index246 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">w = d.width;</code></div><div class="line number248 index247 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">h = d.height;</code></div><div class="line number249 index248 alt2">&nbsp;</div><div class="line number250 index249 alt1">&nbsp;</div><div class="line number251 index250 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java comments">// Prints out the intructions.</code></div><div class="line number252 index251 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">String instruct = </code><code class="java string">"Pick a primitive, line style, paint, transform,"</code><code class="java plain">;</code></div><div class="line number253 index252 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">TextLayout thisTl = </code><code class="java keyword">new</code> <code class="java plain">TextLayout(instruct, </code><code class="java keyword">new</code> <code class="java plain">Font(</code><code class="java string">"Helvetica"</code><code class="java plain">, </code><code class="java value">0</code><code class="java plain">, </code><code class="java value">10</code><code class="java plain">), g2.getFontRenderContext());</code></div><div class="line number254 index253 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java keyword">float</code> <code class="java plain">width = (</code><code class="java keyword">float</code><code class="java plain">)thisTl.getBounds().getWidth();</code></div><div class="line number255 index254 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java keyword">float</code> <code class="java plain">height = (</code><code class="java keyword">float</code><code class="java plain">)thisTl.getBounds().getHeight();</code></div><div class="line number256 index255 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">thisTl.draw(g2, w/</code><code class="java value">2</code><code class="java plain">-width/</code><code class="java value">2</code><code class="java plain">, </code><code class="java value">15</code><code class="java plain">);</code></div><div class="line number257 index256 alt2">&nbsp;</div><div class="line number258 index257 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">instruct = </code><code class="java string">"and rendering method and click the Redraw button."</code><code class="java plain">;</code></div><div class="line number259 index258 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">thisTl = </code><code class="java keyword">new</code> <code class="java plain">TextLayout(instruct, </code><code class="java keyword">new</code> <code class="java plain">Font(</code><code class="java string">"Helvetica"</code><code class="java plain">, </code><code class="java value">0</code><code class="java plain">, </code><code class="java value">10</code><code class="java plain">), g2.getFontRenderContext());</code></div><div class="line number260 index259 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">width = (</code><code class="java keyword">float</code><code class="java plain">)thisTl.getBounds().getWidth();</code></div><div class="line number261 index260 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">thisTl.draw(g2, w/</code><code class="java value">2</code><code class="java plain">-width/</code><code class="java value">2</code><code class="java plain">, height + </code><code class="java value">17</code><code class="java plain">);</code></div><div class="line number262 index261 alt1">&nbsp;</div><div class="line number263 index262 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java comments">// Initialize the transform.</code></div><div class="line number264 index263 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java keyword">if</code> <code class="java plain">(firstTime) {</code></div><div class="line number265 index264 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">at.setToIdentity();</code></div><div class="line number266 index265 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">at.translate(w/</code><code class="java value">2</code><code class="java plain">, h/</code><code class="java value">2</code><code class="java plain">);</code></div><div class="line number267 index266 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">firstTime = </code><code class="java keyword">false</code><code class="java plain">;</code></div><div class="line number268 index267 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">}</code></div><div class="line number269 index268 alt2">&nbsp;</div><div class="line number270 index269 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java comments">// Sets the Stroke.</code></div><div class="line number271 index270 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">Stroke oldStroke = g2.getStroke();</code></div><div class="line number272 index271 alt1">&nbsp;</div><div class="line number273 index272 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java keyword">switch</code> <code class="java plain">( Transform.line.getSelectedIndex() ) {</code></div><div class="line number274 index273 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java keyword">case</code> <code class="java value">0</code> <code class="java plain">: g2.setStroke(</code><code class="java keyword">new</code> <code class="java plain">BasicStroke(</code><code class="java value">3</code><code class="java plain">.0f)); </code><code class="java keyword">break</code><code class="java plain">;</code></div><div class="line number275 index274 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java keyword">case</code> <code class="java value">1</code> <code class="java plain">: g2.setStroke(</code><code class="java keyword">new</code> <code class="java plain">BasicStroke(</code><code class="java value">8</code><code class="java plain">.0f)); </code><code class="java keyword">break</code><code class="java plain">;</code></div><div class="line number276 index275 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java keyword">case</code> <code class="java value">2</code> <code class="java plain">: </code><code class="java keyword">float</code> <code class="java plain">dash[] = {</code><code class="java value">10</code><code class="java plain">.0f};</code></div><div class="line number277 index276 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">g2.setStroke(</code><code class="java keyword">new</code> <code class="java plain">BasicStroke(</code><code class="java value">3</code><code class="java plain">.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, </code><code class="java value">10</code><code class="java plain">.0f, dash, </code><code class="java value">0</code><code class="java plain">.0f));</code></div><div class="line number278 index277 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java keyword">break</code><code class="java plain">;</code></div><div class="line number279 index278 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">}</code></div><div class="line number280 index279 alt1">&nbsp;</div><div class="line number281 index280 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java comments">// Sets the Paint.</code></div><div class="line number282 index281 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">Paint oldPaint = g2.getPaint();</code></div><div class="line number283 index282 alt2">&nbsp;</div><div class="line number284 index283 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java keyword">switch</code> <code class="java plain">( Transform.paint.getSelectedIndex() ) {</code></div><div class="line number285 index284 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java keyword">case</code> <code class="java value">0</code> <code class="java plain">: g2.setPaint(Color.blue);</code><code class="java keyword">break</code><code class="java plain">;</code></div><div class="line number286 index285 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java keyword">case</code> <code class="java value">1</code> <code class="java plain">: g2.setPaint(</code><code class="java keyword">new</code> <code class="java plain">GradientPaint(</code><code class="java value">0</code><code class="java plain">, </code><code class="java value">0</code><code class="java plain">, Color.lightGray, w-</code><code class="java value">250</code><code class="java plain">, h, Color.blue, </code><code class="java keyword">false</code><code class="java plain">));</code></div><div class="line number287 index286 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java keyword">break</code><code class="java plain">;</code></div><div class="line number288 index287 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java keyword">case</code> <code class="java value">2</code> <code class="java plain">: BufferedImage buffi = </code><code class="java keyword">new</code> <code class="java plain">BufferedImage(</code><code class="java value">15</code><code class="java plain">, </code><code class="java value">15</code><code class="java plain">, BufferedImage.TYPE_INT_RGB);</code></div><div class="line number289 index288 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">Graphics2D buffig = buffi.createGraphics();</code></div><div class="line number290 index289 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">buffig.setColor(Color.blue);</code></div><div class="line number291 index290 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">buffig.fillRect(</code><code class="java value">0</code><code class="java plain">, </code><code class="java value">0</code><code class="java plain">, </code><code class="java value">15</code><code class="java plain">, </code><code class="java value">15</code><code class="java plain">);</code></div><div class="line number292 index291 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">buffig.setColor(Color.lightGray);</code></div><div class="line number293 index292 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">buffig.translate((</code><code class="java value">15</code><code class="java plain">/</code><code class="java value">2</code><code class="java plain">)-(</code><code class="java value">5</code><code class="java plain">/</code><code class="java value">2</code><code class="java plain">), (</code><code class="java value">15</code><code class="java plain">/</code><code class="java value">2</code><code class="java plain">)-(</code><code class="java value">5</code><code class="java plain">/</code><code class="java value">2</code><code class="java plain">));</code></div><div class="line number294 index293 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">buffig.fillOval(</code><code class="java value">0</code><code class="java plain">, </code><code class="java value">0</code><code class="java plain">, </code><code class="java value">7</code><code class="java plain">, </code><code class="java value">7</code><code class="java plain">); </code></div><div class="line number295 index294 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">Rectangle r = </code><code class="java keyword">new</code> <code class="java plain">Rectangle(</code><code class="java value">0</code><code class="java plain">,</code><code class="java value">0</code><code class="java plain">,</code><code class="java value">25</code><code class="java plain">,</code><code class="java value">25</code><code class="java plain">);</code></div><div class="line number296 index295 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">g2.setPaint(</code><code class="java keyword">new</code> <code class="java plain">TexturePaint(buffi, r));</code></div><div class="line number297 index296 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java keyword">break</code><code class="java plain">;</code></div><div class="line number298 index297 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">}</code></div><div class="line number299 index298 alt2">&nbsp;</div><div class="line number300 index299 alt1">&nbsp;</div><div class="line number301 index300 alt2">&nbsp;</div><div class="line number302 index301 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java comments">// Sets the Shape.</code></div><div class="line number303 index302 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">Shape shape = shapes[Transform.primitive.getSelectedIndex()];</code></div><div class="line number304 index303 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">Rectangle r = shape.getBounds();</code></div><div class="line number305 index304 alt2">&nbsp;</div><div class="line number306 index305 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java comments">// Sets the selected Shape to the center of the Canvas.</code></div><div class="line number307 index306 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">AffineTransform saveXform = g2.getTransform();</code></div><div class="line number308 index307 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">AffineTransform toCenterAt = </code><code class="java keyword">new</code> <code class="java plain">AffineTransform();</code></div><div class="line number309 index308 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">toCenterAt.concatenate(at);</code></div><div class="line number310 index309 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">toCenterAt.translate(-(r.width/</code><code class="java value">2</code><code class="java plain">), -(r.height/</code><code class="java value">2</code><code class="java plain">));</code></div><div class="line number311 index310 alt2">&nbsp;</div><div class="line number312 index311 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">g2.transform(toCenterAt);</code></div><div class="line number313 index312 alt2">&nbsp;</div><div class="line number314 index313 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java comments">// Sets the rendering method.</code></div><div class="line number315 index314 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java keyword">switch</code> <code class="java plain">( Transform.stroke.getSelectedIndex() ) {</code></div><div class="line number316 index315 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java keyword">case</code> <code class="java value">0</code> <code class="java plain">: g2.draw(shape); </code><code class="java keyword">break</code><code class="java plain">;</code></div><div class="line number317 index316 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java keyword">case</code> <code class="java value">1</code> <code class="java plain">: g2.fill(shape); </code><code class="java keyword">break</code><code class="java plain">;</code></div><div class="line number318 index317 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java keyword">case</code> <code class="java value">2</code> <code class="java plain">: Graphics2D tempg2 = g2;</code></div><div class="line number319 index318 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">g2.fill(shape);</code></div><div class="line number320 index319 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">g2.setColor(Color.darkGray);</code></div><div class="line number321 index320 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">g2.draw(shape);</code></div><div class="line number322 index321 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">g2.setPaint(tempg2.getPaint()); </code><code class="java keyword">break</code><code class="java plain">;&nbsp;&nbsp; </code></div><div class="line number323 index322 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">}</code></div><div class="line number324 index323 alt1">&nbsp;</div><div class="line number325 index324 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">g2.setStroke(oldStroke);</code></div><div class="line number326 index325 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">g2.setPaint(oldPaint);</code></div><div class="line number327 index326 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">g2.setTransform(saveXform);</code></div><div class="line number328 index327 alt1"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code>&nbsp;</div><div class="line number329 index328 alt2"><code class="java spaces">&nbsp;&nbsp;&nbsp;&nbsp;</code><code class="java plain">}</code></div><div class="line number330 index329 alt1"><code class="java plain">}</code></div><div class="line number331 index330 alt2"><code class="java plain">}</code></div></div></td></tr></tbody></table></div></div>
</div>
<div class="clear"></div>
<!-- tutorial boiler plate start -->
<hr>
<div id="Footer">
<table summary="" width="100%" cellspacing="0" cellpadding="5" border="0">
<tbody><tr>
    <td width="75%">
        <p class="footertext"><a name="license_info" id="license_info"></a>
        Your use of this page 
        and all the material on pages under "The Java Tutorials" banner is
        subject to these <a href="https://docs.oracle.com/javase/tutorial/information/cpyr.html">legal
        notices</a>.</p>
    </td>
    <td width="25%">
        <p class="footertext">Copyright © 1995, 2015 Oracle and/or its affiliates. All
        rights reserved.</p>
    </td>
</tr>
</tbody></table>

<table summary="" width="100%" cellspacing="0" cellpadding="5" border="0">
<tbody><tr>
<td width="20%">
</td>
<td width="55%" valign="middle" align="center">
<p class="footertext"><a href="http://www.oracle.com/us/corporate/index.html">About
Oracle</a> | <a href="http://www.oracle.com/technology/index.html">Oracle Technology
Network</a> | <a href="http://www.oracle.com/technetwork/indexes/samplecode/index.html">
Terms of Service</a> | <a href="https://docs.oracle.com/javase/tutorial/information/docaccessibility.html">Documentation
Accessibility</a></p></td>
<td width="25%" valign="middle" align="right"></td>
</tr>
</tbody></table>
</div>
<!-- tutorial boiler plate end -->
<noscript>
<p>Scripting on this page tracks web page traffic, but does not change the content in any way.</p>

</noscript>

<!-- Start SiteCatalyst code -->
<script type="application/javascript" src="Transform_files/ora_docs.js"></script><script type="application/javascript" src="Transform_files/ora_code_docs.js"></script><script type="application/javascript" src="Transform_files/ora_code.js"></script>
<!-- End SiteCatalyst code --> 




</body></html>