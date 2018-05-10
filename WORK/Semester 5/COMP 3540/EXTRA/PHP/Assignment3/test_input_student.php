<!DOCTYPE html>

<html>
<head>
    <title>Test Input</title>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src='https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js'></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <style>
        #box-signin {
            position: absolute;
            top: 200px;
            width: 400px;
            height: 300px;
            display: none;
            border: 1px solid black;
        }
    </style>
    <script>
        window.addEventListener('load', function() {
            document.getElementById('menu-signin').addEventListener('click', show_signin);
        });
        
        function show_signin() {
            document.getElementById('box-signin').style.display = 'block';
        }
        
        function hide_signin() {
            document.getElementById('box-signin').style.display = 'none';
        }
    </script>
</head>

<body>
    <h2>Test Input</h2>

    <br>
    <div class="dropdown">
        <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">Menu - TruForum
        <span class="caret"></span></button>
        <ul class="dropdown-menu dropdown">
            <li><a id='menu-signin' href="#">Sign In</a></li>
            <li class="disabled"><a href="#">Join</a></li>
            <li class="disabled"><a href="#">Forgot Password</a></li>
            <li class="divider"></li>
            <li><a href="http://cs.tru.ca">About Us</a></li>
        </ul>
    </div>

    <br>
    <br>
    <div id='box-signin'>
        <h3>Sign In</h3>
        <br>
        <form method='get' action='test_input_controller_student.php'>
            <input type='hidden' name='page' value='start' required></input>
            <input type='hidden' name='command' value='signin' required></input>
            Username: <input type='text' name='username' placeholder='Enter username' required></input><br>
            Password: <input type='password' name='password' placeholder='Enter password' required></input><br>
            Email: <input type='email' name='email' placeholder='Enter email address' required></input><br><br>
            <input type='submit' value='Submit'></input>
        </form>
    </div>
</body>
</html>
