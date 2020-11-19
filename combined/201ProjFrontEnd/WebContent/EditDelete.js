




function edit(id,newRev){
	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'http://localhost:8080/TestCombined/EditReviewServlet?revid='+id+'&editedRev='+newRev, true);
    xhr.send(null);
	xhr.onload = function (e) {
  		if (xhr.readyState === 4) {
    		if (xhr.status === 200) {
      			if(xhr.responseText.trim() === "success"){
					alert("success");
				}else{
					alert("fail");
				}
   			 } else {
     			 alert("fail");
   			}
  		}
	};
}

function del(id){
	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'http://localhost:8080/TestCombined/DeleteReviewServlet?revid='+id, true);
    xhr.send(null);
	xhr.onload = function (e) {
  		if (xhr.readyState === 4) {
    		if (xhr.status === 200) {
      			if(xhr.responseText.trim() === "success"){
					//document.getElementById(id).remove();
				}else{
					alert("fail");
				}
   			 } else {
     		 console.error(xhr.statusText);
   			}
  		}
	};
	
}


$(document).ready(function() {
   	
	
	
	 $('.deletebtn').click(function() {
            let id = $(this).attr("revid");
			//alert($(this).parent().parent().attr("id"));
			del(id);
     });
       
	
	$('.editbtn').click(function() {
			
				let id2 = $(this).attr("revid");
				let oldRev = $("#rev"+id2).html();
				$("#rev"+id2).html("<textarea>"+oldRev+"</textarea>");
				let child1 = $("#rev"+id2).next().children(":first-child");
				console.log(child1);
				let child2 = child1.next();
				console.log(child2);
				console.log(child1.hasClass("hidden"));
				
				child1.hide();
				child2.show();
			
			
      });

	$('.confirm').click(function(){
		
		let id3 = $(this).attr("revid");
		let text = $("#rev"+id3).children(":first-child").val();
		$("#rev"+id3).html(text);
		//SERVER CALL
		edit(id3,text);
		let child1 = $("#rev"+id3).next().children(":first-child");
		let child2 = child1.next();
		child1.show();
		child2.hide();
	
	
	});
 

});

