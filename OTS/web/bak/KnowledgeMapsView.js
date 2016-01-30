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
     
     
    
    
     $(function(){
          var item={
                     id:3,
                     label:"Plant",
                     description:"Plant Concept",
                     identity:"",
                     children:[]
                     
                  };
        var conceptNode= new OTS.Views.ConceptNode(item);
             me.knowledgeMaplist.push(conceptNode);
     });
    
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
      }
    
   
    OTS.Views.KnowledgeMapsView.prototype.NewKnowledgeMap=function(event){
          me.knowledgeMapFormVisible(true);
          action.Name="new";
          me.knowledgeMapName("");
          me.knowledgeMapDescription("");
          me.formHeading("Create New");
          selectedKnowledgeMapId(0);
     }
    
    OTS.Views.KnowledgeMapsView.prototype.SubmitNewKnowledgeMap=function(event){
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
      
     }
     
      OTS.Views.KnowledgeMapsView.prototype.Display=function(){
           return me;
      
     }
     
     // ******************Private Functions *********************************
     
     
     var UpdateTreeView=function(item){
                  
                   var data=[]; 
                   var _data = {id: item.id, label:item.label,identity:item.identity,
                                 description:item.description, children:item.children  
                        };
                       data.push(_data);
                  
                     if(!treeView.IsInitialized){
                 
                    var tree=  $('#tv-knowledgemap').tree({
                             data: data, autoOpen: true
                       });
                       
                             tree.jqTreeContextMenu($('#myMenu'), {
              "edit": function (node) { alert('Edit node: ' + node.name); },
              "delete": function (node) { alert('Delete node: ' + node.name); },
              "add": function (node) { alert('Add node: ' + node.name); }
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
                      description:content.description,children:content.children};
         UpdateTreeView(item);
         var message="Knowledge map";
         switch(action){
             case "new":
                   var conceptNode=new OTS.Views.ConceptNode(item);
                   conceptNode.UpdateIdentity(identity);
                   me.knowledgeMaplist.push(item);
                   me.responseMessage(message + " created");
                   me.responseBoxStyle("alert alert-success");
             break;
            case "rename":
               var  item1= findKnowledgeMap(id);
               var index= me.knowledgeMaplist.indexOf(item1);
                me.knowledgeMaplist.splice(index,1,item);
                me.responseMessage(message + " renamed");
                me.responseBoxStyle("alert alert-success");
                 
                 break;
          case "delete":
                var  itemToDelete= findKnowledgeMap(id);
             //  var index= me.knowledgeMaplist.indexOf(itemToDelete);
                me.knowledgeMaplist.remove(itemToDelete)
                me.responseMessage(message + " deleted");
                me.responseBoxStyle("alert alert-success");
                 break;
             
           case "duplicate":
               var conceptNode=new OTS.Views.ConceptNode(item);
                conceptNode.UpdateIdentity(identity);
                me.knowledgeMaplist.push(item);
                me.responseMessage(message + "has been duplicated");
                me.responseBoxStyle("alert alert-success");
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
    
}