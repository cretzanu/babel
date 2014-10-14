/**
 * get param by name from URI
 */
function getParameterByName(name) {
 return decodeURIComponent((new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.search)||[,""])[1].replace(/\+/g, '%20'))||null;
}

/**
* @param array 
* @param propertName
* @returns
*/
function sortByProperty(array, propertName) {
   return array.sort(function(a, b) {
       var x = a[propertName]; var y = b[propertName];
       return ((x < y) ? -1 : ((x > y) ? 1 : 0));
   });
}

//GRID handling
//3. helper functions for grid CRUD
function removeSelectedGridRow(grid){
	var x = grid.select();
	if ("undefined" == x) {
		alert ("No rows selected! Please select the row to delete...")
		//displayMessage(RequisitionAPPMessages.GRID_NO_ROWS_SELECTED,__errorTypeEnum.WARNING);
		return;
	}
	var rowIndex = -1;
	try {
		rowIndex = x[0].rowIndex;
	} 
	catch (e) {//undefined-->no row selected
		alert("No rows selected! Please select the row to delete...");
		//displayMessage(RequisitionAPPMessages.GRID_NO_ROWS_SELECTED,__errorTypeEnum.WARNING);
		return;
	}
	
	
	var row = grid.tbody.find(">tr:not(.k-grouping-row)").eq(rowIndex);
	grid.removeRow(row);
	
}

//AJAX handling//
var errWindow = undefined;
var errors = undefined;

/**
 * @param jqXHR
 * @param error
 * @param errorThrown
//1. error management for AJAX + ajax working gif
 */
function handleAjaxCallsInError (jqXHR,error, errorThrown) { 
	       if(jqXHR.status && jqXHR.status==400){
	               	    //alert("eroarea mea " + jqXHR.responseText); 
	                    if (undefined == errWindow){
	                    	$("<div id='errWindowId'></div>").appendTo('body'); 
	                    	errWindow = $("#errWindowId");
	                    } else {
	                    	errWindow.empty();// remove previous errors
	                    }
	                    errors = jQuery.parseJSON(JSON.stringify(JSON.stringify(jqXHR.responseText)));
	                    openErrorWindow(errWindow, errors);
	                    $("#errBtn").show();
	               }else{
	            	  // alert("Ooups... Something went wrong: " + error + "/"+errorThrown );
	            	   displayMessage("Ooups... Something went wrong: " + errorThrown + "/"+ jQuery.parseJSON(JSON.stringify(JSON.stringify(jqXHR.responseText))).message,__errorTypeEnum.ERROR);
	               }
	      }


/**
 * @param jqXHR
 * @param settings
 */
function onAjaxStart (jqXHR, settings){ document.getElementById("ajaxLoading").className = "ajaxvisible";}
/**
 * @param jqXHR
 * @param textStatus
 */
function onAjaxComplete (jqXHR, textStatus){//alert("ajax status = " + jqXHR.status);
	try {
		document.getElementById("ajaxLoading").className = "ajaxinvisible";
	}catch(e) {//do nothing
	}
}

/**
 * @param jqXHR
 * @param settings
 */
function onAjaxStartWfPopUp (jqXHR, settings){ document.getElementById("ajaxLoadingWf").className = "ajaxvisible";}
/**
 * @param jqXHR
 * @param textStatus
 */
function onAjaxCompleteWfPopUp (jqXHR, textStatus){//alert("ajax status = " + jqXHR.status);
	try {
		document.getElementById("ajaxLoadingWf").className = "ajaxinvisible";
	}catch(e) {//do nothing
	}
}



/**
 * @param errorMessage 			- String error message to be displayed
 * @param errorType				- Error type to choose the icon (Aveilable errot types are @ __errorTypeEnum )
 */
function displayMessage(message,errorType){
	var errorMap = {};
	// add a item
	errorMap[errorType] = message;
	displayMessageMap(errorMap);
}


/**
 * @param messages 			- Map of String error message to be displayed
 */
function displayMessageMap(messages){
	 if (undefined == errWindow){
	     	$("<div id='errWindowId'></div>").appendTo('body'); 
	     	errWindow = $("#errWindowId");
	     } else {
	     	errWindow.empty();// remove previous errors
	     }
	 		$("#errBtn").show();
		
		//alert ("fixe me...if uncoment the foloswing code, the IE complains on page load");
		if(!errWindow.data("kendoWindow")){
			errWindow.kendoWindow({
				width: "400px",
	    	    height: "200px",
	        	modal: true,
		        title: jQuery.i18n.prop('messageWindow.title'),
	    	    actions: ["Close"],
	        	content: "",
		        resizable: true,
		        close: onClose
	        });
		} 
		errWindow.data("kendoWindow").content("");
		errWindow.append("<div align='left'>" + jQuery.i18n.prop('messageWindow.info') + "</div><br/>");
		mapErrorsToFields(messages, errWindow);
		errWindow.data("kendoWindow").center()
	   						 .refresh()
							 .open();
}
