<?php
// This function is used to check the validity of the username and password
function is_valid($username, $password) {
	
	if($username == 'comp3540' && $password=='comp3540424'){
		return true;
	}
	else{
		return false;
	}
	

	// Return true when the user name is 'comp3540' and the password is 'comp3540424'. 
         // Otherwise, false.
}
?>   