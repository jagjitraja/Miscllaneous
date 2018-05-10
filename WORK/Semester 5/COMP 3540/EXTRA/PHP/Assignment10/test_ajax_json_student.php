<?php
    header("Content-Type: application/json; charset=UTF-8");
    echo file_get_contents($_GET['filename']);  // read entire file content
?>