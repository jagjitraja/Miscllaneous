<?php
	function start_session($username) {
		session_start();	//Start the session
		
		//Set relevant session data
		$_SESSION['username'] = $username;
	}
	
	function end_session() {
		session_unset();
		$_SESSION = array();
		session_destroy();
	}

?>