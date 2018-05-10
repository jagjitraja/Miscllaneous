<!-- View: Main -->


<!DOCTYPE html>

<html>
<head>
</head>

<body>
    <h1>You are currently signed in.</h1>
    
    <h3>The data passed from the controller:</h3>
<?php
	echo 'Command: ' . $command . '<br>';
	echo 'Username: ' . $username . '<br>';
	echo 'Password: ' . $password . '<br>';
?>
</body>
</html>


<?php 
if (!isset($_SERVER['HTTPS'])) {
	$url = 'https://' . $_SERVER['HTTP_HOST'] . $_SERVER['REQUEST_URI'];
	header("Location: " .$url);
	exit;

}
?>

<!DOCTYPE html> 

<html> 

<!-- Shivani Patel T00064422 -->

<head>

<title> Main Page </title>

<link rel = "stylesheet" type = "text/css" href = "main.css">

<script type = "text/javascript">



var d=new Date();
var monthname=new Array("January","February","March","April","May","June","July","August","September","October","November","December");
//Ensure correct for language. English is "January 1, 2004"
var TODAY = monthname[d.getMonth()] + " " + d.getDate() + ", " + d.getFullYear();



    
window.addEventListener('load', function() {
            	document.getElementById('click_post').addEventListener('click', show_post);
            	document.getElementById('click_list').addEventListener('click', show_list);
				document.getElementById('click_listmine').addEventListener('click', show_listmine);
				document.getElementById('click_search').addEventListener('click', show_search);
            	document.getElementById('click_displayanswer').addEventListener('click', show_displayanswer);
				document.getElementById('click_postanswer').addEventListener('click', show_postanswer);
            	document.getElementById('click_signout').addEventListener('click', show_signout);
		
        });
		
	function hide_all() {
            document.getElementById('box-post').style.display = 'none';
            document.getElementById('box-list').style.display = 'none';
            document.getElementById('box-listmine').style.display = 'none';        
			document.getElementById('box-search').style.display = 'none';
            document.getElementById('box-displayanswer').style.display = 'none';
            document.getElementById('box-postanswer').style.display = 'none'; 
			document.getElementById('box-signout').style.display = 'none';  			
	}
		
        function show_post() {
            hide_all();
            document.getElementById('box-post').style.display = 'block';
        }
		
        function show_list() {
            hide_all();
            document.getElementById('box-list').style.display = 'block';
        }
		
		function show_listmine() {
            hide_all();
            document.getElementById('box-listmine').style.display = 'block';
        }
		
		 function show_search() {
            hide_all();
            document.getElementById('box-search').style.display = 'block';
        }
		
		function show_postanswer() {
            hide_all();
            document.getElementById('box-search').style.display = 'block';
        }
		
		function show_displayanswer() {
            hide_all();
            document.getElementById('box-search').style.display = 'block';
        }
		 function show_signout() {
            hide_all();
            document.getElementById('box-signout').style.display = 'block';
        }
		

</script>

</head>

<body style = "background-color: white;" >
    
<div onclick="hide_all()" id="wrap"></div>

<div class = "main">
	<div class = "header">
		 Welcom 
	
			<p class = "date"> <script language = "javascript" type = "text/javascript">document.write(TODAY);</script> </p>
	
</div>
    

    
<div class = "navigation">
	<ul>
		<li style = "background-color:white;" ><img style = "margin-top:0px;height:35px; width:50px;" src="menu.jpg"/>
			<ul>
				<li class ="n1" id = "click_post">Post Question</li>
				<li class ="n2" id = "click_list">List </li>
                <li class ="n3" id = "click_listmine">List Mine</li>	
				<li class ="n4" id = "click_search">Search </li>
				<li class ="n5" id = "click_displayanswer">Display answer </li>
				<li class ="n6" id = "click_postanswer">Post Answer </li>
				<li class ="n7" id = "click_signout">Sign Out </li>

	</ul>
   </li>
    </ul>
</div>

<div class = "footer">
	<h3><a href = "http://www.tru.ca/science/programs/compsci.html"> About Us </a></h3>
</div> 

<div id ="box-post">
    <h3>Post Question</h3>
    <form  method = 'post'  action = 'controller.php' >
        <input type = 'hidden' name = 'page' value = 'mainpage' >
        <input type = 'hidden' name = 'command' value = 'question' >
        <p>Post_Q: <input type = 'text' name = 'question' 	placeholder = 'Enter Question' id = "text" required></p>
       <br><br>
	   
		<p><button type ="submit" id = "submit">Post</button>
        <input type ="button" value = "Cancel" id ="cancel" name ="cancel" onclick = "hide_all()"></input></p>    
</form>
 </div>

<div id = "box-list">
    <h3>List Questions</h3>
    <form  method = 'post' action ='controller.php'>
    <input type='hidden' name='page' value='mainpage' required>
    <input type='hidden' name='command' value='listquestion' required>
    

        <input type ="button"  onclick = "hide_all()"></input>
    </form>
   </div>

<div id = "box-listmine">
    <h3>List Mine</h3>
    <form  method = 'post'  action = 'controller.php' autocomplete = 'on'>
    <input type='hidden' name='page' value='mainpage' required>
    <input type='hidden' name='command' value='listmine' required>
    
        <input type ="button" value = "Cancel" id ="cancel" name ="cancel" onclick = "hide_all()"></input>  
 </form>
    </div>
	
<div id = "box-search">
    <h3>Search</h3>
    <form  method = 'post' action ='controller.php'>
    <input type='hidden' name='page' value='mainpage' required>
    <input type='hidden' name='command' value='search' required>
     <input type = 'text' name = 'search' placeholder = 'Enter keywords' id='text' required>
   
    <button type="submit" id="submit">Submit</button>
        <input type ="button" value = "Cancel" id ="cancel" name ="cancel" onclick = "hide_all()"></input>
    </form>
   </div>

<div id = "box-displayanswer">
    <h3>Answers</h3>
    <form  method = 'post' action ='controller.php'>
    <input type='hidden' name='page' value='mainpage' required>
    <input type='hidden' name='command' value='displayanswer' required>
     
        <input type ="button" value = "Cancel" id ="cancel" name ="cancel" onclick = "hide_all()"></input>
   </form>
   </div>	

<div id = "box-postanswer">
    <h3>Post Answer</h3>
    <form  method = 'post' action ='controller.php'>
    <input type='hidden' name='page' value='mainpage' required>
    <input type='hidden' name='command' value='postanswer' required>
     <input type = 'text' name = 'search' placeholder = 'Enter keywords' id='text' required>
   
    <p><button type="submit" id="submit">Post</button>
        <input type ="button" value = "Cancel" id ="cancel" name ="cancel" onclick = "hide_all()"></input></p>
    </form>
   </div>
</div>

</body>
</html>
			

