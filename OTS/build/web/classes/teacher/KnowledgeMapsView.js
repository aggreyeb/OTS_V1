var OTS=OTS||{};
OTS.Views=OTS.Views||{};


OTS.Views.ConceptNode=function(conceptNode){
    var me= this;
    me.id=conceptNode.id;
    me.identity=conceptNode.identity;
    me.label=conceptNode.label;
    me.description=conceptNode.description;
    me.children=[];
    
    OTS.Views.ConceptNode.prototype.Clear=function(){
       me.id=0;
       me.label="";
       me.description="";
    }
    OTS.Views.ConceptNode.prototype.UpdateIdentity=function(identity){
        me.identity=identity;
    }
    
    OTS.Views.ConceptNode.prototype.AddChildren=function(array){
        me.children=array;
    }
 }

OTS.Views.KnowledgeMapsView =function(){
    var action={Name:""};
    var treeView={IsInitialized:false};
    var selectedKnowledgeMapId=ko.observable(0);
    var me={};
     me.responseBoxStyle=ko.observable("alert alert-danger");
     me.responseMessage=ko.observable("Unable to save knowledge map");
     me.responseMessageVisible=ko.observable(false);
     me.responseMessageDialog=ko.observable("Error!");
     me.closeResponseMessageDialog=ko.observable(false);
     me.formHeading=ko.observable("Create New");
     me.knowledgeMapFormVisible=ko.observable(false);
     me.knowledgeMapName=ko.observable("Hello");
     me.knowledgeMapDescription=ko.observable("World");
     me.knowledgeMaplist=ko.observableArray([]);
     me.conceptSchemaItems=ko.observableArray([]);
     me.menuSelector={knowledgemapcount:null};
     me.conceptSchemas={
          selectedAction:"",
          action:{New:"newconceptschema",Edit:"editconceptschema",Delete:"deleteconceptschema"},
          conceptSchemaFormVisible:ko.observable(false),
          conceptSchemaMessageVisible:ko.observable(true),
          conceptSchemaResponseMessageDialog:ko.observable("Error!"),
          conceptSchemaformHeading:ko.observable("Create New"),
          conceptSchemaResponseMessageVisible:ko.observable(false),
          conceptSchemaResponseMessage:ko.observable(""),
          conceptSchemaNewButtonEnable:ko.observable(false),
          conceptSchemaAppendNodeSelected:ko.observable(""),
          selectetConceptSchema:{},
          relationName:ko.observable(""),
          conceptName:ko.observable(""),
          conceptAction:ko.observable(""),
          attributeName:ko.observable(""),
          attributeValue:ko.observable(""),
          closeconceptSchemaResponseDialog:function(event){
             me.conceptSchemas.conceptSchemaMessageVisible(false);
          },resetForm:function(){
              me.conceptSchemas.relationName("");
              me.conceptSchemas.conceptName("");
              me.conceptSchemas.conceptAction("");
              me.conceptSchemas.attributeName("");
              me.conceptSchemas.attributeValue("");
          }, 
          submitConceptSchema:function(event){
             $("#txt-conceptschema-relationname").css("background-color","");
              if(!isConceptSchemaValid()){
                $("#txt-conceptschema-relationname").css("background-color","lightyellow");
                return;
            }
              var action=me.conceptSchemas.selectedAction;
              var id="";
               if (me.conceptSchemas.selectetConceptSchema !=null){
                   id=me.conceptSchemas.selectetConceptSchema.id;
               }
               var conceptSchema={
                   id:id,
                   rootid:me.conceptHierarchy.selectedNode.root,
                   parentidentity:me.conceptHierarchy.selectedNode.identity,
                   relationname:me.conceptSchemas.relationName(),
                   conceptname:me.conceptSchemas.conceptName(),
                   conceptaction:me.conceptSchemas.conceptAction(),
                   attributename:me.conceptSchemas.attributeName(),
                   attributevalue:me.conceptSchemas.attributeValue()
               };
              var jsonData =JSON.stringify(conceptSchema);
              var data={action:action,conceptschema:jsonData};
              //  $.post("KnowledgeMapServlet",data,doUpdate);
                doSubmit(data,conceptSchema);
             
          },
          dispalyConceptSechemaMessge:function(msg){           
              
               if(msg==""){
                   me.responseBoxStyle("alert alert-info");
                   me.conceptSchemas.conceptSchemaMessageVisible(true);
                   me.conceptSchemas.conceptSchemaResponseMessageDialog("Error");
                   me.conceptSchemas.conceptSchemaResponseMessage("Error occures whiles performing opertion");
                   return;
               }
               var message =JSON.parse(msg);
               if(message.response.status==="ok"){
                 me.conceptSchemas.conceptSchemaMessageVisible(true);
                 me.responseBoxStyle("alert alert-success");
                 me.conceptSchemas.conceptSchemaResponseMessageDialog("Success!");
                 me.conceptSchemas.conceptSchemaResponseMessage("Opeartion completed Successfully"); 
                 me.conceptSchemas.resetForm();
                 me.conceptSchemas.conceptSchemaformHeading("Create New");
                 action.Name="new";
               }else{
                 me.conceptSchemas.conceptSchemaMessageVisible(true);
                 me.responseBoxStyle("alert alert-info");
                 me.conceptSchemas.conceptSchemaResponseMessageDialog("Error!");
                 me.conceptSchemas.conceptSchemaResponseMessage(" Error occures whiles performing opertion"); 
               }
          }, 
          refreshKnowledgeMapList:function(){
            $.post("KnowledgeMapServlet",{action:"listuserconceptnode"},function(msg){
               me.knowledgeMaplist([]);
             var message =JSON.parse(msg);
              var contents=JSON.parse(message.response.content);
              for(var i=0;i<contents.length;i++){
                //  console.log(contents[i] + "\n")
                  var item=JSON.parse(contents[i])
                  me.knowledgeMaplist.push(item);
                }
              
              });
          },
          cancelConceptSchema:function(event){
               me.conceptSchemas.resetForm();
               me.conceptSchemas.conceptSchemaFormVisible(false);
               me.conceptSchemas.resetForm();
          },
          newConceptSchema:function(event){
                me.conceptSchemas.conceptSchemaFormVisible(true);
                me.conceptSchemas.conceptSchemaMessageVisible(false);
                me.conceptSchemas.conceptSchemaformHeading("Create New");
                me.conceptSchemas.selectedAction=me.conceptSchemas.action.New;
                me.conceptSchemas.selectetConceptSchema={};
                me.conceptSchemas.resetForm();
                me.knowledgeMapFormVisible(false);
          },
          editConceptSchema:function(item,event){
             me.conceptSchemas.selectetConceptSchema=item;
             me.conceptSchemas.conceptSchemaFormVisible(true);
             me.conceptSchemas.conceptSchemaMessageVisible(false);
             me.conceptSchemas.relationName(item.relationname);
             me.conceptSchemas.conceptName(item.conceptname);
             me.conceptSchemas.conceptAction(item.conceptaction);
             me.conceptSchemas.attributeName(item.attributename);
             me.conceptSchemas.attributeValue(item.attributevalue);
             me.conceptSchemas.conceptSchemaformHeading("Edit");
             me.conceptSchemas.selectedAction=me.conceptSchemas.action.Edit;
          },
          deleteConceptSchema:function(item,event){
             me.conceptSchemas.selectetConceptSchema=item;
             me.conceptSchemas.selectedAction=me.conceptSchemas.action.Delete;
             me.conceptSchemas.conceptSchemaMessageVisible(true);
             me.responseBoxStyle("alert alert-danger");
             me.conceptSchemas.conceptSchemaResponseMessageDialog("Cretical!");
             me.conceptSchemas.conceptSchemaResponseMessage("Are tou sure you want to delete?"); 
             me.conceptSchemas.selectetConceptSchema=item;
             me.conceptSchemas.conceptSchemaFormVisible(true);
             me.conceptSchemas.conceptSchemaMessageVisible(false);
             me.conceptSchemas.relationName(item.relationname);
             me.conceptSchemas.conceptName(item.conceptname);
             me.conceptSchemas.conceptAction(item.conceptaction);
             me.conceptSchemas.attributeName(item.attributename);
             me.conceptSchemas.attributeValue(item.attributevalue);
             me.conceptSchemas.conceptSchemaformHeading("Delete");
             me.conceptSchemas.conceptSchemaMessageVisible(true);
            
          },
            addItems:function(items){
                 if(items==null) return;
                  me.conceptSchemaItems([]);
                for(var i=0;i<items.length;i++){
                 me.conceptSchemaItems.push(items[i]);
             }
            
         }
     };
     
     me.conceptHierarchy={
        
        addActionEnable:ko.observable(false),
         deleteActionEnable:ko.observable(true),
         updateActionEnable:ko.observable(true),
         selectedNodeName:ko.observable(""),
         conceptNodeName:ko.observable(),
         relationTypes:[{id:"PartOf",name:"PartOf"},{id:"TypeOf",name:"TypeOf"}],
         selectedRelationType:ko.observable(""),
         selectedNode:null,canExecteAction:function(){
             return me.conceptHierarchy.selectedNode !=null 
         },
         isRoot:function(){
              if(me.conceptHierarchy.selectedNode.parent=== undefined &&
                     me.conceptHierarchy.selectedNode.root===undefined )
             {
                 return true;
             }
             return false;
         },
         createRequestMessage:function(action){
              var id=0; 
             if(me.conceptHierarchy.selectedNode.parent=== undefined &&
                     me.conceptHierarchy.selectedNode.root===undefined)
             {
                 id=me.conceptHierarchy.selectedNode.id; 
             }
             else{
                 id=me.conceptHierarchy.selectedNode.root;
             }
             
             var relationtype=me.conceptHierarchy.selectedRelationType().id;
             var conceptNodeName=me.conceptHierarchy.conceptNodeName();
             var identity=me.conceptHierarchy.selectedNode.identity;
             var parentid=me.conceptHierarchy.selectedNode.parent;
             var root=me.conceptHierarchy.selectedNode.root;
              //Child node
             if(parentid >0){
                 id=parentid;
             }
             var data={action:action,ID:id,
                       Name:conceptNodeName,
                       RelationType:relationtype,
                       Identity:identity};
                   return data;
         },
         Add:function(event){
              action.Name="addconceptnode";
               $("#txtSelectedItem").css("background-color","");
              if(!me.conceptHierarchy.canExecteAction()){
                  return;
              }
              
              if(!isConceptHierarchyValid()){
                 $("#txtSelectedItem").css("background-color","lightyellow");
                 return;
              }
          var data=  me.conceptHierarchy.createRequestMessage(action.Name);
             $.post("KnowledgeMapServlet",data,function(msg){
                 var message=JSON.parse(msg);
                if(message.response.status==="ok"){
                 var content=JSON.parse(message.response.content);
                  var id=message.response.id;
                  refreshView(data.ID,msg);
                   me.responseMessageVisible(true);
                   me.knowledgeMapName("");
                   me.knowledgeMapDescription("");
                   me.conceptHierarchy.conceptNodeName("");
                }
                else{
                   alert("Error")
             }      
             });
             
         },
         Update:function(event){
            
              $("#txtSelectedItem").css("background-color","");
              if(!me.conceptHierarchy.canExecteAction()){
                  return;
              }
              
               if(!isConceptHierarchyValid()){
                 if(!me.conceptHierarchy.isRoot()){
                   $("#txtSelectedItem").css("background-color","lightyellow");  
                 }
                 
                 return;
              }
             action.Name="renameconceptnode";
             if(me.conceptHierarchy.isRoot()){
                
                  return;
              }
             var data=  me.conceptHierarchy.createRequestMessage("renameconceptnode");
              $.post("KnowledgeMapServlet",data,function(msg){
                    refreshView(data.ID,msg);
              });
         },
         Delete:function(event){
              if(!me.conceptHierarchy.canExecteAction()){
                  return;
              }
             action.Name="deleteconceptnode";
             var data=  me.conceptHierarchy.createRequestMessage("deleteconceptnode");
              if(data.id>0 && data.identity=="") return // can't delete parent node here
              if(data.identity!=""){
                  data.id=0;
              }
              if(me.conceptHierarchy.isRoot()){
                 
                  return;
              }
              $.post("KnowledgeMapServlet",data,function(msg){
           
                  var message =JSON.parse(msg);
                  var content=JSON.parse(message.response.content);
                  UpdateKnowledgeMaps("childchanged",content.identity,
                  content,content.id);
                 // me.conceptHierarchy.updateView(msg);
                  refreshView(data.ID,msg);
                  me.conceptSchemaItems([]);
              });
         },
         DisableAllActions:function(){
            $("#lnk-addconceptnode").css("background-color","silver");
             $("#lnk-updateconceptnode").css("background-color","silver");
             $("#lnk-deleteconceptnode").css("background-color","silver");
         },EnableAllActions:function(){
             $("#lnk-addconceptnode").css("background-color","");
             $("#lnk-updateconceptnode").css("background-color","");
             $("#lnk-deleteconceptnode").css("background-color","");
         }
         
     };
      $(function(){
          $('#tv-knowledgemap').bind(
           'tree.click',
            function(event) {
             var node = event.node;
             me.knowledgeMapFormVisible(false);
             me.conceptSchemas.conceptSchemaFormVisible(false);
             if(node.identity==="-"){ //parent Node
               me.conceptSchemas.conceptSchemaNewButtonEnable(false); 
               me.conceptSchemas.conceptSchemaAppendNodeSelected("");
               me.conceptSchemaItems([]);
               $("#lnk-updateconceptnode").css("background-color","silver");
               $("#lnk-deleteconceptnode").css("background-color","silver");
            }else{
              me.conceptSchemas.conceptSchemaNewButtonEnable(true);
              me.conceptSchemas.conceptSchemaAppendNodeSelected("(" + node.name + ")");
               $("#lnk-updateconceptnode").css("background-color","");
               $("#lnk-deleteconceptnode").css("background-color","");
            }
             var conceptSchemas=node.conceptschemas;
             me.conceptSchemas.addItems(conceptSchemas);
             me.conceptHierarchy.selectedNodeName(node.name);
             me.conceptHierarchy.selectedNode=node;
             me.conceptHierarchy.selectedNode={id:node.id,name:node.name,
                 identity:node.identity,parent:node.parentidentity,root:node.rootid};
             }
           );
      });
    /***************************initialize*************************************/
                
                $("#header").load("templates/header.html",function(){
                    $("#logout").click(function(){
                        //alert("LOGOUT");
                        window.location.href="index.jsp";
                    });
                 
                });  
               /*
                $("#leftmenu").load("templates/leftmenu.html",function(){
                
                 //  me.menuSelector.knowledgemapcount=$("#bg-knowledgemap-count");
                  // me.menuSelector.knowledgemapcount=$("#leftmenu");
                   
                });
              */  
                $("#footer").load("templates/footer.html",function(){
                    
                });
               
      
       /*    
        $(function(){
          $.post("KnowledgeMapServlet",{action:"listuserconceptnode"},function(msg){
              var message =JSON.parse(msg);
              var contents=JSON.parse(message.response.content);
           
              updateKnowledgemapMenuCount(contents.length); 
              for(var i=0;i<contents.length;i++){
                //  console.log(contents[i] + "\n")
                  var item=JSON.parse(contents[i]);
                  item.isRoot=true;
                  me.knowledgeMaplist.push(item);
              }
                me.conceptHierarchy.DisableAllActions();
           
          });
        
     });
    */
   /********************************end initilize**********************************/ 
    OTS.Views.KnowledgeMapsView.prototype.onSubmited=function(msg){
                if(msg==""){
                
                  me.responseMessageVisible(true);
                  me.responseBoxStyle("alert alert-danger");
                  me.responseMessageDialog("Fail!");
                  me.responseMessage("Error occurs whiles performing operation. Please contact system administrator");
                    return;
                }
               var message=JSON.parse(msg);
                
                if(message.response.status==="ok"){
                   var content=JSON.parse(message.response.content);
                   var id=message.response.id;
                   content.parentid=0;
                   content.rootid=0;
                 UpdateKnowledgeMaps(action.Name,message.response.identity,content,id);
                   me.responseMessageVisible(true);
                   me.knowledgeMapName("");
                   me.knowledgeMapDescription("");
                }
                else{
                  me.responseMessageVisible(true);
                  me.responseBoxStyle("alert alert-danger");
                  me.responseMessageDialog("Fail!");
                  me.responseMessage("Unable to Save knowledge map");
             }   
               action.Name="new";
               me.formHeading("Create New");
      }
    
   
    OTS.Views.KnowledgeMapsView.prototype.NewKnowledgeMap=function(event){
          me.knowledgeMapFormVisible(true);
          action.Name="new";
          me.knowledgeMapName("");
          me.knowledgeMapDescription("");
          me.formHeading("Create New");
          selectedKnowledgeMapId(0);
          me.conceptSchemas.conceptSchemaFormVisible(false);
     }
    
    OTS.Views.KnowledgeMapsView.prototype.SubmitNewKnowledgeMap=function(event){
        $("#txt-knowledgemapname").css("background-color","");
        if(!isKnowledgeMapValid()){
               $("#txt-knowledgemapname").css("background-color","lightyellow");
            return;
        }
       
        var data={action:action.Name,ID:selectedKnowledgeMapId(), Name:me.knowledgeMapName(), Description:me.knowledgeMapDescription()};
         
        $.post("KnowledgeMapServlet",data,this.onSubmited);
     }
     
     OTS.Views.KnowledgeMapsView.prototype.CancelNewKnowledgeMap=function(event){
         me.knowledgeMapFormVisible(false);
         me.responseMessageVisible(false);
         action.Name="";
         selectedKnowledgeMapId(0);
     }
     
       OTS.Views.KnowledgeMapsView.prototype.CloseResponseDialog=function(event){
           me.responseMessageVisible(false);
         
     }
      OTS.Views.KnowledgeMapsView.prototype.AddKnowledgeMap=function(item){
         me.knowledgeMaplist.push(item);
     }
     
   
     
     OTS.Views.KnowledgeMapsView.prototype.RenameKnowledgeMap=function(item,event){
          event.preventDefault();
         action.Name="rename";
         me.formHeading("Rename");
         me.knowledgeMapName(item.label);
         me.knowledgeMapDescription(item.description);
         me.knowledgeMapFormVisible(true);
         me.responseMessageVisible(false);
         selectedKnowledgeMapId(item.id);
        
     }
     
     OTS.Views.KnowledgeMapsView.prototype.DuplicateKnowledgeMap=function(item,event){
        event.preventDefault();
         action.Name="duplicate";
         me.formHeading("Duplicate");
         me.responseMessageVisible(false);
         me.knowledgeMapFormVisible(true);
         me.knowledgeMapName(item.label + "Copy");
         me.knowledgeMapDescription(item.description + "Copy");
         selectedKnowledgeMapId(item.id);
     }
     
     OTS.Views.KnowledgeMapsView.prototype.DeleteKnowledgeMap=function(item,event){
         event.preventDefault();
         action.Name="delete";
         me.formHeading("Delete");
         me.knowledgeMapFormVisible(true);
         me.responseMessageVisible(true);
         me.responseBoxStyle("alert alert-danger");
         me.responseMessageDialog("Critical!");
         me.responseMessage("Are you sure you want to delete?");
         me.knowledgeMapName(item.label);
         me.knowledgeMapDescription(item.description);
         selectedKnowledgeMapId(item.id);
       
     }
     
       OTS.Views.KnowledgeMapsView.prototype.KnowledgeMapSelected=function(item ,event){
         
           event.preventDefault();
           UpdateTreeView(item);
           me.conceptHierarchy.EnableAllActions();
           me.conceptSchemaItems([]);
           me.conceptSchemas.conceptSchemaAppendNodeSelected("");
     }
     
      OTS.Views.KnowledgeMapsView.prototype.Display=function(){
           return me;
      
     }
     
    
     // ******************Private Functions *********************************
     var updateKnowledgemapMenuCount=function(count){
           $("#bg-knowledgemap-count").text(count); 
     }
    
    var deleteConceptHierarchy=function(node){
         node.label="";
         node.children.length =0;
         UpdateTreeView(node);
       
    }
    
    var isConceptHierarchyValid=function(){
       return $("#txtSelectedItem").val().trim() !="";
   }
   
   var isConceptSchemaValid=function(){
     return  $("#txt-conceptschema-relationname").val().trim() !="";
   }
   var isKnowledgeMapValid=function(){
       return  $("#txt-knowledgemapname").val().trim() !="";
   }
    
    var UpdateTreeView=function(item){
                 
               var data=[]; 
                   var _data = {id: item.id, label:item.label,identity:item.identity,
                                 description:item.description,
                                 parentid:item.parentid,
                                 rootid:item.rootid,
                                 children:item.children  
                        };
                       data.push(_data);
                     if(!treeView.IsInitialized){
                       $('#tv-knowledgemap').tree({
                             data: data, autoOpen: true
                       });
                       treeView.IsInitialized=true
                     }
                     else{
                       $('#tv-knowledgemap').tree('loadData', data);  
                     }      
       }
   
     var UpdateKnowledgeMaps=function(action
         ,identity,content,id){
          var item= {id:id,label:content.label ,
                      description:content.description, parent:content.parentid,
                                 rootId:content.rootid,
                      children:content.children};
          
         var message="Knowledge map";
         switch(action){
             case "new":
                 UpdateTreeView(item);
                 var conceptNode=new OTS.Views.ConceptNode(item);
                   conceptNode.UpdateIdentity(identity);
                   me.knowledgeMaplist.push(item);
                   me.responseMessage(message + " created");
                   me.responseMessageDialog("Success!");
                   me.responseBoxStyle("alert alert-success");
                  $("#lnk-addconceptnode").css("background-color","");
                  $("#lnk-updateconceptnode").css("background-color","silver");
                  $("#lnk-deleteconceptnode").css("background-color","silver");
                  updateKnowledgemapMenuCount(  me.knowledgeMaplist().length); 
             break;
            case "rename":
               UpdateTreeView(item);
               var  item1= findKnowledgeMap(id);
               var index= me.knowledgeMaplist.indexOf(item1);
                me.knowledgeMaplist.splice(index,1,item);
                me.responseMessage(message + " renamed");
                me.responseBoxStyle("alert alert-success");
                 break;
          case "delete":
                var  itemToDelete= findKnowledgeMap(id);
                me.knowledgeMaplist.remove(itemToDelete);
                if(me.knowledgeMaplist!=null && me.knowledgeMaplist.length>0){
                    var firstItem=me.knowledgeMaplist[0];
                    UpdateTreeView(firstItem);
                    me.action.Name="new";
                    me.formHeading("Create New");
                }
                else{
                   
                     //UpdateTreeView(itemToDelete);
                     deleteConceptHierarchy(itemToDelete);
                }
                me.responseMessage(message + " deleted");
                me.responseMessageDialog("Success!");
                me.responseBoxStyle("alert alert-success");
                updateKnowledgemapMenuCount(  me.knowledgeMaplist().length); 
                me.conceptSchemaItems([]);
                 break;
             
           case "duplicate":
                UpdateTreeView(item);
               var conceptNode=new OTS.Views.ConceptNode(item);
                conceptNode.UpdateIdentity(identity);
                me.knowledgeMaplist.push(item);
                me.responseMessageDialog("Success!");
                me.responseMessage(message + " has been duplicated");
                me.responseBoxStyle("alert alert-success");
                updateKnowledgemapMenuCount(me.knowledgeMaplist().length); 
                 break;
             case "addconceptnode":
               UpdateTreeView(item);
               var  item1= findKnowledgeMap(id);
               var index= me.knowledgeMaplist.indexOf(item1);
                me.knowledgeMaplist.splice(index,1,item);
              
                 break;
             case "childchanged":
                UpdateTreeView(item);
               var  item2= findKnowledgeMap(id);
               me.knowledgeMaplist.replace(item2,item);
              break;
             default:
                 
              break;
         }
     };
     
     var findKnowledgeMap=function(id){
         var item=null;
           for(var i=0;i<me.knowledgeMaplist().length; i++){
               if(me.knowledgeMaplist()[i].id==id){
                   item= me.knowledgeMaplist()[i];
                   break;
               }
           }
           return item;
     }
    
    var refreshView=function(rootId,msg){
         me.knowledgeMaplist([]);
          var message =JSON.parse(msg);
              var contents=JSON.parse(message.response.content);
              for(var i=0;i<contents.length;i++){
                //  console.log(contents[i] + "\n")
                  var item=JSON.parse(contents[i])
                  me.knowledgeMaplist.push(item);
              }
              
        var item=  findKnowledgeMap(rootId);
         UpdateTreeView(item);
        me.conceptHierarchy.conceptNodeName("");
    }
    
    var updateSelectedConceptSchemas=function(id,parentIdentity, identity){
        $.post("KnowledgeMapServlet",{action:"listconceptschema",ID:id,
            ParentIdentity:parentIdentity,Identity:identity},function(msg){
             var message=JSON.parse(msg);
             var contents=JSON.parse(message.response.content);
           
              addItems(contents);
        });
    }
    
    var resetConceptSchemaForm=function(){
          me.knowledgeMapFormVisible(true);
          action.Name="new";
          me.knowledgeMapName("");
          me.knowledgeMapDescription("");
          me.formHeading("Create New");
          selectedKnowledgeMapId(0);
    }
    
    var doUpdate=function(msg){
          var selectedNode=  me.conceptHierarchy.selectedNode;
                  var message=JSON.parse(msg);
                  var content=JSON.parse(message.response.content);
                 updateSelectedConceptSchemas(selectedNode.root,
                  selectedNode.parent,selectedNode.identity);
                  me.conceptSchemas.updateConceptSechemas(msg);
                  me.conceptHierarchy.selectedNode={};
                  me.conceptSchemas.refreshKnowledgeMapList();
                  UpdateTreeView(content);  
                  me.conceptSchemas.updateConceptSechemas(msg);
               
            
     }
     
     var doSubmit=function(data,conceptSchema){
    
          $.post("KnowledgeMapServlet",data,function(msg){
               var message =JSON.parse(msg);
               var content =message.response.content;
               var  item2= findKnowledgeMap(message.response.id);
               var data=JSON.parse(content);
               me.knowledgeMaplist.replace(item2,data);
               UpdateTreeView(data);
              
           $.post("KnowledgeMapServlet",{action:"listconceptschema",ID:conceptSchema.rootid,
            ParentIdentity:conceptSchema.parentidentity,
            Identity:me.conceptHierarchy.selectedNode.identity},function(msg){
             var message=JSON.parse(msg);
             var contents=JSON.parse(message.response.content);
              me.conceptSchemas.addItems(contents);
                  me.conceptSchemas.dispalyConceptSechemaMessge(msg);  
                  me.conceptSchemas.resetForm();
             });
          
          });
       
     }
    
}