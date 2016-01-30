var OTS=OTS||{};
OTS.Views.AdminAccountsView=function(){
   var me=this;
   var name="Accounts";
   me.menuClicked=function(menuName){
         //alert("Show");
         if(name===menuName.trim()){
            me.makeMenuActive();
            me.loadView();  
            me.show();
         }else{
             me.makeMenuInActive();
         }
   };
    me.show=function(){
       $("#view-test1").show();
    };
    
    me.hide=function(){
        $("#view-test1").hide();
    };
    
      me.loadView=function(){
        $.get("templates/admin/admin-accounts.html",function(msg){
           $("#view-content").empty();
           $("#view-content").append(msg);
        });
    };
    
      me.makeMenuActive=function(){
        $("lnk-accounts").removeClass("active");
        $("#lnk-accounts").addClass("active");
    };
    
     me.makeMenuInActive=function(){
        $("#lnk-accounts").removeClass("active");
     
    };
}
