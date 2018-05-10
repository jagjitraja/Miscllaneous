<!-- Controller -->

<?php
    re???('???.php');  // Your module file
?>

<?php
    // When no 'page' comes - The user just starts this application.
    if (empty($_POST['page'])) {
        $display_type = 'start';  // In order say 'no need to display any box'
        include ('???.php');  // Your start page
        exit;
    }
    
    // When 'page' comes - the user sends 'signin', '...
    
    if ($_POST['page'] == 'StartPage')
    {
        $command = $_POST['command'];
    
        switch($command) 
        {
        // The user sent 'SignIn' data.
        case 'SignIn':
            $username = $_POST['username'];
            $password = $_POST['password'];
            if (???)  // If the user name and password are valid; You have to use the function in your module file.
                include('???.php');  // Main page
            else {
                ??? = 'signin';  // In order to say 'need to display the sign in box
                $error_message = "???";  // Your error message
                include('???');  // Start page
            }
                
            exit();  // exit

        case 'Join':
            // Do 'join' task, and
            
            exit();

        case 'ForgotPassword':
            // Do 'forgotpassword' task, and

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
