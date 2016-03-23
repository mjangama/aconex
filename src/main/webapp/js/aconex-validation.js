//Contract form validations

$(document).ready(function(){

	$('#contractForm').validate({
		rules: {
			name: {
				required: true,
				minlength: 1,
				maxlength: 128
			},
			code: {
				required: true,
				minlength: 1,
				maxlength: 16
			},
			description: {
				minlength: 1,
				maxlength: 256
			},
			budget: {
				required: true,
				number: true,
				maxlength: 10,
				min: 1
			},
			committedCost: {
				required: true,
				number: true,
				maxlength: 10,
				min: 1
			},
			forecast: {
				required: true,
				number: true,
				maxlength: 10,
				min: 1
			},
			payment: {
				required: true,
				number: true,
				maxlength: 10,
				min: 0
			},
			completionPercentage: {
				required: true,
				digits: true,
				max: 100,
				min: 0
			},
			vendorId: {
				required: true
			}
		},
		highlight: function(element) {
			$(element).closest('.form-group').removeClass('success').addClass('error');
		},
		success: function(element) {
			element
				.text('OK!').addClass('valid')
				.closest('.form-group').removeClass('error').addClass('success');
		}
	});

});