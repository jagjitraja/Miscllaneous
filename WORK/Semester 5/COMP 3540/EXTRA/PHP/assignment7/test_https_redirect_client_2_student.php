<?php
	if (!isset($_SERVER['HTTPS'])) {
		$url = 'https://' . $_SERVER['HTTP_HOST'] . $_SERVER['REQUEST_URI'];  // https and http_host
		header("location: " . $url);  // should be before any output; location is changed to the new $url
		                              // with redirect status code
		exit;
	}
?>

<!DOCTYPE html>

<html>
<head>
    <title>MySQL Test</title>
	<style>
		.code_input, .result_output {
			border:1px solid #c3c3c3;
			width:100%;
			height:600px;
			background-color:#ffffff;
			color:#000000;
		}

		#sourcecode {
			position:absolute;
			height:auto;
			width:48%;
			left:10px;
			top:5px;
			bottom:40px;
		}

		#textareacode {
			position:absolute;
			height:100%;
			width:100%;
			top:30px;
			bottom:10px;
			border:1px solid #d3d3d3; 
			resize: none;
			min-width:400px;
			min-height:350px; 
		}

		#result {
			position:absolute;
			height:auto;
			width:48%;
			right:10px;
			top:5px;
			bottom:40px;
		}
			
		#iframeresult {
			position:absolute;
			background-color: #ffffff;
			height:100%;
			width:100%;
			top:30px;
			bottom:10px;
			border:1px solid #d3d3d3;
			min-width:400px;
			min-height:350px; 
		}
		
		label {
			display: inline-block;
			width: 100px;
		}
		
		input {
			width: 300px;
		}
		
		input[type='submit'] {
			width: 100px;
		}
	</style>
</head>

<body>
	<div id='sourcecode'>
	<div id="textareacode">
		<form action ="test_https_redirect_server_2_student.php" method = "post" target = "iframe_result">
			<label>Username: </label>
			<input type = 'text' name = 'username'><br>
			<label>Password: </label>
			<input type = 'password' name = 'password'><br>
			<br>
			<input type = "submit" value = "Submit">
		</form>
	</div>
	</div>
	
	<div id="result">
	    <p style='position:relative; top:-15px;'>
			This pane is used for another web application within an iframe.
		</p>
		<iframe name='iframe_result' id='iframeresult' class='result_output' frameborder='0'>
		</iframe>
	</div>

</body>
</html>
