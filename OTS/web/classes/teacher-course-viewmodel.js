var OTS=OTS||{};
OTS.ViewModels=OTS.ViewModels||{};
OTS.ViewModels.TeacherCoursesViewModel=function(){
    var me=this;
    
    me.teacherCouresList=ko.observableArray([]);
                                         
  
    me.teacherKnowledgeMaps=ko.observableArray([]);
    me.selectedKnowledgeMaps=ko.observableArray([]);
   me.tempselectedKnowledgeMaps=ko.observableArray([]);
    me.selectedCourse=null;
    me.selectedCourseName=ko.observable("");
    me.selectedCourseNumber=ko.observable("");
    me.dialogType={CRITICAL:"Critical !",SUCCESS: "Success !", FAIL :"Fail !"};
    me.CourseListHeading=ko.observable("Coureses");
    me.SelectedCourseHeading=ko.observable("Selected Course");
    me.responseMessageVisible=ko.observable(true);
    me.responseDialog=ko.observable("Mock Page!");
    me.responseMessageText=ko.observable("This is a mock page for review.Function not implemented"),
    me.responseBoxStyle=ko.observable("alert alert-info");
    me.knowledgeMaplistVisible=ko.observable(false);
    me.alreadySelected="no";
    me.courseKnowledgeMapCache=[];
    
     me.showLoadingMessage=function(){
           $("#div-loadingMessage").show();
      };
     
      me.hideLoadingMessage=function(){
           $("#div-loadingMessage").hide('slow');
      };
    $(function(){
        //get courses for teacher
      me.showLoadingMessage();
       $.post("CourseServlet",{action:"ListTeacherCourse"},function(msg){
        
           me.teacherCouresList([]);
           me.courseKnowledgeMapCache=[];
          var message =JSON.parse(msg);
          var contents=JSON.parse(message.response.content);
           for(var i=0;i<contents.length;i++){
            var item={
                     CourseId:contents[i].Id,
                     KnowledegeMaps:[],
                     SelectedKnowledgeMaps:[]
                 };
                 me.courseKnowledgeMapCache.push(item);
                 me.teacherCouresList.push(contents[i]);
                 
            }
       });
        //get all the knowlege map for the teacher
       $.post("CourseServlet",{action:"ListAllCourseKnowledgeMap"},function(msg){
          
           var message =JSON.parse(msg);
          var contents=JSON.parse(message.response.content);
           for(var i=0;i<contents.length;i++){
               contents[i].ActionText="Select";
               contents[i].CanEnableSelect=true;
               me.teacherKnowledgeMaps.push(contents[i]);
            }
             var teacherKnowledgeMaps=  ko.toJS( me.teacherKnowledgeMaps());
            for(var i=0;i<me.courseKnowledgeMapCache.length;i++){
              
              var array=  me.CreateNewTeacherKnowledgMaps(teacherKnowledgeMaps);
              me.courseKnowledgeMapCache[i].KnowledegeMaps=array;
            }
            
            if(me.courseKnowledgeMapCache.length<=0){ 
              me.ReloadTeacherCoureseKnowledgeMap();
            }
       });
       me.hideLoadingMessage();
    });
    
     me.CreateNewTeacherKnowledgMaps=function(array){
         var items=[];
         for(var i=0;i<array.length;i++){
             
             var item={
                 ActionText:array[i].ActionText,
                 CanEnableSelect:array[i].CanEnableSelect,
                 CourseTypeId:array[i].CourseTypeId,
                 Description:array[i].Description,
                 KnowledgeMapId:array[i].KnowledgeMapId,
                 Name:array[i].Name
             };
             items.push(item);
         }
         return items;
     };
     
    me.IsKnowledgeMapSelected=function(knowledgeMapId){
        if( me.selectedKnowledgeMaps().length==0){
            return true;
        }
        var found=true;
        for(var i=0;i< me.selectedKnowledgeMaps.length;i++){
         
          var result=  me.FindKnowledgeMap(me.selectedKnowledgeMaps()[i].KnowledgeMapId);
          
        }
         return found;
    };
  
    me.FindKnowledgeMap=function(knowledgeMapId){
        var found =null;
        for(var i=0;i< me.teacherKnowledgeMaps().length;i++){
            if(me.teacherKnowledgeMaps()[i].KnowledgeMapId===knowledgeMapId ){
                found=me.teacherKnowledgeMaps()[i];
                break;
            }
        }
        return found;
    };
    
    
    me.ReloadTeacherCoureseKnowledgeMap=function(){
         $.post("CourseServlet",{action:"ListAllCourseKnowledgeMap"},function(msg){
          
           var message =JSON.parse(msg);
          var contents=JSON.parse(message.response.content);
           for(var i=0;i<contents.length;i++){
               contents[i].ActionText="Select";
               contents[i].CanEnableSelect=true;
              // me.teacherKnowledgeMaps.push(contents[i]);
            }
             //var teacherKnowledgeMaps=  ko.toJS( me.teacherKnowledgeMaps());
            for(var i=0;i<me.courseKnowledgeMapCache.length;i++){
              
              var array=  me.CreateNewTeacherKnowledgMaps(contents);
              me.courseKnowledgeMapCache[i].KnowledegeMaps=array;
            }
       });
    };
    
    me.ReloadCourseKnowledgeMapFromCache=function(courseId){
         //get all the knowlege map for the teacher
         
         var item=null;
        
          for(var i=0;i<me.courseKnowledgeMapCache.length;i++){
              if(me.courseKnowledgeMapCache[i].CourseId===courseId){
                  item=me.courseKnowledgeMapCache[i];
                  break;
              }
          }
           if(item!==null){
           me.teacherKnowledgeMaps([]);
           var kms=item.KnowledegeMaps;
           for(var i=0;i<kms.length;i++){
              
               me.teacherKnowledgeMaps.push(kms[i]);
           }
           }
    };
    
    me.onCoureseSelected=function(item,event){
       
        var courseId=item.Id;
        me.ReloadCourseKnowledgeMapFromCache(courseId);
       
        $.post("CourseServlet",{action:"ListCourseKnowledgeMap",CourseId:courseId},function(msg){
          
          var message =JSON.parse(msg);
          var contents=JSON.parse(message.response.content);
            me.selectedKnowledgeMaps([]);
           for(var i=0;i<contents.length;i++){
              me.selectedKnowledgeMaps.push(contents[i]);
            }
         
           if(me.selectedKnowledgeMaps().length>0){
               
               for(var i=0;i<me.selectedKnowledgeMaps().length;i++){
                  var cnm= me.selectedKnowledgeMaps()[i];
                  var currentItem=me.FindKnowledgeMap(cnm.KnowledgeMapId);
                  if(currentItem!==null){
                      me.ToggleTeacherKnowledgeMap(cnm,cnm.CanEnableSelect,cnm.ActionText);
                  }
                 
               }
           }
           
           else{
                 for(var i=0;i<me.teacherKnowledgeMaps().length;i++){
                   var km=me.teacherKnowledgeMaps()[i];
                  
                       me.teacherKnowledgeMaps.replace(km,{ActionText:"Select",
                                                           CanEnableSelect:true,
                                                           CourseTypeId:0,
                                                           Description:km.Description,
                                                           KnowledgeMapId:km.KnowledgeMapId,
                                                           Name:km.Name});
               }
           }
            
        me.responseMessageVisible(false);
        me.selectedCourse=item;
        me.selectedCourseNumber(item.Number);
        me.selectedCourseName(item.Name);
        me.knowledgeMaplistVisible(true);
       });
  
     
    };
    
    me.ToggleTeacherKnowledgeMap=function(knowledgeMap, bStatus,actionText){
         for(var i=0;i<me.teacherKnowledgeMaps().length;i++){
                   var km=me.teacherKnowledgeMaps()[i];
                   if(km.KnowledgeMapId===knowledgeMap.KnowledgeMapId){
                       me.teacherKnowledgeMaps.replace(km,{ActionText:actionText,
                                                           CanEnableSelect:bStatus,
                                                           CourseTypeId:0,
                                                           Description:km.Description,
                                                           KnowledgeMapId:km.KnowledgeMapId,
                                                           Name:km.Name});
                          break;
                   }
                  
               }
    };
    
    me.oncloseMessageBox=function(event){
      
        me.responseMessageVisible(false);
    };
    
    
    me.onCancel=function(item,event){
    
        me.knowledgeMaplistVisible(false);
    };
    
       me.onSubmit=function(){
           var courseId=me.selectedCourse.Id;
           var selectedKnowledgeMaps=ko.toJS(me.selectedKnowledgeMaps);
           var courseKnowledge={
             courseId:courseId,kowledgeMaps:selectedKnowledgeMaps
         };  
         
        me.responseMessageVisible(true);
        
    };
    me.Associate=function(){
        
        
    };
    
    
    me.onSelect=function(item,event){
     
       var courseId=me.selectedCourse.Id;
       var knowledgeMapId=item.KnowledgeMapId;
       
       $.post("CourseServlet",{action:"AddCourseKnowledgeMap",CourseId:courseId,KnowledgeMapId:knowledgeMapId},function(msg){
         
         var message =JSON.parse(msg);
          var status=message.response.status;
          if(status=="ok"){
               me.selectedKnowledgeMaps.push(item);  
               for(var i=0;i<me.teacherKnowledgeMaps().length;i++){
                   var km=me.teacherKnowledgeMaps()[i];
                   if(km.KnowledgeMapId===item.KnowledgeMapId){
                       me.teacherKnowledgeMaps.replace(km,{ActionText:"Selected",
                                                           CanEnableSelect:false,
                                                           CourseTypeId:0,
                                                           Description:km.Description,
                                                           KnowledgeMapId:km.KnowledgeMapId,
                                                           Name:km.Name});
                          break;
                   }
                  
               }
             
          }
          else{
              if(message.response.content=="duplicate"){
                  alert("KnowledgeMap already selected");
              }
               
          }
          
       });
   
    };
    
   me.onRemove=function(item,event){     
       var courseId=me.selectedCourse.Id;
       var knowledgeMapId=item.KnowledgeMapId;
       $.post("CourseServlet",{action:"DeleteCourseKnowledgeMap",CourseId:courseId,KnowledgeMapId:knowledgeMapId},function(msg){
          var message =JSON.parse(msg);
          var status=message.response.status;
          if(status=="ok"){
               me.selectedKnowledgeMaps.remove(item); 
                 for(var i=0;i<me.teacherKnowledgeMaps().length;i++){
                   var km=me.teacherKnowledgeMaps()[i];
                   if(km.KnowledgeMapId===item.KnowledgeMapId){
                       me.teacherKnowledgeMaps.replace(km,{ActionText:"Select",
                                                           CanEnableSelect:true,
                                                           CourseTypeId:0,
                                                           Description:km.Description,
                                                           KnowledgeMapId:km.KnowledgeMapId,
                                                           Name:km.Name});
                         break;
                   }
                   
               }
          }
          else{
              alert(message.response.error);
          }
           
       });
      
   };
   
   me.hasSelectedKnowledgeMap=function(item){
       
       if( me.tempselectedKnowledgeMaps.indexOf(item)<0){
           
           return false;
       }
       return true;
   };
};
