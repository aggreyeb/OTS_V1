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
            $(function(){
                $("#header").load("templates/header.html",function(){
                    $("#logout").click(function(){
                        alert("LOGOUT");
                    });
                 
                });  
               
                $("#leftmenu").load("templates/leftmenu.html",function(){
                    //Register Click envents
                });
                $("#footer").load("templates/footer.html",function(){
                    
                });
               
            });
        </script>
       
        <script>
           
  
            var view;
            $(document).ready(function(){
            
           
              //********* New Knowledge map popover **************// 
              $('#btn-new-knowledgemap').popover({html:true, title:"Testing",placement:'bottom',
                 title:function(){
                     return  $("#frm-knowledgemap-Title").html();
                 },content:function(){
                     return $("#frm-knowledgemap").html();
                 }}   
               );
  
               
                 //********* Start Rename Knowledge map popover **************// 
               
                
                
                 //********* End Rename Knowledge map popover **************// 
                
               view =new   OTS.Views.KnowledgeMapsView();
                ko.applyBindings(view,$("#div-knowledgemaps-list")[0]);
            
             
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
        </style>
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
              
                <div class="col-lg-9">
                    <div class="col-lg-3">
                        <!-- knowledge map tree-->
                        
                        <div class="row">
                            
                            <div class="panel panel-primary">
                                <div class="panel-heading">Concept Hierarchy</div>
                                <div class="panel-body">
                                    <div id="tv-knowledgemap"></div>  
                                </div>
                            </div>
                        </div>
                           
                        
                    </div>   
                    <div class="col-lg-1"><!--Spacing--></div>
                    <div class="col-lg-8">
                        <div  class="row">
                            <div id="div-conceptSchema-Container">
                                <script>
                                    $("#div-conceptSchema-Container").load("templates/teacher/conceptschema.html",function(){
                                        
                                    });
                                </script>
                            </div>
                         
                        </div>
                        <div class="row">
                 
                            <div  class="panel panel-primary hide">
                                <div class="panel panel-heading">
                                    Concept Schema 
                                </div>
                                <div class="panel-body">
                                    <table class="table table-striped table-responsive">
                                        <thead>
                                            <a  id="btn-new-concept-schema" class=" btn btn-info btn-sm pull-left" href="#" >New</a> 
                                            <tr>
                                                <th>Relation Name</th>
                                                <th>Concept Name</th>
                                                <th>Concept Action</th>
                                                <th>Attribute Name</th>
                                                <th>Concept Value</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>is</td>
                                                <td>vegetative organ</td>
                                                 <td></td>
                                                 <td></td>
                                                 <td></td>
                                            </tr>
                                             <tr>
                                                <td>can</td>
                                                <td>water</td>
                                                 <td>absorb</td>
                                                 <td></td>
                                                 <td></td>
                                            </tr>
                                        </tbody>
                                    </table>
                                   
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
                                                 <input data-bind="value:Display().knowledgeMapName,valueUpdate: 'afterkeydown'" type="text" class="form-control"  placeholder="Name">
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
            
            <!-- Concept Schema form -->
            
          <div  id="frm-conceptSchem-Title" class="hide">New Concept Schema</div>
          <div  id="frm-conceptSchema" class="hide">
             <form class="form-horizontal">
                <div class="form-group">
                   <label  class="control-label col-xs-2">Relation Name</label>
                   <div class="col-xs-10">
                   <input type="text" class="form-control" id="inputEmail" placeholder="Relation Name">
                  </div>
               </div>
             <div class="form-group">
               <label class="control-label col-xs-2">Concept Name</label>
               <div class="col-xs-10">
                <input type="text" class="form-control"  placeholder="Concept Name">
              </div>
           </div>
          <div class="form-group">
            <label class="control-label col-xs-2">Concept Action</label>
            <div class="col-xs-10">
                <input type="text" class="form-control"  placeholder="Concept Action">
            </div>
        </div>
        
          <div class="form-group">
            <label class="control-label col-xs-2">Attribute Name</label>
            <div class="col-xs-10">
                <input type="text" class="form-control"  placeholder="Attribute Name">
            </div>
        </div>
          <div class="form-group">
            <label class="control-label col-xs-2">Attribute Value</label>
            <div class="col-xs-10">
                <input type="text" class="form-control"  placeholder="Attribute Value">
            </div>
        </div>
          <div class="form-group">
            <div class="col-xs-offset-2 col-xs-10">
                <button type="button" class="btn btn-primary">Submit</button>
                <button type="button" class="btn btn-default">Cancel</button>
            </div>
          </div>
      </form>

            </div>
          
          
                <div class="dropdown">
                <ul id="myMenu" class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                     <li class="edit"><a href="#edit"><i class="icon-edit"></i> Edit</a></li>
                     <li class="delete"><a href="#delete"><i class="icon-remove"></i> Delete</a></li>
                     <li class="divider"></li>
                     <li class="add"><a href="#add"><i class="icon-plus"></i> Add</a></li>
               </ul>
               </div>

        <footer id="footer">
         
        </footer>
           
    </body>
</html>
