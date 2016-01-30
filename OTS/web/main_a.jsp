<%-- 
    Document   : teacher
    Created on : Jun 8, 2014, 10:50:48 AM
    Author     : MEA
--%>

<%@page import="OTS.ObjectModels.UserProfile"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <META http-equiv="Pragma" content="no-cache">
         <META HTTP-EQUIV="Expires" CONTENT="-1">
         <meta http-equiv="cache-control" content="no-cache" />
        <title>Main</title>
        <link href="content/themes/base/jquery.ui.all.css" rel="stylesheet" type="text/css"/>
        <link href="content/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
        
        <link href="content/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="content/jqtree.css" rel="stylesheet" type="text/css"/>
        <link href="content/jquery.dataTables.css" rel="stylesheet" type="text/css"/>
        <link href="content/bootstrap-select.css" rel="stylesheet" type="text/css"/>
        
        <!--<script src="scripts/jquery-2.1.0.js" type="text/javascript"></script>-->
        <script src="scripts/jquery-1.9.1.js" type="text/javascript"></script>
       <!-- <script src="scripts/jquery-ui-1.8.24.min.js" type="text/javascript"></script>-->
       <script src="scripts/jquery-ui-1.10.3.min.js" type="text/javascript"></script>
       
        <script src="scripts/bootstrap.js" type="text/javascript"></script>
        <script src="scripts/tree.jquery.js" type="text/javascript"></script>
        <script src="scripts/jquery.dataTables.js" type="text/javascript"></script>
        <script src="scripts/bootstrap-select.js" type="text/javascript"></script>
        <script src="scripts/knockout-2.2.0.js" type="text/javascript"></script>
        <script src="scripts/jqTreeContextMenu.js" type="text/javascript"></script>
        <script src="classes/teacher/KnowledgeMapsView.js" type="text/javascript"></script>

        <script src="classes/teacher/teacher-main-menu.js" type="text/javascript"></script>
        <script src="classes/teacher/teacher-welcome-view.js" type="text/javascript"></script>
        <script src="classes/admin/admin-accounts-view.js" type="text/javascript"></script>
        <script src="classes/admin/admin-all-knowledgemaps.js" type="text/javascript"></script>
        <script src="classes/admin/admin-courses-view.js" type="text/javascript"></script>
       
        <script src="classes/admin/admin-course-assignment-viewmodel.js" type="text/javascript"></script>
        <script src="classes/common/student-account-viewmodel.js" type="text/javascript"></script>
        <script src="classes/admin-all-knowledgemaps-viewmodel.js" type="text/javascript"></script>
        <script src="classes/admin/admin-course-management-viewmodel.js" type="text/javascript"></script>
        
        
        <script src="scripts/knockout.mapping-latest.js" type="text/javascript"></script>
        <script src="scripts/knockout.validation.js" type="text/javascript"></script>
        <script src="scripts/knockout-bootstrap.min .js" type="text/javascript"></script>
        
        <script>
          <% 
       
         if (request.getHeader("Referer") == null) {

             response.sendRedirect("./index.jsp" );
          }
   
        %>
        <%
           int timeout = session.getMaxInactiveInterval();
            response.setHeader("Refresh", timeout + "; URL =./index.jsp");
        %>
        </script>
       
        
        <script>
           
  
            var view;
            $(document).ready(function(){
             
             var menu= new OTS.Views.TeacherMainMenu();
              menu.addSubscriber(new OTS.Views.WelcomeView());
              menu.addSubscriber(new OTS.Views.AdminAccountsView());
              menu.addSubscriber(new OTS.Views.AdminKnowledMapsView());
              menu.addSubscriber(new OTS.Views.AdminCoursesView());
              
              $('#btn-new-knowledgemap').popover({html:true, title:"Testing",placement:'bottom',
                 title:function(){
                     return  $("#frm-knowledgemap-Title").html();
                 },content:function(){
                     return $("#frm-knowledgemap").html();
                 }}   
               );
  
           
               view =new   OTS.Views.KnowledgeMapsView();
                ko.applyBindings(view,$("#mainContainer")[0]);
                
                 $("#sel-relationType").selectpicker();
             
            $("#sel-knowledgemap-action").selectpicker();
            
             $("#btn-new-concept-schema").popover({html:true, title:"Testing",placement:'bottom',
                 title:function(){
                     return  $("#frm-conceptSchem-Title").html();
                 },content:function(){
                     return $("#frm-conceptSchema").html();
                 }});
              
            });
            
        </script>
        <style>
          
             .form-control {width:300px;}
             .popover {max-width:600px;} 
             .popover-title{width: 400px;}
             .popover-content{width:400px;}
             .action{}
             // body { overflow-x: hidden;}
             .container {
                    max-width: 100%;
   /* This will remove the outer padding, and push content edge to edge */
                      padding-right: 0;
                      padding-left: 0;
                     }
                     
                     .push, footer {
                        height: 63px;
                    }
                    
                    .form-inline .form-group  input {
                      width:190px;
                    
                    }
                   
        </style>
    </head>
    <body style="padding-top: 65px;">
        <header id="header">  </header>
        <div id="mainContainer" class="container">
           
            <div class="row">
               
                <div class="col-lg-3"> <!-- Left Menu -->
                       <div id="leftmenu">
                           <!-- Left Menu -->
                           <div id="div-teacher-menu" class="table-responsive">
                                <div class="list-group ">
                                    <a id="lnk-home" href="#" class="list-group-item active ">
                                         <span class="glyphicon glyphicon-home"></span> Home 
                                     </a>
                                    <a id="lnk-accounts" href="#" class="list-group-item ">
                                         <span class="glyphicon glyphicon-book"></span> Accounts 
                                     </a>
                                    <a id="lnk-courses" href="#" class="list-group-item ">
                                         <span class="glyphicon glyphicon-folder-open"></span> Courses 
                                     </a>
                                     <a id="lnk-knowledgemaps" href="#" class="list-group-item ">
                                         <span class="glyphicon glyphicon-file"></span> Knowledge Maps
                                     </a> 
                                 </div>
    
                        </div>
                       </div>
                 </div>
              
          <div class="container">
                <div class="col-lg-9">
                    
                        <div class="row">
                      <div id="view-container" class=" panel panel-primary">
                          <div class=" panel-heading"><span id="lbl-selected-menuitem">Welcome</span></div>
                        <div id="view-content" class=" panel-body">
                           General Information here
                        </div>
                     </div>     
                    </div>
                    </div>
                </div>
        </div>
      
        <footer id="footer">
            <div class="container">
                 
            </div>
        </footer>
    </body>
</html>
