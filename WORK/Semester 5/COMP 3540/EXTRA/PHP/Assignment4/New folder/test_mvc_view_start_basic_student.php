<!DOCTYPE html>

<html>
<head>
    <title>Test MVC</title>
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
            border: 2px solid black;
            padding: 20px;
        }
    </style>
    <script>
        window.addEventListener('load', function() {
            document.getElementById('menu-signin').addEventListener('click', show_signin);
            document.getElementById('menu-join').addEventListener('click', show_join);
        });
        function show_signin() {
            document.getElementById('box-signin').style.display = 'block';
        }
        function hide_signin() {
            document.getElementById('box-signin').style.display = 'none';
        }
        function show_join() {
            document.getElementById('box-join').style.display = 'block';
        }
        function hide_join() {
            document.getElementById('box-join').style.display = 'none';
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
            <li><a id='menu-join' href="#">Join</a></li>
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
        <form method='post' action='test_mvc_controller_basic_student.php'>
            <input type='hidden' name='page' value='start'></input>
            <input type='hidden' name='command' value='signin'></input>
            Username: <input type='text' name='username' placeholder='Enter username' required></input><br>
            Password: <input type='password' name='password' placeholder='Enter password' required></input><br>
            <input type='submit' value='Submit'></input>
        </form>
    </div>
    
    <br>
    <br>
    <div id='box-join' class='box'>
        <h3>Join</h3>
        <br>
        <form method='post' action='test_mvc_controller_basic_student.php'>
            <input type='hidden' name='page' value='start'></input>
            <input type='hidden' name='command' value='join'></input>
            Username: <input type='text' name='username' placeholder='Enter username' required></input><br>
            Password: <input type='password' name='password' placeholder='Enter password' required></input><br>
            Email: <input type='text' name='email' placeholder='Enter email address' required></input><br><br>
            <input type='submit' value='Submit'></input>
        </form>
    </div>
</body>
</html>
