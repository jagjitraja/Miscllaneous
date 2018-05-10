<?php 
	function startSession($username) {
		session_start();
		
		$_SESSION['username'] = $username;
		
		}
		
		function endSession() {
			session_unset();
			$_SESSION = array();
			session_destory();
			
		}
?>