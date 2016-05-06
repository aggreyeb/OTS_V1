var OTS=OTS||{};
OTS.ViewModels=OTS.ViewModels||{};
var Item=function(key,value){
    this.key=ko.observable(key);
    this.value=ko.observable(value);
};
OTS.ViewModels.ConceptHierarchy=function(){
    var me=this;
     me.actionType={Add:"addconceptnode",UPDATE:"renameconceptnode",DELETE:"deleteconceptnode"};
     me.selectedTreeNode=null;
     me.SelectedNodeId=ko.observable(0);
     me.SelectedNodeIdentity=ko.observable("");
     me.SelectedNodeName=ko.observable("");
     me.SelectedNodeParent=ko.observable("");
     me.SelectedNodeRelationWithParent=ko.observable();
     me.SelectedEditOptions=ko.observableArray([]);
     me.SelectedNodeChildren=[];
     
     me.NewConceptHierarchy=ko.observable("");
     me.NewRelationTypeSelected=ko.observable("");
    
     
     me.conceptHierarchyInitialize=false;
     me.RelationTypeDropDown=null;
     me.ConceptSchemas=ko.observableArray([]);
  
     
     me.loadView=function(item){
       me.currentSelectedItem=item;
     
       me.relationTypes=ko.observableArray([{id:'PartOf',name:'PartOf'},{id:'TypeOf',name:'TypeOf'}]);
       me.selectedRelationType=ko.observable("");
       me.changeContentHeading("Knowledge Map (" + me.currentSelectedItem.label + ")");
          $.get("templates/teacher/teacher-concept-hierarchy.html",function(msg){
           $("#view-content").empty();
           $("#view-content").append(msg);
           
          
           var conceptSchema={relationname:'',
             conceptname:'',conceptaction:'',
             attributename:'',attributevalue:''};
             me.ConceptSchemas.push(conceptSchema);
            
           /***********Treeview Event************/
           var newconceptSchema={relationname:'',
             conceptname:'',conceptaction:'',
             attributename:'',attributevalue:''};
            $('#tv-conceptHierarchy').bind(
           'tree.click',
            function(event) {
             var node = event.node;
              me.selectedTreeNode=node;
              if(me.selectedTreeNode.identity==="-"){ //root
                me.disableAllActions();  
                me.DisableConceptSchemaInformationRelationType();
                me.ConceptSchemas.splice(0,me.ConceptSchemas().length);
                me.enableNewConceptSchema(false);
                
              }
              else{
                  me.enableAllActions();
                  me.EnabledConceptSchemaInformationRelationType();
                    me.conceptSchemaFormVisible(true);
                    me.enableNewConceptSchema(true);
              }
              me.fillForm(me.selectedTreeNode);
              me.conceptSchemaFormVisible(false);
              me.conceptSchemaResponseMessageVisible(false);
           
             }
           );
           /***************************/
           ko.applyBindings(me,$("#view-content")[0]); 
           me.updateTreeView(item);
           me.RelationTypeDropDown= $("#myrelationType");
        
            
        });  
     };
     
     
      
    me.clearForm=function(){
        
        me.SelectedNodeName("");
        me.SelectedNodeParent("");
        $("#myrelationType").val();
        
    };
    
    me.DisableConceptSchemaInformationRelationType=function(){
         $("#myrelationType").prop("disabled",true);
    };
    
    me.EnabledConceptSchemaInformationRelationType=function(){
         $("#myrelationType").prop("disabled",false);
    };
    
    me.clearNodeInformation=function(){
      
        me.SelectedNodeName("");
        me.SelectedNodeParent("");
       $(me.RelationTypeDropDown.selector).val("");
    };
    
    me.fillForm=function(item){
      try{
        me.SelectedNodeId=item.id;
        me.SelectedNodeIdentity(item.identity);
        me.SelectedNodeName(item.name);
        me.SelectedNodeParent(item.parent.name);
       $(me.RelationTypeDropDown.selector).val(item.relationtype);
        if(item.isRoot){
          me.SelectedNodeRelationWithParent("");    
         
        }else{
           
             if(item.conceptschemas===undefined)
                 return ;
           me.ConceptSchemas([]);
            for(var i=0;i<item.conceptschemas.length;i++){
                  me.ConceptSchemas.push(item.conceptschemas[i]);
            }
        }
       }catch(ex){
           alert(ex)
       }
    };
     
     me.selectedItem=function(item){
         me.currentSelectedItem=item;
         alert(item.decription);
         me.changeContentHeading("Knowledge Map " + item.decription );
    };
    
     me.changeContentHeading=function(name){
       $("#lbl-selected-menuitem").text(name);
   };
   
   
   me.updateTreeView=function(item){
             
               var data=[]; 
                 var _data = {id: item.id, label:item.label,identity:item.identity,
                                 description:item.description,
                                 parentid:item.parentid,
                                 rootid:item.rootid,
                                 children:item.children  
                        };
               data.push(_data);
               if(!me.conceptHierarchyInitialize){
               $('#tv-conceptHierarchy').tree({
                             data: data, autoOpen: true
               });
                    me.conceptHierarchyInitialize=true;
                 }
                 else{
                       $('#tv-conceptHierarchy').tree('loadData', data);  
                   }      
       };
       
   
   me.createRequestMessage=function(action){
              var id=0; 
              var identity="";
             if(me.selectedTreeNode.parentid=== undefined &&
                     me.selectedTreeNode.rootid===undefined)
             {
                 id=me.selectedTreeNode.id; 
             }
             else{
                 id=me.selectedTreeNode.rootid;
                 identity=me.selectedTreeNode.identity;
             }
             
             var relationtype=me.NewRelationTypeSelected().id;
             var conceptNodeName=me.NewConceptHierarchy();
           
             var data={action:action,ID:id,
                       Name:conceptNodeName,
                       RelationType:relationtype,
                       Identity:identity};
                   return data;
         };
         
        
   me.createEditRequestMessage=function(action){
              var id=0; 
              var identity="";
             if(me.selectedTreeNode.parentid=== undefined &&
                     me.selectedTreeNode.rootid===undefined)
             {
                 id=me.selectedTreeNode.id; 
             }
             else{
                 id=me.selectedTreeNode.rootid;
                 identity=me.selectedTreeNode.identity;
             }
           
            // var relationtype=$("#myrelationType").val();
            var relationtype=$(me.RelationTypeDropDown.selector).val();
             var conceptNodeName=me.SelectedNodeName();
           
             var data={action:action,ID:id,
                       Name:conceptNodeName,
                       RelationType:relationtype,
                       Identity:identity};
                   return data;
         }   
         
        me.isConceptHierarchyValid=function(){
            return $("#txtSelectedItem").val().trim() !="";
         };
         
         me.isConceptNodeSelected=function(){
             
               return me.selectedTreeNode!=null;
         };
        
         
             me.disableAllActions=function(){
           // $("#lnk-addconceptnode").css("background-color","silver");
             $("#lnk-updateconceptnode").css("background-color","silver");
             $("#lnk-deleteconceptnode").css("background-color","silver");
         };
         
           me.enableAllActions=function(){
             //$("#lnk-addconceptnode").css("background-color","");
             $("#lnk-updateconceptnode").css("background-color","");
             $("#lnk-deleteconceptnode").css("background-color","");
         };
         
       me.add=function(item,event){
        
             $("#txtSelectedItem").css("background-color","");
           if(!me.isConceptHierarchyValid()){
              $("#txtSelectedItem").css("background-color","lightyellow");
               return;
           }
           
           if(!this.isConceptNodeSelected()){
               alert("Please selected Node and try again");
               return;
           }
           try{
          var data=me.createRequestMessage(me.actionType.Add); 
           $.post("KnowledgeMapServlet",data,function(msg){
               var message=JSON.parse(msg);
                if(message.response.status==="ok"){
                  var content=JSON.parse(message.response.content);
                    me.updateTreeView(content);
                    $("#txtSelectedItem").val("");
                }
                else{
                 alert(message.response.error);    
                }
           });
             me.selectedTreeNode=null;
       }
       catch(ex){
          alert(ex); 
       }
       };
       
       me.update=function(item,event){
       
           if(me.selectedTreeNode==null){
               alert("Select a child node and try again");
               return;
           };
           
           try{  
           var data=  me.createEditRequestMessage(me.actionType.UPDATE);
              $.post("KnowledgeMapServlet",data,function(msg){
                      var message=JSON.parse(msg);
                      console.log(message.response.content);
                if(message.response.status==="ok"){
                  var content=JSON.parse(message.response.content);
                    me.updateTreeView(content);
                    me.clearNodeInformation();
                    me.ConceptSchemas([]);
                    me.enableNewConceptSchema(false);
                    me.conceptSchemaResponseMessageVisible(false);
                }
                else{
                   alert(message.response.error); 
                }
              });
             // me.clearNodeInformation();
               me.selectedTreeNode=null;
          }
          catch(ex){
              alert(ex);
          }
       };
       
       me.deletenode=function(item,event){
          if(me.selectedTreeNode==null){
               alert("Select a child node and try again");
               return;
           };
           
           try{
          var data=me.createEditRequestMessage(me.actionType.DELETE);
          var isParent=me.isRoot(data);
          if(!isParent){  //Can delete on Child Node
              
               $.post("KnowledgeMapServlet",data,function(msg){
                      var message=JSON.parse(msg);
                      console.log(message.response.content);
                if(message.response.status==="ok"){
                  var content=JSON.parse(message.response.content);
                    me.updateTreeView(content);
                   me.enableNewConceptSchema(false);
                   me.conceptSchemaResponseMessageVisible(false);
                }
                else{
                   alert(message.response.error); 
                }
              });
              me.clearNodeInformation();
              me.ConceptSchemas([]);
              me.selectedTreeNode=null;
          }
         }catch(ex){
             alert(ex);
         }
       };
       
       
        me.isRoot=function(item){
              if(item.ID > 0 &&
                     item.Identity==="" )
             {
                 return true;
             }
             return false;
         };
         
      /* Concept Schema management */
      
      me.selectedConceptSchema=null;
      me.conceptSchemaActionType={Add:"newconceptschema",
          EDIT:"editconceptschema",
          DELETE:"deleteconceptschema",
          LIST:"listconceptschema"};
     me.conceptResponseDialogType={CRITICAL:"Critical!",
          INFO:"Info",
          SUCCESS:"Success!"};
     me.conceptSchemaAlertColorType={CRITICAL:"alert alert-danger",
         SUCCESS:"alert alert-success",INFO:"alert alert-info"};
     
     me.conceptSchemaFormVisible=ko.observable(false);
     me.conceptSchemaResponseMessageVisible=ko.observable(false);
     me.conceptResponseDialog=ko.observable(me.conceptResponseDialogType.INFO);
     me.conceptSchemaResponseMessageText=ko.observable("");
     me.conceptSchemaFormHeading=ko.observable("Create New");
     me.conceptScheamResponseBoxStyle=ko.observable(me.conceptSchemaAlertColorType.INFO);
     me.newConceptSchemaActionsVisible=ko.observable(true);
     me.editConceptSchemaActionsVisible=ko.observable(false);
     me.enableNewConceptSchema=ko.observable(false);
     
     me.canBindConcceptSchema=function(){
         
         return me.ConceptSchemas.length>0;
     };
    
    //*************************** Changing relationship type to dropdown
     me.SelectedConceptSchemaRelationTypes=ko.observable(new Item("have"));
     
     me.EnableConceptName=ko.observable(false);
     me.EnableConceptAction=ko.observable(false);
     me.EnableAttributeName=ko.observable(false);
     me.EnableConceptValue=ko.observable(false);
     
     me.SelectedConceptSchemaRelationTypeChnaged=function(){
          var selected=  $("#cn-sel-relationType").val();
         if(selected===undefined){
               me.EnableConceptName(false);
              me.EnableConceptAction(false);
              me.EnableAttributeName(false);
              me.EnableConceptValue(false);
          }
          if(selected==="is"){
              me.EnableConceptName(true);
              me.EnableConceptAction(false);
              me.EnableAttributeName(false);
              me.EnableConceptValue(false);
          }
         if (selected==="can"){
              me.EnableConceptName(true);
              me.EnableConceptAction(true);
              me.EnableAttributeName(false);
              me.EnableConceptValue(false);
         }
         if(selected==="has" || 
              selected==="contain" || 
              selected==="have"){
              me.EnableConceptName(false);
              me.EnableConceptAction(false);
              me.EnableAttributeName(true);
              me.EnableConceptValue(true);
         }
       
     }
   
    
    me.ConceptSchemaRelationTypes=ko.observableArray([
                                                     new Item("can","can"),
                                                     new Item("contain","contain"),
                                                     new Item("has","has"),
                                                     new Item("have","have"),
                                                     new Item("is","is")]);
    
     me.ConceptSchemaForm={
         relationname:ko.observable(""),
         conceptname:ko.observable(""),
         conceptaction:ko.observable(""),
         attributename:ko.observable(""),
         attributevalue:ko.observable("")
     };
     
     me.clearConceptSchemaForm=function(){
         me.ConceptSchemaForm.relationname("");
         me.ConceptSchemaForm.conceptname("");
         me.ConceptSchemaForm.conceptaction("");
         me.ConceptSchemaForm.attributename("");
         me.ConceptSchemaForm.attributevalue("");
     };
     me.fillConceptSchemaForm=function(item){
        //item.relationname
         me.ConceptSchemaForm.relationname(item.relationname);
         $("#cn-sel-relationType").val(item.relationname).change();
         me.ConceptSchemaForm.conceptname(item.conceptname);
         me.ConceptSchemaForm.conceptaction(item.conceptaction);
         me.ConceptSchemaForm.attributename(item.attributename);
         me.ConceptSchemaForm.attributevalue(item.attributevalue);
     };
     me.onConceptSchemaSelected=function(item,event){
          me.conceptSchemaFormHeading("");
          me.selectedConceptSchema=item;
          me.fillConceptSchemaForm(item);
          me.conceptSchemaFormVisible(true);
          me.newConceptSchemaActionsVisible(false);
          me.editConceptSchemaActionsVisible(true);
          me.conceptSchemaResponseMessageVisible(false);
          
     };
    
    me.closeConceptSchemaMessageBox=function(event){
         me.conceptSchemaResponseMessageVisible(false);
     };
     me.onNewConceptSchema=function(){
         me.conceptSchemaFormHeading("Create New");
         me.conceptSchemaFormVisible(true);
         me.newConceptSchemaActionsVisible(true);
         me.editConceptSchemaActionsVisible(false);
         me.clearConceptSchemaForm();
         me.conceptSchemaResponseMessageVisible(false);
     };
      
      me.onCancelNewConceptSchema=function(){
         me.conceptSchemaFormVisible(false);  
      };
      
  
     me.addConceptSchemaRange=function(contents){
         
         me.ConceptSchemas([]);
         for(var i=0;i<contents.length;i++){
             me.ConceptSchemas.push(contents[i]);
         }
     }; 
      
      me.isConceptSchemaRelationNameValid=function(){
        return  me.ConceptSchemaForm.relationname()!="";
      };
      
      
     me.submitNewConceptSchema=function(){
         
         if(!me.isConceptSchemaRelationNameValid()){
             alert("Concept Schema relation name required");
             return;
         }
          try{
            var item=   me.selectedTreeNode;
            var data={
               id:item.parentid,
               rootid:item.rootid,
               parentidentity:item.identity,
               relationname: me.ConceptSchemaForm.relationname(),
               conceptname:me.ConceptSchemaForm.conceptname(),
               conceptaction:me.ConceptSchemaForm.conceptaction(),
               attributename:me.ConceptSchemaForm.attributename(),
               attributevalue: me.ConceptSchemaForm.attributevalue()
            }; 
         
              var jsonData =JSON.stringify(data);
            $.post("KnowledgeMapServlet",{action:"newconceptschema",
                conceptschema:jsonData},function(msg){
                var message=JSON.parse(msg);
               //var content=message.response.content;
                //update the tree view here 
                 var message=JSON.parse(msg);
                 if(message.response.status==="ok"){
                  var content=JSON.parse(message.response.content);
                    me.updateTreeView(content);
                }
       
          $.post("KnowledgeMapServlet",{action:"listconceptschema",ID:data.rootid,
            ParentIdentity:me.selectedTreeNode.parent.identity,
            Identity:me.selectedTreeNode.identity},function(msg){
            var message=JSON.parse(msg);
             var contents=JSON.parse(message.response.content);
              me.addConceptSchemaRange(contents);
             });
         
            });
           me.conceptSchemaResponseMessageVisible(true);
           me.conceptResponseDialog(me.conceptResponseDialogType.SUCCESS);
           me.conceptSchemaResponseMessageText("Concept Schema added");
           me.conceptScheamResponseBoxStyle(me.conceptSchemaAlertColorType.SUCCESS);
           me.clearConceptSchemaForm();
           me.conceptSchemaFormVisible(false);
          
        }
        catch(ex){
           me.conceptSchemaResponseMessageVisible=(true);
           me.conceptResponseDialog(me.conceptResponseDialogType.INFO);
           me.conceptSchemaResponseMessageText(ex);   
          alert("Concept Schema:" + ex) ;   
        }
        };
          
          
      me.onEditConceptSchema=function(item,event){
       
          me.conceptSchemaFormHeading("Edit");
          me.selectedConceptSchema=item;
          me.fillConceptSchemaForm(item);
          me.conceptSchemaResponseMessageVisible(true);
         
      };
      
      me.onCancelEdit=function(){
         me.conceptSchemaFormVisible(false); 
         me.clearConceptSchemaForm();
         me.conceptSchemaResponseMessageVisible(true);
      };
      me.updateConceptSchama=function(){
         
            if(!me.isConceptSchemaRelationNameValid()){
             alert("Concept Schema relation name required");
             return;
         }
       var currentConceptSchema=  me.selectedConceptSchema;
            try{
           
            var data={
               id:currentConceptSchema.id,
               rootid:currentConceptSchema.rootid,
               parentidentity:currentConceptSchema.parentidentity,
               relationname: me.ConceptSchemaForm.relationname(),
               conceptname:me.ConceptSchemaForm.conceptname(),
               conceptaction:me.ConceptSchemaForm.conceptaction(),
               attributename:me.ConceptSchemaForm.attributename(),
               attributevalue: me.ConceptSchemaForm.attributevalue()
            }; 
         
              var jsonData =JSON.stringify(data);
            $.post("KnowledgeMapServlet",{action:"editconceptschema",
                conceptschema:jsonData},function(msg){
                var message=JSON.parse(msg);
               //var content=message.response.content;
              //update the tree view here 
                 var message=JSON.parse(msg);
                 if(message.response.status==="ok"){
                  var content=JSON.parse(message.response.content);
                    me.updateTreeView(content);
                }
        
          $.post("KnowledgeMapServlet",{action:"listconceptschema",ID:data.rootid,
            ParentIdentity:me.selectedTreeNode.parent.identity,
            Identity:me.selectedTreeNode.identity},function(msg){
            var message=JSON.parse(msg);
             var contents=JSON.parse(message.response.content);
              me.addConceptSchemaRange(contents);
             });
         
            });
           me.conceptSchemaResponseMessageVisible(true);
           me.conceptResponseDialog(me.conceptResponseDialogType.SUCCESS);
           me.conceptSchemaResponseMessageText("Concept schema updated");
           me.conceptScheamResponseBoxStyle(me.conceptSchemaAlertColorType.SUCCESS);
           me.clearConceptSchemaForm();
           me.conceptSchemaFormVisible(false);
           
        }
        catch(ex){
           me.conceptSchemaResponseMessageVisible=(true);
           me.conceptResponseDialog(me.conceptResponseDialogType.INFO);
           me.conceptSchemaResponseMessageText(ex);   
          alert("Concept Schema:" + ex) ;   
        }
      };
      
      me.onDeleteConceptSchema=function(){
          me.conceptSchemaFormHeading("Delete");
         // me.selectedConceptSchema=item;
          me.conceptResponseDialog(me.conceptResponseDialogType.CRITICAL);
          me.conceptSchemaResponseMessageText("Are you sure you want to delete");
          me.conceptSchemaResponseMessageVisible(true);
         // me.fillConceptSchemaForm(item);
         
      };
      
      
     
      me.DeleteConceptSchema=function(){
          var currentConceptSchema=  me.selectedConceptSchema;
            try{
           
            var data={
               id:currentConceptSchema.id,
               rootid:currentConceptSchema.rootid,
               parentidentity:currentConceptSchema.parentidentity,
               relationname: me.ConceptSchemaForm.relationname(),
               conceptname:me.ConceptSchemaForm.conceptname(),
               conceptaction:me.ConceptSchemaForm.conceptaction(),
               attributename:me.ConceptSchemaForm.attributename(),
               attributevalue: me.ConceptSchemaForm.attributevalue()
            }; 
         
              var jsonData =JSON.stringify(data);
            $.post("KnowledgeMapServlet",{action:"deleteconceptschema",
                conceptschema:jsonData},function(msg){
                //update the tree view here 
                 var message=JSON.parse(msg);
                 if(message.response.status==="ok"){
                  var content=JSON.parse(message.response.content);
                    me.updateTreeView(content);
                }
               
          $.post("KnowledgeMapServlet",{action:"listconceptschema",ID:data.rootid,
            ParentIdentity:me.selectedTreeNode.parent.identity,
            Identity:me.selectedTreeNode.identity},function(msg){
           
             var message=JSON.parse(msg);
             var contents=JSON.parse(message.response.content);
              me.addConceptSchemaRange(contents);
             });
         
            });
           me.conceptSchemaResponseMessageVisible(true);
           me.conceptResponseDialog(me.conceptResponseDialogType.SUCCESS);
           me.conceptSchemaResponseMessageText("Concept schema deleted");
           me.conceptScheamResponseBoxStyle(me.conceptSchemaAlertColorType.SUCCESS);
           me.clearConceptSchemaForm();
           me.conceptSchemaFormVisible(false);
           
        }
        catch(ex){
           me.conceptSchemaResponseMessageVisible=(true);
           me.conceptResponseDialog(me.conceptResponseDialogType.INFO);
           me.conceptSchemaResponseMessageText(ex);   
          alert("Concept Schema:" + ex) ;   
        }
      };
      
      
      
      
     
}

