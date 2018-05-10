<?php
    // session_start();  // It is already started in controller.
    
    if (!isset($_SESSION['signedin']) || $_SESSION['signedin'] != 'YES')
        exit();
?>

<!DOCTYPE html>

<html>
<head>
    <title>TruQA</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <style>
    </style>
    
    <script>
        var timer = setTimeout(timeout, 600 * 1000);
        window.addEventListener('mousemove', function() {
            clearTimeout(timer);
            timer = setTimeout(timeout, 600 * 1000);
        });
        window.addEventListener('keydown', function() {  // 
            clearTimeout(timer);
            timer = setTimeout(timeout, 600 * 1000);
        });
        window.addEventListener('unload', function() {
            timeout();
        });
        function timeout() {
            document.getElementById('form-signout').submit();
        }
    </script>
</head>

<body>
    <div class='container'>
        <!-- Header -->
        <div class='row'>
            <div class='bg-primary'>
                <h1 style='text-align:center; padding-top: 10px;'>TRU Questions & Answers</h1>
                <h3 style='text-align:center; padding-bottom: 10px;'>- User: <?php echo $_COOKIE['username']; ?> -</h3>
            </div>
        </div>

        <!-- Navigation bars and content -->
        <div class='row'>
            <!-- Navigation bars -->
            <div class='col-md-2 bg-info'>
                <ul class="nav nav-pills nav-stacked">
                    <li><a id='post-question' data-toggle='modal' data-target="#modal-post-question">Post a question</a></li>
                    <li><a id='list-questions'>List questions</a></li>
                    <li><a id='list-answers'>List answers</a></li>
                    <li><a id='search-questions'>Search questions</a></li>
                    <li><a id='search-answers'>Search answers</a></li>
                    <li><a id='signout'>Sign out</a></li>
                </ul>
            </div>
            
            <!-- Content -->
            <div class='col-md-10 bg-success'>
                <div id='content'>
                </div>
            </div>
        </div>
        
        <!-- Footer -->
        <div class='row'>
            <div>
            </div>    
        </div>
        
        <!-- for 'post a question' -->
                
        <div class="modal fade" id="modal-post-question" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form id='form-post-question'>
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Post a Question</h4>
                        </div>
                        <div class="modal-body">
                            <label for='input-post-question'>Question:</label>
                            <input id='input-post-question' class='form-control' type='text' autofocus required>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                            <button id='submit-post-question' type="button" class="btn btn-primary">Submit</button>  <!-- not data-dismiss='modal' -->
                        </div>
                    </form>
                </div>
            </div>
        </div>
        
        <script>
            // When the submit button on the 'PostQuestion' modal window is clicked
            $('#submit-post-question').click(function(e) 
            {
                $('#modal-post-question').modal('hide');
                
                // Send then'PostQuestion' command using jQuery AJAX,
                //      get the result from the server and display it into ContentPane of id=content.
                
                var url = "w9_controller.php";
                var query = {page:'MainPage', command:'PostQuestion', question: $('#input-post-question').???()};
                
                $.load(url, query, function(data) {
                    $('#content').html(data);
                });
            });
        </script>

        <!-- for 'list questions' -->
                
        <script>
            // When the 'ListQuestions' navigation bar is clicked
            $('#list-questions').click(function(e) {
                
                // Send then'ListQuestions' command using AJAX
                //      get the result from the server and display the table into ContentPane of id=content.
                
                var url = "w9_controller.php";
                var query = {page:'MainPage', command:'ListQuestions'};
                
                $.load(url, query, function(data) {  // data: linear array of associative array
                    var data = JSON.parse(data);
                        
                    // construct code of table
                    var str = "";
                    if (data.length > 0) {
                        str = '<table class="table">';
                        str += '<tr>';
                        for (var p in data[0])
                            str += '<th>' + ??? + '</th>';
                        str += '</tr>';
                        for (var i = 0; i < data.length; i++) {
                            str += '<tr>';
                            for (var p in ???)
                                str += '<td>' + ??? + '</td>';
                            str += '</tr>';
                        }
                        str += '</table>';
                    }
                        
                    // display the table code to the <div> of id='content' using jQuery
                    $('#content').html('<h2>List questions</h2><br>' + str);  
                });
            });
        </script>

        <!-- Sign out, using an invisible form -->

        <form id='form-signout' method='post' action='w9_controller.php' style='display:none'>
            <input type='hidden' name='page' value='MainPage'>
            <input type='hidden' name='command' value='SignOut'>
        </form>
        <script>
            // When the 'SignOut' button is clicked
            document.getElementById('signout').addEventListener('click', function() {
                document.getElementById('form-signout').submit();
            });
        </script>
    </div>
</body>
</html>
