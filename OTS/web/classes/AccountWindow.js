var OTS=OTS||{};
var accountView=function(){
     var UserType={
         
         Administrator:1,
         Student:2,
         Teacher:3
         
     };
      var view={
           username:'#txt-username',
           password:'#txt-password',
           loginButton:'#lnk-login',
           submitButton:'#btn-login',
           closeLoginFormButton:'#close-login-form',
           messageBox:'#signin-alert',
           hideMessageBox:function(){
               $(view.messageBox).hide();
           },showMessageBox:function(){
                 $(view.messageBox).show();
           },displayMessage:function(message){
               $("#alert-text").text(message);
           }
         };
       
        $(function(){
           view.hideMessageBox();
          $(view.loginButton).popover({html:true, 
              title:"Testing",placement:'bottom',trigger:'click',
             title:function(){
                 return  $("#div-close-login-form").html();
             },content:function(){
                 return  $("#login-form").html();
             }}  

           );   
         
          $(view.loginButton).on('show.bs.popover', function () {


           });
       
         $(view.loginButton).on('shown.bs.popover', function () {
          $(window).keydown(function(e){
               if(e.keyCode == 13){
                 $("#btn-login").click();
               }
          });

         $(view.submitButton).click(Submit);
       
          $(view.closeLoginFormButton).click(function(){
              $('#lnk-login').popover('hide');
          });
       });   
    });
          

   var Submit=function(){
       view.hideMessageBox();
       var username=$(view.username).val();
        var password=$(view.password).val();
      
         $.post("AuthenticationServlet",{action:"login",username:username,password:password},onLogin);
   };
   
   var onLogin=function(msg){  
           var result=   JSON.parse(msg);
           if(result.response.status==="exception"){
               view.showMessageBox();
               view.displayMessage(result.response.error);
               return;
           }
            if(result.response.status==="ok"){
            var content=JSON.parse(result.response.content);
           // window.location.href="main.jsp" ;
            redirectToHomePage(content.UserTypeId);
            }
            else{
               view.showMessageBox();
            
            }
   };
   
   var redirectToHomePage=function(userType){
       if(userType===UserType.Teacher){
           window.location.href="main.jsp" ;
       }
       if(userType===UserType.Student){
            window.location.href="main_s.jsp" ;
       }
       if(userType===UserType.Administrator){
            window.location.href="main_a.jsp" ;
       }
      
   }
 }
       
   

  

     


    






    





