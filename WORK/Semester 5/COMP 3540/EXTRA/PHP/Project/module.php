<?php
    $conn = mysqli_connect('localhost', 'spatel3540', 'Serendipity.2', 'COMP3540W16_spatel');  // not 'cs.tru.ca'
    if (mysqli_connect_error()) {
        echo "Failed to connect to DB: " . mysqli_connect_error();
        exit;
    }
    
    // the function to validity of usernamd and password
    function checkval($username, $password) {
		global $conn;
		
		$sql = "SELECT * FROM user
                WHERE username = '$username' AND password = '$password'";
		
		$result = mysqli_query($conn, $sql);
        if (mysqli_num_rows($result) >= 1)
            return true;
        else
            return false;
    }
	
	
	function insert($username, $password, $email)
    {
        global $conn;  // inorder to access to global variables
        
        // select user information with $username and $password
        $sql = "SELECT * from  user
                where username ='$username'";  // You've got to remember the values for the two columns are strings.
         $result = mysqli_query($conn, $sql);
		 
     
        if (mysqli_num_rows($result) >= 1)  // if the number of rows is >= 1
            return false;
			
			$sql = "INSERT into `user`(`username`,`password`, `email_address`)
					values ('$username' ,`$password', '$email')";
					
					mysqli_query($conn, $sql);
       
            return true;
    }
function insertquestion($question) {
		global $conn;
		
		//For storing the user that asked the question and its time
		$username = $_SESSION['username'];
		$datetime = time();
		
		$sql = "INSERT INTO questions(username, question, datetime)
				VALUES ('$username', '$question', '$datetime')";
		
		$result = mysqli_query($conn, $sql);

		return true;
	}
	
	function recentquestions() {
		global $conn;
		
	
		$sql = "SELECT question_id, question FROM questions
				ORDER BY datetime DESC
				LIMIT 10";
		
		$result = mysqli_query($conn, $sql);
		
		return $result;
	}
	
	function userquestions() {
		global $conn;
		
		$currentuser = $_SESSION['username'];
		
		$sql = "SELECT question_id, question FROM questions
				WHERE username = '$currentuser'";
		
		$result = mysqli_query($conn, $sql);
		
		return $result;
	}
	
	//Stubbed for now
	function searchquestion($searchterm) {
		return false;
	}
?>
	
	
