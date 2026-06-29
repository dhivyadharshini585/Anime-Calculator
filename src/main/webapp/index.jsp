<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String error = (String) request.getAttribute("error");
Double result = (Double) request.getAttribute("result");

String displayValue = "";

if (result != null) {
    displayValue = String.valueOf(result);
}
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Naruto Shippuden Calculator</title>

<link rel="stylesheet" href="css/style.css">

<script>

let calculated = <%= (result != null) ? "true" : "false" %>;

function add(value){

    let display=document.getElementById("display");
    let text=display.value;

    const operators="+-*/";

    if(calculated){
        display.value="";
        calculated=false;
        text="";
    }

    if(operators.includes(value)){

        if(text.length==0){
            return;
        }

        let last=text.charAt(text.length-1);

        if(operators.includes(last)){
            return;
        }
    }

    display.value+=value;
}

function clearDisplay(){

    document.getElementById("display").value="";
    calculated=false;
}

function backspace(){

    let display=document.getElementById("display");

    display.value=display.value.slice(0,-1);
}

</script>

</head>

<body>

<div class="background">

<div class="calculator-container">

<div class="header">
<img
src="images/header.png"
class="header-image"
alt="Naruto Shippuden">
</div>


<%
if(error!=null){
%>

<p class="error">
<%=error%>
</p>

<%
}
%>

<form action="calculate" method="post">

<input
type="text"
id="display"
class="display"
name="expression"
value="<%=displayValue%>"
readonly
autocomplete="off">

<br><br>

<table class="calculator-table">

<tr>

<td>
<button class="number kakashi"
type="button"
onclick="add('7')">
<span>7</span>
</button>
</td>

<td>
<button class="number sasuke"
type="button"
onclick="add('8')">
<span>8</span>
</button>
</td>

<td>
<button class="number itachi"
type="button"
onclick="add('9')">
<span>9</span>
</button>
</td>

<td>
<button class="operator"
type="button"
onclick="add('/')">
<span>/</span>
</button>
</td>

</tr>

<tr>

<td>
<button class="number shikamaru"
type="button"
onclick="add('4')">
<span>4</span>
</button>
</td>

<td>
<button class="number gaara"
type="button"
onclick="add('5')">
<span>5</span>
</button>
</td>

<td>
<button class="number neji"
type="button"
onclick="add('6')">
<span>6</span>
</button>
</td>

<td>
<button class="operator"
type="button"
onclick="add('*')">
<span>*</span>
</button>
</td>

</tr>

<tr>

<td>
<button class="number rocklee"
type="button"
onclick="add('1')">
<span>1</span>
</button>
</td>

<td>
<button class="number hinata"
type="button"
onclick="add('2')">
<span>2</span>
</button>
</td>

<td>
<button class="number sakura"
type="button"
onclick="add('3')">
<span>3</span>
</button>
</td>

<td>
<button class="operator"
type="button"
onclick="add('-')">
<span>-</span>
</button>
</td>

</tr>

<tr>

<td>
<button class="number jiraiya"
type="button"
onclick="add('0')">
<span>0</span>
</button>
</td>

<td>
<button class="number orochimaru"
type="button"
onclick="add('.')">
<span>.</span>
</button>
</td>

<td>
<button class="number minato clearButton"
type="button"
onclick="clearDisplay()">
<span>C</span>
</button>
</td>

<td>
<button class="operator"
type="button"
onclick="add('+')">
<span>+</span>
</button>
</td>

</tr>

<tr>

<td colspan="2">

<button
class="backspace"
type="button"
onclick="backspace()">

<span>⌫</span>

</button>

</td>

<td colspan="2">

<button
class="equal"
type="submit">

<span>=</span>

</button>

</td>

</tr>

</table>

</form>

</div>

</div>

</body>

</html>