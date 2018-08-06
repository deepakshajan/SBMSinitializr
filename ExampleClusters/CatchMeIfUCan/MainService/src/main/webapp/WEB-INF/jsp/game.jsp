<%-- /**
* MIT License
Copyright (c) 2017 deepakshajan
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/  --%>
<html>
<head>
    <title>Catch Me If U Can</title>

    <%-- Third party css files start --%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}\css\frameworks\bootstrap.min.css"/>
    <%-- Third party css files end --%>

    <link rel="stylesheet" href="${pageContext.request.contextPath}\css\game.css"/>

    <%--Bean definitions start--%>
    <jsp:useBean id="configuration" scope="request" type="java.lang.String"/>
    <%--Bean definitions end--%>

</head>

<body>
<div class="row">
    <div class="heading col-sm-12">
        <h3><span id="headingText">Move in any direction to Start</span></h3>
    </div>
</div>
<div class="row">
    <div class="col-sm-4"></div>
    <canvas id="canvas" class="canvas"></canvas>
    <div class="col-sm-4"></div>
</div>
<div class="row">
    <div class="heading col-sm-12">
        <h3><span>Level : <span id="levelBoard">0</span></span><span> Score : <span id="scoreBoard">0</span></span></h3>
    </div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}\js\game.min.js"></script>

<script>
    cmiuc.init(${configuration});
</script>

</body>
</html>