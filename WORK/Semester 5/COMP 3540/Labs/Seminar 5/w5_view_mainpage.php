<?php
?>

<!DOCTYPE html>

<html>
<head>
    <title>TruQA</title>
    <style>
        /*
        *   layout
        */
        
        #title {
            position: fixed;
            top: 0;
            left: 0;
            width: 100vw;
            height: 80px;
            background-color: Beige;
        }
        #navigation {
            position: fixed;
            top: 80px;
            left: 0;
            width: 150px;
            height: calc(100vh - 80px);
            background-color: Azure;
        }
        #content {
            position: fixed;
            top: 80px;
            left: 150px;
            width: calc(100vw - 150px);
            height: calc(100vh - 80px);
            background-color: Lavender;
        }
        
        /*
        *   navigation bars
        */
        
        #navigation ul {
            list-style: none;
            padding: 0;
            margin: 0;
        } 
        #navigation li {
            list-style: none;
            padding: 10px;
        }
        #navigation li {
            background-color: Aquamarine;
            cursor: pointer;
        }
        #navigation li:hover {
            background-color: Aqua;
        }
        
        /*
        * drop down menu
        */
        
        #ddm {
            position:fixed;
            top:0px;
            left:0px;
        }
        #ddm li, #ddm ul {
            list-style: none;
            padding: 0;
            background-color: Gray;
            cursor:pointer;
        }
        #ddm ul {
            border:1px solid black;
        }
        #ddm > li {
            position: relative;
        }
        #ddm > li > ul {
            display: none;
            position: absolute;
            top: 100%;
            left: 0;
        }
        #ddm > li > ul > li { padding: 5px; }
        #ddm li:hover {
            background-color: #eee;
        }
        #ddm > li:hover > ul {
            display: block;
        }
        
        #blanket {
            display:none;
            width:100%;
            height:100%;
            position:fixed;
            top:0;
            left:0;
            opacity:0.5;
            background-color:Grey;
            z-index:998;
        }

        .modal-window {
            display: none;
            width: 400px;
            height: 200px;
            position: absolute;
            top: 150px;
            left: calc(50% - 201px);
            border: 1px solid black;
            background-color:White;
            padding: 20px;
            z-index:999;
        }
        .modal-label {
            display:inline-block;
            width:80px;
        }
    </style>
    
    <script>
    </script>
</head>

<body>
    <div id='title'>
        <h1 style='text-align:center'>TRU Questions & Answers</h1>
        <!-- Welcome ...! --> <!-- the username should be echoed from the php tag. -->
        <h2 style='text-align:center'>Welcome <?php echo $username ?>!</h2>
        <hr>
    </div>
    
    <div id='navigation'>
        <ul>
            <li>Post a question</li>
            <li>List questions</li>
            <li>Search questions</li>
            <li>Search answers</li>
            <li>Sign out</li>
        </ul>
    </div>
    
    <div id='content'>
        <p>
            Congratulations! You are now in the main page.<br>
            We will develop this page later.
        </p>
    </div>    
</body>
</html>
