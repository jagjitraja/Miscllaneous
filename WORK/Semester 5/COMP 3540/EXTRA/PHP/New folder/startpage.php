<?php
	if (!isset($_SERVER['HTTPS'])) {
		$url = 'https://' . $_SERVER['HTTP_HOST'] . $_SERVER['REQUEST_URI'];  // https and http_host
		header("Location: " . $url);  // should be before any output; location is changed to the new $url
		                              // with redirect status code
		exit;
	}
?>
<!DOCTYPE html>
<html>
	<head>
		<title>Start Page</title>
		<link rel="stylesheet" type="text/css" href="startpage.css" />
		<script>
			var x0;
			var y0;
			window.addEventListener("load", onload, false);
			window.addEventListener("resize", onresize, false);
			window.addEventListener("mousemove", onmousemove, false);
			
			
			
			//Used to initialize x0 and y0 values
			function onload(e) {
				//Attach event listeners to the drop down menu items
				document.getElementById('menu-signin').addEventListener('click', show_signin);
				document.getElementById('join').addEventListener('click', show_join);
				document.getElementById('forgot-password').addEventListener('click', show_forgot_password);
				
				<?php
					if($display_type == 'signin') {
						echo 'show_signin();';
						echo 'document.getElementById("signin_username").value = "' . $username . '";';
						if($error_message == 'invalid_user') {
							echo 'document.getElementById("auth_error").style.visibility = "visible";';
						}
					}
					else if($display_type == 'join') {
						echo 'show_join();';
						if($error_message == 'user_already_exists') {
							echo 'document.getElementById("join_error").style.visibility = "visible";';
						}
					}
				?>
			
				//Attach onclick listener to the image and list items in the drop down menu
				var menu = document.getElementsByClassName("menu-clickable");
				for(i = 0; i < menu.length; i++) {
					menu[i].addEventListener("click", menuclick, false);
				}
				
				var cancelbuttons = document.getElementsByClassName("cancelbutton");
				for(i = 0; i < cancelbuttons.length; i++) {
					cancelbuttons[i].addEventListener("click", hide_all, false);
				}
				
				//Add event listener to close whatever popup is open when the dim box is clicked
				document.getElementById('dim').addEventListener('click', hide_all);
				
				//Apparently setting this to hidden in the CSS doesn't set the DOM object property
				//Kind of gross but it works
				menu = document.getElementsByClassName("menu-list")[0].style.visibility = "hidden";
				//Get the current date for display
				
				var date = document.getElementsByClassName("date")[0].innerHTML = new Date().toLocaleDateString();
				//Initializes the center coordinates
				
				onresize();
			}
			
			//Sets x0 and y0 to be the coords of center-page
			function onresize(e) {
				x0 = window.innerWidth / 2;
				y0 = window.innerHeight / 2;
			}
			
			//Updates the output for mouse direction
			function onmousemove(e) {
				//Gets X and Y coords of the cursor
				var x1 = e.clientX;
				var y1 = e.clientY;
				
				//Get the cursor position in degrees relative to x0,y0
				var rad = Math.atan((y1 - y0) / (x1 - x0));
				var deg = rad * (180 / Math.PI);
				
				var position;
				
				//Tests if the cursor is above or below the center of the page
				//Used for determining which set of +/- sectors we are on (using top and bottom two sectors)
				if(y1 < y0) {	//Top
					if(deg > 75 || deg < -75) {
						position = '12 O\'Clock';
					}
					else if(deg < -45) {
						position = '1 O\'Clock';
					}
					else if(deg > 45) {
						position = '11 O\'Clock';
					}
					else if(deg < -15) {
						position = '2 O\'Clock';
					}
					else if(deg > 15) {
						position = '10 O\'Clock';
					}
					else if(deg < 0) {
						position = '3 O\'Clock';
					}
					else if(deg >= 0) {
						position ='9 O\'Clock';
					}
				}
				else {			//Bottom
					if(deg > 75 || deg < -75) {
						position = '6 O\'Clock';
					}
					else if(deg < -45) {
						position = '7 O\'Clock';
					}
					else if(deg > 45) {
						position = '5 O\'Clock';
					}
					else if(deg < -15) {
						position = '8 O\'Clock';
					}
					else if(deg > 15) {
						position = '4 O\'Clock';
					}
					else if(deg < 0) {
						position = '9 O\'Clock';
					}
					else if(deg >= 0) {
						position ='3 O\'Clock';
					}
				}
				
				document.getElementsByClassName('direction')[0].innerHTML = position;
			}
			
			//Used to handle click events on the menu
			function menuclick(e) {
				var menu = document.getElementsByClassName("menu-list")[0];
				
				//If it's hidden then show it, and vice versa
				if(menu.style.visibility == "hidden") {
					menu.style.visibility = "visible";
				}
				else {
					menu.style.visibility = "hidden";
				}
			}
			
			function show_signin() {
				dim();
				document.getElementById('box-signin').style.display = 'block';
			}
			
			//Gross disgusting repeated code but it works so #yolo am I right?
			function hide_signin() {
				undim();
				temp = document.getElementById('box-signin');
				temp.style.display = 'none';
				temp = temp.getElementsByClassName('textbox');
				for(i = 0; i < temp.length; i++) {
					temp[i].value = "";
				}
			}
			
			function show_join() {
				dim();
				document.getElementById('box-join').style.display = 'block';
			}
			
			//Gross disgusting repeated code but it works so #yolo am I right?
			function hide_join() {
				undim();
				temp = document.getElementById('box-join');
				temp.style.display = 'none';
				temp = temp.getElementsByClassName('textbox');
				for(i = 0; i < temp.length; i++) {
					temp[i].value = "";
				}
			}
			
			function show_forgot_password() {
				dim();
				document.getElementById('box-forgotpassword').style.display = 'block';
			}
			
			//Gross disgusting repeated code but it works so #yolo am I right?
			function hide_forgot_password() {
				undim();
				temp = document.getElementById('box-forgotpassword');
				temp.style.display = 'none';
				temp = temp.getElementsByClassName('textbox');
				for(i = 0; i < temp.length; i++) {
					temp[i].value = "";
				}
			}
			
			function dim() {
				document.getElementById('dim').style.display = 'block';
			}
			
			function undim() {
				document.getElementById('dim').style.display = 'none';
			}
			
			function hide_all() {
				hide_signin();
				hide_join();
				hide_forgot_password();
			}
		</script>
	</head>

	<body>
		<header>
			<!-- Placeholder date value -->
			<div class="date">March 19, 2016</div>
			<div class="title">TRU Forum</div>
			<div class="menu-wrapper">
				<img src="menu.png" class="menu-clickable"></img>
				<ul class="menu-list">
					<li id="menu-signin" class="menu-clickable"><a href="#">Sign In</a></li>
					<li id="join" class="menu-clickable"><a href="#">Join</a></li>
					<li id="forgot-password" class="menu-clickable"><a href="#">Forgot Password</a></li>
				</ul>
			</div>
		</header>
		
		<div class="content">
			<div class="table-box-outside">
				<div class="table-box-inside">
					<div class="box">
						<p>Direction to mouse pointer: <span class="direction"></span></p>
					</div>
				</div>
			</div>
		</div>
		
		<div id='box-signin'>
			<h3>Sign In</h3>
			<br>
			<form method='post' action='index.php' autocomplete="on">
				<input type='hidden' name='page' value='startpage' required></input>
				<input type='hidden' name='command' value='signin' required></input>
				Username: <input id="signin_username" class="textbox" type='text' name='username' placeholder='Enter username' required></input><br>
				Password: <input class="textbox" type='password' name='password' placeholder='Enter password' required></input><br>
				<input class="submitbutton" type='submit' value='Sign In'></input>
				<p id= "auth_error" style="border: 1px solid red; background-color: rgba(255, 0, 0, 0.1); visibility: hidden;">Invalid Username and/or Password</p>
			</form>
			<button class="cancelbutton">Cancel</button>
		</div>
		<div id='box-join'>
			<h3>Join</h3>
			<br>
			<form method='post' action='index.php'>
				<input type='hidden' name='page' value='startpage' required></input>
				<input type='hidden' name='command' value='join' required></input>
				Username: <input class="textbox" type='text' name='username' placeholder='Enter username' required></input><br>
				Password: <input class="textbox" type='password' name='password' placeholder='Enter password' required></input><br>
				Email: <input class="textbox" type='email' name='email' placeholder='Enter email address' required></input><br><br>
				<input class="submitbutton" type='submit' value='Join'></input>
				<p id= "join_error" style="border: 1px solid red; background-color: rgba(255, 0, 0, 0.1); visibility: hidden;">Username Already Taken</p>
			</form>
			<button class="cancelbutton">Cancel</button>
		</div>
		<div id='box-forgotpassword'>
			<h3>Forgot Password</h3>
			<br>
			<form method='post' action='index.php'>
				<input type='hidden' name='page' value='startpage' required></input>
				<input type='hidden' name='command' value='forgotpassword' required></input>
				Username: <input class="textbox" type='text' name='username' placeholder='Enter username' required></input><br>
				<input class="submitbutton" type='submit' value='Forgot Password'></input>
			</form>
			<button class="cancelbutton">Cancel</button>
		</div>
		
		<div id="dim">
		</div>

		<footer>
			<span><a href="http://cs.tru.ca" target="_blank">About Us</a></span>
		</footer>

	</body>

</html>