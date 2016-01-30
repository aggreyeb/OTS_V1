<%-- 
    Document   : test
    Created on : Jun 23, 2014, 3:20:23 PM
    Author     : MEA
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="content/bootstrap.css" rel="stylesheet" type="text/css"/>
        <script src="scripts/jquery-2.1.0.js" type="text/javascript"></script>
        <script src="scripts/bootstrap.js" type="text/javascript"></script>
        <script src="scripts/knockout-2.2.0.js" type="text/javascript"></script>
        <script src="classes/Page.js" type="text/javascript"></script>
        <script src="classes/teacher/TeacherKnowledgeMapPage.js" type="text/javascript"></script>
        <script src="classes/teacher/KnowledgeMapsView.js" type="text/javascript"></script>
        <script src="classes/teacher/teacherKnowledegeMapPageModule.js" type="text/javascript"></script>
        <script src="classes/teacher/Message.js" type="text/javascript"></script>
        <title>JSP Page</title>
        
        <script>
          
            $(document).ready(function(){
                var view=new   KnowledgeMapsView();
                ko.applyBindings(view,$("#panKnowledgeMapList")[0]);
            });
        </script>
    </head>
    <body style="text-align: center">
       
        <div id="panAccount">
            <p>
            <label>UserName</label>
            <input data-bind="value:ShowDescription().UserName"  type="text"/>
        </p>
        
          <p>
            <label>Password</label>
            <input data-bind="value:ShowDescription().Password" type="text"/>
        </p>
        <div>
            <button data-bind="click:Login" type="button">Login</button>
        </div> 
        
        <div id="panKnowledgeMapList" style="text-align: center" >
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Description</th>
                    </tr>
                </thead>
                <tbody data-bind="foreach:DisplayList()" >
                    <tr>
                        <td data-bind="text:id"></td>
                        <td data-bind="text:label"></td>
                        <td data-bind="text:description"></td>
                    </tr>
                </tbody>
            </table>
            <div>
                   <button data-bind="click:New" type="button">New</button>
            </div>
        </div>
        
        </div>
       
    </body>
</html>
