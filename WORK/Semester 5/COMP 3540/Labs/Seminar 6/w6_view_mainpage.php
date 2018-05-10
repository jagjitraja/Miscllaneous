<!DOCTYPE html>

<html>
<head>
    <title>TruQA</title>
    
    <!-- for Bootstrap -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

	<!-- Latest compiled JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <style>
    </style>
    
    <script>

        // when the window is unloaded, i.e., closed
        window.addEventListener('unload', function() {
            signout();  // Just call the signout function. It might not work. Why ???????
        });
        
        function signout() {
				
			document.getElementById("form-signout").submit();
			      // submit the 'form-signout' form.
                  // A similar example is at the end of this file.
        }

        // When the 'sign-out' navigation pill is clicked
        // You need to use jQuery.
        $(document).ready(function() {
            $("sign-out").click(signout());
        });
    </script>
</head>

<body>
    <div class='container-fluid'>  <!-- Think of container, rows, columns -->
        <!-- Header -->
        <div class='row'>
            <div class='bg-primary'>
                <h1 style='text-align:center; padding-top: 10px;'>TRU Questions & Answers</h1>
                <h3 style='text-align:center; padding-bottom: 10px;'>- User: <?php echo $username ?> -</h3>
            </div>
        </div>

        <!-- Navigation bars and content -->
        <div class='row'>
            <!-- Navigation bars -->
            <div class='col-lg-2 bg-info'>
                <ul class="nav nav-pills nav-stacked">
                    <li><a id='post-question' data-toggle='modal' data-target=???>Post a question</a></li>
                    <li><a id='list-questions'>List questions</a></li>
                    <li><a id='list-answers'>List answers</a></li>
                    <li><a id='search-questions'>Search questions</a></li>
                    <li><a id='search-answers'>Search answers</a></li>
                    <li><a id='sign-out'>Sign out</a></li>
                </ul>
            </div>
            
            <!-- Content -->
            <div class='col-lg-10 bg-success'>
                <div id='content'>
                </div>
            </div>
        </div>
        
        <!-- Footer -->
        <div class='row'>
            <div>
            </div>    
        </div>
        
        <!-- modal window for 'post a question' -->
                
        <div class="modal fade" id="modal-post-question" role="dialog">  <!-- modal window -->
            <div class="modal-dialog">
                <div class="modal-content">  <!-- modal content -->
                    <form id='form-post-question'>
                        <div class = 'modal-header'>  <!-- modal header -->
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Post a Question</h4>
                        </div>
                        <div class='modal-body'>  <!-- modal body -->
                            <label for='input-post-question'>Question:</label>
                            <input id='input-post-question' class='form-control' type='text' autofocus required>
                        </div>
                        <div class='modal-footer'>  <!-- modal footer -->
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                            <button id='submit-post-question' type="button" class="btn btn-primary">Submit</button>  <!-- not data-dismiss='modal' -->
                        </div>
                    </form>
                </div>
            </div>
        </div>
        
        <!-- Sign out, using an invisible form -->

        <form id='form-signout' method='post' action='./w6_controller.php' style='display:none;'>
            <input type='hidden' name='page' value='MainPage'>
            <input type='hidden' name='command' value='SignOut'>
        </form>
    </div>
</body>
</html>
