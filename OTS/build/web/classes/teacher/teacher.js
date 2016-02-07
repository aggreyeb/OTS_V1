var teacherWindow=function(){
     var me=this;
    
    $(function(){
           $("#header").find("#logout").click(function(e){
                       e.preventDefault();
                       me.logout();
                    }); 
    });
   /*
    me.courses=ko.observableArray([
                     {id:"1",number:'100',name:'Introduction to Botany'},
                     {id:"2",number:'200',name:'Advance Level Art'}
                 ]);
    */
     me.logout=function(){
        
         $.post("AuthenticationServlet",{action:"logout"},function(msg){
              
              if(isResponseOK(msg))
               {
                    window.location.href="index.jsp";
               }
            });
            
     };
      var isResponseOK=function(msg){
              var result=JSON.parse(msg);
              if(result.response.status==="ok"){
                  return true;
              }
              return false;
        } ;
      
  }
 