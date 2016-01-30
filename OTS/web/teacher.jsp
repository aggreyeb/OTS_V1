<%-- 
    Document   : teacher
    Created on : Jun 8, 2014, 10:50:48 AM
    Author     : MEA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Teacher</title>
        <link href="content/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="content/formly.css" rel="stylesheet" type="text/css"/>
        <script src="scripts/jquery-2.1.0.js" type="text/javascript"></script>
        <script src="scripts/bootstrap.js" type="text/javascript"></script>
        <script src="scripts/knockout-2.2.0.js" type="text/javascript"></script>
        <script src="classes/teacher/teacher.js" type="text/javascript"></script>
        <script src="scripts/formly.js" type="text/javascript"></script>
        <script>
            $(function(){
              //  $("#header").load("templates/header.html");  
                $("#leftmenu").load("templates/leftmenu.html");
                $("#footer").load("templates/footer.html");
                $("#teacher-courses").load("templates/teacher/courses.html");
            });
        </script>
       
        <script>
               
                $.get("templates/header.html",{},function(msg){
                $("#header").empty();
                $("#header").append(msg);
              
            });
             
        </script>
        
         <script>
                $(document).ready(function(){
                    
                    $("#MyForm").formly();
                });
                    /*
                    $("#header").find("#logout").click(function(e){
                       // alert("LOGOUT");
                       e.preventDefault();
                       alert("1224");
                    }); */
                           	
            </script>
            
           
    </head>
    <body style="padding-top: 65px;">
        <header id="header" >
           
        </header
       
        <div data-role="content">
       
        <div id="mainContainer" class="container">
            <div class="row">
              
                <div class="col-lg-3">
                       <div id="leftmenu">
                           <!-- Left Menu -->
                       </div>
                 </div>
               
                <div class="col-lg-1">
                    
                    
                </div>
                <div class="col-lg-8">
                    <fieldset>
                        <legend>Course Information</legend>
                        <form class="form-horizontal"> <!-- Start form -->
                     <div class="form-group">
	             <label for="course-number" class="col-lg-3 control-label">Number</label>
		      <div class="col-lg-6">
                          <input type="text" class="form-control" name="course-number" value="100" readonly="true" >
		      </div>
		    </div>      
                              
                    <div class="form-group">
		      <label for="course-name" class="col-lg-3 control-label">Name</label>
		      <div class="col-lg-6">
                          <input type="text" class="form-control" name="course-name" value="Introduction to Botany" readonly="true" >
		       </div>
		   </div>
                      
                      
                     <div class="form-group">
		       <label for="date-assigned" class="col-lg-3 control-label">Date Assigned</label>
			 <div class="col-lg-6">
                             <input type="text" class="form-control" name="date-assigned" value="06/11/2014" readonly="true">
		          </div>
		     </div>
                      
                      <div class="form-group">
		       <label for="date-completed" class="col-lg-3 control-label">Date Completed</label>
			 <div class="col-lg-6">
                             <input type="text" class="form-control" name="date-completed" placeholder="mm/dd/yyyy">
		          </div>
		     </div>
                      <div class="form-group">
		       <div class="col-lg-offset-3 col-lg-10">
		        <button class="btn btn-primary">Save</button> <a href="#" class="btn btn-default">Cancel</a>
		       </div>
		     </div>
				
                      
                  </form> <!-- End Form -->
                    </fieldset><p></p>
                  
                       <div class="row">
                           
                             <div id="teacher-courses" ></div>  
                       </div>
                 
                    </div
                    
            </div>
          
            </div>
        </div>
        <footer id="footer">
          
        </footer>
       
    </body>
</html>
