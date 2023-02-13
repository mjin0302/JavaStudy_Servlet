<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>

<div class="slider">
	<div><img width="850px" height="auto" src="images/3.jpg"></div>
	<div><img width="850px" height="auto"  src="images/루피.jpg"></div>
	<div><img width="850px" height="auto"  src="images/루피2.jpg"></div>
	
	<div>I am another slide.</div>
</div>

<script>
	$('.slider').bxSlider();
</script>