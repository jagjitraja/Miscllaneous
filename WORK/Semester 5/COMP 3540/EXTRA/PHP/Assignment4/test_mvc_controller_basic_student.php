<!-- Controller -->

<?php
    // When no 'page' comes - The user just starts this application.
    if (empty($_POST['page'])) {
        include ('test_mvc_view_start_basic_student.php');  // Start page
        exit;
    }
    
    // When 'page' comes - the user sends 'signin', '...
    
    if ($_POST['page'] == 'StartPage')  // Start page
    {
        $command = $_POST['command'];
    
        switch($command) 
        {
        // The user sent 'SignIn' data.
		
		case 'SignIn':
            $username = $_POST['username'];
            $password = $_POST['password'];
            // if the username and password are valid
                include('test_mvc_view_main_basic_student');  // Main page
            // else
                // ...
            exit();  // exit
        

        // The user sent 'Join' data
        case 'Join':
            // Do some your works here, and
			//$email = $_POST['email'];
			
             //include('test_mvc_view_start_basic.php');  // Start page
						// For testing
            
			echo 'Join: <br>';
            echo $username . '<br>';  
            echo $password . '<br>';  // for password
            echo $email . '<br>';  // for email
            exit();

        // The user sent 'ForgotPassword' data
        case 'ForgotPassword':
            // Do some your works here, and
            include('test_mvc_view_start_basic.php');  // Start page
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
