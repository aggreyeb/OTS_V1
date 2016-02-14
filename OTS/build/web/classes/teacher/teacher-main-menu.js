var OTS=OTS||{};
OTS.Views.TeacherMainMenu=function(){
     var me=this;
    var subscribers=[];
  
   
        $.get("templates/teacher/welcome.html",function(msg){
           $("#view-content").empty();
           $("#view-content").append(msg);
        });
   
    $(function(){
      
        $(".list-group-item").click(function(e){
           var currentItem="";
           if(e.currentTarget.innerText ===undefined || e.currentTarget.innerText===null ){
               currentItem=e.target.text;
             }
             else{
                  currentItem =e.currentTarget.innerText;
             }
            if(currentItem.trim()==="Home"){
                 me.changeHeading("Welcome");
            }
            else{
            me.changeHeading(currentItem);    
            }
            
            me.hideWelcome();
            
            me.notifySubscribers(currentItem); 
        });
    })
    
    this.notifySubscribers=function(name){
       
        for(var i=0;i< subscribers.length;i++){ 
                var item=  subscribers[i];
             item.menuClicked(name);
            
         }
        
    };
    me.addSubscriber=function(subscriber){
        subscribers.push(subscriber);
    };
    
   me.changeHeading=function(name){
       $("#lbl-selected-menuitem").text(name);
   };
   
   me.hideWelcome=function(){
         $("#view-welcome").hide();
   };
}

