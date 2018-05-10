<!DOCTYPE html>

<html>
<head>
    <title>Test User Authentication</title>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src='https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js'></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <style>
        .box {
            position: absolute;
            top: 200px;
            width: 400px;
            height: 300px;
            display: none;
            border: 1px solid black;
            padding: 20px;
        }
    </style>
    <script>
        window.addEventListener(load, function() {
            document.getElementById('menu-signin').addEventListener('click', show_signin);
			
			<?php
			
				// In this case, you need to show the signin box.
                				if($display_type == 'signin') echo 'show_signin()';  ?>

        });
        function show_signin() {
            document.getElementById('box-signin').style.display = 'block';
        }
        function hide() {
            document.getElementById('box-signin').style.display = 'none';
        }
    </script>
</head>

<body>
    <h2>Test MVC</h2>

    <br>
    <div class="dropdown">
        <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">Menu - TruForum
            <span class="caret"></span></button>
        <ul class="dropdown-menu dropdown-menu">
            <li><a id='menu-signin' href="#">Sign In</a></li>
            <li class='disabled'><a id='menu-join' href="#">Join</a></li>
            <li class="disabled"><a href="#">Forgot Password</a></li>
            <li class="divider"></li>
            <li><a href="http://cs.tru.ca">About Us</a></li>
        </ul>
    </div>

    <br>
    <br>
    <div id='box-signin' class='box'>
        <h3>Sign In</h3>
        <br>
        <form method='post' action='test_mvc_controller_user_auth_student.php'>
            <input type='hidden' name='page' value='StartPage'></input>
            <input type='hidden' name='command' value='SignIn'></input>
            Username: <input type='text' name='username' placeholder='Enter username' required></input><br>
            Password: <input type='password' name='password' placeholder='Enter password' required></input><br>
            <?php echo $error_message ?>
			
            <input type='submit' value='Submit'></input>
        </form>
    </div>
</body>
</html>
