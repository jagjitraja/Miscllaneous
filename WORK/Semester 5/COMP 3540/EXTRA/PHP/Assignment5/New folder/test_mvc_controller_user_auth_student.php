<!-- Controller -->

<?php
    require_once('test_mvc_module_basic_user_auth_student.php');  // Your module file
?>

<?php
    // When no 'page' comes - The user just starts this application.
    if (empty($_POST['page'])) {
        $display_type = 'start';  // In order say 'no need to display any box'
        include ('test_mvc_view_start_user_auth_student.php');  // Your start page
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
            if (checkval ($username, $password) == true)  // If the user name and password are valid; You have to use the function in your module file.
                include('test_mvc_view_main_basic_student.php');  // Main page
            else {
                $display_type = 'signin';  // In order to say 'need to display the sign in box
                $error_message = "invalid username or password";  // Your error message
                include('test_mvc_view_start_user_auth_student.php');  // Start page
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
