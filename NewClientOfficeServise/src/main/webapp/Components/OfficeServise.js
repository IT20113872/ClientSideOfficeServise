$(document).ready(function() {
	$("#alertSuccess").hide();
	$("#alertError").hide();
});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	// Form validation-------------------
	var status = validatePowerSourceForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}

	// If valid------------------------
	var type = ($("#hidPowerIDSubmit").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "SourceAPI",
		type : type,
		data : $("#formPower").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onPowerSaveComplete(response.responseText, status);
		}
	});
});

function onPowerSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);

		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();

			$("#divPowerSourceGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}

	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}

	$("#hidPowerIDSubmit").val("");
	$("#formPower")[0].reset();
}

// UPDATE==========================================
$(document).on(
		"click",
		".btnUpdate",
		function(event) {
			$("#hidPowerIDSubmit").val(
					$(this).closest("tr").find('#hidPowerSourceIDUpdate').val());		
			$("#officeID").val($(this).closest("tr").find('td:eq(0)').text());
			$("#officeName").val($(this).closest("tr").find('td:eq(1)').text());
			$("#officeType").val($(this).closest("tr").find('td:eq(2)').text());
			$("#officeAddress").val($(this).closest("tr").find('td:eq(3)').text());
			$("#officePhone").val($(this).closest("tr").find('td:eq(4)').text());
			$("#officeManager").val($(this).closest("tr").find('td:eq(5)').text());
			
		});

// REMOVE===========================================
$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "PowerSourceAPI",
		type : "DELETE",
		data : "ID=" + $(this).data("powersource"),
		dataType : "text",
		complete : function(response, status) {
			onPowerDeleteComplete(response.responseText, status);
		}
	});
});

function onPowerDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);

		if (resultSet.status.trim() == "success") {

			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();

			$("#divPowerSourceGrid").html(resultSet.data);

		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}

	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

// CLIENT-MODEL=========================================================================
// CLIENT-MODEL================================================================
function validatePowerSourceForm()
{
	 //OfficeID
	 if ($("#officeID").val().trim() == "") {
		return "Insert Office ID.";
	}
	 
	 //OfficeName
	 if ($("#officeName").val().trim() == "") {
		return "Insert Office Name.";
	}
	 
	 //OfficeType
	 if ($("#officeType").val().trim() == "") {
		return "Insert Office Type.";
	}
	 
	 //OfficeAddress
	 if ($("#officeAddress").val().trim() == "") {
		return "Insert Office Address.";
	}
	 
	 //officePhoneNumber
	 if ($("#officePhone").val().trim() == "") {
		return "Insert Phone Number.";
	}
	 
	 //ManagerID
	 if ($("#officeManager").val().trim() == "") {
		return "Insert Manager ID.";
	}
	
}