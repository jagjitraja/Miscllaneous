<!-- Controller -->
<?php
	require_once('module.php');
	require_once('session.php');
	
?>

<?php
    // When no 'page' comes - The user just starts this application.
    if (empty($_POST['page'])) {
		$display_type = 'start';
        include ('start.php');  // First page
        exit;
    }
    
    // When 'page' comes - the user sends 'signin', '...
    
    if ($_POST['page'] == 'start')  // Start page
    {
        $command = $_POST['command'];
    
        switch($command) 
        {
        // The user sent 'SignIn' data.
        case 'signin':
            $username = $_POST['username'];
            $password = $_POST['password'];
			$hashed_password = sha1($password);
			
            // if the username and password are valid

				if (checkval($username, $hashed_password)) {
				startSession($username);
				$recentquestions = recentquestions();
				$userquestions = userquestions();
					include('main.php'); // main page
				}
				else {
					$display_type = 'signin';
					$error_message = "<span style ='color:Red'>Invalid Username or Password<br></span>";
					include('start.php');
			}
               
            exit();  // exit

        // The user sent 'Join' data
        case 'join':
		$username = $_POST['username'];
        $password = $_POST['password'];
		$hashed_password = sha1($password);
		$email = $_POST['email'];
            // Do some your works here, and
            // include('test_mvc_view_start_basic.php');  // Start page
            // For testing
			if(!insert($username, $hashed_password, $email)) {
			$display_type = "join";
			$error_message = "<span style ='color:Red'>User Already Exists<br></span>";
			include('start.php'); 
		}
		else {
			$display_type = "signin";
			$error_message = "none";
			include('start.php');
			exit();
	
		}
            

        // The user sent 'ForgotPassword' data
        case 'forgotpassword':
            // Do some your works here, and
            include('start.php');  // Start page
            exit();

        default:
            echo 'Unknown command<br>';
            exit();
        }
    }
    
    else if ($_POST['page'] == 'main') {
        $command = $_POST['command'];
		
		switch($command) {
		
		case 'signout':
			endSession();
			include('start.php');
			exit();
			
		default:
			echo 'Unknown command<br>';
			exit();
		}
    }
    
    else {
        echo "Unknown page $page <br>";
    }
?>
