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
         <META http-equiv="Pragma" content="no-cache">
         <META HTTP-EQUIV="Expires" CONTENT="-1">
         <meta http-equiv="cache-control" content="no-cache" />
        <title>Knowledge map</title>
        <link href="content/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="content/jqtree.css" rel="stylesheet" type="text/css"/>
        <link href="content/jquery.dataTables.css" rel="stylesheet" type="text/css"/>
        <link href="content/bootstrap-select.css" rel="stylesheet" type="text/css"/>
        <script src="scripts/jquery-2.1.0.js" type="text/javascript"></script>
        <script src="scripts/bootstrap.js" type="text/javascript"></script>
        <script src="scripts/tree.jquery.js" type="text/javascript"></script>
        <script src="scripts/jquery.dataTables.js" type="text/javascript"></script>
        <script src="scripts/bootstrap-select.js" type="text/javascript"></script>
        <script src="scripts/knockout-2.2.0.js" type="text/javascript"></script>
        <script src="scripts/jqTreeContextMenu.js" type="text/javascript"></script>
        <script src="classes/Page.js" type="text/javascript"></script>
        <script src="classes/teacher/TeacherKnowledgeMapPage.js" type="text/javascript"></script>
        <script src="classes/teacher/KnowledgeMapsView.js" type="text/javascript"></script>
        <script src="classes/teacher/teacherKnowledegeMapPageModule.js" type="text/javascript"></script>
        <script>
          <% 
       
      
         if (request.getHeader("Referer") == null) {

             response.sendRedirect("index.jsp" );
          }
   
        %>
            
        </script>
        <script>
            //var menu={knowlegemapcount:'#bg-knowledgemap-count'};
           /*
            $(function(){
                $("#header").load("templates/header.html",function(){
                    $("#logout").click(function(){
                        //alert("LOGOUT");
                        window.location.href="index.jsp";
                    });
                 
                });  
               
                $("#leftmenu").load("templates/leftmenu.html",function(){
                    //Register Click envents
                });
                $("#footer").load("templates/footer.html",function(){
                    
                });
               
            });
          */
        </script>
       
        <script>
           
  
            var view;
            $(document).ready(function(){
            
         
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
               
                <div class="col-lg-3">
                       <div id="leftmenu">
                           <!-- Left Menu -->
                           <div id="div-teacher-menu" class="table-responsive">
                                <div class="list-group">
                                     <a href="#" class="list-group-item ">
                                         <span class="glyphicon glyphicon-book"></span> Assigned Courses <span class="badge">2</span>
                                     </a>
                                     <a href="#" class="list-group-item active">
                                         <span class="glyphicon glyphicon-file"></span> Knowledge Maps <span id="bg-knowledgemap-count" data-bind="text:Display().knowledgeMaplist.length" class="badge">2</span>
                                     </a>

                                    <a href="#" class="list-group-item">
                                         <span class="glyphicon glyphicon-import"></span> Import Knowledge Maps 
                                     </a>
                                     <a href="#" class="list-group-item">
                                         <span class="glyphicon glyphicon-list"></span> Student Accounts <span class="badge">0</span>
                                     </a>
                                     <a href="#" class="list-group-item">
                                         <span class="glyphicon glyphicon-list-alt"></span> Tests <span class="badge">0</span>
                                     </a>

                                 </div>
    
                        </div>
                       </div>
                 </div>
              
                <div class="col-lg-9">
                    <div class="col-lg-3">
                        <!-- knowledge map tree-->
                        
                        <div class="row">
                            
                            <div class="panel panel-primary"> <!-- Concept Hierarchy -->
                                <div class="panel-heading">Concept Hierarchy</div>
                                <div class="panel-body">
                                 
                                        <div class="text-right">
                                            <a id="lnk-addconceptnode" data-bind="event:{click:function(event){Display().conceptHierarchy.Add(event)}}" href="#"><span class="glyphicon  glyphicon-plus-sign">Add</span></a>|
                                            <a id="lnk-updateconceptnode" data-bind="event:{click:function(event){Display().conceptHierarchy.Update(event)}}" href="#"><span class="glyphicon glyphicon-pencil"></span>Update</a> |
                                            <a id="lnk-deleteconceptnode" data-bind="event:{click:function(event){Display().conceptHierarchy.Delete(event)}}" href="#"><span class="glyphicon glyphicon-remove-sign"></span>Delete</a>
                                        </div>
                                       <form class="form-inline">
                                           <div class="form-group">
                                               <label></label>
                                               <input id="txtSelectedItem" data-bind="value:Display().conceptHierarchy.conceptNodeName" type="text" class="form-control"/>
                                           </div>
                                            <div class="form-group">
                                               <label></label>
                                               <Select  id="sel-relationType" class="form-control" data-bind="options:Display().conceptHierarchy.relationTypes,optionsText:'id',value:Display().conceptHierarchy.selectedRelationType">
                                                  
                                               </Select>
                                           </div>
                                       </form><p></p>
                                    <div >
                                      <!--  <span>Success!</span><span>Node Added</span> -->    
                                    </div><p></p>
                                    <div >
                                       <div id="tv-knowledgemap"></div>    
                                    </div>    
                                   
                                </div>
                            </div>
                        </div>
                           
                        
                    </div>   
                    <div class="col-lg-1"><!--Spacing--></div>
                    <div class="col-lg-8">
                       
                        <div class="row">
                 
                            <div  class="panel panel-primary">
                                <div class="panel panel-heading">
                                    Concept Schema <span data-bind="text:Display().conceptSchemas.conceptSchemaAppendNodeSelected">(Test)</span>
                                </div>
                                <div class="panel-body">
                                    <table class="table table-striped table-responsive">
                                        <thead>
                                           <!-- <a id="lnkConceptSchemaNew" data-bind="event:{click:function(event){Display().conceptSchemas.newConceptSchema(event)}}" id="btn-new-concept-schema" class=" btn btn-info btn-sm pull-left" href="#" >New</a>-->
                                        <button class="btn btn-info btn-sm pull-left" data-bind="enable:Display().conceptSchemas.conceptSchemaNewButtonEnable ,event:{click:function(event){Display().conceptSchemas.newConceptSchema(event)}}" >New</button>
                                            <tr>
                                                <th>Relation Name</th>
                                                <th>Concept Name</th>
                                                <th>Concept Action</th>
                                                <th>Attribute Name</th>
                                                <th>Attribute Value</th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tbody data-bind="foreach:Display().conceptSchemaItems">
                                            <tr>
                                                <td data-bind="text:relationname"></td>
                                                <td data-bind="text:conceptname"></td>
                                                <td data-bind="text:conceptaction"></td>
                                                <td data-bind="text:attributename"></td>
                                                <td data-bind="text:attributevalue"></td>
                                                <td>
                                                   <div class="btn-group">
                                                     
                                                        <a class="btn btn-primary dropdown-toggle" data-toggle="dropdown" href="#">
                                                            Action
                                                            <span class="caret"></span>
                                                        </a>
                                                             <ul class="dropdown-menu">
                                                              <!-- dropdown menu links -->
                                                              <li><a data-bind="event:{click:function(item,event){$parent.Display().conceptSchemas.editConceptSchema(item,event)}}" href="#">Edit</a></li>
                                                              <li><a data-bind="event:{click:function(item,event){$parent.Display().conceptSchemas.deleteConceptSchema(item,event)}}" href="#">Delete</a></li>
                                                             </ul>
                                                   </div>
                                                </td>
                                            </tr>
                                            
                                        </tbody>
                                    </table>
                                   
                                </div>
                                <div data-bind="visible:Display().conceptSchemas.conceptSchemaFormVisible" class="panel-footer">
                               <!-- ************************************** Concept Schema Form Start-->
                                  <hr>
                               <div > <!-- Concept Schema form panel-->
                                  <div data-bind="visible:Display().conceptSchemas.conceptSchemaMessageVisible,attr:{'class':Display().responseBoxStyle}" class="alert alert-danger">
                                      <a  data-bind="event:{click:function(event){Display().conceptSchemas.closeconceptSchemaResponseDialog(event)}}" href="#" class="close" data-hide="alert">&times;</a>
                                      <strong data-bind="text:Display().conceptSchemas.conceptSchemaResponseMessageDialog">Error!</strong> <span data-bind="text:Display().conceptSchemas.conceptSchemaResponseMessage">A problem has been occurred while submitting your data.</span>
                                  </div>
                                 <form class="form-horizontal ">
                                     <h4><span data-bind="text:Display().conceptSchemas.conceptSchemaformHeading" class="label label-default">Create New</span></h4>
                                     <div class="form-group">
                                             <label  class="control-label col-xs-2">Relation Name</label>
                                             <div class="col-xs-10">
                                                 <input id="txt-conceptschema-relationname" data-bind="value:Display().conceptSchemas.relationName,valueUpdate: 'afterkeydown'" type="text" class="form-control"  placeholder="Relation Name - Required Field">
                                              </div>
                                         </div>
                                       <div class="form-group">
                                          <label class="control-label col-xs-2">Concept Name</label>
                                          <div class="col-xs-10">
                                              <input data-bind="value:Display().conceptSchemas.conceptName" type="text" class="form-control"  placeholder="Concept Name">
                                          </div>
                                        </div>  
                                         <div class="form-group">
                                          <label class="control-label col-xs-2">Concept Action</label>
                                          <div class="col-xs-10">
                                              <input data-bind="value:Display().conceptSchemas.conceptAction" type="text" class="form-control"  placeholder="Concept Action">
                                          </div>
                                        </div>  
                                      <div class="form-group">
                                          <label class="control-label col-xs-2">Attribute Name</label>
                                          <div class="col-xs-10">
                                              <input data-bind="value:Display().conceptSchemas.attributeName" type="text" class="form-control"  placeholder="Attribute Name">
                                          </div>
                                       </div> 
                                      <div class="form-group">
                                          <label class="control-label col-xs-2">Attribute Value</label>
                                          <div class="col-xs-10">
                                              <input data-bind="value:Display().conceptSchemas.attributeValue" type="text" class="form-control"  placeholder="Attribute Value">
                                          </div>
                                       </div>  
                                    <div class="form-group">
                                      <div class="col-xs-offset-2 col-xs-10">
                                          <button data-bind="event:{click:function(event){Display().conceptSchemas.submitConceptSchema(event)}}" type="buttton" class="btn btn-primary">Submit</button>
                                          <button data-bind="event:{click:function(event){Display().conceptSchemas.cancelConceptSchema(event)}}" type="buttton" class="btn btn-default">Cancel</button>
                                     </div>
                                    </div>
                                </form>
                             </div> <!--End Concept Schema Form-->
                                </div>
                             
                            </div>
                           
                        </div>
                        <div id="div-knowledgemaps-list" class="row">
                               <div class="panel panel-primary">
                                <div class="panel panel-heading">
                                    Knowledge Maps 
                                </div>
                                <div class="panel-body">
                                    <table id="tbl-knowledgemap" class="table table-striped table-responsive">
                                        <thead>
                                            <a  data-bind="event:{click:function(event){NewKnowledgeMap(event)}}" class=" btn btn-info btn-sm pull-left" href="#" >New</a> 
                                            <tr>
                                                <th>Id</th>
                                                <th>Name</th>
                                                <th>Description</th>
                                                <th></th>
                                            
                                            </tr>
                                        </thead>
                                        <tbody data-bind="foreach:Display().knowledgeMaplist">
                                            <tr data-bind="event:{click:function(item,event){$parent.KnowledgeMapSelected(item,event)}}">
                                                <td data-bind="text:id"></td>
                                                <td data-bind="text:label"></td>
                                                <td data-bind="text:description"></td>
                                                 <td>
                                                    <div class="btn-group">
                                                        <button id="btn-rename-knowledgemap" data-bind="event:{click:function(item,event){$parent.RenameKnowledgeMap(item,event)}}" class="btn btn-primary">Rename</button>
                                                        <a class="btn btn-info dropdown-toggle" data-toggle="dropdown" href="#">
                                                            Or
                                                            <span class="caret"></span>
                                                        </a>
                                                             <ul class="dropdown-menu">
                                                              <!-- dropdown menu links -->
                                                              <li><a data-bind="event:{click:function(item,event){$parent.DuplicateKnowledgeMap(item,event)}}" href="#">Duplicate</a></li>
                                                              <li><a data-bind="event:{click:function(item,event){$parent.DeleteKnowledgeMap(item,event)}}" href="#">Delete</a></li>
                                                             </ul>
                                                   </div>
                                                 </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                   
                                </div
                                <div class="panel-footer"> <!-- Start footer -->
                                    <hr>
                                 
                              <div data-bind="visible:Display().knowledgeMapFormVisible"> <!-- Knowledge map form panel-->
                                  <div data-bind="visible:Display().responseMessageVisible,attr:{'class':Display().responseBoxStyle}" class="alert alert-danger">
                                      <a  data-bind="event:{click:function(event){CloseResponseDialog(event)}}" href="#" class="close" data-hide="alert">&times;</a>
                                      <strong data-bind="text:Display().responseMessageDialog()">Error!</strong> <span data-bind="text:Display().responseMessage()">A problem has been occurred while submitting your data.</span>
                                  </div>
                                 <form class="form-horizontal ">
                                     <h4><span data-bind="text:Display().formHeading()" class="label label-default">Create New</span></h4>
                                     <div class="form-group">
                                             <label  class="control-label col-xs-2">Name</label>
                                             <div class="col-xs-10">
                                                 <input id="txt-knowledgemapname" data-bind="value:Display().knowledgeMapName,valueUpdate: 'afterkeydown'" type="text" class="form-control"  placeholder="Name -Required Field">
                                              </div>
                                         </div>
                                       <div class="form-group">
                                          <label class="control-label col-xs-2">Description</label>
                                          <div class="col-xs-10">
                                              <input data-bind="value:Display().knowledgeMapDescription" type="text" class="form-control"  placeholder="Description">
                                          </div>
                                        </div>        
                                    <div class="form-group">
                                      <div class="col-xs-offset-2 col-xs-10">
                                          <button data-bind="event:{click:function(event){SubmitNewKnowledgeMap(event)}}" type="buttton" class="btn btn-primary">Submit</button>
                                          <button data-bind="event:{click:function(event){CancelNewKnowledgeMap(event)}}" type="buttton" class="btn btn-default">Cancel</button>
                                     </div>
                                    </div>
                                </form>
                             </div> <!-- End of knowledge map p-->
                                </div> <!-- End Footer-->
                             </div> <!-- End of List Panel-->
                        </div>
                    </div>
            </div>
        </div>
      
        <footer id="footer">
            <div class="container">
                 Footer !
            </div>
        </footer>
           
    </body>
</html>
