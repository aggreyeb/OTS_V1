<%-- 
    Document   : index
    Created on : Jun 5, 2014, 10:46:26 AM
    Author     : MEA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <META http-equiv="Pragma" content="no-cache">
        <META HTTP-EQUIV="Expires" CONTENT="-1">
         <meta http-equiv="cache-control" content="no-cache" />
        <link href="content/bootstrap.css" rel="stylesheet" type="text/css"/>
        <script src="scripts/jquery-2.1.0.js" type="text/javascript"></script>
        <script src="scripts/bootstrap.js" type="text/javascript"></script>
        <script src="scripts/knockout-3.1.0.js" type="text/javascript"></script>
        <script src="classes/AccountWindow.js" type="text/javascript"></script>
        <title>index</title>
       
    </head>
    <body style="padding-top: 65px;">
         
              <nav class="navbar navbar-default navbar-fixed-top">
                  <div class="container">
                      
                      <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navBody">
                        <span class="sr-only"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                         <span class="icon-bar"></span>
                    </button>
                    <a href="#" class="navbar-brand">Online Test System</a>
                </div>
                      
                           <ul class="nav navbar-nav pull-right">
                             <li id="lnk-login" class="active"><a href="#">Login</a></li>
                      
                          </ul>
                      
                <div id="navBody" class="collapse navbar-collapse" >
                   
                  <ul  class="nav navbar-nav navbar-right">
                     <!-- <a href="#" class="btn dropdown-toggle" data-toggle="dropdown">zzLogin<span class="caret"></span></a>-->
                      
                      <ul id="login-dropdown" class="dropdown-menu" style="padding: 5px 10px 0px 10px;">
                          
                       <div id="div-close-login-form" class="hide">
                           <span style="color: ghostwhite">Login</span> <a id="close-login-form" href="#"><span class="pull-right glyphicon glyphicon-remove-sign"></span></a> 
                        </div> 
                          <form id="login-form">
                             
                              <div class="form-group">
                                  <label class="control-label">User name</label>
                                  <input id="txt-username" data-bind="value:username" class="form-control" type="text" placeholder="Enter user name"/>
                              </div>
                              <div class="form-group">
                                  <label  class="control-label">Password</label>
                                  <input id="txt-password" data-bind="value:password" class="form-control" type="password" placeholder="Enter your password"/>
                              </div>
                              
                               <div class="form-group">
                                  <button   data-bind="click:Login"  id="btn-login" type="button" class="btn  btn-primary ">Sign In</button
                              </div>
                              <p></p>
          
                              <div  data-bind="visible:loginmessage" id="signin-alert" class="form-group">
                                  <p id="alert-text" class="alert alert-danger "  >
                                    Invalid Credentials                        
                                </p>  
                              </div>
                       
                          </form>
                      </ul
                      
                  </ul>  
                      
                  </div>
                 
            </nav> 
        
        <div class="container">
            <div class="jumbotron">
                <h4> Welcome! General Information about  the system will be here .Please read role specific information below</h4>
            </div>
            <div class="row">
                <div class="col-lg-4">
                
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Administrator</h3>
                        </div>
                    </div>
                    <div class="panel-body">
                        Administrator information
                    </div>
                    <div class="panel-footer">
                        
                    </div>
                </div>
                <div class="col-lg-4">
                 <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Student</h3>
                        </div>
                    </div>
                    <div class="panel-body">
                        Student Information information
                    </div>
                    <div class="panel-footer">
                        
                    </div>

                </div>
                <div class="col-lg-4">
                     <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Teacher</h3>
                        </div>
                    </div>
                    <div class="panel-body">
                        Teacher Information information
                    </div>
                    <div class="panel-footer">
                        
                    </div>
                </div>
            </div>
        </div>
        <footer>
            <nav class="navbar navbar-default navbar-fixed-bottom"></nav>
        </footer>
           <script>
            $(document).ready(function(){
            // ko.applyBindings(new accountView(),$("#login-form")[0]);
            var account= new accountView();
            $("#login-dropdown").click(function(e){
                e.stopPropagation();
                });
            });
      
        </script> 
        
        
         <script>
            
          /*
               $('#lnk-login').popover({html:true, title:"Testing",placement:'bottom',trigger:'click',
                 title:function(){
                     return  $("#div-close-login-form").html();
                 },content:function(){
                     return  $("#login-form").html();
                 }}  
             
               );   
       
         
              $('#lnk-login').on('show.bs.popover', function () {
                 
                
               });
               
               $('#lnk-login').on('shown.bs.popover', function () {
                  $(window).keydown(function(e){
                       if(e.keyCode == 13){
                         $("#btn-login").click();
                       }
                  });
                 
                 $("#btn-login11").click(function(){
                      var userName=$("#txt-username").val();
                       alert(userName);
                 });
                  $("#close-login-form").click(function(){
                      $('#lnk-login').popover('hide');
                  });
               });
               */
        </script>
      </body>
</html>
