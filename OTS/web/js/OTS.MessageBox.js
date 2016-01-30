var OTS = OTS || {};
OTS.MessageBox = function(divContainer) {

	var me = this;
	var container = divContainer;
    me.Show = function() {
    	$("#" + container).show();
    };

    me.Hide = function() {
        $("#" + container).hide();
    };

	me.DisplayError = function (htmlMessage) {
		$("#" + container).html(htmlMessage);
		$("#" + container).removeClass("alert  alert-success");
		$("#" + container).removeClass("alert alert-warning");
		$("#" + container).removeClass("alert alert-info");
		$("#" + container).addClass("alert alert-danger");
	    me.Show();
	};

	me.DisplaySuccess = function (html) {
		$("#" + container).html(html);
		$("#" + container).removeClass("alert alert-warning");
		$("#" + container).removeClass("alert alert-danger");
		$("#" + container).removeClass("alert alert-info");
		$("#" + container).addClass("alert  alert-success");
		me.Show();
	};

   me.DisplayInformation = function (html) {
		$("#" + container).html(html);
		$("#" + container).removeClass("alert alert-warning");
		$("#" + container).removeClass("alert alert-danger");
		$("#" + container).removeClass("alert alert-success");
		$("#" + container).addClass("alert alert alert-info");
		me.Show();
   };

   me.DisplayWarning = function (html) {
   		$("#" + container).html(html);
   		$("#" + container).removeClass("alert alert-danger");
   		$("#" + container).removeClass("alert alert-success");
   		$("#" + container).removeClass("alert alert-info");
   		$("#" + container).addClass("alert  alert-warning");
   		me.Show();
   };
  };