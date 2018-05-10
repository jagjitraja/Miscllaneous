<?php
	$conn = mysqli_connect('localhost', 'sroddick3540', 'myunixpassword', 'COMP3540W16_sroddick');  // not 'cs.tru.ca'
    if (mysqli_connect_error()) {
        echo "Failed to connect to DB: " . mysqli_connect_error();
        exit();
    }
	
	function create_user($username, $password, $email) {
		global $conn;
		//Check if username already exists
		$sql = "SELECT * FROM users
                WHERE username = '$username'";
		$result = mysqli_query($conn, $sql);
		
		if (mysqli_num_rows($result) >= 1)
            return false;
		
		//Username doesn't exist, so user can be created
		$sql = "INSERT INTO users(username, password, email_address)
				VALUES ('$username', '$password', '$email')";
	
		mysqli_query($conn, $sql);
		
		return true;
	}
	
	function valid_user($username, $password) {
		global $conn;
		
		$sql = "SELECT * FROM users
                WHERE username = '$username' AND password = '$password'";
		
		$result = mysqli_query($conn, $sql);
        if (mysqli_num_rows($result) >= 1)
            return true;
        else
            return false;
    }
	
	function insert_question($question) {
		global $conn;
		
		//For storing the user that asked the question and its time
		$username = $_SESSION['username'];
		$datetime = time();
		
		$sql = "INSERT INTO questions(username, question, datetime)
				VALUES ('$username', '$question', '$datetime')";
		
		$result = mysqli_query($conn, $sql);

		return true;
	}
	
	function get_recent_questions() {
		global $conn;
		
		//ORDER BY datetime	- most recent (highest value) at top
		//LIMIT 10			- get the first 10 records
		//result			- the 10 most recent records
		$sql = "SELECT question_id, question FROM questions
				ORDER BY datetime DESC
				LIMIT 10";
		
		$result = mysqli_query($conn, $sql);
		
		return $result;
	}
	
	function get_current_user_questions() {
		global $conn;
		
		$current_user = $_SESSION['username'];
		
		$sql = "SELECT question_id, question FROM questions
				WHERE username = '$current_user'";
		
		$result = mysqli_query($conn, $sql);
		
		return $result;
	}
	
	//Stubbed for now
	function search_question($search_term) {
		return false;
	}
?>