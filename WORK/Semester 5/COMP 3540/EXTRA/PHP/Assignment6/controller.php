<!-- Controller -->
<?php
    require_once('module.php');  // Your module file

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
		
            // if the username and password are valid
                
		if (checkval ($username, $password)) // If the user name and password are valid; You have to use the function in your module file.
		{include('main.php'); 
		}
            else {
                $display_type = 'signin';  // In order to say 'need to display the sign in box
                $error_message = "<span style ='color:Red'>*invalid username or password<br></span>";  // Your error message
                include('start.php');  // Start page
            }
                
		exit();




        // The user sent 'Join' data
        case 'join':
		$username = $_POST['username'];
        $password = $_POST['password'];
		$email = $_POST['email'];
            // Do some your works here, and
		if(!insert($username, $password, $email)) {
			$display_type = "join";
			$err_message = "user exists";
			include('start.php'); 
		}
		else {
			$display_type = "signin";
			$error_message = "";
			include('start.php');
			
	
		}
				
			  // Start page
            // For testing
          
            exit();
			
		
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
    
    else if ($page == 'MainPage') {
        // ...
    }
    
    else {
        echo 'Unknown page<br>';
    }
?>
