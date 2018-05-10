<!-- Controller -->

<?php
	require('db_interface_module.php');
	require('session_manage.php');
?>

<?php
    // When no 'page' comes - The user just starts this application.
    if (empty($_POST['page'])) {
		$display_type = 'start';		
        include ('startpage.php');  // First page
        exit();
    }
    
    
    if ($_POST['page'] == 'startpage')  // Start page
    {
        $command = $_POST['command'];
    
        switch($command) 
        {
        // The user sent 'SignIn' data.
        case 'signin':
            $username = $_POST['username'];
            $password = $_POST['password'];
			$hashed_password = sha1($password);
			
			if(valid_user($username, $hashed_password)) {
				start_session($username);
				$recent_questions = get_recent_questions();
				$user_questions = get_current_user_questions();
				include('view_main.php');  // Main page
			}
			else {
				$display_type = "signin";			// Display type for showing signin box
				$error_message = "invalid_user";	// For displaying error message in signin box
				include('startpage.php');
			}
			exit();

        // The user sent 'Join' data
        case 'join':
            $username = $_POST['username'];
            $password = $_POST['password'];
			$hashed_password = sha1($password);
			$email = $_POST['email'];
            
			if(!create_user($username, $hashed_password, $email)) {
				$display_type = "join";
				$error_message = "user_already_exists";
			}
			else {
				$display_type = "signin";
				$error_message = "none";
			}
			
            include('startpage.php');
            exit();

        // The user sent 'ForgotPassword' data
        case 'ForgotPassword':
            // Do some your works here, and
            include('startpage.php');  // Start page
            exit();

        default:
            echo 'Unknown command<br>';
            exit();
        }
    }
    else if ($_POST['page'] == 'MainPage') {
        $command = $_POST['command'];
		
		switch($command) {
		
		case 'signout':
			end_session();
			include('startpage.php');
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
