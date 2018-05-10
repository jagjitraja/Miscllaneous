<?php
?>

<!DOCTYPE html>

<html>
<head>
    <title>TruQA</title>
    <style>
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
    <h1 style='text-align:center'>TRU Questions & Answers</h1>
    <hr>
    
    <div>
        <p>Congratulations! You are now in the main page.<br>
            We will develop this page later.
        </p>
    </div>    
</body>
</html>
