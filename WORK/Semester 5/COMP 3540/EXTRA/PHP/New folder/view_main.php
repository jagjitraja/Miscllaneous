<!-- View: Main -->
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
	<title>Main</title>
	<link rel="stylesheet" type="text/css" href="view_main.css" />
	<script>
		window.addEventListener("load", onload, false);
		var recentTable;
		var userTable;
		
		function onload() {
			document.getElementById("date").innerHTML = new Date().toLocaleDateString();
			
			//Set up the click event for the log out menu in the top right
			document.getElementById("menu-clickable").addEventListener("click", function() {
				var menu = document.getElementsByClassName("menu-list")[0];
				
				//If it's hidden then show it, and vice versa
				if(menu.style.visibility == "visible")
					menu.style.visibility = "hidden";
				else
					menu.style.visibility = "visible";
			});
			
			//Submit hidden sign out form
			document.getElementById("menu-signout").addEventListener("click", function() {
				document.getElementById("signout-form").submit();
			});
			
			recentTable = document.getElementById("recent_ques");
			userTable = document.getElementById("your_ques");
			
			populate_tables();
		}
		
		//Quite possibly the single grossest thing ever written
		//Never look at this again
		function populate_tables() {
			str = "<th><td>Recent Questions</td><td></td><td></td></th>";
			<?php
				while($row = mysqli_fetch_array($recent_questions)) {
					//echo 'str += ' . json_encode($row['question']) . ';';
					echo "str += \"<tr><td>\";";
					echo "str += " . json_encode($row['question']) . ";";
					echo "str += \"</td><td><button class='view_answers' value=\";";
					echo "str += " . json_encode($row['question_id']) . ";";
					echo "str += \">View Answers</button></td>\";";
					echo "str += \"<td><button class='submit_answer' value=\";";
					echo "str += " . json_encode($row['question_id']) . ";";
					echo "str += \">Submit Answer</button></td></tr>\";";
				}
			?>
			
			recentTable.innerHTML = str;
			
			str2 = "<th><td>Your Questions</td><td></td><td></td></th>";
			<?php
				while($row = mysqli_fetch_array($user_questions)) {
					//echo 'str += ' . json_encode($row['question']) . ';';
					echo "str2 += \"<tr><td>\";";
					echo "str2 += " . json_encode($row['question']) . ";";
					echo "str2 += \"</td><td><button class='view_answers' value=\";";
					echo "str2 += " . json_encode($row['question_id']) . ";";
					echo "str2 += \">View Answers</button></td>\";";
					echo "str2 += \"<td><button class='submit_answer' value=\";";
					echo "str2 += " . json_encode($row['question_id']) . ";";
					echo "str2 += \">Submit Answer</button></td></tr>\";";
				}
			?>
			
			userTable.innerHTML = str2;
		}
	</script>
</head>

<body>
	<header>
		<!-- Placeholder date value -->
		<div id="date">March 19, 2016</div>
		<div class="title">TRU Forum</div>
		<div class="menu-wrapper">
			<img src="menu.png" id="menu-clickable"></img>
			<ul class="menu-list">
				<li id="menu-signout" class="menu-clickable"><a href="#">Sign Out</a></li>
			</ul>
		</div>
	</header>
    
	<button id="add_question_button">ADD QUESTION</button>
	<div id="table_container">
		<table id="recent_ques">
		</table>
		
		<table id="your_ques">
		</table>
	</div>
	
	<form id="signout-form" method="post" action="index.php">
		<input type="hidden" name="page" value="MainPage" required></input>
		<input type="hidden" name="command" value="signout" required></input>
	</form>
</body>
</html>
